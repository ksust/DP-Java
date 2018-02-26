package principle;

/**
 * Created by yugao on 2018/2/26.
 * 六大原则
 * 迪米特法则（最少知识原则） Law of Demeter,LOD
 * 描述：只与你直接的朋友们通信（减少依赖，通过第三者转发调用，而不是直接调用不密切相关的）
 * 作用：类之间的解耦、若耦合，提高类的复用率
 * Java：即在OOP过程中考虑扩展（修改），使得需求变更时尽量不修改源代码或极少量修改
 * 样例：通过中间类实例（第三者）调用不密切相关的类实例的方法。即通过朋友访问陌生人
 */
public class LOD {
    public static void main(String []args){
        Friend friend=new Friend();
        friend.callMe();
        friend.callStranger();
    }
}


class Friend{
    private Stranger stranger=new Stranger();
    /**
     * 访问陌生人的方法：通过朋友间接访问
     */
    public void callStranger(){
        stranger.callMe();
    }
    public void callMe(){
        System.out.println("Call me ,my friend");
    }
}

class Stranger{
    public void callMe(){
        System.out.println("Call me,stranger");
    }
}
