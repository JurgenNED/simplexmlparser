package unittests;

import model.ComplexPojo;
import org.junit.Test;
import simplexml.SimpleXml;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

public class ComplexTest {

    private static ComplexPojo newDefaultComplexPojo() {
        final List<String> list = Arrays.asList("first", "second", "monkey");
        final Map<Integer, String> map = new HashMap<>();
        map.put(1, "thumb");
        final Float[] array = { 0.5f, 34.8f };
        final Set<Double> set = new HashSet<>(Arrays.asList(45.3, 1234567.90));

        return new ComplexPojo("complex", list, map, array, set);
    }

    private static final ComplexPojo complex = newDefaultComplexPojo();
    private static final String complexXml = "<complexpojo>\n" +
            "  <name>complex</name>\n" +
            "  <list>first</list>\n" +
            "  <list>second</list>\n" +
            "  <list>monkey</list>\n" +
            "  <map>\n" +
            "    <1>thumb</1>\n" +
            "  </map>\n" +
            "  <array>0.5</array>\n" +
            "  <array>34.8</array>\n" +
            "  <set>45.3</set>\n" +
            "  <set>1234567.9</set>\n" +
            "</complexpojo>\n";

    private SimpleXml simple = new SimpleXml();

    @Test
    public void deserialize() throws IOException {
        final ComplexPojo pojo = simple.fromXml(complexXml, ComplexPojo.class);

        assertNotNull("Pojo is null", pojo);
        assertReflectionEquals(pojo, newDefaultComplexPojo());
    }

    @Test
    public void serialize() {
        final String xml = simple.toXml(complex);
        System.out.println(xml);

        assertNotNull("No serialization response", xml);
        assertEquals("Invalid serialized output", complexXml, xml);
    }
}
