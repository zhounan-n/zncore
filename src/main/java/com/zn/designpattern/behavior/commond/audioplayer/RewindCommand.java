package com.zn.designpattern.behavior.commond.audioplayer;

/**
 * Created by zhoun on 2018/6/21.
 **/
public class RewindCommand implements Commond {

    private AudioPlayer audioPlayer;

    public RewindCommand(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void execute() {
        audioPlayer.rewind();
    }

}
