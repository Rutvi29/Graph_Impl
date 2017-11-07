package pack1;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class sender extends Thread{
	int port;
	byte[] sendData= new byte[1024];
	sender(int port1, byte[] sendData1)
	{
		port = port1;
		sendData = sendData1;
	}
	
	public void run()
	{
		try{
		DatagramSocket sock = new DatagramSocket();
		InetAddress ip = InetAddress.getByName("localhost");
		
		DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ip,port);
		sock.send(sendPacket);
		sock.close();
		}catch(Exception e)
		{}
	}

}
