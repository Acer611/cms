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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beetl.sql.core.engine.PageQuery;
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
        Mediainfo media = mediaService.queryById(mediaguid);
        view.addObject("media", media);
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
        boolean success = mediaService.update(media);
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
        List<Long> idList = ConvertUtil.str2longs(ids);
        mediaService.batchDelMediainfo(idList);
        return new JsonResult().success();
    }
    

}
