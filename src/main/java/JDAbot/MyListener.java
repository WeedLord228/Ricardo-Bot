package JDAbot;


import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
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
    //Нужно создать плеер заранне, чтобы при каждом событии он не сбрасывался
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

            final MessageChannel messageChannel = event.getChannel();
            Guild guild = event.getGuild();
            //Получаем текущую гильдию
            AudioManager manager = guild.getAudioManager();
            manager.setSendingHandler(new MySendHandler(botPlayer.player));

            if (args[0].equals("$play")) {
                try {
                    String youTubeId = args[1].split("v=")[1].substring(0, 11);
                }
                catch (Exception e)
                {
                    messageChannel.sendMessage("Неправильный формат id").queue();
                }

                String youTubeId = args[1].split("v=")[1].substring(0, 11);







                VoiceChannel voiceChannel = event.getMember().getVoiceState().getChannel();
                //Получаем голосовой канал пользователя



//                final AudioPlayerManager playerManager = botPlayer.playerManager;
                AudioSourceManagers.registerRemoteSources(botPlayer.playerManager);

//                final AudioPlayer player =  botPlayer.player;

                YoutubeAudioSourceManager youtubeASM = new YoutubeAudioSourceManager();


                manager.openAudioConnection(voiceChannel);
                //Подключение бота в голосовой канал

                botPlayer.playerManager.registerSourceManager(youtubeASM);
                //регистрация внешних источников треков

                botPlayer.playerManager.loadItem(youTubeId, new AudioLoadResultHandler() {

                    public void trackLoaded(AudioTrack audioTrack)
                    {
                        botPlayer.scheduler.queue(audioTrack);
                        messageChannel.sendMessage("Ваш трек в очереди на " +
                                (botPlayer.scheduler.queue.size() + 1)
                                + "-м месте.").queue();
                    }

                    public void playlistLoaded(AudioPlaylist audioPlaylist) {

                    }

                    public void noMatches() {
                        messageChannel.sendMessage("Трек не найден").queue();
                        return;
                    }

                    public void loadFailed(FriendlyException e) {
                        messageChannel.sendMessage("Не удалось загрузить").queue();
                        return;
                    }
                });


            }

            if (args[0].equals("$skip"))
            {
                botPlayer.scheduler.nextTrack();
            }

            if (args[0].equals("$disconnect"))
            {
            manager.closeAudioConnection();
            }

        }
    }
}