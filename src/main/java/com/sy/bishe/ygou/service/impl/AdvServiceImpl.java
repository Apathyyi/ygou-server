package com.sy.bishe.ygou.service.impl;

import com.sy.bishe.ygou.bean.AdvBean;
import com.sy.bishe.ygou.mapper.AdvMapper;
import com.sy.bishe.ygou.service.AdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdvServiceImpl implements AdvService {

    @Autowired
    private AdvMapper advMapper ;
    @Override
    public List<AdvBean> getAdvList() {
        return advMapper.getAdvList();
    }
}
