
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
 * 兼职
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jianzhi")
public class JianzhiController {
    private static final Logger logger = LoggerFactory.getLogger(JianzhiController.class);

    private static final String TABLE_NAME = "jianzhi";

    @Autowired
    private JianzhiService jianzhiService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private JianzhiCollectionService jianzhiCollectionService;//兼职收藏
    @Autowired
    private JianzhiLiuyanService jianzhiLiuyanService;//兼职留言
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
        params.put("jianzhiDeleteStart",1);params.put("jianzhiDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = jianzhiService.queryPage(params);

        //字典表数据转换
        List<JianzhiView> list =(List<JianzhiView>)page.getList();
        for(JianzhiView c:list){
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
        JianzhiEntity jianzhi = jianzhiService.selectById(id);
        if(jianzhi !=null){
            //entity转view
            JianzhiView view = new JianzhiView();
            BeanUtils.copyProperties( jianzhi , view );//把实体数据重构到view中
            //级联表 商家
            //级联表
            ShangjiaEntity shangjia = shangjiaService.selectById(jianzhi.getShangjiaId());
            if(shangjia != null){
            BeanUtils.copyProperties( shangjia , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "shangjiaId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setShangjiaId(shangjia.getId());
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
    public R save(@RequestBody JianzhiEntity jianzhi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jianzhi:{}",this.getClass().getName(),jianzhi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("商家".equals(role))
            jianzhi.setShangjiaId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<JianzhiEntity> queryWrapper = new EntityWrapper<JianzhiEntity>()
            .eq("shangjia_id", jianzhi.getShangjiaId())
            .eq("jianzhi_name", jianzhi.getJianzhiName())
            .eq("zan_number", jianzhi.getZanNumber())
            .eq("cai_number", jianzhi.getCaiNumber())
            .eq("jianzhi_types", jianzhi.getJianzhiTypes())
            .eq("jianzhi_jiesuan_types", jianzhi.getJianzhiJiesuanTypes())
            .eq("jianzhi_kucun_number", jianzhi.getJianzhiKucunNumber())
            .eq("jianzhi_phone", jianzhi.getJianzhiPhone())
            .eq("jianzhi_address", jianzhi.getJianzhiAddress())
            .eq("jianzhi_shijian", jianzhi.getJianzhiShijian())
            .eq("jianzhi_daiyu", jianzhi.getJianzhiDaiyu())
            .eq("jianzhi_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JianzhiEntity jianzhiEntity = jianzhiService.selectOne(queryWrapper);
        if(jianzhiEntity==null){
            jianzhi.setJianzhiDelete(1);
            jianzhi.setInsertTime(new Date());
            jianzhi.setCreateTime(new Date());
            jianzhiService.insert(jianzhi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JianzhiEntity jianzhi, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,jianzhi:{}",this.getClass().getName(),jianzhi.toString());
        JianzhiEntity oldJianzhiEntity = jianzhiService.selectById(jianzhi.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("商家".equals(role))
//            jianzhi.setShangjiaId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(jianzhi.getJianzhiPhoto()) || "null".equals(jianzhi.getJianzhiPhoto())){
                jianzhi.setJianzhiPhoto(null);
        }

            jianzhiService.updateById(jianzhi);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JianzhiEntity> oldJianzhiList =jianzhiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<JianzhiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            JianzhiEntity jianzhiEntity = new JianzhiEntity();
            jianzhiEntity.setId(id);
            jianzhiEntity.setJianzhiDelete(2);
            list.add(jianzhiEntity);
        }
        if(list != null && list.size() >0){
            jianzhiService.updateBatchById(list);
        }

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
            List<JianzhiEntity> jianzhiList = new ArrayList<>();//上传的东西
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
                            JianzhiEntity jianzhiEntity = new JianzhiEntity();
//                            jianzhiEntity.setShangjiaId(Integer.valueOf(data.get(0)));   //商家 要改的
//                            jianzhiEntity.setJianzhiName(data.get(0));                    //兼职名称 要改的
//                            jianzhiEntity.setJianzhiUuidNumber(data.get(0));                    //兼职编号 要改的
//                            jianzhiEntity.setJianzhiPhoto("");//详情和图片
//                            jianzhiEntity.setZanNumber(Integer.valueOf(data.get(0)));   //赞 要改的
//                            jianzhiEntity.setCaiNumber(Integer.valueOf(data.get(0)));   //踩 要改的
//                            jianzhiEntity.setJianzhiTypes(Integer.valueOf(data.get(0)));   //兼职类型 要改的
//                            jianzhiEntity.setJianzhiJiesuanTypes(Integer.valueOf(data.get(0)));   //结算类型 要改的
//                            jianzhiEntity.setJianzhiKucunNumber(Integer.valueOf(data.get(0)));   //招聘人数 要改的
//                            jianzhiEntity.setJianzhiPhone(data.get(0));                    //联系方式 要改的
//                            jianzhiEntity.setJianzhiAddress(data.get(0));                    //工作地点 要改的
//                            jianzhiEntity.setJianzhiShijian(data.get(0));                    //工作时间 要改的
//                            jianzhiEntity.setJianzhiDaiyu(data.get(0));                    //薪资/小时 要改的
//                            jianzhiEntity.setJianzhiContent("");//详情和图片
//                            jianzhiEntity.setJianzhiDelete(1);//逻辑删除字段
//                            jianzhiEntity.setInsertTime(date);//时间
//                            jianzhiEntity.setCreateTime(date);//时间
                            jianzhiList.add(jianzhiEntity);


                            //把要查询是否重复的字段放入map中
                                //兼职编号
                                if(seachFields.containsKey("jianzhiUuidNumber")){
                                    List<String> jianzhiUuidNumber = seachFields.get("jianzhiUuidNumber");
                                    jianzhiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> jianzhiUuidNumber = new ArrayList<>();
                                    jianzhiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("jianzhiUuidNumber",jianzhiUuidNumber);
                                }
                                //联系方式
                                if(seachFields.containsKey("jianzhiPhone")){
                                    List<String> jianzhiPhone = seachFields.get("jianzhiPhone");
                                    jianzhiPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> jianzhiPhone = new ArrayList<>();
                                    jianzhiPhone.add(data.get(0));//要改的
                                    seachFields.put("jianzhiPhone",jianzhiPhone);
                                }
                        }

                        //查询是否重复
                         //兼职编号
                        List<JianzhiEntity> jianzhiEntities_jianzhiUuidNumber = jianzhiService.selectList(new EntityWrapper<JianzhiEntity>().in("jianzhi_uuid_number", seachFields.get("jianzhiUuidNumber")).eq("jianzhi_delete", 1));
                        if(jianzhiEntities_jianzhiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JianzhiEntity s:jianzhiEntities_jianzhiUuidNumber){
                                repeatFields.add(s.getJianzhiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [兼职编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //联系方式
                        List<JianzhiEntity> jianzhiEntities_jianzhiPhone = jianzhiService.selectList(new EntityWrapper<JianzhiEntity>().in("jianzhi_phone", seachFields.get("jianzhiPhone")).eq("jianzhi_delete", 1));
                        if(jianzhiEntities_jianzhiPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JianzhiEntity s:jianzhiEntities_jianzhiPhone){
                                repeatFields.add(s.getJianzhiPhone());
                            }
                            return R.error(511,"数据库的该表中的 [联系方式] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jianzhiService.insertBatch(jianzhiList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<JianzhiView> returnJianzhiViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = jianzhiOrderService.queryPage(params1);
        List<JianzhiOrderView> orderViewsList =(List<JianzhiOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(JianzhiOrderView orderView:orderViewsList){
            Integer jianzhiTypes = orderView.getJianzhiTypes();
            if(typeMap.containsKey(jianzhiTypes)){
                typeMap.put(jianzhiTypes,typeMap.get(jianzhiTypes)+1);
            }else{
                typeMap.put(jianzhiTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("jianzhiTypes",type);
            PageUtils pageUtils1 = jianzhiService.queryPage(params2);
            List<JianzhiView> jianzhiViewList =(List<JianzhiView>)pageUtils1.getList();
            returnJianzhiViewList.addAll(jianzhiViewList);
            if(returnJianzhiViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = jianzhiService.queryPage(params);
        if(returnJianzhiViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnJianzhiViewList.size();//要添加的数量
            List<JianzhiView> jianzhiViewList =(List<JianzhiView>)page.getList();
            for(JianzhiView jianzhiView:jianzhiViewList){
                Boolean addFlag = true;
                for(JianzhiView returnJianzhiView:returnJianzhiViewList){
                    if(returnJianzhiView.getId().intValue() ==jianzhiView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnJianzhiViewList.add(jianzhiView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnJianzhiViewList = returnJianzhiViewList.subList(0, limit);
        }

        for(JianzhiView c:returnJianzhiViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnJianzhiViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = jianzhiService.queryPage(params);

        //字典表数据转换
        List<JianzhiView> list =(List<JianzhiView>)page.getList();
        for(JianzhiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JianzhiEntity jianzhi = jianzhiService.selectById(id);
            if(jianzhi !=null){


                //entity转view
                JianzhiView view = new JianzhiView();
                BeanUtils.copyProperties( jianzhi , view );//把实体数据重构到view中

                //级联表
                    ShangjiaEntity shangjia = shangjiaService.selectById(jianzhi.getShangjiaId());
                if(shangjia != null){
                    BeanUtils.copyProperties( shangjia , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShangjiaId(shangjia.getId());
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
    public R add(@RequestBody JianzhiEntity jianzhi, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jianzhi:{}",this.getClass().getName(),jianzhi.toString());
        Wrapper<JianzhiEntity> queryWrapper = new EntityWrapper<JianzhiEntity>()
            .eq("shangjia_id", jianzhi.getShangjiaId())
            .eq("jianzhi_name", jianzhi.getJianzhiName())
            .eq("jianzhi_uuid_number", jianzhi.getJianzhiUuidNumber())
            .eq("zan_number", jianzhi.getZanNumber())
            .eq("cai_number", jianzhi.getCaiNumber())
            .eq("jianzhi_types", jianzhi.getJianzhiTypes())
            .eq("jianzhi_jiesuan_types", jianzhi.getJianzhiJiesuanTypes())
            .eq("jianzhi_kucun_number", jianzhi.getJianzhiKucunNumber())
            .eq("jianzhi_phone", jianzhi.getJianzhiPhone())
            .eq("jianzhi_address", jianzhi.getJianzhiAddress())
            .eq("jianzhi_shijian", jianzhi.getJianzhiShijian())
            .eq("jianzhi_daiyu", jianzhi.getJianzhiDaiyu())
            .eq("jianzhi_delete", jianzhi.getJianzhiDelete())
//            .notIn("jianzhi_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JianzhiEntity jianzhiEntity = jianzhiService.selectOne(queryWrapper);
        if(jianzhiEntity==null){
                jianzhi.setZanNumber(1);
                jianzhi.setCaiNumber(1);
            jianzhi.setJianzhiDelete(1);
            jianzhi.setInsertTime(new Date());
            jianzhi.setCreateTime(new Date());
        jianzhiService.insert(jianzhi);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

