package com.exercise;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


/**
 * Tests for CommonUtil class
 */
public class CommonUtilTest {

    @Test
    public void isPositionVacantTest() {
        Plateau plateau = new Plateau(0, 0, 5, 5);
        Rover rover = new Rover(new Position(1, 2), 'N', plateau);
        rover.setInstructions("LMLMLMLMM");
        plateau.addRover(rover);
        rover = new Rover(new Position(3, 3), 'E', plateau);
        rover.setInstructions("MMRMMRMRRM");
        assertTrue(CommonUtil.isPositionVacant(rover, plateau.getRovers()));
    }

    @Test
    public void isPositionInBoundaryTest() {
        Plateau plateau = new Plateau(0, 0, 5, 5);
        Rover rover = new Rover(new Position(1, 2), 'N', plateau);
        plateau.addRover(rover);
        Position newPosition = new Position(3, 3);
        assertTrue(CommonUtil.isPositionInBoundary(newPosition, plateau.getMinPosition(), plateau.getMaxPosition()));
    }

    @Test
    public void runCommandTest() {
        Plateau plateau = new Plateau(0, 0, 5, 5);
        Rover rover = new Rover(new Position(1, 2), 'N', plateau);
        plateau.addRover(rover);
        CommonUtil.runCommand('M', rover);
        assertTrue(rover.getCurrentPosition().getX()==1);
        assertTrue(rover.getCurrentPosition().getY()==3);
        assertTrue(rover.getCurrentDirection()=='N');
    }

}
