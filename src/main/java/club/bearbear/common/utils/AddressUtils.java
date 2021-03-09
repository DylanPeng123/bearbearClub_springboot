package club.bearbear.common.utils;

import club.bearbear.common.constant.Constants;
import club.bearbear.common.utils.http.HttpUtils;
import club.bearbear.framework.config.BearbearConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * ClassName AddressUtils
 *
 * @author Dylan
 * @description 获取地址类
 * @createDate 10/28/20 17:21
 */
public class AddressUtils {

    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    // IP地址查询
    private static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    private static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        String address = UNKNOWN;
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        if (BearbearConfig.isAddressEnabled()) {
            try {
                String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
                if (StringUtils.isEmpty(rspStr)) {
                    log.error("获取地理位置异常 {}", ip);
                    return UNKNOWN;
                }
                HashMap rspMap = JsonUtils.json2Bean(rspStr, HashMap.class);
                String region = (String) rspMap.get("pro");
                String city = (String) rspMap.get("city");
                return String.format("%s %s", region, city);
            } catch (Exception e) {
                log.error("获取地理位置异常 {}", e);
            }
        }
        return address;
    }
}
