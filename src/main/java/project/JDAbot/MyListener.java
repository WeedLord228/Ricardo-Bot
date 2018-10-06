package project.JDAbot;

import net.dv8tion.jda.core.entities.Message;
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
            String command = args[0];
            //Первым аргументом всегда будет команда

            MessageChannel channel = event.getChannel();
            //Здесь нужно узнать из какого каналапришло сообщение, чтобы ответить туда же
            switch (command) {
                case "!ping":
                    channel.sendMessage(ChatOutputCommands.ping()).queue();
                    return;
                    //Обычный пинг
                case "!sum":
                    double a = Double.parseDouble(args[1]);
                    double b = Double.parseDouble(args[2]);
                    channel.sendMessage(ChatOutputCommands.sum(a, b)).queue();
                    return;
                    //Команда вычисления суммы, после команды должны идти два числа разделенные пробелом.
                case "!help":
                    channel.sendMessage(ChatOutputCommands.help()).queue();
                    return;
                    //Справка по всем имеющимся командам.
                case "!echo":
                    channel.sendMessage(ChatOutputCommands.echo(args)).queue();
                    return;
                    //Повторяет текст от лица бота.
            }

            channel.sendMessage("Неправильная команда, обратитесь к команде !help").queue();
        }
    }
}

