package client;

import java.io.BufferedReader;
import java.io.IOException;

public class Response extends Thread {

    private final BufferedReader in;

    public Response(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
