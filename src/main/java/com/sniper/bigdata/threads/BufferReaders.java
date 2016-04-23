package com.sniper.bigdata.threads;

import java.io.BufferedReader;
import java.io.OutputStreamWriter;

/**
 * Created by Administrator on 2016/4/1.
 */
public class BufferReaders {
    public static boolean already=false;
    public static boolean done=false;
    public static BufferedReader stdout=null;
    public static BufferedReader stderr=null;
    public static OutputStreamWriter stdin=null;
    public static void begin(){
        already=true;
        done=false;
    }
    public static void end(){
        done=true;
        already=false;
    }
}
