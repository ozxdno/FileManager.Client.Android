package ozxdno.filemanager.Local;

import ozxdno.filemanager.Tools.*;

/**
 * Created by ozxdn on 2018/01/06.
 */

public final class Manager {
    public final static String RootPath = "File Manager";
    public final static String ConfigPath = RootPath + "\\config";
    public final static String SynchroPath = RootPath + "synchro";
    public final static String PrivatePath = RootPath + "private";
    public final static String ConfigFile = "config";
    public final static String ScoreFile = "score";

    public static boolean createFoldersAndFiles(){
        if(!ozxdno.filemanager.Tools.SDCard.exists()){
            return false;
        }
        boolean ok = true;

        ok &= SDCard.createFolder(RootPath);
        ok &= SDCard.createFolder(ConfigPath);
        ok &= SDCard.createFolder(SynchroPath);
        ok &= SDCard.createFolder(PrivatePath);

        ok &= SDCard.createFile(ConfigPath + "\\" + ConfigFile);
        ok &= SDCard.createFile(ConfigPath + "\\" + ScoreFile);

        return ok;
    }
    public static boolean synchroFolders() {
        return false;
    }
}
