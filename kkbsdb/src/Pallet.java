public class Pallet {
	private int palletNbr;
	private String cookieName, location, bakeDate;
	private String isBlocked;
	private Bill bill;

	public Pallet() {

	}

	public Pallet(int palletNbr, String cookieName, String location, String bakeDate, String isBlocked, Bill bill) {
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

	public String getBakeDate() {
		return bakeDate;
	}

	public void setBakeDate(String bakeDate) {
		this.bakeDate = bakeDate;
	}

	public String isBlocked() {
		return isBlocked;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public void setIsBlocked(String blocked) {
		// TODO Auto-generated method stub
		isBlocked=blocked;
		
	}
}