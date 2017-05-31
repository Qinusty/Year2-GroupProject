/**
 /*
 * @(#) TestTiles.java 1.0 2016/05/01
 *
 * Copyright (c) 2016 Aberystwyth University.
 * All rights reserved.
 *
 */


import org.junit.Test;
import uk.ac.aber.cs221.group16.game.Game;
import uk.ac.aber.cs221.group16.game.container.FlatIslandContainer;
import uk.ac.aber.cs221.group16.game.container.TreasureIslandContainer;
import uk.ac.aber.cs221.group16.game.items.cards.CardFactory;
import uk.ac.aber.cs221.group16.game.items.cards.CrewCard;
import uk.ac.aber.cs221.group16.game.items.treasures.Treasure;
import uk.ac.aber.cs221.group16.game.items.treasures.TreasureFactory;
import uk.ac.aber.cs221.group16.game.map.tiles.*;
import uk.ac.aber.cs221.group16.game.players.Player;
import java.util.*;

import static org.junit.Assert.assertEquals;


/**
 * This class contains the Unit Tests for the Cards package.
 *
 * @author Luke Revill
 * @version 1.0 - Tiles Unit testng
 */
public class TestTiles {

   /**
    * Generates a new game for usage in tests
    * @return g, where g is a object of type Game
    */
   private Game generateTestGame() {
      ArrayList<String> n = new ArrayList<>();
      for (int i = 0; i < 4; i++) {
         n.add("Luke");
      }
      return new Game(n, null);
   }

   /**
    *  Sets the treasure in a FlatIslandContaine to a specified set of treasures
    * @param fi, where fi is a object of type FlatIslandContainer
    */
   private void  setFITestTreasures(FlatIslandContainer fi){
      fi.setTreasurePile(getTestTreasures());
   }

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
    * @return p, where p is a specified generated Player
    */
   private Player generateTestPlayer(int pNum){
      Player[] p = generateTestPlayerList();
      Player p1 = p[pNum];
      return p1;
   }

   /**
    * Generates a list of test Treasures
    * @return treasures, where treasures is an ArrayList containing specified Treasures
    */
   private ArrayList<Treasure> getTestTreasures(){
      ArrayList<Treasure> treasures = new ArrayList<>();
      TreasureFactory tf = new TreasureFactory(1);
      LinkedList<Treasure> tempT = tf.genTreasureList();
      for (Treasure temp: tempT) {
         treasures.add(temp);
      }
      return treasures;
   }

   /**
    * Generates a Queue of CrewCards for use within testing
    * @return cardQueue, where cardQueue is a Queue containing specified crewcards
    */
   private Queue<CrewCard> getTestCrewCards(){
      CardFactory cf = new CardFactory(1);
      ArrayList<CrewCard> cards = cf.getInOrderCrewCards();
      Queue<CrewCard> cardQueue = new LinkedList<>();
      for(CrewCard card : cards) {
         cardQueue.add(card);
      }
      return cardQueue;
   }

   /**
    * Generates a FlatIslandContainer for use within testing
    * @return fIC, where fIC is a object of type FlatIslandContainer
    */
   private FlatIslandContainer generateTestFlatIslandContainer(){
      FlatIslandContainer fIC = new FlatIslandContainer();
      fIC.setCrewCards(getTestCrewCards());
      setFITestTreasures(fIC);
      return fIC;
   }

   /**
    * Generates a FlatIslandTile for use within testing
    * @return Object of type FlatIslandTile
    */
   private FlatIslandTile generateTestFlatIslandTile(){
      FlatIslandContainer testFIC = generateTestFlatIslandContainer();
      return new FlatIslandTile(0,0,0,0,testFIC);
   }

   /**
    * Generates a PortTile for use within testing
    * @return pT, where pT is a object of type PortTile
    */
   private PortTile generateTestPortTile(){
      PortTile pT = new PortTile(Port.London,1,4);
      return pT;
   }

   //FlatIslandTile tests

   /**
    * Tests the takeCrewCards method within the FlatIslandTile
    */
   @Test
   public void testTakeCrewCards() {
      FlatIslandTile tFIT = generateTestFlatIslandTile();
      ArrayList<CrewCard> cards;
      Queue<CrewCard> testCards = getTestCrewCards();
      cards = tFIT.takeCrewCards();

      for(CrewCard card : cards){
         boolean equal = false;
         if(card.getValue() == testCards.peek().getValue() && card.getColor()
                 == testCards.peek().getColor()){
            equal = true;
            assertEquals("Cards in FlatIslandContainer should be the same as the generated list"
                    ,true,equal);
         }
      }
   }

   /**
    * Tests the takeTreasure method within the FlatIslandTile class
    */
   @Test
   public void testTakeTreasure(){
         FlatIslandTile tFIT = generateTestFlatIslandTile();
         ArrayList<Treasure> treasures = tFIT.takeTreasure(2);
         assertEquals("The first treasure returned should be Rubies","Rubies",treasures.get(0).getType());
         assertEquals("The second treasure returned should be Diamonds", "Diamonds",treasures.get(1).getType());
      }

   //PortTile tests

   /**
    * Tests the addTreasure method within the PortTile Class
    */
   @Test
   public void testAddTreasure(){
      PortTile pT = generateTestPortTile();
      pT.addTreasure(getTestTreasures().get(0));
      assertEquals("Treasure should equal Diamonds",
              "Diamonds",pT.getTreasureSet().iterator().next().getType());
   }

