package net.vmyun.client.service;

import com.baomidou.mybatisplus.service.IService;
import net.vmyun.client.entity.Log;

import java.util.List;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author liulingxian
 * @since 2018-01-13
 */
public interface LogService extends IService<Log> {

    public List<Integer> selectSelfMonthData();

}
