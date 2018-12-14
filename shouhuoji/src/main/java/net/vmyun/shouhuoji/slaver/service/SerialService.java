package net.vmyun.shouhuoji.slaver.service;

import net.vmyun.shouhuoji.entity.GoodsPassage;
import net.vmyun.shouhuoji.entity.Order;

import java.util.List;

/**
 * Created by Administrator on 2018/9/30.
 */
public interface SerialService {

    /**
     *根据参数中货道的信息进行出货指令
     * @param goodsPassages*/
    public String deliverGoodsCmd(List<GoodsPassage> goodsPassages);

    /**
     *根据订单给收款机下达收款指令
     * @param order*/
    public String cashReceiptCmd(Order order);

}
