package com.garamm.fcmspringboot.common;

import lombok.AllArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
public class Result {

    int code;
    String msg;
    Object data;

    public HashMap getResultHash(Result result) {
        HashMap map = new HashMap();
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
        return map;
    }

}
