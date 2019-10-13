package cn.itcast.user.service;

/*
* 自定义一个异常类
*   只是给出父类的构造器即可，方便用来创建对象
*   同一个异常类的实例之间的区别在于“异常信息的不同”
* */
public class UserException extends Exception {
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }
}
