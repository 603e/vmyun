package net.vmyun.cloud.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import net.vmyun.cloud.dao.GoodsPassageDao;

import net.vmyun.cloud.entity.Goods;
import net.vmyun.cloud.entity.GoodsPassage;
import net.vmyun.cloud.service.GoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liulingxian
 * @since 2018-08-18
 */
@Service("goodsServiceImpl")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class GoodsServiceImpl extends ServiceImpl<GoodsPassageDao, GoodsPassage> implements GoodsService {


    @Override
    public Goods findGoodsByid(Long id) {
        return baseMapper.findGoodsByid(id);
    }

    @Override
    public List<Goods> selectGoodsList() {
        return baseMapper.selectGoodsList();
    }

    @Override
    public Boolean addGoodsPassage(JSONObject josnObject) {
        JSONArray jsonArray=JSONArray.parseArray((String) josnObject.get("rows"));
        for (int i=0;i<jsonArray.size();i++){
           JSONObject goodsPassageJson=(JSONObject)jsonArray.get(i);
           GoodsPassage goodsPassage=new GoodsPassage();
            goodsPassage.setNumber((String)goodsPassageJson.get("number"));
            goodsPassage.setGoodsId((String)goodsPassageJson.get("number"));
            goodsPassage.setCreateBy((String)goodsPassageJson.get("number"));
            goodsPassage.setDel_flag((String)goodsPassageJson.get("number"));
            goodsPassage.setPrice((String)goodsPassageJson.get("number"));
            goodsPassage.setPrice((String)goodsPassageJson.get("number"));
            goodsPassage.setQty((String)goodsPassageJson.get("number"));
            goodsPassage.setRemarks((String)goodsPassageJson.get("number"));
            goodsPassage.setUpdateBy((String)goodsPassageJson.get("number"));
            goodsPassage.setVmColumn((String)goodsPassageJson.get("number"));
            goodsPassage.setVmId((String)goodsPassageJson.get("number"));
            goodsPassage.setVmRow((String)goodsPassageJson.get("number"));
           baseMapper.insertAllColumn(goodsPassage);
        }
        int count=baseMapper.addGoodsPassage();
        return true;
    }
}
