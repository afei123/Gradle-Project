package Imaginary;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyTest {
    public static void main(String[] args){
        Bean bean = new Bean();
        Imaginary imaginary = new Imaginary();
        imaginary.setSupperClass(Bean.class);
        imaginary.setMethodInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if(method.getName().equals("asd")){
                    System.out.println(2);
                }
                System.out.println(o.getClass()+"..."+method.getName());
                return method.invoke(bean,objects);
            }
        });
        imaginary.asd();
    }
}
