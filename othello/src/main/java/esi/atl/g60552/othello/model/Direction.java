package esi.atl.g60552.othello.model;

public enum Direction {
    UPLEFT(-1, -1),
    UPRIGHT(1, -1),
    DOWNLEFT(-1, 1),
    DOWNRIGHT(1, 1),
    UP(0, -1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private int x;
    private int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getXDirection() {
        return x;
    }

    public int getYDirection() {
        return y;
    }
}
