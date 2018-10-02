package net.vmyun.client.slaver.service;

import net.vmyun.client.entity.GoodsPassage;

import java.util.List;

/**
 * Created by Administrator on 2018/9/30.
 */
public interface SerialService {

    /**
     *根据参数中货道的信息进行出货指令
     * @param goodsPassages*/
    public String deliverGoodsCmd(List<GoodsPassage> goodsPassages);

}
