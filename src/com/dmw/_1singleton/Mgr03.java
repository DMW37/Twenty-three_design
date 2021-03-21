package com.dmw._1singleton;

/**
 * lazy loading
 * 饿汉式
 * 虽然达到了初始化的问题，但是多线程访问时会带来影响
 */
public class Mgr03 {
    //声明非最终的本例对象
    private static Mgr03 INSTANCE;
    //私有化无参构造
    private Mgr03() {
    }
    //如果对象没有被创建，那么才进行初始化，如果已经存在，就返回原本的对象
    public static Mgr03 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //当第一个线程进入此处，且尚未被创建对象，第二个线程也就同样会进入此处，这就有了创建多个对象的可能
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Mgr03.getInstance().hashCode());//同一个类的不同对象，哈希码不同(哈希码相同也有可能不是同一个对象)。 哈希码如：1133046463
            }).start();
        }
        //上面是对下面的简写，如果run()方法中只有一条语句，那么大括号也同样是可以省略的(此时需要去掉那条语句后面的分号;)
      /*  for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Mgr03.getInstance().hashCode());
                }
            }).start();
        }*/
    }
}
