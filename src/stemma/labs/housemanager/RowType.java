package stemma.labs.housemanager;


public class RowType {
	/**
	 * 
	 */
	private int sno;
	private String name;
	private String type;
	private long timeout;
	private boolean toggle_switch;
	
	public RowType()
	{
		
	}
	
	public RowType(int a, String b, String c, long d, boolean e)
	{
		this.sno = a;
		this.name = b;
		this.type = c;
		this.timeout = d;
		this.toggle_switch = e;
	}
	
	public String getType() {
		return type ;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getsno() {
		return sno;
	}

	public void setsno(int a) {
		this.sno = a;
	}
	
	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long a) {
		this.timeout = a;
	}
	
	public boolean getSwitch() {
		return toggle_switch;
	}

	public void setSwitch(boolean a) {
		this.toggle_switch = a;
	}
	
	

}
