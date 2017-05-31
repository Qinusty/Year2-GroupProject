/*
* @(#) TestPlayers.java 1.0 2016/05/01
*
* Copyright (c) 2016 Aberystwyth University.
* All rights reserved.
*
*/



import org.junit.*;
import static org.junit.Assert.*;
import javafx.scene.paint.Color;
import uk.ac.aber.cs221.group16.game.Game;
import uk.ac.aber.cs221.group16.game.items.cards.CardFactory;
import uk.ac.aber.cs221.group16.game.items.cards.CrewCard;
import uk.ac.aber.cs221.group16.game.items.treasures.Treasure;
import uk.ac.aber.cs221.group16.game.items.treasures.TreasureFactory;
import uk.ac.aber.cs221.group16.game.map.Board;
import uk.ac.aber.cs221.group16.game.map.tiles.Tile;
import uk.ac.aber.cs221.group16.game.players.Player;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * This class contains the Unit Tests for the Players package.
 *
 * @author Luke Revill
 * @version 1.0 - Players Unit testing
 */

public class TestPlayers {

   /**
    * Generates list of Players for use to retrieve multiple players
    * @return p, where p is an Array of Players
    */
   private Player[] generateTestPlayerList(){
      Game g = generateTestGame();
      Player [] p = g.getPlayers();
      return p;
   }
   /**
    * Generates test player for use within the tests
    * @return p
    */
   private Player generateTestPlayer(int pNum){
      Player[] p = generateTestPlayerList();
      Player p1 = p[pNum];
      p1.setCrewCards(generateTestHand(3,1));
      return p1;
   }

   /**
    * #Generates a set hand to be used for tests
    *
    * @return hand
    */
   private ArrayList<CrewCard> generateTestHand(int b, int r){
      ArrayList<CrewCard> hand = new ArrayList<>();
      hand.add(new CrewCard(Color.RED,r));
      hand.add(new CrewCard(Color.BLACK,b));
      return hand;
   }
   /**
    *Generates a new game for usage in tests
    *
    * @return g
    */
   private Game generateTestGame(){
      ArrayList<String> n = new ArrayList<>();
      for(int i = 0; i < 4; i++){
         n.add("Luke");
      }
      Game g = new Game(n,null);
      return g;
   }

   //Player class tests

   /**
    * Tests the getMovementDistance function within the player class
    *
    */
   @Test
   public void testMovementDistance(){
      Player p = generateTestPlayer(0);
      assertEquals("Movement Distance should equal 4",4,p.getMovementDistance());
   }

   /**
    * Tests the getHomePort method in the player class
    *
    */
   @Test
   public void testFightingStrength(){
      Player p = generateTestPlayer(0);
      assertEquals("Fighting Strength should equal 2", 2,p.getFightingStrength());
   }
   @Test
   public void testHomePort(){
      Player p = generateTestPlayer(0);
      assertEquals("Home Port should equal London", "London",p.getHomePort().getName());
   }

   /**
    * Tests the getName method in the Player class
    *
    */
   @Test
   public void testGetName(){
      Player p = generateTestPlayer(0);
      assertEquals("Player should equal Luke", "Luke", p.getName());
   }

   /**
    * Test the attack function within the program
    *
    */
   @Test
   public void testAttack(){
      Player p = generateTestPlayer(0);
      Player p2 = generateTestPlayer(0);
      p2.setCrewCards(generateTestHand(1,1));
      assertEquals("Player 1 should win the attack(p2 will be returned)",p2,p.attack(p2));
   }

   /**
    * Test the removeTradable function within the player class
    *
    */
   @Test
   public void testRemoveTradable(){
      Player p = generateTestPlayer(0);
      TreasureFactory tf = new TreasureFactory(1);
      LinkedList<Treasure> t = tf.genTreasureList();
      Treasure testTreasure = t.get(0);
      p.getShip().addTreasure(t.get(0));
      p.setCrewCards(new ArrayList<CrewCard>());
      assertEquals("Initial Treasure should be a 1 Diamond",1,p.getShip().getCargo().size());
      p.removeTradable(testTreasure);
      assertEquals("Cargo should now be empty",0,p.getShip().getCargo().size());

      CrewCard testCard = new CrewCard(Color.BLACK,1);
      p.addCrewCard(testCard);
      assertEquals("Hand size should be equal to 1", 1, p.getCrewCards().size());
      p.removeTradable(testCard);
      assertEquals("Hand size should be 0 after removal", 0, p.getCrewCards().size());
   }

   /**
    * Test the addTradable function within the player class
    *
    */
   @Test
   public void testAddTradable(){
      Player p = generateTestPlayer(0);
      TreasureFactory tf = new TreasureFactory(1);
      LinkedList<Treasure> t = tf.genTreasureList();
      Treasure testTreasure = t.get(0);
      p.setCrewCards(new ArrayList<CrewCard>());

      assertEquals("Cargo should be empty",0,p.getShip().getCargo().size());
      p.addTradable(testTreasure);
      assertEquals("Cargo should now contain 1 treasure", 1, p.getShip().getCargo().size());

      CrewCard testCard = new CrewCard(Color.BLACK,1);

      assertEquals("Hand size should be equal to 0", 0, p.getCrewCards().size());
      p.addCrewCard(testCard);
      assertEquals("Hand size should be 1 after addition of crew card", 1, p.getCrewCards().size());
   }

//Ship Class Tests

   /**
    * Testing the addTreasure method in the Ship class
    *
    */
   @Test
   public void testAddTreasure(){
      Player p = generateTestPlayer(0);
      TreasureFactory tf = new TreasureFactory(1);
      LinkedList<Treasure> t = tf.genTreasureList();

      assertEquals("return true when cargo is empty", true,p.getShip().addTreasure(t.get(0)));

      assertEquals("return true when cargo = 1",true,p.getShip().addTreasure(t.get(1)));

      assertEquals("Return false when cargo >= 2",false,p.getShip().addTreasure(t.get(2)));
   }

   /**
    * Testing the moveTo method in the Ship Class
    *
    */
   @Test
   public void testMoveTo(){
      Game g = generateTestGame();
      Board board = g.getBoard();
      Tile[][] grid = board.getGrid();
      CardFactory cf = new CardFactory(1);
      Player[] ps = g.getPlayers();
      Player p1 = ps[0];

      p1.getShip().moveTo(grid[3][3],board);
      assertEquals("Ship must be on X coordanite 3",3,p1.getShip().getX());
      assertEquals("Ship must be on Y coordanite 3",3,p1.getShip().getY());
   }
}
