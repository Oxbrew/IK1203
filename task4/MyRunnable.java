import java.net.*;
import java.io.*;
import tcpclient.TCPClient;

public class MyRunnable implements Runnable {

	Socket connectionSocket;

	public MyRunnable(Socket connectionSocket){
		this.connectionSocket = connectionSocket;
	}

	String httpHeader = "HTTP/1.1 200 OK\r\n\r\n";
	String hostName;
	String port;
	String string;

	public void run(){

		try {

		BufferedReader clientInput = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		DataOutputStream clientOutput = new DataOutputStream(connectionSocket.getOutputStream());
		String infoUnfiltered = clientInput.readLine();

		String[] info = infoUnfiltered.split("[/?=& ]");

		hostName = null;
		port = null;
		string = null;

		for (int i = 0; i < info.length  ; i++) {
			if (info[i].equals("hostname")){hostName = info[++i];}
			else if (info[i].equals("port")){port = info[++i];}
			else if (info[i].equals("string")){string = info[++i];}
		}

		try {
			if (port != null && hostName != null) {
				String serverRes = TCPClient.askServer(hostName, Integer.parseInt(port), string);
				clientOutput.writeBytes(httpHeader + serverRes + "\r\n");
			} else {
					clientOutput.writeBytes("HTTP/1.1 400 Bad Request\r\n");
				}
				clientInput.close();
				clientOutput.close();
				connectionSocket.close();

			} catch (IOException e) {
				clientOutput.writeBytes("HTTP/1.1 404 Not Found\r\n");
			}
		} catch(IOException e){
			System.out.println("Error");
		}

	}
}
