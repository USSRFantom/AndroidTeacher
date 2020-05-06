package space.kroha.androidteacher;

class Lesson {
    private String age;
    private String lastname;
    private String name;


    public Lesson() {
    }

    public Lesson(String age, String lastname, String name) {
        this.age = age;
        this.lastname = lastname;
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
