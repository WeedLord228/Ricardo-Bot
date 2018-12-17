package JDAbot.QuizzDependencies;

import net.dv8tion.jda.core.entities.User;

public class Round
{
    private String question;

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getTrackId() {
        return trackId;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getVolume() {
        return volume;
    }

    private String answer;
    private String trackId;
    private String author;
    private Integer volume;

    public Round(String answer, String question, String trackId, Integer volume, String author)
    {
        this.question = question;
        this.answer = answer;
        this.author = author;
        this.trackId = trackId;
        this.volume = volume;
    }
}
