package JDAbot;

public class ChatOutputCommands {
    public static String ping() {
        return "Я живой!";
    }

    public static String sum(String[] args) {
        try {
            double a = Double.parseDouble(args[1]);
            double b = Double.parseDouble(args[2]);
        } catch (Exception e) {
            return "Неправильный ввод, попробуйте ввести число";
        }

        if ((Double.parseDouble(args[1]) >= Double.MAX_VALUE / 2 - 1 &&
                Double.parseDouble(args[2]) >= Double.MAX_VALUE / 2 - 1))
            return "Слишком большие числа";

        return Double.toString(Double.parseDouble(args[1]) +
                Double.parseDouble(args[2]));

    }

    public static String help() {
        return "На данный момент доступны команды в формате: " +
                "\n!ping - Для проверки жизнеспособности бота." +
                "\n!sum arg1 arg2 - Для вычисления суммы двух действительных чисел." +
                "\n!echo some text - Повторяет текст от лица бота.";
    }

    public static String echo(String[] args) {
        StringBuilder tempStr = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            tempStr.append(args[i]).append(" ");
        }
        return tempStr.deleteCharAt(tempStr.length() - 1).toString();
        //Методу передаётся массив слов вместе с названием команды
        // Поэтому первое слово не учитывается и расставляются пробелы
    }

    public static String goodNight(String[] args) {
        return "str";
    }

    public static String greetings(String[] args) {
        return "str";
    }

    public static boolean containsTwo(String firstStr, String secondStr, String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].toLowerCase() == firstStr &&
                    args[i + 1].toLowerCase() == secondStr)
                return true;
        }
        return false;
    }


    public static String executeCommand(String[] args) {
        if (containsTwo("спокойной", "ночи", args))
            return "Спокойной ночи, красавчик! :kissing_heart:";

        String command = args[0].toLowerCase();
        if (command.startsWith("!")) {
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
        }
        return "Неправильная команда, обратитесь к команде !help";
    }

}

