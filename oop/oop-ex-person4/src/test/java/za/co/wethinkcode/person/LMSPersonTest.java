package za.co.wethinkcode.person;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LMSPersonTest
{
    @Test
    void personClassContainsDateValidationMethod() throws NoSuchMethodException {
        final Method validationMethod = Person.class.getDeclaredMethod( "validateDateOfBirth", LocalDate.class );
        assertEquals( Void.TYPE, validationMethod.getReturnType() );
        assertTrue( Modifier.isPrivate( validationMethod.getModifiers() ));
    }

    @Test
    void birthDateCanBeChangedToPastDate(){
        Person testPerson = new Person( "Dave" );
        assertNull( testPerson.birthDate() );

        LocalDate now = LocalDate.now();
        testPerson.birthDate( now.minusDays( 2L ));
        assertNotNull( testPerson.birthDate() );
    }

    @Test
    void birthDateCanNotBeChangedToFutureDate(){
        Person testPerson = new Person( "Dave" );
        assertNull( testPerson.birthDate() );

        LocalDate now = LocalDate.now();
        assertThrows( IllegalArgumentException.class, () -> testPerson.birthDate( now.plusDays( 2L )));
    }

    @Test
    void birthDateCanBeChangedToNull(){
        Person testPerson = new Person( "Dave", LocalDate.now().minusYears( 1L ));
        assertNotNull( testPerson.birthDate() );

        testPerson.birthDate( null );
        assertNull( testPerson.birthDate() );
    }

    @Test
    void personClassHasRequiredBirthdateMethod() throws NoSuchMethodException {
        Class<Person> klass = Person.class;
        Method newMethod = klass.getDeclaredMethod( "birthDate", LocalDate.class );
        assertEquals( Void.TYPE, newMethod.getReturnType() );
    }

    @Test
    void personClassHasRequiredValidationMethod() throws NoSuchMethodException {
        Class<Person> klass = Person.class;
        Method newMethod = klass.getDeclaredMethod( "validateDateOfBirth", LocalDate.class );
        assertEquals( Void.TYPE, newMethod.getReturnType() );
    }
}