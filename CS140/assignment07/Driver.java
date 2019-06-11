package assignment07;
import assignment06.Course;
import assignment06.Student;

public class Driver {
	
	public static void main(String[] args){
		UndergradStudent ugstu = new UndergradStudent();
		ugstu.getAllCourses().add(new CSGenedCourse(new CSGened[] {CSGened.B}));
		ugstu.getAllCourses().add(new CSGenedCourse(new CSGened[] {CSGened.H}));
		ugstu.getAllCourses().add(new CSGenedCourse(new CSGened[] {CSGened.C, CSGened.P, CSGened.N}));
		ugstu.getAllCourses().add(new CSGenedCourse(new CSGened[] {CSGened.G, CSGened.C}));
		ugstu.getAllCourses().add(new CSGenedCourse(new CSGened[] {CSGened.G}));
		ugstu.getAllCourses().add(new CSGenedCourse( new CSGened[] {CSGened.F1}));
		ugstu.getAllCourses().add(new CSGenedCourse( new CSGened[] {CSGened.A}));
		ugstu.getAllCourses().add(new CSGenedCourse( new CSGened[] {CSGened.L}));
		ugstu.getAllCourses().add(new CSGenedCourse( new CSGened[] {CSGened.J}));
		ugstu.getAllCourses().add(new CSGenedCourse( new CSGened[] {CSGened.M}));
		ugstu.getAllCourses().add(new CSGenedCourse( new CSGened[] {CSGened.N}));
		System.out.println("Expect true");
		System.out.println(ugstu.satisfiesCSGened());
		
		UndergradStudent ugstu2 = new UndergradStudent();
		ugstu2.getAllCourses().add(new CSGenedCourse(new CSGened[] {CSGened.B}));
		ugstu2.getAllCourses().add(new CSGenedCourse(new CSGened[] {CSGened.H}));
		ugstu2.getAllCourses().add(new CSGenedCourse(new CSGened[] {CSGened.C, CSGened.P, CSGened.N}));
		ugstu2.getAllCourses().add(new CSGenedCourse(new CSGened[] {CSGened.G, CSGened.C}));
		ugstu2.getAllCourses().add(new CSGenedCourse(new CSGened[] {CSGened.G}));
		ugstu2.getAllCourses().add(new CSGenedCourse( new CSGened[] {CSGened.F1}));
		ugstu2.getAllCourses().add(new CSGenedCourse( new CSGened[] {CSGened.A}));
		ugstu2.getAllCourses().add(new CSGenedCourse( new CSGened[] {CSGened.L}));
		ugstu2.getAllCourses().add(new CSGenedCourse( new CSGened[] {CSGened.J}));
		ugstu2.getAllCourses().add(new CSGenedCourse( new CSGened[] {CSGened.M}));
		ugstu2.getAllCourses().add(new CSGenedCourse( new CSGened[] {CSGened.A}));
		System.out.println("Expect false");
		System.out.println(ugstu2.satisfiesCSGened());
	}
}

