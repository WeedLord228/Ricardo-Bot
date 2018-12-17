package JDAbot.QuizzDependencies;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;

public class RegistrationCommadsHandler
{
    public void executeCommand (String[] args){
        if (args[0].equals("$SetContenders"))
            setContenders(args);

    }

    private void setContenders(String[] args)
    {

    }
}
