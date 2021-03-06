import java.io.*;
import java.net.*;
public class smtpClient {
    public static void main(String[] args) {
// declaration section:
// smtpClient: our client socket
// os: output stream
// is: input stream
        Socket smtpSocket = null;  
        DataOutputStream os = null;
        DataInputStream is = null;
// Initialization section:
// Try to open a socket on port 25
// Try to open input and output streams
        try {
            smtpSocket = new Socket("192.168.43.128", 2525);
            os = new DataOutputStream(smtpSocket.getOutputStream());
            is = new DataInputStream(smtpSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }
// If everything has been initialized then we want to write some data
// to the socket we have opened a connection to on port 25
    if (smtpSocket != null && os != null && is != null) {
            try {
// The capital string before each colon has a special meaning to SMTP
// you may want to read the SMTP specification, RFC1822/3
        	String responseLine;
		os.writeBytes("HELO\r\n");
		responseLine = is.readLine();
		System.out.println("Server: " + responseLine);
                os.writeBytes("\r \n");    
                os.writeBytes("MAIL From: mazizi@tmsk.uitm.edu.my\r\n");
                os.writeBytes("RCPT To: test@mail.com\r\n");
                os.writeBytes("DATA\r\n");
                os.writeBytes("From: mazizi@tmsk.uitm.edu.my\r\n");
                os.writeBytes("Subject: testing ITT420\r\n");
                os.writeBytes("untuk makluman semua\r\n"); // message
                //os.writeBytes("\n.\n");
		responseLine = is.readLine();
                System.out.println("Server: " + responseLine);
                os.writeBytes("QUIT\r\n");
                responseLine = is.readLine();
                System.out.println("Server: " + responseLine);
// clean up:
// close the output stream
// close the input stream
// close the socket
        os.close();
                is.close();
                smtpSocket.close();   
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }           
}
