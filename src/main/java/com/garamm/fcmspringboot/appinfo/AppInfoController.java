package com.garamm.fcmspringboot.appinfo;

import com.garamm.fcmspringboot.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/appinfo")
@Controller
public class AppInfoController {

    @Autowired
    AppInfoService service;

    @GetMapping("/list")
    @ResponseBody
    private HashMap getList() {
        Result result;
        try {
            List<AppInfo> appInfoList = service.getAppList();
            result = new Result(200, "성공", appInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(500, "에러", null);
        }
        return result.getResultHash(result);
    }

    @GetMapping("/test")
    private void test() {
        System.out.println("ttt");
    }
}
