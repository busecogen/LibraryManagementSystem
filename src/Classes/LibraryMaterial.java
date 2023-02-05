package Classes;

public abstract class LibraryMaterial implements Comparable<LibraryMaterial>, Esteemable {
	protected String id;
	protected String name;
	protected String type;
	protected String pubDate;
	protected String borrowingDate;
	protected String retDate;
	protected int totalDay;
	
	public LibraryMaterial(){
		
	}

	public LibraryMaterial(String id, String name, String type,String pubDate) {
		this.id = id;
		this.name = name;
		this.type=type;
		this.pubDate = pubDate;
		this.borrowingDate="0";
		this.retDate="0";
		this.totalDay = 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public int getTotalDay() {
		return totalDay;
	}

	public void setTotalDay(int borDate) {
		this.totalDay = borDate;
	}

	
	public String getType() {
		return type;
	}

	public String getBorrowingDate() {
		return borrowingDate;
	}

	public void setBorrowingDate(String borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	public String getRetDate() {
		return retDate;
	}

	public void setRetDate(String retDate) {
		this.retDate = retDate;
	}

	public abstract double calculateCharge(int totalDay,  String pubDate);
	
	
	@Override
	public String toString() {
		return "Id: " +id + "\nName: " + name 
				+"\nBorrow on Day: " + borrowingDate
				+"\nReturned on Day: " + retDate
				+"\nTotal Days since borrowed: " + totalDay
				+ "\nPublication date: " + pubDate + "\n";
		
	}
	
	public int compareTo(LibraryMaterial lm) {
		return id.compareTo(lm.id);
	}
	
	
	
	
	
	
	
}
