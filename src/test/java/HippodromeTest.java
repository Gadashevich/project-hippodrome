import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    void testConstructor_ShouldException_WhenArgIsNull() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void testConstructor_ShouldExceptionMessage_WhenArgNull() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.",e.getMessage());
        }
    }

@ParameterizedTest()
@EmptySource
    void testConstructor_ShouldException_WhenArgsListIsEmpty(List<Horse> list) {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(list));
    }

    @Test
    void testConstructor_ShouldExceptionMessage_WhenArgsListIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()),"Horses cannot be empty.");
    }


    @Test
    void testMovie(){
        List<Horse> list = new ArrayList<>();
        for (int i = 0; i <50 ; i++) {
           list.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(list);
        hippodrome.move();
        for (Horse horse : list) {
            verify(horse).move();
        }
    }

}