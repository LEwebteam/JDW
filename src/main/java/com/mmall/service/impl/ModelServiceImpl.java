package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.dao.ModelMapper;
import com.mmall.pojo.Model;
import com.mmall.service.IModelService;
import com.mmall.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements IModelService {

    @Autowired
    ModelMapper modelMapper;

    public PageInfo getAllResultByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Model> models = modelMapper.selectAll();
        PageInfo<Model> pageInfo = new PageInfo<Model>(models);
        return pageInfo;
    }

  /*  public ServerResponse<byte[]> getDrawingById(Integer id) {//得到图纸
        ModelWithBLOBs m = modelMapper.selectByPrimaryKey(id);
        if (m != null || m.getDrawing() == null || m.getDrawing().length == 0) {
            return ServerResponse.createBySuccess(m.getDrawing());
        }
        return ServerResponse.createByErrorMessage("图纸不存在");
    }*/

    @Override
    public ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count) {
        return null;
    }

    @Override
    public ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count) {
        return null;
    }

    @Override
    public ServerResponse<CartVo> deleteModel(Integer userId, String productIds) {
        return null;
    }

    @Override
    public ServerResponse<CartVo> list(Integer userId) {
        return null;
    }

    @Override
    public ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, Integer checked) {
        return null;
    }

    @Override
    public ServerResponse<Integer> getCartProductCount(Integer userId) {
        return null;
    }
}
