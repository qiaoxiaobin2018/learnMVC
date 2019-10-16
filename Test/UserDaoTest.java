import cn.itcast.user.dao.UserDaoImpl;
import cn.itcast.user.domain.User;
import org.junit.jupiter.api.Test;

/**
 * UserDao的测试
 * */
public class UserDaoTest {
    @Test
    public void testFindByUsername(){
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.findByUserName("乔晓斌");
        System.out.println(user);

    }

    @Test
    public void testAddUser(){
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User();
        user.setUsername("乔晓斌");
        user.setPassword("1q2w3e4r");

        userDao.addUser(user);
    }

}
