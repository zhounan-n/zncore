package com.zn.designpattern.behavior.commond.audioplayer;

/**
 * 接收者角色由录音机扮演
 * Created by zhoun on 2018/6/21.
 **/
public class AudioPlayer {

    public void play(){
        System.out.println("播放……");
    }

    public void stop(){
        System.out.println("停止……");
    }

    public void rewind(){
        System.out.println("倒带……");
    }

}
