import java.util.Date;

public class Bill {
	private int billId;
	private String custName,custAddr;
	private char isDel;
	private Date date;
	
	public Bill(){
	
	}
	
	public Bill(int billId, Date date, char isDel, String custName,String custAddr){
		this.billId=billId;
		this.date=date;
		this.isDel=isDel;
		this.custName=custName;
		this.custAddr=custAddr;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustAddr() {
		return custAddr;
	}

	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}

	public char getIsDel() {
		return isDel;
	}

	public void setIsDel(char isDel) {
		this.isDel = isDel;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
