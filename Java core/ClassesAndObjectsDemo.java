class Student {
    String name;
    int age;
    String course;

    Student(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }

    void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Course: " + course);
        System.out.println();
    }
}

public class ClassesAndObjectsDemo {
    public static void main(String[] args) {
        Student student1 = new Student("Aman", 20, "Java");
        Student student2 = new Student("Sara", 22, "Data Structures");

        System.out.println("Student 1 Details:");
        student1.displayDetails();

        System.out.println("Student 2 Details:");
        student2.displayDetails();
    }
}
