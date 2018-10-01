package net.vmyun.client.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.vmyun.annotation.SysLog;
import net.vmyun.client.base.BaseController;
import net.vmyun.client.base.MySysUser;
import net.vmyun.util.Constants;
import net.vmyun.util.RestResponse;
import net.vmyun.util.VerifyCodeUtil;
import net.vmyun.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

@Controller
public class LoginController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Value("${server.port}")
	private String port;

	@GetMapping("login")
	public String login(HttpServletRequest request) {
		LOGGER.info("跳到这边的路径为:"+request.getRequestURI());
		Subject s = SecurityUtils.getSubject();
		LOGGER.info("是否记住登录--->"+s.isRemembered()+"<-----是否有权限登录----->"+s.isAuthenticated()+"<----");
		if(s.isAuthenticated()){
			return "redirect:index";
		}else {
			//牛星用这个返回
			return "redirect:goods/list";
			//王海用下面这一行，并注释上面那一行
			//return "login";
		}
	}

	@PostMapping("login/main")
	@ResponseBody
	@SysLog("用户登录")
	public RestResponse loginMain(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		String code = request.getParameter("code");
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			return RestResponse.failure("用户名或者密码不能为空");
		}
		Map<String,Object> map = Maps.newHashMap();
		String error = null;
		HttpSession session = request.getSession();
		if(session == null){
			return RestResponse.failure("session超时");
		}
			/*就是代表当前的用户。*/
			Subject user = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username,password,Boolean.valueOf(rememberMe));
			try {
				user.login(token);
				if (user.isAuthenticated()) {
					map.put("url","index");
				}
			}catch (IncorrectCredentialsException e) {
				error = "登录密码错误.";
			} catch (ExcessiveAttemptsException e) {
				error = "登录失败次数过多";
			} catch (LockedAccountException e) {
				error = "帐号已被锁定.";
			} catch (DisabledAccountException e) {
				error = "帐号已被禁用.";
			} catch (ExpiredCredentialsException e) {
				error = "帐号已过期.";
			} catch (UnknownAccountException e) {
				error = "帐号不存在";
			} catch (UnauthorizedException e) {
				error = "您没有得到相应的授权！";
			}
		if(StringUtils.isBlank(error)){
			return RestResponse.success("登录成功").setData(map);
		}else{
			return RestResponse.failure(error);
		}
	}
	
	@GetMapping("index")
	public String showView(Model model){
		return "index";
	}


	/**
	 * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
	 */
	@GetMapping("/genCaptcha")
	public void genCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 4, null);
		//将验证码放到HttpSession里面
		request.getSession().setAttribute(Constants.VALIDATE_CODE, verifyCode);
		LOGGER.info("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
		//设置输出的内容的类型为JPEG图像
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 116, 36, 5, true, new Color(249,205,173), null, null);
		//写给浏览器
		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
	}

	@GetMapping("main")
	public String main(Model model){
		Map map = userService.selectUserMenuCount();
		User user = userService.findUserById(MySysUser.id());
		Set<net.vmyun.entity.Menu> menus = user.getMenus();
		java.util.List<net.vmyun.entity.Menu> showMenus = Lists.newArrayList();
		if(menus != null && menus.size()>0){
			for (net.vmyun.entity.Menu menu : menus){
				if(StringUtils.isNotBlank(menu.getHref())){
					Long result = (Long)map.get(menu.getPermission());
					if(result != null){
						menu.setDataCount(result.intValue());
						showMenus.add(menu);
					}
				}
			}
		}
		showMenus.sort(new MenuComparator());
		model.addAttribute("userMenu",showMenus);
		return "main";
	}

	/**
	 *  空地址请求
	 * @return
	 */
	@GetMapping(value = "")
	public String index() {
		LOGGER.info("这事空地址在请求路径");
		Subject s = SecurityUtils.getSubject();
		return s.isAuthenticated() ? "redirect:index" : "login";
	}

	@GetMapping("systemLogout")
	@SysLog("退出系统")
	public String logOut(){
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}
}

class MenuComparator implements Comparator<net.vmyun.entity.Menu>{

	@Override
	public int compare(net.vmyun.entity.Menu o1, net.vmyun.entity.Menu o2) {
		if(o1.getSort()>o2.getSort()){
			return -1;
		}else {
			return 0;
		}

	}
}