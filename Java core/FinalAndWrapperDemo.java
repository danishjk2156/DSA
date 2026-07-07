class Student {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void display() {
        System.out.println(name + " (" + age + ")");
    }
}

final class MathConstants {
    static final double PI = 3.14159;
    static final double E = 2.71828;

    static void showConstants() {
        System.out.println("PI = " + PI + ", E = " + E);
    }
}

class Parent {
    final void cannotOverride() {
        System.out.println("This method is final - cannot be overridden");
    }
}

class Child extends Parent {
    // void cannotOverride() { } // would cause compile error
}

public class FinalAndWrapperDemo {

    static final int MAX_SCORE = 100;

    static void attemptReassign(final int param) {
        // param = 50; // compile error - final parameter
        System.out.println("Final parameter value: " + param);
    }

    public static void main(String[] args) {

        System.out.println("=== FINAL WITH PRIMITIVE ===");
        final int x = 10;
        System.out.println("x = " + x);
        // x = 20; // would cause compile error

        System.out.println("\n=== FINAL WITH OBJECT - CAN CHANGE, CANNOT REASSIGN ===");
        final Student s = new Student("Aman", 20);
        System.out.print("Original: ");
        s.display();

        s.name = "Rahul";
        s.age = 22;
        System.out.print("After changing fields: ");
        s.display();

        // s = new Student("New", 99); // would cause compile error
        // s = null;                   // would cause compile error

        System.out.println("\n=== FINAL METHOD ===");
        Child c = new Child();
        c.cannotOverride();

        System.out.println("\n=== FINAL CLASS ===");
        MathConstants.showConstants();
        // cannot extend MathConstants

        System.out.println("\n=== FINAL STATIC CONSTANT ===");
        System.out.println("MAX_SCORE = " + MAX_SCORE);
        // MAX_SCORE = 200; // would cause compile error

        System.out.println("\n=== WRAPPER CLASS - AUTOBOXING & UNBOXING ===");
        Integer a = 10;
        Integer b = 20;
        int sum = a + b;
        System.out.println(a + " + " + b + " = " + sum);

        System.out.println("\n=== WRAPPER CLASS - CACHING ===");
        Integer cached1 = 127;
        Integer cached2 = 127;
        System.out.println("127: cached1 == cached2? " + (cached1 == cached2));

        Integer uncached1 = 200;
        Integer uncached2 = 200;
        System.out.println("200: uncached1 == uncached2? " + (uncached1 == uncached2));
        System.out.println("200: uncached1.equals(uncached2)? " + uncached1.equals(uncached2));

        System.out.println("\n=== WRAPPER CLASS - PARSING ===");
        String numStr = "456";
        int parsed = Integer.parseInt(numStr);
        System.out.println("Parsed \"" + numStr + "\" to int: " + parsed);

        String hexStr = Integer.toHexString(255);
        System.out.println("255 in hex: " + hexStr);

        System.out.println("\n=== WRAPPER CLASS - NULL HANDLING ===");
        Integer nullable = null;
        System.out.println("Wrapper can be null: " + nullable);
        // int primitive = null; // would cause compile error

        System.out.println("\n=== WRAPPER CLASS - UTILITY METHODS ===");
        System.out.println("Max of 10 and 20: " + Integer.max(10, 20));
        System.out.println("Min of 10 and 20: " + Integer.min(10, 20));
        System.out.println("Binary of 10: " + Integer.toBinaryString(10));

        System.out.println("\n=== FINAL PARAMETER ===");
        attemptReassign(99);

        System.out.println("\n=== FINAL WRAPPER REFERENCE ===");
        final Integer wrapper = 50;
        // wrapper = 100; // would cause compile error
        System.out.println("Final wrapper value: " + wrapper);

        System.out.println("\n=== FINAL OBJECT WITH WRAPPER FIELD ===");
        java.util.List<String> names = new java.util.ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        System.out.println("Names: " + names);
    }
}
