package cn.itcast.user.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/*
* v1.0
* */
public class JdbcUtils {
    private static Properties properties = null;
    //只在JdbcUtils类被加载时执行一次！
    static {
        /*
        * 得到properties
        * */
        try{
            //加载配置文件
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
            properties = new Properties();
            properties.load(resourceAsStream);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        //加载驱动类
        try {
            Class.forName(properties.getProperty("driverClassName"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public static Connection getConnetion() throws Exception {

        /**
         * 1.加载配置文件
         * 2.加载驱动类
         * 3.调用DriverManager.getConnection()
         * */
        //得到connection
        return DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password"));
    }
}
