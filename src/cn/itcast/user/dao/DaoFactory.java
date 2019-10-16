package cn.itcast.user.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
    private static Properties properties = null;
    static{
        //加载配置文件内容到properties中
        InputStream inputStream = DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
        properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static UserDao getUserDao(){
        /*
        * 给出一个配置文件 ，文件中给出UserDao接口的实现类名称
        * 获取实现类的类名，通过反射完成创建对象！
        * */
        /*
        * 得到Dao实现类的名称
        * */
        String daoClassName = properties.getProperty("cn.itcast.user.dao.UserDao");
        /*
        * 通过反射来创建实现类的对象
        * */
        try {
            Class aClass = Class.forName(daoClassName);
            return  (UserDao) aClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
