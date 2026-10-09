package models;

public class Game {

    private int id;
    private int playerId;

    private Difficulty difficulty;
    private Board board;

    private long startTime;
    private long endTime;

    private boolean finished;
    private boolean victory;

    public Game() {
    }

    public Game(int id, int playerId, Difficulty difficulty) {

        this.id = id;
        this.playerId = playerId;
        this.difficulty = difficulty;

        this.board = new Board(difficulty);

        this.startTime = System.currentTimeMillis();

        this.finished = false;
        this.victory = false;
    }

    public long getElapsedTime() {

        if (!finished) {
            return System.currentTimeMillis() - startTime;
        }

        return endTime - startTime;
    }

    public void finish(boolean victory) {

        this.finished = true;
        this.victory = victory;

        this.endTime = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isVictory() {
        return victory;
    }
}