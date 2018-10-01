package net.vmyun.client.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import net.vmyun.client.dao.GoodsPassageDao;
import net.vmyun.client.entity.Goods;
import net.vmyun.client.entity.GoodsPassage;
import net.vmyun.client.service.GoodsPassageService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liulingxian
 * @since 2018-08-18
 */
@Service("goodsPassageService")
@Transactional( rollbackFor = Exception.class)
public class GoodsPassageServiceImpl extends ServiceImpl<GoodsPassageDao, GoodsPassage> implements GoodsPassageService {


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
        int count;
        for (int i=0;i<jsonArray.size();i++){
            JSONObject goodsPassageJson=(JSONObject)jsonArray.get(i);
            GoodsPassage  goodsPassage=setValue(goodsPassageJson);
            if (goodsPassageJson.get("id")!=null && !goodsPassageJson.get("id").equals("")){
                EntityWrapper<GoodsPassage> GoodsEntityWrapper = new EntityWrapper<>();
                GoodsEntityWrapper.eq("id",goodsPassageJson.get("id"));
                count=baseMapper.update(goodsPassage,GoodsEntityWrapper);
            }else{
                count=baseMapper.insertAllColumn(goodsPassage);
            }
        }
        return true;
    }


    public GoodsPassage setValue(JSONObject goodsPassageJson){
        GoodsPassage goodsPassage=new GoodsPassage();
        goodsPassage.setNumber((String)goodsPassageJson.get("number"));
        goodsPassage.setGoodsId((String)goodsPassageJson.get("goodsId"));
        goodsPassage.setCreateId(Long.valueOf(3));
        goodsPassage.setDelFlag(false);
        goodsPassage.setPrice((String)goodsPassageJson.get("price"));
        goodsPassage.setQty((String)goodsPassageJson.get("qty"));
        goodsPassage.setRemarks((String)goodsPassageJson.get("remarks"));
        goodsPassage.setUpdateId(Long.valueOf(3));
        goodsPassage.setVmColumn((String)goodsPassageJson.get("vmColumn"));
        goodsPassage.setVmId((String)goodsPassageJson.get("vmId"));
        goodsPassage.setVmRow((String)goodsPassageJson.get("vmRow"));
        return goodsPassage;
    }
}
