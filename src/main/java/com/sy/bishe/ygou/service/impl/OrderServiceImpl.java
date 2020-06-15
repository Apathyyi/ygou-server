package com.sy.bishe.ygou.service.impl;

import com.sy.bishe.ygou.bean.OrderBean;
import com.sy.bishe.ygou.mapper.OrderMapper;
import com.sy.bishe.ygou.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int add(OrderBean orderBean) {
        return orderMapper.add(orderBean);
    }

    @Override
    public List<OrderBean> getOrderListByUserId(Integer id) {
        return orderMapper.getOrderListByUserId(id);
    }

    @Override
    public List<OrderBean> getOrderListByUserIdAndType(Integer id, String tag) {
        return orderMapper.getOrderListByUserIdAndType(id,tag);
    }

    @Override
    public List<OrderBean> getOrderListByUserName(String name) {
        return orderMapper.getOrderListByUserName(name);
    }

    @Override
    public int deleteOrderById(Integer id) {
        return orderMapper.deleteOrderById(id);
    }

    @Override
    public int updateTagToSendById(Integer id) {
        return orderMapper.updateTagToSendById(id);
    }

    @Override
    public OrderBean getOrderById(Integer id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public int updateTagToEvlById(Integer id) {
        return orderMapper.updateTagToEvlById(id);
    }

    @Override
    public int updateTagToReceiveById(Integer id) {
        return orderMapper.updateTagToReceiveById(id);
    }
}
