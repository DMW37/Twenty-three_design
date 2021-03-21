package com.dmw._1singleton;

/**
 * 饿汉式
 * 内加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用，推荐使用！
 * 唯一缺点，不管用到与否，类装载时就完成实例化
 * （话说你不用，你加载它干嘛）
 */
public class Mgr01 {
    private static final Mgr01 INSTANCE = new Mgr01();
    //私有无参构造方法
    private Mgr01(){}
    //提供其他类访问该类时的方法
    public static Mgr01 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Mgr01 m1 = getInstance();
        Mgr01 m2 = getInstance();
        //如果两个对象相同，则为true
        System.out.println(m1==m2);
    }
}
