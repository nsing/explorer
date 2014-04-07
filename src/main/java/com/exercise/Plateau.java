package com.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents plateau
 * Contains:
 * 1. Boundary (minimum and maximum positions)
 * 2. Rovers
 * 3. Method to add a rover
 */
public class Plateau {

    private Position minPosition; //lower left coordinates
    private Position maxPosition; //upper right coordinates
    private List<Rover> rovers; //all rovers

    /**
     * Constructor
     */
    public Plateau(int minX, int minY, int maxX, int maxY) {
        this.minPosition = new Position(minX, minY);
        this.maxPosition = new Position(maxX, maxY);
        rovers = new ArrayList<Rover>();
    }

    //Getters:
    public Position getMaxPosition() {
        return maxPosition;
    }

    public Position getMinPosition() {
        return minPosition;
    }

    public List<Rover> getRovers() {
        return Collections.unmodifiableList(rovers);  //Return an unmodifiable list of rovers
    }

    /**
     * Adds a rover to the list after successful validation
     *
     * @param rover
     */
    public void addRover(Rover rover) {
        if (CommonUtil.isPositionInBoundary(rover.getCurrentPosition(), minPosition, maxPosition) &&
                CommonUtil.isPositionVacant(rover, getRovers())) {
            //Rover position is within plateau's boundary and vacant so add to the list
            rovers.add(rover);
        }
    }

    public void runRovers() {
        //Run rover one by one
        for(Rover currRover : rovers) {
            currRover.runInstructions();
            System.out.println(String.format("%s %s %s", currRover.getCurrentPosition().getX(),
                    currRover.getCurrentPosition().getY(),
                    currRover.getCurrentDirection()));
        };
    }




}
