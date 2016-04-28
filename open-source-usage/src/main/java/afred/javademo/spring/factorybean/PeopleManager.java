package afred.javademo.spring.factorybean;


import afred.javademo.spring.bean.People;

/**
 * Created by winnie on 15/12/12.
 */
public class PeopleManager {

    private People people;

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}
