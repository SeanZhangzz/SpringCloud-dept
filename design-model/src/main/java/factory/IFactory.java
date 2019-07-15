package factory;

public interface IFactory {
    IUserOperation getUserOperation();

    IProductOperation getProductOperation();
}
