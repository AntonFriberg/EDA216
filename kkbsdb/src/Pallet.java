import java.util.Date;

public class Pallet {
	private int palletNbr;
	private String cookieName,location;
	private Date bakeDate;
	private char isBlocked;
	private Bill bill;
	
	public Pallet(){
		
	}

	public Pallet(int palletNbr, String cookieName, String location, Date bakeDate, char isBlocked, Bill bill) {
		super();
		this.palletNbr = palletNbr;
		this.cookieName = cookieName;
		this.location = location;
		this.bakeDate = bakeDate;
		this.isBlocked = isBlocked;
		this.bill = bill;
	}

	public int getPalletNbr() {
		return palletNbr;
	}

	public void setPalletNbr(int palletNbr) {
		this.palletNbr = palletNbr;
	}

	public String getCookieName() {
		return cookieName;
	}

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getBakeDate() {
		return bakeDate;
	}

	public void setBakeDate(Date bakeDate) {
		this.bakeDate = bakeDate;
	}

	public char getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(char isBlocked) {
		this.isBlocked = isBlocked;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
}
