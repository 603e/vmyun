package net.vmyun.cloud.entity;

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
	@TableField("amount")
	private String amount;
	@TableField("create_date")
	private String createDate;
	@TableField("create_by")
	private String createBy;
	@TableField("update_date")
	private String updateDate;
	@TableField("update_by")
	private String updateBy;
	@TableField("remarks")
	private String remarks;
	@TableField("del_flag")
	private String del_flag;
	@TableField("unit_price")
	private String unitPrice;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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




	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}



	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}



}
