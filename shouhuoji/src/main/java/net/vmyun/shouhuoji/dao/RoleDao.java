package net.vmyun.shouhuoji.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import net.vmyun.entity.Menu;
import net.vmyun.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liulingxian
 * @since 2018-08-18
 */
public interface RoleDao extends BaseMapper<Role> {

    Role selectRoleById(@Param("id") Long id);

    void saveRoleMenus(@Param("roleId") Long id, @Param("menus") Set<Menu> menus);

    void dropRoleMenus(@Param("roleId")Long roleId);

    void dropRoleUsers(@Param("roleId")Long roleId);
}