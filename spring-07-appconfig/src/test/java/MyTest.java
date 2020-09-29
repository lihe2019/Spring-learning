import org.lihe.config.LiheConfig;
import org.lihe.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        // 如果完全使用了配置类方式去做，我们就只能通过AnnotationConfig 上下文来获取容器，通过噢诶之类的class对象加载
        ApplicationContext context = new AnnotationConfigApplicationContext(LiheConfig.class);
        User getUser = (User) context.getBean("user");
        System.out.println(getUser.getName());
    }
}
