package com.sy.bishe.ygou.service.impl;

import com.sy.bishe.ygou.bean.PicturesBean;
import com.sy.bishe.ygou.mapper.PicturesMapper;
import com.sy.bishe.ygou.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PicturesMapper picturesMapper;

    @Override
    public PicturesBean getPicturesById(int id) {
        return picturesMapper.getPicturesById(id);
    }

    @Override
    public List<PicturesBean> getPicturesList() {
        return picturesMapper.getPicturesList();
    }

    @Override
    public int add(PicturesBean picturesBean) {
        return picturesMapper.add(picturesBean);
    }

    @Override
    public int delete(Integer id) {
        return picturesMapper.delete(id);
    }

    @Override
    public List<PicturesBean> getPicturesByGoodsId(Integer id) {
        return picturesMapper.getPicturesListByGoodsId(id);
    }
}
