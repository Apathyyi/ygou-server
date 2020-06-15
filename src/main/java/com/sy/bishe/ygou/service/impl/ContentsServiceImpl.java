package com.sy.bishe.ygou.service.impl;


import com.sy.bishe.ygou.bean.ContentsBean;
import com.sy.bishe.ygou.mapper.ContentsMapper;
import com.sy.bishe.ygou.service.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentsServiceImpl implements ContentsService {

    @Autowired
    ContentsMapper contentsMapper;

    @Override
    public List<ContentsBean> getContentsByOrderId(Integer id) {
        return contentsMapper.getContentsByOrderId(id);
    }

    @Override
    public int addContent(ContentsBean contentsBean) {
        return contentsMapper.addContent(contentsBean);
    }
}
