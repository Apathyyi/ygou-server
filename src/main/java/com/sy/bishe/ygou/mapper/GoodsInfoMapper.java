package com.sy.bishe.ygou.mapper;


import com.sy.bishe.ygou.bean.GoodsInfoBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsInfoMapper {
    /**
     * id查询商品详情
     * @param id
     * @return
     */
    @Select("SELECT * FROM tb_goodsinfo WHERE goodsinfo_id = #{id} and goodsinfo_tag=1")
    public GoodsInfoBean getGoodsInfoById(Integer id);

    /**
     * 品牌查询商品详情
     * @param type
     * @return
     */
    @Select("SELECT * FROM tb_goodsinfo WHERE goodsinfo_type = #{type} and goodsinfo_tag =1")
    public List<GoodsInfoBean> getGoodsInfoByType(Integer type);

    /**
     * 查询所有
     * @return
     */
    @Select("SELECT * FROM tb_goodsinfo where goodsinfo_tag = 1 Order By goodsinfo_id limit 0,5")
    public List<GoodsInfoBean> getGoodsInfoList();

    @Select("SELECT * FROM tb_goodsinfo WHERE goodsinfo_name like CONCAT('%',#{key},'%') OR goodsinfo_desc like CONCAT('%',#{key},'%') or goodsinfo_lable like CONCAT('%',#{key},'%')  and goodsinfo_tag = 1")
    List<GoodsInfoBean> getListByKey(String key);

    @Select("SELECT * FROM tb_goodsinfo WHERE goodsinfo_type = #{type} AND goodsinfo_area like CONCAT('%',#{area},'%') and goodsinfo_tag = 1 ORDER BY goodsinfo_hot DESC")
    public List<GoodsInfoBean> getGoodsInfoByTypeOrderByHot(Integer type,String area);

    @Select("SELECT * FROM tb_goodsinfo WHERE goodsinfo_type = #{type} AND goodsinfo_area like CONCAT('%',#{area},'%') and goodsinfo_tag = 1 ORDER BY goodsinfo_area ASC")
    public List<GoodsInfoBean> getGoodsInfoByTypeOrderByDistance(Integer type,String area);

    @Select("SELECT * FROM tb_goodsinfo WHERE goodsinfo_type = #{type} AND goodsinfo_area like CONCAT('%',#{area},'%') and goodsinfo_tag = 1")
    public List<GoodsInfoBean> getGoodsInfoByTypeOrderByArea(Integer type,String area);

    @Select("SELECT * FROM tb_goodsinfo WHERE goodsinfo_type = #{type} AND goodsinfo_area like CONCAT('%',#{area},'%') and goodsinfo_tag = 1")
    List<GoodsInfoBean> getGoodsInfoByTypeOrderByDefault(Integer type, String area);

    @Select("SELECT * FROM tb_goodsinfo WHERE goodsinfo_type = #{type} AND goodsinfo_area like CONCAT('%',#{area},'%') and goodsinfo_tag = 1 ORDER BY goodsinfo_price DESC")
    List<GoodsInfoBean> getGoodsInfoByTypeOrderPriceDesc(Integer type, String area);


    @Select("SELECT * FROM tb_goodsinfo WHERE goodsinfo_type = #{type} AND goodsinfo_area like CONCAT('%',#{area},'%') and goodsinfo_tag = 1 ORDER BY goodsinfo_price ASC")
    List<GoodsInfoBean> getGoodsInfoByTypeOrderPriceAsc(Integer type, String area);

    @Select("SELECT * FROM tb_goodsinfo WHERE goodsinfo_type = #{type} AND goodsinfo_area like CONCAT('%',#{area},'%')  and goodsinfo_tag = 1 ORDER BY goodsinfo_time DESC")
    List<GoodsInfoBean> getGoodsInfoByTypeOrderTimeDesc(Integer type, String area);

    @Select("SELECT * FROM tb_goodsinfo WHERE goodsinfo_type = #{type} AND goodsinfo_area like CONCAT('%',#{area},'%')  and goodsinfo_tag = 1 ORDER BY goodsinfo_time ASC")
    List<GoodsInfoBean> getGoodsInfoByTypeOrderTimeAsc(Integer type, String area);

    @Insert("INSERT INTO tb_goodsinfo(goodsinfo_name,goodsinfo_content,goodsinfo_sepecification,goodsinfo_inventory,goodsinfo_desc,goodsinfo_price,goodsinfo_thumb," +
            "goodsinfo_count,goodsinfo_hot,goodsinfo_type,goodsinfo_lable,goodsinfo_userName,goodsinfo_area,goodsinfo_time,goodsinfo_banners) VALUES(#{goodsInfoBean.goodsinfo_name}," +
            "#{goodsInfoBean.goodsinfo_content},#{goodsInfoBean.goodsinfo_sepecification},#{goodsInfoBean.goodsinfo_inventory},#{goodsInfoBean.goodsinfo_desc}," +
            "#{goodsInfoBean.goodsinfo_price},#{goodsInfoBean.goodsinfo_thumb},#{goodsInfoBean.goodsinfo_count},#{goodsInfoBean.goodsinfo_hot},#{goodsInfoBean.goodsinfo_type},#{goodsInfoBean.goodsinfo_lable}," +
            "#{goodsInfoBean.goodsinfo_userName},#{goodsInfoBean.goodsinfo_area},#{goodsInfoBean.goodsinfo_time},#{goodsInfoBean.goodsinfo_banners})")
    int add(@Param("goodsInfoBean") GoodsInfoBean goodsInfoBean);

    @Select({
            "<script>",
            "select *",
            "from tb_goodsinfo",
            "where goodsinfo_tag = 1 and goodsinfo_type in",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<GoodsInfoBean> getGoodsInfoListByIds(@Param("ids")List<Integer> ids);

    @Select("SELECT * FROM tb_goodsinfo  where  goodsinfo_tag = 1 Order By goodsinfo_id limit #{start},#{end}")
    List<GoodsInfoBean> loadMore(Integer start,Integer end);


    @Select("SELECT * FROM tb_goodsinfo where goodsinfo_tag = 1 Order By goodsinfo_price ASC")
    List<GoodsInfoBean> getGoodsInfoListByPrice();

    @Select("SELECT * FROM tb_goodsinfo   where goodsinfo_tag = 1 ORDER BY goodsinfo_hot DESC LIMIT 0,100")
    List<GoodsInfoBean> getGoodsInfoOrderByHot();
    @Select({
            "<script>",
            "select distinct goodsinfo_type",
            "from tb_goodsinfo",
            "where goodsinfo_tag=1 AND goodsinfo_id in",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<Integer> getGoodsInfoTypeById(@Param("ids")List<Integer> ids);

    @Select("SELECT * FROM tb_goodsinfo  WHERE goodsinfo_userName = #{name} and goodsinfo_tag =1")
    List<GoodsInfoBean> getGoodsInfoListByName(String name);


    @Delete("DELETE FROM tb_goodsinfo WHERE goodsinfo_id = #{id} AND goodsinfo_tag = 1")
    int deleteGoodsById(Integer id);

    @Select("SELECT * FROM tb_goodsinfo  WHERE goodsinfo_type=26 AND goodsinfo_tag = 1")
    List<GoodsInfoBean> getGoodsInfoListByBag();

    @Select("SELECT * FROM tb_goodsinfo where goodsinfo_tag=1 Order By goodsinfo_time DESC")
    List<GoodsInfoBean> getGoodsInfoListByLatest();


    @Select("SELECT * FROM tb_goodsinfo WHERE  goodsinfo_tag=1 and goodsinfo_type in(17,18,19,20)")
    List<GoodsInfoBean> getGoodsInfoListByWoman();

    @Select(" SELECT * FROM tb_goodsinfo WHERE  goodsinfo_tag = 1 and goodsinfo_name like CONCAT('%',#{phone},'%')  or goodsinfo_desc like CONCAT('%',#{phone},'%') ")
    List<GoodsInfoBean> getGoodsInfoListByPhone(String phone);


    @Select("SELECT * FROM tb_goodsinfo WHERE  goodsinfo_tag=1 and goodsinfo_type in(111,112,113,114,115,116,117,118,119,120,121) ")
    List<GoodsInfoBean> getGoodsInfoListByMusic();

    @Select("SELECT * FROM tb_goodsinfo order by goodsinfo_time desc")
    List<GoodsInfoBean> getGoodsInfoListByAdmin();

    @Update("update tb_goodsinfo set goodsinfo_tag = -1 where goodsinfo_id = #{id}")
    int updateGoodsToRefuse(String id);


    @Update("update tb_goodsinfo set goodsinfo_tag = 1 where goodsinfo_id = #{id}")
    int updateGoodsToAgree(String id);


    @Select("SELECT * FROM tb_goodsinfo  WHERE goodsinfo_userName = #{name}")
    List<GoodsInfoBean> getGoodsInfoListByNameAll(String name);

    @Select("SELECT * FROM tb_goodsinfo  where goodsinfo_tag = 1 Order By goodsinfo_hot DESC limit 0,5")
    List<GoodsInfoBean> getGoodsInfoBybanners();


    @Select("SELECT * FROM tb_goodsinfo  WHERE goodsinfo_type in (37,38,39,40,41,42,43,44,45,46,47) AND goodsinfo_tag = 1")
    List<GoodsInfoBean> getGoodsInfoListByTool();


    @Select("SELECT * FROM tb_goodsinfo  WHERE goodsinfo_type in (1,4,7,9,33,67,66,18,56) AND goodsinfo_tag = 1")
    List<GoodsInfoBean> getGoodsInfoListByBrand();


    @Select("SELECT * FROM tb_goodsinfo  WHERE goodsinfo_type in (1,2,3,4,5,6,7) AND goodsinfo_tag = 1")
    List<GoodsInfoBean> getGoodsInfoListByBook();


    @Update("update tb_goodsinfo set goodsinfo_hot = #{hot}  where goodsinfo_id = #{id}")
    int updateGoodsHot(Integer id,String hot);
}
