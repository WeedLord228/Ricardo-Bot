package JDAbot.Main;


import JDAbot.AudioDependencies.AudioCommandsHandler;
import JDAbot.AudioDependencies.DiscordAudioPlayer;
import JDAbot.Logic.StringOutputCommands;
import JDAbot.QuizzDependencies.GameState;
import JDAbot.QuizzDependencies.Quizz;
import JDAbot.QuizzDependencies.RegistrationCommandsHandler;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;


public class MyListener extends ListenerAdapter {

    Quizz quizz;

    public void setQuizz()
    {
        this.quizz = new Quizz();
        this.quizz.setGameState(GameState.NoGame);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) throws NullPointerException {
        if (event.getAuthor().isBot())
            return;

        if(event.getMessage().getContentRaw().startsWith("^"))
        {
            setQuizz();
        }

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

            if (event.getMessage().getContentRaw().startsWith("$") && quizz.getGameState().equals(GameState.NoGame))
            {
                //Нужно создать плеер заранне, чтобы при каждом событии он не сбрасывался
                DiscordAudioPlayer discordAudioPlayer = new DiscordAudioPlayer(guild,
                        new AudioSourceManager[] {new YoutubeAudioSourceManager()});

                AudioCommandsHandler audioCommandsHandler = new AudioCommandsHandler(discordAudioPlayer);

                audioCommandsHandler.executeCommand(args,voiceChannel,messageChannel,quizz);

            }
            //Распознавание и выполнение аудио команды

            if (quizz.getGameState().equals(GameState.Registration))
            {
                RegistrationCommandsHandler registrationCommandsHandler = new RegistrationCommandsHandler();

                registrationCommandsHandler.executeCommand(args,messageChannel,quizz);
            }

            if (event.getMessage().getContentRaw().startsWith("!"))
            messageChannel.sendMessage(StringOutputCommands.executeCommand(args)).queue();
            //Распознование и выполнение команды для чата
        }
    }
}