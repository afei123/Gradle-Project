import org.springframework.asm.ClassReader;
import org.springframework.asm.Opcodes;
import org.springframework.asm.ClassWriter;
import org.springframework.asm.MethodVisitor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class Main {
    private final static String AsmClassName = "AsmMain";
    private final static String ClassFile = ".class";
    private final static String FilePath = "D:/ClassFile/";
    private final static String Owner = FilePath+AsmClassName;
    public static void main(String[] args){
        try {
            test();
            asmMain();
            //asd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void asmMain()throws Exception{
        ClassWriter cw = new ClassWriter(1);
        //创建类
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,AsmClassName,null,"java/lang/Object",null);
        //构造方法
        cw.visitMethod(Opcodes.ACC_PUBLIC,"<init>","()V",null,null);
        //创建成员变量
        cw.visitField(Opcodes.ACC_PUBLIC+ Opcodes.ACC_STATIC,"asmNum","I",null,5);
        //创建方法
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC , "asm", "(JI)J", null, null);
        //在方法中写代码{
        mv.visitFieldInsn(Opcodes.GETSTATIC,Owner,"asmNum1","java/lang/String");
        //调用方法
        mv.visitMethodInsn(Opcodes.INVOKESTATIC,"java/lang/System",
               "currentTimeMillis","()J");
        //设置接受变量
        mv.visitVarInsn(Opcodes.ISTORE, 2);
        //获取变量
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out","Ljava/io/PrintStream;");
        mv.visitMethodInsn(Opcodes.INVOKESTATIC,"java/lang/System",
                "currentTimeMillis","()J");
        mv.visitVarInsn(Opcodes.ISTORE,4);
        mv.visitVarInsn(Opcodes.ILOAD,2);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(J)V");//V是返回值 , J是参数列表
        mv.visitVarInsn(Opcodes.ILOAD,2);
        mv.visitInsn(Opcodes.LRETURN);
        //在方法中写代码}
        mv.visitEnd();
        //把class写成文件的格式
        byte[] code = cw.toByteArray();
        outPutClass(code,AsmClassName);
    }
    public static void test() throws Exception {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS
                | ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "my/Example", null,
                "java/lang/Object", null);
        // add constructor
        MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V",
                null, null);
        mw.visitVarInsn(Opcodes.ALOAD, 0); // this 入栈
        mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>",
                "()V");
        mw.visitInsn(Opcodes.RETURN);
        mw.visitMaxs(0, 0);
        mw.visitEnd();
        // add 2 static int, a and b
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "a", "I",
                null, new Integer(6)).visitEnd();
        cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "b", "I",
                null, new Integer(7)).visitEnd();
        // add main method
        mw = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main",
                "([Ljava/lang/String;)V", null, null);
        // read static variables
        mw.visitFieldInsn(Opcodes.GETSTATIC, "my/Example", "a", "I");
        mw.visitFieldInsn(Opcodes.GETSTATIC, "my/Example", "b", "I");
        // do calculation
        mw.visitInsn(Opcodes.IADD);
        mw.visitInsn(Opcodes.ICONST_3);
        mw.visitInsn(Opcodes.IMUL);
        mw.visitVarInsn(Opcodes.ISTORE, 2);
        // call println
        mw.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out","Ljava/io/PrintStream;");
        mw.visitVarInsn(Opcodes.ILOAD, 2);
        mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(I)V");
        mw.visitInsn(Opcodes.RETURN);
        mw.visitMaxs(0, 0);
        mw.visitEnd();
        // store and run code
        final byte[] code = cw.toByteArray();
        outPutClass(code,"copyExample.class");
        loadClass(code);
    }
    public static void asd(){
        String s = Integer.toBinaryString(1546545456);
        String s1 = Integer.toBinaryString(23598);
        System.out.println(1546545456 >>> 16);//101110000101110 0110 1001 0011 0000
        System.out.println("101110000101110".equals(s1));
    }
    public static void outPutClass(byte[] code , String className){
        try {
            FileOutputStream example = new FileOutputStream(FilePath+className+ClassFile);
            example.write(code);
            example.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void loadClass(byte[] code)throws Exception{
        Class exampleClass = new ClassLoader() {
            @SuppressWarnings("unchecked")
            protected Class findClass(String name)
                    throws ClassNotFoundException {
                return defineClass(name, code, 0, code.length);
            }
        }.loadClass("my.Example");
//        OutputStream out = new FileOutputStream("d:/Example.class");
//        out.write(code);
//        out.close();
        Method method = exampleClass.getMethod("main",
                new Class[] { String[].class });
        method.invoke(null, new Object[] { null });
    }
}
