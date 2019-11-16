package com.garamm.fcmspringboot.appinfo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class AppInfo {

    private int appCode;
    private String appName;
    private String memo;
    private String os;
    private String firebaseAuth;

}
