package bydzovsky.dominik.birthdayapp.model;

import java.util.Date;

/**
 * Created by ee5415 on 15.7.2016.
 */
public class Person {
    int id;
    String name;
    String surname;
    Date birthday;
    String email;
    String nameday;

    public Person(int id, String name, String surname, String email, String nameday, Date birthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.nameday = nameday;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getNameday() {
        return nameday;
    }

    public int getId() {
        return id;
    }
}
