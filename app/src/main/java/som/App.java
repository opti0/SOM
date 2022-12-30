package som;

import som.server.database.student.Student;
import som.server.database.student.StudentDao;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        var students = new StudentDao().getAll();
        var student = new StudentDao().get("12345678901");
        System.out.println(student.isPresent());
        System.out.println(students.get(0).pesel());
    }
}
