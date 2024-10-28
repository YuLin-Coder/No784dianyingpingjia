package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.VotingEntity;
import java.util.Map;

/**
 * 投票 服务类
 * @author 
 * @since 2021-04-05
 */
public interface VotingService extends IService<VotingEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}