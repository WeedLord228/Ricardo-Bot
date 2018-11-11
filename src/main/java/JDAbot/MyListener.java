package JDAbot;


import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioTrack;

import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.AudioManager;


public class MyListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().startsWith("!"))
        //Мы хотим чтобы бот обрабатывал только команды
        {

            String[] args = event.getMessage().getContentRaw().split(" ");
            //Разделение входящего сообщения на массив аргументов,
            //предпологается что боту из входящих будут нужны только команды и их аргументы

            MessageChannel textChannel = event.getChannel();
            //Канал, из которго пришло сообщение

            textChannel.sendMessage(ChatOutputCommands.executeCommand(args)).queue();
            //Распознование и выполнение команды
        }

        if (event.getMessage().getContentRaw().startsWith("$")) {


            Guild guild = event.getGuild();
            //Получаем текущую гильдию

            VoiceChannel voiceChannel = event.getMember().getVoiceState().getChannel();
            //Получаем голосовой канал пользователя

            AudioManager manager = guild.getAudioManager();

            final AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
            AudioSourceManagers.registerRemoteSources(playerManager);

            final AudioPlayer player = playerManager.createPlayer();

            YoutubeAudioSourceManager youtubeASM = new YoutubeAudioSourceManager();

            manager.setSendingHandler(new MySendHandler(player));
            manager.openAudioConnection(voiceChannel);

            playerManager.registerSourceManager(youtubeASM);

            playerManager.loadItem("YOUTUBE_TRACK_ID_TO_LOAD", new AudioLoadResultHandler() {

                public void trackLoaded(AudioTrack audioTrack) {
                player.playTrack(audioTrack);
                }

                public void playlistLoaded(AudioPlaylist audioPlaylist) {

                }

                public void noMatches() {

                }

                public void loadFailed(FriendlyException e) {

                }
            });
        }
    }
}