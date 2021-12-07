package Classes;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BusinessListTest {

    @BeforeEach
    public static void setup(){
       BusinessList businessList = BusinessList.getInstance();
       businessList.getBusinesses().clear();
    }

    @Test
    void testSearchForBusinessByEmailAndPassword() {
        BusinessList businessList = BusinessList.getInstance();
        businessList.getBusinesses().clear();
        businessList.getBusinesses().add(new Business("NeekonBusiness", "neekonbusiness@email.com", "supergoodpassword", "1111 Address Lane"));
        boolean inList = businessList.haveBusiness("neekonbusiness@email.com", "supergoodpassword");
        assertEquals(true, inList);
    }
    @Test
    void testDontHaveBusinessByEmailAndPassword(){
        BusinessList businessList = BusinessList.getInstance();
        businessList.getBusinesses().clear();
        businessList.getBusinesses().add(new Business("NeekonBusiness", "neekonbusiness@email.com", "supergoodpassword", "1111 Address Lane"));
        boolean inList = businessList.haveBusiness("neekonbusiness@email.com", "supergoodpassword");
        assertEquals(false, inList);
    }
    @Test
    void testSearchForBusinessByEmail(){
        BusinessList businessList = BusinessList.getInstance();
        businessList.getBusinesses().clear();
        businessList.getBusinesses().add(new Business("SuperBusiness", "SuperBusiness@email.com", "goodpassword", "4444 Address Lane"));
        businessList.getBusinesses().add(new Business("NeekonBusiness", "neekonbusiness@email.com", "supergoodpassword", "1111 Address Lane"));
        Business inList = businessList.haveBusiness("SuperBusiness@email.com");
        assertEquals(DataLoader.getBusinessList().get(0).getBusinessName(),inList);
    }
    @Test
    void testDontHaveBusinessByEmail(){
        BusinessList businessList = BusinessList.getInstance();
        businessList.getBusinesses().clear();
        businessList.getBusinesses().add(new Business("SuperBusiness", "SuperBusiness@email.com", "goodpassword", "4444 Address Lane"));
        businessList.getBusinesses().add(new Business("NeekonBusiness", "neekonbusiness@email.com", "supergoodpassword", "1111 Address Lane"));
        Business inList = businessList.haveBusiness("SuperBusiness@email.com");
        assertEquals(DataLoader.getBusinessList().get(0).getBusinessName(),false);
    }

    @Test
    void testHasBusiness() {
        BusinessList businessList = BusinessList.getInstance();
        businessList.getBusinesses().clear();
        businessList.getBusinesses().add(new Business("NeekonBusiness", "neekonbusiness@email.com", "supergoodpassword", "1111 Address Lane"));
        boolean inList = businessList.haveBusiness("neekonbusiness@email.com") != null;
        assertEquals(true, inList);
    }
    
    // @Test
    // void testAddNoBusiness(){
    //     BusinessList businessList;
    //     businessList = BusinessList.getInstance();
    //     businessList.getBusinesses().clear();
    //     DataWriter.saveBusiness();
    //     assertEquals(0, DataLoader.getBusinessList().size());
    // }

    // @Test
    // void testAddOneBusiness(){
    //     BusinessList businessList = BusinessList.getInstance();
    //     businessList.getBusinesses().clear();
    //     businessList.getBusinesses().add(new Business("NeekonBusiness", "neekonbusiness@email.com", "supergoodpassword", "1111 Address Lane"));
    //     DataWriter.saveBusiness();
    //     assertEquals("NeekonBusiness", DataLoader.getBusinessList().get(0).getBusinessName());
    // }

    // @Test
    // void testAddMultipleBusiness(){
    //     BusinessList businessList = BusinessList.getInstance();
    //     businessList.getBusinesses().clear();
    //     businessList.getBusinesses().add(new Business("NeekonBusiness", "neekonbusiness@email.com", "supergoodpassword", "1111 Address Lane"));
    //     businessList.getBusinesses().add(new Business("SuperBusiness", "SuperBusiness@email.com", "goodpassword", "4444 Address Lane"));
    //     businessList.getBusinesses().add(new Business("HorribleBusiness", "HorribleBusiness@email.com", "horriblepassword", "2222 Address Lane"));
    //     DataWriter.saveBusiness();
    //     assertEquals("HorribleBusiness", DataLoader.getBusinessList().get(2).getBusinessName());
    // }

    @Test 
    void testSaveBusiness(){

    }
}