package org.lihe.demo1;

public class Proxy {
    private Host host;
    public Proxy(){

    }
    public Proxy(Host host){

    }
    public void rene(){
        host.rent();
    }
    public void seeHouse(){
        System.out.println("中介带你看房");
    }

    public void fare(){
        System.out.println("收中介费！");
    }
    public void hetong(){
        System.out.println("签租赁合同");
    }
}
