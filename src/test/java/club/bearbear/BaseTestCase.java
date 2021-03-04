package club.bearbear;

import club.bearbear.common.utils.AddressUtil;
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


    @Test
    public void configTest() {
        String name = BearbearConfig.getName();
        System.out.println("-->>>>>>D----->  " + name);

    }

    @Test
    public void baseTest() {
        String realAddressByIP = AddressUtil.getRealAddressByIP("171.43.153.131");
        System.out.println("-->>>>>>D----->  " + realAddressByIP);
    }

}
