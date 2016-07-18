package bydzovsky.dominik.birthdayapp.utility;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bydzovsky.dominik.birthdayapp.model.Person;
import bydzovsky.dominik.birthdayapp.model.SQLiteDataManager;

public class Service {
    SQLiteDatabase myDatabase;

    public Service() {
//        if (null==myDatabase) {
//            myDatabase = new SQLiteDataManager().getMyDatabase();
//        }
    }
    public ArrayList<Person> getListOfPeople(){
        //String query = "SELECT * FROM " + SQLiteDataManager.CONTACTS_TABLE_NAME + "order by "+SQLiteDataManager.CONTACTS_COLUMN_BIRTHDAY+";";
        ArrayList<Person> listOfPeople = new ArrayList<>();
        listOfPeople.add(new Person(1,"David","Nodopíči","email","01.01.", new Date()));
        listOfPeople.add(new Person(2,"Petr","Kundahunda","email","01.01.", new Date()));
        listOfPeople.add(new Person(3,"Honza","Strčilhomezivrata","email","01.01.", new Date()));
        listOfPeople.add(new Person(4,"Kapříček","Melounek","email","01.01.", new Date()));
        listOfPeople.add(new Person(5,"Uršule","Strčjitamprst","email","01.01.", new Date()));
        listOfPeople.add(new Person(3,"Plešoun","Sinamazalhlavu","email","01.01.", new Date()));
        listOfPeople.add(new Person(4,"Hovniválek","Pršíhovno","email","01.01.", new Date()));
        listOfPeople.add(new Person(5,"Anežka","Malékozymá","email","01.01.", new Date()));
        return listOfPeople;
    }

}
