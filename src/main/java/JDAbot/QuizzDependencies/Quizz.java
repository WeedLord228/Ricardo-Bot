package JDAbot.QuizzDependencies;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Queue;



public class Quizz {
    public Round currentRound;
    private Round[] gamePool;
    private Queue<Round> game;
    private GameState gameState;
    private Contender[] contenders;

    public void setGameState(GameState gameState)
    {
        this.gameState = gameState;
    }

    public void addRound(Round round)
    {
        this.game.offer(round);
    }

    public Round getCurrentRound()
    {
        this.currentRound = this.game.peek();
        return this.currentRound;
    }

    public void setContenders(Contender[] contenders)
    {
        this.contenders = contenders;
    }


}
