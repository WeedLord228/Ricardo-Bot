package JDAbot.Main;


import JDAbot.AudioDependencies.AudioCommandsHandler;
import JDAbot.AudioDependencies.DiscordAudioPlayer;
import JDAbot.AudioDependencies.MySendHandler;
import JDAbot.Logic.StringOutputCommands;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
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


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;

        if (event.getMessage().getContentRaw().startsWith("!") ||
                event.getMessage().getContentRaw().startsWith("$"))
        //Мы хотим чтобы бот обрабатывал только команды
        {

            String[] args = event.getMessage().getContentRaw().split(" ");
            //Разделение входящего сообщения на массив аргументов,
            //предпологается что боту из входящих будут нужны только команды и их аргументы

            Guild guild = event.getGuild();
//            Будет необходима для создания аудио соединения

            VoiceChannel voiceChannel = event.getMember().getVoiceState().getChannel();
            //Аудио канал, в котором сидит пользователь в данный момент, нужно чтобы подкючить бота в его канал

            final MessageChannel messageChannel = event.getChannel();
            //Канал, из которго пришло сообщение

            if (event.getMessage().getContentRaw().startsWith("$"))
            {

                YoutubeAudioSourceManager yotubeASM = new YoutubeAudioSourceManager();
                //Нужно создать плеер заранне, чтобы при каждом событии он не сбрасывался
                DiscordAudioPlayer discordAudioPlayer = new DiscordAudioPlayer(guild,
                        new AudioSourceManager[] {new YoutubeAudioSourceManager()});

                AudioCommandsHandler audioCommandsHandler = new AudioCommandsHandler(discordAudioPlayer);

                audioCommandsHandler.executeCommand(args,voiceChannel,messageChannel);

            }
            //Распознавание и выполнение аудио команды

            if (event.getMessage().getContentRaw().startsWith("!"))
            messageChannel.sendMessage(StringOutputCommands.executeCommand(args)).queue();
            //Распознование и выполнение команды для чата
        }
    }
}