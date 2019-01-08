package com.dragon.media.common.utils;

import com.dragon.media.resourceinfo.entity.FileInfo;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.jaudiotagger.audio.mp3.MP3File;

import java.io.*;
import java.util.*;

/**
 * @program admin
 * @description: FTP 工具类
 * @author: Gaofei
 * @create: 2019/01/02 11:25
 */

public class FTPUtils {

    /**
     * FTP地址
     **/
    private static final String FTP_ADDRESS = "59.151.51.172";

    /**
     * FTP端口
     **/
    private static final int FTP_PORT = 21;

    /**
     * FTP用户名
     **/
    private static final String FTP_USERNAME = "uploadmedia";

    /**
     * FTP密码
     **/
    private static final String FTP_PASSWORD = "uploadmedia123";

    /**
     * FTP基础目录
     **/
    private static final String BASE_PATH = "/2018-11";

    /**
     * 本地字符编码
     **/
    private static String localCharset = "GBK";

    /**
     * FTP协议里面，规定文件名编码为iso-8859-1
     **/
    private static String serverCharset = "ISO-8859-1";

    /**
     * UTF-8字符编码
     **/
    private static final String CHARSET_UTF8 = "UTF-8";

    /**
     * OPTS UTF8字符串常量
     **/
    private static final String OPTS_UTF8 = "OPTS UTF8";

    /**
     * 设置缓冲区大小4M
     **/
    private static final int BUFFER_SIZE = 1024 * 1024 * 4;

    /**
     * FTPClient对象
     **/
    private static FTPClient ftpClient = null;

    /**
     * Description: 向FTP服务器上传文件
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input 输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(BASE_PATH + filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = BASE_PATH;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            if (!ftp.storeFile(filename, input)) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * Description: 从FTP服务器下载文件
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName 要下载的文件名
     * @param localPath 下载后保存到本地的路径
     * @return
     */
    public static boolean downloadFile(String host, int port, String username, String password, String remotePath,
                                       String fileName, String localPath) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, port);
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());

                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }

            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }



     /* 获取该目录下所有文件,以字节数组返回
      *
      * @param ftpPath FTP服务器上文件所在相对路径，例如：test/123
      * @return Map<String, Object> 其中key为文件名，value为字节数组对象
      */
    public static Map<String, byte[]> getFileByFTPPath(String ftpPath) {
        FTPClient ftp = new FTPClient();
        // 登录
        Map<String, byte[]> map = new HashMap<>();
        if (ftp != null) {
            try {
                int reply;
                ftp.connect(FTP_ADDRESS, FTP_PORT);
                // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
                ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
                reply = ftp.getReplyCode();
                if (!FTPReply.isPositiveCompletion(reply)) {
                    ftp.disconnect();
                    return new HashMap<>();
                }
                ftp.changeWorkingDirectory(BASE_PATH + ftpPath);
               // ftp.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
                String[] fs = ftp.listNames();

                // 判断该目录下是否有文件
                if (fs == null || fs.length == 0) {
                    System.out.println(BASE_PATH + ftpPath + "该目录下没有文件");
                    return map;
                }

                FTPFile[] files = null;
                files = ftp.listFiles();
                System.out.println("文件个数为： "+ files.length);
                for (FTPFile file:files) {
                   long size = file.getSize();
                   if(size<=0){
                       continue;
                   }
                   System.out.println("........文件大小为。。。。：  " + size);
                   MP3File mp3File = new MP3File();
                }



                for (String ff : fs) {
                    try (InputStream is = ftp.retrieveFileStream(ff)) {

                        String ftpName = new String(ff.getBytes(serverCharset), localCharset);
                        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[BUFFER_SIZE];
                        int readLength = 0;
                        while ((readLength = is.read(buffer, 0, BUFFER_SIZE)) > 0) {
                            byteStream.write(buffer, 0, readLength);
                        }
                        map.put(ftpName, byteStream.toByteArray());
                        ftp.completePendingCommand(); // 处理多个文件
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }  catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (ftp.isConnected()) {
                    try {
                        ftp.disconnect();
                    } catch (IOException ioe) {
                    }
                }
            }
        }
        return map;
    }

    /**
     * 下载FTP目录下的文件到本地临时目录
     * @param ftpPath
     * @param localPath
     * @return
     */

    public static List<FileInfo> getFileInfoByFTPPath(String ftpPath,String localPath){
        List<FileInfo> fileList = new ArrayList<>();
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return null;
            }
            ftp.changeWorkingDirectory(BASE_PATH + ftpPath);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {

                    FileInfo fileInfo = new FileInfo();
                    File localFile = new File(localPath  + ff.getName());
                    String fileName = localFile.getName();
                    Long fileSize = ff.getSize();
                    if(fileSize<=0){
                        continue;
                    }
                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    fileInfo.setFileName(fileName);
                    fileInfo.setFileSize(fileSize);
                    fileList.add(fileInfo);
                    is.close();
            }

            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return  fileList;
    }

    public static void main(String[] args) {
        try {
            FileInputStream in = new FileInputStream(new File("/Users/apple/Downloads/7330404e6723be9571a6a345fa4c20c6.jpg"));
            boolean flag = FTPUtils.uploadFile( "/images/album", "test.jpg", in);
            System.out.println(flag);

            Map resultMap = FTPUtils.getFileByFTPPath("/images/album/");
            Set<String> keySets = resultMap.keySet();
            for (String key:keySets
                 ) {
                System.out.println("文件名为 ：" + key);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

