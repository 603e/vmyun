package net.vmyun.cloud.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import net.vmyun.cloud.dao.GroupDao;
import net.vmyun.cloud.entity.Group;
import net.vmyun.cloud.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liulingxian
 * @since 2018-08-18
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class GroupServiceImpl extends ServiceImpl<GroupDao, Group> implements GroupService {
	
}
