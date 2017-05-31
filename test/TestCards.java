/**
 /*
 * @(#) TestCards.java 1.0 2016/05/01
 *
 * Copyright (c) 2016 Aberystwyth University.
 * All rights reserved.
 *
 */


import javafx.scene.paint.Color;
import org.junit.Test;
import uk.ac.aber.cs221.group16.game.Game;
import uk.ac.aber.cs221.group16.game.items.cards.CardFactory;
import uk.ac.aber.cs221.group16.game.items.cards.ChanceCard;
import uk.ac.aber.cs221.group16.game.items.cards.CrewCard;
import uk.ac.aber.cs221.group16.game.players.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import static org.junit.Assert.assertEquals;


/**
 * This class contains the Unit Tests for the Cards package.
 *
 * @author Luke Revill
 * @version 1.0 - Cards Unit testing
 */

public class TestCards {

   /**
    * Generates list of Players for use to retrieve multiple players
    * @return p, where p is an Array of Players
    */
   private Player[] generateTestPlayerList() {
      Game g = generateTestGame();
      Player[] p = g.getPlayers();
      return p;
   }

   /**
    * Generates test player for use within the tests
    *
    * @return p
    */
   private Player generateTestPlayer(int pNum) {
      Player[] p = generateTestPlayerList();
      Player p1 = p[pNum];
      p1.setCrewCards(generateTestHand(3, 1));
      return p1;
   }

   /**
    * #Generates a set hand to be used for tests
    *
    * @return hand
    */
   private ArrayList<CrewCard> generateTestHand(int b, int r) {
      ArrayList<CrewCard> hand = new ArrayList<>();

      hand.add(new CrewCard(Color.RED, r));
      hand.add(new CrewCard(Color.BLACK, b));
      return hand;
   }

   /**
    * Generates a new game for usage in tests
    *
    * @return g
    */
   private Game generateTestGame() {
      ArrayList<String> n = new ArrayList<>();
      for (int i = 0; i < 4; i++) {
         n.add("Luke");
      }

      Game g = new Game(n, null);
      return g;
   }

   /**
    * Test the getInOrderCrewCard method from the CardFactory class
    */
   @Test
   public void testGetInOrderCrewCards() {
      Color tCol;
      boolean correct;
      int cardCounter = 0;
      CardFactory cf = new CardFactory(6);
      ArrayList<CrewCard> cards = cf.getInOrderCrewCards();
      for (int j = 1; j <= 3; j++) {
         for (int c = 0; c < 2; c++) {
            if (c == 0) {
               tCol = Color.RED;
            } else
               tCol = Color.BLACK;
            for (int i = 0; i < 6; i++) {
               if (cards.get(cardCounter).getColor() == tCol && cards.get(cardCounter).getValue() == j) {
                  correct = true;
               } else
                  correct = false;
               assertEquals("Each card in ArrayList needs to be the correct value and color", true, correct);
               cardCounter++;
            }
         }
      }
   }

   /**
    * Test the getShuffledCrewCards method from the CardFactoryClass
    */
   @Test
   public void testGetShuffledCrewCards() {
      CardFactory cf = new CardFactory(6);
      int failCount = 0;
      boolean pass = true;
      boolean failLoop = false;
      for (int i = 0; i < 3; i++) {
         Queue<CrewCard> cards = cf.getShuffledCrewCards();
         int repeated = 0;
         CrewCard tempCard = cards.poll();
         for (int j = 0; i > 35; j++) {
            if (cards.peek() == tempCard) {
               repeated++;
            }
            else
               repeated = 0;
            if (repeated > 2) {
               failLoop = true;
            }
            if (failLoop == true)
               failCount++;

         tempCard = cards.poll();
         }

         if (failCount == 3) {
            pass = false;
         }
         assertEquals("Tests for repeats within deck, cannot fail more than 3 times", true, pass);
      }
   }


   /**
    * Tests the getInOrderChanceCard function from the CardFactory Class
    *  ######WILL FAIL UNTIL CHANCE CARDS ARE IMPLEMENTED#######
    */
   @Test
   public void testGetInOrderChanceCards(){
      CardFactory cf = new CardFactory(1);
      List<ChanceCard> chCards = cf.getInOrderChanceCards();
      int i = 1;
      for (ChanceCard card: chCards) {
         assertEquals(String.format("Chance Card id needs to equal %d", i),i, card.getCardID());
         i++;
      }
   }

   /**
    * Tests the getShuffledChanceCards function
    * Makes sure that the cards have been shuffled properly
    *  ######WILL NOT FUNCTION UNTIL CHANCE CARDS ARE IMPLEMENTED#######
    */
   @Test
   public void testGetShuffledChanceCards(){
      CardFactory cf = new CardFactory(1);
      int failCount = 0;
      boolean pass = true;

      for(int j = 0; j < 3; j++) {
         Queue<ChanceCard> chCards= cf.getShuffledChanceCards();
         boolean failLoop = false;

         int proxCount = 0;
         ChanceCard tempCard = chCards.poll();
         for (int i = 0; i < 28 - 1; i++) {
            if (Math.abs(tempCard.getCardID() - chCards.peek().getCardID()) < 1) {
               proxCount++;
            }
            if (proxCount > 3) {
               failLoop = true;
            }
         }
         if (failLoop == true) {
            failCount++;
         }
      }
   if (failCount >= 3){
         pass = false;
   }
   assertEquals("Must not fail 3 passes of checking the shuffle",true,pass);
   }
}