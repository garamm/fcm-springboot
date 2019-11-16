package com.garamm.fcmspringboot.pushlog;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Data
@Component
public class PushLog {
    int logCode;
    String title;
    String msg;
    String imgPath;
    String targetType;
    String target;
    Timestamp time;
}
