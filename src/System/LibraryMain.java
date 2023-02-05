package System;

import GUI.LibraryFrame;

public class LibraryMain {

	public static void main(String[] args) {
		
		if(LibrarySystem.readFromFile()) {
			if(LibrarySystem.readStudentFromFile()) {
				try {
					LibraryFrame frame = new LibraryFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
