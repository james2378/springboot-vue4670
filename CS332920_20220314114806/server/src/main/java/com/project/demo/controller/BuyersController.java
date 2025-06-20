package com.project.demo.controller;

import com.project.demo.entity.Buyers;
import com.project.demo.service.BuyersService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *买家：(Buyers)表控制层
 *
 */
@RestController
@RequestMapping("/buyers")
public class BuyersController extends BaseController<Buyers,BuyersService> {

    /**
     *买家对象
     */
    @Autowired
    public BuyersController(BuyersService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapbuyer_account = new HashMap<>();
        mapbuyer_account.put("buyer_account",String.valueOf(paramMap.get("buyer_account")));
        List listbuyer_account = service.select(mapbuyer_account, new HashMap<>()).getResultList();
        if (listbuyer_account.size()>0){
            return error(30000, "字段买家账号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
