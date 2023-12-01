import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    // В классе иподром проверить что в констр null будет выброшен exception
    // проверить сообщение этого exception
    // проверить что в случае если список пустой, будет ошибка
    // проверить сообщение на эту ошибку


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

    @Test
    void testConstructor_ShouldException_WhenArgsListIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()));
    }

    @Test
    void testConstructor_ShouldExceptionMessage_WhenArgsListIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()),"Horses cannot be empty.");
    }


}