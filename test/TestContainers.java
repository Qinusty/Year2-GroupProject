/**
 /*
 * @(#) TestContainers.java 1.0 2016/05/02
 *
 * Copyright (c) 2016 Aberystwyth University.
 * All rights reserved.
 *
 */


import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import uk.ac.aber.cs221.group16.game.container.FlatIslandContainer;
import uk.ac.aber.cs221.group16.game.container.PirateIslandContainer;
import uk.ac.aber.cs221.group16.game.container.TreasureIslandContainer;
import uk.ac.aber.cs221.group16.game.items.cards.CardFactory;
import uk.ac.aber.cs221.group16.game.items.cards.ChanceCard;
import uk.ac.aber.cs221.group16.game.items.cards.CrewCard;
import uk.ac.aber.cs221.group16.game.items.treasures.Treasure;
import uk.ac.aber.cs221.group16.game.items.treasures.TreasureFactory;

import static org.junit.Assert.*;


/**
 * This class contains the Unit Tests for the Containers package.
 *
 * @author Luke Revill
 * @author Dawid Bernacki
 * @version 1.0 - Containers Unit testing
 */
public class TestContainers {


   /**
    * Sets the List of Test Treasures to flatIslandContainer
    *
    * @param fi, where fi is the flatIslandContainer
    */
   private void  setFITestTreasures(FlatIslandContainer fi){
      fi.setTreasurePile(getTestTreasures());
   }


   /**
    * generates a list of test Treasures
    *
    * @return treasures which is a ArrayList of type Treasure
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
    * Generates a list of test CrewCards
    *
    * @return cardQueue which is a Queue of type CrewCard
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

//Flat Island Container Tests

   /**
    * Tests the SetTreasurePile method within the flatIslandContainer Class
    */
   @Test
   public void testFISetTreasurePile(){
      FlatIslandContainer fIsland = new FlatIslandContainer();
      ArrayList<Treasure> treasures = getTestTreasures();
      assertEquals("treasurePile should be empty",0,fIsland.getTreasurePile().size());
      fIsland.setTreasurePile(treasures);
      int i = 0;
      for (Treasure t: fIsland.getTreasurePile()){
           assertEquals("Treasure in treasurePile should equal treasures for each index",
                   treasures.get(i).getType(),t.getType());
         i++;
      }
   }

   /**
    * Tests the getTreasurePile method within the flatIslandContainer Class
    */
   @Test
   public void testFIGetTreasurePile(){
      FlatIslandContainer fIsland = new FlatIslandContainer();
      setFITestTreasures(fIsland);
      ArrayList<Treasure> treasures = getTestTreasures();
      int i = 0;
      for (Treasure t: treasures){
         assertEquals("Treasure in treasurePile should equal treasures for each index",
                 t.getType(),fIsland.getTreasurePile().get(i).getType());
         i++;
      }
   }

   /**
    * Tests the getCrewCards method within the flatIslandContainer Class
    */
   @Test
   public void testFIGetCrewCards(){
      FlatIslandContainer fIsland = new FlatIslandContainer();
      Queue<CrewCard> cards = new LinkedList<>() ;
      fIsland.setCrewCards(cards);
      assertEquals("Flat island container should be empty of crew cards",
              0,fIsland.getCrewCards().size());
      cards = getTestCrewCards();
      fIsland.setCrewCards(cards);
      assertEquals("Flat island container should contain 6 crew cards",
              6,fIsland.getCrewCards().size());
   }

   /**
    *Tests the setCrewCards method within the flatIslandContainer Class
    */
   @Test
   public void testFISetCrewCards(){
      FlatIslandContainer fIsland = new FlatIslandContainer();
      Queue<CrewCard> testCards = getTestCrewCards();
      fIsland.setCrewCards(testCards);

      for (CrewCard card: fIsland.getCrewCards()) {
         boolean equal = false;
         if (testCards.contains(card)) {
            equal = true;
         }
         assertEquals("The Cards in container should go be created one of each in order",true,equal);
      }

   }

