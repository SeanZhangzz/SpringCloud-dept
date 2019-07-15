package reflect;

import org.springframework.cglib.core.DebuggingClassWriter;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDKProxyTest {


    public static void main(String[] args) {

//        byte[] bytes = ProxyGenerator.generateProxyClass("sean.class", new Class[]{UserDao.class});
//        String ss = "a" + "b";
//        String s = "a";
//        String s2 = "b";
//        String s3 = s + s2;
//        System.out.println(s + s2);
//
//        try {
//            FileOutputStream fos = new FileOutputStream("c://sean.class");
//            fos.write(bytes);
//            fos.flush();
//            fos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:/Temp/code/cglib");
        UserDao dasiyDao = new DasiyDao();
        InvocationHandler invocationHandler = new MyInvocationHandler(dasiyDao);
        UserDao dao = (UserDao)Proxy.newProxyInstance(dasiyDao.getClass().getClassLoader(), dasiyDao.getClass().getInterfaces(), invocationHandler);
        dao.insert();


    }
}
