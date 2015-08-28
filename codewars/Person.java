public class Person {
    String name;

    public Person(String personName) {
        name = personName;
    }

    public String greet(String yourName) {
        return String.format("Hi %s, my name is %s", name, yourName);
    }

    public static void main(String[] args){
        Person p1 = new Person("name1");
        System.out.println(p1.greet("name2"));
    }
}