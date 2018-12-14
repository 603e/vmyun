package net.vmyun.shouhuoji.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import net.vmyun.annotation.SysLog;
import net.vmyun.shouhuoji.base.BaseController;
import net.vmyun.shouhuoji.config.VemConfig;
import net.vmyun.shouhuoji.entity.GoodsPassage;
import net.vmyun.shouhuoji.entity.Order;
import net.vmyun.shouhuoji.entity.OrderDetail;
import net.vmyun.shouhuoji.service.GoodsPassageService;
import net.vmyun.shouhuoji.service.OrderDetailService;
import net.vmyun.shouhuoji.service.OrderService;
import net.vmyun.shouhuoji.slaver.service.SerialService;
import net.vmyun.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/9/15.
 */
@Controller
@RequestMapping("vem/order")
public class OrderController extends BaseController {

    SimpleDateFormat numberSdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    @Autowired
    protected SerialService serialService;

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected OrderDetailService orderDetailService;
    @Autowired
    protected GoodsPassageService goodsPassageService;

    //@RequiresPermissions("sys:user:delete")
    @PostMapping("orderByCash")
    @ResponseBody
    @SysLog("用户现金下单)")
    public RestResponse orderByCash(@RequestBody List<OrderDetail> orderDetails){
        if(orderDetails == null || orderDetails.size()==0){
            return RestResponse.failure("没有订单明细数据");
        }

        int totalQty = 0;
        float totalAmt = 0.0f;
        Order order = new Order(numberSdf.format(new Date()),vemConfig.getId(), Order.METHOD_CASH,totalQty,totalAmt);
        orderService.insert(order);
        for (OrderDetail orderDetail : orderDetails){
            EntityWrapper ew=new EntityWrapper();
            //ew.setEntity(new GoodsPassage());
            ew.where("goods_id = {0}",orderDetail.getGoodsId()).andNew("vem_id = {0}",vemConfig.getId())
                    .andNew("qty > {0}",orderDetail.getGoodsQty()).andNew("order_id IS NULL").orderBy("vem_row").orderBy("vem_column");
            GoodsPassage goodsPassage = goodsPassageService.selectOne(ew);
            if(goodsPassage==null){
                return RestResponse.failure("下单失败！"+orderDetail.getGoodsNumber()+"缺货，请和管理员联系。");
            };
            goodsPassage.setOrderId(order.getId());
            goodsPassage.setDeliverQty(orderDetail.getGoodsQty());
            if(!goodsPassageService.updateById(goodsPassage)){
                return RestResponse.failure("下单失败！更新货道订单ID失败");
            };
            totalQty = totalQty + orderDetail.getGoodsQty();
            totalAmt = totalAmt + orderDetail.getGoodsQty()*orderDetail.getGoodsPrice();
            orderDetail.setOrderId(order.getId());
        }
        order.setTotalQty(totalQty);
        order.setTotalAmt(totalAmt);
        orderDetailService.insertBatch(orderDetails);
        orderService.updateById(order);
        return RestResponse.success("下单成功").setData(order);
    }

    @PostMapping("payByCash")
    @ResponseBody
    @SysLog("现金支付")
    public RestResponse payByCash(@RequestBody Order order){
        if(order == null ){
            return RestResponse.failure("没有订单数据");
        }
        serialService.cashReceiptCmd(order);
        return RestResponse.success("支付成功").setData(order);
    }

    @PostMapping("deliver")
    @ResponseBody
    @SysLog("商品出货")
    public RestResponse deliverGoods(@RequestBody Order order){
        if(order == null ){
            return RestResponse.failure("没有订单数据");
        }
        EntityWrapper ew=new EntityWrapper();
        ew.setEntity(new GoodsPassage());
        ew.where("vem_id = {0}",vemConfig.getId())
                .andNew("order_id = {0}",order.getId());
        List<GoodsPassage> goodsPassages = goodsPassageService.selectList(ew);
        serialService.deliverGoodsCmd(goodsPassages);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RestResponse.success();
    }
}
