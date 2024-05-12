
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 兼职留言
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jianzhiLiuyan")
public class JianzhiLiuyanController {
    private static final Logger logger = LoggerFactory.getLogger(JianzhiLiuyanController.class);

    private static final String TABLE_NAME = "jianzhiLiuyan";

    @Autowired
    private JianzhiLiuyanService jianzhiLiuyanService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private JianzhiService jianzhiService;//兼职
    @Autowired
    private JianzhiCollectionService jianzhiCollectionService;//兼职收藏
    @Autowired
    private JianzhiOrderService jianzhiOrderService;//兼职申请
    @Autowired
    private ShangjiaService shangjiaService;//商家
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("商家".equals(role))
            params.put("shangjiaId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = jianzhiLiuyanService.queryPage(params);

        //字典表数据转换
        List<JianzhiLiuyanView> list =(List<JianzhiLiuyanView>)page.getList();
        for(JianzhiLiuyanView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JianzhiLiuyanEntity jianzhiLiuyan = jianzhiLiuyanService.selectById(id);
        if(jianzhiLiuyan !=null){
            //entity转view
            JianzhiLiuyanView view = new JianzhiLiuyanView();
            BeanUtils.copyProperties( jianzhiLiuyan , view );//把实体数据重构到view中
            //级联表 兼职
            //级联表
            JianzhiEntity jianzhi = jianzhiService.selectById(jianzhiLiuyan.getJianzhiId());
            if(jianzhi != null){
            BeanUtils.copyProperties( jianzhi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setJianzhiId(jianzhi.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(jianzhiLiuyan.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody JianzhiLiuyanEntity jianzhiLiuyan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jianzhiLiuyan:{}",this.getClass().getName(),jianzhiLiuyan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            jianzhiLiuyan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        jianzhiLiuyan.setCreateTime(new Date());
        jianzhiLiuyan.setInsertTime(new Date());
        jianzhiLiuyanService.insert(jianzhiLiuyan);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JianzhiLiuyanEntity jianzhiLiuyan, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,jianzhiLiuyan:{}",this.getClass().getName(),jianzhiLiuyan.toString());
        JianzhiLiuyanEntity oldJianzhiLiuyanEntity = jianzhiLiuyanService.selectById(jianzhiLiuyan.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            jianzhiLiuyan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        jianzhiLiuyan.setUpdateTime(new Date());

            jianzhiLiuyanService.updateById(jianzhiLiuyan);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JianzhiLiuyanEntity> oldJianzhiLiuyanList =jianzhiLiuyanService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        jianzhiLiuyanService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<JianzhiLiuyanEntity> jianzhiLiuyanList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            JianzhiLiuyanEntity jianzhiLiuyanEntity = new JianzhiLiuyanEntity();
//                            jianzhiLiuyanEntity.setJianzhiId(Integer.valueOf(data.get(0)));   //兼职 要改的
//                            jianzhiLiuyanEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            jianzhiLiuyanEntity.setJianzhiLiuyanText(data.get(0));                    //留言内容 要改的
//                            jianzhiLiuyanEntity.setInsertTime(date);//时间
//                            jianzhiLiuyanEntity.setReplyText(data.get(0));                    //回复内容 要改的
//                            jianzhiLiuyanEntity.setUpdateTime(sdf.parse(data.get(0)));          //回复时间 要改的
//                            jianzhiLiuyanEntity.setCreateTime(date);//时间
                            jianzhiLiuyanList.add(jianzhiLiuyanEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        jianzhiLiuyanService.insertBatch(jianzhiLiuyanList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = jianzhiLiuyanService.queryPage(params);

        //字典表数据转换
        List<JianzhiLiuyanView> list =(List<JianzhiLiuyanView>)page.getList();
        for(JianzhiLiuyanView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JianzhiLiuyanEntity jianzhiLiuyan = jianzhiLiuyanService.selectById(id);
            if(jianzhiLiuyan !=null){


                //entity转view
                JianzhiLiuyanView view = new JianzhiLiuyanView();
                BeanUtils.copyProperties( jianzhiLiuyan , view );//把实体数据重构到view中

                //级联表
                    JianzhiEntity jianzhi = jianzhiService.selectById(jianzhiLiuyan.getJianzhiId());
                if(jianzhi != null){
                    BeanUtils.copyProperties( jianzhi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJianzhiId(jianzhi.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(jianzhiLiuyan.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody JianzhiLiuyanEntity jianzhiLiuyan, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jianzhiLiuyan:{}",this.getClass().getName(),jianzhiLiuyan.toString());
        jianzhiLiuyan.setCreateTime(new Date());
        jianzhiLiuyan.setInsertTime(new Date());
        jianzhiLiuyanService.insert(jianzhiLiuyan);

            return R.ok();
        }

}

