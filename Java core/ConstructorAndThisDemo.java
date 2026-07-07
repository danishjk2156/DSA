class Student {
    String name;
    int age;
    String course;

    Student(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
        System.out.println("3-param constructor called");
    }

    Student(String name, int age) {
        this(name, age, "Undecided");
        System.out.println("2-param constructor called -> chained to 3-param");
    }

    Student() {
        this("Unknown", 0, "Undecided");
        System.out.println("no-arg constructor called -> chained to 3-param");
    }

    void displayDetails() {
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Course: " + this.course);
        System.out.println();
    }

    void updateName(String name) {
        this.name = name;
    }

    void compare(Student other) {
        if (this.name.equals(other.name) && this.age == other.age) {
            System.out.println(this.name + " and " + other.name + " have same details");
        } else {
            System.out.println(this.name + " and " + other.name + " are different");
        }
    }
}

public class ConstructorAndThisDemo {
    public static void main(String[] args) {
        System.out.println("=== Constructor Overloading & this Demo ===\n");

        Student s1 = new Student("Aman", 20, "Java");
        System.out.println("s1 details:");
        s1.displayDetails();

        Student s2 = new Student("Sara", 22);
        System.out.println("s2 details:");
        s2.displayDetails();

        Student s3 = new Student();
        System.out.println("s3 details:");
        s3.displayDetails();

        System.out.println("=== this keyword usages ===");
        s1.updateName("Aman Kumar");
        System.out.println("After updateName:");
        s1.displayDetails();

        System.out.println("Comparing s2 and s3:");
        s2.compare(s3);
    }
}
