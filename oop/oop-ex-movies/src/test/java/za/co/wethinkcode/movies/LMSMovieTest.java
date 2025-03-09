package za.co.wethinkcode.movies;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.Test;
import person.Person;

import static org.junit.jupiter.api.Assertions.*;

class LMSMovieTest
{
    public static final String TITLE = "Dr Strange";
    public static final String SYNOPSIS_TEXT = "It was a dreadful movie.";
    public static final LocalDate PAST_DATE = LocalDate.now().minusYears( 1L );
    public static final LocalDate FUTURE_DATE = LocalDate.now().plusYears( 1L );
    public static final Person DIRECTOR = new Person( "Scott Derrickson" );
    public static final Duration DURATION = Duration.ofMinutes( 97L );

    //<editor-fold desc="Title attribute tests">
    @Test
    void constructor_titleMayNotBeNull(){
        //#start solution
        assertThrows( NullPointerException.class, () -> new Movie( null, "text", PAST_DATE, DIRECTOR, DURATION ));
        //#end solution
        //#start exercise
        //fail( "TODO" );
        //#end exercise
    }

    @Test
    void constructor_titleMayNotBeEmpty(){
        //#start solution
        assertThrows( IllegalArgumentException.class, () -> new Movie( "", "text", PAST_DATE, DIRECTOR, DURATION ));
        //#end solution
        //#start exercise
        //fail( "TODO" );
        //#end exercise
    }

    @Test
    void thereShouldBeNoSetTitleMethod(){
        assertThrows( NoSuchMethodException.class, () -> Movie.class.getDeclaredMethod( "setTitle", String.class ));
    }
    //</editor-fold>

    //<editor-fold desc="Synopsis attribute tests">
    @Test
    void synopsisTurnsToEmptyIfNull_constructorTest(){
        //#start solution
        Movie testMovie = new Movie( TITLE, null, PAST_DATE, DIRECTOR, DURATION );
        assertEquals( "", testMovie.getSynopsis() );
        //#end solution
        //#start exercise
        //fail( "TODO" );
        //#end exercise
    }

    @Test
    void synopsisTurnsToEmptyIfNull_setterTest(){
        //#start solution
        Movie testMovie = new Movie( TITLE, "Some text", PAST_DATE, DIRECTOR, DURATION );
        testMovie.setSynopsis( null );
        assertEquals( "", testMovie.getSynopsis() );
        //#end solution
        //#start exercise
        //fail( "TODO" );
        //#end exercise
    }
    //</editor-fold>

    //<editor-fold desc="Release-date tests">
    @Test
    void constructor_releaseDateMayNotBeNull(){
        //#start solution
        assertThrows( NullPointerException.class, () -> new Movie( TITLE, SYNOPSIS_TEXT, null, DIRECTOR, DURATION ));
        //#end solution
        //#start exercise
        //fail( "TODO" );
        //#end exercise
    }

    @Test
    void releaseDateChange_releaseDateIsInTheFuture(){
        //#start solution
        Movie testMovie = new Movie( TITLE, "Some text", FUTURE_DATE, DIRECTOR, DURATION );
        LocalDate newReleaseDate = LocalDate.now().plusYears( 2L );
        testMovie.setReleaseDate( newReleaseDate );
        assertEquals( newReleaseDate, testMovie.getReleaseDate() );
        //#end solution
        //#start exercise
        //fail( "TODO" );
        //#end exercise
    }

    @Test
    void releaseDateChange_releaseDateIsPast(){
        //#start solution
        Movie testMovie = new Movie( TITLE, "Some text", PAST_DATE, DIRECTOR, DURATION );
        LocalDate newReleaseDate = LocalDate.now().plusYears( 2L );
        testMovie.setReleaseDate( newReleaseDate );
        assertEquals( PAST_DATE, testMovie.getReleaseDate() );
        //#end solution
        //#start exercise
        //fail( "TODO" );
        //#end exercise
    }
    //</editor-fold>

    //<editor-fold desc="Director attribute tests">
    @Test
    void constructor_directorMayNotBeNull(){
        //#start solution
        assertThrows( NullPointerException.class, () -> new Movie( TITLE, SYNOPSIS_TEXT, PAST_DATE, null, DURATION ));
        //#end solution
        //#start exercise
        //fail( "TODO" );
        //#end exercise
    }

    @Test
    void changeDirector_releaseDateIsPast(){
        //#start solution
        Movie testMovie = new Movie( TITLE, SYNOPSIS_TEXT, PAST_DATE, DIRECTOR, DURATION );
        testMovie.setDirector( new Person( "Sam Raimi" ));
        assertEquals( DIRECTOR, testMovie.getDirector() );
        //#end solution
        //#start exercise
        //fail( "TODO" );
        //#end exercise
    }

    @Test
    void changeDirector_releaseDateIsFuture(){
        //#start solution
        Movie testMovie = new Movie( TITLE, SYNOPSIS_TEXT, FUTURE_DATE, DIRECTOR, DURATION );
        Person newDirector = new Person( "Sam Raimi" );
        testMovie.setDirector( newDirector );
        assertNotEquals( DIRECTOR, testMovie.getDirector() );
        assertEquals( newDirector, testMovie.getDirector() );
        //#end solution
        //#start exercise
        //fail( "TODO" );
        //#end exercise
    }
    //</editor-fold>

    //<editor-fold desc="Run-time attribute tests">
    @Test
    void runTimeMayNotBeNull(){
        //#start solution
        assertThrows( NullPointerException.class, () -> new Movie( TITLE, SYNOPSIS_TEXT, PAST_DATE, DIRECTOR, null ));
        //#end solution
        //#start exercise
        //fail( "TODO" );
        //#end exercise
    }

    @Test
    void thereShouldBeNoPublicSetRunTimeMethod(){
        //#start solution
        Set<Method> methods = Set.of( Movie.class.getDeclaredMethods() );
        assertFalse( methods.parallelStream()
            .anyMatch( method -> method.getParameterCount() == 1
                && Arrays.stream( method.getParameterTypes() ).anyMatch( aType -> aType.equals( Duration.class ) )
                && !Modifier.isPrivate( method.getModifiers() )));
        //#end solution
        //#start exercise
        //fail( "TODO" );
        //#end exercise
    }
    //</editor-fold>
}