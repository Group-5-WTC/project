package wethinkcode.places;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;

import org.junit.jupiter.api.*;
import wethinkcode.places.model.Places;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-test suite for the CSV parser.
 */
public class PlacesCsvParserTest
{
    private final LineNumberReader input = new LineNumberReader( new StringReader( PlacesTestData.CSV_DATA ));
    private final PlacesCsvParser parser = new PlacesCsvParser();

    // The first 3 tests are about checking the basic parsing of a line of CSV data.

    @Test
    public void firstLineGetsSkipped() throws IOException {
        parser.parseCsvSource( input );
        assertEquals( 13, input.getLineNumber() );
    }

    @Test
    public void splittingALineIntoValuesProducesCorrectNumberOfValues(){
        final String testLine = "Brakpan,Non_Perennial,92797,-26.60444444,26.34,01-06-1992,,North West,66,,262,8,16,DC40,Matlosana,,,NW403,,";
        final String[] values = parser.splitLineIntoValues( testLine );
        assertTrue( values.length > PlacesCsvParser.MIN_COLUMNS );
    }

    @Test
    public void splittingALineWithTheWrongNumberOfValuesReportsAnError(){
        final String testLine = "Brakpan,Non_Perennial,92797,-26.60444444,26.34,01-06-1992,,North West,66,,262,8,16,DC40,Matlosana,,,,";
        final String[] values = parser.splitLineIntoValues( testLine );
        assertFalse( values.length < PlacesCsvParser.MIN_COLUMNS );
    }

    @Test
    public void urbanAreasAreWanted(){
        final String testLine = "Brakpan,Urban Area,92799,-26.23527778,28.37,31-05-1995,,Gauteng,114,,280,3,16,EKU,Ekurhuleni Metro,,,EKU,,\n";
        final String[] values = parser.splitLineIntoValues( testLine );
        assertEquals( "urban area", values[PlacesCsvParser.FEATURE_COLUMN].toLowerCase() );
        assertTrue( parser.isLineAWantedFeature( values ));
    }

    // The next 3 tests are about filtering the bulk data to extract only data that is
    // relevant to out needs.
    //
    // Ask yourself: Are Urban Areas and Towns the *only* features where electricity gets delivered?
    // If you identify and *other* kinds of places that are relevant, you'll need to add unit tests
    // to verify that your code filters correctly for those, too.

    @Test
    public void townsAreWanted(){
        final String testLine = "Brakpan,Town,92802,-27.95111111,26.53333333,30-05-1975,,Free State,68,,155,2,16,DC18,Matjhabeng,,,FS184,,";
        final String[] values = parser.splitLineIntoValues( testLine );
        assertEquals( "town", values[PlacesCsvParser.FEATURE_COLUMN].toLowerCase() );
        assertTrue( parser.isLineAWantedFeature( values ));
    }

    @Test
    public void otherFeaturesAreNotWanted(){
        final String testLine = "Amatikulu,Station,95756,-29.05111111,31.53138889,31-05-1989,,KwaZulu-Natal,79,,237,4,16,DC28,uMlalazi,,,KZ284,,";
        final String[] values = parser.splitLineIntoValues( testLine );
        assertEquals( "station", values[PlacesCsvParser.FEATURE_COLUMN].toLowerCase() );
        assertFalse( parser.isLineAWantedFeature( values ));
    }

    @Test
    public void parseBulkTestData(){
        final Places db = parser.parseDataLines( input );
        assertEquals( 5, db.size() );
    }
}