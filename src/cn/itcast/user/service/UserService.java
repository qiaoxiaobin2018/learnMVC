package cn.itcast.user.service;

import cn.itcast.user.dao.DaoFactory;
import cn.itcast.user.dao.UserDao;
import cn.itcast.user.dao.UserDaoImpl;
import cn.itcast.user.domain.User;

/**
 * User的业务逻辑层
 * */
public class UserService {
    /*
    * 把具体的实现类的创建，隐藏到工厂中去
    * */
    private UserDao userDao = DaoFactory.getUserDao();

    /**
     * 注册功能
     * */
    public void Regist(User user) throws UserException{
        /*
        * 1.使用用户名去查询，如果返回null, 完成添加
        * 2.如果返回的不是null,抛出异常
        * */
        User _user = userDao.findByUserName(user.getUsername());
        if(_user != null){
            throw new UserException("用户名 "+user.getUsername()+" 已被注册！");
        }
        userDao.addUser(user);
    }
    /**
     * 登录功能
     * */
    public User login(User form) throws UserException{
        /*
        * 1.使用form 中的username进行查询，得到User user;
        * */
        User user = userDao.findByUserName(form.getUsername());
        /**
         * 2.如果user为null，说明用户不存在，抛出异常（用户不存在）
         */
        if(user == null) throw new UserException("用户名不存在！");
        /*
        * 3.比较user的password和form的password，如果不同，抛出异常（密码错误）
        * */
        if(!user.getPassword().equals(form.getPassword())){
            throw  new UserException("密码错误！");
        }
        /*
        * 4.返回数据库中查询出来的user，而不是form，因为form中只有用户名和密码，而user中有所有的用户信息
        * */
        return user;

    }
}
