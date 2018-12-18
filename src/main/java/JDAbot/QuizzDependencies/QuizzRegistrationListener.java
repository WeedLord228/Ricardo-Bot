package JDAbot.QuizzDependencies;

import JDAbot.AudioDependencies.AudioCommandsHandler;
import JDAbot.AudioDependencies.DiscordAudioPlayer;
import JDAbot.Logic.StringOutputCommands;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class QuizzRegistrationListener extends ListenerAdapter
{
    RegistrationCommandsHandler registrationCommandsHandler;


    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Quizz quizz = new Quizz();
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].startsWith("$"))
        {
        //Разделение входящего сообщения на массив аргументов,
        //предпологается что боту из входящих будут нужны только команды и их аргументы

        Guild guild = event.getGuild();
//            Будет необходима для создания аудио соединения

        final MessageChannel messageChannel = event.getChannel();
        //Канал, из которго пришло сообщение

        registrationCommandsHandler.executeCommand(args,messageChannel,quizz);
        //Распознование и выполнение команды для чата
    }
    }
}
