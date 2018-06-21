package com.zn.designpattern.behavior.commond.audioplayer;

import java.awt.*;

/**
 * 请求者角色 由键盘扮演
 * Created by zhoun on 2018/6/21.
 **/
public class KeyPad {

    private Commond playCommond;
    private Commond rewindCommond;
    private Commond stopCommond;


    public void setPlayCommond(Commond playCommond) {
        this.playCommond = playCommond;
    }


    public void setRewindCommond(Commond rewindCommond) {
        this.rewindCommond = rewindCommond;
    }


    public void setStopCommond(Commond stopCommond) {
        this.stopCommond = stopCommond;
    }

    public void play(){
        playCommond.execute();
    }

    public void stop(){
        stopCommond.execute();
    }

    public void rewind(){
        rewindCommond.execute();
    }

}
