

## 解耦的思路

​	![image-20200924210923265](C:\Users\91156\Desktop\CS\JavaOnly\笔记\image-20200924210923265.png)

​	但是这样换数据库的时候还是要修改，

​	第二步：通过读取配置文件来获取要创建的对象的全限定类名

​	![image-20200924212051785](C:\Users\91156\Desktop\CS\JavaOnly\笔记\image-20200924212051785.png)

​	也就是通过反射解耦，工厂模式解耦

IOC控制反转，把找资源的权利交给工厂
作用就是削减程序的耦合，削减不能消除

## 2 使用spring的IOC解决程序的耦合
spring做的工作是项目管理，工厂模式是spring的核心模式，甚至可以说spring就是在打造一个工厂，生产对象，创建对象。但是，工厂模式的核心并不在于生产对象，而是在于管理，是在用生产的方式去管理组件

使用mybaits简化了jdbc，简化到只需要一些配置文件了。spring就是要简化new的过程。

目的：解决企业开发的复杂性

### spring历史

* 2002年，首次推出了spring的雏形interface21。
* 2004年，3.24，正是发布1.0版本。
* Rod Johnson 音乐博士
* spring理念：是现有的技术更加容易使用，本身是一个大杂烩，整合了现有的技术框架
* SSH： Struct2 + Spring + Hibernate
* SSM：SpringMVC + Spring + Mybatis

### 2.2  优点

* Spring是一个开源的免费的框架（容器）
* Spring是一个轻量级的、非入侵式的框架
* 控制反转IOC，面相切片编程AOP
* 支持事务的处理，对框架整合的支持

总结一句话：Spring就是一个轻量级的控制反转和面向切片编程的框架

###  2.3 组成

* 七大组成

### 2.4 扩展

* spring boot 构建一切
* spring cloud 协调一切
* spring cloud data flow 连接一切

学习spring boot的前提，是完全掌握spring以及springMVC，承上启下的作用

弊端：发展了太久之后，违背了原来的理念！配置十分繁琐，人称“配置地狱”

## 3 IOC理论推导

1. UserDao 接口
2. UserDaoImpl 实现类
3. UserService 接口
4. UserServiceimpl 实现类

在我们之前的业务中，用户的需求可能影响我们原来的代码，我们需要根据用户的需求去修改原代码！如果程序代码量十分大，修改一次的成本代价十分昂贵。

我们使用一个Set接口，已经发生了革命性的变化。

* 之前，程序是主动创建对象！控制权在程序员手上
* 使用了set注入之后，程序不再具有主动性，而是变成了被动的接受对象

这种思想，从本质上解决了问题，我们程序员不用再去管理对象的创建了。系统的耦合性大大降低，可以更加专注在业务的实现上，这就是IOC的原型。

## 5 IOC创建对象的方式
1. 使用无参构造创建对象，默认
2. 假设
   1. 下标赋值
   2. 类型
   3. 参数名

总结：在配置文件加载的时候，容器中的管理的对象就已经初始化了！  
## 6. Spring配置
### 6.1 bean
* id：bean的唯一标识符，也就是相当于我们学的对象名
* class：bean 对象所对应的全限定类名：包名 + 类名
* name：别名
### 6.2 别名 alias
### 6.3 import
这个import，一般用于团队开发，导入多个xml配置文件，合并为一个，一个总的，多个分的
## 6. DI依赖注入
### 6.1构造器注入
前面已经说过了
### 6.2 Set方式注入【重点】
- 依赖注入
  * 依赖：bean对象的创建依赖于容器！
  * 注入：bean对象中的所有属性，有容器来

【环境搭建】
1. 复杂类型

```java
public class Address {
    private String address;
	public String getAddress() {
    	return address;
	}

	public void setAddress(String address) {
   		this.address = address;
	}
}
```
2. 真实测试对象

实际上p（property）命名空间对应的就是set方式注入，而c（constructor）命名空间对应的就是构造注入方式

### 6.3 拓展方式

我们可以使用p命名空间和c命名空间



注意点：p命名空间和c命名空间不能这使用，必须导入xml约束

![image-20200928192122627](C:\Users\91156\AppData\Roaming\Typora\typora-user-images\image-20200928192122627.png)

六种作用域



1. 单例模式（Spring的默认机制）

   ```xml
       <bean id="user2" class="org.lihe.pojo.User" c:age="18" c:name="lihe2" scope="singleton"/>
   ```

2. 原型模式：每次从容器中get的时候，都会产生一个新对象

3. 其余的request、session、application，这些只能在web开发中使用。

## 7. Bean的自动装配

现在都是手动装配，都是自己写的

- 自动装配是Spring满足bean依赖的一种方式！
- Spring会在上下文中自动寻找，并自动给bean装配属性！

在Spring中有三种装配方式

1. 在xml中显式的配置
2. 在java中显式配置（写java configure 比较麻烦）
3. 隐式的自动装配bean【重要】



### 7.1 测试

环境搭建：一个人有两个宠物！

```xml
    <bean id="cat" class="org.lihe.pojo.Cat"/>
    <bean id="dog" class="org.lihe.pojo.Dog"/>

    <bean id="person" class="org.lihe.pojo.Person">
        <property name="name" value="lihe"/>
        <property name="dog" ref="dog"/>
        <property name="cat" ref="cat"/>
    </bean>

```



### 7.2 byName自动装配

