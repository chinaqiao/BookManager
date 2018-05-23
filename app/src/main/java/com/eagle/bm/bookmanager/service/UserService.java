package com.eagle.bm.bookmanager.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eagle.bm.bookmanager.common.Net;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 用户相关服务
 * 单例模式
 * @author Eagle
 */
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    private static UserService getInstance(){
        return UserServiceSingleHolder.instance;
    }
    private static class UserServiceSingleHolder {
        private static final UserService instance = new UserService();
    }

    /**
     * 用户登陆
     * 同步方法，需要异步调用
     * 登陆成功后记录数据到本地缓存
     * @param uname 用户名
     * @param pwd 密码
     * @return 返回登陆成功还是失败
     */
    public boolean login(String uname, String pwd) {
        logger.info("Login :" + uname);

        Map<String, String> json = new HashMap<>();
        json.put("uname", uname);
        json.put("pwd", pwd);
        String resp = "";
        try {
            resp = Net.post("https://admin.cncar.net/auth/1/admin/auth", JSON.toJSONString(json));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject respObj = JSON.parseObject(resp);
        String code = respObj.getString("code");

        return "-1".equals(code);
    }

    /**
     * 通过本地用户信息和token，刷新服务端token
     * 返回true，则数据更新成功
     * @return 如果服务端token过期，false
     */
    public boolean refresh() {
        // TODO 调用服务端刷新接口
        return false;
    }

    /**
     * 退出
     */
    private void logout() {
        // TODO 清除本地缓存
    }
}
