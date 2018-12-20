package com.dragon.media.category.web;

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

import com.dragon.media.resourceinfo.web.query.MediafileinfoQuery;
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
import com.dragon.media.category.entity.*;
import com.dragon.media.category.service.*;
import com.dragon.media.category.web.query.*;

/**
 * Mediacategory 接口
 */
@Controller
public class MediacategoryController{

    private final Log log = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/category/mediacategory";


    @Autowired private MediacategoryService mediacategoryService;
    
    @Autowired
    FileService fileService;
    /* 页面 */

    @GetMapping(MODEL + "/index.do")
    @Function("category.mediacategory.query")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/category/mediacategory/index.html") ;
        view.addObject("search", MediacategoryQuery.class.getName());
        return view;
    }

    @GetMapping(MODEL + "/edit.do")
    @Function("category.mediacategory.edit")
    @ResponseBody
    public ModelAndView edit(Integer id) {
        ModelAndView view = new ModelAndView("/category/mediacategory/edit.html");
        Mediacategory mediacategory = mediacategoryService.queryById(id);
        view.addObject("mediacategory", mediacategory);
        return view;
    }

    @GetMapping(MODEL + "/add.do")
    @Function("category.mediacategory.add")
    @ResponseBody
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/category/mediacategory/add.html");
        return view;
    }

    /* ajax json */

    @PostMapping(MODEL + "/list.json")
    @Function("category.mediacategory.query")
    @ResponseBody
    public JsonResult<PageQuery> list(MediacategoryQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        mediacategoryService.queryByCondition(page);
        return JsonResult.success(page);
    }


    @PostMapping(MODEL + "/listCategory.json")
    @Function("category.mediacategory.query")
    @ResponseBody
    public JsonResult<PageQuery> listCategory(MediacategoryQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        mediacategoryService.queryCategory(page);
        return JsonResult.success(page);
    }
    @PostMapping(MODEL + "/add.json")
    @Function("category.mediacategory.add")
    @ResponseBody
    public JsonResult add(@Validated(ValidateConfig.ADD.class)Mediacategory mediacategory)
    {
        mediacategoryService.save(mediacategory);
        return new JsonResult().success();
    }

    @PostMapping(MODEL + "/edit.json")
    @Function("category.mediacategory.edit")
    @ResponseBody
    public JsonResult<String> update(@Validated(ValidateConfig.UPDATE.class)  Mediacategory mediacategory) {
        boolean success = mediacategoryService.update(mediacategory);
        if (success) {
            return new JsonResult().success();
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }


   
    @GetMapping(MODEL + "/view.json")
    @Function("category.mediacategory.query")
    @ResponseBody
    public JsonResult<Mediacategory>queryInfo(Integer id) {
        Mediacategory mediacategory = mediacategoryService.queryById( id);
        return  JsonResult.success(mediacategory);
    }

    @PostMapping(MODEL + "/delete.json")
    @Function("category.mediacategory.delete")
    @ResponseBody
    public JsonResult delete(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        List<Long> idList = ConvertUtil.str2longs(ids);
        mediacategoryService.batchDelMediacategory(idList);
        return new JsonResult().success();
    }


    /**
     * 查询当前分类code下的子分类
     * @param condtion
     * @param categoryCode
     * @return
     */
    @PostMapping(MODEL + "/listByCode.json")
    @Function("category.mediacategory.query")
    @ResponseBody
    public JsonResult<PageQuery> listByCode(MediafileinfoQuery condtion,String categoryCode)
    {
        PageQuery page = condtion.getPageQuery();
        page.setPara("parentcategorycode",categoryCode);
        mediacategoryService.queryByCondition(page);
        return JsonResult.success(page);
    }


    /**
     * 给专辑添加分类
     * @param mediaGuid 专辑ID
     * @param  categoryList 分类ID集合
     * @return
     */
    @PostMapping(MODEL + "/addCategory.json")
    @Function("category.mediacategory.add")
    @ResponseBody
    public JsonResult addCategorys(@RequestParam(name = "mediaGuid") String mediaGuid,
                                   @RequestParam(name = "categoryList") List<String> categoryList) {

        mediacategoryService.addCategorys(mediaGuid,categoryList);
        return new JsonResult().success();
    }

    /**
     * 给专辑添加分类（先删除现有的的分类，然后添加新的分类）
     * @param mediaGuid 专辑ID
     * @param  categoryList 分类ID集合
     * @return
     */
    @PostMapping(MODEL + "/addMediaCategorys.json")
    @Function("category.mediacategory.add")
    @ResponseBody
    public JsonResult addMediaCategorys(@RequestParam(name = "mediaGuid") String mediaGuid,
                                   @RequestParam(name = "categoryList") List<String> categoryList) {

        //删除
        mediacategoryService.deleteCategoryDetail(mediaGuid);
        //添加
        mediacategoryService.addCategorys(mediaGuid,categoryList);
        return new JsonResult().success();
    }

}
