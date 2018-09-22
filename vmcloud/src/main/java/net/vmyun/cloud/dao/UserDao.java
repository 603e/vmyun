package net.vmyun.cloud.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import net.vmyun.entity.Role;
import net.vmyun.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.Set;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liulingxian
 * @since 2018-08-18
 */
public interface UserDao extends BaseMapper<User> {
	User selectUserByMap(Map<String, Object> map);

	void saveUserRoles(@Param("userId") Long id, @Param("roleIds") Set<Role> roles);

	void dropUserRolesByUserId(@Param("userId") Long userId);

	Map selectUserMenuCount();
}