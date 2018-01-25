package ExceptionPag;

public class IOException extends Exception {
    @Override
    public void exceptionMessage(StringBuffer exception) {
        exception.append("测试异常");
    }
}
