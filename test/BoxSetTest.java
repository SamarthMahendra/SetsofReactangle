import box.BoxSet;
import box.SimpleBoxSet;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class BoxSetTest {

    // Test case 1
    // create a box set and add a box
    @Test
    public void testAdd() {
        BoxSet boxSet = new SimpleBoxSet();
        boxSet.add(0, 0, 1, 1);
        assertEquals(1, boxSet.size());
    }

    // Test case 2
    // create a box set with 2 overlapping boxes - > size = 3
    @Test
    public void testadd() {
        BoxSet boxSet = new SimpleBoxSet();
        boxSet.add(0, 0, 2, 2);
        boxSet.add(1, 1, 2, 2);
        assertEquals(3, boxSet.size());
    }
}
