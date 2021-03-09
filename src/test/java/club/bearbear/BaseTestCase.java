package club.bearbear;

import club.bearbear.common.utils.AddressUtils;
import club.bearbear.common.utils.RedisUtils;
import club.bearbear.framework.config.BearbearConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName BaseTestCase
 *
 * @author Dylan
 * @description TODO
 * @createDate 10/28/20 18:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTestCase {

    @Autowired
    private RedisUtils redisUtil;

    @Test
    public void cacheTest(){
        //redisUtil.set("test3","12334",1234l);

        long name = redisUtil.getExpire("test3");
        System.out.println("-->>>>>>D----->  " +name);
        //Object test = redisUtil.get("test1");

        //System.out.println("-->>>>>>D----->  " +test.toString());

    }

    @Test
    public void configTest() {
        String name = BearbearConfig.getName();
        System.out.println("-->>>>>>D----->  " + name);

    }

    @Test
    public void baseTest() {
        String realAddressByIP = AddressUtils.getRealAddressByIP("171.43.153.131");
        System.out.println("-->>>>>>D----->  " + realAddressByIP);
    }




}
