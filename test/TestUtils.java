/*
* @(#) TestUtils.java 1.0 2016/03/16
*
* Copyright (c) 2016 Aberystwyth University.
* All rights reserved.
*
*/

import org.junit.*;
import static org.junit.Assert.*;
import javafx.scene.paint.Color;
import sun.security.util.Length;
import uk.ac.aber.cs221.group16.game.Game;
import uk.ac.aber.cs221.group16.game.items.cards.CardFactory;
import uk.ac.aber.cs221.group16.game.items.cards.CrewCard;
import uk.ac.aber.cs221.group16.game.items.treasures.Treasure;
import uk.ac.aber.cs221.group16.game.items.treasures.TreasureFactory;
import uk.ac.aber.cs221.group16.game.map.Board;
import uk.ac.aber.cs221.group16.game.map.Orientation;
import uk.ac.aber.cs221.group16.game.map.tiles.Tile;
import uk.ac.aber.cs221.group16.game.players.Player;
import uk.ac.aber.cs221.group16.game.utils.Calculations;
import uk.ac.aber.cs221.group16.game.utils.MoveAssistance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;


/**
 * This class contains the Unit Tests for the Players package.
 *
 * @author Luke Revill
 * @author Elliot Sheehy
 * @version 1.0 - Utils Unit testing
 */

public class TestUtils {

   /**
    * Generates test game for use within the tests
    *
    * @return a new game
    */
   private Game generateTestGame(){
      ArrayList<String> n = new ArrayList<>();
      for(int i = 0; i < 4; i++){
         n.add("Luke");
      }

      Game g = new Game(n,null);
      return g;
   }

   /**
    * Generates test player list for use within the tests
    *
    * @return a list of 4 new players
    */
   private Player[] generateTestPlayerList(){
      Game g = generateTestGame();
      Player [] p = g.getPlayers();
      return p;
   }

   /**
    * Generates test player for use within the tests
    *
    * @return a new player from the list
    */
   private Player generateTestPlayer(int pNum){
      Player[] p = generateTestPlayerList();
      Player p1 = p[pNum];

      return p1;
   }

   /**
    * Generates test hand of crew cards for use within the tests
    *
    * @return a set of crew cards
    */
   private ArrayList<CrewCard> generateTestHand(int b, int r){
      ArrayList<CrewCard> hand = new ArrayList<>();

      hand.add(new CrewCard(Color.RED,r));
      hand.add(new CrewCard(Color.BLACK,b));
      return hand;
   }
   //Calculations class tests

   /**
    * Generates test direction for use within the tests
    *
    * @return a direction for a ship
    */
   @Test
   public void testCalculateDirection() {
     Game NewGame = generateTestGame();
     Board NewBoard = NewGame.getBoard();
     Tile[][] grid = NewBoard.getGrid();
     Orientation ShipOrientation = Calculations.calculateDirection(grid[1][1],grid[3][3]);
     assertEquals("Direction should equal southeast",Orientation.SE ,ShipOrientation);
     //message,expected,actual
   }


   //MoveAssistance class tests

   /**
    * Tests whether a set of moves are valid or not
    *
    */
   @Test
   public void testGetValidMoves(){
      Game NewGame = generateTestGame();
      Board NewBoard = NewGame.getBoard();
      Tile[][] grid = NewBoard.getGrid();
      Player NewPlayer = generateTestPlayer(0);
      NewPlayer.setCrewCards(generateTestHand(1,1));
      NewPlayer.getShip().moveTo(grid[5][5],NewBoard);
      NewPlayer.getShip().setOrientation(Orientation.E);
      ArrayList<Tile> ValidMovesList = new ArrayList<>();
      Set<Tile> MovesToTest = MoveAssistance.getValidMoves(NewPlayer, NewGame.getState(), grid);
      ValidMovesList.add(grid[5][5]);
      ValidMovesList.add(grid[6][5]);
      ValidMovesList.add(grid[7][5]);
      int Incrementer = 0;

      boolean contains = false;
      for (Tile t : MovesToTest){
         contains = MovesToTest.contains(ValidMovesList.get(Incrementer));
         assertEquals("the function doesnt generate the same move set i worked out", true ,contains);
         Incrementer++;
      }

   }

   /**
    * Tests whether the function to do so returns a list of tiles surrounding the current tile
    *
    */
   @Test
   public void testGetSurroundingTiles(){
      Game NewGame= generateTestGame();
      Board NewBoard= NewGame.getBoard();
      Tile[][] grid = NewBoard.getGrid();
      ArrayList<Tile> MovesToTest = MoveAssistance.getSurroundingTiles(5,5,grid);
      ArrayList<Tile> ValidMovesList = new ArrayList<>();
      int Incrementer =0;
      ValidMovesList.add(grid[4][4]);
      ValidMovesList.add(grid[5][4]);
      ValidMovesList.add(grid[6][4]);
      ValidMovesList.add(grid[4][5]);
      ValidMovesList.add(grid[5][6]);
      ValidMovesList.add(grid[4][6]);
      ValidMovesList.add(grid[5][6]);
      ValidMovesList.add(grid[6][6]);
      boolean contains = false;
      for (Tile t : MovesToTest){
         contains = MovesToTest.contains(ValidMovesList.get(Incrementer))
         ;         assertEquals("not all tiles surrounding the one checked are present", true ,contains);
         Incrementer++;
      }
   }

   /**
    * Tests whether or not a prticular tile is sailable
    *
    */
   @Test
   public void testIsSailable(){
      Game NewGame= generateTestGame();
      Board NewBoard= NewGame.getBoard();
      Tile[][] grid = NewBoard.getGrid();
      boolean LowBoundarySeaTest = MoveAssistance.isSailableCoordinate(0,0,grid);
      boolean NormalSeaTest = MoveAssistance.isSailableCoordinate(5,5,grid);
      boolean IslandTest = MoveAssistance.isSailableCoordinate(10,10,grid);
      boolean HighBoundarySeaTest = MoveAssistance.isSailableCoordinate(19,19,grid);
      boolean ExtremeSeaTest = MoveAssistance.isSailableCoordinate(20,20,grid);
      assertEquals("sea should be sailable", true ,LowBoundarySeaTest);
      assertEquals("more sea should be sailable", true ,NormalSeaTest);
      assertEquals("island shouldnt be sailable", false ,IslandTest);
      assertEquals("port should be sailable", true ,HighBoundarySeaTest);
      assertEquals("doesnt exist shouldnt be sailable", false ,ExtremeSeaTest);

   }
}

