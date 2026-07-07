# Classes and Objects in Java

## What is a Class?

A class is a blueprint or template used to create objects. It defines the data and behavior that its objects will have.

For example, if `Student` is a class, it can define:

- data: name, age, roll number
- behavior: study, display details

## What is an Object?

An object is a real instance of a class. When we create an object, memory is allocated for it, and it can use the variables and methods defined inside the class.

An object represents a real-world entity in a program. For example, a student, car, bank account, employee, or book can be represented as an object.

Each object has its own copy of instance variables. If we create two objects from the same class, both objects can store different values.

Example:

```java
Student student1 = new Student("Aman", 20);
```

Here:

- `Student` is the class name.
- `student1` is the object reference.
- `new Student(...)` creates a new object.

## How to Access Instance Variables

Instance variables are variables declared inside a class but outside any method. They belong to an object.

We access instance variables using the dot operator:

```java
objectName.variableName
```

Example:

```java
class Student {
    String name;
    int age;
}

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student();

        student1.name = "Aman";
        student1.age = 20;

        System.out.println(student1.name);
        System.out.println(student1.age);
    }
}
```

Output:

```text
Aman
20
```

Here:

- `name` and `age` are instance variables.
- `student1.name` assigns and accesses the `name` variable of the `student1` object.
- `student1.age` assigns and accesses the `age` variable of the `student1` object.

## Class vs Object

| Class | Object |
| --- | --- |
| A class is a blueprint or plan. | An object is a real instance created from a class. |
| It does not take memory for object data until an object is created. | It takes memory when it is created using `new`. |
| It defines variables and methods. | It uses the variables and methods defined by the class. |
| Example: `Student` | Example: `student1`, `student2` |

Simple example:

```java
Student student1 = new Student("Aman", 20, "Java");
```

Here, `Student` is the class and `student1` is the object.

## Properties of an Object

An object mainly has three properties:

1. State

   State means the data or values stored inside an object.

   Example: `name`, `age`, and `course` are the state of a `Student` object.

2. Behavior

   Behavior means the actions an object can perform. These actions are written as methods inside the class.

   Example: `displayDetails()` is a behavior of the `Student` object.

3. Identity

   Identity means each object is unique, even if two objects have the same data.

   Example: `student1` and `student2` are two different objects.

## Stack and Heap Memory in Java

Java uses memory mainly in two important areas:

- stack memory
- heap memory

These are used to store different types of data while a program is running.

## What is Stack Memory?

Stack memory is used for method execution. Whenever a method is called, Java creates a new block in stack memory for that method.

This block stores:

- local variables
- method parameters
- references to objects

Example:

```java
public static void main(String[] args) {
    Student student1 = new Student("Aman", 20, "Java");
}
```

Here, `student1` is a reference variable. It is stored in stack memory because it is created inside the `main` method.

## What is Heap Memory?

Heap memory is used to store objects created using the `new` keyword.

Example:

```java
new Student("Aman", 20, "Java");
```

This creates a real `Student` object in heap memory.

The object contains:

- `name`
- `age`
- `course`

## How Class and Object Are Stored

A class itself is not stored like a normal object variable. When a Java program runs, the class information is loaded by the JVM. This class information contains details such as:

- class name
- fields
- methods
- constructors

When we create an object from the class, the actual object data is stored in heap memory.

Example:

```java
Student student1 = new Student("Aman", 20, "Java");
```

Memory working:

- `student1` is stored in stack memory.
- `new Student("Aman", 20, "Java")` creates an object in heap memory.
- The `student1` reference points to that object in heap memory.

Simple diagram:

```text
Stack Memory                  Heap Memory
------------                  -----------------------------
student1  ----------------->  Student object
                              name = "Aman"
                              age = 20
                              course = "Java"
```

## Why Stack and Heap Are Used

Java uses stack and heap because they solve different memory needs.

Stack is used because:

- it is fast
- method calls can be managed easily
- memory is removed automatically when a method finishes

Heap is used because:

- objects may need to live longer than one method call
- many methods can use the same object
- objects can be managed by the garbage collector

## How Object Memory Works

When this line runs:

```java
Student student2 = new Student("Sara", 22, "Data Structures");
```

Java does these steps:

1. Creates a reference variable `student2` in stack memory.
2. Creates a new `Student` object in heap memory.
3. Stores `"Sara"`, `22`, and `"Data Structures"` inside that object.
4. Connects `student2` to the heap object.

So, the reference is on the stack, but the actual object is on the heap.

## Dynamic Memory Allocation in Java

Dynamic memory allocation means memory is given to a program while the program is running.

In Java, objects are created dynamically using the `new` keyword. This means the memory for an object is not fixed before the program starts. It is allocated when the object is actually created.

Example:

```java
Student student1 = new Student("Aman", 20, "Java");
```

Here:

- `new Student(...)` dynamically creates a `Student` object.
- The object is stored in heap memory.
- The reference variable `student1` is stored in stack memory.
- `student1` points to the object in heap memory.

