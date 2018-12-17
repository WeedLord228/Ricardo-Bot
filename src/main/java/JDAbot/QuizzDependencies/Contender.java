package JDAbot.QuizzDependencies;

import net.dv8tion.jda.core.entities.User;

public class Contender {
    private User user;
    private String name;

    public Contender(User user)
    {
        this.user = user;
        this.name = user.getName();
    }

    public User getUser() {
         return this.user;
    }


}
