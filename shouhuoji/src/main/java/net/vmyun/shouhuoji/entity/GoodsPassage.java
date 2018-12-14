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
 * @author wanghai
 * @since 2018-09-24
 */
@TableName("vem_goods_passage")
public class GoodsPassage extends DataEntity<GoodsPassage> {

    private static final long serialVersionUID = 1L;
	@TableField(exist = false)
    private Goods goods;
    /**
     * vmId的编号
     */
	@TableField("vem_id")
	private String vmId;
	@TableField("number")
	private String number;

	@TableField("vem_row")
	private int vmRow;
	@TableField("vem_column")
	private int vmColumn;
	@TableField("goods_id")
	private String goodsId;
	@TableField("qty")
	private int qty;
	@TableField("order_id")
	private Long orderId;
	@TableField("deliver_qty")
	private int deliverQty;
	//是否需要加热，这个值是从订单明细中来，不需要保存到数据库
	@TableField(exist = false)
	private boolean heatFlag;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getVmId() {
		return vmId;
	}

	public void setVmId(String vmId) {
		this.vmId = vmId;
	}



	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getRemarks() {
		return remarks;
	}

	public int getVmRow() {
		return vmRow;
	}

	public void setVmRow(int vmRow) {
		this.vmRow = vmRow;
	}

	public int getVmColumn() {
		return vmColumn;
	}

	public void setVmColumn(int vmColumn) {
		this.vmColumn = vmColumn;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public boolean getHeatFlag() {
		return heatFlag;
	}

	public void setHeatFlag(boolean heatFlag) {
		this.heatFlag = heatFlag;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getDeliverQty() {
		return deliverQty;
	}

	public void setDeliverQty(int deliverQty) {
		this.deliverQty = deliverQty;
	}

}
