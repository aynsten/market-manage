package cn.lmjia.market.core.controller.main.order;

import cn.lmjia.market.core.entity.Login;
import cn.lmjia.market.core.entity.MainOrder;
import cn.lmjia.market.core.entity.support.Address;
import cn.lmjia.market.core.repository.MainGoodRepository;
import cn.lmjia.market.core.service.LoginService;
import cn.lmjia.market.core.service.MainOrderService;
import me.jiangcai.wx.model.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

/**
 * @author CJ
 */
public abstract class AbstractMainOrderController {
    @Autowired
    private MainGoodRepository mainGoodRepository;
    @Autowired
    private MainOrderService mainOrderService;
    @Autowired
    private LoginService loginService;

    /**
     * 展示下单页面
     *
     * @param login 当前身份
     * @param model model
     */
    protected void orderIndex(Login login, Model model) {
        model.addAttribute("goodList", mainGoodRepository.findByEnableTrue());
    }

    protected MainOrder newOrder(Login login, Model model, long recommendId, String name, int age, Gender gender
            , Address address, String mobile, long goodId, int amount, String mortgageIdentifier) {
        return mainOrderService.newOrder(login, loginService.get(recommendId), name, mobile, age, gender, address
                , mainGoodRepository.getOne(goodId), amount, mortgageIdentifier);
    }
}
