//GROUP MEMBERS: Ashad Nadeem - 18660, Qiblatain Fatima - 18648

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;
import java.util.Stack;

public class server_java_tcp {

        public static void main(String[] args) throws IOException {
          
                int PORT = Integer.parseInt(args[0].trim());
                String request, servertext;
                ServerSocket Ssoc = new ServerSocket(PORT);
                System.out.println("Waiting for a connection...");
                Socket soc = Ssoc.accept();
                System.out.println("Connection Estabilished with: " + soc.getInetAddress().getHostAddress());
                Scanner get;
                PrintStream put;
                System.err.println("Server Side");

                get = new Scanner(soc.getInputStream());
                request = get.nextLine();
                System.out.println("Client: " + request);

                put = new PrintStream(soc.getOutputStream());
                servertext = Manipulate(request);
                put.println(servertext);
        }

        private static String Manipulate(String text) {
//                On receiving a string from a client, the server should: 
//                1) reverse all the characters, and 
//                2) reverse the capitalization of the strings (network‚would now become ‚"KROWTEN"
                Stack<Character> stk = new Stack<>();
                String reply = "";
                //Reversing the String by using Stack
                for (int i = 0; i < text.length(); i++) {
                        stk.push(text.charAt(i));
                }
                for (int i = 0; i < text.length(); i++) {
                        reply = reply + stk.pop();
                }

                //Reversing the Capitalisation
                char c;
                String reply2 = "";
                for (int i = 0; i < text.length(); i++) {
                        c = reply.charAt(i);
                        //Capital Letter becomes Non Capital
                        if (c >= 'A' && c <= 'Z') {
                                reply2 = reply2 + (char) ((int) c + 32);
                        } //Non Capital becomes Capital
                        else if (c >= 'a' && c <= 'z') {
                                reply2 = reply2 + (char) ((int) c - 32);
                        } //Speacial charachter remains same
                        else {
                                reply2 = reply2 + c;
                        }
                }
                return reply2;
        }

}
