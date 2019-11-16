---
# FCM 관리자

### DB 구조

AppInfo
column | type | memo
----- | ----- | -----
appCode | int | auto_increment
appName | varchar(20) | 앱 이름
os | varchar(20) | OS 구분자(android/ios)
firebaseAuth | varchar(300) | firebase auth key
memo | varchar(100) | 메모
```mysql
create table AppInfo(
    appCode int auto_increment not null,
    appName varchar(20) not null,
    os varchar(20) not null,
    firebaseAuth varchar(300) not null,
    memo varchar(100),
    primary key(appCode) 
);
```

Topic
column | type | memo
----- | ----- | -----
topicCode | int | auto_increment
appCode | int | 앱 코드
topic | varchar(20) | 토픽명
memo | varchar(100) | 메모
```mysql
create table Topic(
    topicCode int auto_increment not null,
    appCode int not null,
    topic varchar(20) not null,
    memo varchar(100),
    primary key(topicCode) 
);
```

UserInfo
column | type | memo
----- | ----- | -----
userCode | int | auto_increment
userName | varchar(10) | 사용자명
userPhone | varchar(20) | 사용자 연락처
userOS | varchar(10) | 사용자 OS(android/ios)
fcmToken | varchar(300) | FCM Token
memo | varchar(100) | 메모
```mysql
create table UserInfo(
    userCode int auto_increment not null,
    userName varchar(10) not null,
    userPhone varchar(10) not null,
    userOS varchar(10) not null,
    fcmToken varchar(300) not null,
    memo varchar(100),
    primary key(userCode) 
);
```

PushLog
column | type | memo
----- | ----- | -----
logCode | int | auto_increment
title | varchar(30) | 푸쉬 제목
msg | varchar(100) | 푸쉬 내용
imgPath | varchar(200) | 이미지 경로
targetType | varchar(10) | 수신자 타입(individual/topic)
target | varchar(100) | 수신 대상자(individual:userPhone/topic:topic)
time | timestamp | CURRENT_TIMESTAMP
```mysql
create table PushLog(
    logCode int auto_increment not null,
    title varchar(30) not null,
    msg varchar(100) not null,
    imgPath varchar(200) not null,
    targetType varchar(10) not null,
    target varchar(100) not null,
    time timestamp not null,
    primary key(logCode) 
);
```