Visual:

```text
Before object creation:

Stack Memory                  Heap Memory
------------                  -----------
empty                         empty


After this line:
Student student1 = new Student("Aman", 20, "Java");

Stack Memory                  Heap Memory
------------                  -----------------------------
student1  ----------------->  Student object
                              name = "Aman"
                              age = 20
                              course = "Java"
```

## Why Dynamic Memory Allocation is Needed

Dynamic memory allocation is useful because we may not know how many objects are needed before the program runs.

Example:

```java
Student student1 = new Student("Aman", 20, "Java");
Student student2 = new Student("Sara", 22, "Data Structures");
Student student3 = new Student("Ravi", 21, "Python");
```

Each `new Student(...)` creates a separate object in heap memory.

Visual:

```text
Stack Memory                  Heap Memory
------------                  -----------------------------
student1  ----------------->  Student object
                              name = "Aman"
                              age = 20
                              course = "Java"

student2  ----------------->  Student object
                              name = "Sara"
                              age = 22
                              course = "Data Structures"

student3  ----------------->  Student object
                              name = "Ravi"
                              age = 21
                              course = "Python"
```

Each object has its own memory and its own values.

## What Happens When an Object is Not Used?

If no reference variable points to an object, that object cannot be used anymore.

Example:

```java
student1 = null;
```

Now `student1` no longer points to the object.

Visual:

```text
Stack Memory                  Heap Memory
------------                  -----------------------------
student1 = null               Student object
                              name = "Aman"
                              age = 20
                              course = "Java"
                              no active reference
```

This unused object becomes eligible for garbage collection. The garbage collector can remove it from heap memory automatically.

## Key Points

- A class defines properties and methods.
- An object is created from a class.
- Each object can have its own data.
- Methods define what an object can do.
- Constructors are used to initialize objects.
- Local reference variables are stored in stack memory.
- Objects created with `new` are stored in heap memory.
- Dynamic memory allocation happens at runtime.
- Java dynamically allocates memory for objects in heap memory.

## Example Concept

In the Java example file, we create a `Student` class with:

- fields: `name`, `age`, and `course`
- constructor: to set initial values
- method: `displayDetails()` to print student information

Then, in the `main` method, we create multiple student objects and call their methods.

## Constructor in Java

A constructor is a special method used to initialize an object when it is created. It has the same name as the class and does not have a return type.

### Why do we use constructors?

Constructors are used to:

- assign initial values to object fields
- set up object state when the object is created
- ensure that an object is ready to use immediately

### How does a constructor work?

When you create an object using `new`, Java automatically calls the constructor.

Example:

```java
class Student {
    String name;
    int age;

    // Constructor
    Student(String n, int a) {
        name = n;
        age = a;
    }

    void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Aman", 20);
        s1.displayDetails();
    }
}
```

### Key points about constructors

- A constructor has the same name as the class.
- It is called automatically when an object is created.
- It helps initialize the object.
- If you do not write a constructor, Java provides a default constructor.

### How constructor works internally

Internally, when you write `new Student("Aman", 20)`, Java does the following:

1. Allocates memory for the new object in heap memory.
2. Initializes the object fields with default values.
3. Calls the constructor to set the values you provide.
4. Returns the reference of the new object so it can be stored in a variable.

So, the constructor is not called manually by you; it is invoked automatically by the JVM during object creation.

### Default constructor in Java

A default constructor is a constructor that Java provides automatically when you do not define any constructor in your class.

It has:

- no parameters
- no body written by you
- the same name as the class

Example:

```java
class Student {
    String name;
    int age;
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        System.out.println(s1.name); // null
        System.out.println(s1.age);  // 0
    }
}
```

### What happens with a default constructor?

When you create an object using `new Student()`, Java calls the default constructor automatically. Since no values are assigned in the constructor, the fields get their default values:

- `0` for numeric types
- `false` for boolean
- `null` for reference types like strings

### Use case example

Constructors are very useful when you want every object to start with some default or given values. For example, a `Student` object can be created with a name and age as soon as it is created.

### Example with a greeting method

```java
class Person {
    String name;
    int age;

    Person() {
        this.name = "JohnDoe";
        this.age = 26;
    }

    void greeting() {
        System.out.println("Hello, my name is " + name);
    }
}

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.greeting();
    }
}
```

### Explanation

In this example:

- the constructor `Person()` sets the values for `name` and `age`
- `this.name = "JohnDoe";` assigns the name to the current object
- `this.age = 26;` assigns the age to the current object
- the `greeting()` method prints the name when it is called

When the program runs, it will print:

```text
Hello, my name is JohnDoe
```

## this Keyword in Java

The `this` keyword in Java refers to the current object. It is used to access the current object's variables and methods.

### Why is `this` used?

`this` is used when there is a naming conflict between:

- instance variables and local variables
- parameters and fields

It helps Java clearly understand that you want to use the object's field, not the local variable.

