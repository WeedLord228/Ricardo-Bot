package project.JDAbot;

import java.util.ArrayList;

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
        return tempStr.toString();
        //Методу передаётся массив слов вместе с названием команды
        // Поэтому первое слово не учитывается и расставляются пробелы

    }

}
