package com.exercise;

import java.util.List;

/**
 * Contains utility functions
 */
public class CommonUtil {

    /**
     * Checks whether rover's new position is valid or not
     *
     * @param rover
     * @param newPosition
     * @return
     */
    public static boolean validateNewPosition(Rover rover, Position newPosition) {
        if (isPositionVacant(rover, rover.getPlateau().getRovers()) &&
                isPositionInBoundary(newPosition, rover.getPlateau().getMinPosition(), rover.getPlateau().getMaxPosition())){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks whether a rover's position is vacant or not
     *
     * @param newRover
     * @param rovers
     * @return
     */
    public static boolean isPositionVacant(Rover newRover, List<Rover> rovers) {
        boolean success = true;
        for(Rover existingRover : rovers) {
            if(existingRover == newRover) {
                continue;
            }
            if(existingRover.getCurrentPosition().getX() == newRover.getCurrentPosition().getX() &&
                    existingRover.getCurrentPosition().getY() == newRover.getCurrentPosition().getY()) {
                success = false;
                break;
            }
        }
        return success;
    }

    /**
     * Checks whether given position is within the boundary
     *
     * @param newPosition
     * @param minPosition
     * @param maxPosition
     * @return
     */
    public static boolean isPositionInBoundary(Position newPosition, Position minPosition, Position maxPosition) {
        return (newPosition.getX() <= maxPosition.getX() &&
                newPosition.getY() <= maxPosition.getY() &&
                newPosition.getX() >= minPosition.getX() &&
                newPosition.getY() >= minPosition.getY());
    }

    /**
     * Moves rover according to a given command
     *
     * @param command
     * @param rover
     */
    public static void runCommand(char command, Rover rover) {
        switch(command) {
            case 'L':
                switch(rover.getCurrentDirection()) {
                    case 'E':
                        rover.setCurrentDirection('N');
                        break;
                    case 'W':
                        rover.setCurrentDirection('S');
                        break;
                    case 'N':
                        rover.setCurrentDirection('W');
                        break;
                    case 'S':
                        rover.setCurrentDirection('E');
                        break;
                }
                break;
            case 'R':
                switch(rover.getCurrentDirection()) {
                    case 'E':
                        rover.setCurrentDirection('S');
                        break;
                    case 'W':
                        rover.setCurrentDirection('N');
                        break;
                    case 'N':
                        rover.setCurrentDirection('E');
                        break;
                    case 'S':
                        rover.setCurrentDirection('W');
                        break;
                }
                break;
            case 'M':
                int newX;
                int newY;
                switch(rover.getCurrentDirection()) {
                    case 'E':
                        newX = rover.getCurrentPosition().getX()+1;
                        newY = rover.getCurrentPosition().getY();
                        break;
                    case 'W':
                        newX = rover.getCurrentPosition().getX()-1;
                        newY = rover.getCurrentPosition().getY();
                        break;
                    case 'N':
                        newY = rover.getCurrentPosition().getY()+1;
                        newX = rover.getCurrentPosition().getX();
                        break;
                    default:
                        newY = rover.getCurrentPosition().getY()-1;
                        newX = rover.getCurrentPosition().getX();
                        break;
                }
                Position newPosition = new Position(newX, newY);
                if (CommonUtil.validateNewPosition(rover, newPosition)) {
                    rover.setCurrentPosition(newPosition);
                }
        }
    }


}
