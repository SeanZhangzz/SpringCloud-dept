package reflect;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class SeanCallback implements MethodInterceptor {
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("callback before ...");
        Object o = methodProxy.invokeSuper(proxy, args);
        System.out.println("callback after ...");

        return o;
    }
}
