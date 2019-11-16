package com.garamm.fcmspringboot.appinfo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface AppInfoService {

    public List<AppInfo> getAppList();

    public int bulkInsert();
}