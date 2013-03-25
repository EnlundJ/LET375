/**
 * @author eb
 *
 */
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
