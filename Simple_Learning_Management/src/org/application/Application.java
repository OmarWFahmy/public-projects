package org.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.application.data.Course;
import org.application.data.Student;

public class Application {

	public static void main(String[] args) {
		
		System.out.println("Welcome to LMS");
		System.out.println("created by {Omar Fahmy_21/1/2023}");
		System.out.println("====================================================================================");
		System.out.println("Home page");
		System.out.println("====================================================================================");
		/////////////=====[Initializing Students and Courses and importing them into classes]=====/////////////
		
		String studentfiledata = handlestudentfile();

		Student[] student = null;
		
		student = GenerateStudents(studentfiledata);
		
		String coursefiledata = handlecoursefile();
		
		Course[] course = null;
		
		course = GenerateCourses(coursefiledata);
		
		System.out.println("------------------------------------------------------------------------------------");
		
		int studentid = -1;
		
		boolean found = false;
		
		for(;;)
		{
			if(!found)
			{
				System.out.println("Please select the required student: ");
				
				//Scanner sc = new Scanner(System.in);
				
				String studentstringid = getInput();//sc.next();
				
				if(isNumeric(studentstringid))
				{
					studentid = Integer.parseInt(studentstringid);
					
					found = DisplayStudent(studentid, student);
				}
				else
				{
					System.out.println("Wrong choice! please enter a valid choice");
				}
				//sc.close();
			/*if(found)
			{
				sc.close();
				break;
			}*/
			}
			else
			{
				found = MenuUI(course, student, studentid);
			}
		}				
	}
	
	public static String getInput()
	{
		Scanner sc = new Scanner(System.in);
		
		String input = sc.next();
		
		return input;
	}
	
	public static boolean MenuUI(Course[] course, Student[] student, int studentid)
	{
		boolean found = true;
		System.out.println("------------------------------------------------------------------------------------");

		System.out.println("Please choose from the following:");
		System.out.println("a - Enroll in a course");
		System.out.println("d - Unenroll from an existing course");
		System.out.println("r - Replacing an existing course");
		System.out.println("b - Back to the main page");
		System.out.println("please select the required action: ");

		String choices = getInput();//sc.next();
		
		switch(choices)
		{
			case "a":
			{
				EnrollmentUI(course, student[studentid]);
				UpdateStudentCourseDetails(student);
				break;
			}
			case "d":
			{
				found = UnEnrollmentUI(course, student[studentid]);
				UpdateStudentCourseDetails(student);
				break;
			}
			case "r":
			{
				found = ReplaceUI(course, student[studentid]);
				UpdateStudentCourseDetails(student);
				
				break;
			}
			case "b":
			{
				found = false;
				break;
			}
			default:
			{
				System.out.println("Wrong choice! please enter a valid choice");
				break;
			}
		}
		return found;
	}
	
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static boolean ReplaceUI(Course[] course, Student student)
	{
		boolean found = true;
		
		System.out.println("Please enter the course id to be replaced: ");
		
		String choice = getInput();
		
		if(isNumeric(choice))
		{
			int courseid = Integer.parseInt(choice);
			
			ArrayList<Course> studentcourses = student.getCourses();
			
			if(studentcourses.size() == 0)
			{
				System.out.println("Failed to replace as the student hasn't enrolled in any course yet");
			}
			else if(studentcourses.size()-1 >= courseid)
			{
				//courseid--;
				System.out.println("====================================================================================================");
				System.out.println(course[0]);
				System.out.println("----------------------------------------------------------------------------------------------------");
				for(int i=0; i<course.length; i++)
				{
					System.out.println(course[i].showCourse());
				}
				
				System.out.println("Please enter the required course id to replace:");
				
				String newchoice = getInput();
				
				if(isNumeric(newchoice))
				{
					int newcourseid = Integer.parseInt(newchoice);
					
					if(newcourseid <= 0 || newcourseid >= course.length)
					{
						System.out.println("Failed to replace: The course with id = " + newcourseid + " is not exist");
					}
					else
					{
						Course getcoursename = studentcourses.get(courseid);
						studentcourses.remove(courseid);
						studentcourses.add(course[newcourseid]);
						student.setCourses(studentcourses);
						System.out.println("Courses replaced successfully from the " + getcoursename.getCourseName() + " course to " + course[newcourseid].getCourseName() + " course");
					}
				}
				else
				{
					System.out.println("Wrong choice! please enter a valid choice");
				}
			}
			else
			{
				System.out.println("The input you have provided is invalid, please enter a valid input");
			}
		}
		else
		{
			System.out.println("Wrong choice! please enter a valid choice");
		}
		
		return found;
	}
	
