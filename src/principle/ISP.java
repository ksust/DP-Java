package principle;

/**
 * Created by yugao on 2018/2/26.
 * 七大原则
 * 接口隔离原则 Interface Segregation Principle，ISP
 * 描述：客户端（调用接口的类）不应该依赖它不需要的接口（类间的依赖关系应该建立在最小依赖上）
 * 作用：值提供调用者需要的方法，屏蔽不需要的方法，防止接口污染
 * Java：在Java中对应一个实现类实现多个接口类
 * 样例：使用多个接口类，由一个实现类实现，再由各个接口类引用示例以隔离接口。以管理员、普通用户视角接口
 */
public class ISP {

    public static void main(String []args){
        AdminFunc adminFunc=FuncImpl.getAdminFuncInstance();
        UserFunc userFunc=FuncImpl.getUserFuncInstance();
        adminFunc.changeUserHeadImage("998");
        adminFunc.changeUserHeadImage("997");
        adminFunc.changeUserPassword("999");
        adminFunc.changeUserPassword("996");
        userFunc.changeMyHeadImage();
        userFunc.changeMyPassword();
    }
}

/**
 * 管理员具有的接口方法
 */
interface AdminFunc{
    void changeUserPassword(String username);
    void changeUserHeadImage(String username);
}

/**
 * 普通用户具有的接口方法
 */
interface UserFunc{
    void changeMyHeadImage();
    void changeMyPassword();
}

class FuncImpl implements AdminFunc,UserFunc{

    /**
     * 接口隔离调用：管理员接口
     * @return 隔离接口，从大到小
     */
    public static AdminFunc getAdminFuncInstance(){
        return new FuncImpl();
    }

    /**
     * 接口隔离调用：用户接口
     * @return 隔离接口，从大到小
     */
    public static UserFunc getUserFuncInstance(){
        return new FuncImpl();
    }
    @Override
    public void changeUserPassword(String username) {
        System.out.println("管理员改变用户密码："+username);
    }

    @Override
    public void changeUserHeadImage(String username) {
        System.out.println("管理员改变用户头像："+username);
    }

    @Override
    public void changeMyHeadImage() {
        System.out.println("用户改变自己的头像");
    }

    @Override
    public void changeMyPassword() {
        System.out.println("用户改变自己的密码");
    }
}