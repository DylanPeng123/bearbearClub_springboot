package club.bearbear.blog.controller;

import club.bearbear.blog.dto.AccessTokenDTO;
import club.bearbear.blog.dto.GithubUser;
import club.bearbear.blog.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName AuthorizeController
 *
 * @author Dylan
 * @description 第三方认证
 * @createDate 2020-03-05 14:51
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("{github.redirect.uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String githubCallback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String result = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println(result);
        //获取token
        String[] split = (result.split("&"))[0].split("=");
        String s = split[1];
        GithubUser githubUser = githubProvider.getGithubUser(s);
        if (githubUser != null) {
            System.out.println(githubUser.toString());
        }
        return "index";
    }

}
