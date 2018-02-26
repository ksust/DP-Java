package principle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yugao on 2018/2/26.
 * 六大原则
 * 里式替换原则：Liskov Substitution Principle
 * 概述：父类能出现的地方子类就可以出现，即将父类替换成子类不会有影响（反过来不成立）
 * Java：该原则简单实现类似OO中的多态，即使用父类抽象方法做统一接口，使用父类引用子类对象
 * 注：体现里式替换原则的设计模式有：策略模式、组合模式、代理模式
 * 样例：模拟菜单切换来体现里式替换原则，每页的菜单是一个selector类，启动该页方法为run，关闭（挂起）为stop。
 * 要求切换执行后只能存在一个selector处于开启状态
 */

public class LSP {
    private static List<SelectorBase> selector=new ArrayList<>();
    //静态初始化
    static {
        selector.add(new SelectorMain());
        selector.add(new SelectorCenter());
    }
    public static void main(String []args){
        LSP lsp=new LSP();
        lsp.changeSelector(SelectorType.MAIN);
        lsp.changeSelector(SelectorType.CENTER);
    }

    /**
     * 切换菜单：传入枚举类型
     * @param type SelectorType
     */
    private void changeSelector(SelectorType type){
        //停止所有、启动
        for (SelectorBase selectorGet:selector) {
            if (selectorGet.getSelectorType()==type){
                selectorGet.run();
            }
            else{
                selectorGet.stop();
            }
            selectorGet.test();
        }
    }
}

/**
 * 枚举菜单名
 */
enum SelectorType{
    MAIN,CENTER
}

/**
 * 抽象类，用作统一接口。不可直接被实例化（接口类亦不可被实例化）
 * 模拟菜单选项卡基类，切换菜单时调用run、stop方法
 */
abstract class SelectorBase{
    //抽象方法
    public abstract void run();
    public abstract void stop();
    public abstract SelectorType getSelectorType();
    //普通不可重载方法，默认包访问权限
    final void test(){
        System.out.println("test->this is abstract class final method");
    }
}

/**
 * 模拟菜单切换时首页选项卡类
 */
class SelectorMain extends SelectorBase{
    @Override
    public void run() {
        System.out.println("Selector Main has started");
    }

    @Override
    public void stop() {
        System.out.println("Selector Main has stopped");
    }

    @Override
    public SelectorType getSelectorType() {
        return SelectorType.MAIN;
    }
}

/**
 * 用户中心选项卡类
 */
class SelectorCenter extends SelectorBase{
    @Override
    public void run() {
        System.out.println("Selector Center has started");
    }

    @Override
    public void stop() {
        System.out.println("Selector Center has stopped");
    }

    @Override
    public SelectorType getSelectorType() {
        return SelectorType.CENTER;
    }
}