	public static boolean UnEnrollmentUI(Course[] course, Student student)
	{
		boolean found = true;
		System.out.println("Please enter course id:");
		String choice = getInput();
		
		if(isNumeric(choice))
		{
			ArrayList<Course> studentcourses = student.getCourses();
			
			int courseid = Integer.parseInt(choice);
			
			if(studentcourses.size()-1 >= courseid)
			{
				Course checkcname = studentcourses.get(courseid);
				System.out.println("Unenrolled successfully from the " + checkcname.getCourseName() + " course");
				studentcourses.remove(courseid);
				student.setCourses(studentcourses);
				found = false;
			}
			else if(studentcourses.size() == 0)
			{
				System.out.println("Failed to unenroll: The student has not enrolled in any course yet.");
			}
			else
			{
				System.out.println("The input you have provided is invalid, please enter a valid input");
			}
		}
		else
		{
			System.out.println("Wrong choice! please enter a valid choice");
		}
		return found;
		
	}
	
	public static void EnrollmentUI(Course[] course, Student student)
	{
		System.out.println("Enrollment page");
		System.out.println("====================================================================================================");
		System.out.println(course[0]);
		System.out.println("----------------------------------------------------------------------------------------------------");
		for(int i=0; i<course.length; i++)
		{
			System.out.println(course[i].showCourse());
		}
		System.out.println("----------------------------------------------------------------------------------------------------");
		
		System.out.println("Please make one of the following:");
		
		boolean check = false;
		
		for(;;)
		{
			System.out.println("Enter the course id that you want to enroll the student to");
			System.out.println("Enter b to go back to the home page");
			System.out.println("Please select the required action:");
			
			//Scanner scanaction = new Scanner(System.in);
			
			String choice = getInput();//scanaction.next();
			
			switch(choice) 
			{
				case "b":
				{
					check = true;
					break;
				}
				default:
				{
					if(isNumeric(choice))
					{
						int courseid = Integer.parseInt(choice);
						
						if(courseid <= 0 || courseid >= course.length)
						{
							System.out.println("Failed to enroll: The course with id = " + courseid + " is not exist");
						}
						else
						{
							Enroll(course[courseid], student);
						}
					}
					else
					{
						System.out.println("Wrong choice! please enter a valid choice");
					}
					break;
				}
			}
			if(check)
			{
				break;
			}
		}
	}
	
	public static void Enroll(Course course, Student student)
	{
		ArrayList<Course> studentcourses = student.getCourses();
		
		if(studentcourses.size() == 6)
		{
			System.out.println("Students can’t enroll in more than 6 programs at the same time.");
		}
		else
		{
			boolean valid = true;
			for(int i=0; i<studentcourses.size(); i++)
			{
				Course courcecheck = studentcourses.get(i);
				if(courcecheck.getId() == course.getId())
				{
					valid = false;
				}
			}
			if(valid)
			{
				studentcourses.add(course);
				student.setCourses(studentcourses);
				System.out.println("The student is Enrolled Successfully in the " + course.getCourseName() + " course");
				
				
			}
			else
			{
				System.out.println("Student is already enrolled in this course.");
			}
		}
		/*int countcourses = student.getCountCourses();
		Course[] studentcourses = student.getCourses();
		
		if(countcourses != 0)
		{
			if(countcourses == 5)
			{
				System.out.println("Students can’t enroll in more than 6 programs at the same time.");
			}
			else
			{
			}
			for(int i=0; i<studentcourses.length; i++)
			{
				studentcourses[i].getId();
			}
		}
		else
		{
			countcourses++;
			student.setCountCourses(countcourses);
			studentcourses[0] = new Course();
			studentcourses[0].setId(course.getId());
			studentcourses[0].setCourseName(course.getCourseName());
			studentcourses[0].setCourseDuration(course.getCourseDuration());
			studentcourses[0].setInstructor(course.getInstructor());
			studentcourses[0].setLocation(course.getLocation());
			studentcourses[0].setCourseTime(course.getCourseTime());
			
			student
			
			System.out.println("The student is Enrolled Successfully in the " + student.getCourses() + " course");
		}*/
	}
	
