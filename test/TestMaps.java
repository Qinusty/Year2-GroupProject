/**
* @(#) TestUtils.java 1.0 2016/03/16
*
* Copyright (c) 2016 Aberystwyth University.
* All rights reserved.
*
*/

import javafx.util.Pair;
import org.junit.*;
import static org.junit.Assert.*;
import javafx.scene.paint.Color;
import sun.security.util.Length;
import uk.ac.aber.cs221.group16.game.Game;
import uk.ac.aber.cs221.group16.game.container.FlatIslandContainer;
import uk.ac.aber.cs221.group16.game.container.PirateIslandContainer;
import uk.ac.aber.cs221.group16.game.container.TreasureIslandContainer;
import uk.ac.aber.cs221.group16.game.items.cards.CardFactory;
import uk.ac.aber.cs221.group16.game.items.cards.ChanceCard;
import uk.ac.aber.cs221.group16.game.items.cards.CrewCard;
import uk.ac.aber.cs221.group16.game.items.treasures.Treasure;
import uk.ac.aber.cs221.group16.game.items.treasures.TreasureFactory;
import uk.ac.aber.cs221.group16.game.map.Board;
import uk.ac.aber.cs221.group16.game.map.MapBuilder;
import uk.ac.aber.cs221.group16.game.map.Orientation;
import uk.ac.aber.cs221.group16.game.map.TileFactory;
import uk.ac.aber.cs221.group16.game.map.tiles.*;
import uk.ac.aber.cs221.group16.game.players.Player;
import uk.ac.aber.cs221.group16.game.utils.Calculations;
import uk.ac.aber.cs221.group16.game.utils.MoveAssistance;

import java.util.*;


/**
 * This class contains the Unit Tests for the Players package.
 *
 * @author Luke Revill
 * @author Elliot Sheehy
 * @version 1.0 - Utils Unit testing
 */
public class TestMaps {
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
    * Generates a set of treasures to be used in testing and returns them in a linked list
    *
    * @return testTreasures
    */
   private LinkedList<Treasure> generateTestTreasures(){
      TreasureFactory tf = new TreasureFactory(1);
      LinkedList<Treasure> testTreasures = tf.genTreasureList();
      return testTreasures;
   }

   /**
    * Generates a set hand to be used for tests
    *
    * @return hand
    */
   private ArrayList<CrewCard> generateTestHand(int b, int r) {
      ArrayList<CrewCard> hand = new ArrayList<>();

      hand.add(new CrewCard(Color.RED, r));
      hand.add(new CrewCard(Color.BLACK, b));
      return hand;
   }


   //TreasureIslandTile tests

   /**
    * tests if the value of a set of treasure is equal to the requested value of treasure
    *
    */
   @Test
   public void testGetTreasuresOfVaules() {
      Game NewGame = generateTestGame();
      Board NewBoard = NewGame.getBoard();
      Set<Treasure> ListOfTreasure = NewBoard.getTreasureIslandContainer().getTreasuresOfValue(10, 2, true);
      int total=0;
         Iterator<Treasure> iterator = ListOfTreasure.iterator();

         while(iterator.hasNext()){
            Treasure next = iterator.next();
            total += next.getValue();
         }
      assertEquals("Treasure has different value to that asked for", 10 ,total);
   }

   /**
    * tests if a chance card is drawn
    *
    */
   @Test
   public void testDrawChanceCard() {
      Game NewGame = generateTestGame();
      Board NewBoard = NewGame.getBoard();
      Tile[][] grid = NewBoard.getGrid();
      TreasureIslandTile TreasureIslandTest = (TreasureIslandTile) grid[10][10];
      ChanceCard TestChanceCard = TreasureIslandTest.drawChanceCard();
      int id=TestChanceCard.getCardID();
      boolean valid = false;
      if((id >= 1) && (id <= 28)){
      valid = true;
      }
      assertEquals("The card drawn doesnt have a valid id", true, valid);
   }

   /**
    * tests whether a chance card is returned to the bottom
    *
    */
   @Test
   public void testPlaceChanceCardToBottom() {
      Game NewGame = generateTestGame();
      Board NewBoard = NewGame.getBoard();
      Tile[][] grid = NewBoard.getGrid();
      TreasureIslandTile tIT = (TreasureIslandTile) grid[10][10];
      ChanceCard subject = tIT.drawChanceCard();
      tIT.placeChanceCardToBottom(subject);
      while(NewBoard.getTreasureIslandContainer().getChanceCards().size()>1){
         tIT.drawChanceCard();
      }
      ChanceCard last = tIT.drawChanceCard();
      assertEquals("the first card was not at the bottom of 28 cards", subject ,last);
   }

   //Orientation tests

   /**
    * tests if the orientation correctly corresponds to the ships angle
    *
    */
   @Test
   public void testFromAngle() {
      int Angle = 0;
      int Incrementer = 0;
      Orientation OrientationsList[] = new Orientation[8];

      while (Angle<360) {
         OrientationsList[Incrementer]=Orientation.fromAngle(Angle);
         Angle+=45;
         Incrementer++;
      }
      assertEquals("N",OrientationsList[0],  Orientation.N);
      assertEquals("NE",OrientationsList[1],  Orientation.NE);
      assertEquals("E",OrientationsList[2],  Orientation.E);
      assertEquals("SE",OrientationsList[3],  Orientation.SE);
      assertEquals("S",OrientationsList[4],  Orientation.S);
      assertEquals("SW",OrientationsList[5],  Orientation.SW);
      assertEquals("W",OrientationsList[6],  Orientation.W);
      assertEquals("NW",OrientationsList[7],  Orientation.NW);

   }

