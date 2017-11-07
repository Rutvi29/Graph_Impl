package pack1;


import java.io.ByteArrayInputStream;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;
import java.util.logging.Handler;

import org.omg.CORBA.portable.InputStream;

import undirgraph.Graph;
import undirgraph.IGraph;
import undirgraph.Node;
import undirgraph.Edge;
public class graph 
{
	

   

public static byte[] serialize(Object obj) throws IOException {

        try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
            try(ObjectOutputStream o = new ObjectOutputStream(b)){
                o.writeObject(obj);

            }
            return b.toByteArray();
        }
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try(ByteArrayInputStream b = new ByteArrayInputStream(bytes)){
            try(ObjectInputStream o = new ObjectInputStream(b)){
                return o.readObject();
            }
        }
    }

   public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException
   {
	   final HashMap<String, Double> inner1 = new HashMap<>(); // name and distance
	   inner1.put("two", 7.7);
  	 inner1.put("three", 4.0);
  	 
  	 
  	final HashMap<String, Double> inner2 = new HashMap<>(); // name and distance
	   inner2.put("one", 7.0);
	 inner2.put("three", 4.0);
	 
  	 
	 final HashMap<String, Double> inner3 = new HashMap<>(); // name and distance
	   inner3.put("one", 7.2);
	 inner3.put("two", 7.7);
	
	 
  	final int[] port_number = {4455,4377,4589};
  	
  	HashMap<Integer,String> record = new HashMap<>(); // port number and name
  	record.put( port_number[0],"one");
  	record.put( port_number[1],"two");
  	record.put(port_number[2],"three");
  	
  	ArrayList<HashMap<String, Double>> list = new ArrayList<>();
	 list.add(inner1);
	 list.add(inner2);
	 list.add(inner3);
  	 

  	 HashMap<String,HashMap> bighash = new HashMap<>();
  	HashMap<String, String[]> temp = new HashMap<>();
  	
  	for(int i=0;i<3;i++)
  	{
  		for(int j=0;j<3;j++)
  		{
  			if(port_number[i]!=port_number[j])
  			{
  				sender t = new sender(port_number[j],serialize(list.get(i)));
  		  		t.start();
  			}
  		}
  		
  	}
  	receiver rec[] = new receiver[3]; 
  	for(int i=0;i<3;i++)
  	{
  		rec[i] = new receiver(port_number[i]);
 		new Thread(rec[i]).start();
  	}
  	Thread.sleep(2000);
  	for(int i=0;i<3;i++)
  	{
  		bighash.put(record.get(rec[i].port), (HashMap<String, Double>) deserialize(rec[i].get_info(record,list)));
  	}
  	 
  	//bighash.put("Sid",inner);
  	IGraph<String> g1 = new Graph<String>();
  	
  	g1.addNode(new Node<String>("one"));
	g1.addNode(new Node<String>("two"));
	g1.addNode(new Node<String>("three"));
	
	ArrayList<Node<String>> temp1 = g1.getNodes();
	
	for(Node<String> s : temp1)
	{
		System.out.println("hello " + s.getId());
	}
	
	
	
	
	
	System.out.println(bighash.toString());
	
	Iterator<String> it = bighash.keySet().iterator();
	
	while(it.hasNext())
	{
		String node=it.next();
		HashMap<String,Double> m=bighash.get(node);
		Iterator<String> q=m.keySet().iterator();
		while(q.hasNext()){
			String destnode=q.next();
		g1.addEdge(g1.getNode(node),g1.getNode(destnode),m.get(destnode).doubleValue());
		}
	}
	
	
	ArrayList<Edge<String>> temp2 = g1.getEdges();
	for(Edge<String> s1 : temp2)
	{
		System.out.println("sup  " + s1.getWt());
	}
	
   
   }
}
