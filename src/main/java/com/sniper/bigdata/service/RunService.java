package com.sniper.bigdata.service;

import com.sniper.bigdata.io.FileOperateUtil;
import com.sniper.bigdata.ssh.GanymedUtils;
import com.sniper.bigdata.threads.ExecuteRunnable;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Created by Administrator on 2016/4/1.
 */
public class RunService implements IRunService {
    private GanymedUtils ganymedUtils = null;
    private Logger logger = Logger.getLogger(RunService.class);
    private ExecuteRunnable executeRunnable = null;
    /*private boolean ON = false;*/
    private Thread runThread = null;

    public void setCmd(String cmd) {
        executeRunnable.setCmd(cmd);
    }

    public void setExecuteRunnable(ExecuteRunnable executeRunnable) {
        this.executeRunnable = executeRunnable;
    }

    public void setGanymedUtils(GanymedUtils ganymedUtils) {
        this.ganymedUtils = ganymedUtils;
    }

    public void run() {
        runThread = new Thread(executeRunnable);
        runThread.start();
    }

    public void run(String storeName, String contextPath) {
        //先清理数据集缓存
        cleanInput();
        //接着上传文件到hadoop服务器，再到hdfs
        uploadFile(storeName, contextPath);

        //然后运行算法

        //最后将结果从hdfs转存到hadoop服务器临时文件，最后下载到web服务器
        //       /* downloadFile(contextPath);*/

    }

    public void stopHadoop() {

        logger.info("关闭Hadoop...");
        executeRunnable.setCmd("stop-all.sh");
        runThread = new Thread(executeRunnable);
        runThread.start();
    }

    public void startHadoop() {

        logger.info("启动Hadoop...");
        executeRunnable.setCmd("start-all.sh");
        runThread = new Thread(executeRunnable);
        runThread.start();
    }

    public void release() {
        try {
            runThread.join();
            logger.info("线程" + runThread.getName() + "回收成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("线程" + runThread.getName() + "回收失败");
        }
    }

    //已测试
    public void cleanInput() {
        StringBuffer cmds = new StringBuffer();

        //清理临时文件
        cmds.append("rm -rf ~/Documents/tmp/data/*;");
        cmds.append("rm -rf ~/Documents/tmp/result/*;");

        //清理hdfs中的文件
        cmds.append("hadoop fs -rmr /user/hadoop/testdata/*;");
        cmds.append("hadoop fs -rmr /user/hadoop/output/*");
        ganymedUtils.setCmd(cmds.toString());
        ganymedUtils.executeOnly();
    }

    //已测试
    public void uploadFile(String localName, String storePath) {
        String localDir = storePath + FileOperateUtil.UPLOADDIR + localName;

        String[] localDirs = new String[]{localDir};

        //首先将数据从web服务器上传到hadoop服务器本地
        ganymedUtils.uploadFilesToRemote(localDirs, "~/Documents/tmp/data");

        //再将文件从hadoop服务器本地传进hdfs中
        ganymedUtils.setCmd("hadoop fs -put ~/Documents/tmp/data/* /user/hadoop/testdata/");
        ganymedUtils.executeOnly();

    }

    public void downloadFile(String storePath) {
        String downloadDir = storePath + FileOperateUtil.DOWNLOADDIR;

        //确保下载路径存在，不存在则创建
        File downloadDirFile = new File(downloadDir);
        if (!downloadDirFile.exists())
            downloadDirFile.mkdir();

        //先将文件从hdfs中下载到hadoop本地服务器中的临时文件
        ganymedUtils.setCmd("hadoop fs -get /user/hadoop/output/ ~/Document/tmp/result");
        ganymedUtils.executeOnly();

        //再从临时文件传到web服务器中
        //ganymedUtils.downloadFileFromRemote(new String[]{""}, "");
    }

}
