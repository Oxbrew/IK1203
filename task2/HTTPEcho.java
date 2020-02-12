import java.net.*;
import java.io.*;

public class HTTPEcho {
    public static void main( String[] args) throws IOException {

		ServerSocket welcomeSocket = new ServerSocket(Integer.parseInt(args[0]));
		String httpHeader = "HTTP/1.1 200 OK\r\n\r\n";
		String t; //data stored from clientInput

		while(true){
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream clientOutput = new DataOutputStream(connectionSocket.getOutputStream());

			StringBuilder textShown = new StringBuilder();
			textShown.append(httpHeader);

			while((t = clientInput.readLine()) != null && t.length() != 0){
				textShown.append(t + "\r\n");

			}

			clientOutput.writeBytes(textShown.toString()); //Echo
			clientInput.close();
			clientOutput.close();
			connectionSocket.close();
		}
	}
}
