package com.project.demo.controller;

import com.project.demo.entity.PurchaseCenter;
import com.project.demo.service.PurchaseCenterService;
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
 *求购中心：(PurchaseCenter)表控制层
 *
 */
@RestController
@RequestMapping("/purchase_center")
public class PurchaseCenterController extends BaseController<PurchaseCenter,PurchaseCenterService> {

    /**
     *求购中心对象
     */
    @Autowired
    public PurchaseCenterController(PurchaseCenterService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
