package com.bigdata.ssh.test;

import com.sniper.bigdata.ssh.GanymedUtils;
import com.sniper.bigdata.threads.BufferReaders;
import com.sniper.bigdata.threads.ExecuteThread;
import org.junit.Test;

/**
 * Created by Administrator on 2016/3/15.
 */

public class GanymedTest {

    @Test
    public void testConnection() {
        GanymedUtils ganymedUtils=new GanymedUtils();
        ganymedUtils.closeConnection();
        ganymedUtils.openConnection("192.168.1.108", "sniper", "1234");
    }

    @Test
    public void testExecuteCmd() throws Exception{

        /*GanymedUtils.executeShellCmd("192.168.1.107", "hadoop", "hadoop", "stop-all.sh");*/

        /*ExecuteThread executeThread=new ExecuteThread();
        executeThread.setHost("192.168.1.107");
        executeThread.setUsername("hadoop");
        executeThread.setPassword("hadoop");
        executeThread.setCmd("start-all.sh");
        executeThread.setNotifyObj(this);*/
      /*  //

        executeThread.start();
        //等待生产者准备完毕
        synchronized (this){
            wait();
            String line=null;
            while ( (line= BufferReaders.stdout.readLine())!=null)
                System.out.println("STDOUT:"+line);
            //己方消费完毕，通知对方关闭资源
            synchronized (executeThread){
                executeThread.notify();
            }
            executeThread.join();
        }*/
    }
    @Test
    public void Comprehent(){
        GanymedUtils ganymedUtils=new GanymedUtils();
        ganymedUtils.setPassword("hadoop");
        ganymedUtils.setHost("192.168.1.105");
        ganymedUtils.setUsername("hadoop");


        StringBuffer cmds=new StringBuffer();

        //清空临时转存的数据集和结果集
        cmds.append("rm -rf ~/Documents/tmp/data/*;");
        cmds.append("rm -rf ~/Documents/tmp/result/*;");

        //清空hadoop文件系统中的数据集和结果集
        cmds.append("hadoop fs -rmr /user/hadoop/testdata/*;");
        cmds.append("hadoop fs -rmr /user/hadoop/output/*");
        ganymedUtils.setCmd(cmds.toString());
        ganymedUtils.executeOnly();
    }

    @Test
    public void testUpload() {
        GanymedUtils ganymedUtils=new GanymedUtils();
        ganymedUtils.setPassword("hadoop");
        ganymedUtils.setHost("192.168.1.105");
        ganymedUtils.setUsername("hadoop");
        /*ganymedUtils.uploadFilesToRemote(new String[]{"D://BugReport.txt"},"~/Documents/tmp/result");*/
        ganymedUtils.uploadFilesToRemote(new String[]{"D://BugReport.txt"},"~/Documents/tmp/data");

        //再将文件从hadoop服务器本地上传到Hadoop文件系统中
        ganymedUtils.setCmd("hadoop fs -put ~/Documents/tmp/data/* /user/hadoop/testdata/");
        ganymedUtils.executeOnly();
    }
    @Test
    public void testDownload(){
        GanymedUtils ganymedUtils=new GanymedUtils();
        ganymedUtils.setPassword("hadoop");
        ganymedUtils.setHost("192.168.1.105");
        ganymedUtils.setUsername("hadoop");
        ganymedUtils.downloadFileFromRemote(new String[]{"~/Documents/res_cluster_1.txt"},"D:\\");
    }
}
