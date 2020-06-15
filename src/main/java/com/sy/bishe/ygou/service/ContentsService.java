package com.sy.bishe.ygou.service;


import com.sy.bishe.ygou.bean.ContentsBean;

import java.util.List;

public interface ContentsService {
    List<ContentsBean> getContentsByOrderId(Integer id);

    int addContent(ContentsBean contentsBean);
}
