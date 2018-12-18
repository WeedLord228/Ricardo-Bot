package JDAbot.QuizzDependencies;

import JDAbot.Logic.StringOutputCommands;
import JDAbot.Main.BotLauncher;
import JDAbot.Main.MyListener;
import net.dv8tion.jda.core.entities.MessageChannel;

public class RegistrationCommandsHandler
{

    public void executeCommand (String[] args, MessageChannel messageChannel,Quizz quizz)
    {
        if (args[0].equals("$SetContenders")) {
            setContendersNames(args, messageChannel, quizz);
        }
        if (args[0].equals("$AddRound")){
            addRound(args,messageChannel,quizz);
        }
        if (args[0].equals("$play")) {
            play(messageChannel, quizz);
        }
        if (args[0].equals("$ping")) {
            ping(messageChannel);
        }
    }

    private void ping(MessageChannel messageChannel)
    {
        messageChannel.sendMessage("SAS TI SOS").queue();
    }

    private void setContendersNames(String[] args,MessageChannel messageChannel,Quizz quizz)
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

    private void addRound(String[] args,MessageChannel messageChannel,Quizz quizz)
    {
        quizz.addRound(new Round(args[1],args[2],args[3],Integer.parseInt(args[4]),args[5]));
        messageChannel.sendMessage((quizz.getCurrentRound().getQuestion())).queue();
    }

    private void play(MessageChannel messageChannel,Quizz quizz)
    {
        quizz.setGameState(GameState.Round);
        messageChannel.sendMessage(("Рыба карась, игра сась!")).queue();
    }
}
