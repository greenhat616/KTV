package me.a632079.ktv.models;

import me.a632079.ktv.Application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 数据库配置
 * Referer: https://www.jianshu.com/p/c9db5979e13e
 */
public class DBConfig {

    private String driver;
    private String url;
    private String userName;
    private String password;

    public DBConfig() {
        InputStream inputStream = Application.class.getClassLoader().getResourceAsStream("./dbConfig.properties");
        Properties p = new Properties();
        try {
            p.load(inputStream);
            this.driver = p.getProperty("driver");
            this.url = p.getProperty("url");
            this.userName = p.getProperty("username");
            this.password = p.getProperty("password");
            // System.out.println(driver);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}