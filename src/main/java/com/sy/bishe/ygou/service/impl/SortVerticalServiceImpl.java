package com.sy.bishe.ygou.service.impl;

import com.sy.bishe.ygou.bean.SortVerticalBean;
import com.sy.bishe.ygou.mapper.SortVerticalMapper;
import com.sy.bishe.ygou.service.SortVerticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortVerticalServiceImpl implements SortVerticalService {

    @Autowired
    private SortVerticalMapper sortVerticalMapper;

    @Override
    public List<SortVerticalBean> getSortVertical() {
        return sortVerticalMapper.getSortVertical() ;
    }
}
