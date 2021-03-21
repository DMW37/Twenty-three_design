package com.dmw._1singleton;

/**
 * 跟第一个是一个意思
 */
public class Mgr02 {
    //先定义静态实例
    private static final Mgr02 INSTANCE;
    //通过静态代码块赋值
    static {
        INSTANCE = new Mgr02();
    }
    //私有化午餐构造
    private Mgr02() {

    }
    //提供其他类能够访问的静态方法
    public static Mgr02 getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        Mgr02 m1 = getInstance();
        Mgr02 m2 = getInstance();
        //如果两个对象相同，则为true
        System.out.println(m1==m2);
    }
}
