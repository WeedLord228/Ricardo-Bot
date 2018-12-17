package JDAbot.QuizzDependencies;

import JDAbot.Logic.StringOutputCommands;
import JDAbot.Main.BotLauncher;
import JDAbot.Main.MyListener;
import net.dv8tion.jda.core.entities.MessageChannel;

public class RegistrationCommandsHandler
{
    Quizz quizz;

    public RegistrationCommandsHandler(Quizz quizz)
    {this.quizz = quizz;}

    public void executeCommand (String[] args, MessageChannel messageChannel)
    {
        if (args[0].equals("$SetContenders"))
            setContendersNames(args,messageChannel);
        if (args[0].equals("$AddRound"));
            addRound(args,messageChannel);
        if (args[0].equals("$play"))
            play(messageChannel);
    }

    private void setContendersNames(String[] args,MessageChannel messageChannel)
    {
        String[] tempArr = new String[args.length - 1];
        int j = 0;
        for (int i = 1; i < args.length; i++)
        {
            tempArr[j] = args[i];
            j++;
        }

        quizz.setContendersNames(tempArr);
        messageChannel.sendMessage(quizz.getContendersNames()[0]).queue();
    }

    private void addRound(String[] args,MessageChannel messageChannel)
    {
        Round currentRound = new Round(args[1],args[2],args[3],Integer.parseInt(args[4]),args[5]);
        quizz.addRound(currentRound);
        messageChannel.sendMessage((quizz.getCurrentRound().getQuestion())).queue();

    }

    private void play(MessageChannel messageChannel)
    {
        BotLauncher.jda.removeEventListener(new QuizzRegistrationListener());
        BotLauncher.jda.addEventListener(new GameListener());
        messageChannel.sendMessage(("Рыба карась, игра началась!")).queue();
    }
}
