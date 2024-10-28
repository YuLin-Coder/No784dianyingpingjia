package com.dao;

import com.entity.VotingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.VotingView;

/**
 * 投票 Dao 接口
 *
 * @author 
 * @since 2021-04-05
 */
public interface VotingDao extends BaseMapper<VotingEntity> {

   List<VotingView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
