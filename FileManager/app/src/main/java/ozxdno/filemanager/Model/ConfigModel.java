package ozxdno.filemanager.Model;

/**
 * Created by ozxdn on 2018/01/07.
 */

public class ConfigModel {
    private String field;
    private String value;
    private boolean ok;

    public String getField() {
        return field;
    }
    public String getValue() {
        return value;
    }
    public boolean getIsOK() {
        return ok;
    }
    public String[] getItems() {
        if(value == null) {
            return new String[0];
        }
        return value.split("\\|");
    }

    public boolean setField(String field) {
        if(field == null) {
            return false;
        }
        this.field = field;
        return true;
    }
    public boolean setValue(String value) {
        if(value == null) {
            return false;
        }
        this.value = value;
        return true;
    }
    public boolean setItems(String[] items) {
        if(items == null) {
            return false;
        }
        if(items.length == 0) {
            this.value = "";
            return true;
        }
        String value = "";
        for(int i=0; i<items.length; i++) {
            value += items[i] + "|";
        }
        this.value = value.substring(0,value.length());
        return true;
    }

    public ConfigModel() {
        clear();
    }
    public ConfigModel(String item) {
        clear();
        if(item == null || item.length() == 0) {
            return;
        }
        int cut0 = 0;
        while(cut0 < item.length() && item.charAt(cut0) != '\r' && item.charAt(cut0) != '\n') {
            cut0++;
        }
        item = item.substring(0, cut0);
        int idx = item.indexOf('=');
        if(idx < 0) {
            field = "";
            value = item;
            return;
        }
        int cut1 = idx-1;
        int cut2 = idx+1;
        while(cut1 >= 0 && item.charAt(cut1) == ' ') {
            cut1--;
        }
        while(cut2 < item.length() && item.charAt(cut2) == ' ') {
            cut2++;
        }
        field = item.substring(0, cut1+1);
        value = item.substring(cut2);
    }
    public ConfigModel(String field, String value) {
        clear();
        setField(field);
        setValue(value);
    }

    public void clear() {
        field = "";
        value = "";
        ok = true;
    }

    public boolean getBoolean() {
        try {
            ok = true;
            return Double.parseDouble(value) != 0;
        } catch(Exception e) {
            ok = false;
            return false;
        }
    }
    public int getInt() {
        try {
            ok = true;
            return Integer.parseInt(value);
        } catch(Exception e) {
            ok = false;
            return 0;
        }
    }
    public long getLong() {
        try {
            ok = true;
            return Long.parseLong(value);
        } catch(Exception e) {
            ok = false;
            return 0;
        }
    }
    public double getDouble() {
        try {
            ok = true;
            return Double.parseDouble(value);
        } catch(Exception e) {
            ok = false;
            return 0;
        }
    }
    public String getString() {
        ok = true;
        return value;
    }
    public boolean[] getBooleanList() {
        String[] items = value.split("\\|");
        boolean[] list = new boolean[items.length];
        try {
            ok = true;
            for(int i=0; i<items.length; i++) {
                list[i] = Double.parseDouble(items[i]) != 0;
            }
            return list;
        } catch(Exception e) {
            ok = false;
            return null;
        }
    }
    public int[] getIntList() {
        String[] items = value.split("\\|");
        int[] list = new int[items.length];
        try {
            ok = true;
            for(int i=0; i<items.length; i++) {
                list[i] = Integer.parseInt(items[i]);
            }
            return list;
        } catch(Exception e) {
            ok = false;
            return null;
        }
    }
    public long[] getLongList() {
        String[] items = value.split("\\|");
        long[] list = new long[items.length];
        try {
            ok = true;
            for(int i=0; i<items.length; i++) {
                list[i] = Long.parseLong(items[i]);
            }
            return list;
        } catch(Exception e) {
            ok = false;
            return null;
        }
    }
    public double[] getDoubleList() {
        String[] items = value.split("\\|");
        double[] list = new double[items.length];
        try {
            ok = true;
            for(int i=0; i<items.length; i++) {
                list[i] = Double.parseDouble(items[i]);
            }
            return list;
        } catch(Exception e) {
            ok = false;
            return null;
        }
    }
    public String[] getStringList() {
        if(value == null) {
            ok = false;
            return new String[0];
        }
        ok = true;
        return value.split("\\|");
    }
}
