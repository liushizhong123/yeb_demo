package com.yeb.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeb.server.pojo.Admin;
import com.yeb.server.vo.RespBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liushizhong
 * @since 2021-09-09
 */
public interface IAdminService extends IService<Admin> {

    /**
     *登陆后返回 token
     * @param username
     * @param password
     * @param request
     * @return
     */
    RespBean login(String username, String password, HttpServletRequest request);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    Admin getAdminByUsername(String username);
}
