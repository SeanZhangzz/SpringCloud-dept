package reflect;

public class DasiyDao implements UserDao {
    @Override
    public int query() {
        System.out.println("hello daisy");
        return 1;
    }

    @Override
    public int insert() {
        System.out.println("daisy insert data");

        return 1;
    }
}
