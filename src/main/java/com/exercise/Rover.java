package com.exercise;

/**
 * Represents a rover
 */
public class Rover {

    private Position currentPosition; //current coordinates
    private char currentDirection; //current direction
    private Plateau plateau; //plateau
    private String instructions; //instructions

    /**
     * Constructor
     */
    public Rover(Position currentPosition, char currentDirection, Plateau plateau) {
        this.currentPosition = currentPosition;
        this.currentDirection = currentDirection;
        this.plateau = plateau;
    }

    //Getters:
    public Position getCurrentPosition() {
        return currentPosition;
    }

    public char getCurrentDirection() {
        return currentDirection;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public String getInstructions() {
        return instructions;
    }

    //Setters:
    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setCurrentDirection(char currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

    /**
     * Run exploration instructions
     */
    public void runInstructions() {
		if( null != instructions) {
        	char[] commands = instructions.toCharArray();
	        for(char command : commands) {
	            runCommand(command);
	        }
		}
    }

    public void runCommand(char command) {
		CommonUtil.runCommand(command, this);
    }



}
