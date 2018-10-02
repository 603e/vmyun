package net.vmyun.client.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import net.vmyun.base.DataEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanghai
 * @since 2018-09-24
 */
@TableName("vm_goods_passage")
public class GoodsPassage extends DataEntity<GoodsPassage> {

    private static final long serialVersionUID = 1L;
	@TableField(exist = false)
    private Goods goods;
    /**
     * vmId的编号
     */
	@TableField("vm_id")
	private String vmId;
	@TableField("number")
	private String number;

	@TableField("vm_row")
	private String vmRow;
	@TableField("vm_column")
	private String vmColumn;
	@TableField("goods_id")
	private String goodsId;
	@TableField("qty")
	private String qty;
	@TableField("create_date")
	private String createDate;
	@TableField("remarks")
	private String remarks;
	@TableField("price")
	private String price;
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

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getVmRow() {
		return vmRow;
	}

	public void setVmRow(String vmRow) {
		this.vmRow = vmRow;
	}

	public String getVmColumn() {
		return vmColumn;
	}

	public void setVmColumn(String vmColumn) {
		this.vmColumn = vmColumn;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}


	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}




}
