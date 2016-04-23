package com.sniper.bigdata.io;

import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/3/20.
 */
public class FileOperateUtil {
    public static final String REALNAME="realName";
    public static final String STORENAME="storeName";
    public static final String SIZE="size";
    public static final String SUFFIX="suffix";
    public static final String CONTENTTYPE="contentType";
    public static final String CREATETIME="createTime";
    public static final String UPLOADDIR="uploadDir/";
    public static final String DOWNLOADDIR="downloadDir";
    public static final String STATUS="status";
    public static final String STOREPATH="storePath";

    private static long maxSize=1024*1024*10;

    private static Logger logger=Logger.getLogger(FileOperateUtil.class);

    public static long getMaxSize() {
        return maxSize;
    }

    public static void setMaxSize(long maxSize) {
        FileOperateUtil.maxSize = maxSize;
    }

    public static String delFile(HttpServletRequest request,String storeName){
        //文件存放路径
        String uploadDir=request.getSession().getServletContext()
                .getRealPath("/")+
                FileOperateUtil.UPLOADDIR;
        File target=new File(uploadDir+storeName);
        if(target.exists()){
            if(target.isDirectory())
                return "该路径是目录，无法删除";
            else {
                target.delete();
                return "删除成功";
            }
        }
        else
            return "该路径不存在";

    }

    //重命名文件
    public static String rename(String name){
        Long now=Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        Long random=(long)(Math.random()*now);

        String fileName=now+""+random;

        return fileName+=name;
    }
    //命名压缩文件
    public static String zipName(String name){
        String prefix="";
        if(name.indexOf(".")!=-1)
            prefix=name.substring(0,name.lastIndexOf("."));
        else
            prefix=name;

        return prefix+".zip";
    }
    //文件上传
    public static List<Map<String,Object>> upload(HttpServletRequest request){
        List<Map<String,Object>> result=new ArrayList<Map<String, Object>>();

        //将request对象转换为多文件上传对象
        MultipartHttpServletRequest mRequest=(MultipartHttpServletRequest)request;
        //读取文件映射
        Map<String,MultipartFile> fileMap=mRequest.getFileMap();

        //文件存放路径
        String uploadDir=request.getSession().getServletContext()
                .getRealPath("/")+
                FileOperateUtil.UPLOADDIR;
       /* System.out.println(uploadDir);*/
        //打开文件对象，查看路径是否存在，不存在则创建文件存放路径
        File file=new File(uploadDir);
        if(!file.exists()){
            file.mkdir();
        }
        String fileName=null;
        int i=0;
        for(Iterator<Map.Entry<String,MultipartFile>> it=fileMap.entrySet().iterator();it.hasNext();i++){
            Map.Entry<String,MultipartFile> entry=it.next();
            MultipartFile mfile=entry.getValue();
            if(mfile.getSize()==0)
                continue;
            Map<String,Object> item=new HashMap<String, Object>();
            fileName=mfile.getOriginalFilename();
           /* System.out.println(fileName);*/
            //过滤掉过大尺寸的文件
            if(mfile.getSize()>maxSize)
            {
                item.put(STATUS,"failed due to too large");
                item.put(REALNAME,fileName);
                result.add(item);
                logger.error("File "+fileName+" upload failed due to too long.");
                continue;
            }
            //将文件重新命名，以防止命名冲突
            String storeName=rename(fileName);
            try {
                //写入文件
                BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(new File(uploadDir+storeName)));
                InputStream bis=mfile.getInputStream();
                FileCopyUtils.copy(bis,bos);
                item.put(STOREPATH,uploadDir);
                item.put(STATUS,"ok");
                item.put(REALNAME,fileName);
                item.put(STORENAME,storeName);
                item.put(CREATETIME,new Date());
                item.put(SIZE,mfile.getSize());
                result.add(item);
                logger.info("File "+fileName+" upload successfully.");

            }catch (IOException e){
                e.printStackTrace();
                logger.error("File "+fileName+" upload failed due to IO errors.");
                item.put(STATUS,"failed due to IO error");
                item.put(REALNAME,fileName);
                result.add(item);
                continue;
            }

        }
        return result;
    }

    //文件下载
    public static void download (HttpServletRequest request,HttpServletResponse response,String storeName,String realName,String contentType){
        try {
            response.setContentType("text/html;charset=UTF-8");
            //可在这里设置request.setCharacterEncoding("UTF-8");
            BufferedInputStream bis=null;
            BufferedOutputStream bos=null;

            String contextPath=request.getSession().getServletContext()
                    .getRealPath("/")
                    +FileOperateUtil.UPLOADDIR;

            String downloadPath=contextPath+storeName;
            File fileObejct=new File(downloadPath);
            long fileLength=fileObejct.length();

            response.setContentType(contentType);
            response.setHeader("Content-disposition","attachment;filename="+new String(realName.getBytes("utf-8"),"ISO8859-1"));
            response.setHeader("Content-Length",String.valueOf(fileLength));

            //写入
            bis=new BufferedInputStream(new FileInputStream(fileObejct));
            bos=new BufferedOutputStream(response.getOutputStream());
            byte[] buff=new byte[2048];
            int bytesReads;
            while ( (bytesReads=bis.read(buff,0,buff.length))==1 )
                bos.write(buff,0,bytesReads);

            //关闭IO流
            bis.close();
            bos.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