### How does `this` work?

When an object calls a method, Java internally passes the reference of that object to the method. The `this` keyword holds that reference.

So, `this.name` means: use the `name` variable of the current object.

### Example

```java
class Student {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void displayDetails() {
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Aman", 20);
        s1.displayDetails();
    }
}
```

### Internally, how does `this` work?

Internally, `this` is a reference variable that points to the current object in memory. It is automatically available inside instance methods and constructors.

It helps the JVM identify which object's data should be used when the same method is called by different objects.

### Key points

- `this` refers to the current object.
- It is used to access instance variables and methods.
- It removes confusion when parameter names and field names are the same.
- It is automatically available inside non-static methods and constructors.

## Constructor Overloading in Java

Constructor overloading means defining multiple constructors in the same class with different parameter lists. Each constructor must have a different number or type of parameters.

### Why use constructor overloading?

Constructor overloading allows you to create objects in different ways. Depending on what data is available, you can call a different constructor.

### Example

```java
class Student {
    String name;
    int age;
    String course;

    // Constructor with all parameters
    Student(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }

    // Constructor with name and age only
    Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.course = "Undecided";
    }

    // Constructor with no parameters
    Student() {
        this.name = "Unknown";
        this.age = 0;
        this.course = "Undecided";
    }

    void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Course: " + course);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Aman", 20, "Java");
        Student s2 = new Student("Sara", 22);
        Student s3 = new Student();

        s1.displayDetails();
        s2.displayDetails();
        s3.displayDetails();
    }
}
```

Output:

```text
Name: Aman
Age: 20
Course: Java
Name: Sara
Age: 22
Course: Undecided
Name: Unknown
Age: 0
Course: Undecided
```

### Key points about constructor overloading

- Multiple constructors can exist in the same class as long as they have different parameter lists.
- The correct constructor is called based on the arguments passed during object creation.
- Constructor overloading provides flexibility in how objects are initialized.
- It follows the same rules as method overloading but applies to constructors.

### How Java decides which constructor to call

Java matches the arguments you pass in `new ClassName(...)` against the constructor parameter lists. If you call `new Student("Aman", 20, "Java")`, Java uses the constructor with three parameters. If you call `new Student()`, Java uses the no-argument constructor. The number and type of arguments determine which constructor is invoked.

## Calling a Constructor from Another Constructor Using `this()`

In Java, one constructor can call another constructor of the same class using the `this()` keyword. This is called constructor chaining.

### Why use constructor chaining?

- Avoids code duplication — common initialization logic stays in one constructor.
- Makes code cleaner and easier to maintain.
- Ensures all constructors share the same base initialization.

### Rules of constructor chaining

- `this()` must be the **first statement** in the calling constructor.
- It can only call another constructor in the same class (use `super()` to call a parent class constructor).
- The chain continues until a constructor without a `this()` call is reached.

### Example

```java
class Student {
    String name;
    int age;
    String course;

    // Main constructor — does the actual work
    Student(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }

    // Calls the 3-param constructor with a default course
    Student(String name, int age) {
        this(name, age, "Undecided");
    }

    // Calls the 3-param constructor with default values for everything
    Student() {
        this("Unknown", 0, "Undecided");
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Aman", 20, "Java");
        Student s2 = new Student("Sara", 22);
        Student s3 = new Student();
    }
}
```

### What happens when `new Student("Sara", 22)` runs?

1. The 2-parameter constructor `Student(String name, int age)` is called.
2. It immediately executes `this(name, age, "Undecided")`.
3. This calls the 3-parameter constructor, which sets all three fields.
4. Control returns to the 2-parameter constructor, which finishes.

The result is the same as calling the 3-parameter constructor directly, but the caller only needed to provide a name and age.

### Key points

- `this()` must be the **first line** inside a constructor.
- Constructor chaining reduces code duplication — initialization logic lives in one place.
- Multiple constructors can chain in a chain: no-arg → 2-param → 3-param (or any order).
- Always ends at a constructor that does not use `this()`.

## How `new` Keyword Works in Memory Allocation

The `new` keyword in Java is responsible for dynamic memory allocation. When you write:

```java
Student s1 = new Student("Aman", 20);
```

Java performs the following steps internally:

### Step-by-step breakdown of `new`

1. **Class loading check** — The JVM checks if the `Student` class is already loaded. If not, the class loader loads it into the method area (a part of heap memory where class metadata is stored).

2. **Memory allocation in heap** — The JVM allocates a block of memory in the heap large enough to hold all instance variables of the class. The size is determined by the types and number of fields.

3. **Default initialization** — All instance variables are set to their default values:
   - numeric types → `0`
   - `boolean` → `false`
   - object references (like `String`) → `null`

4. **Constructor call** — The constructor you invoked is called. If you used constructor chaining (`this()`), those calls happen first. Inside the constructor body, your assignment statements (`this.name = name`) overwrite the default values with the values you provided.

