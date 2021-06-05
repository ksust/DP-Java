package principle;

/**
 * Created by yugao on 2018/2/26.
 * 七大原则
 * 依赖倒置原则 Dependence Inversion Principle，DIP
 * 概述：高层模块、细节模块（实现类）都应该依赖其抽象（接口类、抽象类）
 * 作用：是实现开闭原则的重要途径，使得成员变动对项目影响极小
 * Java：抽象对应Java中的接口类或抽象类，高层模块对应调用接口类或抽象类，细节模块对应接口或抽象实现类
 * 样例：定义接口类并实现，高层类（Driver类、main函数）使用接口类做实例化引用（接口类不能被直接实例化）
 */
public class DIP {

    public static void main(String []args){
        //测试
        Car car=new BMW();
        Driver driver=new LaoDriver();
        driver.drive(car);
    }
}

/**
 * 司机抽象类
 */
interface Driver{
    void drive(Car car);//接口，默认public
}

/**
 * 实例化的司机：老司机类
 */
class LaoDriver implements Driver{

    @Override
    public void drive(Car car) {
        System.out.println("老司机即将发车！");
        car.run();
    }
}

/**
 * 车：接口类
 */
interface Car{
    void run();//接口，默认public
}

/**
 * 实例化的车：宝马，用于实例化
 */
class BMW implements Car{
    @Override
    public void run() {
        System.out.println("正在开BMW");
    }
}