package net.vmyun.cloud.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import net.vmyun.cloud.entity.Goods;
import net.vmyun.cloud.entity.GoodsPassage;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liulingxian
 * @since 2018-08-18
 */
public interface GoodsPassageDao extends BaseMapper<GoodsPassage> {
    Goods findGoodsByid(@Param("id")Long id);
    List<Goods> selectGoodsList();
    int addGoodsPassage();
}