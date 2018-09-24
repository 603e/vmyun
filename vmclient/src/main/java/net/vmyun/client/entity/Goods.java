package net.vmyun.client.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import net.vmyun.base.DataEntity;

import java.math.BigDecimal;
import java.util.Set;

/**
 * <p>
 * 
 * </p>
 *
 * @author liulingxian
 * @since 2018-08-18
 */
@TableName("vm_goods")
public class Goods extends DataEntity<Goods> {

    private static final long serialVersionUID = 1L;
	/**
	 * 商品编码
	 */
	private String code;
    /**
     * 商品名称
     */
	@TableField("name")
	private String name;
	@TableField("icon")
	private String icon;

	@TableField("href")
	private String href;
	@TableField("price")
	private BigDecimal price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