   /**
    * tests whether the generated array contains all directions
    *
    */
   @Test
   public void testGetAll() {
      Orientation OrientationList[] = Orientation.getAll();
      boolean check = true;

      if (OrientationList[0]!=Orientation.NW)check = false;
      if (OrientationList[1]!=Orientation.N)check = false;
      if (OrientationList[2]!=Orientation.NE)check = false;
      if (OrientationList[3]!=Orientation.W)check = false;
      if (OrientationList[4]!=Orientation.E)check = false;
      if (OrientationList[5]!=Orientation.SW)check = false;
      if (OrientationList[6]!=Orientation.S)check = false;
      if (OrientationList[7]!=Orientation.SE)check = false;
      assertEquals("array of direction not present in same order established in class",check,  true);
   }

   /**
    * tests whether the method returns the correct angle from each enum
    *
    */
   @Test
   public void testGetAngle() {
      boolean CorrectOrientation = true;
      int Angle;
      Angle = Orientation.N.getAngle();
      if(Angle != 0)CorrectOrientation = false;
      Angle = Orientation.NE.getAngle();
      if(Angle != 45)CorrectOrientation = false;
      Angle=Orientation.E.getAngle();
      if(Angle != 90)CorrectOrientation = false;
      Angle=Orientation.SE.getAngle();
      if(Angle != 135)CorrectOrientation = false;
      Angle=Orientation.S.getAngle();
      if(Angle != 180)CorrectOrientation = false;
      Angle=Orientation.SW.getAngle();
      if(Angle != 225)CorrectOrientation = false;
      Angle=Orientation.W.getAngle();
      if(Angle != 270)CorrectOrientation = false;
      Angle=Orientation.NW.getAngle();
      if(Angle != 315)CorrectOrientation = false;

      assertEquals("Enums dont return correct value",CorrectOrientation,  true);
   }

   /**
    * tests whether the method returns the correct modifier to an angle
    *
    */
   @Test
   public void testGetDelta() {
      Pair<Integer, Integer> CheckDelta;
      Pair<Integer, Integer> FunctionDelta;
      boolean DeltaMatch = true;

      FunctionDelta = new Pair<>(0, -1);
      CheckDelta = Orientation.N.getDelta();
      if(CheckDelta == FunctionDelta)DeltaMatch = false;
      FunctionDelta = new Pair<>(1, -1);
      CheckDelta = Orientation.NE.getDelta();
      if(CheckDelta == FunctionDelta)DeltaMatch = false;
      FunctionDelta = new Pair<>(1, 0);
      CheckDelta=Orientation.E.getDelta();
      if(CheckDelta == FunctionDelta)DeltaMatch = false;
      FunctionDelta = new Pair<>(1, 1);
      CheckDelta = Orientation.SE.getDelta();
      if(CheckDelta == FunctionDelta)DeltaMatch = false;
      FunctionDelta = new Pair<>(0, 1);
      CheckDelta = Orientation.S.getDelta();
      if(CheckDelta == FunctionDelta)DeltaMatch = false;
      FunctionDelta = new Pair<>(-1, 1);
      CheckDelta = Orientation.SW.getDelta();
      if(CheckDelta == FunctionDelta)DeltaMatch = false;
      FunctionDelta = new Pair<>(-1, 0);
      CheckDelta = Orientation.W.getDelta();
      if(CheckDelta == FunctionDelta)DeltaMatch = false;
      FunctionDelta = new Pair<>(-1, -1);
      CheckDelta = Orientation.NW.getDelta();
      if(CheckDelta == FunctionDelta)DeltaMatch = false;

      assertEquals("the methods results dont match what they should ",DeltaMatch,  true);
   }


   //TileFactory tests

   /**
    * tests if the right tiles are created
    *
    */
   @Test
   public void TestTileFactory(){
      FlatIslandContainer fIsland = new FlatIslandContainer();
      PirateIslandContainer pIsland = new PirateIslandContainer(null);
      TreasureFactory tf = new TreasureFactory(1);
      List<Treasure> treasures = tf.genTreasureList();
      Queue<ChanceCard> cards = new LinkedList<>();

      TreasureIslandContainer tIsland = new TreasureIslandContainer(treasures,cards);
      TileFactory tileF = new TileFactory(fIsland,pIsland,tIsland);
      Tile t;
      assertEquals("Tile Created should be SeaTile",true,
              (t = tileF.makeTile("SEA",1,1)) instanceof SeaTile);
      assertEquals("Tile Created should be FlatIslandTile",true,
              (t = tileF.makeTile("FLAT",1,1)) instanceof FlatIslandTile);
      assertEquals("Tile Created should be PirateIslandTile",true,
              (t = tileF.makeTile("PIRATE",1,1)) instanceof PirateIslandTile);
      assertEquals("Tile Created should be TreasureIslandTile",true,
              (t = tileF.makeTile("TREASURE",1,1)) instanceof TreasureIslandTile);
      assertEquals("Tile Created should be PortTile",true,
              (t = tileF.makeTile("LONDON",1,1)) instanceof PortTile);
      assertEquals("Tile Created should be PortTile",true,
              (t = tileF.makeTile("CADIZ",1,1)) instanceof PortTile);
      assertEquals("Tile Created should be BayTile",true,
              (t = tileF.makeTile("ANCHOR",1,1)) instanceof BayTile);
   }


}
