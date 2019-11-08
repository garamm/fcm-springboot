package com.garamm.fcmspringboot.appinfo;


import com.garamm.fcmspringboot.mapper.AppInfoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppInfoServiceImpl implements AppInfoService {

    @Mapper
    AppInfoMapper mapper;

    @Override
    public List<AppInfo> getAppList() {
        return mapper.getAppList();
    }
}
