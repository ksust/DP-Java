package principle;

/**
 * Created by yugao on 2021/6/5.
 * 七大原则
 * 合成复用原则 Composite Reuse Principle，CRP
 * 概述：尽量使用对象组合，而不是继承来达到复用的目的。(多用组合，少用继承)
 * 作用：组合/聚合可以使系统更加灵活，类与类之间的耦合度降低，一个类的变化对其他类造成的影响相对较少。
 * Java：Java Web分层思想中，DAO层的数据库连接可配置多种方式(例如JDBC或连接池)，获取连接可使用组合。
 * 样例：模拟DAO持有不同类型的数据库连接，均组合DBUtil获取数据库连接
 */
public class CRP {
    public static void main(String[] args) {
        DBUtil db1 = new DBUtil();
        NewDBUtil db2 = new NewDBUtil();
        //DAO
        StudentDAO studentDAO = new StudentDAO(db1);
        TeacherDAO teacherDAO = new TeacherDAO(db2);
        studentDAO.getStudentCount();
        teacherDAO.getTeacherCount();
    }

}

class DBUtil {
    public int getConnection() {
        System.out.println("getConnection from 0");
        return 0;
    }

}

/**
 * 继承DBUtil，新的获取数据库连接方式
 */
class NewDBUtil extends DBUtil {
    @Override
    public int getConnection() {
        System.out.println("getConnection from 1");
        return 1;
    }
}

/**
 * 需要获取DB Connection的DAO。这里还可向上抽象DAO的基类，复用DBUtil的操作方法。
 */
class StudentDAO {
    private DBUtil dbOperator;

    public StudentDAO(DBUtil dbOperator) {
        setDBOperator(dbOperator);
    }

    public void setDBOperator(DBUtil dbOperator) {
        this.dbOperator = dbOperator;
    }

    public int getStudentCount() {
        dbOperator.getConnection();
        System.out.println("getStudentCount");
        return 10;
    }

}

class TeacherDAO {
    private DBUtil dbOperator;

    public TeacherDAO(DBUtil dbOperator) {
        setDBOperator(dbOperator);
    }

    public void setDBOperator(DBUtil dbOperator) {
        this.dbOperator = dbOperator;
    }

    public int getTeacherCount() {
        dbOperator.getConnection();
        System.out.println("getTeacherCount");
        return 10;
    }
}