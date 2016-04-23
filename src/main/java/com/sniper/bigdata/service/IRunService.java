package com.sniper.bigdata.service;

import com.sniper.bigdata.threads.ExecuteRunnable;
import com.sniper.bigdata.threads.ExecuteThread;

/**
 * Created by Administrator on 2016/4/2.
 */
public interface IRunService {
    public void setCmd(String cmd);
    public void startHadoop();
    public void setExecuteRunnable(ExecuteRunnable executeRunnable);
    public void run();
    public void release();
    public void stopHadoop();
    public void cleanInput();
    public void uploadFile(String localName,String storePath);
    public void downloadFile(String storePath);
    public void run(String storeName,String contextPath);
}
