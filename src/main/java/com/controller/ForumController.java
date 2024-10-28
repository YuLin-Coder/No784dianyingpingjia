package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
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

import com.entity.ForumEntity;

import com.service.ForumService;
import com.entity.view.ForumView;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 论坛
 * 后端接口
 * @author
 * @email
 * @date 2021-04-05
*/
@RestController
@Controller
@RequestMapping("/forum")
public class ForumController {
    private static final Logger logger = LoggerFactory.getLogger(ForumController.class);

    @Autowired
    private ForumService forumService;


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
        PageUtils page = forumService.queryPage(params);

        //字典表数据转换
        List<ForumView> list =(List<ForumView>)page.getList();
        for(ForumView c:list){
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
        ForumEntity forum = forumService.selectById(id);
        if(forum !=null){
            //entity转view
            ForumView view = new ForumView();
            BeanUtils.copyProperties( forum , view );//把实体数据重构到view中

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
    public R save(@RequestBody ForumEntity forum, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,forum:{}",this.getClass().getName(),forum.toString());
        Wrapper<ForumEntity> queryWrapper = new EntityWrapper<ForumEntity>()
            .eq("title", forum.getTitle())
            .eq("content", forum.getContent())
            .eq("parentid", forum.getParentid())
            .eq("userid", forum.getUserid())
            .eq("username", forum.getUsername())
            .eq("isdone", forum.getIsdone())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ForumEntity forumEntity = forumService.selectOne(queryWrapper);
        if(forumEntity==null){
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      forum.set
        //  }
            forumService.insert(forum);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ForumEntity forum, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,forum:{}",this.getClass().getName(),forum.toString());
        //根据字段查询是否有相同数据
        Wrapper<ForumEntity> queryWrapper = new EntityWrapper<ForumEntity>()
            .notIn("id",forum.getId())
            .andNew()
            .eq("title", forum.getTitle())
            .eq("content", forum.getContent())
            .eq("parentid", forum.getParentid())
            .eq("userid", forum.getUserid())
            .eq("username", forum.getUsername())
            .eq("isdone", forum.getIsdone())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ForumEntity forumEntity = forumService.selectOne(queryWrapper);
        if(forumEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      forum.set
            //  }
            forumService.updateById(forum);//根据id更新
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
        forumService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
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
        PageUtils page = forumService.queryPage(params);

        //字典表数据转换
        List<ForumView> list =(List<ForumView>)page.getList();
        for(ForumView c:list){
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
    ForumEntity forum = forumService.selectById(id);
        if(forum !=null){
            //entity转view
    ForumView view = new ForumView();
            BeanUtils.copyProperties( forum , view );//把实体数据重构到view中

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
    public R add(@RequestBody ForumEntity forum, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,forum:{}",this.getClass().getName(),forum.toString());
        Wrapper<ForumEntity> queryWrapper = new EntityWrapper<ForumEntity>()
            .eq("title", forum.getTitle())
            .eq("content", forum.getContent())
            .eq("parentid", forum.getParentid())
            .eq("userid", forum.getUserid())
            .eq("username", forum.getUsername())
            .eq("isdone", forum.getIsdone())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
    ForumEntity forumEntity = forumService.selectOne(queryWrapper);
        if(forumEntity==null){
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      forum.set
        //  }
        forumService.insert(forum);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}

