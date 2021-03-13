package club.bearbear.blog.dto;

import java.util.StringJoiner;

/**
 * ClassName GithubAccessTokenDTO
 *
 * @author Dylan
 * @description github认证 dto 数据传输模型
 * @createDate 2020-03-04 16:45
 */

public class GithubAccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GithubAccessTokenDTO.class.getSimpleName() + "[", "]")
                .add("client_id='" + client_id + "'")
                .add("client_secret='" + client_secret + "'")
                .add("code='" + code + "'")
                .add("redirect_uri='" + redirect_uri + "'")
                .add("state='" + state + "'")
                .toString();
    }
}
