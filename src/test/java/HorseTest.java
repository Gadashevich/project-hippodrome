import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
        //test{Method}_Should{Do}_When{Condition}
    void testConstructor_ShouldException_WhenArgNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 20.2));
    }

    @Test
    void testConstructor_ShouldExceptionMessage_WhenArgNameIsNull() {
        try{
            Horse horse = new Horse(null, 20.0);
        }catch (IllegalArgumentException e){
            e.getMessage();
            assertEquals("Name cannot be null.",e.getMessage());
        }
    //    assertThrows(IllegalArgumentException.class, () -> new Horse(null, 20.2), "Name cannot be null.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f", "  "})
    void testConstructor_ShouldException_WhenArgNameIsBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 20.2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f", "  "})
    void testConstructor_ShouldExceptionMessage_WhenArgNameIsBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 20.2), "Name cannot be blank.");
    }

    @Test
    void testConstructor_ShouldException_WhenArgSpeedNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1.0));
    }

    @Test
    void testConstructor_ShouldExceptionMessage_WhenArgSpeedNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1.0), "Speed cannot be negative.");
    }

    @Test
    void testConstructor_ShouldException_WhenArgDistanceNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 2.0, -1.0));
    }

    @Test
    @DisplayName("")
    void testConstructor_ShouldExceptionMessage_WhenArgDistanceNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 2.0, -1.0), "Distance cannot be negative.");
    }


//    @ParameterizedTest
//    @CsvSource({
//            "Alex, 20, true",
//            "Boris, 25, false"
//    })
//    void test(String name, int age, boolean male){
//
//    }


    // В классе иподром проверить что в констр null будет выброшен exception
    // проверить сообщение этого exception
    // проверить что в случае если список пустой, будет ошибка
    // проверить сообщение на эту ошибку


}