package JDAbot.AudioDependencies;

import JDAbot.Main.BotLauncher;
import JDAbot.Main.MyListener;
import JDAbot.QuizzDependencies.GameState;
import JDAbot.QuizzDependencies.Quizz;
import JDAbot.QuizzDependencies.QuizzRegistrationListener;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;

public class AudioCommandsHandler {

    DiscordAudioPlayer discordAudioPlayer;

    public AudioCommandsHandler(DiscordAudioPlayer discordAudioPlayer)
    {
        this.discordAudioPlayer = discordAudioPlayer;
    }

    public void executeCommand (String[] args,VoiceChannel voiceChannel,MessageChannel messageChannel,Quizz quizz){
        if (args[0].equals("$play"))
            play(args,voiceChannel,messageChannel);
        if (args[0].equals("$disconnect"))
            disconnect(args);
        if (args[0].equals("$skip"))
            skip(args);
        if (args[0].equals("$volume"))
            setVolume(args,messageChannel);
        if (args[0].equals("$quizz"))
            startQuizz(messageChannel, quizz);
    }

    private void startQuizz(MessageChannel messageChannel, Quizz quizz)
    {
        quizz.setGameState(GameState.Registration);
        messageChannel.sendMessage(("Рыба карась, игра началась!")).queue();
    }

    private void loadAndQueue (String[] args, final MessageChannel messageChannel)
    {
        String id = getId(args,messageChannel);

        discordAudioPlayer.playerManager.loadItem(id, new AudioLoadResultHandler() {

            public void trackLoaded(AudioTrack audioTrack)
            {
                discordAudioPlayer.scheduler.queue(audioTrack);
                messageChannel.sendMessage("Ваш трек в очереди на " +
                        (discordAudioPlayer.scheduler.queue.size() + 1)
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

    private String getId(String[] args,MessageChannel messageChannel)
    {
        if (args[1].length() < 11)
        {
            messageChannel.sendMessage("Неправильный track identifier, попробуйте обратиться к комнде !help").queue();
            return null;
        }
        else return args[1].split("v=")[1].substring(0, 11);
    }

    private void play(String[] args, VoiceChannel voiceChannel,MessageChannel messageChannel)
    {
        discordAudioPlayer.openAudioConnection(voiceChannel);
        loadAndQueue(args,messageChannel);
    }

    private void disconnect(String[] args)
    {
        discordAudioPlayer.audioManager.closeAudioConnection();
    }

    private void skip(String[] args)
    {
        discordAudioPlayer.scheduler.nextTrack();
    }

    private void setVolume(String[] args,MessageChannel messageChannel)
    {
        discordAudioPlayer.scheduler.setVolume(Integer.parseInt(args[1]));
        messageChannel.sendMessage("Текущая громкость" + discordAudioPlayer.player.getVolume()).queue();
    }
}

