//GROUP MEMBERS: Ashad Nadeem - 18660, Qiblatain Fatima - 18648

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Stack;

public class server_java_udp {

        public static void main(String[] args) throws IOException {
                String msg, reply;
                int PORT = Integer.parseInt(args[0]);
                DatagramSocket soc = new DatagramSocket(PORT); //port number given as the parameter

                //data packet received from the client
                //The data is temporarily stored in the buffer in case of delay in communication
                byte[] ReceivingBuffer = new byte[65536];

                //UDP packet created to store client's data using the receiving buffer
                DatagramPacket inputPacket = new DatagramPacket(ReceivingBuffer, ReceivingBuffer.length);

                System.err.println("Server " + PORT);
                
                //receive data from the client           
                soc.receive(inputPacket);
                byte[] data = inputPacket.getData(); //retrieve the data in the udp packet
                String str = new String(data, 0, inputPacket.getLength());

                //System.out.println("Message received: " + str);
                //Acknowledgement
                //Server's message to be sent to the client
                //str = "Acknowledge the message (from client): " + str;
                str = Manipulate(str);
                DatagramPacket ack = new DatagramPacket(str.getBytes(), str.getBytes().length, inputPacket.getAddress(), inputPacket.getPort());
                soc.send(ack);
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
