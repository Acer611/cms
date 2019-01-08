package com.dragon.media.mediainfo.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.dragon.media.category.entity.Mediacategory;
import com.dragon.media.category.service.MediacategoryService;
import com.dragon.media.common.constant.MediaConstant;
import com.dragon.media.resourceinfo.service.MediafileinfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beetl.sql.core.engine.PageQuery;
import org.json.JSONArray;
import org.jxls.common.Context;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReadMessage;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ibeetl.admin.console.web.dto.DictExcelImportData;
import com.ibeetl.admin.console.web.query.UserQuery;
import com.ibeetl.admin.core.annotation.Function;
import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.entity.CoreDict;
import com.ibeetl.admin.core.entity.CoreUser;
import com.ibeetl.admin.core.file.FileItem;
import com.ibeetl.admin.core.file.FileService;
import com.ibeetl.admin.core.web.JsonResult;
import com.ibeetl.admin.core.util.*;
import com.dragon.media.mediainfo.entity.*;
import com.dragon.media.mediainfo.service.*;
import com.dragon.media.mediainfo.web.query.*;

/**
 * Mediainfo 接口
 */
@Controller
public class MediainfoController{

    private final Log log = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/media/media";


    @Autowired private MediainfoService mediaService;

    @Autowired private MediafileinfoService mediafileinfoService;

    @Autowired private MediacategoryService mediacategoryService;
    
    @Autowired
    FileService fileService;
    /* 页面 */

    @GetMapping(MODEL + "/index.do")
    @Function("media.media.query")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/media/media/index.html") ;
        view.addObject("search", MediainfoQuery.class.getName());
        return view;
    }

    @GetMapping(MODEL + "/edit.do")
    @Function("media.media.edit")
    @ResponseBody
    public ModelAndView edit(String mediaguid) {
        ModelAndView view = new ModelAndView("/media/media/edit.html");
        //Mediainfo media = mediaService.queryById(mediaguid);
        // 获取演播者信息
        Mediainfo media = mediaService.queryMediaById(mediaguid);
        //TODO 获取分类信息
        view.addObject("media", media);
        return view;
    }

    @GetMapping(MODEL + "/addCategory.do")
    @Function("media.media.edit")
    @ResponseBody
    public ModelAndView addCategory(String mediaguid) throws Exception {
        ModelAndView view = new ModelAndView("/media/media/addCategory.html");
        // 获取当前专辑已有的分类信息
        List<Mediacategory> hasCategoryList = mediacategoryService.queryCategoryByMediaGuid(mediaguid);
        List<Mediacategory>  mediacategorys = mediacategoryService.queryCategoryByCode(MediaConstant.CATEGORY_CODE);

        ObjectMapper mapper = new ObjectMapper();
        String categorys = mapper.writeValueAsString(mediacategorys);
        String hasCategorys = mapper.writeValueAsString(hasCategoryList);

        view.addObject("hasCategorys",hasCategorys);
        view.addObject("mediaguid", mediaguid);
        view.addObject("categoryList", categorys);
        return view;
    }

    @GetMapping(MODEL + "/addResource.do")
    @Function("media.media.add")
    @ResponseBody
    public ModelAndView addResource(String mediaguid){
        //TODO 资源页面添加
        ModelAndView view = new ModelAndView("/media/media/addResource.html");
        view.addObject("mediaguid", mediaguid);
        return view;
    }

    @GetMapping(MODEL + "/add.do")
    @Function("media.media.add")
    @ResponseBody
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/media/media/add.html");
        return view;
    }

    /* ajax json */

    @PostMapping(MODEL + "/list.json")
    @Function("media.media.query")
    @ResponseBody
    public JsonResult<PageQuery> list(MediainfoQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        mediaService.queryByCondition(page);
        return JsonResult.success(page);
    }

    @PostMapping(MODEL + "/add.json")
    @Function("media.media.add")
    @ResponseBody
    public JsonResult add(@Validated(ValidateConfig.ADD.class)Mediainfo media)
    {
        mediaService.save(media);
        return new JsonResult().success();
    }

    @PostMapping(MODEL + "/edit.json")
    @Function("media.media.edit")
    @ResponseBody
    public JsonResult<String> update(@Validated(ValidateConfig.UPDATE.class)  Mediainfo media) {
        boolean success = mediaService.updateT(media);
        if (success) {
            return new JsonResult().success();
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }


   
    @GetMapping(MODEL + "/view.json")
    @Function("media.media.query")
    @ResponseBody
    public JsonResult<Mediainfo>queryInfo(String mediaguid) {
        Mediainfo media = mediaService.queryById( mediaguid);
        return  JsonResult.success(media);
    }

    @PostMapping(MODEL + "/delete.json")
    @Function("media.media.delete")
    @ResponseBody
    public JsonResult delete(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        List<String> idList = ConvertUtil.converList(ids);
        mediaService.batchDelMediainfo(idList);
        return new JsonResult().success();
    }

    /**
     * 专辑批量上线
     * @param ids
     * @return
     */
    @PostMapping(MODEL + "/online.json")
    @Function("media.media.online")
    @ResponseBody
    public JsonResult online(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        List<String> idList = ConvertUtil.converList(ids);
        mediaService.batchOnlineMediainfo(idList);
        //  对专辑下资源的上线操作
        mediafileinfoService.onlineMediaFileInfoByMediaGuid(idList);
        return new JsonResult().success();
    }

    /**
     * 专辑批量下线
     * @param ids
     * @return
     */
    @PostMapping(MODEL + "/offline.json")
    @Function("media.media.online")
    @ResponseBody
    public JsonResult offline(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        List<String> idList = ConvertUtil.converList(ids);
        mediaService.batchOfflineMediainfo(idList);
        //  对专辑下资源的下线操作
        mediafileinfoService.offlineMediaFileInfoByMediaGuid(idList);
        return new JsonResult().success();
    }

    /**
     * 到期且定更
     * @param ids
     * @return
     */
    @PostMapping(MODEL + "/expire.json")
    @Function("media.media.online")
    @ResponseBody
    public JsonResult expire(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        List<String> idList = ConvertUtil.converList(ids);
        mediaService.expireMediainfo(idList);
        //  对专辑下资源的到期且停更操作
        mediafileinfoService.expireMediaFileInfoByMediaGuid(idList);
        return new JsonResult().success();
    }


    /**
     *修改授权状态
     * @param guid
     * @param authState
     * @return
     */
    @PostMapping(MODEL + "/changeAuthState")
    @Function("media.media.online")
    @ResponseBody
    public JsonResult changeAuthState(@RequestParam("guid")String guid,@RequestParam("authState") Integer authState){
        JsonResult result= new JsonResult();
        if(StringUtils.isEmpty(guid)||authState==null){
            result.setMsg("传参有误");
            result.setCode("400");
            return new JsonResult().fail();
        }

        if(authState==1){
            mediafileinfoService.changeAuthState(guid,authState,1);
            result.setMsg("启用资源的授权成功");
        }else if(authState==2){
            mediafileinfoService.changeAuthState(guid,authState,4);
            result.setMsg("停用资源的授权成功");
        }
        return new JsonResult().success();
    }


}
