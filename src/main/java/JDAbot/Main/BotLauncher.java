package JDAbot.Main;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class BotLauncher {

    ListenerAdapter currentListener;

    public static void main(String[] args) throws LoginException {
        JDA jda = new JDABuilder("NDkzNzA3NDMzMjk5MTQ4ODAw.Do4pGw.eZl9Iu0_GqzJS3kDvNY4KAqzXeU")
                .addEventListener(new MyListener())
                .build();
    }

}
