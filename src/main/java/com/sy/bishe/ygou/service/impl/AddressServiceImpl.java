package com.sy.bishe.ygou.service.impl;

import com.sy.bishe.ygou.bean.AddressBean;
import com.sy.bishe.ygou.mapper.AddressMapper;
import com.sy.bishe.ygou.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<AddressBean> getAddressList(Integer id) {
        return addressMapper.getAddressList(id);
    }

    @Override
    public int deleteAddressById(Integer id) {
        return addressMapper.deleteAddressById(id);
    }

    @Override
    public int add(AddressBean addressBean) {
        return addressMapper.add(addressBean);
    }

}
