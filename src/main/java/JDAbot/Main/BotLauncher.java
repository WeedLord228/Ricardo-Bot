package JDAbot.Main;

import JDAbot.QuizzDependencies.QuizzRegistrationListener;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class BotLauncher {

    public static JDA jda;

    private static MyListener myListener;

    private static QuizzRegistrationListener quizzRegistrationListener;



    public static void main(String[] args) throws LoginException {
        myListener = new MyListener();
        quizzRegistrationListener =new QuizzRegistrationListener();

        jda = new JDABuilder(System.getenv("JDA_BOT_TOKEN"))
                .addEventListener(myListener)
                .build();
    }

    public static void removeMylistener()
    {
        jda.removeEventListener(myListener);
    }

    public static void addMylistener()
    {
        jda.addEventListener(myListener);
    }

    public static void removeQuizzRegistrationListener()
    {
        jda.removeEventListener(myListener);
    }

    public static void addQuizzRegistrationListener()
    {
        jda.addEventListener(quizzRegistrationListener);
    }



}
