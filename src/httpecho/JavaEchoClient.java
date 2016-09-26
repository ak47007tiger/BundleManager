package httpecho;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class JavaEchoClient {

	public static void main(String[] args) {
		String host = "127.0.0.1";
		host = "54.137.0.75";
		int port = 8080;
		try {
			Socket socket = new Socket(host, port);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			writer.write("mars-123123-hello");
			writer.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			char[] cbuf = new char[128];
			int count = reader.read(cbuf);
			System.out.println(new String(cbuf,0,count));
			writer.close();
			reader.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
