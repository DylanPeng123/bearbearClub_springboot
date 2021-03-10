package club.bearbear;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * FileName BlogServletInitializer
 *
 * @author Dylan
 * @createDate 3/11/21 3:24
 * @description 外置tomcat运行项目配置
 */
public class BlogServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BlogApplication.class);
    }
}
