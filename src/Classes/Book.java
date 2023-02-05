package Classes;

public class Book extends LibraryMaterial{
	
	private String author;
	private int pages;
	
	
	public Book(String id, String name, String type, String pubDate, String author, String pages) {
		super(id, name, type, pubDate);
		this.author=author; 
		this.pages= Integer.parseInt(pages);
	}
	
	@Override
	public boolean getEsteemable(String pubDate) {
		
		if(Integer.parseInt(pubDate) < 2000) {
			return true;
		}
		return false;
	}

	@Override
	public double calculateCharge(int totalDay, String pubDate) {
		double charge=0.5;
		if(totalDay>10) {
			if(getEsteemable(pubDate)) {
				return (totalDay)*charge*2;
			}else {
				return (totalDay)*charge;
			}
		}else {
			return 0.0;
		}
	}
	
	
	@Override
	public boolean isLost() {
		if(totalDay>50 && !borrowingDate.equalsIgnoreCase("0")) {
			return true;
		}
		return false;
	}

	

	@Override
	public String toString() {
		return "\tBook\n"+ super.toString() 
				+ "Author: " + author 
				+ "\nPages: " + pages + "\n";
	}
	


}
