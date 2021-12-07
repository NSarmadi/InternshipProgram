package Classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class ExperienceListTest {
    ExperienceList List = ExperienceList.getInstance();
    ArrayList<Experience> expList = List.getExperiences(); 

    @BeforeEach
    public void setup() {
        expList.clear();
    }

    @Test
    void testAddExperience() {
        List.addExperience(new Experience("companyName", "startDate", "endDate"));
        assertEquals("companyName", expList.get(0).getCompanyName());
    }

    @Test
    void testAddEmptyExperience() {
        List.addExperience(new Experience("", "", ""));
        assertEquals("", expList.get(0).getCompanyName());
    }

    @Test
    void testAddNullExperience() {
        List.addExperience(null);
        assertEquals(null, expList.get(0));
    }

    @Test
    void testRemoveExperience() {
        List.addExperience(new Experience("companyName", "startDate", "endDate"));
        List.removeExperience(0);
        assertEquals(0, expList.size());
    }
}
