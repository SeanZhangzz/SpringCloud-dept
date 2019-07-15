package factory;

public class TestMain {

    public static void main(String[] args) {
        //MysqlUserFactory factory = new MysqlUserFactory();
        IFactory factory = new OracleUserFactory();
        IUserOperation mysqlUser = factory.getUserOperation();
        IProductOperation productOperation = factory.getProductOperation();
        mysqlUser.insert(new User());
        mysqlUser.getUser(5);
        productOperation.insert(new User());
    }
}
