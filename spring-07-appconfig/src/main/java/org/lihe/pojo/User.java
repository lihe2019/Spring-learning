package org.lihe.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 这里这个注解的意思就是这个类被Spring接管了，注册到了容器中
@Component
public class User {
//    @Value("lihe")
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    @Value("Lihe")
    public void setName(String name) {
        this.name = name;
    }
}
