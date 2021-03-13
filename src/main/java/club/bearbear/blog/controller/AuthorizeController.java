package club.bearbear.blog.controller;

import club.bearbear.blog.dto.GiteeAccessTokenDTO;
import club.bearbear.blog.dto.GiteeUser;
import club.bearbear.blog.dto.GithubAccessTokenDTO;
import club.bearbear.blog.dto.GithubUser;
import club.bearbear.blog.provider.GiteeProvider;
import club.bearbear.blog.provider.GithubProvider;
import club.bearbear.common.utils.JsonUtils;
import club.bearbear.common.utils.StringUtils;
import club.bearbear.framework.config.auth2.GiteeAuthConfig;
import club.bearbear.framework.config.auth2.GithubAuthConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * ClassName AuthorizeController
 *
 * @author Dylan
 * @description 第三方认证
 * @createDate 2020-03-05 14:51
 */
@Controller
@RequestMapping("/auth")
public class AuthorizeController {

    private static final Logger log = LoggerFactory.getLogger(AuthorizeController.class);

    @Autowired
    private GithubAuthConfig githubAuthConfig;
    @Autowired
    private GiteeAuthConfig giteeAuthConfig;
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private GiteeProvider giteeProvider;

    @GetMapping("/github")
    public String githubAuthLogin() {
        String url = githubAuthConfig.getAuthorizeUrl() + "?client_id=" +
                githubAuthConfig.getClientId() + "&redirect_uri=" +
                githubAuthConfig.getRedirectUrl() + "&state=" +
                StringUtils.getRandomString(5);
        log.info("github授权url:{}", url);
        return "redirect:" + url;
    }

    @GetMapping("/github/callback")
    public String githubCallback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
        GithubAccessTokenDTO githubAccessTokenDTO = new GithubAccessTokenDTO();
        githubAccessTokenDTO.setClient_id(githubAuthConfig.getClientId());
        githubAccessTokenDTO.setClient_secret(githubAuthConfig.getClientSecret());
        githubAccessTokenDTO.setRedirect_uri(githubAuthConfig.getRedirectUrl());
        githubAccessTokenDTO.setCode(code);
        githubAccessTokenDTO.setState(state);
        String result = githubProvider.getAccessToken(githubAccessTokenDTO, githubAuthConfig.getAccessTokenUrl());
        String[] split = (result.split("&"))[0].split("=");
        String s = split[1];
        GithubUser githubUser = githubProvider.getGithubUser(s, githubAuthConfig.getUserInfoUrl());
        if (githubUser != null) {
            System.out.println(githubUser.toString());
        }
        return "index";
    }

    @GetMapping("/gitee")
    public String giteeAuthLogin() {
        //https://gitee.com/oauth/authorize?client_id={client_id}&redirect_uri={redirect_uri}&response_type=code
        String url = giteeAuthConfig.getAuthorizeUrl() + "?client_id=" +
                giteeAuthConfig.getClientId() + "&redirect_uri=" +
                giteeAuthConfig.getRedirectUrl()+"&response_type=code";
        log.info("gitee授权url{}", url);
        return "redirect:" + url;
    }

    @GetMapping("/gitee/callback")
    public String giteeCallback(@RequestParam(name = "code") String code) {
        //https://gitee.com/oauth/token?grant_type=authorization_code&
        // code={code}&client_id={client_id}&redirect_uri={redirect_uri}&client_secret={client_secret}
        GiteeAccessTokenDTO giteeAccessTokenDTO = new GiteeAccessTokenDTO();
        giteeAccessTokenDTO.setClient_id(giteeAuthConfig.getClientId());
        giteeAccessTokenDTO.setClient_secret(giteeAuthConfig.getClientSecret());
        giteeAccessTokenDTO.setRedirect_uri(giteeAuthConfig.getRedirectUrl());
        giteeAccessTokenDTO.setGrant_type(giteeAuthConfig.getGrantType());
        giteeAccessTokenDTO.setCode(code);
        String result = giteeProvider.getAccessToken(giteeAccessTokenDTO, giteeAuthConfig.getAccessTokenUrl());
        Map reslultMap = JsonUtils.json2Bean(result, Map.class);
        String accessToken = (String) reslultMap.get("access_token");
        GiteeUser giteeUser = giteeProvider.getGiteeUser(accessToken,giteeAuthConfig.getUserInfoUrl());
        if (giteeUser != null) {
            System.out.println(giteeUser.toString());
        }
        return "redirect:/";
    }

}
