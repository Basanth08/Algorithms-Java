package unit08.weighted;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;


@Testable
public class WVertexTest {
    @Test
    public void create() {
        // setup
        String value = "abc";
        
        // invoke
        WVertex<String> vertex = new WVertex<>(value);

        // analyze
        assertEquals(value, vertex.getValue());
        assertEquals(value, vertex.toString());
    }

    @Test
    public void connectOne() {
        // setup
        WVertex<Integer> vertex = new WVertex<>(123);
        WVertex<Integer> neighbor = new WVertex<Integer>(456);
        double weight = 456.789;

        // invoke
        vertex.connect(neighbor, weight);

        // analyze
        assertEquals(weight, vertex.weight(neighbor));
    }

    @Test
    public void connectThree() {
        // setup
        WVertex<Integer> vertex = new WVertex<>(123);
        WVertex<Integer> neighborOne = new WVertex<Integer>(456);
        double weightOne = 456.789;
        WVertex<Integer> neighborTwo = new WVertex<Integer>(789);
        double weightTwo = 123.456;
        WVertex<Integer> neighborThr = new WVertex<Integer>(980);
        double weightThr = 234.567;

        // invoke
        vertex.connect(neighborOne, weightOne);
        vertex.connect(neighborTwo, weightTwo);
        vertex.connect(neighborThr, weightThr);

        // analyze
        assertEquals(weightOne, vertex.weight(neighborOne));
        assertEquals(weightTwo, vertex.weight(neighborTwo));
        assertEquals(weightThr, vertex.weight(neighborThr));
    }
}