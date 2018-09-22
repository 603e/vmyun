package net.vmyun.cloud.controller.system;


import net.vmyun.annotation.SysLog;
import net.vmyun.cloud.base.BaseController;
import net.vmyun.cloud.entity.Site;
import net.vmyun.cloud.entity.UploadInfo;
import net.vmyun.cloud.service.UploadService;
import net.vmyun.util.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liulingxian
 * @since 2017-12-30
 */
@Controller
@RequestMapping("/admin/system/site")
public class SiteController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    @Qualifier("ossService")
    private UploadService ossService;

    @RequiresPermissions("sys:site:list")
    @GetMapping("show")
    @SysLog("跳转网站展示页面")
    public String show(Model model){
        Site site = siteService.getCurrentSite();
        model.addAttribute("site",site);
        return "admin/system/site/show";
    }

    @RequiresPermissions("sys:site:list")
    @GetMapping("oss")
    public String oss(Model model){
        UploadInfo uploadInfo = uploadInfoService.getOneInfo();
        model.addAttribute("info",uploadInfo);
        return "admin/system/site/oss";
    }

    @RequiresPermissions("sys:site:editOss")
    @PostMapping("editOss")
    @ResponseBody
    @Transactional
    public RestResponse editOss(UploadInfo uploadInfo, HttpServletRequest httpServletRequest){
        Site site = (Site)httpServletRequest.getAttribute("site");
        if(site == null){
            return RestResponse.failure("加载信息错误");
        }
        if(null == uploadInfo.getId() || 0 == uploadInfo.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(!ossService.testAccess(uploadInfo)){
            return RestResponse.failure("阿里云上传测试检测失败");
        }
        UploadInfo oldInfo = uploadInfoService.selectById(uploadInfo.getId());
        uploadInfo.setQiniuDir(oldInfo.getQiniuDir());
        uploadInfoService.updateInfo(uploadInfo);
        site.setFileUploadType("oss");
        siteService.updateSite(site);
        return RestResponse.success();
    }

    @RequiresPermissions("sys:site:list")
    @GetMapping("qiniu")
    public String qiniu(Model model){
        UploadInfo uploadInfo = uploadInfoService.getOneInfo();
        model.addAttribute("info",uploadInfo);
        return "admin/system/site/qiniu";
    }

    @RequiresPermissions("sys:site:editQiniu")
    @PostMapping("editQiniu")
    @ResponseBody
    @Transactional
    public RestResponse editQiniu(UploadInfo uploadInfo,HttpServletRequest httpServletRequest){
        Site site = (Site)httpServletRequest.getAttribute("site");
        if(site == null){
            return RestResponse.failure("加载信息错误");
        }
        if(null == uploadInfo.getId() || 0 == uploadInfo.getId()){
            return RestResponse.failure("ID不能为空");
        }
        UploadInfo oldInfo = uploadInfoService.selectById(uploadInfo.getId());
        uploadInfo.setOssDir(oldInfo.getOssDir());
        uploadInfoService.updateInfo(uploadInfo);
        site.setFileUploadType("qiniu");
        siteService.updateSite(site);
        return RestResponse.success();
    }

    @RequiresPermissions("sys:site:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存网站基本数据")
    public RestResponse edit(Site site,HttpServletRequest httpServletRequest){
        if(site.getId() == null){
            return RestResponse.failure("ID不能为空");
        }
        if(site.getId() != 1){
            return RestResponse.failure("ID不正确");
        }
        if(StringUtils.isBlank(site.getName())){
            return RestResponse.failure("站点名称不能为空");
        }
        if(StringUtils.isNotBlank(site.getRemarks())){
            site.setRemarks(site.getRemarks().replace("\"", "\'"));
        }
        if("oss".equals(site.getFileUploadType())){
            UploadInfo uploadInfo = uploadInfoService.getOneInfo();
            if(StringUtils.isBlank(uploadInfo.getOssKeySecret()) ||
               StringUtils.isBlank(uploadInfo.getOssKeyId()) ||
               StringUtils.isBlank(uploadInfo.getOssEndpoint())){
                return RestResponse.failure("阿里云存储信息不能为空");
            }
        }
        siteService.updateSite(site);
        return RestResponse.success();
    }
}
