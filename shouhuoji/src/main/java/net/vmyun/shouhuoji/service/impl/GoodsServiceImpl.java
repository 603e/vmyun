package net.vmyun.shouhuoji.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import net.vmyun.shouhuoji.dao.GoodsDao;

import net.vmyun.shouhuoji.entity.Goods;

import net.vmyun.shouhuoji.service.GoodsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liulingxian
 * @since 2018-08-18
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {
	
}
