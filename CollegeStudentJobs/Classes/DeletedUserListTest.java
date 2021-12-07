package Classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class DeletedUserListTest {
    DeletedUserList List = DeletedUserList.getInstance();
    ArrayList<User> delList = List.getDeletedUsers(); 

    @BeforeEach
    public void setup() {
        delList.clear();
    }

    @Test
    void testAddDeletedUser() {
        List.removeAccount(new User("email", "password"));
        assertEquals("email", delList.get(0).getEmail());
    }

    @Test
    void testAddEmptyUser() {
        List.removeAccount(new User("", ""));
        assertEquals("", delList.get(0).getEmail());
    }

    @Test
    void testAddNullExperience() {
        List.removeAccount(null);
        assertEquals(null, delList.get(0));
    }
}
