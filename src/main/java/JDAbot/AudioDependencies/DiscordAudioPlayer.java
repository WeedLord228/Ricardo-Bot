package JDAbot.AudioDependencies;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.managers.AudioManager;

public class DiscordAudioPlayer
{
    public AudioPlayerManager playerManager;
    public AudioPlayer player;
    public TrackScheduler scheduler;
    public Guild guild;
    public AudioManager audioManager;

    //В этом классе проходит работа с плеером и очередью треков.

    public DiscordAudioPlayer(Guild guild, AudioSourceManager[] sourceManagers)
    {
        this.playerManager = new DefaultAudioPlayerManager();
        this.guild = guild;
        this.player = playerManager.createPlayer();
        this.scheduler = new TrackScheduler(player);
        this.audioManager = guild.getAudioManager();
        player.addListener(scheduler);
        audioManager.setSendingHandler(new MySendHandler(player));
        for (int i =0;i < sourceManagers.length; i++)
            playerManager.registerSourceManager(sourceManagers[i]);
        //Пока у нас только один sendHandler мы не хотим задавать его отдельно
    }

    public void openAudioConnection(VoiceChannel voiceChannel)
    {
        audioManager.openAudioConnection(voiceChannel);
    }
}


