package project.CommandsTest;

import org.junit.jupiter.api.Test;
import project.JDAbot.ChatOutputCommands;

import static org.junit.jupiter.api.Assertions.*;

class ChatOutputCommandsTest {

    @Test
    void pingTest() {
        String a = "Я живой!";
        assertEquals(ChatOutputCommands.ping(), a);
    }

    @Test
    void sumTest1() {
        String[] args = {"3","2"};
        assertEquals(ChatOutputCommands.sum(args), "5");
    }
//    "Неправильный ввод, попробуйте ввести число"
    @Test
    void sumTest2() {
        String[] args = {"a","2"};
        assertEquals(ChatOutputCommands.sum(args), "Неправильный ввод, попробуйте ввести число");
    }
    @Test
    void helpTest() {
        String a = "На данный момент доступны команды в формате: " +
                "\n!ping - Для проверки жизнеспособности бота." +
                "\n!sum arg1 arg2 - Для вычисления суммы двух действительных чисел." +
                "\n!echo some text - Повторяет текст от лица бота.";
        assertEquals(ChatOutputCommands.help(), a);
    }

    @Test
    void echoTest() {
        String[] args = {"a", "b", "c"};
        assertEquals(ChatOutputCommands.echo(args), "b c");
    }

    @Test
    void executeCommand1() {
        String[] args1 = {"!ping"};



        assertEquals(ChatOutputCommands.executeCommand(args1),"Я живой!");
    }
    @Test
    void executeCommand2()
    {
        String[] args2 = {"!sum", "4", "5"};
        assertEquals(ChatOutputCommands.executeCommand(args2),"9.0");

    }
    @Test
    void  executeCommand3()
    {
        String[] args3 = {"!ehco"};
        assertEquals(ChatOutputCommands.executeCommand(args3),"Неправильная команда, обратитесь к команде !help");

    }
    @Test
    void executeCommand4()
    {
        String[] args4 = {"!echo","Hello","world!"};
        assertEquals(ChatOutputCommands.executeCommand(args4),"Hello world!");
    }
}