package factory.easy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ReflectTest {

        static interface Subject{
            void sayHi();
            void sayHello();
        }

        static class SubjectImpl implements Subject{

            @Override
            public void sayHi() {
                System.out.println("hi");
            }

            @Override
            public void sayHello() {
                System.out.println("hello");
            }
        }

        static class ProxyInvocationHandler implements InvocationHandler {
            private SubjectImpl target;
            public ProxyInvocationHandler(SubjectImpl target) {
                this.target=target;
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equals("sayHi")){
                    System.out.print("sayHi:");
                }else if(method.getName().equals("sayHello")){
                    System.out.print("sayHello:");
                }

                return method.invoke(target, args);
            }

        }

        public static void main(String[] args) {
            SubjectImpl subject=new SubjectImpl();
            Subject subjectProxy=(Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), new ProxyInvocationHandler(subject));
            subjectProxy.sayHi();
            subjectProxy.sayHello();

        }
}
