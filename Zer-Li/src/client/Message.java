package client;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable
{
	private Object obj;
	private String type;
	
	public Message(Object array, String msgType)
	{
		this.obj=array;
		this.type=msgType;
	}
	
	
	public Object getMsgObject()
	{
		return this.obj;
		
	}
	
	public void setObject(Object obj) 
	{
		this.obj=obj;
	}
	
	public String getMsgType()
	{
		return this.type;
		
	}
	
	public void setMsgType(String newMsgType)
	{
		this.type=newMsgType;
		
	}
}