   /**
    * Tests the addCrewCard method within the PortTile class
    */
   @Test
   public void testAddCrewCard(){
      PortTile pT = generateTestPortTile();
      pT.addCrewCard(getTestCrewCards().poll());
      CrewCard c = (getTestCrewCards().poll());
      assertEquals("Card color should equal Red",c.getColor(),
              pT.getCrewCardSet().iterator().next().getColor());
      assertEquals("Card value should equal 1",c.getValue(),
              pT.getCrewCardSet().iterator().next().getValue());
   }

   /**
    * Tests the getTotalValue method within the PortTile class
    */
   @Test
   public void  testGetTotalValue(){
      PortTile pT = generateTestPortTile();
      ArrayList<Treasure> treasures = getTestTreasures();
      for(Treasure t : treasures) {
         pT.addTreasure(t);
      }
      assertEquals("Total value of treasure should equal 19",19,pT.getTotalValue());
   }

   /**
    * Tests the addTradeable method within the PortTile class
    */
   @Test
   public void testAddTradeable(){
      PortTile pT = generateTestPortTile();
      pT.addTradable(getTestTreasures().get(0));
      assertEquals("Treasure should equal Diamonds",
              "Diamonds",pT.getTreasureSet().iterator().next().getType());
      pT.addTradable(getTestCrewCards().poll());
      CrewCard c = (getTestCrewCards().poll());
      assertEquals("Card color should equal Red",c.getColor(),
              pT.getCrewCardSet().iterator().next().getColor());
      assertEquals("Card value should equal 1",c.getValue(),
              pT.getCrewCardSet().iterator().next().getValue());
   }

   /**
    * tests the removeTradeable method within the PortTile Class
    */
   @Test
   public void testRemoveTradeable(){
      PortTile pT = generateTestPortTile();
      Treasure t = getTestTreasures().get(0);
      CrewCard c = (getTestCrewCards().poll());
      pT.addTradable(t);
      pT.addTradable(c);
      assertEquals("Treasure should equal Diamonds",
              "Diamonds",pT.getTreasureSet().iterator().next().getType());
      pT.addTradable(getTestCrewCards().poll());

      assertEquals("Card color should equal Red",c.getColor(),
              pT.getCrewCardSet().iterator().next().getColor());
      assertEquals("Card value should equal 1",c.getValue(),
              pT.getCrewCardSet().iterator().next().getValue());

      pT.removeTradable(t);
      assertEquals("Port should now not contain t",
              false, pT.getTreasureSet().contains(t));
      pT.removeTradable(c);
      assertEquals("Port should now not contain c",
              false,pT.getCrewCardSet().contains(c));

   }


   //Tile tests

   /**
    * Tests the putShip method within the Tile Class
    */
   @Test
   public void testPutShip(){
      Game g = generateTestGame();
      Player p = generateTestPlayer(0);
      Tile[][] grid = g.getBoard().getGrid();
      assertEquals("Grid location 5,5 should be empty",false,grid[5][5].isOccupied());
      grid[5][5].putShip(p.getShip());
      assertEquals("Grid location 5,5 should contain a ship",true,grid[5][5].isOccupied());
   }

   /**
    * Tests the removeShip method within the Tile class
    */
   @Test
   public void testRemoveShip(){
      Game g = generateTestGame();
      Player p = generateTestPlayer(0);
      Tile[][] grid = g.getBoard().getGrid();
      grid[5][5].putShip(p.getShip());
      assertEquals("Grid location 5,5 should contain a ship",true,grid[5][5].isOccupied());
      grid[5][5].removeShip(p.getShip());
      assertEquals("Grid location 5,5 should be empty",false,grid[5][5].isOccupied());
   }

   /**
    * Tests the isOccupied method within the Tile class
    */
   @Test
   public void testIsOccupied(){
      Game g = generateTestGame();
      Player p = generateTestPlayer(0);
      Tile[][] grid = g.getBoard().getGrid();
      assertEquals("isOccupied for grid 5,5 should be false", false, grid[5][5].isOccupied());
      grid[5][5].putShip(p.getShip());
      assertEquals("isOccupied for grid 5,5 should now be true",true,grid[5][5].isOccupied());
   }

   //TreasureIslandTile tests

   /**
    * Tests the getTreasuresOfValue within the TreasureIslandTile class
    */
   @Test
   public void testGetTreasuresOfValue(){
      CardFactory cf = new CardFactory(6);
      TreasureFactory tf = new TreasureFactory(4);
      TreasureIslandContainer tIC = new TreasureIslandContainer(tf.genTreasureList()
              ,cf.getShuffledChanceCards());
      TreasureIslandTile tIT = new TreasureIslandTile(0,0,0,0,tIC);
      Set<Treasure> treasures = tIC.getTreasuresOfValue(5, 2, true);
      int val = 0;
      for(Treasure t: treasures){
         val = val + treasures.iterator().next().getValue();
      }
      assertEquals("If treasures value = 5", 5, val);
   }

   /**
    * Tests the values of treasure assigned to trade ports
    */
   @Test
   public void testPortValues(){
      for(int i = 0; i < 5; i++) {
         Game g = generateTestGame();
         int valueA = g.getBoard().getAmsterdamTile().getTotalValue();
         for (CrewCard c : g.getBoard().getAmsterdamTile().getCrewCardSet()) {
            valueA = valueA + c.getValue();
         }
         int valueV = g.getBoard().getVeniceTile().getTotalValue();
         for (CrewCard c : g.getBoard().getVeniceTile().getCrewCardSet()) {
            valueV = valueV + c.getValue();
         }
         assertEquals("Total value of Amsterdam trade items = 8", 8, valueA);
         assertEquals("Total value of Venice trade items = 8", 8, valueV);
      }
   }
}
