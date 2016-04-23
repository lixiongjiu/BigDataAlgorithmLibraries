package com.sniper.bigdata.ssh;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.sniper.bigdata.threads.BufferReaders;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URLDecoder;

/**
 * Created by Administrator on 2016/3/15.
 */
public class GanymedUtils {
    private Connection connection = null;
    private String username = null;
    private String host = null;
    private String password = null;
    private Logger logger = Logger.getLogger(GanymedUtils.class);
    private Session singleSession;
    private InputStream stdout = null;
    private InputStream stderr = null;
    private OutputStream stdin = null;
    private String cmd = null;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void openConnection() {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("Connecting to " + host + " with user: " + username + " and password: " + password);
            }
            if (connection == null || !connection.isAuthenticationComplete()) {
                connection = new Connection(host);
                connection.connect();
                boolean isAuthenticated = connection.authenticateWithPassword(username, password);
                if (!isAuthenticated)
                    throw new IOException("Authentication failed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Connection host " + host + " failed.");
        }
    }

    public void openConnection(
            String host,
            String username,
            String password) {
        this.host = host;
        this.username = username;
        this.password = password;
        openConnection();
    }

    public void openSession() {
        try {
            if (singleSession == null) {
                singleSession = connection.openSession();
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Open session failed.");
        }

    }

    public void closeConnection() {
        if (connection != null)
            connection.close();
        connection = null;
        logger.info("Connection to " + host + " already closed.");
    }

    public void closeSession() {
        if (singleSession != null) {
            singleSession.close();
            singleSession = null;
            logger.info("Session has been closed.");
        }
    }

    /* public String executeShellCmdInOneSession(String cmd){

     }*/
    public void closeStream() {
        try {
            stderr.close();
            /*stdin.close();*/
            stdout.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Close stream of Stdin,Stdout,Stderr failed.");
        }
    }

    public void executeOnly() {
        if (logger.isInfoEnabled())
            logger.info("Running SSH cmd [" + cmd + "]");

        if (logger.isInfoEnabled())
            try {
                //确保连接已建立
                openConnection(host, username, password);
                //确保会话已建立
                openSession();

                singleSession.execCommand("source /etc/profile;" + cmd);

            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Execute cmd [" + cmd + "] failed.");
            } finally {
                closeSession();
                closeConnection();
            }
    }

    public void executeShellCmd() {
        if (logger.isInfoEnabled())
            logger.info("Running SSH cmd [" + cmd + "]");

        if (logger.isInfoEnabled())
            try {
                //确保连接已建立
                openConnection(host, username, password);
                //确保会话已建立
                openSession();

                //通过会话执行命令
                singleSession.requestDumbPTY();

                //读取shell的stdout信息并转换为BufferedReader对象
                stdout = new StreamGobbler(singleSession.getStdout());
                stderr = new StreamGobbler(singleSession.getStderr());

                //消费者线程，必须等生产者-即自己准备好了，才告知消费者，让消费者读
                BufferReaders.stderr = new BufferedReader(new InputStreamReader(stderr));
                BufferReaders.stdout = new BufferedReader(new InputStreamReader(stdout));
                BufferReaders.begin();
                System.out.println("生产者已经通知消费者");
                singleSession.execCommand("source /etc/profile;" + cmd);
                System.out.println("已经执行命令");
                //等待消费者方读取完毕，才能关闭Buffered流
                while (BufferReaders.done == false) {
                    Thread.sleep(1000);
                }
                System.out.println("生产者已经接收到消费者通知，可以关闭");
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Execute cmd [" + cmd + "] failed.");
            } finally {
                closeStream();
                closeSession();
                closeConnection();
            }
    }

    public boolean uploadFilesToRemote(String[] localFiles, String remoteTargetDir) {
        openConnection();
        try {
            SCPClient scpClient = connection.createSCPClient();
            for (String localFile : localFiles) {
                File item = new File(localFile);
                if (item.isFile()) {
                    logger.info("upload file:" + localFile + " to host:" + getHost() + " which name:" + getUsername());
                    scpClient.put(URLDecoder.decode(localFile,"UTF-8"), remoteTargetDir, "0777");
                } else
                    logger.error("File:" + localFile + " is not file,can't be upload.");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }

    }

    public boolean downloadFileFromRemote(String[] remoteFiles, String localTargetDirectory) {
        for (String remoteFile : remoteFiles) {
            logger.info("Download file:" + remoteFile + " from remote host:" + getHost() + " which name:" + getUsername());
        }
        try {
            openConnection();
            SCPClient scpClient = new SCPClient(connection);
            scpClient.get(remoteFiles, localTargetDirectory);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