```xml
    <bean id="cat" class="org.lihe.pojo.Cat"/>
    <bean id="dog111" class="org.lihe.pojo.Dog"/>

    <!--
    byName: 会自动装配在容器上下文中查找，和自己对象的set方法后面的值对应的 bean id！
    byType: 会自动装配在容器上下文中查找，和自己对象属性类型相同的 bean！但是有相同名字的类型的时候会报错，必须类型唯一
    -->
    <bean id="person" class="org.lihe.pojo.Person" autowire="byType">
        <property name="name" value="lihe"/>
    </bean>

```

### 7.3 byType自动装配

小节：

- byName的时候必须保证所有bean的id唯一，并且这个bean需要和自动注入的属性的set方法的值一致！
- byType的时候，需要保证所有的bean的class唯一，并且这个bean需要和自动注入的属性的类型唯一！



### 7.4 注解实现自动装配

jdk1.5支持的注解，Spring2.5就支持注解！

The introduction of annotation-based configuration raised the question of whether this approach is “better” than XML. The short answer is “it depends.”  狂神说错了

要使用注解须知：

1. 导入约束. context约束

2. 配置注解的支持：<context:annotation-config/>

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd">
   
       <context:annotation-config/>
   
   </beans>
   ```

@Autowired

直接在属性上使用即可！

Set方法上也可以，在属性上的话连set方法都不需要！

使用Autowired我们可以不用编写set方法了，前提是你这个自动装配的属性在IOC（Spring）容器中存在，且符合名字byName！

科普：

```java
@Nullable 字段标记了这个注解，说明这个属性可以为Null
```

```java
public @interface Autowired {
    boolean required() default true;
}
// 如果显式定义了Autowired的required属性为false，说明这个对象可以为null，否则不允许为空
```



测试代码

```java
public class Person {
    @Autowired(required = false)
    private Dog dog;
    @Autowired
    private Cat cat;
    private String name;
}
```



如果@Autowired自动装配的属性比较复杂，自动装配无法通过一个注解【@Autowired】完成的时候，我们可以使用@Qulifier(value=“dog222”)去配合autowired使用，指定一个唯一的bean对象



**java自带的@Resource注解**

```java
public class Person {
    // 先通过名字找，在通过类型找
    @Resource
    private Dog dog;
}
```



小节：

@Resource和@Autowired的区别

- 都是用来自动转配的，都可以放在属性字段上
- @Autowired通过byType的方式实现，而且必须要求这个对象存在。【常用】
- @Resource通过byName的方式实现，如果找不到，就通过byType，如果都找不到，就报错！【常用】
- 执行顺序不同：@Autowired通过byType，@Resource通过byName的方式实现

@Autowired是先type后name，@Resource相反

## 8. 使用注解开发

在Spring4之后，要使用注解开发，必须保证aop包的导入

![image-20200928222723627](C:\Users\91156\AppData\Roaming\Typora\typora-user-images\image-20200928222723627.png)

使用注解需要导入context约束，增加注解的支持！





1. bean

2. 属性如何注入

   ```java
   // 等价于在bean里面
   @Component
   public class User {
       // 相当于在bean中给属性value， 简单这样写，复杂的还是配置文件方便
       @Value("lihe")
       public String name;
   }
   
   ```

   

3. 衍生的注解

   @Component有几个衍生注解，我们在web开发中会按照mvc三层架构分层！

   - dao 【@Repository】

   - service 【@Service】

   - controller 【@Controller】

     这四个注解功能时一样的，都是代表将某个类注册到Spring中，装配Bean

4. 自动配置

   上面说过了

   ```xml
   - @Autowired：自动装配通过类型、名字
       如果Autowired不能唯一自动装配上属性，则需要通过@Qualifier(value="xxx")
   - @Resource：自动装配通过类型、名字
   - @Nullable: 可以为空
   ```

5. 作用域

   @Scope

6. 小节

   xml与注解：

   - xml：更加完成，适用于任何场合！维护简单方便
   - 注解：不是自己的类不能使用，维护相对复杂

   xml与注解的最佳实践：

   - xml用来管理bean
   - 注解复杂完成属性的注入
   - 我们在使用的过程中，只需要注意一个问题：要想让注解生效，就需要开启注解的支持



## 9. 使用Java的方式配置Spring

现在要完全不适用Spring的xml配置了，全权交给Java来做！

JavaConfig是Spring的一个子项目，在Spring4之后，他成为了一个核心功能！

![image-20200929093516229](C:\Users\91156\AppData\Roaming\Typora\typora-user-images\image-20200929093516229.png)





这种纯Java的配置方式，在SpringBoot中随处可见！SSM



## 10. 代理模式

中介；

为什么要学习代理模式？因为这就是Spring AOP的底层实现！【SpringAOP 和 SpringMVC 面试必考】

代理模式分类：

- 静态代理
- 动态代理

![代理模式](C:\Users\91156\Pictures\visio\代理模式.png)

### 10.1 静态代理

角色分析：

- 抽象角色：一般会使用接口或抽象类解决
- 真实角色：被代理的角色
- 代理角色：代理真实角色，代理真实角色后，一般会做一些附属操作
- 客户：访问代理对象的人！



代码步骤：

1. 接口

   ```java
   public interface Rent {
       public void rent();
   }
   ```

   

2. 真实角色

   ```java
   public class Host implements Rent {
       public void rent() {
           System.out.println("房东要出租房子！");
       }
   }
   
   ```

   

3. 代理角色

   ```java
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
   
   ```

   

4. 客户端访问角色

   ```java
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
   
   ```

   



代理模式的好处：

- 可以使现实角色的操作更加纯粹！不用去关注一些公共的业务
- 公共业务交给代理角色！实现了业务的分工！
- 公共业务发生扩展的时候，方便集中管理！

缺点：

- 一个真实角色就会产生一个代理角色；代码量会翻倍~开发效率会变低~

### 10.2 加深理解