   /**
    * Tests the takeCards method within the FlatIslandContainer Class
    */
   @Test
   public void testFITakeCards(){
      FlatIslandContainer fIsland = new FlatIslandContainer();
      fIsland.setCrewCards(getTestCrewCards());
      ArrayList<CrewCard> cards = new ArrayList<>();
      assertEquals("Cards should be emtpy", 0, cards.size());
      cards = fIsland.takeCards();
      assertEquals("No of cards should equal 6", 6,cards.size());
   }

   /**
    * Tests the takeTreasure method within the FlatIslandContainer Class
    */
   @Test
   public void testFITakeTreasure(){
      FlatIslandContainer fIsland = new FlatIslandContainer();

      ArrayList<Treasure> t = getTestTreasures();
      assertEquals("Treasures should be empty", 0, fIsland.getTreasurePile().size());
      fIsland.setTreasurePile(t);
      int i = 0;
      for (Treasure treasures: fIsland.getTreasurePile()) {

         assertEquals("Treasures should the same",t.get(i),treasures);
         i++;
      }
   }

   /**
    * Tests the addTreasure method within the FlatIslandContiner Class
    */
   @Test
   public void testFIAddTreasure(){
      FlatIslandContainer fIsland = new FlatIslandContainer();
      ArrayList<Treasure> t = getTestTreasures();

      assertEquals("Treasures should be empty", 0, fIsland.getTreasurePile().size());
      fIsland.addTreasure(t.get(0));
      assertEquals("Treasure in FlatIslandContainer should equal t",
              t.get(0),fIsland.getTreasurePile().get(0));

   }

   //Pirate Island Container Tests

   /**
    * Tests the getTreasure and setTreasure methods within the piratetIslandContainer Class
    */
   @Test
   public void testPIGetAndSetTreasure(){
      ArrayList<Treasure> treasures;
      Queue<CrewCard> testCrew = new LinkedList<>();
      PirateIslandContainer pIsland = new PirateIslandContainer(testCrew);
      treasures = getTestTreasures();

      assertEquals("Treasure on Pirate island should be 0",
              0,pIsland.getTreasure().size() );
      pIsland.setTreasure(treasures);
      int i = 0;
      for (Treasure t: pIsland.getTreasure()) {

         assertEquals("Treasure needs to equal Treasures generated",
                 treasures.get(i),t);
         i++;
      }

   }

   /**
    * Tests the getCrewCards and setCrewCards methods within the pirateIslandContainer Class
    */
   @Test
   public void testPIGetAndSetCrewCards(){
      Queue<CrewCard> testCrew = new LinkedList<>();
      PirateIslandContainer pIsland = new PirateIslandContainer(testCrew);
      assertEquals("Initial CrewCards on pirate island should be 0",
              0,testCrew.size());
      testCrew = getTestCrewCards();
      pIsland.setCrewCards(testCrew);
      for (CrewCard c: pIsland.getCrewCards()) {

         boolean contains = false;

         if(testCrew.contains(c)){
            contains = true;
         }
         assertEquals("CrewCards need to equal cards generated",
                 true,contains);
      }
   }

   /**
    * Tests the addTreasure method within the pirateIslandContainer Class
    */
   @Test
   public void testPIAddTreasure(){
      Queue<CrewCard> cards = new LinkedList<>();
      PirateIslandContainer pIsland = new PirateIslandContainer(cards);

      Treasure t = getTestTreasures().get(0);
      pIsland.addTreasure(t);
      assertEquals("Treasure added to PirateIslandContainer needs to equal " +
              "generated Treasure",t,pIsland.getTreasure().get(0));
   }

   /**
    * Tests the addCrewCard method within the pirateIslandContainerClass
    */
   @Test
   public void testPIAddCrewCard(){
      Queue<CrewCard> cards = new LinkedList<>();
      PirateIslandContainer pIsland = new PirateIslandContainer(cards);

      CrewCard testCard = getTestCrewCards().poll();
      pIsland.addCrewCard(testCard);
      assertEquals("CrewCard added to PirateIslandContainer needs to " +
              "equal generated CrewCard",testCard,pIsland.takeCrewCard());
   }

