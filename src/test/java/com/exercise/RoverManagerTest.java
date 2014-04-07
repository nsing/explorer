package com.exercise;

import static org.junit.Assert.assertTrue;


import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;


/**
 * Tests for CommonUtil class
 */
public class RoverManagerTest {

    @Test
    public void moveRoversTest() {
	    //Create a plateau
        Plateau plateau = new Plateau(0, 0, 5, 5);
        //Create a rover
        Rover rover = new Rover(new Position(1, 2), 'N', plateau);
        //Set instructions
        rover.setInstructions("LMLMLMLMM");
        //Add to plateau
        plateau.addRover(rover);
        //Create another rover
        rover = new Rover(new Position(3, 3), 'E', plateau);
        //Set instructions
        rover.setInstructions("MMRMMRMRRM");
        //Add to plateau
        plateau.addRover(rover);
        RoverManager roverManager = new RoverManager();
        roverManager.moveRovers(plateau);
		//Get rovers
		List<Rover> rovers = plateau.getRovers();
		assertTrue(rovers.get(0).getCurrentPosition().getX()==1);
		assertTrue(rovers.get(0).getCurrentPosition().getY()==3);
        assertTrue(rovers.get(0).getCurrentDirection()=='N');
        assertTrue(rovers.get(1).getCurrentPosition().getX()==5);
		assertTrue(rovers.get(1).getCurrentPosition().getY()==1);
        assertTrue(rovers.get(1).getCurrentDirection()=='E');
    }

    @Test
    public void loadDataTest() {
        URL url = RoverManagerTest.class.getResource("/com/exercise/input1.txt");
        RoverManager roverManager = new RoverManager();
        System.out.println(url);
        Plateau plateau = roverManager.loadData(new File(url.getFile()));
        assertTrue(plateau.getMaxPosition().getX()==5);
        assertTrue(plateau.getMaxPosition().getY()==5);
        assertTrue(plateau.getMinPosition().getX()==0);
        assertTrue(plateau.getMinPosition().getY()==0);
        List<Rover> rovers = plateau.getRovers();
		assertTrue(rovers.get(0).getCurrentPosition().getX()==1);
		assertTrue(rovers.get(0).getCurrentPosition().getY()==2);
        assertTrue(rovers.get(0).getCurrentDirection()=='N');
        assertTrue(rovers.get(0).getInstructions().equals("LMLMLMLMM"));
        assertTrue(rovers.get(1).getCurrentPosition().getX()==3);
		assertTrue(rovers.get(1).getCurrentPosition().getY()==3);
        assertTrue(rovers.get(1).getCurrentDirection()=='E');
        assertTrue(rovers.get(1).getInstructions().equals("MMRMMRMRRM"));
    }


}
