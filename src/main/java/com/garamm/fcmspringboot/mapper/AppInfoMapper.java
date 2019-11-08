package com.garamm.fcmspringboot.mapper;

import com.garamm.fcmspringboot.appinfo.AppInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface AppInfoMapper {


    @Select("SELECT * FROM AppInfo")
    public List<AppInfo> getAppList();

}