   //Treasure Island Container Tests

   /**
    * Tests the getTreasure and setTreasure methods within the TreasureIslandContainer Class
    */
   @Test
   public void testTIGetSetTreasure(){
      ArrayList<Treasure> treasures;
      Queue<CrewCard> testCrew = new LinkedList<>();
      TreasureFactory tf = new TreasureFactory(0);
      CardFactory cf = new CardFactory(0);
      TreasureIslandContainer ti = new TreasureIslandContainer(tf.genTreasureList()
              ,cf.getShuffledChanceCards());
      treasures = getTestTreasures();

      assertEquals("Treasure on Treasure Island should be 0", 0
              , ti.getTreasures().size());
      ti.setTreasure(treasures);
      int i = 0;
      for (Treasure t: ti.getTreasures()){
         assertEquals("Treasure need to equal the generated treasure", treasures.get(i),t);
         i++;
      }
   }

   /**
    * Tests the getChanceCards method within the TreasureIslandContainer class
    */
   @Test
   public void testTIGetChanceCards(){
      CardFactory cf = new CardFactory(0);
      TreasureFactory tf = new TreasureFactory(0);
      List<ChanceCard> testChance = cf.getInOrderChanceCards();
      TreasureIslandContainer tIsland = new TreasureIslandContainer(tf.genTreasureList(),
              cf.getShuffledChanceCards());

      assertEquals("Initial Chance cards on Treasure island should be 28",
              28, tIsland.getChanceCards().size());
   }

   /**
    * Tests the takeChanceCards method within the TreasureIslandContainer class
    */
   @Test
   public void testTITakeChanceCards(){
      CardFactory cf = new CardFactory(0);
      TreasureFactory tf = new TreasureFactory(0);
      TreasureIslandContainer tIsland = new TreasureIslandContainer(tf.genTreasureList(), cf.getShuffledChanceCards());

      assertEquals("Chance cards on Treasure island should be 28", 28, tIsland.getChanceCards().size());
      ChanceCard testChanceCard  = tIsland.takeChanceCard();
      assertEquals("Chance cards on Treasure island should decrease to 27", 27, tIsland.getChanceCards().size());
   }

   /**
    * Tests the putChanceCard method within the TreasureIslandContainer Class
    */
   @Test
   public void testTIPutChanceCard(){
      CardFactory cf = new CardFactory(0);
      TreasureFactory tf = new TreasureFactory(0);

      TreasureIslandContainer tIsland = new TreasureIslandContainer(tf.genTreasureList(), cf.getShuffledChanceCards());

      assertEquals("Chance cards on Treasure island should be 28", 28, tIsland.getChanceCards().size());
      ChanceCard cc = tIsland.takeChanceCard();
      assertEquals("Chance cards on Treasure island should be decreased to 27", 27, tIsland.getChanceCards().size());
      tIsland.putChanceCard(cc);
      assertEquals("Chance cards on treasure island should go back up to 28", 28, tIsland.getChanceCards().size());
   }

   /**
    * Tests the addTreasure method within the TreasureIslandContainer Class
    */
   @Test
   public void testTIAddTreasure(){
      TreasureFactory tf = new TreasureFactory(0);
      CardFactory cf = new CardFactory(0);
      TreasureIslandContainer tIsland = new TreasureIslandContainer(tf.genTreasureList(),cf.getShuffledChanceCards());
      ArrayList<Treasure> t = getTestTreasures();
      assertEquals("Treasures should be empty", 0, tIsland.getTreasures().size());
      tIsland.addTreasure(t.get(0));
      assertEquals("Treasure in TreasureIslandContainer should equal t", t.get(0),tIsland.getTreasures().get(0));
   }
}
