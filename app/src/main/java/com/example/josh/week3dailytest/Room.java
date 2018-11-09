package com.example.josh.week3dailytest;

public class Room {
    public final boolean isInfected;
    public boolean visited = false;
    public Room(boolean infected) {
        isInfected = infected;
    }

    public boolean isInfected() {
        return isInfected;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
