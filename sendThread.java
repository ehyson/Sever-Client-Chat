import java.io.*;
import java.net.*;

public class sendThread implements Runnable{
	
	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in =null;
	
	public sendThread(Socket socket)
	{
		this.socket = socket;
	}
	
	public void run()
	{
		try
		{
			String clientMsg; 
			
			out = new PrintWriter( socket.getOutputStream(), true);
			in = new BufferedReader( new InputStreamReader( System.in) );
			
			
			while( true)
			{
				clientMsg = in.readLine();
				if( clientMsg != null )
				{
					out.println( clientMsg );
				}
					
			}
		
		}
		catch( IOException e)
		{
			System.err.println( "IOException in sending thread: " + e.getMessage() );
			System.exit(-1);
		}
		
	}

}
