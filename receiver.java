package pack1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class receiver implements Runnable{
	int port;
	byte[] byar;
	receiver(int port1)
	{
		port = port1;
		
	}
	
	public void run()
	{
		try{
			   byte[] receiveData= new byte[1024];
			   DatagramSocket sock = new DatagramSocket(port);
			   DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
				sock.receive(receivePacket);
		        byar=receivePacket.getData();
		       
		}catch(Exception e)
		{}
	}
public byte[] getValue()
{
	return byar;
}

public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
    try(ByteArrayInputStream b = new ByteArrayInputStream(bytes)){
        try(ObjectInputStream o = new ObjectInputStream(b)){
            return o.readObject();
        }
    }
}

public static byte[] serialize(Object obj) throws IOException {

    try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
        try(ObjectOutputStream o = new ObjectOutputStream(b)){
            o.writeObject(obj);

        }
        return b.toByteArray();
    }
}

public byte[] get_info(HashMap<Integer,String> record, ArrayList<HashMap<String, Double>> arr) throws ClassNotFoundException, IOException

{
	
	byte[] a = getValue();
	HashMap<String,Double> temp = (HashMap<String, Double>) deserialize(a);
	HashMap<String,Double> new_hash = new HashMap<>();
	
	
	Iterator<String> itr2 = temp.keySet().iterator();
	String Mykey = record.get(port);
	System.out.println(Mykey);
	if(Mykey.equals("one"))
	{
		System.out.println("inside one");
		new_hash = arr.get(0);
		
		
		while(itr2.hasNext())
		{
			new_hash.replace(itr2.next(),temp.get(itr2.next()));
		}
		Iterator<String> itr = new_hash.keySet().iterator();
		if(itr.next().equals(Mykey))
		{
			new_hash.remove(itr.next());
		}
	
	}
	if(Mykey.equals("two"))
	{
		 new_hash = arr.get(1);
		
		
	 itr2 = temp.keySet().iterator();
		
		while(itr2.hasNext())
		{
			new_hash.replace(itr2.next(),temp.get(itr2.next()));
		}
		Iterator<String> itr = new_hash.keySet().iterator();
		if(itr.next().equals(Mykey))
		{
			new_hash.remove(itr.next());
		}
	
	}
	if(Mykey.equals("three"))
	{
		 new_hash = arr.get(2);
		 
		
		 itr2 = temp.keySet().iterator();
		
		while(itr2.hasNext())
		{
			new_hash.replace(itr2.next(),temp.get(itr2.next()));
		}
		Iterator<String> itr = new_hash.keySet().iterator();
		if(itr.next().equals(Mykey))
		{
			new_hash.remove(itr.next());
		}
	
	}
	
	
	
	
	return serialize(new_hash);
}


}
