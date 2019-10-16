package cn.itcast.user.dao;

import cn.itcast.user.domain.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;

/*
* 数据类
* UserDao的某一个实现类
* */
public class UserDaoImpl implements UserDao{
    private String path = "D:/users.xml";
    /**
     * 按照用户名查询
     * */
    public User findByUserName(String username){
        /*
        * 1.得到Document
        * 2.Xpath查询
        *   3.校验查询结果是否为null，若为null，返回null
        *   4.如果不为null，需要把Element封装到User对象中。
        * */
        /* 创建解析器*/
        SAXReader saxReader = new SAXReader();
        //得到document
        try {
            Document document = saxReader.read(path);
            /*通过xpath查询得到Element*/
            Element ele = (Element)document.selectSingleNode("//user[@username='" + username + "']");
            /*校验ele是否为null,if null,return null*/
            if(ele == null) return null;
            /*把ele的数据封装到User对象中*/
            User user = new User();
            String attrUsername = ele.attributeValue("username");
            String attrPassword = ele.attributeValue("password");
            user.setUsername(attrUsername);
            user.setPassword(attrPassword);

            return user;
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加用户
     * */
    public void addUser(User user){
        /**
         * 1.得到Document
         * 2.通过Document得到root元素！ <users>
         * 3.使用参数User ,转化为Element元素
         * 4.把element添加到root元素中
         * 5.保存Document
         * */
        /*得到Document*/
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(path);
            /*得到根元素*/
            Element rootElement = document.getRootElement();
            //通过根元素创建新元素
            //Element userele = rootElement.element("user");
            Element userele = rootElement.addElement("user");
            /*为userele设置属性*/
            userele.addAttribute("username",user.getUsername());
            userele.addAttribute("password",user.getPassword());
            /*
            * 保存文档
            * */
            //创建输出格式化器
            OutputFormat format = new OutputFormat("\t", true);//缩进使用\t,是否换行，使用是
            format.setTrimText(true);//清空原有的换行和缩进
            /*
            * 使用XMLWriter保存文件
            * */
            try {
                XMLWriter xmlWriter = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path),"utf-8"),format);
                xmlWriter.write(document);
                xmlWriter.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }catch (DocumentException e){
            throw new RuntimeException(e);
        }

    }

}
