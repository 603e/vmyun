package net.vmyun.shouhuoji.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import net.vmyun.shouhuoji.dao.GoodsDao;
import net.vmyun.shouhuoji.dao.OrderDao;
import net.vmyun.shouhuoji.entity.Goods;
import net.vmyun.shouhuoji.entity.GoodsPassage;
import net.vmyun.shouhuoji.entity.Order;
import net.vmyun.shouhuoji.service.GoodsService;
import net.vmyun.shouhuoji.service.OrderService;
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
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {

}
