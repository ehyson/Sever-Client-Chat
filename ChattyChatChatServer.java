import java.net.*;
import java.io.*;
import java.util.Vector;

public class ChattyChatChatServer{
	
	static int port;
	public static Vector<ChatHandler> clientArr = new Vector<ChatHandler>();
	static ServerSocket sock; 
	
	public static void main( String[] args ) throws IOException
	{
		if (args.length != 1) {
            System.err.println("Invalid input for port");
            System.exit(1);
        }
		
		try
		{
			port = Integer.parseInt( args[0] );
			sock = new ServerSocket( port );
			
			while( true )
			{
				ChatHandler newClient = new ChatHandler( sock.accept() );
				Thread newThread = new Thread(newClient);
				newThread.start();
				
				synchronized(clientArr)
				{
					clientArr.add( newClient );
				}
			}
			
			
			
		}
		catch( IOException e )
		{
			System.err.println("IOException occurred while connecting to port: " + e.getMessage() );
			
		}
		catch(ArrayIndexOutOfBoundsException a)
		{
			System.err.println("ArrayIndexOutofBoundsException in Server: " + a.getMessage());
		}
		
	}
}