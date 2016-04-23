package com.bigdata.fileIO.test;

import com.sniper.bigdata.io.FileOperateUtil;
import org.junit.Test;
import org.springframework.jdbc.support.incrementer.SybaseAnywhereMaxValueIncrementer;

/**
 * Created by Administrator on 2016/3/20.
 */
public class FileIO {
    @Test
    public void testRename(){
        System.out.println(FileOperateUtil.rename("≤‚ ‘.jsp"));
    }
}
