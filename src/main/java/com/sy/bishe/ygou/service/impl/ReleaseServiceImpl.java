package com.sy.bishe.ygou.service.impl;


import com.sy.bishe.ygou.bean.MyReleaseBean;
import com.sy.bishe.ygou.bean.ReleaseBean;
import com.sy.bishe.ygou.mapper.ReleaseMapper;
import com.sy.bishe.ygou.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    @Autowired
    private ReleaseMapper releaseMapper;
    @Override
    public int addReleaseBean(ReleaseBean releaseBean) {
        return releaseMapper.addReleaseBean(releaseBean);
    }

    @Override
    public List<ReleaseBean> getUserBuyListById(Integer id) {
        return releaseMapper.getUserBuyListById(id);
    }

    @Override
    public List<ReleaseBean> getReleaseList() {
        return releaseMapper.getReleaseList();
    }

    @Override
    public ReleaseBean getReleaseById(Integer id) {
        return releaseMapper.getReleaseById(id);
    }

    @Override
    public int DeleteReleaseById(Integer id) {

        return releaseMapper.DeleteReleaseById(id);
    }

    @Override
    public int updateReleaseBean(ReleaseBean releaseBean) {
        return releaseMapper.updateReleaseBean(releaseBean);
    }
}
