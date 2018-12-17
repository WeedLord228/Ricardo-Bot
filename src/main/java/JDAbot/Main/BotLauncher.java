package JDAbot.Main;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class BotLauncher {

    public static JDA jda;

    public static void main(String[] args) throws LoginException {
        jda = new JDABuilder(System.getenv("JDA_BOT_TOKEN"))
                .addEventListener(new MyListener())
                .build();
    }

}
