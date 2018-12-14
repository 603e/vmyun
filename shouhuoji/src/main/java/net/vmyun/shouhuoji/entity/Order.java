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
@TableName("vem_order")
public class Order extends DataEntity<Order> {

	private static final long serialVersionUID = 1L;

	//订单状态 01：创建
	public static final String STUTAS_CREAT="01";
	//订单状态 03：支付中
	public static final String STUTAS_PAYING="03";
	//订单状态 05：支付失败
	public static final String STUTAS_FAILURE="05";
	//订单状态 06：支付成功
	public static final String STUTAS_SUCESS="06";

	//支付方式 01：现金
	public static final String METHOD_CASH="01";
	//支付方式 02：微信
	public static final String METHOD_WEIXIN="02";
	//支付方式 03：支付成功
	public static final String METHOD_ALIPAY="03";
	/**
	 * 为了解决cannot deserialize from Object value (no delegate- or property-based Creator)问题
	 * */
	public Order() {	}

	public Order(String number,int vemId,String payMethod, int totalQty, float totalAmt) {
		this.number = number;
		this.vemId = vemId;
		this.payMethod = payMethod;
		this.totalQty = totalQty;
		this.totalAmt = totalAmt;
	}
	@TableField("number")
	private String number;
	/**
	 * 自动售卖机ID
	 */
	@TableField("vem_id")
	private int vemId;
    /**
     * 支付方式
     */
	@TableField("pay_method")
	private String payMethod;
	/**
	 * 支付状态
	 * */
	@TableField("status")
	private String status;

	@TableField("total_qty")
	private int totalQty;
	/**
	 * 订单金额
	 * */
	@TableField("total_amt")
	private float totalAmt;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getVemId() {
		return vemId;
	}

	public void setVemId(int vemId) {
		this.vemId = vemId;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	public float getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}
}
