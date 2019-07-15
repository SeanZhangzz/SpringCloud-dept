package factory.easy;

import factory.IUserOperation;
import factory.MysqlUser;
import factory.OracleUser;

public class ReflectFactory {

    // 数据库名称
    // private static String db="";
     private static String db="Oracle";

    public static IUserOperation createUser() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        IUserOperation user=null;
        String className = "factory." + db + "User";
        user = (IUserOperation)Class.forName(className).newInstance();
        return user;
    }

   /* public static ILogin createLogin(){

        ILogin login=null;
        switch (db){
            case "MySQL":
                login=new MysqlLogin();
                break;

            case "Oracle":
                login=new OracleLogin();
                break;
        }
        return login;
    }*/

}
