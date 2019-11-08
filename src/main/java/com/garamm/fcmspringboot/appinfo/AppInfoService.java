package com.garamm.fcmspringboot.appinfo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppInfoService {

    public List<AppInfo> getAppList();
}