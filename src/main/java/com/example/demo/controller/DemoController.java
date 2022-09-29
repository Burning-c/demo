package com.example.demo.controller;

import cn.hutool.http.Header;
import cn.hutool.json.JSONUtil;
import com.example.demo.annotation.RightCheck;
import static com.example.demo.dto.ResponseDTO.*;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.pojo.User;
import com.example.demo.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user-info")
@Api(tags = "用户信息接口")
public class DemoController {
    @Resource
    private IUserService userService;

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    @RightCheck(value = {"user"})
    public ResponseDTO<String> home(HttpServletRequest request) {
       String authorization = Header.AUTHORIZATION.getValue();
        return ResponseDTO.success(request.getHeader(authorization));
    }

    @RequestMapping(value = "/addInfo", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户信息", notes = "用户信息添加接口")
    public ResponseDTO<String> info(@RequestBody @Validated User user) {
        log.info("入参：{}", JSONUtil.toJsonStr(user));
        return success();
    }

}
