package com.yeb.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeb.server.vo.RespBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 未登录或者token失效时访问接口的自定义返回结果
 * AuthenticationEntryPoint: 遇到认证失败的异常处理接口
 * @author lsz on 2021/9/12
 */

@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        RespBean bean = RespBean.error("用户未登录,请登录!");
        bean.setCode(401);
        // 将 bean 对象转换为 json 字符串，并输出
        out.write(new ObjectMapper().writeValueAsString(bean));
        out.flush();
        out.close();
    }
}