import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
        Bean bean = new Bean();
        bbb asd = (bbb) Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if (method.getName().equals("asd")){
                    System.out.println(321);
                }
                return method.invoke(bean,objects);
            }
        });
        asd.asd();
    }
    public static void JDKIncoationHandler(){
        Bean bean = new Bean();
        bbb asd = (bbb)java.lang.reflect.Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new java.lang.reflect.InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("asd")){
                    System.out.println(321);
                }
                Object invoke = method.invoke(bean, args);
                return invoke;
            }
        });
        asd.asd();
    }
}
