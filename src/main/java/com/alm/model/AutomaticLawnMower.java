package com.alm.model;

public class AutomaticLawnMower {
    private Position position;
    private Orientation orientation;
    private final Lawn lawn;

    public AutomaticLawnMower(Position position, Orientation orientation, Lawn lawn) {
        this.position = position;
        this.orientation = orientation;
        this.lawn = lawn;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Lawn getLawn() {
        return lawn;
    }

    @Override
    public String toString() {
        return position + " " + orientation;
    }
}
