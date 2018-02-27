package principle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yugao on 2018/2/26.
 * 六大原则
 * 开闭原则 Open-Closed Principle,OCP
 * 描述：一个软件实体应当对扩展开放，对修改关闭
 * 作用：是OOP最基础的原则，起到总指导作用，其他原则为OCP的具体形态
 * Java：即在OOP过程中考虑扩展（修改），使得需求变更时尽量不修改源代码或极少量修改
 * 样例：使用Java示例一个图书销售模块，遇所有图书需要9折的扩展的简单例子
 */
public class OCP {
    //发生修改时只需要修改类名principle.OffNovelBook（可在配置文件中配置类名）
    public final static String BookClassName="principle.NovelBook";//principle.OffNovelBook
    public static void main(String []args){
        BookStore bookStore=new BookStore();
        bookStore.showBooks();
    }
}

/**
 * 图书销售类：相当于主逻辑
 */
class BookStore{
    private List<IBook> bookList=new ArrayList<>();
    //实例初始化，演示数据载入
    {
        //当需要新增售卖规则时不修改原程序而使用扩展方式，这里使用反射类，类名可通过配置文件配置
        try {
            try {
                Constructor bookClassConstructor = Class.forName(OCP.BookClassName).getConstructor(String.class, double.class);
                try {
                    bookList.add((IBook) bookClassConstructor.newInstance("西游记", 88.8));
                    bookList.add((IBook) bookClassConstructor.newInstance("西游记续集", 88.9));
                    bookList.add((IBook) bookClassConstructor.newInstance("三国演义", 89.8));
                    bookList.add((IBook) bookClassConstructor.newInstance("水浒传", 89.9));
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 展示书籍售卖价格等信息
     */
    public void showBooks(){
        System.out.println("------------书籍售卖列表-------------");
        System.out.println("书名     价格");
        for(IBook book:bookList){
            System.out.println(book.getName()+"   "+book.getPrice());
        }
    }
}

/**
 * 图书接口类
 */
interface IBook{
    String getName();
    double getPrice();
}

/**
 * 实现类：小说书籍
 */
class NovelBook implements IBook{

    private String name;
    private double price;

    public NovelBook(String name,double price){
        this.name=name;
        this.price=price;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

/**
 * 需求变更后的书（价格9折）：用于扩展，不修改原书类程序
 */
class OffNovelBook extends NovelBook{

    public OffNovelBook(String name, double price) {
        super(name, price);
    }
    @Override
    public double getPrice(){
        return super.getPrice()*0.9;
    }
}