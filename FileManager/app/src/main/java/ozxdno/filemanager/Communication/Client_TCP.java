package ozxdno.filemanager.Communication;

import java.net.*;
import java.io.*;

/**
 * Created by ozxdn on 2018/01/06.
 */

public class Client_TCP {
    private static String ip;
    private static int port;
    private final static int maxport = 65535;
    private final static int minport = 10000;
    private static Accept_TCP accept;
    private static boolean started = false;

    public static InetAddress getInetAddress() {
        try {
            return InetAddress.getByName(ip);
        } catch (Exception e) {
            System.out.println(e.getCause());
            return null;
        }
    }
    public static int getPort() {
        return port;
    }
    public static boolean isStarted() {
        return started;
    }

    public static boolean setIp(String ip) {
        try {
            InetAddress newIp = InetAddress.getByName(ip);
            Client_TCP.ip = ip;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean setPort(int port) {
        if(port < minport || port > maxport) {
            return false;
        }
        Client_TCP.port = port;
        return true;
    }
    protected static boolean setStarted(boolean started) {
        Client_TCP.started = started;
        return true;
    }

    public static boolean start() {
        if(started) {
            return true;
        }
        clear();
        accept = new Accept_TCP();
        accept.start();
        return true;
    }
    public static boolean restart() {
        clear();
        accept = new Accept_TCP();
        accept.start();
        return true;
    }
    public static void stop() {
        if(accept != null) {
            accept.myAbort();
        }
    }
    public static void clear() {
        ip = "192.168.191.1";
        port = maxport;
        accept = null;
        started = false;
    }
    public static boolean send(String str) {
        if(accept == null) {
            return false;
        }
        return accept.setSendStr(str);
    }
    public static String receive() {
        if(accept == null) {
            return null;
        }
        return accept.getRecestr();
    }
    public boolean isRunning() {
        if(accept == null) {
            return false;
        }
        return accept.isRunning();
    }
}

class Accept_TCP extends Thread {
    private Socket client;
    private DataInputStream recestream;
    private String recestr;
    private byte[] recebyte;
    private DataOutputStream sendstream;
    private String sendstr;
    private byte[] sendbyte;
    private boolean abort;
    private boolean running;

    public String getRecestr() {
        String ret = recestr;
        recestr = null;
        return ret;
    }
    public boolean setSendStr(String str) {
        if(str == null || str.length() == 0) {
            return false;
        }
        this.sendstr = str;
        return true;
    }

    public Accept_TCP(){
        clear();
        super.setName("Accept_TCP");
    }

    public void run() {
        abort = false;
        running = false;
        try {
            client = new Socket(Client_TCP.getInetAddress(), Client_TCP.getPort());
            sendstream = new DataOutputStream(client.getOutputStream());
            recestream =  new DataInputStream(client.getInputStream());
        } catch (Exception e) {
            System.out.println(e.getCause());
            Client_TCP.setStarted(false);
            return;
        }

        running = true;
        Client_TCP.setStarted(true);
        while (!abort) {
            if(send()) {
                receive();
                setSendStr(getRecestr());
            }
        }
        running = false;
        abort = false;
    }
    public void clear(){
        client = null;
        recestream = null;
        recestr = "";
        recebyte = new byte[1024];
        sendstream = null;
        sendstr = "";
        sendbyte = null;
        abort = false;
        running = false;
    }
    public void myAbort() {
        try {
            client.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }

        while (running) {
            abort = true;
        }

        try {
            client.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
    public boolean isRunning() {
        return running;
    }
    private boolean send() {
        if(sendstr == null || sendstr.length() == 0) {
            return false;
        }
        if(client == null || client.isClosed()) {
            return false;
        }
        if(sendstream == null) {
            return false;
        }
        try {
            sendbyte = sendstr.getBytes();
            sendstream.write(sendbyte);
            return true;
        } catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
    }
    private String receive() {
        if(client == null || client.isClosed()) {
            return null;
        }
        if(recestream == null) {
            return null;
        }
        try {
            recestream.read(recebyte);
            int len = 0;
            for(int i=0; i<recebyte.length; i++) {
                if(recebyte[i] == '\r') {
                    break;
                }
                if(recebyte[i] == '\n') {
                    break;
                }
                if(recebyte[i] == '\0') {
                    break;
                }
                len++;
            }
            //recestr = new String(recebyte,0,len, "GB2312");
            recestr = new String(recebyte,0,len, "UTF-8");
            return recestr;
        } catch (Exception e) {
            System.out.println(e.getCause());
            return null;
        }
    }
}