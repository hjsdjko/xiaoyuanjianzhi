
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
 * 兼职申请
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jianzhiOrder")
public class JianzhiOrderController {
    private static final Logger logger = LoggerFactory.getLogger(JianzhiOrderController.class);

    private static final String TABLE_NAME = "jianzhiOrder";

    @Autowired
    private JianzhiOrderService jianzhiOrderService;


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
    private JianzhiLiuyanService jianzhiLiuyanService;//兼职留言
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
        PageUtils page = jianzhiOrderService.queryPage(params);

        //字典表数据转换
        List<JianzhiOrderView> list =(List<JianzhiOrderView>)page.getList();
        for(JianzhiOrderView c:list){
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
        JianzhiOrderEntity jianzhiOrder = jianzhiOrderService.selectById(id);
        if(jianzhiOrder !=null){
            //entity转view
            JianzhiOrderView view = new JianzhiOrderView();
            BeanUtils.copyProperties( jianzhiOrder , view );//把实体数据重构到view中
            //级联表 兼职
            //级联表
            JianzhiEntity jianzhi = jianzhiService.selectById(jianzhiOrder.getJianzhiId());
            if(jianzhi != null){
            BeanUtils.copyProperties( jianzhi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setJianzhiId(jianzhi.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(jianzhiOrder.getYonghuId());
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
    public R save(@RequestBody JianzhiOrderEntity jianzhiOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jianzhiOrder:{}",this.getClass().getName(),jianzhiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            jianzhiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        jianzhiOrder.setCreateTime(new Date());
        jianzhiOrder.setInsertTime(new Date());
        jianzhiOrderService.insert(jianzhiOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JianzhiOrderEntity jianzhiOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,jianzhiOrder:{}",this.getClass().getName(),jianzhiOrder.toString());
        JianzhiOrderEntity oldJianzhiOrderEntity = jianzhiOrderService.selectById(jianzhiOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            jianzhiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            jianzhiOrderService.updateById(jianzhiOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JianzhiOrderEntity> oldJianzhiOrderList =jianzhiOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        jianzhiOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<JianzhiOrderEntity> jianzhiOrderList = new ArrayList<>();//上传的东西
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
                            JianzhiOrderEntity jianzhiOrderEntity = new JianzhiOrderEntity();
//                            jianzhiOrderEntity.setJianzhiOrderUuidNumber(data.get(0));                    //订单编号 要改的
//                            jianzhiOrderEntity.setJianzhiId(Integer.valueOf(data.get(0)));   //兼职 要改的
//                            jianzhiOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            jianzhiOrderEntity.setJianzhiOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            jianzhiOrderEntity.setInsertTime(date);//时间
//                            jianzhiOrderEntity.setCreateTime(date);//时间
                            jianzhiOrderList.add(jianzhiOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单编号
                                if(seachFields.containsKey("jianzhiOrderUuidNumber")){
                                    List<String> jianzhiOrderUuidNumber = seachFields.get("jianzhiOrderUuidNumber");
                                    jianzhiOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> jianzhiOrderUuidNumber = new ArrayList<>();
                                    jianzhiOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("jianzhiOrderUuidNumber",jianzhiOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单编号
                        List<JianzhiOrderEntity> jianzhiOrderEntities_jianzhiOrderUuidNumber = jianzhiOrderService.selectList(new EntityWrapper<JianzhiOrderEntity>().in("jianzhi_order_uuid_number", seachFields.get("jianzhiOrderUuidNumber")));
                        if(jianzhiOrderEntities_jianzhiOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JianzhiOrderEntity s:jianzhiOrderEntities_jianzhiOrderUuidNumber){
                                repeatFields.add(s.getJianzhiOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jianzhiOrderService.insertBatch(jianzhiOrderList);
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
        PageUtils page = jianzhiOrderService.queryPage(params);

        //字典表数据转换
        List<JianzhiOrderView> list =(List<JianzhiOrderView>)page.getList();
        for(JianzhiOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JianzhiOrderEntity jianzhiOrder = jianzhiOrderService.selectById(id);
            if(jianzhiOrder !=null){


                //entity转view
                JianzhiOrderView view = new JianzhiOrderView();
                BeanUtils.copyProperties( jianzhiOrder , view );//把实体数据重构到view中

                //级联表
                    JianzhiEntity jianzhi = jianzhiService.selectById(jianzhiOrder.getJianzhiId());
                if(jianzhi != null){
                    BeanUtils.copyProperties( jianzhi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJianzhiId(jianzhi.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(jianzhiOrder.getYonghuId());
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
    public R add(@RequestBody JianzhiOrderEntity jianzhiOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jianzhiOrder:{}",this.getClass().getName(),jianzhiOrder.toString());
            JianzhiEntity jianzhiEntity = jianzhiService.selectById(jianzhiOrder.getJianzhiId());
            if(jianzhiEntity == null){
                return R.error(511,"查不到该兼职");
            }
            // Double jianzhiNewMoney = jianzhiEntity.getJianzhiNewMoney();

            if(false){
            }
            else if((jianzhiEntity.getJianzhiKucunNumber() -1)<0){
                return R.error(511,"购买数量不能大于库存数量");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            jianzhiOrder.setJianzhiOrderTypes(101); //设置订单状态为已申请
            jianzhiOrder.setYonghuId(userId); //设置订单支付人id
            jianzhiOrder.setJianzhiOrderUuidNumber(String.valueOf(new Date().getTime()));
            jianzhiOrder.setInsertTime(new Date());
            jianzhiOrder.setCreateTime(new Date());
                jianzhiEntity.setJianzhiKucunNumber( jianzhiEntity.getJianzhiKucunNumber() -1);
                jianzhiService.updateById(jianzhiEntity);
                jianzhiOrderService.insert(jianzhiOrder);//新增订单

            ShangjiaEntity shangjiaEntity = shangjiaService.selectById(jianzhiEntity.getShangjiaId());
            shangjiaService.updateById(shangjiaEntity);

            return R.ok();
    }
    /**
     * 添加订单
     */
    @RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
        String jianzhiOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");


        String data = String.valueOf(params.get("jianzhis"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> jianzhis = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<JianzhiOrderEntity> jianzhiOrderList = new ArrayList<>();
        //商家表
        ArrayList<ShangjiaEntity> shangjiaList = new ArrayList<>();
        //商品表
        List<JianzhiEntity> jianzhiList = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);

        //循环取出需要的数据
        for (Map<String, Object> map : jianzhis) {
           //取值
            Integer jianzhiId = Integer.valueOf(String.valueOf(map.get("jianzhiId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            JianzhiEntity jianzhiEntity = jianzhiService.selectById(jianzhiId);//购买的商品
            String id = String.valueOf(map.get("id"));
            //获取商家信息
            Integer shangjiaId = jianzhiEntity.getShangjiaId();
            ShangjiaEntity shangjiaEntity = shangjiaService.selectById(shangjiaId);//商家

            //判断商品的库存是否足够
            if(jianzhiEntity.getJianzhiKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(jianzhiEntity.getJianzhiName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                jianzhiEntity.setJianzhiKucunNumber(jianzhiEntity.getJianzhiKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            JianzhiOrderEntity jianzhiOrderEntity = new JianzhiOrderEntity<>();

            //赋值订单信息
            jianzhiOrderEntity.setJianzhiOrderUuidNumber(jianzhiOrderUuidNumber);//订单编号
            jianzhiOrderEntity.setJianzhiId(jianzhiId);//兼职
                        jianzhiOrderEntity.setYonghuId(userId);//用户
            jianzhiOrderEntity.setJianzhiOrderTypes(101);//订单类型
            jianzhiOrderEntity.setInsertTime(new Date());//订单创建时间
            jianzhiOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
//            if(jianzhiOrderPaymentTypes == 1){//余额支付
//                //计算金额
//                Double money = new BigDecimal(jianzhiEntity.getJianzhiNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();
//
//                if(yonghuEntity.getNewMoney() - money <0 ){
//                    return R.error("余额不足,请充值！！！");
//                }else{
//                    //计算所获得积分
//                    Double buyJifen =0.0;
//
//
//                    jianzhiOrderEntity.setJianzhiOrderTruePrice(money);
//
//                    //修改商家余额
//                    shangjiaEntity.setNewMoney(shangjiaEntity.getNewMoney()+money);
//                }
//            }
            jianzhiOrderList.add(jianzhiOrderEntity);
            shangjiaList.add(shangjiaEntity);
            jianzhiList.add(jianzhiEntity);

        }
        jianzhiOrderService.insertBatch(jianzhiOrderList);
        shangjiaService.updateBatchById(shangjiaList);
        jianzhiService.updateBatchById(jianzhiList);
        yonghuService.updateById(yonghuEntity);

        return R.ok();
    }


    /**
    * 取消申请
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            JianzhiOrderEntity jianzhiOrder = jianzhiOrderService.selectById(id);//当前表service
            Integer jianzhiId = jianzhiOrder.getJianzhiId();
            if(jianzhiId == null)
                return R.error(511,"查不到该兼职");
            JianzhiEntity jianzhiEntity = jianzhiService.selectById(jianzhiId);
            if(jianzhiEntity == null)
                return R.error(511,"查不到该兼职");
            //获取商家信息
            Integer shangjiaId = jianzhiEntity.getShangjiaId();
            ShangjiaEntity shangjiaEntity = shangjiaService.selectById(shangjiaId);//商家

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            Double zhekou = 1.0;

                //计算金额
                //计算所获得积分
                Double buyJifen = 0.0;


                //修改商家余额



            jianzhiOrder.setJianzhiOrderTypes(102);//设置订单状态为已取消申请
            jianzhiOrderService.updateById(jianzhiOrder);//根据id更新
            shangjiaService.updateById(shangjiaEntity);
            yonghuService.updateById(yonghuEntity);//更新用户信息
            jianzhiService.updateById(jianzhiEntity);//更新订单中兼职的信息

            return R.ok();
    }

    /**
     * 同意
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        JianzhiOrderEntity  jianzhiOrderEntity = jianzhiOrderService.selectById(id);
        jianzhiOrderEntity.setJianzhiOrderTypes(103);//设置订单状态为已同意
        jianzhiOrderService.updateById( jianzhiOrderEntity);

        return R.ok();
    }


}

