package project.JDAbot;


public class ChatOutputCommands
{
    public static String ping() {
        return "Я живой!";
    }

    public  static  String sum(double a, double b)
    {

        return Double.toString(a + b);

    }

    public static String help()
    {
        return "На данный момент доступны команды в формате: " +
                "\n!ping - Для проверки жизнеспособности бота." +
                "\n!sum arg1 arg2 - Для вычисления суммы двух действительных чисел." +
                "\n!echo some text - Повторяет текст от лица бота.";
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

        switch (command) {
            case "!ping":
               return ChatOutputCommands.ping();
            //Обычный пинг
            case "!sum":
                double a = Double.parseDouble(args[1]);
                double b = Double.parseDouble(args[2]);
                return ChatOutputCommands.sum(a, b);
            //Команда вычисления суммы, после команды должны идти два числа разделенные пробелом.
            case "!help":
                return ChatOutputCommands.help();
            //Справка по всем имеющимся командам.
            case "!echo":
                return ChatOutputCommands.echo(args);
            //Повторяет текст от лица бота.
        }
        return "Неправильная команда, обратитесь к команде !help";
    }

}
