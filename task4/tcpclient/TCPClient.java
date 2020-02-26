package tcpclient;
import java.net.*;
import java.io.*;

public class TCPClient {

	private static int BUFFERSIZE = (1024 * 8);

    public static String askServer(String hostname, int port, String ToServer) throws  IOException {
		if(ToServer == null){
			return askServer(hostname, port);
		}

		byte [] clientBuffer = new byte[BUFFERSIZE];
		byte [] serverBuffer = new byte[BUFFERSIZE];
		clientBuffer = ToServer.getBytes();
		Socket socket = new Socket(hostname, port);


		socket.getOutputStream().write(clientBuffer);
		socket.getOutputStream().write('\n');

		int serverBufferLength = socket.getInputStream().read(serverBuffer);

		String string = new String(serverBuffer, 0, serverBufferLength);

		socket.close();

		return string;


    }

    public static String askServer(String hostname, int port) throws  IOException {

		byte [] serverBuffer = new byte[BUFFERSIZE];
		Socket socket = new Socket(hostname, port);

		int serverBufferLength = socket.getInputStream().read(serverBuffer);
		String string = new String(serverBuffer, 0, serverBufferLength);

		socket.close();

		return string;
    }
}