5. **Reference returned** — The `new` expression returns the memory address (reference) of the newly created object. This reference is stored in the variable `s1` on the stack.

### Visual

```
Stack Memory         Heap Memory
-----------         ------------
s1 (ref)  ------->  Student object @ 0x4F2A
                     name = "Aman"      (String ref → string pool)
                     age  = 20
                     
                     Class metadata (method area):
                     Student.class
                       - fields: name, age
                       - constructor: Student(String, int)
                       - method: displayDetails()
```

### What the `new` keyword is NOT

- `new` does **not** allocate stack memory. Only the reference goes to the stack.
- `new` does **not** call the garbage collector. Object cleanup happens later when no references point to it.
- `new` does **not** create the class. It only creates an instance (object) of an already loaded class.

### Memory overhead

Every Java object has a header (object header) stored before its fields. This header contains:
- **Mark word** — stores identity hash code, GC information, lock state (typically 8 bytes on 64-bit JVM)
- **Klass pointer** — points to the class metadata (typically 4 or 8 bytes, depending on compressed oops)
- **Padding** — objects are aligned to 8-byte boundaries

For example, an object with only an `int` field may use 16 bytes: 8 (mark) + 4 (klass with compressed oops) + 4 (int) = 16 bytes (already aligned).

### Array allocation with `new`

Arrays are also allocated with `new`:

```java
int[] numbers = new int[5];
```

This creates an array object in heap with:
- Object header (mark + klass)
- Length field (4 bytes)
- Elements initialized to default values (`0` for int)
- Returns a reference stored in `numbers` on the stack

## Wrapper Classes in Java

### What are wrapper classes?

Wrapper classes are Java classes that **wrap** primitive data types inside an object. Each primitive type has a corresponding wrapper class:

| Primitive | Wrapper Class |
|-----------|---------------|
| `byte`    | `Byte`        |
| `short`   | `Short`       |
| `int`     | `Integer`     |
| `long`    | `Long`        |
| `float`   | `Float`       |
| `double`  | `Double`      |
| `char`    | `Character`   |
| `boolean` | `Boolean`     |

### Why do we need wrapper classes?

Java is an object-oriented language, but primitives are not objects. Wrapper classes bridge this gap. They are needed when:

1. **Collections** — Java collections like `ArrayList`, `HashMap`, `HashSet` can only store objects, not primitives:
   ```java
   ArrayList<Integer> list = new ArrayList<>();  // ✅ works
   // ArrayList<int> list = new ArrayList<>();   // ❌ compile error
   ```

2. **Generics** — Type parameters must be reference types:
   ```java
   Box<Integer> box = new Box<>();  // ✅ works
   // Box<int> box = new Box<>();   // ❌ compile error
   ```

3. **Utility methods** — Wrapper classes provide useful methods:
   ```java
   int x = Integer.parseInt("123");      // String → int
   String s = Integer.toHexString(255);  // "ff"
   int y = Integer.max(10, 20);          // 20
   ```

4. **Null values** — Primitives cannot be `null`, but wrapper objects can:
   ```java
   Integer age = null;     // ✅ valid — object reference can be null
   // int age = null;      // ❌ compile error
   ```

5. **Serialization** — Only objects can be serialized. Wrapper classes allow primitive values to be serialized.

### Autoboxing and Unboxing

**Autoboxing** is the automatic conversion of a primitive value to its wrapper object:

```java
Integer a = 10;  // Java auto-converts int 10 → Integer.valueOf(10)
```

**Unboxing** is the automatic conversion of a wrapper object to its primitive value:

```java
int b = a;  // Java auto-converts Integer → a.intValue()
```

This works in expressions, method arguments, and comparisons:

```java
Integer x = 100;
Integer y = 100;
System.out.println(x + y);       // 200 — unboxing happens for arithmetic
System.out.println(x == y);      // true — values are in cached range (-128 to 127)
System.out.println(x.equals(y)); // true — always safe for value comparison

Integer p = 200;
Integer q = 200;
System.out.println(p == q);      // false — different objects (outside cache range)
System.out.println(p.equals(q)); // true — values are equal
```

> ⚠️ **Important:** `==` on wrapper objects compares **references**, not values. The JVM caches `Integer` objects for the range `-128` to `127` (and similar for other types). Outside this range, new objects are created, so `==` may give unexpected results. Always use `.equals()` for value comparison.

### Wrapper class memory allocation

When you create a wrapper object explicitly with `new`, it always allocates a new object in heap:

```java
Integer a = new Integer(10);  // always creates a new object on heap
```

But with autoboxing or `valueOf()`, the JVM may reuse cached objects:

```java
Integer a = 10;               // Integer.valueOf(10) → returns cached object
Integer b = 10;               // same cached object reused
System.out.println(a == b);   // true

Integer c = 200;              // Integer.valueOf(200) → creates new object
Integer d = 200;              // creates another new object
System.out.println(c == d);   // false
```

### Cache ranges for wrapper classes

