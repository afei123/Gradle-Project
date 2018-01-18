import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Cglib {
    public static void main(String[] args){
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
}
