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

    public Round[] getGamePool() {
        return gamePool;
    }

    public Queue<Round> getGame() {
        return game;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Contender[] getContenders() {
        return contenders;
    }

    public String[] getContendersNames() {
        return contendersNames;
    }

    private String[] contendersNames;

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

    public void setContendersNames(String[] contendersNames)
    {
        this.contendersNames = contendersNames;
    }



}
