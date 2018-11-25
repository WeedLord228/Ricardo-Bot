package JDAbot.AudioDependencies;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.core.audio.AudioSendHandler;

public class MySendHandler implements AudioSendHandler {
    private final AudioPlayer audioPlayer;
    private AudioFrame lastFrame;

    public MySendHandler(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }


    public boolean canProvide() {
        lastFrame = audioPlayer.provide();
        return lastFrame != null;
    }


    public byte[] provide20MsAudio() {
        return lastFrame.getData();
    }

    public boolean isOpus() {
        return true;
    }

}

