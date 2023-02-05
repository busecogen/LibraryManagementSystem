package Classes;

public class MultiMedia extends LibraryMaterial{
	
	private String materialType;
	private int hour;
	private double size;
	private String language;
	private String subTitle;
	

	public MultiMedia(String id, String name, String type, String pubDate, String materialType, String hour, String size, String language, String subTitle) {
		super(id, name, type,pubDate);
		this.materialType = type;
		this.hour = Integer.parseInt(hour);
		this.size = Double.parseDouble(size);
		this.language = language;
		this.subTitle = subTitle;
	}

	
	@Override
	public boolean getEsteemable(String pubDate) {
		
		if(Integer.parseInt(pubDate) < 1980) {
			return true;
		}
		return false;
	}

	@Override
	public double calculateCharge(int borDay, String pubDate) {
		double charge=1.5;
		if(getEsteemable(pubDate)) {
			return (borDay)*charge*2;
		}else {
			return (borDay)*charge;
		}
	}


	@Override
	public boolean isLost() {
		if(totalDay>60 && !borrowingDate.equalsIgnoreCase("0")) {
			return true;
		}
		return false;
	}


	@Override
	public String toString() {
		return "\tMulti Media:\n" + super.toString() 
				+ "Type: " + materialType 
				+ "\nHour: " + hour 
				+ "\nSize: " + size 
				+ "\nLanguage: " + language
				+ "\nSub Title: " + subTitle + "\n";
	}
	
	

}
