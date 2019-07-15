package factory;

public class OracleUserFactory implements IFactory {
    @Override
    public IUserOperation getUserOperation() {
        return new OracleUser();
    }

    @Override
    public IProductOperation getProductOperation() {
        return new SnakeOperation();
    }
}
