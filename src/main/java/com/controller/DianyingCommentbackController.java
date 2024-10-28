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

import com.entity.DianyingCommentbackEntity;

import com.service.DianyingCommentbackService;
import com.entity.view.DianyingCommentbackView;
import com.service.DianyingService;
import com.entity.DianyingEntity;
import com.service.YonghuService;
import com.entity.YonghuEntity;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 电影评论
 * 后端接口
 * @author
 * @email
 * @date 2021-04-05
*/
@RestController
@Controller
@RequestMapping("/dianyingCommentback")
public class DianyingCommentbackController {
    private static final Logger logger = LoggerFactory.getLogger(DianyingCommentbackController.class);

    @Autowired
    private DianyingCommentbackService dianyingCommentbackService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;


    //级联表service
    @Autowired
    private DianyingService dianyingService;
    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
            logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
            if(params.get("commentbackTypes")!= null && params.get("commentbackTypes").equals("2")){
                params.put("dianyingId",params.get("dianyingId2"));
            }
            params.put("orderBy","id");
            PageUtils page = dianyingCommentbackService.queryPage(params);

            //字典表数据转换
            List<DianyingCommentbackView> list =(List<DianyingCommentbackView>)page.getList();
            for(DianyingCommentbackView c:list){
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
        DianyingCommentbackEntity dianyingCommentback = dianyingCommentbackService.selectById(id);
        if(dianyingCommentback !=null){
            //entity转view
            DianyingCommentbackView view = new DianyingCommentbackView();
            BeanUtils.copyProperties( dianyingCommentback , view );//把实体数据重构到view中

            //级联表
            DianyingEntity dianying = dianyingService.selectById(dianyingCommentback.getDianyingId());
            if(dianying != null){
                BeanUtils.copyProperties( dianying , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setDianyingId(dianying.getId());
            }
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(dianyingCommentback.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
            }
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
    public R save(@RequestBody DianyingCommentbackEntity dianyingCommentback, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,dianyingCommentback:{}",this.getClass().getName(),dianyingCommentback.toString());
        dianyingCommentback.setInsertTime(new Date());
        dianyingCommentback.setCreateTime(new Date());
        dianyingCommentbackService.insert(dianyingCommentback);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody DianyingCommentbackEntity dianyingCommentback, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,dianyingCommentback:{}",this.getClass().getName(),dianyingCommentback.toString());
        //根据字段查询是否有相同数据
        Wrapper<DianyingCommentbackEntity> queryWrapper = new EntityWrapper<DianyingCommentbackEntity>()
            .notIn("id",dianyingCommentback.getId())
            .andNew()
            .eq("dianying_id", dianyingCommentback.getDianyingId())
            .eq("yonghu_id", dianyingCommentback.getYonghuId())
            .eq("commentback_types", dianyingCommentback.getCommentbackTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DianyingCommentbackEntity dianyingCommentbackEntity = dianyingCommentbackService.selectOne(queryWrapper);
        if(dianyingCommentbackEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      dianyingCommentback.set
            //  }
            dianyingCommentbackService.updateById(dianyingCommentback);//根据id更新
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
        dianyingCommentbackService.deleteBatchIds(Arrays.asList(ids));
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
        PageUtils page = dianyingCommentbackService.queryPage(params);

        //字典表数据转换
        List<DianyingCommentbackView> list =(List<DianyingCommentbackView>)page.getList();
        for(DianyingCommentbackView c:list){
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
    DianyingCommentbackEntity dianyingCommentback = dianyingCommentbackService.selectById(id);
        if(dianyingCommentback !=null){
            //entity转view
    DianyingCommentbackView view = new DianyingCommentbackView();
            BeanUtils.copyProperties( dianyingCommentback , view );//把实体数据重构到view中

            //级联表
                DianyingEntity dianying = dianyingService.selectById(dianyingCommentback.getDianyingId());
            if(dianying != null){
                BeanUtils.copyProperties( dianying , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setDianyingId(dianying.getId());
            }
            //级联表
                YonghuEntity yonghu = yonghuService.selectById(dianyingCommentback.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
            }
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
    public R add(@RequestBody DianyingCommentbackEntity dianyingCommentback, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,dianyingCommentback:{}",this.getClass().getName(),dianyingCommentback.toString());
        Wrapper<DianyingCommentbackEntity> queryWrapper = new EntityWrapper<DianyingCommentbackEntity>()
            .eq("dianying_id", dianyingCommentback.getDianyingId())
            .eq("yonghu_id", dianyingCommentback.getYonghuId())
            .eq("commentback_types", dianyingCommentback.getCommentbackTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
    DianyingCommentbackEntity dianyingCommentbackEntity = dianyingCommentbackService.selectOne(queryWrapper);
        if(dianyingCommentbackEntity==null){
            dianyingCommentback.setInsertTime(new Date());
            dianyingCommentback.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      dianyingCommentback.set
        //  }
        dianyingCommentbackService.insert(dianyingCommentback);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}

