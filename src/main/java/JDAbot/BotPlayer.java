package JDAbot;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.managers.AudioManager;

public class BotPlayer
{
    public final AudioPlayerManager playerManager;
    public final AudioPlayer player;
    public final TrackScheduler scheduler;


    public BotPlayer()
    {
        this.playerManager = new DefaultAudioPlayerManager();
        this.player = playerManager.createPlayer();
        this.scheduler = new TrackScheduler(player);
        player.addListener(scheduler);
    }
}
