package com.example.kbtg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DemoServiceTest {

    @Test
    @DisplayName("ในการทำงานต้อง random ได้ค่า 5")
    public void random_5() {
        DemoService demoService = new DemoService();
        demoService.setRandom(new MockRandom(5));
        String actualResult =demoService.generateData("somkiat");
        assertEquals("somkiat5", actualResult);
    }

    @Test
    @DisplayName("Should throw runtime exception when random is 4")
    public void random_4() {
        DemoService demoService = new DemoService();
        demoService.setRandom(new MockRandom(4));
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            demoService.generateData("somkiat");
        });

        assertEquals("Invalid number with 4", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw runtime exception when random is 9")
    public void random_9() {
        DemoService demoService = new DemoService();
        demoService.setRandom(new MockRandom(9));
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            demoService.generateData("somkiat");
        });

        assertEquals("Invalid number with 9", exception.getMessage());
    }
}

class MockRandom extends Random {
    int randomNumber;
    public MockRandom(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    @Override
    public int nextInt(int bound) {
        return randomNumber;
    }
}
