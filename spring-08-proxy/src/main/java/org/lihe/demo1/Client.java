package org.lihe.demo1;

public class Client {
    public static void main(String[] args) {
        // 房东 要出租房子
        Host host = new Host();
        // 代理 中介帮房东租房子，但是呢？代理一般会有一些附属操作
        Proxy proxy = new Proxy(host);
        // 客户不用面对房东，直接找房屋中介租房即可
        proxy.rene();
    }
}
