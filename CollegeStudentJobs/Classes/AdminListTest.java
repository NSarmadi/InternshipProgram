package Classes;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminListTest {
    
    @BeforeEach
    public void setup() {
        AdminList adminList = AdminList.getInstance();
        adminList.getAdmins().clear();
    }

    @Test
    public void testAddNoAdmin() {
        AdminList adminlist = AdminList.getInstance();
        adminlist.getAdmins().clear();
        assertEquals(0, adminlist.getAdmins().size());
    }

    @Test
    public void testAddOneAdmin() {
        AdminList adminlist = AdminList.getInstance();
        adminlist.getAdmins().clear();
        adminlist.getAdmins().add(new Admin("email", "password"));
        assertEquals("email", adminlist.getAdmins().get(0).getEmail());
    }

    @Test
    public void testAddMultipleAdmins() {
        AdminList adminlist = AdminList.getInstance();
        adminlist.getAdmins().clear();
        adminlist.getAdmins().add(new Admin("email", "password"));
        adminlist.getAdmins().add(new Admin("email1","password1"));
        adminlist.getAdmins().add(new Admin("email2","password2"));
        adminlist.getAdmins().add(new Admin("email3","password3"));
        assertEquals("email2", adminlist.getAdmins().get(2).getEmail());
    }

    @Test
    public void testAddAdminAlreadyInList() {
        AdminList adminlist = AdminList.getInstance();
        adminlist.getAdmins().clear();
        adminlist.getAdmins().add(new Admin("email", "password"));
        boolean added = adminlist.addAdmin("email", "password");
        assertEquals(false, added);
    }

    @Test
    public void testAddAdminNotInList() {
        AdminList adminlist = AdminList.getInstance();
        adminlist.getAdmins().clear();
        boolean added = adminlist.addAdmin("email", "password");
        assertEquals(true, added);
    }

    @Test
    public void testRemoveAdminInList() {
        AdminList adminlist = AdminList.getInstance();
        adminlist.getAdmins().clear();
        adminlist.getAdmins().add(new Admin("email", "password"));
        adminlist.getAdmins().add(new Admin("email1","password1"));
        adminlist.getAdmins().add(new Admin("email2","password2"));
        adminlist.getAdmins().add(new Admin("email3","password3"));
        adminlist.removeAdmin(adminlist.getAdmins().get(2));
        assertEquals(3, adminlist.getAdmins().size());
    }

    @Test
    public void testRemoveAdminNotInList() {
        AdminList adminlist = AdminList.getInstance();
        adminlist.getAdmins().clear();
        adminlist.getAdmins().add(new Admin("email", "password"));
        adminlist.getAdmins().add(new Admin("email1","password1"));
        adminlist.getAdmins().add(new Admin("email2","password2"));
        adminlist.getAdmins().add(new Admin("email3","password3"));
        Admin admin = new Admin("email4", "password4");
        adminlist.removeAdmin(admin);
        assertEquals(4, adminlist.getAdmins().size());
    }

    @Test
    public void testHaveAdminByEmailandPassword() {
        AdminList adminlist = AdminList.getInstance();
        adminlist.getAdmins().clear();
        adminlist.getAdmins().add(new Admin("email", "password"));
        adminlist.getAdmins().add(new Admin("email1","password1"));
        adminlist.getAdmins().add(new Admin("email2","password2"));
        adminlist.getAdmins().add(new Admin("email3","password3"));
        boolean inList = adminlist.haveAdmin("email", "password");
        assertEquals(true, inList);
    }

    @Test
    public void testDontHaveAdminByEmailandPassword() {
        AdminList adminlist = AdminList.getInstance();
        adminlist.getAdmins().clear();
        adminlist.getAdmins().add(new Admin("email", "password"));
        adminlist.getAdmins().add(new Admin("email2","password2"));
        adminlist.getAdmins().add(new Admin("email3","password3"));
        boolean inList = adminlist.haveAdmin("email1", "password1");
        assertEquals(false, inList);
    }

    @Test
    public void testHaveAdminByEmail() {
        AdminList adminlist = AdminList.getInstance();
        adminlist.getAdmins().clear();
        adminlist.getAdmins().add(new Admin("email", "password"));
        adminlist.getAdmins().add(new Admin("email1","password1"));
        adminlist.getAdmins().add(new Admin("email2","password2"));
        adminlist.getAdmins().add(new Admin("email3","password3"));
        boolean inList = adminlist.haveAdmin("email");
        assertEquals(true, inList);
    }

    @Test
    public void testDontHaveAdminByEmail() {
        AdminList adminlist = AdminList.getInstance();
        adminlist.getAdmins().clear();
        adminlist.getAdmins().add(new Admin("email", "password"));
        adminlist.getAdmins().add(new Admin("email2","password2"));
        adminlist.getAdmins().add(new Admin("email3","password3"));
        boolean inList = adminlist.haveAdmin("email1");
        assertEquals(false, inList);
    }
}
