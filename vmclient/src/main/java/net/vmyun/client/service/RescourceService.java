package net.vmyun.client.service;

import net.vmyun.client.entity.Rescource;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 系统资源 服务类
 * </p>
 *
 * @author liulingxian
 * @since 2018-01-14
 */
public interface RescourceService extends IService<Rescource> {

    int getCountByHash(String hash);

    Rescource getRescourceByHash(String hash);

}