| Wrapper  | Cache Range              | Method            |
|----------|--------------------------|-------------------|
| `Integer`| -128 to 127              | `valueOf(int)`    |
| `Long`   | -128 to 127              | `valueOf(long)`   |
| `Short`  | -128 to 127              | `valueOf(short)`  |
| `Byte`   | -128 to 127 (all values) | `valueOf(byte)`   |
| `Character`| 0 to 127               | `valueOf(char)`   |
| `Boolean`| `true` / `false`         | `valueOf(boolean)`|
| `Float`  | no cache                 | `valueOf(float)`  |
| `Double` | no cache                 | `valueOf(double)` |

`Float` and `Double` do not cache because there are too many possible values.

### Converting between primitive, wrapper, and String

```java
// Primitive → Wrapper
Integer obj = Integer.valueOf(42);
Integer obj2 = 42;                    // autoboxing

// Wrapper → Primitive
int val = obj.intValue();
int val2 = obj;                       // unboxing

// String → Primitive
int num = Integer.parseInt("123");

// String → Wrapper
Integer numObj = Integer.valueOf("123");

// Primitive → String
String str = Integer.toString(123);
String str2 = String.valueOf(123);
String str3 = 123 + "";               // concatenation
```

### When autoboxing/unboxing happens

| Scenario | Example |
|----------|---------|
| Assignment | `Integer x = 10;` |
| Method argument | `list.add(42);` |
| Return value | `return count;` (if return type is `Integer`) |
| Arithmetic | `Integer sum = a + b;` |
| Comparison | `if (a > 5)` where `a` is `Integer` |
| Ternary | `Integer x = condition ? 1 : 2;` |

### Performance note

Autoboxing/unboxing has a small performance cost because it involves method calls (`valueOf()`, `intValue()`, etc.) and potentially creates new objects. In performance-critical code (e.g., tight loops), prefer primitives:

```java
// ✅ Fast — uses primitives
int sum = 0;
for (int i = 0; i < 1000000; i++) {
    sum += i;
}

// ❌ Slow — creates many Integer objects
Integer sum = 0;
for (Integer i = 0; i < 1000000; i++) {
    sum += i;  // unbox + add + autobox each iteration
}
```

## Pass by Value vs Pass by Reference in Java

### Does Java use pass by value or pass by reference?

**Java is strictly pass by value.** There is no pass by reference in Java.

This is a common interview question and source of confusion. The key insight is:

- When you pass a **primitive**, Java copies the value.
- When you pass an **object reference**, Java copies the reference (the memory address), not the object itself.

### Pass by value with primitives

```java
public static void main(String[] args) {
    int x = 10;
    change(x);
    System.out.println(x);  // 10 — unchanged
}

static void change(int a) {
    a = 20;  // only the local copy is modified
}
```

Memory diagram:

```text
main() stack frame:    x = 10
change() stack frame:  a = 10  (copy of x)
                       a = 20  (local copy changed, x unaffected)
```

The parameter `a` is a completely separate variable in the stack. Changing it has no effect on `x`.

### Pass by value with object references

```java
public static void main(String[] args) {
    Student s = new Student("Aman", 20);
    changeName(s);
    System.out.println(s.name);  // "Rahul" — object changed!
}

static void changeName(Student stu) {
    stu.name = "Rahul";  // modifies the same object both references point to
}
```

Memory diagram:

```text
Before changeName():
Stack                          Heap
--------                       ----------
s (ref @0x4F2A)  ----------->  Student object @0x4F2A
                                name = "Aman"

changeName() stack frame:
stu (ref @0x4F2A) ----------->  (same object)
    
After stu.name = "Rahul":
                                name = "Rahul"  (mutated)
```

Here, `stu` is a **copy** of the reference `s`. Both hold the same memory address (`0x4F2A`), so both point to the same object. Modifying the object through `stu` is visible through `s`.

### Reassigning the reference inside a method

This is the classic trick question:

```java
public static void main(String[] args) {
    Student s = new Student("Aman", 20);
    changeStudent(s);
    System.out.println(s.name);  // "Aman" — NOT changed
}

static void changeStudent(Student stu) {
    stu = new Student("Rahul", 22);  // only the local copy of the reference is reassigned
}
```

Memory diagram:

```text
Before changeStudent():
Stack                          Heap
--------                       ----------
s (ref @0x4F2A)  ----------->  Student A @0x4F2A ("Aman")

changeStudent() stack frame:
stu (ref @0x4F2A) -----------> Student A @0x4F2A

After stu = new Student("Rahul", 22):
s (ref @0x4F2A)  ----------->  Student A @0x4F2A ("Aman")

stu (ref @0x8B3C) ----------->  Student B @0x8B3C ("Rahul")
```

The reassignment only affects the local copy `stu`. The original reference `s` still points to `Student A`. This proves Java is pass by value — the reference itself is copied, and reassigning the copy does not affect the original.

### Summary table

