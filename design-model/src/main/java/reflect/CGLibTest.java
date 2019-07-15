package reflect;


import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;

public class CGLibTest {

    public static void main(String[] args) {

        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //设置将cglib生成的代理类字节码生成到指定位置
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:/Temp/code/cglib");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SeanDao.class);

        Callback callback =  new SeanCallback();
        enhancer.setCallback(callback);


        SeanDao seanCGProxy = (SeanDao)enhancer.create();

        System.out.println(seanCGProxy.query());


    }
}
