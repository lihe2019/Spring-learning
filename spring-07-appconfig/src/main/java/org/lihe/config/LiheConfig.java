package org.lihe.config;

import org.lihe.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 这个也会被Spring托管，它本身也是一个Component，@Configure代表这是一个配置类，就和之前的beans.xml一样
public class LiheConfig {
    /*
    注册一个bean，就相当于我们之前写的一个bean标签
    这个方法的名字，就相当于bean标签的id属性
    这个方法的返回值，就相当于bean标签中的class属性
     */
    @Bean
    public User getUser(){
        return new User(); // return就是返回要注入的对象
    }
}
