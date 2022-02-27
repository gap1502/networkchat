package client;

import configuration.Configuration;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Scanner scanner;
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;

    public Client() {
        try {
            Configuration configuration = Configuration.getInstance();
            scanner = new Scanner(System.in);
            socket = new Socket(configuration.getHost(), configuration.getPort());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            System.out.println("Введите свой username: ");
            out.println(scanner.nextLine());

            Response response = new Response(in);
            response.start();

            String msg = " ";
            while (!"exit".equals(msg)) {
                msg = scanner.nextLine();
                out.println(msg);
            }
            response.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    private void closeAll() {
        try {
            in.close();
            out.close();
            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
