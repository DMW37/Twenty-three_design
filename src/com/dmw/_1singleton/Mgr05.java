package com.dmw._1singleton;

/**
 * lazy loading
 * 饿汉式
 * 虽然达到了初始化的问题，但是多线程访问时会带来影响
 */
public class Mgr05 {
    //声明非最终的本例对象
    private static Mgr05 INSTANCE;

    //私有化无参构造
    private Mgr05() {
    }

    public static Mgr05 getInstance() {
        if (INSTANCE == null) {
            //妄图通过减少同步代码块的方式提高效率，结果不可行
            //原因：当某一线程释放锁后，其它线程可以获得锁，就又会创建出不同的对象
            synchronized (Mgr05.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Mgr05();
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
                System.out.println(Mgr05.getInstance().hashCode());//同一个类的不同对象，哈希码不同(哈希码相同也有可能不是同一个对象)。 哈希码如：1133046463
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