	public static void UpdateStudentCourseDetails(Student[] student) {
		
		String path = "S:\\javaworkspace\\Simple_Learning_Management\\bin\\org\\application\\data\\studentcoursedetails.json";
		
		File nf = new File(path);
		
		try {
			nf.createNewFile();
			
			FileWriter fw = new FileWriter(nf.getPath());
			
			StringBuilder input = new StringBuilder();
			String newdata = null;
			
			input.append("{\n");
			
			for(int i=1; i<student.length; i++)
			{
				
				ArrayList<Course> studentcourses = student[i].getCourses();
				if(studentcourses.size() != 0)
				{
					input.append("\""+ i + "\":[\n");
					for(int j=0; j<studentcourses.size(); j++)
					{
						Course courseid = studentcourses.get(j);
						if(j+1 > studentcourses.size())
						{
							input.append(courseid.getId()+"\n");
						}
						else
						{
							input.append(courseid.getId()+",\n");
						}
					}
					input.append("],\n");
				}
				
			}
			
			input.append("}\n");
			
			newdata = input.toString();
			/*		
			 * StringBuilder data = new StringBuilder();
			String newdata = null;
			String nd = null;

					//System.out.println("Loading the course data for the first time before converting:");
					while(sf.hasNextLine())
					{
						data.append(sf.nextLine());
					}
					
					newdata = data.toString();
	
					nd = preparecoursefile(newdata);*/
			
			fw.write(newdata);
			
			//System.out.println("File has been created: " + nf.getName());
			
			fw.close();
			
			
		}
		catch(IOException e) {
			System.out.println("Failed to create a file or file was already created.");
		}
		
		//Student course details.json
	}
	
	public static boolean DisplayStudent(int studentid, Student[] student)
	{
		boolean found = false;
		System.out.println("====================================================================================");
		System.out.println("Student Details page");
		System.out.println("====================================================================================");
		if(studentid > student.length-1 || studentid <= 0)
		{
			System.out.println("Invalid Student ID");
			found = false;
		}
		else
		{
			System.out.println("Name: " + student[studentid].getName() + "   " + "Grade: " + student[studentid].getGrade() + "   " + "Email: " + student[studentid].getEmail());
			System.out.println("------------------------------------------------------------------------------------");
			System.out.println("Enrolled courses.");
			
			ArrayList<Course> coursescount = student[studentid].getCourses();
			
			if(coursescount.size() != 0)
			{
				for(int i=0; i<coursescount.size(); i++) {
					Course displayCourse = new Course();
					
					displayCourse = coursescount.get(i);
					System.out.println(i + "- "+ displayCourse.showCourse());
				}
			}
			else
			{
				System.out.println("This student hasn't enrolled in any courses yet.");
			}
			found = true;
			
		}
		return found;
		
	}
	
	public static Student[] GenerateStudents(String studentfiledata)
	{
		Student[] student = null;
		String[] studentsdata = studentfiledata.split("\n");
		
		student = new Student[studentsdata.length];//To remove the header

		for(int i=0; i<studentsdata.length; i++)
		{
			String[] studentdata = studentsdata[i].split(",");
			
			student[i] = new Student();
			
			student[i].setId(studentdata[0].toString());
			student[i].setName(studentdata[1].toString());
			student[i].setGrade(studentdata[2].toString());
			student[i].setEmail(studentdata[3].toString());
			student[i].setAddress(studentdata[4].toString());
			student[i].setRegion(studentdata[5].toString());
			student[i].setCountry(studentdata[6].toString());
						
		}
				
		for(int i=0; i<student.length; i++) {
			System.out.println(student[i].showStudent());
		}
		return student;
	}
	
	public static Course[] GenerateCourses(String coursefiledata)
	{
		Course[] course = null;
		String[] coursesdata = coursefiledata.split("\n");
		
		course = new Course[coursesdata.length];//To Remove the header of the courses
		
		
		for(int i=0; i<coursesdata.length; i++)
		{
			String[] coursedata = coursesdata[i].split(",");
			
			course[i] = new Course();
			
			course[i].setId(coursedata[0].toString());
			course[i].setCourseName(coursedata[1].toString());
			course[i].setInstructor(coursedata[2].toString());
			course[i].setCourseDuration(coursedata[3].toString());
			course[i].setCourseTime(coursedata[4].toString());
			course[i].setLocation(coursedata[5].toString());
		}
		
		/*for(int i=0; i<course.length;i++)
		{
			System.out.println(course[i].showCourse());
		}*/
		return course;
	}
	
	public static String loadcoursefile(String path) {
		
		StringBuilder data = new StringBuilder();
		String newdata = null;
		String nd = null;
		
		File file = new File(path);
		
		if(file.exists())
		{
			Scanner sf = null;
			
			try {
				sf = new Scanner(file);
				//System.out.println("Loading the course data for the first time before converting:");
				while(sf.hasNextLine())
				{
					data.append(sf.nextLine());
				}
				
				newdata = data.toString();
				/*System.out.println();
				System.out.println(newdata);
				System.out.println();
				
				System.out.println("Editing the string of the new data");
				System.out.println();*/
				nd = preparecoursefile(newdata);
				
				//System.out.println(nd);
				
			} 
			catch (FileNotFoundException e) {
				System.out.println("Course file is not found.");
			}
		}
		
		return nd;
	}
	
