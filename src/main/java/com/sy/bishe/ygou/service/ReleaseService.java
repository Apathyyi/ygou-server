package com.sy.bishe.ygou.service;

import com.sy.bishe.ygou.bean.MyReleaseBean;
import com.sy.bishe.ygou.bean.ReleaseBean;

import java.util.List;

public interface ReleaseService {

    int addReleaseBean(ReleaseBean releaseBean);

    List<ReleaseBean> getUserBuyListById(Integer id);

    List<ReleaseBean> getReleaseList();

    ReleaseBean getReleaseById(Integer id);

    int DeleteReleaseById(Integer id);

    int updateReleaseBean(ReleaseBean releaseBean);
}
