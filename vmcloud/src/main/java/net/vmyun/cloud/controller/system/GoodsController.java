package net.vmyun.cloud.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sun.org.apache.bcel.internal.generic.NEW;
import net.vmyun.annotation.SysLog;
import net.vmyun.cloud.base.BaseController;
import net.vmyun.cloud.entity.Goods;
import net.vmyun.cloud.entity.GoodsPassage;
import net.vmyun.cloud.service.GoodsService;
import net.vmyun.util.LayerData;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangl on 2017/12/2.
 * todo:
 */
@Controller
@RequestMapping("admin/goods")
public class GoodsController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    protected GoodsService goodsService;


    @GetMapping("list")
    @SysLog("跳转商品管理页")
    public String list(ModelMap map){
        return "admin/goods/goodsPassage";
    }

    @RequiresPermissions("sys:role:list")
    @PostMapping("list")
    @ResponseBody
    public LayerData<GoodsPassage> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                        @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                        ServletRequest request){
        LayerData<GoodsPassage> GoodsLayerData = new LayerData<>();
        EntityWrapper<GoodsPassage> GoodsEntityWrapper = new EntityWrapper<>();
        GoodsEntityWrapper.eq("vm_id",1);
        Page<GoodsPassage> goodsPassagePage = goodsService.selectPage(new Page<>(page,limit),GoodsEntityWrapper);
        GoodsLayerData.setCount(goodsPassagePage.getTotal());
        GoodsLayerData.setData(setGoods(goodsPassagePage.getRecords()));
        return GoodsLayerData;
    }

    private List<GoodsPassage> setGoods(List<GoodsPassage> GoodsPassages){
        for(GoodsPassage r : GoodsPassages){
            if(r.getGoodsId() != null && !r.getGoodsId().equals("")){
                Goods goods= goodsService.findGoodsByid(Long.parseLong(r.getGoodsId()));
                if(!StringUtils.isBlank(goods.getName())){
                   r.setGoods(goods);
                }
            }
        }
        return GoodsPassages;
    }
    @PostMapping("queryGoods")
    @ResponseBody
    public List<Goods> queryGoods(){
       List<Goods> GoodsList= goodsService.selectGoodsList();
        return GoodsList;
    }

    @PostMapping("addGoodsPassage")
    @ResponseBody
    public String addGoodsPassage( @RequestParam Map<String,Object> reqMap){
        JSONObject josnObject=(JSONObject)JSONObject.toJSON(reqMap);
        Boolean b=goodsService.addGoodsPassage(josnObject);
      return "";
    }

}