| Aspect | Primitive | Object Reference |
|--------|-----------|------------------|
| What is copied? | The actual value | The memory address (reference) |
| Can the method change the value seen by caller? | No | Yes (by mutating the object) |
| Can the method make the caller's variable point to a different object? | N/A | No |
| Memory location of copy | New slot on stack | New slot on stack (holds same address) |

### What pass by reference would look like

If Java had pass by reference, you could swap objects in a method:

```java
// This does NOT work in Java — just for illustration
static void swap(Student a, Student b) {   // Java: copies references
    Student temp = a;
    a = b;
    b = temp;   // only local copies are swapped
}

// What pass by reference would allow:
// after swap(s1, s2), s1 would point to s2's object and vice versa
// Java does NOT support this
```

### Real pass by reference languages

Languages like C++ (with `&`), C# (with `ref`), and PHP (with `&`) have true pass by reference, where the method parameter is an alias of the caller's variable. Changing the parameter changes the caller's variable directly.

### Key takeaway

- **Java is always pass by value.**
- For primitives, the value itself is copied → changes inside the method are not reflected outside.
- For objects, the reference value (memory address) is copied → both the original and the copy point to the same object, so mutations are visible, but reassignment of the reference is not.

## The `final` Keyword in Java

The `final` keyword is a non-access modifier used to restrict modification. What it restricts depends on what it is applied to:

### 1. `final` variables — constant values

A `final` variable can be assigned **only once**. After that, its value cannot change.

```java
final int MAX_SPEED = 120;
// MAX_SPEED = 150;  // ❌ compilation error — cannot reassign
```

**Purpose:** Define constants and ensure they are not accidentally modified.

```java
class Circle {
    final double PI = 3.14159;     // constant — same for all objects

    double area(double radius) {
        return PI * radius * radius;
    }
}
```

**Blank final variable:** A `final` variable that is not initialized at declaration must be initialized exactly once in every constructor:

```java
class Student {
    final int rollNo;  // blank final

    Student(int rollNo) {
        this.rollNo = rollNo;  // ✅ must be assigned here
    }

    // Student() { }  // ❌ compilation error — rollNo not initialized
}
```

**Purpose:** Each object gets a fixed value that cannot change, like an ID or serial number.

### 2. `final` methods — cannot be overridden

A `final` method in a parent class cannot be overridden by a child class.

```java
class Parent {
    final void show() {
        System.out.println("This cannot be overridden");
    }
}

class Child extends Parent {
    // void show() { }  // ❌ compilation error — cannot override final method
}
```

**Purpose:** Prevent subclasses from changing critical behavior. Used for:
- Template methods that define the skeleton of an algorithm
- Security-sensitive methods (e.g., in `String` class)
- Methods that should maintain consistent behavior across all subclasses

### 3. `final` classes — cannot be inherited

A `final` class cannot be extended (subclassed).

```java
final class MathUtils {
    static int square(int x) {
        return x * x;
    }
}

// class AdvancedMath extends MathUtils { }  // ❌ compilation error
```

**Purpose:** Prevent inheritance. Used for:
- Immutable classes like `String`, `Integer`, `Boolean`
- Utility classes where inheritance makes no sense
- Security — preventing malicious subclassing

### 4. `final` parameters — cannot be reassigned

A `final` parameter cannot be reassigned inside the method body.

```java
void printValue(final int x) {
    // x = 10;  // ❌ compilation error
    System.out.println(x);
}
```

**Purpose:** Signal that the parameter value should not change. Helps readability and prevents bugs.

### `final` and memory — primitive vs object

`final` behaves differently for primitives and objects:

```java
final int x = 10;
// x = 20;  // ❌ cannot change primitive value

final Student s = new Student("Aman", 20);
// s = new Student("Rahul", 22);  // ❌ cannot change reference (address)
s.name = "Rahul";                  // ✅ object's internal state IS mutable
```

**Memory diagram — primitive:**

```text
Stack                    Heap
--------                 ----------
x = 10 (fixed value)
   cannot change
```

`final` on a primitive locks the **value itself**. The stack slot holding `10` is frozen.

**Memory diagram — object:**

```text
Stack                    Heap
--------                 -----------------------------
s (final ref @0x4F2A)    Student object @0x4F2A
   cannot change  ------->  name: "Aman"  → "Rahul"  ✅
   the address               age: 20
```

`final` on an object reference locks **the address in the stack slot** (`0x4F2A`). The object at that address is **not protected** — its fields can still be modified.

### The golden rule: you CAN change, you CANNOT reassign

This is the most important thing to remember about `final` with objects:

```java
final Student s = new Student("Aman", 20);

s.name = "Rahul";        // ✅ CAN change — modifying object's internal state
s.age = 22;              // ✅ CAN change — modifying object's internal state
s.displayDetails();      // ✅ CAN change — calling methods on the object

s = new Student("X", 0); // ❌ CANNOT reassign — changing which object s points to
s = null;                // ❌ CANNOT reassign — even null is not allowed
```

