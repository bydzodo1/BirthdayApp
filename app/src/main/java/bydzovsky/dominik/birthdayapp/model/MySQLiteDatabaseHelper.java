package bydzovsky.dominik.birthdayapp.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MySQLiteDatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH = "/data/data/bydzovsky.dominik.birthdayapp/databases";
    private static String DATABASE_NAME = "birthdayapp.db";
    private Context myContext;
    public final static int DEFAULT_SHOW_DAYS = 70;
    private SQLiteDatabase db;

    public SQLiteDatabase getDb() {
        return db;
    }

    public MySQLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = SQLiteDatabase.openOrCreateDatabase(DB_PATH, null);
        onCreate(db);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableDays();
        createTableContacts();
        //addConstraintsOnTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Person> getAllContacts(){
        ArrayList<Person> lst = new ArrayList<>();
        Cursor c1 = db.rawQuery("SELECT id, name, surname, email, nameday, birthday, day_of_year_birthday,phone,address,other FROM contacts", null);

        c1.moveToFirst();
        while (c1.moveToNext()) {
            int id = c1.getInt(0);
            String name = c1.getString(1);
            String surname = c1.getString(2);
            String email = c1.getString(3);
            String nameday = c1.getString(4);
            Date birthday = java.sql.Date.valueOf(c1.getString(5));
            int dayOfYearBirthday = c1.getInt(6);
            String phone = c1.getString(7);
            String address = c1.getString(8);
            String other = c1.getString(9);
            lst.add(new Person(id, name, surname, email, nameday, birthday, Person.BIRTHDAY, dayOfYearBirthday, phone, address, other));
        }
        return lst;
    }
    public ArrayList<Person> getBirthdaysList() {
        return getBirthdaysList(DEFAULT_SHOW_DAYS);
    }

    public ArrayList<Person> getBirthdaysList(int showDays) {
        Calendar calendar = Calendar.getInstance();
        int from = calendar.get(Calendar.DAY_OF_YEAR);
        return getBirthdaysList(from, showDays);
    }

    public ArrayList<Person> getTestData() {
        ArrayList<Person> listOfPeople = new ArrayList<>();
//        listOfPeople.add(new Person(1, "David", "Nodopíči", "email", "01.01.", new Date()));
//        listOfPeople.add(new Person(2, "Petr", "Kundahunda", "email", "01.01.", new Date()));
//        listOfPeople.add(new Person(3, "Honza", "Strčilhomezivrata", "email", "01.01.", new Date()));
//        listOfPeople.add(new Person(4, "Kapříček", "Melounek", "email", "01.01.", new Date()));
//        listOfPeople.add(new Person(5, "Uršule", "Strčjitamprst", "email", "01.01.", new Date()));
//        listOfPeople.add(new Person(6, "Plešoun", "Sinamazalhlavu", "email", "01.01.", new Date()));
//        listOfPeople.add(new Person(7, "Hovniválek", "Pršíhovno", "email", "01.01.", new Date()));
//        listOfPeople.add(new Person(8, "Anežka", "Malékozymá", "email", "01.01.", new Date()));
        return listOfPeople;
    }

    public ArrayList<Person> getBirthdaysList(int from, int showDays) {

        Calendar calendar = Calendar.getInstance();
        int todaysDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

        //return getTestData();
        int toDayOfYear = from + showDays;
        ArrayList<Person> records1 = new ArrayList<>();
        if (toDayOfYear > 366) {
            int fromJan = Math.abs(toDayOfYear - 366);

            Cursor c1 = db.rawQuery("SELECT id, name, surname, email, nameday, birthday, day_of_year_birthday,phone,address,other FROM contacts WHERE day_of_year_birthday > 0" +
                    " AND day_of_year_birthday < " + fromJan + " ORDER BY day_of_year_birthday", null);

            c1.moveToFirst();
            while (c1.moveToNext()) {

                int id = c1.getInt(0);
                String name = c1.getString(1);
                String surname = c1.getString(2);
                String email = c1.getString(3);
                String nameday = c1.getString(4);

                Date birthday = java.sql.Date.valueOf(c1.getString(5));
                int dayOfYearBirthday = c1.getInt(6);
                String phone = c1.getString(7);
                String address = c1.getString(8);
                String other = c1.getString(9);
                //if (!is_leap_year() && todaysDayOfYear > 59) dayOfYearBirthday--;
                records1.add(new Person(id, name, surname, email, nameday, birthday, Person.BIRTHDAY, dayOfYearBirthday, phone, address, other));
            }
        }

        ArrayList<Person> records2 = new ArrayList<>();

        Cursor c2 = db.rawQuery("SELECT id,name,surname,email,nameday,birthday,day_of_year_birthday,phone,address,other FROM contacts WHERE day_of_year_birthday > " + from +
                " AND day_of_year_birthday < " + toDayOfYear + " ORDER BY day_of_year_birthday", null);

        c2.moveToFirst();
        while (c2.moveToNext()) {
            int id = c2.getInt(0);
            String name = c2.getString(1);
            String surname = c2.getString(2);
            String email = c2.getString(3);
            String nameday = c2.getString(4);
            String dateString = c2.getString(5);
            int dayOfYearBirthday = c2.getInt(6);
            //if (!is_leap_year() && todaysDayOfYear > 59) dayOfYearBirthday--;
            Date birthday = java.sql.Date.valueOf(dateString);

            String phone = c2.getString(7);
            String address = c2.getString(8);
            String other = c2.getString(9);
            records2.add(new Person(id, name, surname, email, nameday, birthday, Person.BIRTHDAY, dayOfYearBirthday,phone,address,other));
        }
        ArrayList<Person> merged = new ArrayList<>();
        merged.addAll(records1);
        merged.addAll(records2);
        return merged;
    }

    public ArrayList<Person> getNameDayList() {
        return getNameDayList(DEFAULT_SHOW_DAYS);
    }

    public ArrayList<Person> getNameDayList(int showDays) {
        Calendar calendar = Calendar.getInstance();
        int from = calendar.get(Calendar.DAY_OF_YEAR);
        return getNameDayList(from, showDays);
    }

    public ArrayList<Person> getNameDayList(int from, int showDays) {
        int toDayOfYear = from + showDays;

        ArrayList<Person> records1 = new ArrayList<>();
        if (toDayOfYear > 366) {
            int fromJan = Math.abs(toDayOfYear - 366);
            Cursor c1 = db.rawQuery("SELECT id,name,surname,email,nameday,birthday,phone,address,other FROM contacts WHERE nameday > 0 AND nameday < " + fromJan + " ORDER BY nameday;", null);

            c1.moveToFirst();
            while (c1.moveToNext()) {
                int id = c1.getInt(0);
                String name = c1.getString(1);
                String surname = c1.getString(2);
                String email = c1.getString(3);
                int nameday = c1.getInt(4);
                Date birthday = java.sql.Date.valueOf(c1.getString(5));
                String phone = c1.getString(6);
                String address = c1.getString(7);
                String other = c1.getString(8);
                records1.add(new Person(id, name, surname, email, nameday + "", birthday, Person.NAMEDAY, nameday,phone,address,other));
            }
        }

        ArrayList<Person> records2 = new ArrayList<>();

        Cursor c2 = db.rawQuery("SELECT id,name,surname,email,nameday,birthday,phone,address,other FROM contacts WHERE nameday > " + from +
                " AND nameday < " + toDayOfYear + " ORDER BY nameday;", null);
        c2.moveToFirst();
        while (c2.moveToNext()) {
            int id = c2.getInt(0);
            String name = c2.getString(1);
            String surname = c2.getString(2);
            String email = c2.getString(3);
            int nameday = c2.getInt(4);
            Date birthday = java.sql.Date.valueOf(c2.getString(5));

            String phone = c2.getString(6);
            String address = c2.getString(7);
            String other = c2.getString(8);
            records1.add(new Person(id, name, surname, email, nameday + "", birthday, Person.NAMEDAY, nameday,phone, address,other));
        }

        ArrayList<Person> merged = new ArrayList<>();
        merged.addAll(records1);
        merged.addAll(records2);
        return merged;

//        $recs = array();
//        foreach($records as $record) {
//            $day = $this -> database -> table('days')->select('day, month, n_month')->
//            wherePrimary($record -> nameday)->fetch();
//            $nameday = $record -> nameday;
//            if (!$this -> is_leap_year((int) date('YYYY')) && $nameday > 60) {
//                $nameday -= 1;
//            }
//            $recs[]=(object) array(
//                    'name' = > $record -> name,
//                    'surname' =>$record -> surname,
//                    'nameday' =>$nameday,
//                    'day' =>$day -> day,
//                    'month' =>$day -> month,
//                    'n_month' =>$day -> n_month
//            );
//        }
//        return $recs;
    }
