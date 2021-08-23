//GROUP MEMBERS: Ashad Nadeem - 18660, Qiblatain Fatima - 18648

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

public class client_java_tcp {

        public static void main(String[] args) throws IOException {
             
                String text, reply;
                Scanner input = new Scanner(System.in);
//                String ipaddress = InetAddress.getLocalHost().getHostAddress();
                String ipaddress = (args[0]);
                int PORT = Integer.parseInt(args[1]);
                System.out.println("Client IP:" + ipaddress);
                Socket soc = new Socket(ipaddress, PORT);
                PrintStream send = new PrintStream(soc.getOutputStream());
                Scanner receive = new Scanner(soc.getInputStream());

                System.out.println("Communication Estab");
                System.err.println("Client Side");

                System.out.print("Enter text: ");
                text = input.nextLine();

                send.println(text);
                reply = receive.nextLine();

                System.out.println("Response from server: " + reply);
                soc.close();
                System.exit(0);
        }

}
