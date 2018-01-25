package Imaginary;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class Imaginary {
    private MethodInterceptor methodInterceptor;
    private MethodProxy methodProxy;
    private Class supperClass;

    public void asd()  {
        try {
            String methodName = "asd";
            methodInterceptor.intercept(this, supperClass.getMethod(methodName), new Object[]{}, methodProxy);
        }catch(Throwable e){
            e.printStackTrace();
        }
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public void setMethodProxy(MethodProxy methodProxy) {
        this.methodProxy = methodProxy;
    }

    public void setSupperClass(Class supperClass) {
        this.supperClass = supperClass;
    }
}
