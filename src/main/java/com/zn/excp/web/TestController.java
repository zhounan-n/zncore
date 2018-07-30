package com.zn.excp.web;

import com.zn.excp.ExceptionHandle;
import com.zn.excp.entities.Result;
import com.zn.excp.enums.ExceptionEnum;
import com.zn.excp.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhounan
 * created on 2018/7/30
 */
@RestController
@RequestMapping
public class TestController {

    @Autowired
    private ExceptionHandle exceptionHandle;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Result getResult(@RequestParam("name") String name, @RequestParam("pwd") String pwd) {
        Result result = ResultUtils.success();

        if (("zhounan").equals(name)) {
            UserInfo userInfo = new UserInfo();
            userInfo.setName(name);
            userInfo.setName(pwd);
            result = ResultUtils.success(userInfo);
        }
        if (!("zhounan").equals(name)) {
            result = ResultUtils.error(ExceptionEnum.USER_NOT_FOUND);
        }

        return result;
    }


    class UserInfo {
        private String name;
        private String password;

        public String getName() {
            return name;
        }

        private void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
