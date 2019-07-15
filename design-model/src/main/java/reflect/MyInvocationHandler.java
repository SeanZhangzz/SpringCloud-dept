package reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    Object object;

    public MyInvocationHandler(Object o){
        super();
        this.object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object o = null;
        if(method.getName().equals("insert")){
            System.out.println("before InvocationHandler");
            o = method.invoke(object, args);
            System.out.println("after InvocationHandler");
        }
        return o;
    }
}
