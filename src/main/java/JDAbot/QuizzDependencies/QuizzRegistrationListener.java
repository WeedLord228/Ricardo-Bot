package JDAbot.QuizzDependencies;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class QuizzRegistrationListener extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getMessage().getContentRaw().startsWith("$"))
        {
            String[] args = event.getMessage().getContentRaw().split(" ");
        }
    }
}
