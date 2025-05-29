package za.co.wethinkcode.botworld.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static za.co.wethinkcode.botworld.model.Heading.*;

public class ExplorerBotTest
{
    private static final int MIDDLE_X = ExplorerBot.WORLD_MAX_X / 2;
    private static final int MIDDLE_Y = ExplorerBot.WORLD_MAX_Y / 2;
    
    // This test creates a bot in an illegal position - its X coordinate is too small,
    // and the test therefore expects the ExplorerBot constructor to throw an
    // IllegalArgumentException.
    @Test
    void createBot_initialPositionIsOutsideTheWorld_xTooSmall(){
        assertThrows( IllegalArgumentException.class, () -> new ExplorerBot( -1, MIDDLE_Y ));
    }

    // This test creates a bot in an illegal position - its X coordinate is greater than the World's WIDTH,
    // and the test therefore expects the ExplorerBot constructor to throw an IllegalArgumentException.
    @Test
    void createBot_initialPositionIsOutsideTheWorld_xTooLarge(){
        assertThrows( IllegalArgumentException.class, () -> new ExplorerBot( ExplorerBot.WORLD_MAX_X + 1, 0 ));
    }

    @Test
    void createBot_outsideWorld_yTooSmall(){
        assertThrows( IllegalArgumentException.class, () -> new ExplorerBot( MIDDLE_X, -1 ));
    }

    @Test
    void createBot_outsideWorld_yTooLarge(){
        assertThrows( IllegalArgumentException.class, () -> new ExplorerBot( MIDDLE_Y, ExplorerBot.WORLD_MAX_Y + 1 ));
    }

    // Here's a test creating a Bot INSIDE the world (at last!) This means the test should pass if the
    // Bot gets created successfully, and fails if any exceptions (or other errors) occur.
    @Test
    void createBot_insideTheWorld(){
        ExplorerBot bot =  new ExplorerBot( MIDDLE_X, MIDDLE_Y );
    }

    // Tests of the `turnTo` and `move` methods

    // We'll give you the first one for free. After this you're on your own...
    @Test
    void moveNorth_destinationIsInsideTheWorld(){
        ExplorerBot bot =  new ExplorerBot( MIDDLE_X, MIDDLE_Y );
        bot.turnTo( N );
        bot.move();
        assertEquals( MIDDLE_Y - 1, bot.yPosition() );
    }

    //...what about the other directions?...

    @Test
    void moveNorth_DestinationIsOutsideTheWorld(){
        ExplorerBot bot =  new ExplorerBot( MIDDLE_X, 0 );
        bot.turnTo( N );
        bot.move();
        assertEquals( 0, bot.yPosition() );
    }

    //...more tests, please!
}