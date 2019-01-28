package application;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ByteConvert {
	public byte[] getByte(Packet p) throws Exception {
		byte[] retObject = null;
		
		ByteArrayOutputStream byo=new ByteArrayOutputStream();
		ObjectOutputStream obo=new ObjectOutputStream(byo);
		obo.writeObject(p);
		
		retObject=byo.toByteArray();
		obo.close();
		byo.close();
		
		return retObject;
	}
	
	public Packet getPacket(byte[] obyte) throws Exception{
		Packet p=null;
		ByteArrayInputStream bys=new ByteArrayInputStream(obyte);
		ObjectInputStream obs=new ObjectInputStream(bys);
		p=(Packet)obs.readObject();
		bys.close();
		obs.close();
		
		return p;
	}
	
}
