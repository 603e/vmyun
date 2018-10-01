package net.vmyun.cloud.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import net.vmyun.cloud.entity.Goods;
import net.vmyun.cloud.entity.GoodsPassage;
import sun.util.resources.ga.LocaleNames_ga;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liulingxian
 * @since 2018-08-18
 */
public interface GoodsService extends IService<GoodsPassage> {
    Goods findGoodsByid(Long id);
    List<Goods> selectGoodsList();
    Boolean addGoodsPassage(JSONObject josnObject);
}
