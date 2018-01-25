package ExceptionPag;

public abstract class Exception {
    public void printStackTrace(){
        StringBuffer exception = new StringBuffer();
        exceptionMessage(exception);
        System.out.println(exception);
    }

    public abstract void exceptionMessage(StringBuffer exception);
}
