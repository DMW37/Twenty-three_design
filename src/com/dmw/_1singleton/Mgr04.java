package com.dmw._1singleton;

/**
 * lazy loading
 * 饿汉式
 * 虽然达到了初始化的问题，但是多线程访问时会带来影响
 */
public class Mgr04 {
    //声明非最终的本例对象
    private static Mgr04 INSTANCE;
    //私有化无参构造
    private Mgr04() {
    }
    //如果对象没有被创建，那么才进行初始化，如果已经存在，就返回原本的对象
    //加上synchronized(已同步) 锁定本类的资源，解决了多个线程访问时造成的不安全，却同时效率下降了
    public static synchronized Mgr04 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr04();
        }
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Mgr04.getInstance().hashCode());//同一个类的不同对象，哈希码不同(哈希码相同也有可能不是同一个对象)。 哈希码如：1133046463
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
