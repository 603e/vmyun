package net.vmyun.shouhuoji.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import net.vmyun.shouhuoji.dao.GoodsPassageDao;
import net.vmyun.shouhuoji.entity.Goods;
import net.vmyun.shouhuoji.entity.GoodsPassage;
import net.vmyun.shouhuoji.service.GoodsPassageService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
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
    public Boolean addGoodsPassage(JSONObject josnObject, int vmId) {
        JSONArray jsonArray=JSONArray.parseArray((String) josnObject.get("rows"));
        int count=0;
        for (int i=0;i<jsonArray.size();i++){
            JSONObject goodsPassageJson=(JSONObject)jsonArray.get(i);
            GoodsPassage  goodsPassage=setValue(goodsPassageJson);
            if (goodsPassageJson.get("id")!=null && !goodsPassageJson.get("id").equals("")){
                EntityWrapper<GoodsPassage> GoodsEntityWrapper = new EntityWrapper<>();
                GoodsEntityWrapper.eq("id",goodsPassageJson.get("id"));
                goodsPassage.setVmId((String)goodsPassageJson.get("vmId"));
                goodsPassage.setUpdateDate(new Date());
                count=baseMapper.update(goodsPassage,GoodsEntityWrapper);
            }else{
                goodsPassage.setVmId(String.valueOf(vmId));
                goodsPassage.setCreateDate(new Date());
                count=baseMapper.insertAllColumn(goodsPassage);
            }
        }
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

    public static  GoodsPassage setValue(JSONObject goodsPassageJson){
        GoodsPassage goodsPassage=new GoodsPassage();
        goodsPassage.setNumber((String)goodsPassageJson.get("number"));
        goodsPassage.setGoodsId((String)goodsPassageJson.get("goodsId"));
        goodsPassage.setCreateId(Long.valueOf(3));
        goodsPassage.setDelFlag(false);
        //goodsPassage.setPrice( new BigDecimal(String.valueOf(goodsPassageJson.get("price"))));
        goodsPassage.setQty(Integer.parseInt( goodsPassageJson.getString("qty")));
        goodsPassage.setRemarks((String)goodsPassageJson.get("remarks"));
        goodsPassage.setUpdateId(Long.valueOf(3));
        goodsPassage.setVmColumn(Integer.parseInt(goodsPassageJson.getString("vmColumn")));
        goodsPassage.setVmRow(Integer.parseInt(goodsPassageJson.getString("vmRow")));
        return goodsPassage;
    }
}