The variable `s` is permanently bound to the object it was assigned. That specific object's insides are still fully modifiable.

### Key difference table

| Aspect | `final` primitive | `final` object reference |
|--------|------------------|--------------------------|
| What is frozen? | The actual value | The memory address (pointer) |
| Can you reassign? | ❌ `x = 20` fails | ❌ `s = new Student()` fails |
| Can you modify internal state? | N/A (no internal state) | ✅ `s.name = "Rahul"` works |
| Example | `final int MAX = 100;` | `final List<Integer> list = new ArrayList<>();` — you can `list.add(5)` but not `list = new ArrayList<>()` |
| Common term | Constant value | Immutable reference (mutable object)

### `final` with static — compile-time constants

When `static` and `final` are combined, the variable becomes a compile-time constant (if initialized with a literal):

```java
class Config {
    static final int MAX_USERS = 100;     // compile-time constant
    static final String APP_NAME = "MyApp"; // compile-time constant
}
```

Such constants are stored in the **constant pool** (not as a regular static field) and are inlined by the compiler — wherever `Config.MAX_USERS` is used, the compiler replaces it directly with `100`.

### Common use cases

| Use Case | Example |
|----------|---------|
| Constants | `public static final double PI = 3.14159;` |
| Immutable IDs | `final int id;` assigned in constructor |
| Method security | `final void authenticate() { ... }` |
| Immutable class | `public final class String { ... }` |
| Anonymous classes | Variable used in anonymous class must be `final` or effectively final |
| Local variable capture in lambdas | Variable referenced from lambda must be `final` or effectively final |

### Effectively final (Java 8+)

Even without the `final` keyword, a variable is **effectively final** if it is never reassigned after initialization. Such variables can be used inside anonymous classes and lambdas:

```java
int x = 10;
// x = 20;  // if this line is uncommented, x is no longer effectively final
Runnable r = () -> System.out.println(x);  // ✅ works — x is effectively final
```

## Garbage Collection in Java

### What is garbage collection?

Garbage collection is the automatic process of identifying and removing objects from heap memory that are no longer reachable by any live thread. This frees memory for new objects without the programmer needing to manually deallocate it.

### When is an object eligible for garbage collection?

An object becomes eligible (unreachable) when no active thread has a reference to it:

```java
Student s = new Student("Aman", 20);
s = null;                    // the Student object is now unreachable

// or when the reference goes out of scope
void method() {
    Student s2 = new Student("Sara", 22);  // created here
}                                          // goes out of scope here → eligible
```

### How garbage collection works — the process

Java uses various GC algorithms, but the general process follows these steps:

**1. Marking phase**

The GC identifies which objects are still alive by traversing from **GC roots** and marking every object reachable from them.

GC roots include:
- Local variables on stack frames
- Static fields
- Active threads
- JNI (native) references

```
Heap before marking:
┌─────────────────────────────────────┐
│  [A] ←── [B]    [D] ←── [E]        │
│   ↑                ↑                │
│  (stack ref)     (stack ref)        │
│                                     │
│       [C]         [F]               │
│      (unused)    (unused)           │
└─────────────────────────────────────┘

After marking (✓ = alive, ✗ = garbage):
┌─────────────────────────────────────┐
│  [A]✓ ←── [B]✓    [D]✓ ←── [E]✓   │
│   ↑                  ↑              │
│  (root)            (root)           │
│                                     │
│       [C]✗          [F]✗           │
└─────────────────────────────────────┘
```

**2. Sweeping phase**

The GC sweeps through the heap and deallocates memory occupied by unmarked (unreachable) objects, recording the freed space in free lists.

**3. Compacting phase (optional, done by some collectors)**

The GC moves all live objects together to eliminate fragmentation:

```
Before compacting:
┌──────┬──────┬──────────┬──────┬─────────┐
│ [A]  │ free │   [B]    │ free │   [E]   │
└──────┴──────┴──────────┴──────┴─────────┘

After compacting:
┌─────────────────────┬──────────────────┐
│       [A][B][E]     │   free (contiguous)│
└─────────────────────┴──────────────────┘
```

### Generational garbage collection

Most modern JVMs use a **generational** approach based on the observation that most objects die young.

```
Heap layout (generational):
┌────────────┬──────────────┬──────────────────────┐
│ Young Gen  │  Survivor    │     Old Gen          │
│ (Eden)     │  S0 | S1     │   (Tenured)          │
├────────────┴──────────────┴──────────────────────┤
│           Metaspace (class metadata)              │
└──────────────────────────────────────────────────┘
```

**Young Generation (Eden + Survivor spaces):**
- New objects are allocated in Eden.
- When Eden fills, a **minor GC** runs.
- Live objects move to Survivor space S0.
- On the next minor GC, live objects from Eden + S0 move to S1 (S0 and S1 swap).
- Objects that survive many minor GC cycles are **promoted** to Old Gen.

