package com.sy.bishe.ygou.web;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sy.bishe.ygou.bean.*;
import com.sy.bishe.ygou.service.*;
import com.sy.bishe.ygou.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class GoodsInfoController  {

    @Autowired
    private GoodsInfoService goodsInfoService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    UserService userService;

    @Autowired
    SortContentService sortContentService;

    @Autowired
    BrandService brandService;

    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    /**
     * 查询所有商品
     * @return
     */
    @GetMapping(value = "goodsinfo/query")
    public ResponseEntity<JsonResult> getGoodsInfoList(){
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserBean> userBeanList = new ArrayList<>();
            List<GoodsInfoBean> goodsInfoList = goodsInfoService.getGoodsInfoList();
            System.out.println(goodsInfoList.size());
          if (!goodsInfoList.isEmpty()){
              for (GoodsInfoBean goodsInfoBean:goodsInfoList){
                  String userName = goodsInfoBean.getGoodsinfo_userName();
                  List<UserBean> userBeanByName = userService.getUserBeanByName(userName);
                  System.out.println(goodsInfoBean.getGoodsinfo_userName());
                  System.out.println(userBeanByName.size());
                  UserBean userBean = userBeanByName.get(0);
                  userBeanList.add(userBean);
              }
          }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",userBeanList);
            jsonResult.setData(goodsInfoList);
            jsonResult.setJsonObject(jsonObject);
            jsonResult.setStatus("ok");
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }

    @GetMapping(value = "goodsinfo/query/banners")
    public ResponseEntity<JsonResult> getGoodsInfoBybanners(){

        JsonResult jsonResult = new JsonResult();

        try {
            List<GoodsInfoBean> list = goodsInfoService.getGoodsInfoBybanners();

            if (list!=null){
                jsonResult.setStatus("ok");
                jsonResult.setData(list);
            }
            return ResponseEntity.ok(jsonResult);
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加载更多 一次加载五个
     * @param count
     * @return
     */
    @GetMapping(value = "goodsinfo/loadmore/{count}")
    public ResponseEntity<JsonResult> loadMore(@PathVariable(value = "count") Integer count){
        JsonResult jsonResult = new JsonResult();
        System.out.println(count);
        int start = count * 5;
        int end = start + 5;
        try {
            List<UserBean> userBeanList = new ArrayList<>();
            List<GoodsInfoBean> goodsInfoList = goodsInfoService.loadMore(start,end);
            if (!goodsInfoList.isEmpty()){
                System.out.println(goodsInfoList.size());
                for (GoodsInfoBean goodsInfoBean:goodsInfoList){
                    String userName = goodsInfoBean.getGoodsinfo_userName();
                    List<UserBean> userBeanByName = userService.getUserBeanByName(userName);
                    System.out.println(goodsInfoBean.getGoodsinfo_userName());
                    System.out.println(userBeanByName.size());
                    UserBean userBean = userBeanByName.get(0);
                    userBeanList.add(userBean);
                }
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",userBeanList);
            jsonResult.setData(goodsInfoList);
            jsonResult.setJsonObject(jsonObject);
            jsonResult.setStatus("ok");
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }

    /**
     * 低价商品
     * @return
     */
    @GetMapping(value = "goodsinfo/query/lowprice")
    public ResponseEntity<JsonResult> getGoodsInfoListByPrice(){
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserBean> userBeanList = new ArrayList<>();
            //根据名字查id

            List<Integer> ids =new ArrayList<>();
            List<GoodsInfoBean> goodsInfoList = goodsInfoService.getGoodsInfoListByPrice();

            for (GoodsInfoBean goodsInfoBean:goodsInfoList){
                String userName = goodsInfoBean.getGoodsinfo_userName();
                List<UserBean> userBeanByName = userService.getUserBeanByName(userName);
                System.out.println(goodsInfoBean.getGoodsinfo_userName());
                System.out.println(userBeanByName.size());
                UserBean userBean = userBeanByName.get(0);
                userBeanList.add(userBean);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",userBeanList);
            jsonResult.setData(goodsInfoList);
            jsonResult.setJsonObject(jsonObject);
            jsonResult.setStatus("ok");
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }


    /**
     * 通过名字id查询商品
     * @param id
     * @return
     */

    @GetMapping(value = "goodsinfo/queryByName/{id}")
    public ResponseEntity<JsonResult> queryByName(@PathVariable(value = "id")Integer id){
        System.out.println("传入id"+id);
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserBean> userBeanList = new ArrayList<>();
            //根据名字查id
            List<SortContentBean> sortContentById = sortContentService.getSortContentById(id);
            List<Integer> ids =new ArrayList<>();
            for (SortContentBean sortContentBean:sortContentById){
                    ids.add(sortContentBean.getContent_id());
            }

            System.out.println("分类有"+ids.toString());


            //通过id找品牌
            List<Integer> brandIds = sortContentService.getSortContentByIds(ids);

            System.out.println("传入品牌名"+brandIds);


            List<GoodsInfoBean> goodsInfoList = goodsInfoService.getGoodsInfoListByIds(brandIds);

            for (GoodsInfoBean goodsInfoBean:goodsInfoList){
                String userName = goodsInfoBean.getGoodsinfo_userName();
                List<UserBean> userBeanByName = userService.getUserBeanByName(userName);
                System.out.println(goodsInfoBean.getGoodsinfo_userName());
                System.out.println(userBeanByName.size());
                UserBean userBean = userBeanByName.get(0);
                userBeanList.add(userBean);
            }
            System.out.println("传入商品有"+goodsInfoList.toString());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",userBeanList);
            jsonResult.setData(goodsInfoList);
            jsonResult.setJsonObject(jsonObject);
            jsonResult.setStatus("ok");
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }



    /**
     * 添加商品
     * @param goodsInfoBean
     * @return
     */
    @RequestMapping(value = "goodsinfo/add")
    public ResponseEntity<JsonResult> add(@RequestBody GoodsInfoBean goodsInfoBean){
        System.out.println(goodsInfoBean.getGoodsinfo_name());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String format = formatter.format(date);
        goodsInfoBean.setGoodsinfo_time(format);
        GoodsBean goodsBean =  new GoodsBean();
        goodsBean.setGoods_img(goodsInfoBean.getGoodsinfo_thumb());
        goodsBean.setGoods_text(goodsInfoBean.getGoodsinfo_name());
        goodsBean.setGoods_spanSize("4");
        int add = goodsService.add(goodsBean);
        int id = goodsInfoService.add(goodsInfoBean);
        JsonResult jsonResult = new JsonResult();

        jsonResult.setStatus("ok");
        jsonResult.setData(id);
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 通过id查找商品  同时商品热度加1
     * @param id
     * @return
     */
    @GetMapping(value = "goodsinfo/queryById/{id}")
    public ResponseEntity<JsonResult> getBeanById(@PathVariable(value = "id") Integer id){
        try {
            System.out.println("ID---------"+id);
            JsonResult jsonResult = new JsonResult();
            GoodsInfoBean goodsInfoBean = goodsInfoService.getGoodsInfoById(id);
            if (goodsInfoBean == null){
                jsonResult.setStatus("error");
            }else {
                String goodsinfo_hot = goodsInfoBean.getGoodsinfo_hot();
                int hot = Integer.parseInt(goodsinfo_hot) + 1 ;
                int result =  goodsInfoService.updateGoodsHot(id,String.valueOf(hot));
                if (result!=0){
                    jsonResult.setCode("0");
                    jsonResult.setStatus("ok");
                    jsonResult.setData(goodsInfoBean);
                }else {
                    jsonResult.setStatus("error");
                }
            }
            return ResponseEntity.ok(jsonResult);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过类型和品牌名模糊查找商品
     * @param type
     * @param goodsInfoName
     * @return
     */
    @RequestMapping(value = "goodsinfo/queryByType/{type}" ,method = RequestMethod.POST)
    public ResponseEntity<JsonResult> getGoodsInfoByType(@PathVariable(value = "type") Integer type ,@RequestBody JSONObject goodsInfoName) {
        //根据品牌名查找特定的商品
        System.out.println(type+"商品名字"+goodsInfoName);
        List<GoodsInfoBean> list = new ArrayList<>();
        try {
            JsonResult jsonResult = new JsonResult();
            JSONObject jsonObject = new JSONObject();
            String name = goodsInfoName.getString("goodsInfoName");
            //type是0 代表查询所有类的商品 通过品牌 -1代表从搜索历史 -2代表低价
            if (type==-1){
                //模糊查询
                ResponseEntity<JsonResult> listByKey = getListByKey(name);
                return listByKey;
            }
            else if (type==0){
                System.out.println("000000");
                List<Integer> ids = brandService.getIdByName(name);
                if (!ids.isEmpty()){
                    System.out.println(ids.get(0));
                    list= goodsInfoService.getGoodsInfoListByIds(ids);
                    System.out.println(list.size()+"查询到的商品数目");
                    jsonResult.setData(list);
                    jsonResult.setCode("0");
                    jsonResult.setStatus("OK");
                    //数据
                    return ResponseEntity.ok(jsonResult);
                }
              //  -2代表低价
            }else if(type==-2){
                List<Integer> ids = brandService.getIdByName(name);
                if (!ids.isEmpty()){
                    System.out.println(ids.get(0));
                    list= goodsInfoService.getGoodsInfoListByIds(ids);
                    list.sort(new Comparator<GoodsInfoBean>() {
                        @Override
                        public int compare(GoodsInfoBean goodsInfoBean, GoodsInfoBean t1) {
                            if (goodsInfoBean.getGoodsinfo_price()>t1.getGoodsinfo_price()){
                                return 1;
                            }else {
                                return -1;
                            }
                        }
                    });
                    System.out.println(list.size()+"查询到的商品数目");
                    jsonResult.setData(list);
                    jsonResult.setCode("0");
                    jsonResult.setStatus("OK");
                    //数据
                    return ResponseEntity.ok(jsonResult);
                }
            }
            else  {
                //查找type对应的id查找brand表里面等于name的brandID
                int barnd_id =  brandService.getBrandIdByNameAndId(type,name);
                //得到商品的brand_id
                list =goodsInfoService.getGoodsInfoByType(barnd_id);
                jsonResult.setData(list);
                jsonResult.setCode("0");
                jsonResult.setStatus("OK");
                //数据
            }
            //附加字段
            jsonResult.setJsonObject(jsonObject);
            //状态
            jsonResult.setCode("0");
            jsonResult.setStatus("OK");
            //数据
            return ResponseEntity.ok(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 热搜商品的品牌
     * @return
     */
    @GetMapping(value = "goodsinfo/queryByHot")
    public ResponseEntity<JsonResult> getGoodsInfoOrderByHot() {
        //根据热度查找的商品 只去前一百  0-size-1
        JsonResult jsonResult = new JsonResult();
        try {
            List<String> strings = new ArrayList<>();
            List<GoodsInfoBean> list = goodsInfoService.getGoodsInfoOrderByHot();
            int size = list.size();
            List<Integer> numbers = new ArrayList<>();

            if (size<15){
                for (GoodsInfoBean g:list){
                    int type = g.getGoodsinfo_type();
                     String name = brandService.getBrandName(type);
                    strings.add(name);
                }
                jsonResult.setData(strings);
            }else {
                //大于215 产生15个随机  并且数字在size范围类
                Random random = new Random();
                //产生0-99的随机数
                while (numbers.size()<15){
                    //加五个
                    int number = random.nextInt(100);
                    if (number<size&&!numbers.contains(number)){
                        //这个数字在size范围内
                        numbers.add(number);
                    }
                }
                //得到20个体随机数
                List<GoodsInfoBean> hotList = new ArrayList<>();
                //遍历15个数字
                for (Integer i:numbers
                     ) {
                        int id = i;
                        hotList.add(list.get(i));
                }
                for (GoodsInfoBean g:hotList
                     ) {
                    int type = g.getGoodsinfo_type();
                    String name = brandService.getBrandName(type);
                   strings.add(name);
                }
                jsonResult.setData(strings);
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult) ;
    }

    /**
     * 热度排序
     * @param type
     * @param goodsInfoName
     * @return
     */
    @RequestMapping(value = "goodsinfo/queryByTypeOrderByHot/{type}" ,method = RequestMethod.POST)
    public ResponseEntity<JsonResult> getGoodsInfoByTypeOrderByHot(@PathVariable(value = "type") Integer type ,@RequestBody JSONObject goodsInfoName) {
        //根据品牌名查找特定的商品
        try {
            String area = goodsInfoName.getString("area");
            String name = goodsInfoName.getString("goodsInfoName");
            if (area.equals("全国")){
                area="";
            }
            List<GoodsInfoBean> list = goodsInfoService.getGoodsInfoByTypeOrderByAreaByHot(type,area);
            List<GoodsInfoBean> goodsInfoBeans = new ArrayList<>();
            int size = list.size();
            System.out.println("SIZE"+size);
            if (size>0) {
                for (int i = 0; i < size; i++) {
                    GoodsInfoBean goodsInfoBean = list.get(i);
                    System.out.println("ID"+goodsInfoBean.getGoodsinfo_id());
                    //包含品牌名
                    if ((goodsInfoBean.getGoodsinfo_name()+goodsInfoBean.getGoodsinfo_desc()).contains(name)) {
                        goodsInfoBeans.add(goodsInfoBean);
                    }
                    else {
                        goodsInfoBeans.add(goodsInfoBean);
                    }
                }
            }else {
                //没有找到数据则随机推荐
                goodsInfoBeans = goodsInfoService.getGoodsInfoList();
            }
            JsonResult jsonResult = new JsonResult();
            JSONObject jsonObject = new JSONObject();
            //附加字段
            jsonResult.setJsonObject(jsonObject);
            //状态
            jsonResult.setCode("0");
            jsonResult.setStatus("OK");
            //数据
            jsonResult.setData(goodsInfoBeans);
            return ResponseEntity.ok(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 距离排序
     * @param type
     * @param goodsInfoName
     * @return
     */
    @RequestMapping(value = "goodsinfo/queryByTypeOrderByDistance/{type}" ,method = RequestMethod.POST)
    public ResponseEntity<JsonResult> getGoodsInfoByTypeOrderByDistance(@PathVariable(value = "type") Integer type ,@RequestBody JSONObject goodsInfoName) {
        //根据品牌名查找特定的商品
        try {
            String area = goodsInfoName.getString("area");
            String name = goodsInfoName.getString("goodsInfoName");
            if (area.equals("全国")){
                area = "";
            }
            List<GoodsInfoBean> list = goodsInfoService.getGoodsInfoByTypeOrderByDistance(type,area);
            List<GoodsInfoBean> goodsInfoBeans = new ArrayList<>();
            int size = list.size();
            System.out.println("SIZE"+size);
            if (size>0) {
                for (int i = 0; i < size; i++) {
                    GoodsInfoBean goodsInfoBean = list.get(i);
                    System.out.println("ID"+goodsInfoBean.getGoodsinfo_id());
                    //包含品牌名
                    System.out.println(goodsInfoBean.getGoodsinfo_name()+goodsInfoBean.getGoodsinfo_desc()+"*********"+name);
                    if ((goodsInfoBean.getGoodsinfo_name()+goodsInfoBean.getGoodsinfo_desc()).contains(name)) {
                        goodsInfoBeans.add(goodsInfoBean);
                    }
                    else {
                        goodsInfoBeans.add(goodsInfoBean);
                    }
                }
            }else {
                //没有找到数据则随机推荐
                goodsInfoBeans = goodsInfoService.getGoodsInfoList();
            }
            JsonResult jsonResult = new JsonResult();
            JSONObject jsonObject = new JSONObject();
            //附加字段
            jsonResult.setJsonObject(jsonObject);
            //状态
            jsonResult.setCode("0");
            jsonResult.setStatus("OK");
            //数据
            jsonResult.setData(goodsInfoBeans);
            return ResponseEntity.ok(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 默认排序
     * @param type
     * @param goodsInfoName
     * @return
     */
    @RequestMapping(value = "goodsinfo/queryByTypeDefault/{type}" ,method = RequestMethod.POST)
    public ResponseEntity<JsonResult> getGoodsInfoByTypeOrderByDefault(@PathVariable(value = "type") Integer type ,@RequestBody JSONObject goodsInfoName) {
        //根据品牌名查找特定的商品
        try {
            String area = goodsInfoName.getString("area");
            String name = goodsInfoName.getString("goodsInfoName");
            List<GoodsInfoBean> list = goodsInfoService.getGoodsInfoByTypeOrderByDefault(type,area);
            List<GoodsInfoBean> goodsInfoBeans = new ArrayList<>();
            int size = list.size();
            System.out.println("SIZE"+size);
            if (size>0) {
                for (int i = 0; i < size; i++) {
                    GoodsInfoBean goodsInfoBean = list.get(i);
                    System.out.println("ID"+goodsInfoBean.getGoodsinfo_id());
                    //包含品牌名
                    if ((goodsInfoBean.getGoodsinfo_name()+goodsInfoBean.getGoodsinfo_desc()).contains(name)) {
                        goodsInfoBeans.add(goodsInfoBean);
                    }
                    else {
                        goodsInfoBeans.add(goodsInfoBean);
                    }
                }
            }else {
                //没有找到数据则随机推荐
                goodsInfoBeans = goodsInfoService.getGoodsInfoList();
            }
            JsonResult jsonResult = new JsonResult();
            JSONObject jsonObject = new JSONObject();
            //附加字段
            jsonResult.setJsonObject(jsonObject);
            //状态
            jsonResult.setCode("0");
            jsonResult.setStatus("OK");
            //数据
            jsonResult.setData(goodsInfoBeans);
            return ResponseEntity.ok(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 地区排序
     * @param type
     * @param goodsInfoName
     * @return
     */
    @RequestMapping(value = "goodsinfo/queryByTypeOrderByArea/{type}" ,method = RequestMethod.POST)
    public ResponseEntity<JsonResult> getGoodsInfoByTypeOrderByArea(@PathVariable(value = "type") Integer type ,@RequestBody JSONObject goodsInfoName) {
        //根据品牌名查找特定的商品
        try {
            String name = goodsInfoName.getString("goodsInfoName");
            String area = goodsInfoName.getString("area");
            System.out.println("area***********"+area);
            if (area.equals("全国")){
                area = "";
            }else {
                area = area.substring(0,area.length()-1);
            }
            List<GoodsInfoBean> list = goodsInfoService.getGoodsInfoByTypeOrderByArea(type,area);
            List<GoodsInfoBean> goodsInfoBeans = new ArrayList<>();
            int size = list.size();
            System.out.println("SIZE"+size);
            if (size>0) {
                for (int i = 0; i < size; i++) {
                    GoodsInfoBean goodsInfoBean = list.get(i);
                    goodsInfoBeans.add(goodsInfoBean);
                    }
            }
//            else {
//                //没有找到数据则随机推荐
//                goodsInfoBeans = goodsInfoService.getGoodsInfoList();
//            }
            JsonResult jsonResult = new JsonResult();
            JSONObject jsonObject = new JSONObject();
            //附加字段
            jsonResult.setJsonObject(jsonObject);
            //状态
            jsonResult.setCode("0");
            jsonResult.setStatus("OK");
            //数据
            jsonResult.setData(goodsInfoBeans);
            return ResponseEntity.ok(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 价格降序
     * @param type
     * @param goodsInfoName
     * @return
     */
    @RequestMapping(value = "goodsinfo/queryByTypeOrderByPriceDesc/{type}" ,method = RequestMethod.POST)
    public ResponseEntity<JsonResult> getGoodsInfoByTypeOrderPriceDesc(@PathVariable(value = "type") Integer type ,@RequestBody JSONObject goodsInfoName) {
        //根据品牌名查找特定的商品
        try {
            String area = goodsInfoName.getString("area");
            if (area.equals("全国")){
                area = "";
            }
            String name = goodsInfoName.getString("goodsInfoName");
            List<GoodsInfoBean> list = goodsInfoService.getGoodsInfoByTypeOrderPriceDesc(type,area);
            List<GoodsInfoBean> goodsInfoBeans = new ArrayList<>();
            int size = list.size();
            System.out.println("SIZE"+size);
            if (size>0) {
                for (int i = 0; i < size; i++) {
                    GoodsInfoBean goodsInfoBean = list.get(i);
                    System.out.println("ID"+goodsInfoBean.getGoodsinfo_id());
                    //包含品牌名
                    if ((goodsInfoBean.getGoodsinfo_name()+goodsInfoBean.getGoodsinfo_desc()).contains(name)) {
                        goodsInfoBeans.add(goodsInfoBean);
                    }else {
                        goodsInfoBeans.add(goodsInfoBean);
                    }
                }
            }else {
                //没有找到数据则随机推荐
                goodsInfoBeans = goodsInfoService.getGoodsInfoList();
            }
            JsonResult jsonResult = new JsonResult();
            JSONObject jsonObject = new JSONObject();
            //附加字段
            jsonResult.setJsonObject(jsonObject);
            //状态
            jsonResult.setCode("0");
            jsonResult.setStatus("OK");
            //数据
            jsonResult.setData(goodsInfoBeans);
            return ResponseEntity.ok(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 价格升序
     * @param type
     * @param goodsInfoName
     * @return
     */
    @RequestMapping(value = "goodsinfo/queryByTypeOrderByPriceAsc/{type}" ,method = RequestMethod.POST)
    public ResponseEntity<JsonResult> getGoodsInfoByTypeOrderPriceAsc(@PathVariable(value = "type") Integer type ,@RequestBody JSONObject goodsInfoName) {
        //根据品牌名查找特定的商品
        try {
            String area = goodsInfoName.getString("area");
            if (area.equals("全国")){
                area = "";
            }
            String name = goodsInfoName.getString("goodsInfoName");
            List<GoodsInfoBean> list = goodsInfoService.getGoodsInfoByTypeOrderPriceAsc(type,area);
            List<GoodsInfoBean> goodsInfoBeans = new ArrayList<>();
            int size = list.size();
            System.out.println("SIZE"+size);
            if (size>0) {
                for (int i = 0; i < size; i++) {
                    GoodsInfoBean goodsInfoBean = list.get(i);
                    System.out.println("ID"+goodsInfoBean.getGoodsinfo_id());
                    //包含品牌名
                    if ((goodsInfoBean.getGoodsinfo_name()+goodsInfoBean.getGoodsinfo_desc()).contains(name)) {
                        goodsInfoBeans.add(goodsInfoBean);
                    }else {
                        goodsInfoBeans.add(goodsInfoBean);
                    }
                }
            }else {
                //没有找到数据则随机推荐
                goodsInfoBeans = goodsInfoService.getGoodsInfoList();
            }
            JsonResult jsonResult = new JsonResult();
            JSONObject jsonObject = new JSONObject();
            //附加字段
            jsonResult.setJsonObject(jsonObject);
            //状态
            jsonResult.setCode("0");
            jsonResult.setStatus("OK");
            //数据
            jsonResult.setData(goodsInfoBeans);
            return ResponseEntity.ok(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 时间升序
     * @param type
     * @param goodsInfoName
     * @return
     */
    @RequestMapping(value = "goodsinfo/queryByTypeOrderByTimeAsc/{type}" ,method = RequestMethod.POST)
    public ResponseEntity<JsonResult> getGoodsInfoByTypeOrderTimeAsc(@PathVariable(value = "type") Integer type ,@RequestBody JSONObject goodsInfoName) {
        //根据品牌名查找特定的商品
        try {
            String area = goodsInfoName.getString("area");
            if (area.equals("全国")){
                area = "";
            }
            String name = goodsInfoName.getString("goodsInfoName");
            List<GoodsInfoBean> list = goodsInfoService.getGoodsInfoByTypeOrderTimeAsc(type,area);
            List<GoodsInfoBean> goodsInfoBeans = new ArrayList<>();
            int size = list.size();
            System.out.println("SIZE"+size);
            if (size>0) {
                for (int i = 0; i < size; i++) {
                    GoodsInfoBean goodsInfoBean = list.get(i);
                    System.out.println("ID"+goodsInfoBean.getGoodsinfo_id());
                    //包含品牌名
                    if ((goodsInfoBean.getGoodsinfo_name()+goodsInfoBean.getGoodsinfo_desc()).contains(name)) {
                        goodsInfoBeans.add(goodsInfoBean);
                    }else {
                        goodsInfoBeans.add(goodsInfoBean);
                    }
                }
            }else {
                //没有找到数据则随机推荐
                goodsInfoBeans = goodsInfoService.getGoodsInfoList();
            }
            JsonResult jsonResult = new JsonResult();
            JSONObject jsonObject = new JSONObject();
            //附加字段
            jsonResult.setJsonObject(jsonObject);
            //状态
            jsonResult.setCode("0");
            jsonResult.setStatus("OK");
            //数据
            jsonResult.setData(goodsInfoBeans);
            return ResponseEntity.ok(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 时间降序
     * @param type
     * @param goodsInfoName
     * @return
     */
    @RequestMapping(value = "goodsinfo/queryByTypeOrderByTimeDesc/{type}" ,method = RequestMethod.POST)
    public ResponseEntity<JsonResult> getGoodsInfoByTypeOrderTimeDesc(@PathVariable(value = "type") Integer type ,@RequestBody JSONObject goodsInfoName) {
        //根据品牌名查找特定的商品
        try {
            String area = goodsInfoName.getString("area");
            if (area.equals("全国")){
                area = "";
            }
            String name = goodsInfoName.getString("goodsInfoName");
            List<GoodsInfoBean> list = goodsInfoService.getGoodsInfoByTypeOrderTimeDesc(type,area);
            List<GoodsInfoBean> goodsInfoBeans = new ArrayList<>();
            int size = list.size();
            System.out.println("SIZE"+size);
            if (size>0) {
                for (int i = 0; i < size; i++) {
                    GoodsInfoBean goodsInfoBean = list.get(i);
                    System.out.println("ID"+goodsInfoBean.getGoodsinfo_id());
                    //包含品牌名
                    if ((goodsInfoBean.getGoodsinfo_name()+goodsInfoBean.getGoodsinfo_desc()).contains(name)) {
                        goodsInfoBeans.add(goodsInfoBean);
                    }else {
                        goodsInfoBeans.add(goodsInfoBean);
                    }
                }
            }else {
                //没有找到数据则随机推荐
                goodsInfoBeans = goodsInfoService.getGoodsInfoList();
            }
            JsonResult jsonResult = new JsonResult();
            JSONObject jsonObject = new JSONObject();
            //附加字段
            jsonResult.setJsonObject(jsonObject);
            //状态
            jsonResult.setCode("0");
            jsonResult.setStatus("OK");
            //数据
            jsonResult.setData(goodsInfoBeans);
            return ResponseEntity.ok(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 上传图片
     * @param AttachmentKey
     * @return
     */
    @RequestMapping(value = "goodsinfo/upload",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> uploadImg(@RequestParam(value = "AttachmentKey",required = false) MultipartFile[] AttachmentKey){
        System.out.println("图片张数"+AttachmentKey.length);
        String tomcat_path = "D:\\apache-tomcat-10.0.0-M1\\webapps\\ROOT\\upload\\";
        JsonResult jsonResult = new JsonResult();
        List<String> urls = new ArrayList<>();
        for (MultipartFile f:AttachmentKey
             ) {
            String originalFilename = f.getOriginalFilename();
            System.out.println("文件名字"+originalFilename);
            try {
                //传到tomcat
                UploadUtils.uploadFile(f.getBytes(),tomcat_path,originalFilename);
                urls.add(originalFilename);
                jsonResult.setStatus("ok");
            } catch (Exception e) {
                jsonResult.setStatus("exception");
                e.printStackTrace();
            }
            jsonResult.setData(urls);
        }
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 通过关键字查询
     * @param key
     * @return
     */
    @RequestMapping(value = "goodsinfoByKey/query/{key}")
    public ResponseEntity<JsonResult> getListByKey(@PathVariable(value = "key") String key){
        JsonResult jsonResult = new JsonResult();
        try {
            List<GoodsInfoBean> list = goodsInfoService.getListByKey(key);
            jsonResult.setData(list);
            jsonResult.setStatus("ok");
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 查询用户喜欢的
     * @param id
     * @return
     */
    @RequestMapping(value = "goodsinfo/queryByLike/{id}")
    public ResponseEntity<JsonResult> getListByLike(@PathVariable(value = "id") Integer id){
        JsonResult jsonResult = new JsonResult();
        List<UserBean> userBeanList = new ArrayList<>();
        try {
            List<Integer> goodsIds = new ArrayList<>();
            List<OrderBean> orderListByUserId = orderService.getOrderListByUserId(id);
            //有订单
            if (!orderListByUserId.isEmpty()){
                System.out.println("有订单");
                for (OrderBean o:orderListByUserId
                ) {
                    int order_goods_id = o.getOrder_goods_id();
                    goodsIds.add(order_goods_id);
                }
            }else {
                System.out.println("每订单");
                //没有订单
                //查询购物车
                List<CartBean> goodsIdByUserId = cartService.getGoodsIdByUserId(id);
                if (!goodsIdByUserId.isEmpty()){
                    //购物车不为空
                    System.out.println("有购物车");
                    for (CartBean c:goodsIdByUserId
                         ) {
                        int cart_goods_id = c.getCart_goods_id();
                        goodsIds.add(cart_goods_id);
                    }
                }else {
                    //购物车也为空则默认推荐
                    System.out.println("没有购物车");
                    ResponseEntity<JsonResult> goodsInfoList = getGoodsInfoList();
                    return goodsInfoList;
                }
            }
            //得到商品id
            //查询id分类 brand_id
            if (!goodsIds.isEmpty()){
                System.out.println("推荐");
                List<Integer>  brandIds = goodsInfoService.getGoodsInfoTypeById(goodsIds);
                List<GoodsInfoBean> goodsInfoListByIds = goodsInfoService.getGoodsInfoListByIds(brandIds);

                for (GoodsInfoBean goodsInfoBean:goodsInfoListByIds){
                    String userName = goodsInfoBean.getGoodsinfo_userName();
                    List<UserBean> userBeanByName = userService.getUserBeanByName(userName);
                    System.out.println(goodsInfoBean.getGoodsinfo_userName());
                    System.out.println(userBeanByName.size());
                    UserBean userBean = userBeanByName.get(0);
                    userBeanList.add(userBean);
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("users",userBeanList);
                jsonResult.setStatus("ok");
                jsonResult.setData(goodsInfoListByIds);
                jsonResult.setJsonObject(jsonObject);
                return ResponseEntity.ok(jsonResult);
            }else {
                ResponseEntity<JsonResult> goodsInfoList = getGoodsInfoList();
                return goodsInfoList;
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    /**
     * 查询用户喜欢的 搜索历史
     * @param id
     * @return
     */
    @RequestMapping(value = "goodsinfo/queryByHist/{id}",method = RequestMethod.POST)
    public ResponseEntity<JsonResult> getListByHist(@PathVariable(value = "id") Integer id,@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject.toJSONString());
        JSONArray list = jsonObject.getJSONArray("list");
        List<UserBean> userBeanList = new ArrayList<>();
        JsonResult jsonResult = new JsonResult();
        List<GoodsInfoBean> goodsInfoBeanList = new ArrayList<>();
        try {
            if(!list.isEmpty()){
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    String text = list.getString(i);
                    List<GoodsInfoBean> listByKey = goodsInfoService.getListByKey(text);
                    goodsInfoBeanList.addAll(listByKey);
                }
            }else {
                System.out.println("空");
                //查不到就随机推荐
                ResponseEntity<JsonResult> goodsInfoList = getGoodsInfoList();
                return goodsInfoList;
            }
            if (!goodsInfoBeanList.isEmpty()){
                for (GoodsInfoBean goodsInfoBean:goodsInfoBeanList){
                    String userName = goodsInfoBean.getGoodsinfo_userName();
                    List<UserBean> userBeanByName = userService.getUserBeanByName(userName);
                    System.out.println(goodsInfoBean.getGoodsinfo_userName());
                    System.out.println(userBeanByName.size());
                    UserBean userBean = userBeanByName.get(0);
                    userBeanList.add(userBean);
                }

                jsonResult.setStatus("ok");
                JSONObject users = new JSONObject();
                users.put("users",userBeanList);
                jsonResult.setData(goodsInfoBeanList);
                jsonResult.setJsonObject(users);
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);
    }

    @RequestMapping(value = "goodsinfo/delete/{id}")
    public ResponseEntity<JsonResult> deleteGoodsById(@PathVariable(value = "id") Integer id){
        JsonResult jsonResult = new JsonResult();
        System.out.println("传入的商品id"+id);
        try {
            int result = goodsInfoService.deleteGoodsById(id);

            if (result!=0){
                jsonResult.setStatus("ok");
            }
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }

        return ResponseEntity.ok(jsonResult);
    }


    /**
     * 背包
     * @return
     */
    @GetMapping(value = "goodsinfo/query/bag")
    public ResponseEntity<JsonResult> getGoodsInfoListByBag(){
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserBean> userBeanList = new ArrayList<>();
            //根据名字查id
            List<Integer> ids =new ArrayList<>();
            List<GoodsInfoBean> goodsInfoList = goodsInfoService.getGoodsInfoListByBag();
            for (GoodsInfoBean goodsInfoBean:goodsInfoList){
                String userName = goodsInfoBean.getGoodsinfo_userName();
                List<UserBean> userBeanByName = userService.getUserBeanByName(userName);
                System.out.println(goodsInfoBean.getGoodsinfo_userName());
                System.out.println(userBeanByName.size());
                UserBean userBean = userBeanByName.get(0);
                userBeanList.add(userBean);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",userBeanList);
            jsonResult.setData(goodsInfoList);
            jsonResult.setJsonObject(jsonObject);
            jsonResult.setStatus("ok");
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }
    /**
     * 工具
     * @return
     */
    @GetMapping(value = "goodsinfo/query/tool")
    public ResponseEntity<JsonResult> getGoodsInfoListByTool(){
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserBean> userBeanList = new ArrayList<>();
            //根据名字查id
            List<Integer> ids =new ArrayList<>();
            List<GoodsInfoBean> goodsInfoList = goodsInfoService.getGoodsInfoListByTool();
            for (GoodsInfoBean goodsInfoBean:goodsInfoList){
                String userName = goodsInfoBean.getGoodsinfo_userName();
                List<UserBean> userBeanByName = userService.getUserBeanByName(userName);
                System.out.println(goodsInfoBean.getGoodsinfo_userName());
                System.out.println(userBeanByName.size());
                UserBean userBean = userBeanByName.get(0);
                userBeanList.add(userBean);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",userBeanList);
            jsonResult.setData(goodsInfoList);
            jsonResult.setJsonObject(jsonObject);
            jsonResult.setStatus("ok");
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }

    /**
     * 品牌
     * @return
     */
    @GetMapping(value = "goodsinfo/query/brand")
    public ResponseEntity<JsonResult> getGoodsInfoListByBrand(){
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserBean> userBeanList = new ArrayList<>();
            //根据名字查id
            List<Integer> ids =new ArrayList<>();
            List<GoodsInfoBean> goodsInfoList = goodsInfoService.getGoodsInfoListByBrand();
            for (GoodsInfoBean goodsInfoBean:goodsInfoList){
                String userName = goodsInfoBean.getGoodsinfo_userName();
                List<UserBean> userBeanByName = userService.getUserBeanByName(userName);
                System.out.println(goodsInfoBean.getGoodsinfo_userName());
                System.out.println(userBeanByName.size());
                UserBean userBean = userBeanByName.get(0);
                userBeanList.add(userBean);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",userBeanList);
            jsonResult.setData(goodsInfoList);
            jsonResult.setJsonObject(jsonObject);
            jsonResult.setStatus("ok");
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }

    /**
     *考研数据
     * @return
     */
    @GetMapping(value = "goodsinfo/query/book")
    public ResponseEntity<JsonResult> getGoodsInfoListByBook(){
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserBean> userBeanList = new ArrayList<>();
            //根据名字查id
            List<Integer> ids =new ArrayList<>();
            List<GoodsInfoBean> goodsInfoList = goodsInfoService.getGoodsInfoListByBook();
            for (GoodsInfoBean goodsInfoBean:goodsInfoList){
                String userName = goodsInfoBean.getGoodsinfo_userName();
                List<UserBean> userBeanByName = userService.getUserBeanByName(userName);
                System.out.println(goodsInfoBean.getGoodsinfo_userName());
                System.out.println(userBeanByName.size());
                UserBean userBean = userBeanByName.get(0);
                userBeanList.add(userBean);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",userBeanList);
            jsonResult.setData(goodsInfoList);
            jsonResult.setJsonObject(jsonObject);
            jsonResult.setStatus("ok");
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }



    /**
     * 最新商品
     * @return
     */
    @GetMapping(value = "goodsinfo/query/Latest")
    public ResponseEntity<JsonResult> getGoodsInfoListByLatest(){
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserBean> userBeanList = new ArrayList<>();
            //根据名字查id
            List<Integer> ids =new ArrayList<>();
            List<GoodsInfoBean> goodsInfoList = goodsInfoService.getGoodsInfoListByLatest();

            for (GoodsInfoBean goodsInfoBean:goodsInfoList){
                String userName = goodsInfoBean.getGoodsinfo_userName();
                List<UserBean> userBeanByName = userService.getUserBeanByName(userName);
                System.out.println(goodsInfoBean.getGoodsinfo_userName());
                System.out.println(userBeanByName.size());
                UserBean userBean = userBeanByName.get(0);
                userBeanList.add(userBean);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",userBeanList);
            jsonResult.setData(goodsInfoList);
            jsonResult.setJsonObject(jsonObject);
            jsonResult.setStatus("ok");
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }



    /**
     * 女装
     * @return
     */
    @GetMapping(value = "goodsinfo/query/woman")
    public ResponseEntity<JsonResult> getGoodsInfoListByWoman(){

        System.out.println("女装");
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserBean> userBeanList = new ArrayList<>();
            //根据名字查id
            List<Integer> ids =new ArrayList<>();
            List<GoodsInfoBean> goodsInfoList = goodsInfoService.getGoodsInfoListByWoman();
            System.out.println("商品数量"+goodsInfoList.size());
            for (GoodsInfoBean goodsInfoBean:goodsInfoList){
                String userName = goodsInfoBean.getGoodsinfo_userName();
                List<UserBean> userBeanByName = userService.getUserBeanByName(userName);
                System.out.println(goodsInfoBean.getGoodsinfo_userName());
                System.out.println(userBeanByName.size());
                UserBean userBean = userBeanByName.get(0);
                userBeanList.add(userBean);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",userBeanList);
            jsonResult.setData(goodsInfoList);
            jsonResult.setJsonObject(jsonObject);
            jsonResult.setStatus("ok");
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }
    /**
     * 手机
     * @return
     */
    @GetMapping(value = "goodsinfo/query/phone")
    public ResponseEntity<JsonResult> getGoodsInfoListByPhone(){
        System.out.println("查询手机");
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserBean> userBeanList = new ArrayList<>();
            //根据名字查id
            List<Integer> ids =new ArrayList<>();
            List<GoodsInfoBean> goodsInfoList = goodsInfoService.getGoodsInfoListByPhone("手机");
            System.out.println("查询数量");
            for (GoodsInfoBean goodsInfoBean:goodsInfoList){
                String userName = goodsInfoBean.getGoodsinfo_userName();
                List<UserBean> userBeanByName = userService.getUserBeanByName(userName);
                System.out.println(goodsInfoBean.getGoodsinfo_userName());
                System.out.println(userBeanByName.size());
                UserBean userBean = userBeanByName.get(0);
                userBeanList.add(userBean);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",userBeanList);
            jsonResult.setData(goodsInfoList);
            jsonResult.setJsonObject(jsonObject);
            jsonResult.setStatus("ok");
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }
    /**
     * 乐器
     * @return
     */
    @GetMapping(value = " goodsinfo/query/music")
    public ResponseEntity<JsonResult> getGoodsInfoListByMusic(){
        JsonResult jsonResult = new JsonResult();
        try {
            List<UserBean> userBeanList = new ArrayList<>();
            //根据名字查id
            List<Integer> ids =new ArrayList<>();
            List<GoodsInfoBean> goodsInfoList = goodsInfoService.getGoodsInfoListByMusic();

            for (GoodsInfoBean goodsInfoBean:goodsInfoList){
                String userName = goodsInfoBean.getGoodsinfo_userName();
                List<UserBean> userBeanByName = userService.getUserBeanByName(userName);
                System.out.println(goodsInfoBean.getGoodsinfo_userName());
                System.out.println(userBeanByName.size());
                UserBean userBean = userBeanByName.get(0);
                userBeanList.add(userBean);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",userBeanList);
            jsonResult.setData(goodsInfoList);
            jsonResult.setJsonObject(jsonObject);
            jsonResult.setStatus("ok");
        } catch (Exception e) {
            jsonResult.setStatus("exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonResult);

    }


}
