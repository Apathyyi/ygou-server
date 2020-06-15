package com.sy.bishe.ygou.service.impl;

import com.sy.bishe.ygou.bean.BrandBean;
import com.sy.bishe.ygou.mapper.BrandMapper;
import com.sy.bishe.ygou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<BrandBean> getBrandById(Integer id) {
        return brandMapper.getBrandById(id);
    }

    @Override
    public String getBrandName(int type) {
        return brandMapper.getBrandName(type);
    }

    @Override
    public List<Integer> getIdByName(String name) {
        return brandMapper.getIdByName(name);
    }

    @Override
    public int getBrandIdByNameAndId(Integer type, String name) {
        return brandMapper.getBrandIdByNameAndId(type,name);
    }
}