	public static String preparecoursefile(String data) {		
		
		String newdata;
		
		newdata = data.replace("<row>", "");
		newdata = newdata.replace(",", "");
		newdata = newdata.replace("</row>", "\n");
		newdata = newdata.replace("<root>", "");
		newdata = newdata.replace("</root>", "");
		newdata = newdata.replace("<id>", "");
		newdata = newdata.replace("</id>", ",");
		newdata = newdata.replace("<CourseName>", "");
		newdata = newdata.replace("</CourseName>", ",");
		newdata = newdata.replace("<Instructor>", "");
		newdata = newdata.replace("</Instructor>", ",");
		newdata = newdata.replace("<CourseDuration>", "");
		newdata = newdata.replace("</CourseDuration>", ",");
		newdata = newdata.replace("<CourseTime>", "");
		newdata = newdata.replace("</CourseTime>", ",");
		newdata = newdata.replace("<Location>", "");
		newdata = newdata.replace("</Location>", "");
		
		//System.out.println(newdata);
		
		int i = newdata.indexOf("<");
		int j = newdata.indexOf(">");
		String replacing = newdata.substring(i, j+1);
		newdata = newdata.replace(replacing,"");
		
		StringBuilder sb = new StringBuilder();//id,Course Name,Instructor,Course duration,Course time,Location
		
		sb.insert(0, "id,Course Name,Instructor,Course duration,Course time,Location\n");
		
		String newdata2 = sb.toString() + newdata;
		
		return newdata2;
	}
	
	public static void savecoursefile(String path, String data) {
		File nf = new File(path);
		
		try {
			nf.createNewFile();
			
			FileWriter fw = new FileWriter(nf.getPath());
			
			fw.write(data);
			
			//System.out.println("File has been created: " + nf.getName());
			
			fw.close();
			
			
		}
		catch(IOException e) {
			System.out.println("Failed to create a file or file was already created.");
		}
	}
	
	public static String handlecoursefile() {
		String path = "S:\\javaworkspace\\Simple_Learning_Management\\bin\\org\\application\\data\\coursedata.xml";
		
		String data = loadcoursefile(path);
		
		/*System.out.println("=====================================================================");
		
		System.out.println();
		System.out.println("-------------------------------");
		System.out.println("Current Courses List");
		System.out.println("-------------------------------");
		System.out.println();
		
		System.out.println(data);
		
		System.out.println("=====================================================================");*/
		
		//System.out.println();
		
		String newpath = "S:\\javaworkspace\\Simple_Learning_Management\\bin\\org\\application\\data\\coursedata.csv";
		
		savecoursefile(newpath, data);
		
		return data;
	}
	
	public static String handlestudentfile() {
		String path = "S:\\javaworkspace\\Simple_Learning_Management\\bin\\org\\application\\data\\student-data.txt";
		
		String data = loadstudentfile(path);
		
		//System.out.println("=====================================================================");
		
		//System.out.println("Made the changes on the loaded data : switched # to , and $ to \\n");
		
		//System.out.println();
		//System.out.println("-------------------------------");
		System.out.println("Student List");
		//System.out.println("-------------------------------");
		System.out.println();
		
		/*System.out.println(data);
		
		System.out.println("=====================================================================");*/
		
		System.out.println();
		
		String newfilepath = "S:\\javaworkspace\\Simple_Learning_Management\\bin\\org\\application\\data\\student-data.csv";
		
		savestudentfile(newfilepath, data);
		
		return data;
	}

	public static void savestudentfile(String path, String data) {
		
		File nf = new File(path);
		
		try {
			nf.createNewFile();
			
			FileWriter fw = new FileWriter(nf.getPath());
			
			fw.write(data);
			
			//System.out.println("File has been created: " + nf.getName());
			
			fw.close();
			
		} catch (IOException e) {
			System.out.println("Failed to create a file or file was already created.");
		}		
	}
	
	public static String loadstudentfile(String path) {
		

		File file = new File(path);
		
		String data = null;
		
		String newdata = null;
		
		//System.out.println("=====================================================================");
		
		if(file.exists())
		{
			Scanner fr = null;
			try {
				fr = new Scanner(file);
				
				while(fr.hasNextLine())
				{
					data = fr.nextLine();
					/*System.out.println("Loading the student data for the first time before converting:");
					System.out.println(data);
					System.out.println();*/
				}
				newdata = data.replace('#', ',');
				newdata = newdata.replace('$', '\n');				
			} 
			catch (FileNotFoundException e) {
				System.out.println("Student file is not found.");
			}

		}		
		return newdata;
	}

}
