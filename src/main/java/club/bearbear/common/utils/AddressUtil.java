package club.bearbear.common.utils;

import club.bearbear.common.constant.Constants;
import club.bearbear.common.utils.http.HttpUtil;
import club.bearbear.framework.config.BearbearConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * ClassName AddressUtil
 *
 * @author Dylan
 * @description 获取地址类
 * @createDate 10/28/20 17:21
 */
public class AddressUtil {

    private static final Logger log = LoggerFactory.getLogger(AddressUtil.class);

    // IP地址查询
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知地址
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        String address = UNKNOWN;
        // 内网不查询
        if (IpUtil.internalIp(ip)) {
            return "内网IP";
        }
        if (BearbearConfig.isAddressEnabled()) {
            try {
                String rspStr = HttpUtil.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
                if (StringUtil.isEmpty(rspStr)) {
                    log.error("获取地理位置异常 {}", ip);
                    return UNKNOWN;
                }
                HashMap rspMap = JsonUtil.json2Bean(rspStr, HashMap.class);
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
