package Projeto;


public class Person {

    private String ideNumber;

    private String name;

    private String address;

    private Date birth;

    public int age() {
        return 0;
    }

    public boolean olderThan(Person p) {
        return false;
    }

    public String toString() {
        return null;
    }

    public boolean equals(Object o) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String getIdeNumber() {
        return ideNumber;
    }
    public void setIdeNumber(String ideNumber) {
        this.ideNumber = ideNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }


}