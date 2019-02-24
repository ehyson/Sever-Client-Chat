import java.io.*;
import java.net.*;

class recieveThread implements Runnable {
	
	Socket socket =null;
	BufferedReader recieve=null;
	
	public recieveThread(Socket socket) 
	{
		this.socket = socket;
	}
	
	public void run()
	{
		try
		{
			String serverMsg;
			BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
			
			while(true)
			{
				if( (serverMsg = in.readLine()) != null )
					System.out.println( serverMsg );
			}
		}
		catch( IOException e)
		{
			System.out.println("IOException recieving thread: " + e.getMessage() );
		}
	}

}
