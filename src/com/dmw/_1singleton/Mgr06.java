package com.dmw._1singleton;

/**
 * lazy loading
 * 饿汉式
 * 这是一种完美的
 */
public class Mgr06 {
    //声明非最终的本例对象
    //需要加上volatile
    //JIT优化 语句重排
    private static volatile Mgr06 INSTANCE;

    //私有化无参构造
    private Mgr06() {
    }

    public static Mgr06 getInstance() {
        if (INSTANCE == null) {
            synchronized (Mgr06.class) {
                //如果已经有对象被创建了，即使获得了锁，也不会再创建对象了
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr06();
                }
            }
        }
        return INSTANCE;

    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr06.getInstance().hashCode());//同一个类的不同对象，哈希码不同(哈希码相同也有可能不是同一个对象)。 哈希码如：1133046463
            }).start();
        }
        //上面是对下面的简写，如果run()方法中只有一条语句，那么大括号也同样是可以省略的(此时需要去掉那条语句后面的分号;)
      /*  for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Mgr05.getInstance().hashCode());
                }
            }).start();
        }*/
    }
}
