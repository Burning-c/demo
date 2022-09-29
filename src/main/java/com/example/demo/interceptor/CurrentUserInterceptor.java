package com.example.demo.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import com.example.demo.exception.MyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class CurrentUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        String token = StrUtil.removePrefixIgnoreCase(request.getHeader(Header.AUTHORIZATION.getValue()), "bearer ");
        if (StrUtil.isEmpty(token)) {
            response.setStatus(401);
            log.error("path:{}, token校验失败", request.getRequestURI());
            throw new MyException("no right access");
        }
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
