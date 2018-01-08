package ozxdno.filemanager.Communication;

import ozxdno.filemanager.Model.ConfigModel;
import ozxdno.filemanager.Resource.Enums;

/**
 * Created by ozxdn on 2018/01/07.
 */

public class Client_CMD {
    private int mode;
    private Client_TCP tcp;
    private Client_UDP udp;

    public int getMode() {
        return mode;
    }
    public boolean setMode(int mode) {
        if(mode > 1) {
            return false;
        }
        this.mode = mode;
        return true;
    }

    public Client_CMD() {
        clear();
    }
    public Client_CMD(int mode) {
        clear();
        setMode(mode);
    }

    public void clear() {
        mode = Enums.Client_TCP;
    }
    public void stop() {
        if(mode == Enums.Client_TCP) {
            tcp.stop();
        }
        if(mode == Enums.Client_UDP) {

        }
    }

    public int login(String name, String password) {
        if(mode == Enums.Client_TCP) {
            tcp = new Client_TCP();
            tcp.start();
            tcp.send("login = " + name + "|" + password);

            while (tcp.isRunning()) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    ;
                }
                String recestr = tcp.receive();
                if(recestr != null && recestr.length() != 0) {
                    ConfigModel config = new ConfigModel(recestr);
                    tcp.stop();
                    int err = config.getInt();
                    if(config.getIsOK()) { return err; }
                    return 1;
                }
            }
            return 1;
        }
        return -1;
    }


}
