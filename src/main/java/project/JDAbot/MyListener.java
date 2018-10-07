package project.JDAbot;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class MyListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().startsWith("!"))
        //Мы хотим чтобы бот обрабатывал только команды
        {
            String[] args = event.getMessage().getContentRaw().split(" ");
            //Разделение входящего сообщения на массив аргументов,
            //предпологается что боту из входящих будут нужны только команды и их аргументы

            MessageChannel channel = event.getChannel();
            //Канал, из которго пришло сообщение

            channel.sendMessage(ChatOutputCommands.executeCommand(args)).queue();
            //Распознование и выполнение команды
        }
    }
}

