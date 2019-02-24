import java.io.*;
import java.net.*;
import java.net.*;
import java.net.*;
//
public class ChattyChatChatClient {
	
	private static Socket socket;

	public static void main( String[] args )
	{
		try
		{
			String host = args[0];
            int port = Integer.parseInt(args[1]);
			socket = new Socket(host, port);
			
			
			Thread sendMessage = new Thread( new Runnable() 
			{
				@Override
				public void run()
				{
					try
					{
						sendMessage(socket);
					}
					catch( IOException e)
					{
						System.err.println( "IOException in sending thread: " + e.getMessage() );
					}
				
				}	
			});
			
			
			
			Thread readMessage = new Thread( new Runnable() 
			{
				@Override
				public void run()
				{
					try
					{
						readMessage(socket);
					}
					catch( IOException e)
					{
						System.out.println("IOException recieving thread: " + e.getMessage() );
					}
				}
			});
			
			
		readMessage.start();
		sendMessage.start();
			
			
		}
		catch( IOException e )
		{
			System.out.println("IOException Error in client: " + e.getMessage());
			
		}
	}
	
	
	
	
	
	
	
	
	public static void readMessage(Socket socket) throws IOException
	{
		BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
		String serverMsg;
		
		while(true)
		{
			serverMsg = in.readLine();
			if( serverMsg != null )
			{
				System.out.println( serverMsg );
			}
		}
	}
	

	
	
	public static void sendMessage(Socket socket) throws IOException
	{
		PrintWriter out = new PrintWriter( socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in) );
		String clientMsg; 
		
		while(true)
		{
			clientMsg = in.readLine();
			if( clientMsg != null )
			{
				out.println( clientMsg );
			}
				
		}
	}
	
}	
