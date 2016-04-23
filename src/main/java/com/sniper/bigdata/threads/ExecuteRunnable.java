package com.sniper.bigdata.threads;

import com.sniper.bigdata.ssh.GanymedUtils;

/**
 * Created by Administrator on 2016/4/3.
 */
public class ExecuteRunnable implements Runnable {
    private GanymedUtils ganymedUtils = null;

    public void setCmd(String cmd) {
        ganymedUtils.setCmd(cmd);
    }

    public void setGanymedUtils(GanymedUtils ganymedUtils) {
        this.ganymedUtils = ganymedUtils;
    }

    public void run() {
        ganymedUtils.executeShellCmd();
    }
}
