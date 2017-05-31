/*
* @(#) TestTreasures.java 1.0 2016/03/16
*
* Copyright (c) 2016 Aberystwyth University.
* All rights reserved.
*
*/



import org.junit.*;
import static org.junit.Assert.*;

import uk.ac.aber.cs221.group16.game.items.treasures.Treasure;
import uk.ac.aber.cs221.group16.game.items.treasures.TreasureFactory;

import java.util.LinkedList;

/**
 * This class contains the Unit Tests for the treasures package.
 *
 * @author Luke Revill
 * @version 1.0 -  Treasures Unit testing
 */

public class TestTreasures {

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
    * Test for the values of each type of Treasure's value
    */
   @Test
   public void testTreasureValues() {
      LinkedList<Treasure> testTreasures = generateTestTreasures();
      assertEquals("Diamond value should equal 5", 5, testTreasures.get(0).getValue());
      assertEquals("Gold value should equal 4", 4, testTreasures.get(1).getValue());
      assertEquals("Pearl value should equal 3", 3, testTreasures.get(2).getValue());
      assertEquals("Ruby value should equal 5", 5, testTreasures.get(3).getValue());
      assertEquals("Rum value should equal 2", 2, testTreasures.get(4).getValue());
   }

   /**
    * Test for the Type of each Treasure
    */
   @Test
   public void testTreasureType(){
      LinkedList<Treasure> testTreasures = generateTestTreasures();
      assertEquals("Treasure type should equal \"Diamonds\"",
              "Diamonds", testTreasures.get(0).getType());
      assertEquals("Treasure type should equal \"Gold Bars\"",
              "Gold Bars", testTreasures.get(1).getType());
      assertEquals("Treasure type should equal \"Pearls\"",
              "Pearls", testTreasures.get(2).getType());
      assertEquals("Treasure type should equal \"Rubies\"",
              "Rubies", testTreasures.get(3).getType());
      assertEquals("Treasure type should equal \"Barrel of Rum\"",
              "Barrel of Rum", testTreasures.get(4).getType());
   }

   @Test
   public void testGenTreausreList(){
      TreasureFactory tf = new TreasureFactory(1);
      LinkedList<Treasure> testTreasures = tf.genTreasureList();
      assertEquals("Treasure type should equal \"Diamonds\"",
              "Diamonds", testTreasures.get(0).getType());
      assertEquals("Treasure type should equal \"Gold Bars\"",
              "Gold Bars", testTreasures.get(1).getType());
      assertEquals("Treasure type should equal \"Pearls\"",
              "Pearls", testTreasures.get(2).getType());
      assertEquals("Treasure type should equal \"Rubies\"",
              "Rubies", testTreasures.get(3).getType());
      assertEquals("Treasure type should equal \"Barrel of Rum\"",
              "Barrel of Rum", testTreasures.get(4).getType());
   }

}

