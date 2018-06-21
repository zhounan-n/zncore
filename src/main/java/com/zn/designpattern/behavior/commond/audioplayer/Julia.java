package com.zn.designpattern.behavior.commond.audioplayer;

import java.awt.*;

/**
 * 客户端角色由julia扮演
 * Created by zhoun on 2018/6/21.
 **/
public class Julia {

    public static void main(String[] args) {

        //创建接收者对象
        AudioPlayer audioPlayer = new AudioPlayer();

        //创建命令对象
        Commond play = new PlayCommand(audioPlayer);
        Commond stop = new StopCommond(audioPlayer);
        Commond rewind = new RewindCommand(audioPlayer);

        KeyPad keyPad=new KeyPad();
        keyPad.setPlayCommond(play);
        keyPad.setRewindCommond(rewind);
        keyPad.setStopCommond(stop);

        keyPad.play();
        keyPad.stop();
        keyPad.rewind();


    }

}
