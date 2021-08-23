//GROUP MEMBERS: Ashad Nadeem - 18660, Qiblatain Fatima - 18648

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


public class client_java_udp {

        public static void main(String[] args) throws IOException {

                String msg, reply;
                DatagramSocket soc = new DatagramSocket();
                String ipaddress = (args[0]);
                int PORT = Integer.parseInt(args[1]);
                
                InetAddress IPAddress = InetAddress.getByName(ipaddress);
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                System.err.println("Client " + ipaddress + " " + PORT);

                //Client's message to be sent to the server
                System.out.print("Enter text: ");
                msg = (String) br.readLine();

                //sending the packet to the server
                byte[] sendingDataBuffer = msg.getBytes();
                DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length, IPAddress, PORT);
                soc.send(outputPacket);

                //Reply from the server                         
                byte[] receivingDataBuffer = new byte[65536];
                DatagramPacket receivedPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
                soc.receive(receivedPacket);

                byte[] data = receivedPacket.getData();
                reply = new String(data, 0, receivedPacket.getLength());
                System.out.println("Response from server: " + reply);

                soc.close();
                System.exit(0);
        }

}
