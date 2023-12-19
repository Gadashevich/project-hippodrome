import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

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

    @Test
    void testGetHorses(){
        List<Horse> list = new ArrayList<>();
        for (int i = 0; i <30 ; i++) {
            list.add(new Horse("name"+i,i,i));
        }
        Hippodrome hippodrome = new Hippodrome(list);
        assertEquals(list,hippodrome.getHorses());
    }

    @Test
    void testGetWinner() {
        Horse horse1 = new Horse("One", 1, 2);
        Horse horse2 = new Horse("Two", 2, 30);
        Horse horse3 = new Horse("Three", 4, 50);
        Horse horse4 = new Horse("Four", 1, 10);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4));
        assertSame(horse3,hippodrome.getWinner());
    }

}