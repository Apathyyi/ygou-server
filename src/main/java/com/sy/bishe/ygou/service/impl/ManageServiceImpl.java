package com.sy.bishe.ygou.service.impl;


import com.sy.bishe.ygou.bean.AdminBean;
import com.sy.bishe.ygou.mapper.ManageMapper;
import com.sy.bishe.ygou.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    ManageMapper manageMapper;
    @Override
    public AdminBean getAdminByName(String name) {
        return manageMapper.getAdminByName( name);
    }
}
