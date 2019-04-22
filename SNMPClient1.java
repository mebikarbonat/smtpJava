import java.io.*;  
import java.net.*;  
public class Client1 {  
public static void main(String[] args) {  
try{      
Socket s=new Socket("192.168.43.128",1616);  
System.out.println("Connected to SNMP Agent!\n");
System.out.println("Requesting OID 1.3.6.1.2.1.25.3.3.1.2\n");

DataOutputStream dout=new DataOutputStream(s.getOutputStream());  

DataInputStream din=new DataInputStream(s.getInputStream());  

dout.writeBytes("");
dout.flush();
dout.writeBytes("SNMP-Walk");  
dout.flush();  
dout.writeBytes("OID:1.3.6.1.2.1.25.3.3.1.2");
dout.flush();

String str2="";

str2=din.readLine();  
System.out.println("Message from server: "+str2);  

dout.close();
din.close();  
s.close();  
}catch(Exception e){System.out.println(e);}  
}  
}  
