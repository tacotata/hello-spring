package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args){
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("abc");

//        String name = helloLombok.getName();
//        System.out.println("name = " + name); //name= abc
        System.out.println("helloLombok = " + helloLombok); //helloLombok = HelloLombok(name=abc, age=0)
    }
}
