// Author(s): Einar Blaberg and Niklas Beischer
// Email:	einar.blaberg@gmail.com, niklas.beischer@gmail.com
// Fire-groupID: 19
// Date:	2013-04-08

public class SingleBuffer<Typ>
{
	private Typ data=null;
	
	public boolean put(Typ a)
	{
		if(data == null)
		{
			this.data = a;
			return true;
		}
		else
			return false;
	}
	
	public Typ get()
	{
		Typ tmp=data;
		this.data=null;
		return tmp;
	}
}
