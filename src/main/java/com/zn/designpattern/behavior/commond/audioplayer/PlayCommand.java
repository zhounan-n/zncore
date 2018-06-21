package com.zn.designpattern.behavior.commond.audioplayer;

/**
 * Created by zhoun on 2018/6/21.
 **/
public class PlayCommand implements Commond {

    private AudioPlayer audioPlayer;

    public PlayCommand(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void execute() {
        audioPlayer.play();
    }


}