**Old Generation (Tenured):**
- Stores long-lived objects.
- A **major GC** (full GC) runs when Old Gen fills.
- Major GC is slower because it processes more memory.

### Types of GC implementations in JVM

| Collector | Description | Best for |
|-----------|-------------|----------|
| Serial GC | Single-threaded, pauses all threads | Small heaps, single-core |
| Parallel GC | Multi-threaded marking/sweeping | High throughput, batch jobs |
| G1 GC | Divides heap into regions, prioritizes regions with most garbage | Large heaps, low latency |
| ZGC | Ultra-low latency (<10ms pauses) | Very large heaps, real-time |

### Requesting GC (not guaranteed)

You can **suggest** garbage collection, but the JVM decides when to actually run it:

```java
System.gc();           // suggests GC — not guaranteed to run
Runtime.getRuntime().gc();  // equivalent
```

### Key points

- GC runs automatically — you do not need to free memory manually like in C/C++.
- An object is eligible for GC when it is **unreachable** from GC roots.
- You cannot force GC — `System.gc()` is only a suggestion.
- GC pauses application threads during collection (stop-the-world pauses).
- Generational collection minimizes pause times.

---

## Finalizers (`finalize()` Method)

### What is a finalizer?

The `finalize()` method is called by the garbage collector **before** reclaiming an object's memory. It is meant for cleanup operations like closing files, releasing native resources, or releasing network connections.

```java
class Resource {
    private java.io.FileInputStream stream;

    Resource(String path) throws Exception {
        stream = new java.io.FileInputStream(path);
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (stream != null) {
                stream.close();
                System.out.println("Stream closed in finalize()");
            }
        } finally {
            super.finalize();  // always call parent finalizer
        }
    }
}
```

### The finalization process

```
Object becomes unreachable
         │
         ▼
GC detects unreachable object
         │
         ▼
Does the class have a finalize() method? ───No──→ Memory reclaimed
         │                                            immediately
        Yes
         │
         ▼
Object added to finalization queue (F-Queue)
         │
         ▼
Finalizer thread runs finalize() (may resurrect the object!)
         │
         ▼
         │
    ┌────┴────┐
    ▼         ▼
Resurrected    Not resurrected
(no longer      │
 eligible        ▼
 for GC)      Next GC cycle reclaims memory
```

### Object resurrection

Inside `finalize()`, an object can make itself reachable again:

```java
class Resurrectable {
    static Resurrectable savedRef;

    @Override
    protected void finalize() {
        savedRef = this;  // becomes reachable again — GC won't collect it
        System.out.println("Resurrected!");
    }
}
```

The object survives that GC cycle. `finalize()` will not be called again on the same object (it runs at most once).

### Problems with finalizers

| Problem | Explanation |
|---------|-------------|
| **Unpredictable timing** | You don't know when or if `finalize()` will run — GC timing is JVM-dependent |
| **Performance cost** | Objects with `finalize()` take longer to collect (they need queue processing) |
| **Thread issues** | Finalizer thread has low priority — may not run before program exits |
| **Exception swallowing** | Uncaught exceptions in `finalize()` are ignored — stack trace is lost |
| **Resurrection** | Object can accidentally become reachable again, delaying collection |

### The order of execution

```java
class Parent {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Parent finalize");
        super.finalize();
    }
}

class Child extends Parent {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Child finalize");
        super.finalize();  // must call parent's finalizer
    }
}
```

Output when GC runs:
```
Child finalize
Parent finalize
```

### `finalize()` is deprecated (Java 9+)

Starting from Java 9, `finalize()` is **deprecated**. It is replaced by:

| Alternative | Description |
|-------------|-------------|
| **try-with-resources** | Classes implement `AutoCloseable` — resources close automatically |
| **Cleaner API** (`java.lang.ref.Cleaner`) | Register cleanup actions that run when an object becomes phantom-reachable |

### Preferred cleanup — try-with-resources

```java
// ✅ Modern approach — no finalizer needed
class Resource implements AutoCloseable {
    private java.io.FileInputStream stream;

    public Resource(String path) throws Exception {
        stream = new java.io.FileInputStream(path);
    }

    public void close() {
        if (stream != null) {
            try { stream.close(); } catch (Exception e) { }
        }
    }
}

// Usage — automatically calls close() even if an exception occurs
try (Resource r = new Resource("file.txt")) {
    // use resource
} // close() called here automatically
```

### Summary

| | Garbage Collection | Finalizer |
|--|-------------------|-----------|
| **Purpose** | Automatic memory management | Cleanup before object destruction |
| **Who runs it** | JVM (GC thread) | Finalizer thread |
| **When** | When heap is full or GC cycle triggered | Before GC reclaims the object |
| **Guaranteed to run?** | Eventually (when needed) | No — may never run |
| **Modern alternative** | Use GC as-is | `AutoCloseable` / `Cleaner` API |

## How to Run

Compile:

```bash
javac ClassesAndObjectsDemo.java
```

Run:

```bash
java ClassesAndObjectsDemo
```
