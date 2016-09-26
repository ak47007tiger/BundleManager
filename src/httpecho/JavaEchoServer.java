package httpecho;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class JavaEchoServer {

	public static void main(String[] args) {
		File file = new File(new File(System.getenv("OPENSHIFT_LOG_DIR")), "javaEchoServer.log");
		PrintStream p = null;
		try {
			p = new PrintStream(file);
			p.println("exec this class");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String host = System.getenv("OPENSHIFT_DIY_IP");
		host = null == host ? "127.0.0.1" : host;
		String portStr = System.getenv("OPENSHIFT_DIY_PORT"); 
		int port = null == portStr ? 8080 : Integer.parseInt(portStr);
		int backlog = 128;
		try {
			InetAddress bindAddr = InetAddress.getByName(host);
			ServerSocket serverSocket = new ServerSocket(port, backlog,
					bindAddr);
			p.println("server create");
			char[] cbuf = new char[128];
			while (true) {
				Socket client = serverSocket.accept();
				p.println("new client");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(client.getInputStream()));
				
				int count = reader.read(cbuf);
				String msg = new String(cbuf,0,count);
				String[] info = msg.split("-");
				if (info.length == 3 && info[0].equals("mars")
						&& info[1].equals("123123")) {
					if (info[2].equals("close")) {
						reader.close();
						client.close();
						break;
					} else {
						BufferedWriter writer = new BufferedWriter(
								new OutputStreamWriter(client.getOutputStream()));
						writer.write("HTTP/1.0 200OK");
						writer.write("Content-type:text/html");
						writer.write("this is echo server");
						writer.flush();
						reader.close();
						writer.close();
						client.close();
					}
				}else{
					System.out.println("not a user");
				}
			}
			serverSocket.close();
			p.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			p.println(e.getMessage());
		} catch (IOException e) {
			p.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
