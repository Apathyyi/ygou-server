package com.sy.bishe.ygou.service;

import com.sy.bishe.ygou.bean.AddressBean;

import java.util.List;

public interface AddressService {

    List<AddressBean> getAddressList(Integer id);

    int deleteAddressById(Integer id);

    int add(AddressBean addressBean);
}
