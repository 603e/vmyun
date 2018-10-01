package net.vmyun.client.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import net.vmyun.client.entity.Goods;
import net.vmyun.client.entity.GoodsPassage;


import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liulingxian
 * @since 2018-08-18
 */
public interface GoodsPassageService extends IService<GoodsPassage> {
    Goods findGoodsByid(Long id);
    List<Goods> selectGoodsList();
    Boolean addGoodsPassage(JSONObject josnObject);
}
