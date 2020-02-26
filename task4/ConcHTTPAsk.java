import java.net.*;
import java.io.*;

public class ConcHTTPAsk {
	public static void main(String[] args) throws IOException {

		try {


			ServerSocket welcomeSocket = new ServerSocket(Integer.parseInt(args[0]));

			while(true){
				Socket connectionSocket = welcomeSocket.accept();
				new Thread(new MyRunnable(connectionSocket)).start();
			}
		}	catch(IOException c) {
				System.out.println("error");
		}
	}
}
