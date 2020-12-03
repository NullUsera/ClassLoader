package com.demo.lib;

/**
 * ClassLoader双亲委派机制的验证
 */
public class ParentDelegationModel {

    public static void main(String[] args) {
        ClassLoader classLoader = Student.class.getClassLoader();
        System.out.println("Student classLoader is: " + classLoader);

        ClassLoader parent1 = classLoader.getParent();
        System.out.println("Student classLoader parent is: " + parent1);

        ClassLoader parent2 = parent1.getParent();
        System.out.println("Student classLoader grandParent is: " + parent2);

        /*Student类加载分析：
        1、APPClassLoader将加载的任务委派给它的父类加载器ExtClassLoader，
        2、ExtClassLoader的parent为null，所以直接将加载任务委派给BootstrapClassLoader
        3、BootstrapClassLoader在jdk/lib下无法找到Test.class文件，因此返回的Class为null
        4、因为parent和BootstrapClassLoader都没有成功加载Test类，所以AppClassLoader会调用自己的findClass()
           来加载Test类

           “双亲委派”机制只是Java推荐的机制，并不是强制要求的，我们可以通过继承java.lang.ClassLoader来实现自己
           的ClassLoader。
           如果想保持双亲委派机制，重写findClass()即可；
           如果想破坏双亲委派机制，可以重写loadClass()。
         */

        /** 这是ClassLoader类中给出的自定义ClassLoader的示例：
         *
         * <p> The network class loader subclass must define the methods {@link
         * #findClass <tt>findClass</tt>} and <tt>loadClassData</tt> to load a class
         * from the network.  Once it has downloaded the bytes that make up the class,
         * it should use the method {@link #defineClass <tt>defineClass</tt>} to
         * create a class instance.  A sample implementation is:
         *
         * <blockquote><pre>
         *     class NetworkClassLoader extends ClassLoader {
         *         String host;
         *         int port;
         *
         *         public Class findClass(String name) {
         *             byte[] b = loadClassData(name);
         *             return defineClass(name, b, 0, b.length);
         *         }
         *
         *         private byte[] loadClassData(String name) {
         *             // load the class data from the connection
         *             &nbsp;.&nbsp;.&nbsp;.
         *         }
         *     }
         */
    }
}
