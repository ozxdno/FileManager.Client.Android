package ozxdno.filemanager.Tools;

import android.os.Environment;

import java.io.File;

/**
 * Created by ozxdn on 2018/01/06.
 */

public class SDCard {
    public static boolean exists(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    public static String path(){
        if(!exists()){
            return null;
        }
        File dir = Environment.getExternalStorageDirectory();
        return dir.getAbsolutePath();
    }
    public static boolean existsFolder(String url) {
        if(!exists()) {
            return false;
        }
        if(url == null || url.length() == 0) {
            return false;
        }

        url = path() + "\\" + url;
        File folder = new File(url);
        return folder.exists() && folder.isDirectory();
    }
    public static boolean createFolder(String url) {
        if(!exists()) {
            return false;
        }
        if(url == null || url.length() == 0) {
            return false;
        }

        url = path() + "/" + url;
        File folder = new File(url);
        if(folder.exists() && folder.isDirectory()) {
            return true;
        }
        return folder.mkdir();
    }
    public static boolean existsFile(String url) {
        if(!exists()) {
            return false;
        }
        if(url == null || url.length() == 0) {
            return false;
        }

        url = path() + "\\" + url;
        File file = new File(url);
        return file.exists() && file.isFile();
    }
    public static boolean createFile(String url){
        if(!exists()) {
            return false;
        }
        if(url == null || url.length() == 0) {
            return false;
        }

        url = path() + "/" + url;
        File file = new File(url);
        if(file.exists() && file.isFile()) {
            return true;
        }
        try{
            return file.createNewFile();
        } catch (Exception e) {
            return false;
        }
    }
}
