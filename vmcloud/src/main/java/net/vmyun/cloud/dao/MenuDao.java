package net.vmyun.cloud.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import net.vmyun.entity.Menu;
import net.vmyun.cloud.entity.VO.ShowMenu;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author liulingxian
 * @since 2018-08-18
 */

public interface MenuDao extends BaseMapper<Menu> {

    List<Menu> showAllMenusList(Map map);

    List<Menu> getMenus(Map map);

    List<ShowMenu> selectShowMenuByUser(Map<String, Object> map);
}