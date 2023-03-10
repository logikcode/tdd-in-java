package logikcode.springframework.petty.fauxspring;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class InlineMockTestTest {
    @Test
    void inlineMockTest(){
        Map mapMock = mock(Map.class);
        assertEquals(mapMock.size(),0);
    }

}