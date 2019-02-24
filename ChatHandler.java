import java.net.*;
import java.io.*;


public class ChatHandler implements Runnable {
	
	public BufferedReader in;
	public PrintWriter out;
	private Socket sock = null;
	public String nickName = null;
	
	
	public ChatHandler(Socket soc)
	{
		this.sock = soc;
	}
	
	
	//@Override
	public void run()
	{
		try
		{
			String inLine;
			String[] parse;
			
			in = new BufferedReader( new InputStreamReader( sock.getInputStream()) );
			out = new PrintWriter( sock.getOutputStream(), true );
			
			while( (inLine = in.readLine()) != null )
			{
				parse = inLine.split("\\s+", 3);
				synchronized( ChattyChatChatServer.clientArr )
				{
					//Nickname Command
					if( parse[0].equals("/nick") )
					{
						nickName = parse[1];
					}
					//Message Command
					else if( parse[0].equals("/dm") ) 
					{
						for( int i = 0; i < ChattyChatChatServer.clientArr.size(); i++ )
						{
							ChatHandler client = ChattyChatChatServer.clientArr.get(i);
							
							if( client.nickName != null && client.nickName.equals( parse[1]) )
								client.out.println( parse[2] );
						}
					}
					//Quit command
					else if( parse[0].equals("/quit") )
					{
						ChattyChatChatServer.clientArr.remove(this);
						sock.close();
						return;
					}
					else 
					{
						for (int i = 0; i < ChattyChatChatServer.clientArr.size(); i++)
                        {
                            if (ChattyChatChatServer.clientArr.get(i) != this)
                                ChattyChatChatServer.clientArr.get(i).out.println(inLine);
                        }

					}
					
				}
			}
		}
		catch( IOException e ) 
		{ 
			System.err.println("Error occurred in ChatHandler");
			
		} 
	}

}
