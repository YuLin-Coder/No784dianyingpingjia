package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.VotingEntity;
import com.service.VotingService;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.DianyingEntity;

import com.service.DianyingService;
import com.entity.view.DianyingView;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 电影
 * 后端接口
 * @author
 * @email
 * @date 2021-04-05
*/
@RestController
@Controller
@RequestMapping("/dianying")
public class DianyingController {
    private static final Logger logger = LoggerFactory.getLogger(DianyingController.class);

    @Autowired
    private DianyingService dianyingService;

    @Autowired
    private VotingService votingService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;


    //级联表service


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        params.put("orderBy","id");
        PageUtils page = dianyingService.queryPage(params);

        //字典表数据转换
        List<DianyingView> list =(List<DianyingView>)page.getList();
        for(DianyingView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        DianyingEntity dianying = dianyingService.selectById(id);
        if(dianying !=null){
            //entity转view
            DianyingView view = new DianyingView();
            BeanUtils.copyProperties( dianying , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody DianyingEntity dianying, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,dianying:{}",this.getClass().getName(),dianying.toString());
        Wrapper<DianyingEntity> queryWrapper = new EntityWrapper<DianyingEntity>()
            .eq("dianying_name", dianying.getDianyingName())
            .eq("dianying_zhuyan", dianying.getDianyingZhuyan())
            .eq("leix_types", dianying.getLeixTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DianyingEntity dianyingEntity = dianyingService.selectOne(queryWrapper);
        if(dianyingEntity==null){
            dianying.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      dianying.set
        //  }
            dianyingService.insert(dianying);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody DianyingEntity dianying, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,dianying:{}",this.getClass().getName(),dianying.toString());
        //根据字段查询是否有相同数据
        Wrapper<DianyingEntity> queryWrapper = new EntityWrapper<DianyingEntity>()
            .notIn("id",dianying.getId())
            .andNew()
            .eq("dianying_name", dianying.getDianyingName())
            .eq("dianying_zhuyan", dianying.getDianyingZhuyan())
            .eq("leix_types", dianying.getLeixTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DianyingEntity dianyingEntity = dianyingService.selectOne(queryWrapper);
        if("".equals(dianying.getYangbanPhoto()) || "null".equals(dianying.getYangbanPhoto())){
                dianying.setYangbanPhoto(null);
        }
        if(dianyingEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      dianying.set
            //  }
            dianyingService.updateById(dianying);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        dianyingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/voting")
    public R delete(Integer ids, HttpServletRequest request){
        DianyingEntity dianying = dianyingService.selectById(ids);
        if(dianying == null){
            R.error();
        }
        VotingEntity voting = new VotingEntity();
        voting.setDianyingId(ids);
        voting.setYonghuId((Integer) request.getSession().getAttribute("userId"));
        Wrapper<VotingEntity> queryWrapper = new EntityWrapper<VotingEntity>()
                .eq("yonghu_id", voting.getYonghuId())
                .eq("dianying_id", voting.getDianyingId())
                ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        VotingEntity votingEntity = votingService.selectOne(queryWrapper);
        if(votingEntity!=null){
            return R.error("你已经投过票了");
        }
        voting.setInsertTime(new Date());
        voting.setCreateTime(new Date());
        dianying.setVoting(dianying.getVoting()+1);
        boolean b = dianyingService.updateById(dianying);
        if(b){
            boolean insert = votingService.insert(voting);
            if(insert){
                return R.ok();
            }
        }
        return R.error();
    }



    /**
    * 前端列表
    */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = dianyingService.queryPage(params);

        //字典表数据转换
        List<DianyingView> list =(List<DianyingView>)page.getList();
        for(DianyingView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
    DianyingEntity dianying = dianyingService.selectById(id);
        if(dianying !=null){
            //entity转view
    DianyingView view = new DianyingView();
            BeanUtils.copyProperties( dianying , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody DianyingEntity dianying, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,dianying:{}",this.getClass().getName(),dianying.toString());
        Wrapper<DianyingEntity> queryWrapper = new EntityWrapper<DianyingEntity>()
            .eq("dianying_name", dianying.getDianyingName())
            .eq("dianying_zhuyan", dianying.getDianyingZhuyan())
            .eq("leix_types", dianying.getLeixTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
    DianyingEntity dianyingEntity = dianyingService.selectOne(queryWrapper);
        if(dianyingEntity==null){
            dianying.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      dianying.set
        //  }
        dianyingService.insert(dianying);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}

