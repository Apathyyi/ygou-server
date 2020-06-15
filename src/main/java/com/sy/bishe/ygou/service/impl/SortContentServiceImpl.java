package com.sy.bishe.ygou.service.impl;

import com.sy.bishe.ygou.bean.SortContentBean;
import com.sy.bishe.ygou.mapper.SortContentMapper;
import com.sy.bishe.ygou.service.SortContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortContentServiceImpl implements SortContentService {

    @Autowired
    private SortContentMapper sortContentMapper;

    @Override
    public List<SortContentBean> getSortContentById(Integer id) {
        return sortContentMapper.getSortContent(id);
    }

    @Override
    public int getBrandIdByName(String brand) {
        return sortContentMapper.getBrandIdByName(brand);
    }

    @Override
    public List<Integer> getSortContentByIds(List<Integer> ids) {
        return sortContentMapper.getSortContentByIds(ids);
    }
}
