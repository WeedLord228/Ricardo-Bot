package project.JDAbot;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;


import javax.security.auth.login.LoginException;

public class BotLauncher
{
    public static void main(String[] args)
            throws LoginException, InterruptedException
    {
        JDA jdaBot = new JDABuilder("NDkzNzA3NDMzMjk5MTQ4ODAw.Do4pGw.eZl9Iu0_GqzJS3kDvNY4KAqzXeU")
                .addEventListener(new MyListener())
                .build();
    }
}
