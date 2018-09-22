package net.vmyun.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import net.vmyun.base.DataEntity;

import java.util.Set;

/**
 * <p>
 * 
 * </p>
 *
 * @author liulingxian
 * @since 2018-08-18
 */
@TableName("sys_role")
public class Role extends DataEntity<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
	private String name;

	@TableField(exist = false)
	private Set<Menu> menuSet;

	@TableField(exist = false)
	private Set<User> userSet;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Menu> getMenuSet() {
		return menuSet;
	}

	public void setMenuSet(Set<Menu> menuSet) {
		this.menuSet = menuSet;
	}

	public Set<User> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}
}
