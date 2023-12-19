import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
class HorseTest {
    final String name = "Zephyr";
    final double speed = 2.6;
    final double distance = 3;
    Horse horse;

    @BeforeEach
    private void init() {
        horse = new Horse(name, speed, distance);
    }

    @Test
        //test{Method}_Should{Do}_When{Condition}
    void testConstructor_ShouldException_WhenArgNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 20.2));
    }

    @Test
    void testConstructor_ShouldExceptionMessage_WhenArgNameIsNull() {
        String expMessage = "Name cannot be null.";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed));
        assertEquals(expMessage, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f", "  "})
    void testConstructor_ShouldException_WhenArgNameIsBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f", "  "})
    void testConstructor_ShouldExceptionMessage_WhenArgNameIsBlank(String name) {
        String expMessage = "Name cannot be blank.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed));
        assertEquals(expMessage, exception.getMessage());
    }

    @Test
    void testConstructor_ShouldException_WhenArgSpeedNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, -speed));
    }

    @Test
    void testConstructor_ShouldExceptionMessage_WhenArgSpeedNegative() {
        String expMessage = "Speed cannot be negative.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, -speed));
        assertEquals(expMessage, exception.getMessage());
    }

    @Test
    void testConstructor_ShouldException_WhenArgDistanceNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, -distance));
    }

    @Test
    @DisplayName("")
    void testConstructor_ShouldExceptionMessage_WhenArgDistanceNegative() {
        String expMessage = "Distance cannot be negative.";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, -distance));
        assertEquals(expMessage, exception.getMessage());
    }


    @Test
    void testGet_ShouldHorseName() {
        assertEquals("Zephyr", horse.getName());
    }

    @Test
    @SneakyThrows
    void testGet_ShouldHorseName_whenReflection() {
        String expName = "Zephyr";
        Field field = horse.getClass().getDeclaredField("name");
        field.setAccessible(true);
        String actualName = (String) field.get(horse);
        assertEquals(expName, actualName);
    }

    @Test
    @SneakyThrows
    void testGet_ShouldHorseSpeed_whenReflection() {
        double expDistance = 2.6;
        Field field = horse.getClass().getDeclaredField("distance");
        field.setAccessible(true);
        double actualDistance = (double) field.get(horse);
        assertEquals(expDistance, actualDistance);
    }

    @Test
    void testMovie_ShouldInvokeGetRandomDouble_whenGetRandomDoubleArgs0d20d9() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(anyDouble(), eq(0.9)));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 0.2, 1.3, 4, 4.5})
    void testMovie_Should(double fakeValues) {
        double expDistance = horse.getDistance() + horse.getSpeed() * fakeValues;

        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(fakeValues);
            horse.move();
            double actualDistance = horse.getDistance();

            assertEquals(expDistance, actualDistance);
        }
    }
}