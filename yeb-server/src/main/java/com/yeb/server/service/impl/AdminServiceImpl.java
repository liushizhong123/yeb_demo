package com.yeb.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeb.server.config.security.JwtTokenUtil;
import com.yeb.server.mapper.AdminMapper;
import com.yeb.server.pojo.Admin;
import com.yeb.server.service.IAdminService;
import com.yeb.server.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liushizhong
 * @since 2021-09-09
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

     @Autowired
     private UserDetailsService userDetailsService;
     @Autowired
     private PasswordEncoder passwordEncoder;
     @Autowired
     private JwtTokenUtil jwtTokenUtil;
     @Value("${jwt.tokenHeader}")
     private String tokenHeader;
     @Autowired
     private AdminMapper adminMapper;

    /**
     * 登入后返回 token
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // userDetails 为空或者密码不正确
        if(userDetails == null||!passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("用户名或密码不正确!");
        }
        //账号被禁用
        if(!userDetails.isEnabled()){
            return RespBean.error("账号被禁用，请联系管理员!");
        }
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //生成 token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHeader",tokenHeader);
        return RespBean.success("登入成功",tokenMap);
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username)
        .eq("enabled",true));
    }
}
