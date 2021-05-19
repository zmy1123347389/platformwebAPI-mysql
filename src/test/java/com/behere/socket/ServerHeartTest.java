package com.behere.socket;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: Behere
 */
public class ServerHeartTest extends Thread {

    private ServerSocket server = null;
    Object obj = new Object();

    @Override
    public void run() {
        try {
            try {
                server = new ServerSocket(9090);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                Socket clent = server.accept();
                synchronized (obj) {
                    new Thread(new Client(clent)).start();
                    ;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("开始检测客户端是否在线...");
        new ServerHeartTest().start();
    }

    /**
     * 客户端线程
     */
    class Client implements Runnable {
        Socket client;

        public Client(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ObjectInput in = new ObjectInputStream(client.getInputStream());
                    Entity entity = (Entity) in.readObject();
                    System.out.println(entity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
