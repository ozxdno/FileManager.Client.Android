package ozxdno.filemanager.Tools;

/**
 * Created by ozxdn on 2018/01/07.
 */

public class Strings {
    public static String getFirstLine(String text) {
        if(text == null || text.length() == 0) {
            return "";
        }
        for(int i=0; i<text.length(); i++) {
            char c = text.charAt(i);
            if(c == '\r') {
                return text.substring(0,i);
            }
            if(c == '\n') {
                return text.substring(0,i);
            }
            if(c == 0) {
                return text.substring(0,i);
            }
        }
        return text;
    }
}
