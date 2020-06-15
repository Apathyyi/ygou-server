package com.sy.bishe.ygou.service;


import com.sy.bishe.ygou.bean.EvaluateBean;

import java.util.List;

public interface EvaluateService {
    int addEvaluate(EvaluateBean evaluateBean);

    List<EvaluateBean> getEvaluateByOrderId(int parseInt);

    List<EvaluateBean> getEvaluateByHot();

    List<EvaluateBean> getEvaluateList();
}
