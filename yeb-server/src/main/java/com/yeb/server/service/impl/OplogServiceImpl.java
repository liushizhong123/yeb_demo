package com.yeb.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeb.server.mapper.OplogMapper;
import com.yeb.server.pojo.Oplog;
import com.yeb.server.service.IOplogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liushizhong
 * @since 2021-09-09
 */
@Service
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
