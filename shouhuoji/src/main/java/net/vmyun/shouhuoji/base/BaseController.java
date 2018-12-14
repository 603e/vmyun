package net.vmyun.shouhuoji.base;

import net.vmyun.entity.User;
import net.vmyun.shouhuoji.config.VemConfig;
import net.vmyun.shouhuoji.service.*;
import net.vmyun.shouhuoji.util.ConstUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {

	private String userId;
	public User getCurrentUser() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		//从session里面获取对应的值
		User loginUser = (User) requestAttributes.getAttribute(ConstUtil.SESSION_CURRENT_USER, RequestAttributes.SCOPE_SESSION);
		return loginUser;
	}

	public void setCurrentUser(User loginUser) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		requestAttributes.setAttribute(ConstUtil.SESSION_CURRENT_USER,loginUser, RequestAttributes.SCOPE_SESSION);

	}

	@Autowired
	protected VemConfig vemConfig;
	@Autowired
	protected UserService userService;

	@Autowired
	protected MenuService menuService;

	@Autowired
	protected RoleService roleService;

	@Autowired
	protected LogService logService;

	@Autowired
	protected GoodsService goodsService;
}
