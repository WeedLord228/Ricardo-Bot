package JDAbot;

public class ChatOutputCommands
{
    public static String ping() {
        return "Я живой!";
    }

    public  static  String sum(String[] args)
    {
        try
        {
            double a = Double.parseDouble(args[1]);
            double b = Double.parseDouble(args[2]);
        }
        catch (Exception e)
        {
            return "Неправильный ввод, попробуйте ввести число";
        }

        if ((Double.parseDouble(args[1]) >= Double.MAX_VALUE/2 -1 &&
                Double.parseDouble(args[2]) >= Double.MAX_VALUE/2 - 1))
            return "Слишком большие числа";

        return Double.toString(Double.parseDouble(args[1]) +
                Double.parseDouble(args[2]));

    }

    public static String help()
    {
        return "На данный момент доступны команды в формате: " +
                "\n!ping - Для проверки жизнеспособности бота." +
                "\n!sum arg1 arg2 - Для вычисления суммы двух действительных чисел." +
                "\n!echo some text - Повторяет текст от лица бота." +
                "\n Ключ $ для команд, которые работают с аудио" +
                "\n $play [track identifier] - track identifier одинадцать символов вместе с \"v=\" в ссылке на видео, ставит эту песню" +
                "\n $skip - пропустить текущий трек" +
                "\n $disconnect - отключить бота от голосового канала"
                ;
    }

    public static String echo(String[] args)
    {
        StringBuilder tempStr = new StringBuilder();
        for (int i = 1; i < args.length; i++)
        {
            tempStr.append(args[i]).append(" ");
        }
        return tempStr.deleteCharAt(tempStr.length()-1).toString();
        //Методу передаётся массив слов вместе с названием команды
        // Поэтому первое слово не учитывается и расставляются пробелы
    }

    public static String executeCommand(String[] args)
    {
        String command = args[0].toLowerCase();

        if ("!ping".equals(command)) {
            return ChatOutputCommands.ping();
            //Обычный пинг
        } else if ("!sum".equals(command)) {
            return ChatOutputCommands.sum(args);
            //Команда вычисления суммы, после команды должны идти два числа разделенные пробелом.
        } else if ("!help".equals(command)) {
            return ChatOutputCommands.help();
            //Справка по всем имеющимся командам.
        } else if ("!echo".equals(command)) {
            return ChatOutputCommands.echo(args);
            //Повторяет текст от лица бота.
        }
        return "Неправильная команда, обратитесь к команде !help";
    }
}

