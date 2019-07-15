package factory.easy;

import factory.IUserOperation;
import factory.MysqlUser;
import factory.OracleUser;

public class EasyFactory {

    // 数据库名称
    // private static String db="MySQL";
     private static String db="Oracle";

    public static IUserOperation createUser(){

        IUserOperation user=null;
        switch (db){
            case "MySQL":
                user=new MysqlUser();
                break;

            case "Oracle":
                user=new OracleUser();
                break;
        }
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
