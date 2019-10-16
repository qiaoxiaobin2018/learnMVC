package cn.itcast.user.dao;

import cn.itcast.user.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDaoImpl implements UserDao {
    @Override
    public User findByUserName(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            /*
             * 一、得到连接
             * */
            connection = JdbcUtils.getConnetion();
            /*
             * 二、准备SQL模板
             * */
            String sql = "SELECT * FROM t_user WHERE username=?";
            preparedStatement = connection.prepareStatement(sql);
            /*
             * 三、为preparedStatement赋值
             * */
            preparedStatement.setString(1,username);
            /*
             * 四、执行
             * */
            resultSet = preparedStatement.executeQuery();
            /*
            * 五、把RessultSet转化为User类型，返回
            * */
            if(resultSet == null){
                return null;
            }else if(resultSet.next()){//返回bool类型
                //转换为User对象，并返回
                //ORM --> 对象关系映射（hibernate!）
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setAge(resultSet.getString("age"));
                user.setGender(resultSet.getString("gender"));
                return user;
            }
            else{
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            /*
            * 一、得到连接
            * */
            connection = JdbcUtils.getConnetion();
            /*
            * 二、准备SQL模板
            * */
            String sql = "INSERT INTO t_user VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            /*
            * 三、为preparedStatement赋值
            * */
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setInt(3,Integer.parseInt(user.getAge()));
            preparedStatement.setString(4,user.getGender());

            /*
            * 四、执行
            * */
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
