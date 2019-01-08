package com.dragon.media.mediainfo.web;

import com.dragon.media.common.constant.MediaConstant;
import com.dragon.media.common.utils.FTPUtils;
import com.dragon.media.common.utils.FileUtil;
import com.dragon.media.common.webservice.FileRepositoryService;
import com.dragon.media.common.webservice.FileRepositoryServiceLocator;
import com.dragon.media.mediainfo.dao.MediainfoDao;
import com.dragon.media.mediainfo.entity.Mediainfo;
import com.dragon.media.mediainfo.service.MediainfoService;
import com.dragon.media.resourceinfo.entity.FileInfo;
import com.dragon.media.resourceinfo.entity.Mediafileinfo;
import com.dragon.media.resourceinfo.service.MediafileinfoService;
import com.ibeetl.admin.core.web.JsonResult;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.message.PrefixedQName;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import javax.xml.namespace.QName;
import org.apache.axis.message.PrefixedQName;
import org.apache.axis.message.SOAPHeaderElement;
import javax.xml.soap.Name;

/**
 * @program admin
 * @description: 文件上传工具类
 * @author: Gaofei
 * @create: 2019/01/02 14:22
 */
@CrossOrigin
@Controller
public class FileUploadController {
    @Autowired
    private MediafileinfoService resourceService;
    @Autowired private MediainfoService mediainfoService;
    @Autowired private MediainfoDao mediainfoDao;

