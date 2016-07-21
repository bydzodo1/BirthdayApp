package bydzovsky.dominik.birthdayapp.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ee5415 on 15.7.2016.
 */
public class Person implements Comparable {
    private int id;
    private String name;
    private String surname;
    private Date birthday;
    private String email;
    private String nameday;
    private int dayOfCelebration;
    private int celebratesWhat;
    private String phone;
    private String address;
    private String other;
    public static final int NAMEDAY = 0;
    public static final int BIRTHDAY = 1;

    public Person(int id, String name, String surname, String email, String nameday, Date birthday, int celebratesWhat, int dayOfCelebration, String phone, String address, String other) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.nameday = nameday;
        this.birthday = birthday;
        this.celebratesWhat = celebratesWhat;
        this.dayOfCelebration = dayOfCelebration;
        this.phone = phone;
        this.address = address;
        this.other = other;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getOther() {
        return other;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOther(String other) {
        this.other = other;
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

    public int getDayOfCelebration() {
        return dayOfCelebration;
    }

    public int getCelebratesWhat() {
        return celebratesWhat;
    }

    public int getAge(){
        Calendar calendar = Calendar.getInstance();
        int todaysYear = calendar.get(Calendar.YEAR);
        calendar.setTime(birthday);
        int personsYear = calendar.get(Calendar.YEAR);
        return todaysYear - personsYear;
    }
    @Override
    public int compareTo(Object another) {
        // this.compareTo(person)
        // return -2: this je menší
        Calendar calendar = Calendar.getInstance();
        int todaysDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        Person person = (Person) another;
        int personsDay = person.getDayOfCelebration();
        int thissDay = this.getDayOfCelebration();

        //oba jsou před tímto datem
        if (personsDay <= todaysDayOfYear && thissDay <= todaysDayOfYear) {
            return thissDay - personsDay;
        }
        // Oba jsou po tomto datu
        else if (personsDay >= todaysDayOfYear && thissDay >= todaysDayOfYear) {
            return thissDay - personsDay;
        }
        // this je před datem a person je po datu
        else if (personsDay >= todaysDayOfYear && thissDay <= todaysDayOfYear) {
            return 1;
        }
        // this je po datu a person je před datem
        else if (personsDay <= todaysDayOfYear && thissDay >= todaysDayOfYear) {
            return -1;
        }
        return 0;
    }
}
