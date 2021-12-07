package Classes;

import java.util.ArrayList;
import java.util.UUID;
/**
 * Creates the DeletedUserList class, which holds a list of Users that have been deleted
 */
public class DeletedUserList {
    private static DeletedUserList deletedUserList;
    private ArrayList<User> deleted;

    /**
     * Constructor for the DeletedUserList object, loads the deleted user list with Users from the json file
     */
    private DeletedUserList() {
        deleted = DataLoader.getDeletedUserList();
    }

    /**
     * Gets the single instance of the DeletedUserList class
     * @return DeletedUserList, DeletedUserList instance
     */
    public static DeletedUserList getInstance() {
        if (deletedUserList == null) {
            deletedUserList = new DeletedUserList();
        }
        return deletedUserList;
    }

    /**
     * Gets a deleted user from the deleted User list based on UUID
     * @param id UUID, Unique id for the user being searched for
     * @return User, User being searched for, if null, user is not found
     */
    public User getDeletedUser(UUID id) {
        for (User deletedUser: deleted) {
            if (deletedUser.getID().equals(id)) return deletedUser;
        }
        return null;
    }
    
    /**
     * Gets the list of Deleted Users
     * @return ArrayList<User>, full list of deleted users
     */
    public ArrayList<User> getDeletedUsers(){
        return deleted;
    }

    /**
     * Writes the list of deleted users to a json file
     */
    public static void saveDeletedUsers() {
        DataWriter.saveDeletedUsers();
    }

    /**
     * Removes a User from the list of deleted users
     * @param user User, user to be removed from the list of deleted users
     */
    public void removeAccount(User user){
        deleted.add(user);
    }
}
