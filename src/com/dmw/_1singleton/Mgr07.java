package com.dmw._1singleton;

/**
 * 静态内部类方式
 * JVM保证单例
 * 加载外部类时不会加载内部类，这样可以实现懒加载
 * 这是最完美的
 */
public class Mgr07 {
    private Mgr07() {
    }

    private static class Mgr07Holder {
        private final static Mgr07 INSTANCE = new Mgr07();
    }

    public static Mgr07 getInstance() {
        return Mgr07Holder.INSTANCE;
    }

    public void m() {
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr06.getInstance().hashCode());//同一个类的不同对象，哈希码不同(哈希码相同也有可能不是同一个对象)。 哈希码如：1133046463
            }).start();
        }
    }
}
