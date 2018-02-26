package principle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yugao on 2018/2/26.
 * 六大原则
 * 单一职责原则 Single Responsibility Principle
 * 概述：一个类应当只有一个引起它变化的原因，即在定义对象职责时需要考虑职责与对象间是否真正具有所属关系
 * Java：如Java Web中分层的思想（如service、dal、controller层等）就体现单一职责
 * 作用：类的职责越少，对象间的依赖关系就越少，耦合度减弱，才能保证对象的高内聚
 * 样例：模拟Java Web中数据操纵，涉及实体类（entity）、数据连接对象类（dao）、业务类（service）
 */
public class SRP {
    static List<User> userList=new ArrayList<>();//用于测试的数据
    static {
        userList.add(new User("张3","9991"));
        userList.add(new User("张4","9992"));
        userList.add(new User("张5","9993"));
        userList.add(new User("张6","9994"));
    }

    public static void main(String []args){
        //测试
        UserService userService=new UserService();
        //列出所有用户
        for(User user:userService.getAllUserList()){
            System.out.println("name->"+user.getName()+",password->"+user.getPassword());
        }
        //测试登陆
        userService.checkUserLogin("张3","9991");
        userService.checkUserLogin("张3","9992");
    }

}

/**
 * 数据业务层：模拟数据业务操作，用于外部访问。与User依赖关系，UserDao关联关系
 */
class UserService{
    private UserDao userDao=new UserDao();//关联关系

    /**
     * 判断用户登陆
     * @param name 用户名
     * @param password 用户密码
     * @return 验证成功返回true，失败返回false
     */
    public boolean checkUserLogin(String name,String password){
        User user=userDao.getUserByName(name);
        if (null==user){
            System.out.println("不存在该用户");
            return false;
        }
        else if(!user.getPassword().equals(password)){
            System.out.println("密码错误");
            return false;
        }
        System.out.println("登录成功");
        return true;
    }

    /**
     * 获取所有用户列表
     * @return 用户列表
     */
    public List<User> getAllUserList(){
        return userDao.getAllUserList();
    }
}

/**
 * 数据连接对象：模拟操作User类型数据。与User依赖关系
 */
class UserDao{
    /**
     * 获取所有用户。
     * @return 用户对象列表。为空就返回空的List
     */
    public List<User> getAllUserList(){
        return SRP.userList;
    }

    /**
     * 通过用户名搜索用户
     * @param name 用户名
     * @return 用户对象|null
     */
    public User getUserByName(String name){
        for (User user:SRP.userList){
            if (user.getName().equals(name))
                return user;
        }
        return null;
    }
}

/**
 * 实体类：用户
 */
class User{
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    User(String name,String password){
        this.name=name;
        this.password=password;
    }
}
