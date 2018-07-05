package com.zn.paramcheck;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * xml配置 <aop:aspectj-autoproxy proxy-target-class="true"/>
 * Created by zhoun on 2018/7/5.
 **/

@RequestMapping("/txUser")
@Controller("txUserController")
public class TestController {

    @RequestMapping(value = "/insert")
    @CheckParam(fieldNames="name,age",require=true)
    public String insert(Object user){
        System.out.println("参数：" + user);
        return "success";
    }

}
