package JDAbot;



import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
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

            if(args[0].toLowerCase() == "!play")
            {
                Guild guild = event.getGuild();
                //Получаем текущую гильдию

                VoiceChannel voiceChannel = event.getMember().getVoiceState().getChannel();
                //Получаем голосовой канал пользователя

                AudioManager manager = guild.getAudioManager();

                AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
                AudioSourceManagers.registerRemoteSources(playerManager);

                AudioPlayer player =playerManager.createPlayer();

                manager.setSendingHandler(new MySendHandler(player));
                manager.openAudioConnection(voiceChannel);
            }
        }
    }
}