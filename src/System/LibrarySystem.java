package System;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

import Classes.Book;
import Classes.LibraryMaterial;
import Classes.MultiMedia;
import user.Student;

public class LibrarySystem {
	public static int systemDate = 10;
	public static HashSet<Book> bookList = new HashSet<Book>();
	public static HashSet<MultiMedia> multimediaList = new HashSet<MultiMedia>();
	public static HashSet<LibraryMaterial> libraryMaterial= new HashSet<LibraryMaterial>();
	public static HashSet<LibraryMaterial> borrowedMaterial = new HashSet<LibraryMaterial>();
	public static TreeSet<Student> studentSet = new TreeSet<Student>();

	public static String MATERIAL_FILENAME = "matlist.txt";
	public static String STUDENT_FILENAME = "studentlist.txt";

	public static boolean readFromFile() {
		File f = new File(MATERIAL_FILENAME);
		Scanner input = null;
		try {
			input = new Scanner(f);
			while (input.hasNext()) {
				String id,name,type,pubDate,author,pages,materialType,language,subTitle,hour,size;
				String all[] = input.nextLine().split("\\*");
				id=all[0];
				name=all[1];
				type=all[2];
				pubDate=all[3];
				if(type.equalsIgnoreCase("Book")) {
					author=all[4];
					pages=all[5];
					bookList.add(new Book(id,name,type,pubDate,author,pages));
				}else {
					materialType=all[4];
					hour=all[5];
					size=all[6];
					language=all[7];
					subTitle=all[8];
					multimediaList.add(new MultiMedia(id, name,type,pubDate,materialType,hour,size,language,subTitle));
				}
			}
			libraryMaterial.addAll(bookList);
			libraryMaterial.addAll(multimediaList);
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public static boolean readStudentFromFile() {
		File student = new File(STUDENT_FILENAME);
		Scanner inp;
		String all[];
		try {
			inp = new Scanner(student);
			while (inp.hasNext()) {
				all = inp.nextLine().split("\\*");
				String materials[] = all[5].split("\\&");
				String days[]=all[6].split("\\&");
				Student s = new Student(all[0], all[1], all[2], all[3], all[4]);
				studentSet.add(s);
				int f=0;
				for(String i: materials) {
					LibraryMaterial y=searchMaterial(i);
					if(y!=null) {
						y.setBorrowingDate(days[f]);
						y.setTotalDay(systemDate-Integer.parseInt(y.getBorrowingDate()));
						s.addMaterial(y);
						borrowedMaterial.add(y);						
					}
					f++;
					
				}
			}
			inp.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void insertBorrowedMaterial(String id) {
		for(Student s: studentSet) {
			s.addMaterial(searchMaterial(id));
			borrowedMaterial.add(searchMaterial(id));
		}
	}

	public static double calculateStuFine(Student s) {
		double charge=0.0;
		double totalFine = 0.0;
		TreeSet<LibraryMaterial> lms = s.getOwnedMaterial();
		for(LibraryMaterial lm : lms) {
			int day = lm.getTotalDay();
			String pdate = lm.getPubDate();
			charge = lm.calculateCharge(day, pdate);
			totalFine += charge;
			s.setFine(totalFine);
		}
		return charge;
		
	}

	public static LibraryMaterial searchMaterial(String id) {
		for (LibraryMaterial l : libraryMaterial)
			if (l.getId().equalsIgnoreCase(id))
				return l;
		return null;
	}

	public static boolean deleteMaterials(String id) {
		for (LibraryMaterial l : borrowedMaterial) {
			if (l.getId().equalsIgnoreCase(id)) {
				l.setTotalDay(0);
				borrowedMaterial.remove(l);
				return true;
			}
		}
		return false;
	}

	public static boolean checkStuId(int id) {
		for (Student s : studentSet)
			if (s.getStuId() == id)
				return true;
		return false;
	}

	public static boolean addStudent(Student stu) {
		if (checkStuId(stu.getStuId()))
			return false;
		studentSet.add(stu);
		return true;

	}

	public static boolean deleteStudent(int id) {
		for (Student s : studentSet) {
			if (s.getStuId() == id)
				studentSet.remove(s);
			return true;
		}
		return false;
	}

	public static Student searchStudent(int id) {
		for (Student s : studentSet)
			if (s.getStuId() == id)
				return s;
		return null;
	}

	public static String[] getStudentId() {
		int i=0;
		String[] ids = new String[studentSet.size()];
		for(Student s:studentSet) {
			ids[i]=s.getStuId()+"";
			i++;
		}
		return ids;
	}
	
	
	public static String displayBorrowedMaterials() {
		String res = "";
		for (LibraryMaterial l : borrowedMaterial) {
			res += l.toString() + "-".repeat(50) + "\n";
		}
		return res;
	}

	public static void addBook(String id, String name, String type, String pubDate, String author, String pages) {
		Book b = new Book(id, name,type, pubDate, author, pages);
		borrowedMaterial.add(b);
	}

	public static void addMultimedia(String id, String name, String type, String pubDate, String medType, String hour, String size,
			String language, String subTitle, String borDate) {
		MultiMedia m = new MultiMedia(id, name, type, pubDate, medType, hour, size, language, subTitle);
		borrowedMaterial.add(m);
	}
}
