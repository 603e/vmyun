package net.vmyun.cloud.service;

import net.vmyun.cloud.entity.UploadInfo;
import com.baomidou.mybatisplus.service.IService;
/**
 * <p>
 * 文件上传配置 服务类
 * </p>
 *
 * @author liulingxian
 * @since 2018-07-06
 */
public interface UploadInfoService extends IService<UploadInfo> {

    public UploadInfo getOneInfo();

    public void updateInfo(UploadInfo uploadInfo);

}
