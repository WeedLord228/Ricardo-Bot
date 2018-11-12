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
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.AudioManager;


public class MyListener extends ListenerAdapter {

    private BotPlayer botPlayer = new BotPlayer();

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
            String[] args = event.getMessage().getContentRaw().split(" ");

            if (args[0].equals("$play")) {
                String youTubeId = args[1].split("v=")[1].substring(0, 11);

                Guild guild = event.getGuild();
                //Получаем текущую гильдию

                VoiceChannel voiceChannel = event.getMember().getVoiceState().getChannel();
                //Получаем голосовой канал пользователя

                AudioManager manager = guild.getAudioManager();

//                final AudioPlayerManager playerManager = botPlayer.playerManager;
                AudioSourceManagers.registerRemoteSources(botPlayer.playerManager);

//                final AudioPlayer player =  botPlayer.player;

                YoutubeAudioSourceManager youtubeASM = new YoutubeAudioSourceManager();

                manager.setSendingHandler(new MySendHandler(botPlayer.player));
                manager.openAudioConnection(voiceChannel);

                botPlayer.playerManager.registerSourceManager(youtubeASM);

                botPlayer.playerManager.loadItem(youTubeId, new AudioLoadResultHandler() {

                    public void trackLoaded(AudioTrack audioTrack) {
                        botPlayer.scheduler.queue(audioTrack);
                    }

                    public void playlistLoaded(AudioPlaylist audioPlaylist) {

                    }

                    public void noMatches() {

                    }

                    public void loadFailed(FriendlyException e) {

                    }


                });

                event.getChannel().sendMessage("Сос" + botPlayer.scheduler.queue.size()).queue();
            }

        }
    }
}