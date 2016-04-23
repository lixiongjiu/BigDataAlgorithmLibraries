package com.sniper.bigdata.threads;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.sniper.bigdata.ssh.GanymedUtils;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by Administrator on 2016/4/1.
 */
public class ExecuteThread extends Thread {
    private GanymedUtils ganymedUtils=null;

    public void setCmd(String cmd) {
        ganymedUtils.setCmd(cmd);
    }

    public void setGanymedUtils(GanymedUtils ganymedUtils) {
        this.ganymedUtils = ganymedUtils;
    }

    @Override
    public void run() {
        ganymedUtils.executeShellCmd();
    }
}
