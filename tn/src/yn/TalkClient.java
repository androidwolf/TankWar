package yn;

import java.io.*;
import java.net.*;

public class TalkClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1",8888);
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			String Readline;
			Readline = sin.readLine();
			while(!Readline.equals("bye")) {
				os.println(Readline);
				os.flush();
				System.out.println("Clinet:" + Readline);
				System.out.println("Server:" + is.readLine());
				Readline = sin.readLine();
			}
			os.close();
			is.close();
			socket.close();
		} catch(Exception e) {
			System.out.println("Error" + e);
		}
	}

}

