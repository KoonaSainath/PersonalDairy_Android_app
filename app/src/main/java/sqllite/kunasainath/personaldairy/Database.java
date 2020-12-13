package sqllite.kunasainath.personaldairy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Dairy database";
    public static final int VERSION = 1;
    private static final String TABLE_NAME = "Entry";
    private static final String ENTRY_ID = "Id";
    private static final String TITLE = "Title";
    private static final String DESCRIPTION = "Descrption";
    private static final String DATE = "DATE";
    private static final String TIME = "TIME";


    private String query;

    public Database(Context context, String DATABASE_NAME, SQLiteDatabase.CursorFactory cursorFactory, int version){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database = SQLiteDatabase.create(null);

        //CREATE TABLE
        //create table dairy(id integer primary key autoincrement, date text, time text, title text, description text)
        query = "create table " + TABLE_NAME + "(" + ENTRY_ID + " integer primary key autoincrement, " + DATE + " text, " + TIME + " text, " + TITLE + " varchar, " + DESCRIPTION + " text)";
        database.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onCreate(sqLiteDatabase);
    }

    public void saveEntry(Entry entry){
        //INSERT VALUES
        //insert into dairy values(0, entry.getDate, entry.getTime, entry.getTitle, entry.getDescription);
        String title = entry.getTitle().replace("\'","").replace("\"","");
        String description = entry.getDescription().replace("\'","").replace("\"","");
        query = "insert into " + TABLE_NAME + " values(null, '" + entry.getDate() + "', '" + entry.getTime() + "', '" + title + "', '" + description + "')";

        SQLiteDatabase database = getWritableDatabase();

        database.execSQL(query);

        database.close();
    }

    public ArrayList<Entry> getAllEntries(){
        SQLiteDatabase database = getWritableDatabase();
        ArrayList<Entry> entries = new ArrayList<Entry>();

        //SELECT QUERY
        //select * from dairy;
        query = "select * from " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        while(cursor.moveToNext()){
            int id;
            String date, time, title, description;

            id = cursor.getInt(0);
            date = cursor.getString(1);
            time = cursor.getString(2);
            title = cursor.getString(3);
            description = cursor.getString(4);

            Entry entry = new Entry(id, date, time, title, description);

            entries.add(entry);
        }
        database.close();
        return entries;
    }

    public void deleteEntry(int id){
        //REMOVE A ROW
        //delete from dairy where entry_id = id;
        query = "delete from " + TABLE_NAME + " where " + ENTRY_ID + " = " + id ;
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(query);
        database.close();
    }

    public void updateEntry(Entry entry){
        //UPDATE RECORD
        //update dairy set title = newtitle, des = new des where id = entry.getId

        query = "update " + TABLE_NAME + " set " + TITLE + " = '" + entry.getTitle() + "', " + DESCRIPTION + " = '" + entry.getDescription() + "'where " + ENTRY_ID + " = " + entry.getId();
        SQLiteDatabase database  = getWritableDatabase();
        database.execSQL(query);
        database.close();
    }
}
