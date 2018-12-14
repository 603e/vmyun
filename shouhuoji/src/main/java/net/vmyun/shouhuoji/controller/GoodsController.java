package net.vmyun.shouhuoji.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import net.vmyun.annotation.SysLog;
import net.vmyun.shouhuoji.base.BaseController;
import net.vmyun.shouhuoji.entity.Goods;
import net.vmyun.entity.QuartzTask;
import net.vmyun.entity.User;
import net.vmyun.util.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/9/15.
 */
@Controller
@RequestMapping("vem/goods")
public class GoodsController  extends BaseController {

    @GetMapping("list")
    public String goods(Model model){
        //Map map = userService.selectUserMenuCount();
        EntityWrapper<Goods> wrapper = new EntityWrapper<>();
        List<Goods> goods = goodsService.selectList(wrapper);
        model.addAttribute("goods",goods);
        return "goods";
    }
}
