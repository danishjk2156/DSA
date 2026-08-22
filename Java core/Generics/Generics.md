# Java Generics

> A comprehensive guide to Java Generics — type parameters, wildcards, bounds, and the PECS principle.

---

## Table of Contents

1. [What is Generics?](#what-is-generics)
2. [Type Parameters `<T>`](#1-type-parameters-t)
3. [Wildcards `?`](#2-wildcards-)
4. [`? extends` — Upper Bounded Wildcard](#3--extends--upper-bounded-wildcard)
5. [`? super` — Lower Bounded Wildcard](#4--super--lower-bounded-wildcard)
6. [PECS — Producer Extends, Consumer Super](#5-pecs--producer-extends-consumer-super)

---

## What is Generics?

Generics let you write classes, interfaces, and methods that work with **any type** while providing **compile-time type safety**. Without generics, you'd use `Object` and cast everywhere — leading to runtime `ClassCastException`s. With generics, the compiler catches type mismatches before your code even runs.

```java
// Without generics — unsafe
List list = new ArrayList();
list.add("hello");
String s = (String) list.get(0);   // manual cast required
list.add(42);                       // compiles but will blow up later

// With generics — safe
List<String> list = new ArrayList<>();
list.add("hello");
String s = list.get(0);            // no cast needed
list.add(42);                       // compiler error — caught early!
```

---

## 1. Type Parameters `<T>`

### A. What is it?

`<T>` is a **type parameter** — a placeholder for a type that gets filled in when you use the class or method. It works like a variable, but for types instead of values. You can name them anything (`T`, `U`, `V`, `E`, `K`, `V`, `R`), but the convention is:

| Letter | Meaning |
|---|---|
| `T` | Type |
| `E` | Element |
| `K` | Key |
| `V` | Value |
| `R` | Return |

### B. Why do we need it?

Without `<T>`, you'd have to write duplicate code for every type — `IntegerList`, `StringList`, `DoubleList`, etc. Or you'd use `Object` everywhere and lose type safety. Generics let you write **one class that works for all types** while the compiler ensures you never mix up types.

### C. Basic Syntax

```java
// Generic class
public class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

// Usage
Box<String> stringBox = new Box<>();
stringBox.set("Hello");
String s = stringBox.get();       // no cast needed

Box<Integer> intBox = new Box<>();
intBox.set(42);
int n = intBox.get();             // auto-unboxing

// Generic method
public static <T> T getFirst(T[] array) {
    return array[0];
}

// Usage — type is inferred
String first = getFirst(new String[]{"a", "b", "c"});
Integer num = getFirst(new Integer[]{1, 2, 3});
```

### D. Small Example

```java
// A generic Pair class that holds two values of any type
public class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() { return first; }
    public B getSecond() { return second; }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    // Generic method to swap a Pair
    public static <A, B> Pair<B, A> swap(Pair<A, B> pair) {
        return new Pair<>(pair.getSecond(), pair.getFirst());
    }

    public static void main(String[] args) {
        Pair<String, Integer> student = new Pair<>("Alice", 95);
        System.out.println("Original: " + student);

        Pair<Integer, String> swapped = swap(student);
        System.out.println("Swapped:  " + swapped);
        // Output: (95, Alice)
    }
}
```

### E. Internals / How It Works

- Generics are implemented via **type erasure** — at runtime, all generic type information is removed.
- `List<String>` and `List<Integer>` are both just `List` at runtime.
- The compiler inserts **implicit casts** where needed, but they happen behind the scenes.
- You **cannot** do `new T()` or `new T[]` because the type is erased at runtime.
- You **cannot** use primitives as type arguments (`List<int>` won't compile). Use wrapper classes (`List<Integer>`).

### F. Common Mistakes / Interview Questions

| Mistake / Question | Answer |
|---|---|
| `List<int>` | **Won't compile.** Use `List<Integer>`. Generics only work with reference types. |
| Can I check `T instanceof String`? | No, because `T` is erased at runtime. Use a `Class<T>` parameter instead. |
| Can I create `new T()`? | No. Type erasure means the runtime doesn't know what `T` is. Pass a `Supplier<T>` or a `Class<T>` and use reflection. |
| What is type erasure? | The compiler removes all generic type info at compile time. `List<String>` becomes `List` at runtime. This exists for backward compatibility with pre-generics Java. |
| `Generic<T>` vs `Generic<?>` | `<T>` declares a type parameter you can name and use. `<?>` is an anonymous wildcard meaning "some unknown type." |

---

## 2. Wildcards `?`

### A. What is it?

The wildcard `?` represents an **unknown type**. Unlike `<T>` which declares a type you can name and reuse, `?` is anonymous — you're saying "I don't care what type this is, I just need to accept *something*." It's used in method parameters when you want flexibility in what types a method accepts.

### B. Why do we need it?

Without wildcards, Java generics have strict invariant rules. A `List<Dog>` is **not** a `List<Animal>`, even though `Dog` extends `Animal`. This is because if it were, you could add a `Cat` to a `List<Dog>` through the `List<Animal>` reference. Wildcards solve this by allowing you to express "a list of **some** Animal subclass" without committing to which one.

### C. Basic Syntax

```java
// Accept any type
public static void printAll(List<?> list) {
    for (Object item : list) {
        System.out.println(item);
    }
}

// Works with any list type
printAll(List.of(1, 2, 3));            // List<Integer>
printAll(List.of("a", "b"));           // List<String>
printAll(List.of(1.0, 2.0));           // List<Double>
```

**Key rule:** You can **read** from `<?>` but you can't **write** to it (except `null`).

```java
public static void readAll(List<?> list) {
    Object item = list.get(0);   // ✅ OK — returns Object
    list.add("hello");            // ❌ WON'T COMPILE — unknown type
    list.add(null);               // ✅ OK — null is always safe
}
```

### D. Small Example

```java
import java.util.ArrayList;
import java.util.List;

public class WildcardDemo {

    // Accepts ANY list of any type
    public static void printSize(List<?> list) {
        System.out.println("List of " + list.getClass().getSimpleName()
                         + " has " + list.size() + " elements");
    }

    // Accepts any list, sums numeric values
    // (won't work with ? because you can't cast unknown types)
    // This shows the LIMITATION of raw wildcards

    // Compare two lists of unknown types
    public static void describeList(List<?> list) {
        if (list.isEmpty()) {
            System.out.println("Empty list");
        } else {
            System.out.println("First element type: "
                + list.get(0).getClass().getSimpleName());
        }
    }

    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3);
        List<String> words = List.of("hello", "world");
        List<Double> decimals = List.of(1.1, 2.2);

        printSize(nums);
        printSize(words);
        printSize(decimals);

        describeList(nums);
        describeList(words);
    }
}
```

### E. Internals / How It Works

- `<?>` is shorthand for `<? extends Object>`.
- At compile time, the compiler tracks the wildcard and prevents you from adding elements (except `null`).
- The rule is: if you don't know the type, you can't safely add to the collection.
- `List<?>` is the **most general** List type — every `List<X>` is a subtype of `List<?>`.

### F. Common Mistakes / Interview Questions

| Mistake / Question | Answer |
|---|---|
| Why can't I add to `List<?>`? | Because the compiler doesn't know what type the list holds. Adding a `String` to a `List<Integer>` through `List<?>` would break type safety. |
| `List<?>` vs `List<Object>` | `List<Object>` accepts **only** `Object`. `List<?>` accepts **any** type. They are not the same — `List<Integer>` is not a `List<Object>` but IS a `List<?>`. |
| When to use `<?>`? | When you want to **read** from a collection but don't care about the specific type. Common in utility methods. |

---

## 3. `? extends` — Upper Bounded Wildcard

### A. What is it?

`? extends Type` means "some unknown type that is **Type or a subclass of Type**." It sets an **upper bound** — the wildcard can be any type, as long as it extends the specified type. This makes the generic type **covariant** (you can read subtypes).

### B. Why do we need it?

This is how you make `List<Dog>` assignable to `List<? extends Animal>`. Without bounded wildcards, you'd have to write separate methods for every subclass. With `? extends Animal`, you write **one method** that accepts lists of `Dog`, `Cat`, `Bird`, or any other `Animal` subclass.

**The key use case:** When you want to **read** from a collection but don't need to write to it.

### C. Basic Syntax

```java
// Accepts List of Animal or any subclass of Animal
public static double totalLegs(List<? extends Animal> animals) {
    double total = 0;
    for (Animal a : animals) {
        total += a.getLegs();   // ✅ OK to READ
    }
    return total;
}

// Works with any Animal subtype
List<Dog> dogs = List.of(new Dog(), new Dog());
List<Cat> cats = List.of(new Cat());

totalLegs(dogs);   // ✅ OK
totalLegs(cats);   // ✅ OK

// Reading returns the upper bound type
public static Animal getFirst(List<? extends Animal> list) {
    return list.get(0);   // returns Animal (the upper bound)
}
```

**Key rule:** You can **read** (returns the bound type), but you can't **write** to it.

```java
public static void addAnimal(List<? extends Animal> list) {
    list.add(new Dog());      // ❌ WON'T COMPILE
    list.add(null);            // ✅ OK
}
```

### D. Small Example

```java
import java.util.List;

class Animal {
    String name;
    int legs;
    Animal(String name, int legs) {
        this.name = name;
        this.legs = legs;
    }
    int getLegs() { return legs; }
}

class Dog extends Animal {
    Dog() { super("Dog", 4); }
}

class Cat extends Animal {
    Cat() { super("Cat", 4); }
}

class Spider extends Animal {
    Spider() { super("Spider", 8); }
}

public class ExtendsDemo {

    // Works with ANY animal list — Dog, Cat, Spider, etc.
    public static void printAnimalInfo(List<? extends Animal> animals) {
        System.out.println("Number of animals: " + animals.size());
        for (Animal a : animals) {
            System.out.println(a.name + " has " + a.getLegs() + " legs");
        }
    }

    // Find the animal with the most legs
    public static Animal findMostLegs(List<? extends Animal> animals) {
        Animal most = animals.get(0);
        for (Animal a : animals) {
            if (a.getLegs() > most.getLegs()) {
                most = a;
            }
        }
        return most;
    }

    public static void main(String[] args) {
        List<Dog> dogs = List.of(new Dog(), new Dog());
        List<Cat> cats = List.of(new Cat());
        List<Spider> spiders = List.of(new Spider(), new Spider(), new Spider());

        System.out.println("--- Dogs ---");
        printAnimalInfo(dogs);

        System.out.println("\n--- Cats ---");
        printAnimalInfo(cats);

        System.out.println("\n--- Spiders ---");
        printAnimalInfo(spiders);

        Animal champion = findMostLegs(spiders);
        System.out.println("\nMost legs: " + champion.name + " (" + champion.getLegs() + ")");
    }
}
```

### E. Internals / How It Works

- `? extends T` means the compiler knows the type is **T or below** in the inheritance hierarchy.
- When you call `get()`, the return type is `T` (the upper bound), not `?`.
- You **cannot add** elements because the compiler doesn't know the exact type — it could be a `Dog` list, and adding a `Cat` would be wrong.
- This is **covariance**: `List<Dog>` IS-A `List<? extends Animal>`.

```
        Animal          ← upper bound
       /      \
     Dog      Cat       ← ? could be either one
```

### F. Common Mistakes / Interview Questions

| Mistake / Question | Answer |
|---|---|
| Why can't I add to `List<? extends Animal>`? | The compiler only knows it's "some Animal subtype." If it's actually a `List<Dog>`, adding a `Cat` would corrupt it. |
| `extends` vs `super`? | `extends` = read-only (covariant). `super` = write-only (contravariant). Use `extends` when producing, `super` when consuming. |
| When to use `? extends`? | When you want to **read** from a collection and don't need to write. E.g., methods that process, search, or sum elements. |

---

## 4. `? super` — Lower Bounded Wildcard

### A. What is it?

`? super Type` means "some unknown type that is **Type or a superclass of Type**." It sets a **lower bound** — the wildcard can be any type, as long as it's a supertype of the specified type. This makes the generic type **contravariant** (you can write subtypes).

### B. Why do we need it?

This lets you **write** to a collection safely. For example, if you have a `List<? super Dog>`, you know you can safely add `Dog` objects to it (because the list is at least a `List<Dog>`, or possibly `List<Animal>` or `List<Object>` — all of which can hold a `Dog`). Without this, you couldn't safely add anything to a generic collection.

**The key use case:** When you want to **write** to a collection but don't care about the exact element type for reading.

### C. Basic Syntax

```java
// Accepts List<Dog>, List<Animal>, List<Object> — anything Dog or above
public static void addDogs(List<? super Dog> list) {
    list.add(new Dog());         // ✅ OK to WRITE
    list.add(new Puppy());       // ✅ OK — Puppy is a Dog subtype
}

// Reading returns Object (the safest common type)
public static void readDogs(List<? super Dog> list) {
    Object item = list.get(0);   // ✅ returns Object
    Dog d = list.get(0);         // ❌ WON'T COMPILE — can't downcast
}
```

### D. Small Example

```java
import java.util.ArrayList;
import java.util.List;

class Puppy extends Dog {
    Puppy() { super(); }
}

public class SuperDemo {

    // This method puts Dog objects into ANY collection that can hold them
    public static void addPuppies(List<? super Puppy> collection) {
        collection.add(new Puppy());
        collection.add(new Puppy());
        System.out.println("Added puppies! Size: " + collection.size());
    }

    public static void main(String[] args) {

        // List<Puppy> — directly holds Puppy
        List<Puppy> puppyList = new ArrayList<>();
        addPuppies(puppyList);

        // List<Dog> — can hold Puppy because Puppy extends Dog
        List<Dog> dogList = new ArrayList<>();
        addPuppies(dogList);

        // List<Animal> — can hold Puppy because Puppy extends Animal
        List<Animal> animalList = new ArrayList<>();
        addPuppies(animalList);

        // List<Object> — can hold anything
        List<Object> objectList = new ArrayList<>();
        addPuppies(objectList);

        System.out.println("Puppy list size: " + puppyList.size());
        System.out.println("Dog list size: " + dogList.size());
        System.out.println("Animal list size: " + animalList.size());
        System.out.println("Object list size: " + objectList.size());
    }
}
```

### E. Internals / How It Works

- `? super T` means the compiler knows the type is **T or above** in the inheritance hierarchy.
- When you call `add()`, you can add `T` or any subtype of `T`.
- When you call `get()`, you only get `Object` (because you don't know the exact type).
- This is **contravariance**: `List<Animal>` IS-A `List<? super Dog>`.

```
     Object             ← could be anything up here
       |
     Animal             ← ? super Dog could be this
       |
      Dog               ← lower bound
       |
     Puppy              ← you can add this
```

### F. Common Mistakes / Interview Questions

| Mistake / Question | Answer |
|---|---|
| Why does `get()` return `Object`? | Because you only know the lower bound. The list could be `List<Dog>` or `List<Object>` — the safest common type is `Object`. |
| When to use `? super`? | When you want to **write** to a collection. E.g., methods that populate, copy, or merge collections. |
| Can I add a `Cat` to `List<? super Dog>`? | **No.** The compiler only knows the list holds Dog or its supertypes. A `Cat` is not a `Dog`. |

---

## 5. PECS — Producer Extends, Consumer Super

### A. What is it?

PECS is a **mnemonic** (memory aid) that summarizes when to use `extends` vs `super`:

> **P**roducer **E**xtends, **C**onsumer **S**uper

- If a collection **produces** (provides) data for you to read → use `? extends`
- If a collection **consumes** (receives) data that you write into it → use `? super`

### B. Why do we need it?

Java's type system is strict. Without PECS, you'd constantly run into compilation errors when trying to write flexible generic methods. PECS gives you a simple rule to decide which wildcard to use — no deep type theory required. It's the most commonly asked generics concept in interviews.

### C. The Rule

```java
// PRODUCER — you READ from it → extends
public static void copy(
    List<? extends T> source,     // produces T values → read from here
    List<? super T> dest          // consumes T values → write to here
)
```

Think of it as a **flow of data**:

```
Producer (source)  →  [ extends ]  →  reads go OUT
Consumer (dest)    →  [ super  ]  →  writes go IN
```

### D. Small Example — `Collections.copy()` Real-World Pattern

```java
import java.util.ArrayList;
import java.util.List;

public class PECSDemo {

    // PECS applied: source PRODUCES, dest CONSUMES
    public static <T> void copy(
            List<? extends T> source,    // Producer — we READ from here
            List<? super T> dest          // Consumer — we WRITE to here
    ) {
        for (T item : source) {          // reading from source
            dest.add(item);              // writing to dest
        }
    }

    // Another PECS example: find the max
    // The collection PRODUCES values, we don't write to it
    public static <T extends Comparable<T>> T findMax(List<? extends T> list) {
        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    // PECS example: populate a list
    // The collection CONSUMES values, we write to it
    public static void fillWithDefaults(List<? super Integer> list, int count) {
        for (int i = 0; i < count; i++) {
            list.add(i);                  // writing into the consumer
        }
    }

    public static void main(String[] args) {

        // --- copy() example ---
        List<Integer> source = List.of(1, 2, 3, 4, 5);
        List<Number> dest = new ArrayList<>();   // Number is a supertype of Integer
        copy(source, dest);
        System.out.println("Copied: " + dest);   // [1, 2, 3, 4, 5]

        List<Object> dest2 = new ArrayList<>();  // Object is also a supertype
        copy(source, dest2);
        System.out.println("Copied: " + dest2);  // [1, 2, 3, 4, 5]

        // --- findMax() example ---
        List<Integer> nums = List.of(3, 7, 2, 9, 4);
        System.out.println("Max: " + findMax(nums));   // 9

        List<String> words = List.of("apple", "zebra", "banana");
        System.out.println("Max: " + findMax(words));  // zebra

        // --- fillWithDefaults() example ---
        List<Integer> intList = new ArrayList<>();
        fillWithDefaults(intList, 5);
        System.out.println("Filled: " + intList);  // [0, 1, 2, 3, 4]

        List<Number> numList = new ArrayList<>();
        fillWithDefaults(numList, 3);
        System.out.println("Filled: " + numList);  // [0, 1, 2]
    }
}
```

### E. The Full Mental Model

```
                    ? extends T          ? super T
                    ───────────          ─────────
Direction:          READ only            WRITE only
Return type:        T                    Object
Add elements:       ❌ No                ✅ Yes (T or subtypes)
Use case:           Producer             Consumer
                    (provides data)      (receives data)

                  ┌────────────────────────────────────┐
                  │          ? extends T               │
                  │  (you GET T values out)            │
                  │                                    │
    Producer ──── │  source.get(0) returns T           │
                  │  source.add(x) → ❌ COMPILE ERROR  │
                  └────────────────────────────────────┘

                  ┌────────────────────────────────────┐
                  │           ? super T                │
                  │  (you PUT T values in)             │
                  │                                    │
    Consumer ──── │  dest.add(x) → ✅ (if x is T)     │
                  │  dest.get(0) returns Object        │
                  └────────────────────────────────────┘
```

### F. Common Mistakes / Interview Questions

| Mistake / Question | Answer |
|---|---|
| `? extends` vs `? super` in one sentence? | `extends` = you read. `super` = you write. |
| What does PECS stand for? | **P**roducer **E**xtends, **C**onsumer **S**uper. |
| Why does `Collections.copy()` use both? | It reads from `source` (producer → extends) and writes to `dest` (consumer → super). |
| Can I use both `extends` and `super`? | Only if a method both reads AND writes — then you need two separate type parameters, not wildcards. |
| `List<? extends Number>` — can I add an Integer? | **No.** The list might be `List<Double>`. `extends` means read-only. |
| `List<? super Integer>` — what does `get()` return? | `Object`. Because the list could be `List<Number>` or `List<Object>`. |
| When do I NOT need wildcards? | When the type parameter is **named** (`<T>`), not anonymous (`<?>`). Named parameters let you read AND write. Wildcards are for flexibility in method signatures. |
| What is the difference between `<T extends Animal>` and `<? extends Animal>`? | `<T extends Animal>` **declares** a type parameter with a bound — you can use `T` throughout the method. `<? extends Animal>` is a wildcard — anonymous, read-only, used in parameters. |

---

## Quick Reference: Generics Cheat Sheet

| Syntax | Meaning | Can Read? | Can Write? |
|---|---|---|---|
| `<T>` | Declare a type parameter | ✅ returns `T` | ✅ add `T` |
| `<?>` | Unknown type | ✅ returns `Object` | ❌ (except null) |
| `<? extends T>` | T or subclass of T | ✅ returns `T` | ❌ (except null) |
| `<? super T>` | T or superclass of T | ✅ returns `Object` | ✅ add `T` |

---

## Bounded Type Parameters (`<T extends Bound>`)

This is different from wildcards — it **declares** a named type with a bound:

```java
// T must be Comparable
public static <T extends Comparable<T>> T findMin(T[] array) {
    T min = array[0];
    for (T item : array) {
        if (item.compareTo(min) < 0) {
            min = item;
        }
    }
    return min;
}

// Multiple bounds (class first, then interfaces)
public static <T extends Animal & Comparable<T>> T findOldest(List<T> list) {
    // T must extend Animal AND implement Comparable
    // ...
}
```

### Difference Between Bounded `<T>` and Bounded `<?>`

| Feature | `<T extends Animal>` | `<? extends Animal>` |
|---|---|---|
| Named? | Yes — you use `T` everywhere | No — anonymous |
| Read type? | Returns `T` | Returns `Animal` |
| Write? | ✅ Yes — add `T` | ❌ No |
| Multiple uses? | ✅ Same type in all positions | ❌ Each `?` could be different |

---

*Written for Java interview prep and quick reference.*
