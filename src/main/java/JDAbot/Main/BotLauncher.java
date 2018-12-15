package JDAbot.Main;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class BotLauncher {

    public static void main(String[] args) throws LoginException {
        JDA jda = new JDABuilder(System.getenv("JDA_BOT_TOKEN"))
                .addEventListener(new MyListener())
                .build();
    }

}
