package com.garamm.fcmspringboot;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@RestController
public class FcmController {

    String serverKey = "";

    @PostMapping("/send")
    public HashMap<String, Object> send(@RequestParam("token") String token, // 개별토큰 혹은 토픽명(20자 미만)
                                        @RequestParam("title") String title,
                                        @RequestParam("body") String body) {
        try {
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title", title);
            notificationObj.put("body", body);
            JSONObject sendJson = new JSONObject();
            sendJson.put("priority", "high");
            sendJson.put("notification", notificationObj);
            sendJson.put("data", notificationObj);
            sendJson.put("to", token);
            //sendJson.put("timestamp", new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"));
            boolean isSingle = true;
            if (token.length() < 20) { // 입력받은 token이 20자 미만이면 토픽으로 간주하고 message_id를 리턴하지 않음
                isSingle = false;
                sendJson.put("to", "/topics/" + token);
            } else {
                sendJson.put("to", token);
            }

            String input = sendJson.toString();
            return sendPush(input, isSingle);
        } catch (Exception e) {
            return makeResultMap(400, "전송실패1 : " + e.toString(), null);
        }
    }

    @PostMapping("/send/img")
    public HashMap<String, Object> sendImg(@RequestParam("token") String token, // 개별토큰 혹은 토픽명(20자 미만)
                                           @RequestParam("title") String title,
                                           @RequestParam("body") String body,
                                           @RequestParam("image") String image) {
        try {
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title", title);
            notificationObj.put("body", body);
            notificationObj.put("sound", "default");
            notificationObj.put("badge", "1");
            notificationObj.put("image", image);

            JSONObject sendJson = new JSONObject();
            sendJson.put("priority", "high");
            sendJson.put("notification", notificationObj);
            sendJson.put("data", notificationObj);
            sendJson.put("content_available", true);
            sendJson.put("mutable_content", true);

            //sendJson.put("timestamp", new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"));
            boolean isSingle = true;
            if (token.length() < 20) { // 입력받은 token이 20자 미만이면 토픽으로 간주하고 message_id를 리턴하지 않음
                isSingle = false;
                sendJson.put("to", "/topics/" + token);
            } else {
                sendJson.put("to", token);
            }
            String input = sendJson.toString();
            return sendPush(input, isSingle);
        } catch (Exception e) {
            return makeResultMap(400, "전송실패1: " + e.toString(), null);
        }
    }

    private HashMap<String, Object> sendPush(String input, boolean isSingle) {

        try {
            URL url = new URL("https://fcm.googleapis.com/fcm/send");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "key=" + serverKey);

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes("UTF-8"));
            os.flush();
            os.close();

            if (conn.getResponseCode() != 200) {
                return makeResultMap(400, "전송실패2 : " + conn.getResponseMessage(), null);
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                HashMap data = new HashMap();
                if (isSingle) {
//                    String res = response.toString();
//                    JSONObject object = new JSONObject(res);
//                    JSONArray results = object.getJSONArray("results");
//                    JSONObject messageObj = (JSONObject) results.get(0);
//                    String messageId = messageObj.getString("message_id");
//                    data.put("messageId", messageId);
                }
                return makeResultMap(200, "전송성공 ", data);
            }
        } catch (Exception e) {
            return makeResultMap(400, "전송실패3 : " + e.toString(), null);
        }
    }

    public static HashMap<String, Object> makeResultMap(int code, String msg, Object data) {
        HashMap map = new HashMap();
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
        return map;
    }

}
