package JDAbot;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;

public class BotPlayer
{
    public final AudioPlayerManager playerManager;
    public final AudioPlayer player;
    public final TrackScheduler scheduler;

    //В этом классе проходит работа с плеером и очередью треков.
    public BotPlayer()
    {
        this.playerManager = new DefaultAudioPlayerManager();
        this.player = playerManager.createPlayer();
        this.scheduler = new TrackScheduler(player);
        player.addListener(scheduler);
    }
}