    /**
     * 处理图片上传文
     */
    @RequestMapping(value="/uploadimg", method = RequestMethod.POST)
    @ResponseBody
    public  JsonResult<HashMap> uploadImg(@RequestParam("file") MultipartFile file,
                                          HttpServletRequest request) {

        //文件存放路径
        String path = this.getClass().getClassLoader().getResource("").getPath() + "public" + File.separator;
        System.out.println(path);
        String contentType = file.getContentType();   //图片文件类型
        String fileName = file.getOriginalFilename();  //图片名字


        //调用文件处理类FileUtil，处理文件，将文件写入指定位置
        try {
            FileUtil.uploadFile(file.getBytes(), path, fileName);
            //把制定文件放到FTP 服务器指定地址
            FileInputStream in = new FileInputStream(new File(path + File.separator + fileName));
            boolean flag = FTPUtils.uploadFile( MediaConstant.IMAGE_BASE_URL, fileName, in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap mapResult = new HashMap();
        String resultpath = MediaConstant.URL_PREFIX + MediaConstant.IMAGE_BASE_URL + File.separator +fileName;
        //mapResult.put("src","http://192.168.3.131:8080/"+fileName);
        mapResult.put("src",resultpath);
        mapResult.put("title","");
        // 返回图片的存放路径
        return JsonResult.success(mapResult);
    }

    /**
     * FTP 上传
     * @param path FTP 路径
     * @param request
     * @return
     */
    @RequestMapping(value="/ftpUpload", method = RequestMethod.POST)
    @ResponseBody
    public  JsonResult uploadByFTP(@RequestParam("path") String path,
                                            @RequestParam("mediaguid") String mediaguid,
                                          HttpServletRequest rquest) {

        //文件存放路径
        String localPath = this.getClass().getClassLoader().getResource("").getPath() + "public" + File.separator;
        //获取当前FStringTP路径下的所有文件
       // Map keyMap = FTPUtils.getFileByFTPPath(path);
        List<FileInfo> fileInfoList = FTPUtils.getFileInfoByFTPPath(path,localPath);
        //Set<String> keySets = keyMap.keySet();
        try{

            Integer totalDuration = 0;
            for (FileInfo fileInfo:fileInfoList) {
                Integer duration = 0;
                Mediafileinfo mediafileinfo = new Mediafileinfo();
                mediafileinfo.setMediaguid(mediaguid);
                mediafileinfo.setCreatedate(new Date());
                mediafileinfo.setUpdatedate(new Date());
                mediafileinfo.setDelFlag(0);
                String fileName = fileInfo.getFileName();
                mediafileinfo.setFiletitle(fileName);
                mediafileinfo.setFilesize(fileInfo.getFileSize());
                mediafileinfo.setLinkurl(MediaConstant.URL_PREFIX + path + "/"+fileName);
                //资源状态 0 未上线  1 已上线 2 已下线 3 到期停更
                mediafileinfo.setState(0);

                //获取文件时长
                MP3File mp3File = new MP3File(localPath + fileName);
                MP3AudioHeader audioHeader = (MP3AudioHeader)mp3File.getAudioHeader();

                String strLen = audioHeader.getTrackLengthAsString();
                //System.out.println(strLen);

                int intLen = audioHeader.getTrackLength();
                duration = intLen;
                mediafileinfo.setDuration(duration);
                resourceService.save(mediafileinfo);
                FileUtil.delFile(localPath+fileName);
            }
        }catch(Exception e){
            e.printStackTrace();
        }


        // 更新专辑的更新数量
        Mediainfo editmediainfo = new Mediainfo();
        Mediainfo mediainfo = mediainfoService.queryMediaById(mediaguid);
        int fileCount = mediainfo.getFilecount()+fileInfoList.size();
        editmediainfo.setFilecount(fileCount);

        //当音频更新数量和总数一致时状态改为已完成更新状态
        if(fileCount >= mediainfo.getTotalCount()){
            editmediainfo.setResourcestate(1);
        }
        editmediainfo.setUpdatedate(new Date());
        editmediainfo.setMediaguid(mediaguid);
        mediainfoService.updateTemplate(editmediainfo);
        return new JsonResult().success();
    }

    /**
     * 处理音频上传文件
     */
    @RequestMapping(value="/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public  JsonResult uploadFile(@RequestParam("file") MultipartFile file,
                                  @RequestParam("mediaguid") String mediaguid,
                                          HttpServletRequest request) {

        //文件存放路径
        String path = this.getClass().getClassLoader().getResource("").getPath() + "temp" + File.separator;
        String contentType = file.getContentType();   //图片文件类型
        String fileName = file.getOriginalFilename();  //图片名字
        Integer fileSize = 0;
        Integer duration = 0;

        //调用文件处理类FileUtil，处理文件，将文件写入指定位置
        try {
            FileUtil.uploadFile(file.getBytes(), path, fileName);
            //把制定文件放到FTP 服务器指定地址
            FileInputStream in = new FileInputStream(new File(path + File.separator + fileName));
            //获取文件大小
            fileSize = in.available();
            boolean flag = FTPUtils.uploadFile( MediaConstant.FILE_UPLOAD_ADDRESS, fileName, in);

            //获取文件时长
            MP3File mp3File = new MP3File(path + fileName);
            MP3AudioHeader audioHeader = (MP3AudioHeader)mp3File.getAudioHeader();

            String strLen = audioHeader.getTrackLengthAsString();
            //System.out.println(strLen);

            int intLen = audioHeader.getTrackLength();
            duration = intLen;
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //拼接外网链接
        String resultpath = MediaConstant.URL_PREFIX + MediaConstant.FILE_UPLOAD_ADDRESS + File.separator +fileName;

        Mediafileinfo mediafileinfo = new Mediafileinfo();
        mediafileinfo.setMediaguid(mediaguid);
        mediafileinfo.setCreatedate(new Date());
        mediafileinfo.setUpdatedate(new Date());
        mediafileinfo.setDelFlag(0);
        mediafileinfo.setFiletitle(fileName);
        mediafileinfo.setDuration(duration);
        mediafileinfo.setLinkurl(resultpath);
        mediafileinfo.setFilesize(fileSize.longValue());
        //资源状态 0 未上线  1 已上线 2 已下线 3 到期停更
        mediafileinfo.setState(0);
        resourceService.save(mediafileinfo);

        //更新专辑音频大小
        Mediainfo mediainfo = mediainfoService.queryMediaById(mediaguid);
        Mediainfo editInfo = new Mediainfo();
        editInfo.setFilesize(mediainfo.getFilesize()+fileSize);
        //更新专辑的音频数量
        int fileCount = mediainfo.getFilecount()+1;
        editInfo.setFilecount(fileCount);
        editInfo.setUpdatedate(new Date());
        //当音频更新数量和总数一致时状态改为已完成更新状态
        if(fileCount >= mediainfo.getTotalCount()){
            editInfo.setResourcestate(1);
        }
        editInfo.setMediaguid(mediaguid);
        mediainfoDao.updateTemplateById(editInfo);
        // 返回图片的存放路径
        return JsonResult.success();
    }






    public static void main(String[] args) {
      // System.out.println(System.getProperty("user.dir"));

        try {

     /*       String outPath = "";
            String filePath="/Users/apple/Downloads/7330404e6723be9571a6a345fa4c20c6.jpg";
            FileInputStream in = new FileInputStream(new File(filePath));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            while((count = in.read(buffer)) >= 0){
                baos.write(buffer, 0, count);
            }
            String uploadBuffer = new String(Base64.getEncoder().encodeToString(baos.toByteArray()));  //进行Base64编码


            String endpoint = "http://uploadfile.qikan.com.cn/FileRepositoryService.svc";
            String targetNameSpace = "http://tempuri.org/IFileRepositoryService/CreateImageFile";
            //直接引用远程的wsdl文件
            //以下都是套路
            List<Object> list = new ArrayList<Object>();
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setUsername("MediaICON");
            call.setPassword("MediaICON");

            call.setOperationName("CreateImageFile");//WSDL里面描述的接口名称

            call.addParameter("MediaICON/7330404e6723be9571a6a345fa4c20c6.jpg", XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);//接口的参数

            call.addParameter(uploadBuffer,XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);

            call.addParameter(outPath,XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);

            call.setUseSOAPAction(true);
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型
            call.setSOAPActionURI(targetNameSpace);

            *//*Name headerName = new PrefixedQName( new QName("http://tempuri.org/Imports", "AuthHeaderCS") );
            org.apache.axis.message.SOAPHeaderElement header = new SOAPHeaderElement(headerName);
            header.addChildElement("Username").setValue("MediaICON");
            header.addChildElement("Password").setValue("MediaICON");
            call.addHeader(header);*//*

            list.add("MediaICON/test/7330404e6723be9571a6a345fa4c20c6.jpg");
            list.add(uploadBuffer);
            list.add(outPath);
            String result = (String)call.invoke(list.toArray());
            //给方法传递参数，并且调用方法
            System.out.println("result is "+result);*/



            MP3File mp3File = new MP3File("/Users/apple/Downloads/11023.mp3");
            MP3AudioHeader audioHeader = (MP3AudioHeader)mp3File.getAudioHeader();

            String strLen = audioHeader.getTrackLengthAsString();
            System.out.println(strLen);

            int intLen = audioHeader.getTrackLength();
            System.out.println(intLen);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
