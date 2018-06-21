package com.zn.designpattern.behavior.commond.audioplayer;

import javax.activation.CommandObject;

/**
 * Created by zhoun on 2018/6/21.
 **/
public class StopCommond implements Commond {

    private AudioPlayer audioPlayer;

    public StopCommond(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void execute() {
        audioPlayer.stop();
    }
}
