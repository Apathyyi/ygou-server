package com.sy.bishe.ygou.service.impl;


import com.sy.bishe.ygou.bean.EvaluateBean;
import com.sy.bishe.ygou.mapper.EvaluateMapper;
import com.sy.bishe.ygou.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateMapper evaluateMapper;

    @Override
    public int addEvaluate(EvaluateBean evaluate) {
        return evaluateMapper.addEvaluate(evaluate);
    }

    @Override
    public List<EvaluateBean> getEvaluateByOrderId(int parseInt) {
        return evaluateMapper.getEvaluateByOrderId(parseInt);
    }

    @Override
    public List<EvaluateBean> getEvaluateByHot() {
        return evaluateMapper.getEvaluateByHot();
    }

    @Override
    public List<EvaluateBean> getEvaluateList() {
        return evaluateMapper.getEvaluateList();
    }
}
