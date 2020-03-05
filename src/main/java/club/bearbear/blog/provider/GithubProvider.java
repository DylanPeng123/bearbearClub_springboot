package club.bearbear.blog.provider;

import club.bearbear.blog.dto.AccessTokenDTO;
import club.bearbear.blog.dto.GithubUser;
import club.bearbear.blog.utils.HttpUtil;
import club.bearbear.blog.utils.JsonUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * ClassName GithubProvider
 *
 * @author Dylan
 * @description TODO
 * @createDate 2020-03-04 17:14
 */
@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        String url = "https://github.com/login/oauth/access_token";
        String result = null;
        try {
            String json = JsonUtil.toJson(accessTokenDTO);
            result = HttpUtil.postRequest(url, json);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public GithubUser getGithubUser(String accessToken) {
        //请求github用户信息的url
        String url = "https://api.github.com/user?access_token=" + accessToken;
        try {
            String result = HttpUtil.getRequest(url);
            //将json字符串的用户信息转为bean
            GithubUser githubUser = JsonUtil.json2Bean(result, GithubUser.class);
            System.out.println(githubUser.toString());
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
