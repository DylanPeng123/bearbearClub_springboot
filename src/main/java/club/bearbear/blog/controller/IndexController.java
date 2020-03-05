package club.bearbear.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ClassName IndexController
 *
 * @author Dylan
 * @description TODO
 * @createDate 2020-03-04 16:45
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }

}