//
//    public Date parseDateFromDatabase(String sqlDate) {
//        Date date;
//        int year = Integer.parseInt(sqlDate.substring(0,3));
//        int month = Integer.parseInt(sqlDate.substring(5,6));
//        int day = Integer.parseInt(sqlDate.substring(8,9));
//        date = new Date();
//        date.setYear(year);
//        date.setMonth(month);
////        try {
////            date = SimpleDateFormat.getInstance().parse(sqlDate);
////        } catch (Exception e){
////            e.printStackTrace();
////            date = null;
////        }
//        return date;
//    }

    public void addConstraintsOnTables() {
        db.execSQL("ALTER TABLE contacts\n" +
                " ADD CONSTRAINT svatekKontaktu FOREIGN KEY (`nameday`) REFERENCES days (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;");
    }

    public void createTableContacts() {
        db.execSQL("CREATE TABLE IF NOT EXISTS contacts (\n" +
                "  `id` integer,\n" +
                "  `name` text,\n" +
                "  `surname` text,\n" +
                "  `nameday` integer,\n" +
                "  `birthday` date,\n" +
                "  `day_of_year_birthday` integer,\n" +
                "  `email` text,\n" +
                "  `phone` text,\n" +
                "  `address` text,\n" +
                "  `other` text,\n" +
                "  PRIMARY KEY( 'id' )" +
              //  " FOREIGN KEY(namedayOfContact) REFERENCES days(id)" +
                ");");

        db.execSQL("DELETE FROM contacts");
        db.execSQL("INSERT INTO contacts (`id`, `name`, `surname`, `nameday`, `birthday`, `day_of_year_birthday`, `email`, `phone`, `address`, `other`) VALUES\n" +
                "(1, 'Petr', 'Skocdopole', 217, '1994-11-29', 334, 'dominik01@email.cz', '733600596', NULL, ''),\n" +
                "(2, 'Anička', 'Vylízalpes', 53, '1988-03-20', 80, 'asdfsdf@asd', '786789782789', NULL, ''),\n" +
                "(3, 'Alžběta', 'Hnusněvypadám', 165, '1995-07-11', 193, 'afsg@ncvb', '789678967896', NULL, ''),\n" +
                "(4, 'Martin', 'Láskomílek', 199, '1995-07-03', 185, 'sdadf@asdf', '78967867896', NULL, ''),\n" +
                "(5, 'Jan', 'Křemílek', 145, '1965-07-09', 191, 'kremilkova@seznam.cz', '78967892', NULL, ''),\n" +
                "(6, 'Klára', 'Nečekaná', 225, '1990-07-01', 183, 'necekana@gmail.com', '7896789278', NULL, ''),\n" +
                "(7, 'Jiří', 'Mouchulapil', 115, '1962-01-27', 27, 'mouchulapil@gmail.com', '789289278', NULL, ''),\n" +
                "(8, 'Vít', 'Znechutil', 167, '1995-03-19', 79, 'znechutil@gmail.com', '78962789', NULL, ''),\n" +
                "(9, 'Kateřina', 'Dostavníková', 330, '1997-01-06', 6, 'dostanikova@gmail.com', '785752782', NULL, ''),\n" +
                "(10, 'Jitka', 'Usmrkaná', 340, '1968-08-26', 239, 'usmrkana@gmail.com', '78785785', NULL, ''),\n" +
                "(11, 'David', 'Líný', 365, '1992-10-06', 280, 'liny@gmail.com', '77867572', NULL, ''),\n" +
                "(12, 'Denisa', 'Hlavakulatá', 261, '1966-11-22', 327, 'hlavakulata@gmail.com', '7464868796', NULL, ''),\n" +
                "(13, 'Jiřinka', 'Utrhlimihlavu', 115, '1990-07-11', 193, 'palec@gmail.com', '78678578578', NULL, ''),\n" +
                "(14, 'Pepinka', 'Krásnopleťka', 79, '1962-09-24', 268, 'malicek@gmail.com', '5785785786', NULL, ''),\n" +
                "(15, 'Vylízanou', 'Hlavuma', 90, '1970-11-22', 327, 'prstenicek@gmail.com', '787857278', NULL, ''),\n" +
                "(16, 'Honzík', 'Skočildotrávy', 145, '1943-02-19', 50, 'skociladotravy@gmail.com', '78578578', NULL, ''),\n" +
                "(17, 'Jaroslava', 'Uprdlasi', 118, '1940-12-03', 338, 'kopnuldonej@gmail.com', '786785754', NULL, ''),\n" +
                "(18, 'Jaroslava', 'Lízátková', 183, '1944-03-18', 78, 'lizatkova@gmail.com', '452452456', NULL, ''),\n" +
                "(19, 'Pepa', 'Zeslámydosenaskočil', 256, '1941-09-21', 265, 'maruska@asd.cz', '12365871', NULL, ''),\n" +
                "(20, 'Ladislav', 'Uzdičůral', 178, '1938-01-10', 10, 'uzdichcal@gmail.com', '6456456456', NULL, ''),\n" +
                "(21, 'Jiří', 'Samostatný', 115, '1966-04-15', 106, 'samostatny@gmail.com', '45645645', NULL, '');");
    }

    public void createTableDays() {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS days (\n" +
                        "  `id` integer,\n" +
                        "  `day` integer,\n" +
                        "  `month` varchar,\n" +
                        "  `n_month` integer,\n" +
                        "  `svatek1` varchar,\n" +
                        "  `svatek2` varchar,\n" +
                        "  `svatek3` varchar," +
                        " PRIMARY KEY( 'id' ));\n");
        db.execSQL("DELETE FROM days");
        db.execSQL(
                "INSERT INTO days (`id`, `day`, `month`, `n_month`, `svatek1`, `svatek2`, `svatek3`) VALUES\n " +
                        "(1, 1, 'leden', 1, 'Nový rok', '', ''),\n" +
                        "(2, 2, 'leden', 1, 'Karina', '', ''),\n" +
                        "(3, 3, 'leden', 1, 'Radmila', '', ''),\n" +
                        "(4, 4, 'leden', 1, 'Diana', '', ''),\n" +
                        "(5, 5, 'leden', 1, 'Dalimil', '', ''),\n" +
                        "(6, 6, 'leden', 1, 'Tři králové', '', ''),\n" +
                        "(7, 7, 'leden', 1, 'Vilma', '', ''),\n" +
                        "(8, 8, 'leden', 1, 'Čestmír', '', ''),\n" +
                        "(9, 9, 'leden', 1, 'Vladan', '', ''),\n" +
                        "(10, 10, 'leden', 1, 'Břetislav', '', ''),\n" +
                        "(11, 11, 'leden', 1, 'Bohdana', '', ''),\n" +
                        "(12, 12, 'leden', 1, 'Pravoslav', '', ''),\n" +
                        "(13, 13, 'leden', 1, 'Edita', '', ''),\n" +
                        "(14, 14, 'leden', 1, 'Radovan', '', ''),\n" +
                        "(15, 15, 'leden', 1, 'Alice', '', ''),\n" +
                        "(16, 16, 'leden', 1, 'Ctirad', '', ''),\n" +
                        "(17, 17, 'leden', 1, 'Drahoslav', '', ''),\n" +
                        "(18, 18, 'leden', 1, 'Vladislav', '', ''),\n" +
                        "(19, 19, 'leden', 1, 'Doubravka', '', ''),\n" +
                        "(20, 20, 'leden', 1, 'Ilona', '', ''),\n" +
                        "(21, 21, 'leden', 1, 'Běla', '', ''),\n" +
                        "(22, 22, 'leden', 1, 'Slavomír', '', ''),\n" +
                        "(23, 23, 'leden', 1, 'Zdeněk', '', ''),\n" +
                        "(24, 24, 'leden', 1, 'Milena', '', ''),\n" +
                        "(25, 25, 'leden', 1, 'Miloš', '', ''),\n" +
                        "(26, 26, 'leden', 1, 'Zora', '', ''),\n" +
                        "(27, 27, 'leden', 1, 'Ingrid', '', ''),\n" +
                        "(28, 28, 'leden', 1, 'Otýlie', '', ''),\n" +
                        "(29, 29, 'leden', 1, 'Zdislava', '', ''),\n" +
                        "(30, 30, 'leden', 1, 'Robin', '', ''),\n" +
                        "(31, 31, 'leden', 1, 'Marika', '', ''),\n" +
                        "(32, 1, 'únor', 2, 'Hynek', '', ''),\n" +
                        "(33, 2, 'únor', 2, 'Nela', '', ''),\n" +
                        "(34, 3, 'únor', 2, 'Blažej', '', ''),\n" +
                        "(35, 4, 'únor', 2, 'Jarmila', '', ''),\n" +
                        "(36, 5, 'únor', 2, 'Dobromila', '', ''),\n" +
                        "(37, 6, 'únor', 2, 'Vanda', '', ''),\n" +
                        "(38, 7, 'únor', 2, 'Veronika', '', ''),\n" +
                        "(39, 8, 'únor', 2, 'Milada', '', ''),\n" +
                        "(40, 9, 'únor', 2, 'Apolena', '', ''),\n" +
                        "(41, 10, 'únor', 2, 'Mojmír', '', ''),\n" +
                        "(42, 11, 'únor', 2, 'Božena', '', ''),\n" +
                        "(43, 12, 'únor', 2, 'Slavěna', '', ''),\n" +
                        "(44, 13, 'únor', 2, 'Věnceslav', '', ''),\n" +
                        "(45, 14, 'únor', 2, 'Valentýn', '', ''),\n" +
                        "(46, 15, 'únor', 2, 'Jiřina', '', ''),\n" +
                        "(47, 16, 'únor', 2, 'Ljuba', '', ''),\n" +
                        "(48, 17, 'únor', 2, 'Miloslava', '', ''),\n" +
                        "(49, 18, 'únor', 2, 'Gizela', '', ''),\n" +
                        "(50, 19, 'únor', 2, 'Patrik', '', ''),\n" +
                        "(51, 20, 'únor', 2, 'Oldřich', '', ''),\n" +
                        "(52, 21, 'únor', 2, 'Lenka', '', ''),\n" +
                        "(53, 22, 'únor', 2, 'Petr', '', ''),\n" +
                        "(54, 23, 'únor', 2, 'Svatopluk', '', ''),\n" +
                        "(55, 24, 'únor', 2, 'Matěj', '', ''),\n" +
                        "(56, 25, 'únor', 2, 'Liliana', '', ''),\n" +
                        "(57, 26, 'únor', 2, 'Dorota', '', ''),\n" +
                        "(58, 27, 'únor', 2, 'Alexandr', '', ''),\n" +
                        "(59, 28, 'únor', 2, 'Lumír', '', ''),\n" +
                        "(60, 29, 'únor', 2, 'Horymír', '', ''),\n" +
                        "(61, 1, 'březen', 3, 'Bedřich', '', ''),\n" +
                        "(62, 2, 'březen', 3, 'Anežka', '', ''),\n" +
                        "(63, 3, 'březen', 3, 'Kamil', '', ''),\n" +
                        "(64, 4, 'březen', 3, 'Stela', '', ''),\n" +
                        "(65, 5, 'březen', 3, 'Kazimír', '', ''),\n" +
                        "(66, 6, 'březen', 3, 'Miroslav', '', ''),\n" +
                        "(67, 7, 'březen', 3, 'Tomáš', '', ''),\n" +
                        "(68, 8, 'březen', 3, 'Gabriela', '', ''),\n" +
                        "(69, 9, 'březen', 3, 'Františka', '', ''),\n" +
                        "(70, 10, 'březen', 3, 'Viktorie', '', ''),\n" +
                        "(71, 11, 'březen', 3, 'Anděla', '', ''),\n" +
                        "(72, 12, 'březen', 3, 'Řehoř', '', ''),\n" +
                        "(73, 13, 'březen', 3, 'Růžena', '', ''),\n" +
                        "(74, 14, 'březen', 3, 'Růt', 'Matylda', ''),\n" +
                        "(75, 15, 'březen', 3, 'Ida', '', ''),\n" +
                        "(76, 16, 'březen', 3, 'Elena', 'Herbert', ''),\n" +
                        "(77, 17, 'březen', 3, 'Vlastimil', '', ''),\n" +
                        "(78, 18, 'březen', 3, 'Eduard', '', ''),\n" +
                        "(79, 19, 'březen', 3, 'Josef', '', ''),\n" +
                        "(80, 20, 'březen', 3, 'Světlana', '', ''),\n" +
                        "(81, 21, 'březen', 3, 'Radek', '', ''),\n" +
                        "(82, 22, 'březen', 3, 'Leona', '', ''),\n" +
                        "(83, 23, 'březen', 3, 'Ivona', '', ''),\n" +
                        "(84, 24, 'březen', 3, 'Gabriel', '', ''),\n" +
                        "(85, 25, 'březen', 3, 'Marián', '', ''),\n" +
                        "(86, 26, 'březen', 3, 'Emanuel', '', ''),\n" +
                        "(87, 27, 'březen', 3, 'Dita', '', ''),\n" +
                        "(88, 28, 'březen', 3, 'Soňa', '', ''),\n" +
                        "(89, 29, 'březen', 3, 'Taťána', '', ''),\n" +
                        "(90, 30, 'březen', 3, 'Arnošt', '', ''),\n" +
                        "(91, 31, 'březen', 3, 'Kvido', '', ''),\n" +
                        "(92, 1, 'duben', 4, 'Hugo', '', ''),\n" +
                        "(93, 2, 'duben', 4, 'Erika', '', ''),\n" +
                        "(94, 3, 'duben', 4, 'Richard', '', ''),\n" +
                        "(95, 4, 'duben', 4, 'Ivana', '', ''),\n" +
                        "(96, 5, 'duben', 4, 'Miroslava', '', ''),\n" +
                        "(97, 6, 'duben', 4, 'Vendula', '', ''),\n" +
                        "(98, 7, 'duben', 4, 'Heřman', 'Hermína', ''),\n" +
                        "(99, 8, 'duben', 4, 'Ema', '', ''),\n" +
                        "(100, 9, 'duben', 4, 'Dušan', '', ''),\n" +
                        "(101, 10, 'duben', 4, 'Darja', '', ''),\n" +
                        "(102, 11, 'duben', 4, 'Izabela', '', ''),\n" +
                        "(103, 12, 'duben', 4, 'Julius', '', ''),\n" +
                        "(104, 13, 'duben', 4, 'Aleš', '', ''),\n" +
                        "(105, 14, 'duben', 4, 'Vincenc', '', ''),\n" +
                        "(106, 15, 'duben', 4, 'Anastázie', '', ''),\n" +
                        "(107, 16, 'duben', 4, 'Irena', '', ''),\n" +
                        "(108, 17, 'duben', 4, 'Rudolf', '', ''),\n" +
                        "(109, 18, 'duben', 4, 'Valérie', '', ''),\n" +
                        "(110, 19, 'duben', 4, 'Rostislav', '', ''),\n" +
                        "(111, 20, 'duben', 4, 'Marcela', '', ''),\n" +
                        "(112, 21, 'duben', 4, 'Alexandra', '', ''),\n" +
                        "(113, 22, 'duben', 4, 'Evženie', '', ''),\n" +
                        "(114, 23, 'duben', 4, 'Vojtěch', '', ''),\n" +
                        "(115, 24, 'duben', 4, 'Jiří', '', ''),\n" +
                        "(116, 25, 'duben', 4, 'Marek', '', ''),\n" +
                        "(117, 26, 'duben', 4, 'Oto', '', ''),\n" +
                        "(118, 27, 'duben', 4, 'Jaroslav', '', ''),\n" +
                        "(119, 28, 'duben', 4, 'Vlastislav', '', ''),\n" +
                        "(120, 29, 'duben', 4, 'Robert', '', ''),\n" +
                        "(121, 30, 'duben', 4, 'Blahoslav', '', ''),\n" +
                        "(122, 1, 'květen', 5, 'Svátek práce', '', ''),\n" +
                        "(123, 2, 'květen', 5, 'Zikmund', '', ''),\n" +
                        "(124, 3, 'květen', 5, 'Alexej', '', ''),\n" +
                        "(125, 4, 'květen', 5, 'Květoslav', '', ''),\n" +
                        "(126, 5, 'květen', 5, 'Klaudie', '', ''),\n" +
                        "(127, 6, 'květen', 5, 'Radoslav', '', ''),\n" +
                        "(128, 7, 'květen', 5, 'Stanislav', '', ''),\n" +
                        "(129, 8, 'květen', 5, 'Státní svátek', 'Den osvobození', ''),\n" +
                        "(130, 9, 'květen', 5, 'Ctibor', '', ''),\n" +
                        "(131, 10, 'květen', 5, 'Blažena', '', ''),\n" +
                        "(132, 11, 'květen', 5, 'Svatava', '', ''),\n" +
                        "(133, 12, 'květen', 5, 'Pankrác', '', ''),\n" +
                        "(134, 13, 'květen', 5, 'Servác', '', ''),\n" +
                        "(135, 14, 'květen', 5, 'Bonifác', '', ''),\n" +
                        "(136, 15, 'květen', 5, 'Žofie', '', ''),\n" +
                        "(137, 16, 'květen', 5, 'Přemysl', '', ''),\n" +
                        "(138, 17, 'květen', 5, 'Aneta', '', ''),\n" +
                        "(139, 18, 'květen', 5, 'Nataša', '', ''),\n" +
                        "(140, 19, 'květen', 5, 'Ivo', '', ''),\n" +
                        "(141, 20, 'květen', 5, 'Zbyšek', '', ''),\n" +
                        "(142, 21, 'květen', 5, 'Monika', '', ''),\n" +
                        "(143, 22, 'květen', 5, 'Emil', '', ''),\n" +
                        "(144, 23, 'květen', 5, 'Vladimír', '', ''),\n" +
                        "(145, 24, 'květen', 5, 'Jana', '', ''),\n" +
                        "(146, 25, 'květen', 5, 'Viola', '', ''),\n" +
                        "(147, 26, 'květen', 5, 'Filip', '', ''),\n" +
                        "(148, 27, 'květen', 5, 'Valdemar', '', ''),\n" +
                        "(149, 28, 'květen', 5, 'Vilém', '', ''),\n" +
                        "(150, 29, 'květen', 5, 'Maxmilián', 'Maxim', ''),\n" +
                        "(151, 30, 'květen', 5, 'Ferdinand', '', ''),\n" +
                        "(152, 31, 'květen', 5, 'Kamila', '', ''),\n" +
                        "(153, 1, 'červen', 6, 'Laura', '', ''),\n" +
                        "(154, 2, 'červen', 6, 'Jarmil', '', ''),\n" +
                        "(155, 3, 'červen', 6, 'Tamara', '', ''),\n" +
                        "(156, 4, 'červen', 6, 'Dalibor', '', ''),\n" +
                        "(157, 5, 'červen', 6, 'Dobroslav', '', ''),\n" +
                        "(158, 6, 'červen', 6, 'Norbert', '', ''),\n" +
                        "(159, 7, 'červen', 6, 'Iveta', 'Slavoj', ''),\n" +
                        "(160, 8, 'červen', 6, 'Medard', '', ''),\n" +
                        "(161, 9, 'červen', 6, 'Stanislava', '', ''),\n" +
                        "(162, 10, 'červen', 6, 'Gita', '', ''),\n" +
                        "(163, 11, 'červen', 6, 'Bruno', '', ''),\n" +
                        "(164, 12, 'červen', 6, 'Antonie', '', ''),\n" +
                        "(165, 13, 'červen', 6, 'Antonín', '', ''),\n" +
                        "(166, 14, 'červen', 6, 'Roland', '', ''),\n" +
                        "(167, 15, 'červen', 6, 'Vít', '', ''),\n" +
                        "(168, 16, 'červen', 6, 'Zbyněk', '', ''),\n" +
                        "(169, 17, 'červen', 6, 'Adolf', '', ''),\n" +
                        "(170, 18, 'červen', 6, 'Milan', '', ''),\n" +
                        "(171, 19, 'červen', 6, 'Leoš', '', ''),\n" +
                        "(172, 20, 'červen', 6, 'Květa', '', ''),\n" +
                        "(173, 21, 'červen', 6, 'Alois', '', ''),\n" +
                        "(174, 22, 'červen', 6, 'Pavla', '', ''),\n" +
                        "(175, 23, 'červen', 6, 'Zdeňka', '', ''),\n" +
                        "(176, 24, 'červen', 6, 'Jan', '', ''),\n" +
                        "(177, 25, 'červen', 6, 'Ivan', '', ''),\n" +
                        "(178, 26, 'červen', 6, 'Adriana', '', ''),\n" +
                        "(179, 27, 'červen', 6, 'Ladislav', '', ''),\n" +
                        "(180, 28, 'červen', 6, 'Lubomír', '', ''),\n" +
                        "(181, 29, 'červen', 6, 'Petr', 'Pavel', ''),\n" +
                        "(182, 30, 'červen', 6, 'Šárka', '', ''),\n" +
                        "(183, 1, 'červenec', 7, 'Jaroslava', '', ''),\n" +
                        "(184, 2, 'červenec', 7, 'Patricie', '', ''),\n" +
                        "(185, 3, 'červenec', 7, 'Radomír', '', ''),\n" +
                        "(186, 4, 'červenec', 7, 'Prokop', '', ''),\n" +
                        "(187, 5, 'červenec', 7, 'Státní svátek', 'Cyril a Metoděj', ''),\n" +
                        "(188, 6, 'červenec', 7, 'Státní svátek', 'Upálení Jana Husa', ''),\n" +
                        "(189, 7, 'červenec', 7, 'Bohuslava', '', ''),\n" +
                        "(190, 8, 'červenec', 7, 'Nora', '', ''),\n" +
                        "(191, 9, 'červenec', 7, 'Drahoslava', '', ''),\n" +
                        "(192, 10, 'červenec', 7, 'Libuše', 'Amálie', ''),\n" +
                        "(193, 11, 'červenec', 7, 'Olga', '', ''),\n" +
                        "(194, 12, 'červenec', 7, 'Bořek', '', ''),\n" +
                        "(195, 13, 'červenec', 7, 'Markéta', '', ''),\n" +
                        "(196, 14, 'červenec', 7, 'Karolína', '', ''),\n" +
                        "(197, 15, 'červenec', 7, 'Jindřich', '', ''),\n" +
                        "(198, 16, 'červenec', 7, 'Luboš', '', ''),\n" +
                        "(199, 17, 'červenec', 7, 'Martina', '', ''),\n" +
                        "(200, 18, 'červenec', 7, 'Drahomíra', '', ''),\n" +
                        "(201, 19, 'červenec', 7, 'Čeněk', '', ''),\n" +
                        "(202, 20, 'červenec', 7, 'Ilja', '', ''),\n" +
                        "(203, 21, 'červenec', 7, 'Vítězslav', '', ''),\n" +
                        "(204, 22, 'červenec', 7, 'Magdaléna', '', ''),\n" +
                        "(205, 23, 'červenec', 7, 'Libor', '', ''),\n" +
                        "(206, 24, 'červenec', 7, 'Kristýna', '', ''),\n" +
                        "(207, 25, 'červenec', 7, 'Jakub', '', ''),\n" +
                        "(208, 26, 'červenec', 7, 'Anna', '', ''),\n" +
                        "(209, 27, 'červenec', 7, 'Věroslav', '', ''),\n" +
                        "(210, 28, 'červenec', 7, 'Viktor', '', ''),\n" +
                        "(211, 29, 'červenec', 7, 'Marta', '', ''),\n" +
                        "(212, 30, 'červenec', 7, 'Bořivoj', '', ''),\n" +
                        "(213, 31, 'červenec', 7, 'Ignác', '', ''),\n" +
                        "(214, 1, 'srpen', 8, 'Oskar', '', ''),\n" +
                        "(215, 2, 'srpen', 8, 'Gustav', '', ''),\n" +
                        "(216, 3, 'srpen', 8, 'Miluše', '', ''),\n" +
                        "(217, 4, 'srpen', 8, 'Dominik', '', ''),\n" +
                        "(218, 5, 'srpen', 8, 'Kristián', '', ''),\n" +
                        "(219, 6, 'srpen', 8, 'Oldřiška', '', ''),\n" +
                        "(220, 7, 'srpen', 8, 'Lada', '', ''),\n" +
                        "(221, 8, 'srpen', 8, 'Soběslav', '', ''),\n" +
                        "(222, 9, 'srpen', 8, 'Roman', '', ''),\n" +
                        "(223, 10, 'srpen', 8, 'Vavřinec', '', ''),\n" +
                        "(224, 11, 'srpen', 8, 'Zuzana', '', ''),\n" +
                        "(225, 12, 'srpen', 8, 'Klára', '', ''),\n" +
                        "(226, 13, 'srpen', 8, 'Alena', '', ''),\n" +
                        "(227, 14, 'srpen', 8, 'Alan', '', ''),\n" +
                        "(228, 15, 'srpen', 8, 'Hana', '', ''),\n" +
                        "(229, 16, 'srpen', 8, 'Jáchym', '', ''),\n" +
                        "(230, 17, 'srpen', 8, 'Petra', '', ''),\n" +
                        "(231, 18, 'srpen', 8, 'Helena', '', ''),\n" +
                        "(232, 19, 'srpen', 8, 'Ludvík', '', ''),\n" +
                        "(233, 20, 'srpen', 8, 'Bernard', '', ''),\n" +
                        "(234, 21, 'srpen', 8, 'Johana', '', ''),\n" +
                        "(235, 22, 'srpen', 8, 'Bohuslav', '', ''),\n" +
                        "(236, 23, 'srpen', 8, 'Sandra', '', ''),\n" +
                        "(237, 24, 'srpen', 8, 'Bartoloměj', '', ''),\n" +
                        "(238, 25, 'srpen', 8, 'Radim', '', ''),\n" +
                        "(239, 26, 'srpen', 8, 'Luděk', '', ''),\n" +
                        "(240, 27, 'srpen', 8, 'Otakar', '', ''),\n" +
                        "(241, 28, 'srpen', 8, 'Augustýn', '', ''),\n" +
                        "(242, 29, 'srpen', 8, 'Evelína', '', ''),\n" +
                        "(243, 30, 'srpen', 8, 'Vladěna', '', ''),\n" +
                        "(244, 31, 'srpen', 8, 'Pavlína', '', ''),\n" +
                        "(245, 1, 'září', 9, 'Linda', 'Samuel', ''),\n" +
                        "(246, 2, 'září', 9, 'Adéla', '', ''),\n" +
                        "(247, 3, 'září', 9, 'Bronislav', '', ''),\n" +
                        "(248, 4, 'září', 9, 'Jindřiška', '', ''),\n" +
                        "(249, 5, 'září', 9, 'Boris', '', ''),\n" +
                        "(250, 6, 'září', 9, 'Boleslav', '', ''),\n" +
                        "(251, 7, 'září', 9, 'Regína', '', ''),\n" +
                        "(252, 8, 'září', 9, 'Mariana', '', ''),\n" +
                        "(253, 9, 'září', 9, 'Daniela', '', ''),\n" +
                        "(254, 10, 'září', 9, 'Irma', '', ''),\n" +
                        "(255, 11, 'září', 9, 'Denisa', '', ''),\n" +
                        "(256, 12, 'září', 9, 'Marie', '', ''),\n" +
                        "(257, 13, 'září', 9, 'Lubor', '', ''),\n" +
                        "(258, 14, 'září', 9, 'Radka', '', ''),\n" +
                        "(259, 15, 'září', 9, 'Jolana', '', ''),\n" +
                        "(260, 16, 'září', 9, 'Ludmila', '', ''),\n" +
                        "(261, 17, 'září', 9, 'Naděžda', '', ''),\n" +
                        "(262, 18, 'září', 9, 'Kryštof', '', ''),\n" +
                        "(263, 19, 'září', 9, 'Zita', '', ''),\n" +
                        "(264, 20, 'září', 9, 'Oleg', '', ''),\n" +
                        "(265, 21, 'září', 9, 'Matouš', '', ''),\n" +
                        "(266, 22, 'září', 9, 'Darina', '', ''),\n" +
                        "(267, 23, 'září', 9, 'Berta', '', ''),\n" +
                        "(268, 24, 'září', 9, 'Jaromír', '', ''),\n" +
                        "(269, 25, 'září', 9, 'Zlata', '', ''),\n" +
                        "(270, 26, 'září', 9, 'Andrea', '', ''),\n" +
                        "(271, 27, 'září', 9, 'Jonáš', '', ''),\n" +
                        "(272, 28, 'září', 9, 'Státní svátek', 'Den české státnosti', ''),\n" +
                        "(273, 29, 'září', 9, 'Michal', '', ''),\n" +
                        "(274, 30, 'září', 9, 'Jeroným', '', ''),\n" +
                        "(275, 1, 'říjen', 10, 'Igor', '', ''),\n" +
                        "(276, 2, 'říjen', 10, 'Olivie', 'Oliver', ''),\n" +
                        "(277, 3, 'říjen', 10, 'Bohumil', '', ''),\n" +
                        "(278, 4, 'říjen', 10, 'František', '', ''),\n" +
                        "(279, 5, 'říjen', 10, 'Eliška', '', ''),\n" +
                        "(280, 6, 'říjen', 10, 'Hanuš', '', ''),\n" +
                        "(281, 7, 'říjen', 10, 'Justýna', '', ''),\n" +
                        "(282, 8, 'říjen', 10, 'Věra', '', ''),\n" +
                        "(283, 9, 'říjen', 10, 'Štefan', 'Sára', ''),\n" +
                        "(284, 10, 'říjen', 10, 'Marina', '', ''),\n" +
                        "(285, 11, 'říjen', 10, 'Andrej', '', ''),\n" +
                        "(286, 12, 'říjen', 10, 'Marcel', '', ''),\n" +
                        "(287, 13, 'říjen', 10, 'Renáta', '', ''),\n" +
                        "(288, 14, 'říjen', 10, 'Agáta', '', ''),\n" +
                        "(289, 15, 'říjen', 10, 'Tereza', '', ''),\n" +
                        "(290, 16, 'říjen', 10, 'Havel', '', ''),\n" +
                        "(291, 17, 'říjen', 10, 'Hedvika', '', ''),\n" +
                        "(292, 18, 'říjen', 10, 'Lukáš', '', ''),\n" +
                        "(293, 19, 'říjen', 10, 'Michaela', '', ''),\n" +
                        "(294, 20, 'říjen', 10, 'Vendelín', '', ''),\n" +
                        "(295, 21, 'říjen', 10, 'Brigita', '', ''),\n" +
                        "(296, 22, 'říjen', 10, 'Sabina', '', ''),\n" +
                        "(297, 23, 'říjen', 10, 'Teodor', '', ''),\n" +
                        "(298, 24, 'říjen', 10, 'Nina', '', ''),\n" +
                        "(299, 25, 'říjen', 10, 'Beáta', '', ''),\n" +
                        "(300, 26, 'říjen', 10, 'Erik', '', ''),\n" +
                        "(301, 27, 'říjen', 10, 'Šarlota', 'Zoe', ''),\n" +
                        "(302, 28, 'říjen', 10, 'Státní svátek', 'Den vzniku samostatného československého státu', ''),\n" +
                        "(303, 29, 'říjen', 10, 'Silvie', '', ''),\n" +
                        "(304, 30, 'říjen', 10, 'Tadeáš', '', ''),\n" +
                        "(305, 31, 'říjen', 10, 'Štěpánka', '', ''),\n" +
                        "(306, 1, 'listopad', 11, 'Felix', '', ''),\n" +
                        "(307, 2, 'listopad', 11, 'Památka zesnulých', '', ''),\n" +
                        "(308, 3, 'listopad', 11, 'Hubert', '', ''),\n" +
                        "(309, 4, 'listopad', 11, 'Karel', '', ''),\n" +
                        "(310, 5, 'listopad', 11, 'Miriam', '', ''),\n" +
                        "(311, 6, 'listopad', 11, 'Liběna', '', ''),\n" +
                        "(312, 7, 'listopad', 11, 'Saskie', '', ''),\n" +
                        "(313, 8, 'listopad', 11, 'Bohumír', '', ''),\n" +
                        "(314, 9, 'listopad', 11, 'Bohdan', '', ''),\n" +
                        "(315, 10, 'listopad', 11, 'Evľen', '', ''),\n" +
                        "(316, 11, 'listopad', 11, 'Martin', '', ''),\n" +
                        "(317, 12, 'listopad', 11, 'Benedikt', '', ''),\n" +
                        "(318, 13, 'listopad', 11, 'Tibor', '', ''),\n" +
                        "(319, 14, 'listopad', 11, 'Sáva', '', ''),\n" +
                        "(320, 15, 'listopad', 11, 'Leopold', '', ''),\n" +
                        "(321, 16, 'listopad', 11, 'Otmar', '', ''),\n" +
                        "(322, 17, 'listopad', 11, 'Státní svátek', 'Den boje za svobodu a demokracii', 'Mahulena'),\n" +
                        "(323, 18, 'listopad', 11, 'Romana', '', ''),\n" +
                        "(324, 19, 'listopad', 11, 'Alžběta', '', ''),\n" +
                        "(325, 20, 'listopad', 11, 'Nikola', '', ''),\n" +
                        "(326, 21, 'listopad', 11, 'Albert', '', ''),\n" +
                        "(327, 22, 'listopad', 11, 'Cecílie', '', ''),\n" +
                        "(328, 23, 'listopad', 11, 'Klement', '', ''),\n" +
                        "(329, 24, 'listopad', 11, 'Emílie', '', ''),\n" +
                        "(330, 25, 'listopad', 11, 'Kateřina', '', ''),\n" +
                        "(331, 26, 'listopad', 11, 'Artur', '', ''),\n" +
                        "(332, 27, 'listopad', 11, 'Xenie', '', ''),\n" +
                        "(333, 28, 'listopad', 11, 'René', '', ''),\n" +
                        "(334, 29, 'listopad', 11, 'Zina', '', ''),\n" +
                        "(335, 30, 'listopad', 11, 'Ondřej', '', ''),\n" +
                        "(336, 1, 'prosinec', 12, 'Iva', '', ''),\n" +
                        "(337, 2, 'prosinec', 12, 'Blanka', '', ''),\n" +
                        "(338, 3, 'prosinec', 12, 'Svatoslav', '', ''),\n" +
                        "(339, 4, 'prosinec', 12, 'Barbora', '', ''),\n" +
                        "(340, 5, 'prosinec', 12, 'Jitka', '', ''),\n" +
                        "(341, 6, 'prosinec', 12, 'Mikuláš', '', ''),\n" +
                        "(342, 7, 'prosinec', 12, 'Ambrož', 'Benjamín', ''),\n" +
                        "(343, 8, 'prosinec', 12, 'Květoslava', '', ''),\n" +
                        "(344, 9, 'prosinec', 12, 'Vratislav', '', ''),\n" +
                        "(345, 10, 'prosinec', 12, 'Julie', '', ''),\n" +
                        "(346, 11, 'prosinec', 12, 'Dana', '', ''),\n" +
                        "(347, 12, 'prosinec', 12, 'Simona', '', ''),\n" +
                        "(348, 13, 'prosinec', 12, 'Lucie', '', ''),\n" +
                        "(349, 14, 'prosinec', 12, 'Lýdie', '', ''),\n" +
                        "(350, 15, 'prosinec', 12, 'Radana', 'Radan', ''),\n" +
                        "(351, 16, 'prosinec', 12, 'Albína', '', ''),\n" +
                        "(352, 17, 'prosinec', 12, 'Daniel', '', ''),\n" +
                        "(353, 18, 'prosinec', 12, 'Miloslav', '', ''),\n" +
                        "(354, 19, 'prosinec', 12, 'Ester', '', ''),\n" +
                        "(355, 20, 'prosinec', 12, 'Dagmar', '', ''),\n" +
                        "(356, 21, 'prosinec', 12, 'Natálie', '', ''),\n" +
                        "(357, 22, 'prosinec', 12, 'Šimon', '', ''),\n" +
                        "(358, 23, 'prosinec', 12, 'Vlasta', '', ''),\n" +
                        "(359, 24, 'prosinec', 12, 'Štědrý den', 'Adam', 'Eva'),\n" +
                        "(360, 25, 'prosinec', 12, '1. svátek vánoční', 'Boží hod vánoční', ''),\n" +
                        "(361, 26, 'prosinec', 12, '2. svátek vánoční', 'Štěpán', ''),\n" +
                        "(362, 27, 'prosinec', 12, 'Žaneta', '', ''),\n" +
                        "(363, 28, 'prosinec', 12, 'Bohumila', '', ''),\n" +
                        "(364, 29, 'prosinec', 12, 'Judita', '', ''),\n" +
                        "(365, 30, 'prosinec', 12, 'David', '', ''),\n" +
                        "(366, 31, 'prosinec', 12, 'Silvestr', '', '');\n");
//        db.execSQL(
//                "ALTER TABLE `days`\n" +
//                        "  ADD PRIMARY KEY (`id`);\n");
//        db.execSQL(
//                "ALTER TABLE `days`\n" +
//                "  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=367;\n" +
//                "/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;\n" +
//                "/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;\n" +
//                "/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;\n");
    }

    public boolean is_leap_year() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        boolean isLeapYear;

        // divisible by 4
        isLeapYear = (year % 4 == 0);

        // divisible by 4 and not 100
        isLeapYear = isLeapYear && (year % 100 != 0);

        // divisible by 4 and not 100 unless divisible by 400
        isLeapYear = isLeapYear || (year % 400 == 0);

        return isLeapYear;
    }

    public Person getPerson(int index) {
        Cursor c = db.rawQuery("SELECT id,name,surname,email,nameday,birthday,day_of_year_birthday,phone,address,other FROM contacts WHERE id = " + index + "  LIMIT 0,1;", null);

        if (c.getCount() == 1) {
            c.moveToFirst();
            int id = c.getInt(0);
            String name = c.getString(1);
            String surname = c.getString(2);
            String email = c.getString(3);
            String nameday = c.getString(4);
            Date birthday = java.sql.Date.valueOf(c.getString(5));
            int dayOfYearBirthday = c.getInt(6);

            String phone = c.getString(7);
            String address = c.getString(8);
            String other = c.getString(9);
            return new Person(id, name, surname, email, nameday, birthday, Person.BIRTHDAY, dayOfYearBirthday,phone,address,other);
        }
        return null;
//        Person person = new Person(0, "Pavel", "Novotný", "můj email", "04.08.", new Date());
//        return person;
    }

}


