package club.bearbear.blog.dto;

import lombok.Data;

/**
 * ClassName AccessTokenDTO
 *
 * @author Dylan
 * @description github认证 dto 数据传输模型
 * @createDate 2020-03-04 16:45
 */
@Data
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
