package JDAbot.QuizzDependencies;

import net.dv8tion.jda.core.entities.User;

public class Round
{
    private String question;
    private String answer;
    private String trackId;
    private User author;
    private Integer volume;

    public Round(String answer, String question, String trackId, Integer volume, User author)
    {
        this.question = question;
        this.answer = answer;
        this.author = author;
        this.trackId = trackId;
        this.volume = volume;
    }
}
