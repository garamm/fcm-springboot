package com.garamm.fcmspringboot.appinfo;


import com.garamm.fcmspringboot.mapper.AppInfoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppInfoServiceImpl implements AppInfoService {

    @Autowired
    SqlSession mapper;

    @Override
    public List<AppInfo> getAppList() {
        return mapper.selectList("time.getTime");
    }

    @Override
    public int bulkInsert() {
        Map map = new HashMap();
        List list = new ArrayList();

        for(int i = 0; i < 10; i++){
            list.add(i);
        }

        map.put("list",list);
        System.out.println("ddddddddd");
        return mapper.insert("time.bulkInsert",map);
    }
}
