/*
* @(#) TestGraphicLoader.java 1.0 2017/04/01
*
* Copyright (c) 2017 Aberystwyth University.
* All rights reserved.
*
*/
import javafx.application.Application;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.ac.aber.cs221.group16.game.GraphicLoader;

import static junit.framework.TestCase.assertNotNull;

/**
 * This Class will contain all tests for the GraphicLoader class.
 *
 * @author Josh Smith
 * @version 1.0
 */
public class TestGraphicLoader {

   /**
    * Runs the JavaFX thread so that tests can be performed.
    * @throws Exception If sleep fails then throws exception.
    */
   @BeforeClass
   public static void setUp() throws Exception {
      System.out.println("Starting JavaFX Thread.");
      Thread t = new Thread("JavaFX Init Thread") {
         public void run() {
            Application.launch(MockApplication.class, new String[0]);
         }
      };
      t.setDaemon(true);
      t.start();
      Thread.sleep(1000);
   }

   /**
    * Tests whether the ships have been loaded by the Graphic Loader.
    *
    */
   @Test
   public void testShipsLoaded() {
      int numPlayers = 4;
      GraphicLoader instance = GraphicLoader.getInstance();
      for (int i = 0; i < numPlayers; i++)
         assertNotNull(String.format("Ship %d has failed to load.", i), instance.getShips()[i]);
   }

   /**
    * Tests whether all of the islands have been correctly loaded by the Graphic Loader.
    *
    */
   @Test
   public void testIslandTilesLoaded() {
      GraphicLoader instance = GraphicLoader.getInstance();
      for (int x = 0; x < instance.getTreasureIslandTiles().length; x++) {
         for (int y = 0; y < instance.getTreasureIslandTiles()[x].length; y++) {
            assertNotNull(String.format("Treasure Island image at x:%d, y:%d failed to load.", x, y),
                    instance.getTreasureIslandTiles()[x][y]);
         }
      }

      for (int x = 0; x < instance.getFlatIslandTiles().length; x++) {
         for (int y = 0; y < instance.getFlatIslandTiles()[x].length; y++) {
            assertNotNull(String.format("Flat Island image at x:%d, y%d failed to load.", x, y),
                    instance.getFlatIslandTiles()[x][y]);
         }
      }

      for (int x = 0; x < instance.getPirateIslandTiles().length; x++) {
         for (int y = 0; y < instance.getPirateIslandTiles().length; y++) {
            assertNotNull(String.format("Pirate Island image at x:%d, y%d failed to load.", x, y),
                    instance.getPirateIslandTiles()[x][y]);
         }
      }
   }

   /**
    * Tests whether or not the Sea Tile has been loaded correctly by theh Graphic Loader.
    *
    */
   @Test
   public void testSeaTileLoaded() {
      GraphicLoader instance = GraphicLoader.getInstance();
      assertNotNull("Sea Tile image failed to load.", instance.getSeaTile());
   }

   /**
    * Tests whether or not the Ports have all be loaded correctly by the Graphic Loader.
    *
    */
   @Test
   public void testPortsLoaded() {
      GraphicLoader instance = GraphicLoader.getInstance();
      assertNotNull("Venice image not loaded.", instance.getVeniceTile());
      assertNotNull("Amsterdam image not loaded.", instance.getAmsterdamTile());
      assertNotNull("Cadiz image not loaded.", instance.getCadizTile());
      assertNotNull("Genoa image not loaded.", instance.getGenoaTile());
      assertNotNull("London image not loaded.", instance.getLondonTile());
      assertNotNull("Marseilles image not loaded.", instance.getMarseillesTile());
   }

   /**
    * Tests whether or no the Bays have all been loaded correctly by the Graphic Loader.
    *
    */
   @Test
   public void testBaysLoaded() {
      GraphicLoader instance = GraphicLoader.getInstance();
      assertNotNull("Mud Bay image not loaded.", instance.getMudBayTile());
      assertNotNull("Cliff Creek image not loaded.", instance.getCliffCreekTile());
      assertNotNull("Anchor Bay image not loaded.", instance.getAnchorBayTile());
   }

   /**
    * Tests whether or not the misc UI elements have been loaded correctly by the Graphic Loader.
    *
    */
   @Test
   public void testUIImagesLoaded() {
      GraphicLoader instance = GraphicLoader.getInstance();
      assertNotNull("Rotate Triangle image not loaded.", instance.getRotateTriangle());
      assertNotNull("Crew Card Stack image not loaded.", instance.getCrewCardStack());
      assertNotNull("Chance Card Stack image not loaded.", instance.getChanceCardStack());
   }

}
