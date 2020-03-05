package club.bearbear.blog.dto;

import lombok.Data;

/**
 * ClassName GithubUser
 *
 * @author Dylan
 * @description Github授权的用户信息
 * @createDate 2020-03-04 18:57
 */
@Data
public class GithubUser {

    private String id;
    private String name;
    private String bio;
    private String email;

}
