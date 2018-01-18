import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Cglib {
    public static void main(String[] args){
        InvocationHanderle();
    }
    public static void MethodInterceptor(){
        Bean asd = (Bean)Enhancer.create(Bean.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if (method.getName().equals("asd")){
                    System.out.println(321);
                }
                methodProxy.invokeSuper(o,objects);
                return null;
            }
        });
        asd.asd();
    }
    public static void InvocationHanderle(){
        Bean asd = (Bean)Enhancer.create(Bean.class, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if (method.getName().equals("asd")){
                    System.out.println(321);
                }
                return null;
            }
        });
        asd.asd();
    }
}