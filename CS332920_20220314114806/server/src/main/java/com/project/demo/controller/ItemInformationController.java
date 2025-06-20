package com.project.demo.controller;

import com.project.demo.entity.ItemInformation;
import com.project.demo.service.ItemInformationService;
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
 *物品信息：(ItemInformation)表控制层
 *
 */
@RestController
@RequestMapping("/item_information")
public class ItemInformationController extends BaseController<ItemInformation,ItemInformationService> {

    /**
     *物品信息对象
     */
    @Autowired
    public ItemInformationController(ItemInformationService service) {
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
