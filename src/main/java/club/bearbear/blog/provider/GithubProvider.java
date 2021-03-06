package club.bearbear.blog.provider;

import club.bearbear.blog.dto.GithubAccessTokenDTO;
import club.bearbear.blog.dto.GithubUser;
import club.bearbear.common.utils.JsonUtils;
import club.bearbear.common.utils.http.OKHttpUtils;
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

    public String getAccessToken(GithubAccessTokenDTO githubAccessTokenDTO, String url) {
        String result = null;
        try {
            String json = JsonUtils.toJson(githubAccessTokenDTO);
            result = OKHttpUtils.postRequest(url, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public GithubUser getGithubUser(String accessToken, String url) {
        //请求github用户信息的url
        url = url + "?access_token=" + accessToken;
        try {
            String result = OKHttpUtils.getRequest(url);
            //将json字符串的用户信息转为bean
            GithubUser githubUser = JsonUtils.json2Bean(result, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
