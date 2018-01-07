package ozxdno.filemanager.Resource;

import java.util.*;

import ozxdno.filemanager.Model.UserModel;

/**
 * Created by ozxdn on 2018/01/07.
 */

public class Users {
    private static Set<UserModel> users = new HashSet<UserModel>();
    private static UserModel current = null;

    public static UserModel getCurrentUser() {
        return current;
    }
    public static boolean setCurrentUser(UserModel user) {
        if(user == null) {
            return false;
        }
        current = user;
        current.setState(Enums.UserState_Online);
        return true;
    }

    public static void setOfflineUser() {
        current = new UserModel();
    }
}
