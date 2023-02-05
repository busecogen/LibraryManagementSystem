package user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.TreeSet;

import Classes.LibraryMaterial;

public class Student implements Comparable<Student>{
	
	private TreeSet<LibraryMaterial> ownedMaterial = new TreeSet<LibraryMaterial>();
	
	private int stuId;
	private String name;
	private String surname;
	private String deptName;
	private String email;
	private double fine;
	
	public static int studentNumber=0;
	

	public Student(String stuId, String name, String surname, String deptName, String email) {
		this.stuId = Integer.parseInt(stuId);
		this.name=name;
		this.surname=surname;
		this.deptName = deptName;
		this.email = email;
		this.fine=0;
		studentNumber++;
	}
	
	
	public String getName() {
		return name;
	}



	public String getSurname() {
		return surname;
	}



	public String getDeptName() {
		return deptName;
	}



	public String getEmail() {
		return email;
	}



	public double getFine() {
		return fine;
	}



	public static int getStudentNumber() {
		return studentNumber;
	}


	public int getStuId() {
		return stuId;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	public void addMaterial(LibraryMaterial lm) {
		if(lm!=null) {
			ownedMaterial.add(lm);
		}
		
	}

	
	public TreeSet<LibraryMaterial> getOwnedMaterial() {
		return ownedMaterial;
	}
	
	
	public String displayOwnedMaterials() {
		String out="";
		for(LibraryMaterial a :ownedMaterial) {
			out+=a.toString()+"\n";
		}
		return out;
	}

	public void setOwnedMaterial(TreeSet<LibraryMaterial> ownedMaterial) {
		this.ownedMaterial = ownedMaterial;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(stuId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return stuId == other.stuId;
	}


	@Override
	public int compareTo(Student s) {
		return stuId-s.stuId;
	}

	@Override
	public String toString() {
		return "Student Information\n Student Id= " 
				+ stuId + "\nName= " + name +" "+ surname
				+ "\nDepartment Name= " + deptName 
				+ "\nEmail= " + email + "\n";
	}


	
	
}
