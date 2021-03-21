package com.dmw._1singleton;

/**
 * 枚举单例
 * 不仅可以解决线程同步，还可以防止反序列化
 * 完美中的完美
 *
 * 反序列化讲解：
 * JVM对java文件解析成class
 * 创建对象的方式：是指将class文件放到内存，通过反序列化方式创建出一个对象出来
 * 为什么这种方式可以防止反序列化？
 * 枚举没有构造方法，反编译后为abstract class
 */
public enum Mgr08 {
    INSTANCE;

    public void m() {
        System.out.println("aaa");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr08.INSTANCE.hashCode());//同一个类的不同对象，哈希码不同(哈希码相同也有可能不是同一个对象)。 哈希码如：1133046463
            }).start();
        }
    }
}
