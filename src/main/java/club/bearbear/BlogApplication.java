package club.bearbear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * ClassName BlogApplication
 *
 * @author Dylan
 * @description 程序启动类
 * @createDate 2020-03-04 13:22
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class,args);
    }
}
