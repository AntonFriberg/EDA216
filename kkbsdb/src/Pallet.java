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
		isBlocked=blocked;
	}
	/*
	 * Formatting the output to fit a table-like structure
	 * in the program using | as divider. 
	 * Y means blocked, N means not blocked. 
	 */
	public String toString() {
		String format = "%1$-5d %2$-10s %3$-18s";
		String blocked = isBlocked() != null ? "Y" : "N";
		return String.format(format, palletNbr,
				"| " + (bakeDate.split(" "))[0], "| " + cookieName) + "| " + blocked;
		
	}
}