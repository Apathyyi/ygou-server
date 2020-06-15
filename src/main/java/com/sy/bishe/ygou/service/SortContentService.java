package com.sy.bishe.ygou.service;

import com.sy.bishe.ygou.bean.SortContentBean;

import java.util.List;

public interface SortContentService {
    List<SortContentBean> getSortContentById(Integer id);

    int getBrandIdByName(String brand);

    List<Integer> getSortContentByIds(List<Integer> ids);
}
