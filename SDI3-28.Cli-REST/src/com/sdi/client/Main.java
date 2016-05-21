package com.sdi.client;

import java.util.List;

public class Main {
	private static final 
		String REST_SERVICE_URL = 
			"http://localhost:8280/SDI3-28/rest/UserServiceRs";

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		List<User> usuarios = restGetUsers(); // A GET operation
		
		showStudents(alumnos);
		
		String res = getStudentByIdAsJsonString(alumnos.get(0));
		System.out.println(res);
		
		res = getStudentByIdAsXmlString(alumnos.get(0));
		System.out.println(res);
		
		Alumno a = getStudentByIdAsObject(alumnos.get(0));
		printStudent(a);
		
		a = createNewStudent(); // A PUT operation
		updateStudent(alumnos.get(0));// A POST operation
		deleteStudent(alumnos.get(1));// A DELETE operation
		
		System.out.println("\n-- ws REST JAX-RS remote client ended -");
	}
}
