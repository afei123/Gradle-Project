package ExceptionPag;

public class MainTest {
    public static void main(String[] args){
        IOException ioException = new IOException();
        ioException.printStackTrace();
        Exception exception = new Exception() {
            @Override
            public void exceptionMessage(StringBuffer exception) {
                exception.append("似曾相识");
            }
        };
        exception.printStackTrace();
    }
}
