package com.sy.bishe.ygou.service;

import com.sy.bishe.ygou.bean.OrderBean;

import java.util.List;

public interface OrderService {
    public int add(OrderBean orderBean);

    List<OrderBean> getOrderListByUserId(Integer id);

    List<OrderBean> getOrderListByUserIdAndType(Integer id, String tag);

    List<OrderBean> getOrderListByUserName(String name);

    int deleteOrderById(Integer id);

    int updateTagToSendById(Integer id);

    OrderBean getOrderById(Integer id);

    int updateTagToEvlById(Integer id);

    int updateTagToReceiveById(Integer id);
}
