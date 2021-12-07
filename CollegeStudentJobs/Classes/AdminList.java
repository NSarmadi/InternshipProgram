package Classes;

import java.util.ArrayList;
/**
 * Creates the AdminList class, which will hold a single instance of the List of Admins
 */
public class AdminList {
    private static AdminList adminList;
    private static ArrayList<Admin> admins;
    
    /**
     * AdminList Constructor, gets the admin list from json and adds it to the class' ArrayList
     */
    private AdminList() {
        admins = DataLoader.getAdminList();
    }

    /**
     * Returns the singular instance of the AdminList class
     * @return AdminList, the instance of the AdminList
     */
    public static AdminList getInstance() {
        if (adminList == null) {
            adminList = new AdminList();
        }
        return adminList;
    }

    /**
     * Returns an admin from the AdminList, finding it by email
     * @param email String, Email for the admin to be found and returned
     * @return
     */
    public Admin getAdmin(String email) {
        for (Admin admin: admins) {
            if (admin.getEmail().equals(email)) return admin;
        }
        return null;
    }

    /**
     * Returns the full ArrayList of Admins 
     * @return ArrayList<Admin>, the full ArrayList of admins
     */
    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    /**
     * Adds the passed in admin to the admin list
     * @param admin Admin, the admin to be added to the list
     */
    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    /**
     * Removes the passed in admin from the ArrayList
     * @param admin Admin, admin to be removed from the list
     */
    public void removeAdmin(Admin admin) {
        admins.remove(admin);
    }

    /**
     * Saves the list of admins to the adminlist json file
     */
    public static void saveAdmins() {
        DataWriter.saveAdminList();
    }

    /**
     * Checks the Admin list to determine if it has an admin matching with a passed in email and password
     * @param email String, Email of the admin account being searched for
     * @param password String, Password of the admin account being searched for
     * @return boolean, True if admin is found, false if not
     */
    public boolean haveAdmin(String email, String password){
        for(Admin admin: admins){
            if(admin.getEmail().equals(email) && admin.getPassword().equals(password))
                return true;
        }
        return false;
    }

    public boolean haveAdmin(String email){
        for(Admin admin: admins){
            if(admin.getEmail().equals(email))
                return true;
        }
        return false;
    }

    /**
     * Creates and adds an admin to the Admin List
     * @param email String, The email for the new admin being created
     * @param password String, The password for the email being created
     * @return boolean, Returns true if the admin account is created, false if the admin account already exists
     */
    public boolean addAdmin(String email, String password){
        if(haveAdmin(email))return false;
        
		admins.add(new Admin(email, password));
        return true;
    }   
}
