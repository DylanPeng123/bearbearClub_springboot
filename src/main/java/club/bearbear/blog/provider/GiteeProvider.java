package club.bearbear.blog.provider;

import club.bearbear.blog.dto.GiteeAccessTokenDTO;
import club.bearbear.blog.dto.GiteeUser;
import club.bearbear.common.utils.JsonUtils;
import club.bearbear.common.utils.http.OKHttpUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * FileName GiteeProvider
 *
 * @author Dylan
 * @createDate 3/13/21 3:41
 */
@Component
public class GiteeProvider {

    public String getAccessToken(GiteeAccessTokenDTO giteeAccessTokenDTO,String url){
        String result = null;
        try {
            String json = JsonUtils.toJson(giteeAccessTokenDTO);
            result = OKHttpUtils.postRequest(url, json);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public GiteeUser getGiteeUser(String accessToken, String url) {
        //请求github用户信息的url
        url = url + "?access_token=" + accessToken;
        try {
            String result = OKHttpUtils.getRequest(url);
            //将json字符串的用户信息转为bean
            GiteeUser giteeUser = JsonUtils.json2Bean(result, GiteeUser.class);
            return giteeUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
