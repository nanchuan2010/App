package com.example.lzw.myapp.Broadcast;

import com.example.lzw.myapp.Utils;

/**
 * Created by LZW on 2017/06/01.
 */
public class Test60SecBCR extends ALongRunningReceiver {
    @Override
    public Class getLRSClass() {
        Utils.logThreadSignature("Test60SecBCR");
        return Test60SecBCRService.class;
    }
}
