package org.lihe.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// 等价于在bean里面
@Component
@Scope("singleton")
public class User {
    // 相当于在bean中给属性value， 简单这样写，复杂的还是配置文件方便
    @Value("lihe")
    public String name;
}
