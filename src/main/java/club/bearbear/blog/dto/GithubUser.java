package club.bearbear.blog.dto;

import java.util.StringJoiner;

/**
 * ClassName GithubUser
 *
 * @author Dylan
 * @description Github授权的用户信息
 * @createDate 2020-03-04 18:57
 */

public class GithubUser {

    private String id;
    private String name;
    private String bio;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GithubUser.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .add("bio='" + bio + "'")
                .add("email='" + email + "'")
                .toString();
    }
}
