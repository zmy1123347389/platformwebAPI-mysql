package com.behere.socket;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author: Behere
 */
public class ClientSender {

    private ClientSender() {
    }

    Socket sender = null;
    private static ClientSender instance;

    public static ClientSender getInstance() {
        if (instance == null) {
            synchronized (ClientHeart.class) {
                instance = new ClientSender();
            }
        }
        return instance;
    }

    public void send() {
        try {
            sender = new Socket(InetAddress.getLocalHost(), 9090);
            while (true) {
                ObjectOutputStream out = new ObjectOutputStream(sender.getOutputStream());
                Entity obj = new Entity();
                obj.setName("huojg");
                obj.setSex("男");
                out.writeObject(obj);
                out.flush();

                System.out.println("已发送...");
                Thread.sleep(5000);
            }
        } catch (Exception e) {

        }
    }
}
