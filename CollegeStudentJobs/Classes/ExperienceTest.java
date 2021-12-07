package Classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ExperienceTest {

    @Test
    void testAddingDescription() {
        Experience exp = new Experience("companyName", "startDate", "endDate");
        exp.addDescription("description");
        assertEquals("description", exp.getDescriptions().get(0));
    }

    @Test
    void testAddingEmptyDescription() {
        Experience exp = new Experience("companyName", "startDate", "endDate");
        exp.addDescription("");
        assertEquals("", exp.getDescriptions().get(0));
    }

    @Test
    void testAddingNullDescription() {
        Experience exp = new Experience("companyName", "startDate", "endDate");
        exp.addDescription(null);
        assertEquals(null, exp.getDescriptions().get(0));
    }

    @Test   
    void testAddingThreeDescriptions() {
        Experience exp = new Experience("companyName", "startDate", "endDate");
        exp.addDescription("description");
        exp.addDescription("description1");
        exp.addDescription("description2");
        assertEquals("description2", exp.getDescriptions().get(2));
    }

    @Test
    void testRemoveDescription() {
        Experience exp = new Experience("companyName", "startDate", "endDate");
        exp.addDescription("description");
        exp.removeDescription(0);
        assertEquals(0, exp.getDescriptions().size());
    }

    @Test
    void testRemoveVoidDescription() {
        Experience exp = new Experience("companyName", "startDate", "endDate");
        exp.addDescription(null);
        exp.removeDescription(0);
        assertEquals(0, exp.getDescriptions().size());
    }
}
