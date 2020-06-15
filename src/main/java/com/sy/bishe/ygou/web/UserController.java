package com.sy.bishe.ygou.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.sy.bishe.ygou.bean.*;
import com.sy.bishe.ygou.service.*;
import com.sy.bishe.ygou.utils.UploadUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController  {
    @Value("classpath:city.json")
    private Resource resource;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private GoodsInfoService goodsInfoService;

    @Autowired
    OrderService orderService;

    @Autowired
    ReleaseService releaseService;

    @Autowired
    FriendService friendService;

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @GetMapping(value = "user/queryById/{id}")
    public JsonResult getUserById(@PathVariable(value = "id") Integer id){
        try {
            UserBean userBean = userService.getUserBeanById(id);
            JsonResult jsonResult = new JsonResult();
            jsonResult.setCode("0");
            jsonResult.setStatus("OK");
            jsonResult.setData(userBean);
            return jsonResult;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping(value = "user/queryByPhone/{phone}")

    public ResponseEntity<JsonResult> getUserByPhone(@PathVariable(value = "phone") String phone){
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserBean> userBean =  userService.getUserBeanByPhone(phone);
            if (userBean.size()>0){
                //注册过
                jsonResult.setStatus("ok");
            }else {
                jsonResult.setStatus("error");
            }
        }catch (Exception e){
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }
    @GetMapping(value = "user/queryImg/{name}")
    public ResponseEntity<JsonResult> getUserByName(@PathVariable(value = "name") String name){
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserBean> list =  userService.getUserBeanByName(name);
            if (!list.isEmpty()){
                jsonResult.setStatus("OK");
                jsonResult.setData(list.get(0));
            }
        }catch (Exception e){
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    @RequestMapping(value = "user/queryByPhone",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> queryByPhone(@RequestBody UserBean userBean){
        JsonResult jsonResult = new JsonResult();
        List<UserBean> user = userService.getUserBeanByPhone(userBean.getUser_phone());
        System.out.print(user.size());
        if (user.size()<1){
            //没注册
            jsonResult.setCode("0");
            jsonResult.setStatus("error");
        }
        else {
            //有注册
            jsonResult.setStatus("ok");
        }
        return ResponseEntity.ok(jsonResult);
    }
    /**
     * 登录接口 获取Android端传入的参数
     * @param userBean
     * @return
     */
    @RequestMapping(value = "user/login",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> login(@RequestBody UserBean userBean){
        JsonResult jsonResult = new JsonResult();
        List<UserBean> user = userService.getUserBeanByName(userBean.getUser_name());
        System.out.print(user.size());
        if (user.size()<1){
            jsonResult.setCode("0");
            jsonResult.setStatus("用户不存在");
        }
        else if(user.get(0).getUser_password().equals(userBean.getUser_password())){
            jsonResult.setStatus("ok");
            jsonResult.setJsonObject((JSONObject) JSONObject.toJSON(user.get(0)));
            System.out.print(user.get(0));
        }else {
            jsonResult.setStatus("密码错误");
        }
        return ResponseEntity.ok(jsonResult);
    }
    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping(value = "user/query",method = RequestMethod.GET)
    public JsonResult getUserList(){
        try {
            List<UserBean> userBeanList = userService.getUserList();
            JsonResult jsonResult = new JsonResult();
            jsonResult.setCode("0");
            jsonResult.setStatus("OK");
            jsonResult.setData(userBeanList);
            return jsonResult;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加用户
     * @param userBean
     * @return
     */
    @RequestMapping(value = "user/add",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> add(@RequestBody UserBean userBean){
        System.out.print(userBean.getUser_name());
        JsonResult jsonResult = new JsonResult();
        try {
            int oderId = userService.add(userBean);
            if (oderId < 0){
                jsonResult.setData(oderId);
                jsonResult.setStatus("failed");
            }else {
                jsonResult.setData(oderId);
                jsonResult.setStatus("ok");
            }
        }catch (Exception e){
            jsonResult.setData(e.getClass().getName()+":"+e.getMessage());
            jsonResult.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "user/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<JsonResult> delete(@PathVariable(value = "id") Integer id){
        JsonResult jsonResult = new JsonResult();
        try {
            int delId = userService.delete(id);
            if (delId < 0 ){
                jsonResult.setData(delId);
                jsonResult.setStatus("failed");
            }else {
                jsonResult.setData(delId);
                jsonResult.setStatus("ok");
            }
        }catch (Exception e){
            jsonResult.setData(e.getClass().getName()+":"+e.getMessage());
            jsonResult.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 根据id修改用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "user/update/{id}",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> update(@PathVariable(value = "id") Integer id,@RequestBody UserBean user){
        System.out.println(user.getUser_name());
        JsonResult jsonResult = new JsonResult();
        try {
            int ret = userService.update(id,user);
            if (ret < 0){
                jsonResult.setData(ret);
                jsonResult.setStatus("failed");
            }else {
                jsonResult.setData(ret);
                jsonResult.setStatus("ok");
            }
        }catch (Exception e){
            jsonResult.setData(e.getClass().getName()+":"+e.getMessage());
            jsonResult.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 通过名字查找用户
     * @param userBean
     * @return
     */
    @RequestMapping(value = "user/queryByName",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> getUserBeanByName(@RequestBody UserBean userBean){
        JsonResult jsonResult = new JsonResult();
        List<UserBean> user = userService.getUserBeanByName(userBean.getUser_name());
        if (user.size()<1){
            jsonResult.setCode("0");
            jsonResult.setStatus("用户不存在");
        }
        else if(user.get(0).getUser_password().equals(userBean.getUser_password())){
            jsonResult.setStatus("ok");
            jsonResult.setJsonObject((JSONObject) JSONObject.toJSON(user.get(0)));
            System.out.print(user.get(0));
        }
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping(value = "user/queryByName/{user_name}")
    public ResponseEntity<JsonResult> getUserBeanByName(@PathVariable(value = "user_name") String user_name){
        JsonResult jsonResult = new JsonResult();
        List<UserBean> user = userService.getUserBeanByName(user_name);
        if (user.size()<1){
            jsonResult.setCode("0");
            jsonResult.setStatus("用户不存在");
        }else {
            jsonResult.setStatus("ok");
            UserBean userBean = user.get(0);
            jsonResult.setData(userBean);
        }
        return ResponseEntity.ok(jsonResult);
    }
    @RequestMapping(value = "user/queryByNames",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> getUserBeanByName(@RequestBody List<String> userNames){
        System.out.println(userNames.toString());
        JsonResult jsonResult = new JsonResult();
        List<UserBean> list = new ArrayList<>();
        for (String name:userNames
             ) {
            List<UserBean> user = userService.getUserBeanByName(name);
            if (user.isEmpty()){

            }else {
                list.add(user.get(0));
                jsonResult.setStatus("ok");
            }
        }
        jsonResult.setData(list);
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 通过id 查找用户购物车
     * @param id
     * @return
     */
    @RequestMapping(value = "cart/query/{id}")
    public ResponseEntity<JsonResult> getUserShopCart(@PathVariable(value = "id") Integer id){
        //通过id查找用户添加过的商品
        JsonResult jsonResult = new JsonResult();
        try {
            //cart集合
            List<CartBean> cartBeans = cartService.getGoodsIdByUserId(id);

            List<CartBean> list = new ArrayList<>();
            int size = cartBeans.size();
            if (size>0){
                for (int i = 0; i < size; i++) {
                    CartBean cartBean = cartBeans.get(i);
                    System.out.println(cartBean.toString());
                    System.out.println("goodscount "+cartBeans.get(i).getCart_goods_count());
                    System.out.println("CartId "+cartBean.getCart_goods_id());
                    int goodsid = cartBean.getCart_goods_id();
                    int goodscount = cartBean.getCart_goods_count();
                    //通过id 查找商品
                    GoodsInfoBean goodsInfoBean = goodsInfoService.getGoodsInfoById(goodsid);
                    String goodsinfo_name = goodsInfoBean.getGoodsinfo_name();
                    String goodsinfo_desc = goodsInfoBean.getGoodsinfo_desc();
                    String goodsinfo_thumb = goodsInfoBean.getGoodsinfo_thumb();
                    Double goodsinfo_price = goodsInfoBean.getGoodsinfo_price();

                    cartBean.setCart_name(goodsinfo_name);
                    cartBean.setCart_desc(goodsinfo_desc);
                    cartBean.setCart_goods_count(goodscount);
                    cartBean.setCart_price(goodsinfo_price);
                    cartBean.setCart_thumb(goodsinfo_thumb);
                    list.add(cartBean);
                }
                jsonResult.setStatus("ok");
                jsonResult.setData(list);
            }else {
                //没有购物信息
                jsonResult.setStatus("没有购物信息");
            }
            return ResponseEntity.ok(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "user/query/money/{id}")
    public Double getUserBalanceById(@PathVariable (value = "id") Integer id){
        Double money = userService.getUserBalanceById(id);
        System.out.println(money);
        return money;
    }

    @RequestMapping(value = "user/balance/sub/{price}",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> updateUserBalance(@PathVariable(value = "price") String price,@RequestBody Integer id){

        System.out.println("用户ID"+id+"消费总价"+price);
        JsonResult jsonResult = new JsonResult();
        Double balance = userService.getUserBalanceById(id);
        System.out.println("balance"+balance);
        int isOk = 0;
        try {
            isOk = userService.updateUserBalance(Double.valueOf(price),id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ISOK"+isOk);

        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 充值
     * @param price
     * @param id
     * @return
     */
    @RequestMapping(value = "user/balance/add/{price}",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> addUserBalance(@PathVariable(value = "price") String price,@RequestBody Integer id){

        System.out.println("用户ID"+id+"消费总价"+price);
        JsonResult jsonResult = new JsonResult();
        Double balance = userService.getUserBalanceById(id);
        System.out.println("balance"+balance);
        int isOk = 0;
        try {
            isOk = userService.addUserBalance(Double.valueOf(price),id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ISOK"+isOk);

        return ResponseEntity.ok(jsonResult);
    }

    @RequestMapping(value = "user/friend/query/{id}")
    public ResponseEntity<JsonResult> getUserFriendList(@PathVariable(value = "id") Integer id){
        JsonResult userList = getUserList();
        return ResponseEntity.ok(userList);
    }

    @RequestMapping(value = "city/query")
    public ResponseEntity<JsonResult> getCity(){
        JsonResult jsonResult = new JsonResult();
        try {
            String string = IOUtils.toString(resource.getInputStream(), Charset.forName("UTF-8"));
            JSONObject jsonObject = JSONObject.parseObject(string);
            jsonResult.setStatus("ok");
            jsonResult.setJsonObject(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
            return ResponseEntity.ok(jsonResult);
    }



@RequestMapping(value = "admin/add/{price}")
    public ResponseEntity<JsonResult> addAdmin(@PathVariable(value = "price") Double price){

        userService.updateBalanceAdmin(price);

        JsonResult jsonResult = new JsonResult();

        return ResponseEntity.ok(jsonResult);
}


    /**
     * 上传头像
     * @param AttachmentKey
     * @return
     */
    @RequestMapping(value = "user/upload/{id}",method = RequestMethod.POST)

    public ResponseEntity<JsonResult> uploadImg(@PathVariable(value = "id")Integer id,@RequestParam(value = "AttachmentKey",required = false) MultipartFile[] AttachmentKey){


        String tomcat_path = "D:\\apache-tomcat-10.0.0-M1\\webapps\\ROOT\\avater\\";
        JsonResult jsonResult = new JsonResult();
        MultipartFile multipartFile = AttachmentKey[0];
            String originalFilename = multipartFile.getOriginalFilename();
            System.out.println("文件原名字"+originalFilename);
            try {
                //传到tomcat
                String host = "http://192.168.43.191:8080/avater/";
                String totalname = host + originalFilename;
                System.out.println("文件访问文字"+totalname);
                int ok = userService.updateUserImg(id,totalname);
                System.out.println();
                UploadUtils.uploadFile(multipartFile.getBytes(),tomcat_path,originalFilename);
                jsonResult.setStatus("ok");
            } catch (Exception e) {
                jsonResult.setStatus("exception");
                e.printStackTrace();
            }
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 查询用户的、发布
     * @param name
     * @return
     */
    @GetMapping(value = "user/queryRelease/{name}")
    public ResponseEntity<JsonResult> getUserReleaseList(@PathVariable(value = "name") String name){
        JsonResult jsonResult = new JsonResult();
        System.out.println("查询的用户"+name);
        //发布list
        List<MyReleaseBean> myReleaseBeans = new ArrayList<>();
        //从订单列表查询用户已形成的订单商品
        try {
            List<OrderBean> orderBeans = orderService.getOrderListByUserName(name);
            //查询商品表里的在售商品
            //便利订单表
            for (OrderBean o:orderBeans
                 ) {
                int order_user_id = o.getOrder_user_id();
                UserBean userBeanById = userService.getUserBeanById(order_user_id);
                MyReleaseBean myReleaseBean = new MyReleaseBean();
                myReleaseBean.setUser_name(userBeanById.getUser_name());
                myReleaseBean.setId(o.getOrder_goods_id());
                myReleaseBean.setImg(o.getOrder_thumb());
                myReleaseBean.setTitle(o.getOrder_title());
                myReleaseBean.setPrice(o.getOrder_price());
                myReleaseBean.setCount(o.getOrder_count());
                myReleaseBean.setTime(o.getOrder_time());
                myReleaseBean.setTag(o.getOrder_tag());
                myReleaseBeans.add(myReleaseBean);
            }
            //查寻商品表在售的商品
            List<GoodsInfoBean> goodsInfoBeans = goodsInfoService.getGoodsInfoListByNameAll(name);
            //便利在售商品
            for (GoodsInfoBean g:goodsInfoBeans
                 ) {
                MyReleaseBean myReleaseBean = new MyReleaseBean();
                myReleaseBean.setId(g.getGoodsinfo_id());
                myReleaseBean.setImg(g.getGoodsinfo_thumb());
                myReleaseBean.setTitle(g.getGoodsinfo_name());
                myReleaseBean.setPrice(g.getGoodsinfo_price());
                myReleaseBean.setCount(Integer.parseInt(g.getGoodsinfo_count()));
                int goodsinfo_tag = g.getGoodsinfo_tag();
                if (goodsinfo_tag==1){
                    myReleaseBean.setTag("在售");
                }else if (goodsinfo_tag==0){
                    myReleaseBean.setTag("待审核");
                }else {
                    myReleaseBean.setTag("被驳回");
                }
                myReleaseBean.setTime(g.getGoodsinfo_time());
                myReleaseBeans.add(myReleaseBean);
            }
        } catch (NumberFormatException e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        jsonResult.setData(myReleaseBeans);
        jsonResult.setStatus("ok");
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 查询用户求购
     * @param id
     * @return
     */

    @RequestMapping(value = "user/queryBuy/{id}")
    public ResponseEntity<JsonResult> getUserBuyListById(@PathVariable(value = "id") Integer id){

        JsonResult jsonResult = new JsonResult();
        try {
            List<ReleaseBean> list = releaseService.getUserBuyListById(id);
            jsonResult.setData(list);
            jsonResult.setStatus("ok");
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        //查询求购
        return ResponseEntity.ok(jsonResult);
    }


    /**
     * 设置密码
     * @param num
     * @return
     */

    @RequestMapping(value = "user/updatePayNum/{num}")
    public ResponseEntity<JsonResult> updateUserPayNum(@PathVariable(value = "num") String num,@RequestBody Integer id){

        JsonResult jsonResult = new JsonResult();
        try {
            int result = userService.updateUserPayNum(num,id);
            if (result!=0){
                jsonResult.setStatus("ok");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        //查询求购
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 更新登陆密码
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "user/updatePassByPhone/{phone}")
    public ResponseEntity<JsonResult> updatePassByPhone(@PathVariable(value = "phone") String phone,@RequestBody String  pass){


        System.out.println(pass);
        JsonResult jsonResult = new JsonResult();
        try {
            int result = userService.updatePassByPhone(pass,phone);
            if (result!=0){
                jsonResult.setStatus("ok");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        //查询求购
        return ResponseEntity.ok(jsonResult);
    }
    @RequestMapping(value = "user/queryByLikeName/{name}")
        public ResponseEntity<JsonResult> getUserListLikeName(@PathVariable(value = "name") String name){
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserBean> list =  userService.getUserListLikeName(name);
            jsonResult.setStatus("ok");
            jsonResult.setData(list);
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }


    @RequestMapping(value = "user/addFriend/{id}")
    public ResponseEntity<JsonResult> addFrined(@PathVariable(value = "id") Integer id,@RequestBody String name){

        System.out.println("传入的名字"+name);
        FriendBean friendBean = new FriendBean();

        friendBean.setFriend_id(id);
        //通过名字找到用户
        JsonResult jsonResult = null;
        try {
            List<UserBean> userBeanByName = userService.getUserBeanByName(name);
            if (!userBeanByName.isEmpty()){
                UserBean userBean = userBeanByName.get(0);
                int user_id = userBean.getUser_id();
                friendBean.setUser_id(user_id);
            }
            friendBean.setFriend_tag("notify");
            friendBean.setFriend_agree(false);
            jsonResult = new JsonResult();
            //添加到朋友
            int add = friendService.add(friendBean);
            if (add!=0){
                //成功
                jsonResult.setStatus("ok");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }
}
