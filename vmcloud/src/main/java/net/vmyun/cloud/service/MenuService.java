package net.vmyun.cloud.service;

import com.baomidou.mybatisplus.service.IService;
import net.vmyun.entity.Menu;
import net.vmyun.cloud.entity.VO.ShowMenu;
import net.vmyun.cloud.entity.VO.ZtreeVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liulingxian
 * @since 2018-08-18
 */
public interface MenuService extends IService<Menu> {

    List<Menu> selectAllMenus(Map<String, Object> map);

    List<ZtreeVO> showTreeMenus();

    List<ShowMenu> getShowMenuByUser(Long id);

    void saveOrUpdateMenu(Menu menu);

    int getCountByPermission(String permission);

    int getCountByName(String name);

}
