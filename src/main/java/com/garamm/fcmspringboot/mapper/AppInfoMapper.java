package com.garamm.fcmspringboot.mapper;

import com.garamm.fcmspringboot.appinfo.AppInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public class AppInfoMapper {

    @Autowired
    SqlSession sqlSession;

    public List<AppInfo> getAppList(){

        sqlSession.selectOne("time.getTime");

        return null;
    }

}
