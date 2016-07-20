package bydzovsky.dominik.birthdayapp.model;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ee5415 on 18.7.2016.
 */
public class SQLiteDataManager {
    SQLiteDatabase myDatabase;
    public static final String DATABASE_NAME = "BirthdayApp.db";

    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_SURNAME = "surname";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_NAMEDAY = "nameday";
    public static final String CONTACTS_COLUMN_BIRTHDAY = "birthday";

    public SQLiteDataManager() {
        buildDatabase();
        createSomeData();
    }

    public void buildDatabase(){
        myDatabase = SQLiteDatabase.openOrCreateDatabase(DATABASE_NAME, null);

        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS" + CONTACTS_TABLE_NAME + "("
                + CONTACTS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CONTACTS_COLUMN_NAME + " TEXT, "
                + CONTACTS_COLUMN_SURNAME + " TEXT, "
                + CONTACTS_COLUMN_EMAIL + " TEXT, "
                + CONTACTS_COLUMN_NAMEDAY + "TEXT, "
                + CONTACTS_COLUMN_BIRTHDAY + "DATE )";
        myDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    public void createSomeData(){
        String query = "INSERT INTO " + CONTACTS_TABLE_NAME +
               "("+CONTACTS_COLUMN_ID+","+CONTACTS_COLUMN_NAME+","+CONTACTS_COLUMN_SURNAME+","
                +CONTACTS_COLUMN_EMAIL+","+CONTACTS_COLUMN_NAMEDAY+","+CONTACTS_COLUMN_BIRTHDAY+") VALUES";

        String query1 = query + "(null,'Dominik','Bydžovský','dominik01@email.cz','04.08.','11/29/1994');";
        String query2 = query + "(null,'Pavel','Hromada','hromada@seznam.cz','01.11.','05/27/1954');";
        String query3 = query + "(null,'Tomáš','Maliniak','t.maliniak@seznam.cz','25.02.','12/30/2000');";
        String query4 = query + "(null,'Jakub','Tran','tran@email.cz','12.08.','05/01/1950');";
        String query5 = query + "(null,'Jan','Urban','ycbxcb@email.cz','04.08.','09/09/1920');";
        String query6 = query + "(null,'Pavel','Košťál','dfasdf@email.cz','11.12.','10/15/2005');";
        String query7 = query + "(null,'Henry','Novotný','asd@email.cz','05.09.','11/10/2007');";
        myDatabase.execSQL(query1);
        myDatabase.execSQL(query2);
        myDatabase.execSQL(query3);
        myDatabase.execSQL(query4);
        myDatabase.execSQL(query5);
        myDatabase.execSQL(query6);
        myDatabase.execSQL(query7);
    }

    public SQLiteDatabase getMyDatabase() {
        return myDatabase;
    }
}
