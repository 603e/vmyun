package net.vmyun.shouhuoji.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import net.vmyun.base.DataEntity;

import java.math.BigDecimal;


/**
 * <p>
 * 
 * </p>
 *
 * @author liulingxian
 * @since 2018-08-18
 */
@TableName("vem_order_detail")
public class OrderDetail extends DataEntity<OrderDetail> {

    private static final long serialVersionUID = 1L;
	 //订单ID
	@TableField("order_id")
	private Long orderId;
     // 商品ID
	@TableField("goods_id")
	private String goodsId;
	//商品编号
	@TableField("goods_number")
	private String goodsNumber;
	//商品数量
	@TableField("goods_qty")
	private int goodsQty;
	//单价
	@TableField("goods_price")
	private float goodsPrice;
	//
	@TableField("heat_flag")
	private boolean heatFlag;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public boolean isHeatFlag() {
		return heatFlag;
	}

	public void setHeatFlag(boolean heatFlag) {
		this.heatFlag = heatFlag;
	}

	public int getGoodsQty() {
		return goodsQty;
	}

	public void setGoodsQty(int goodsQty) {
		this.goodsQty = goodsQty;
	}

	public float getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
}
