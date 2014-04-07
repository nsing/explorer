package com.exercise;

import java.io.FileNotFoundException;
import java.util.List;
import java.io.File;
import java.util.Scanner;


/**
 * Rover manager
 */
public class RoverManager {

    public static void main(String[] args) {
		if(0 == args.length) {
			System.out.println("Input file name not supplied");
	    }
	    else {
			//Instantiate rover manager
	    	RoverManager roverManager = new RoverManager();
	    	//Load data
            File file = new File(args[0]);
	    	Plateau plateau = roverManager.loadData(file);
	    	roverManager.moveRovers(plateau);
	    }
    }

	/**
     * Move rovers
     *
     * @param plateau
     */
    public void moveRovers(Plateau plateau) {
		if (null != plateau) {
	       	//Get rovers
        	List<Rover> rovers = plateau.getRovers();
        	//Move rovers one by one
        	for(Rover currRover : rovers) {
	       		currRover.runInstructions();
                System.out.println(String.format("%s %s %s", currRover.getCurrentPosition().getX(),
                currRover.getCurrentPosition().getY(),
                currRover.getCurrentDirection()));
           	}
    	}
	}

	/**
     * Load data from the given file
     *
     * @param file
     * @return Plateau
     */
    public Plateau loadData(File file) {
		Plateau plateau = null;
        Rover rover;
		try {
			boolean plateauLoaded = false;
			//File file = new File(fileName);
			Scanner sc = new Scanner(file);
			int lineCounter = 1;
			if (sc.hasNextLine()) { //Plateau data
				String line = sc.nextLine();
				String[] elements = line.split(" ");
				if(2 != elements.length) {
					System.out.println("Incorrect plateau data");
				}
                else {
                    try {
                        plateau = new Plateau(0, 0, Integer.parseInt(elements[0]), Integer.parseInt(elements[1]));
                        plateauLoaded = true;
                    }
                    catch(NumberFormatException nfe) {
                        System.out.println("Incorrect plateau data");
                    }
                }
			}
			if (plateauLoaded) {
                while(sc.hasNextLine()) { //Rover data
                    lineCounter++;
                    String line = sc.nextLine(); //Rover coordinates and position
                    String[] elements = line.split(" ");
                    if(3 != elements.length) {
                        System.out.println(String.format("Incorrect rover data in line %d", lineCounter));
                        break;
                    }
                    int x, y;
                    try {
                        x = Integer.parseInt(elements[0]);
                        y = Integer.parseInt(elements[1]);
                    }
                    catch(NumberFormatException nfe) {
                        System.out.println(String.format("Incorrect rover coordinates data in line %d", lineCounter));
                        break;
                    }
                    char direction;
                    if("N".equals(elements[2]) || "S".equals(elements[2]) || "W".equals(elements[2]) || "E".equals(elements[2])) {
                        direction = elements[2].charAt(0);
                    }
                    else {
                        System.out.println(String.format("Incorrect rover position in line %d", lineCounter));
                        break;
                    }
                    String instructions;
                    if(sc.hasNextLine()) {
                        lineCounter++;
                        instructions = sc.nextLine(); //Rover instruction
                    }
                    else {
                        System.out.println(String.format("No rover instructions found after line %d", lineCounter));
                        break;
                    }
                    rover = new Rover(new Position(x, y), direction, plateau);
                    rover.setInstructions(instructions);
                    plateau.addRover(rover);
                    lineCounter++;
                }
            }
		    sc.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Couldn't locate input file");
    	}
    	return plateau;
	}
}
