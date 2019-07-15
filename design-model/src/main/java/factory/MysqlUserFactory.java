package factory;

public class MysqlUserFactory implements IFactory {
    @Override
    public IUserOperation getUserOperation() {
        return new MysqlUser();
    }
    @Override
    public IProductOperation getProductOperation() {
        return new SnakeOperation();
    }
}
