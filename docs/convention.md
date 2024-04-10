## 系统约定

### 人员角色与密集

* 人员密级： secret level (sl)
  - ['非密', '内部', '一般', '重要', '核心']
  - SEC_NP("SEC_NP", "非密"),  NOBODY
  - SEC_RP("SEC_RP", "内部"),  registered person
  - SEC_GP("SEC_GP", "一般"),  general person
  - SEC_IP("SEC_IP", "重要"),  important person
  - SEC_CP("SEC_CP", "核心"),  core person

* 数据密级： confidentiality level (cl)
  - SEC_NONE("SEC_NONE", "无级别"),
  - SEC_OPEN("SEC_OPEN", "非密"),
  - SEC_AUTH("SEC_AUTH", "内部"),
  - SEC_MIN("SEC_MIN", "秘密"),
  - SEC_MID("SEC_MID", "机密"),
  - SEC_TOP("SEC_TOP", "绝密");

* 角色：
  - GUEST("guest", "访客")
  - ADMIN("administrator", "系统管理员")
  - PO("owner", "工程创建者"),
  - PM("manager", "工程经理"),
  - MEMBER("member", "工程成员")


* 用户 schema
  - 用户 JWT: [生成工具](https://tooltt.com/jwt-encode/)
    ```json
    {
      "header": {
        "alg": "HS256",
        "typ": "JWT"
      },
      "payload": {
         "subject": "tester1",
         "iat": 1679051598281,
         "expiration": 1725811200000,
         "name": "测试员1",
         "sl": "SET_GP"
      },
      "secret": "jwt@scse"
    }
    ```

    ```json
    {
      "sub": "tester1",
      "exp": 1679067710,
      "iat": 1725811200,
      "authorities": "admin, user",
      "name": "测试员1",
      "sl":"SEC_GP"
    }
    ```
    结果例：
    ```
    eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZXIxIiwiZXhwIjoxNjc5MDY3NzEwLCJpYXQiOjE3MjU4MTEyMDAsImF1dGhvcml0aWVzIjoiYWRtaW4sIHVzZXIiLCJuYW1lIjoi5rWL6K-V5ZGYMSIsInNsIjoiU0VDX0dQIn0.UDQgkKGhLSZzAg90Yc_3E8e2p5NU3sprXOuixwte_MY
    ```

> HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), "qdjwt@scse")