package sg.edu.np.mad.practical_2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBAdapter extends SQLiteOpenHelper {
    List<User> userList = new ArrayList();

    public DBAdapter(Context c)
    {
        super(c, "MAD.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USER (Name TEXT, Description TEXT, id INT, Followed INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS USER");
        onCreate(db);
    }

    public void addUser(User u)
    {
        // INSERT INTO USER VALUES("NAME", "DESCRIPTION", "ID", "FOLLOWED")
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO USER VALUES(\"" + u.name + "\", \"" + u.description + "\", \"" + u.id + "\", \"" + u.followed +"\")");
        db.close();
    }

    public ArrayList<User> getUser()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<User> list = new ArrayList<>();

        // SELECT * FROM USER;
        Cursor cursor = db.rawQuery("SELECT * FROM USER;",null);
        while (cursor.moveToNext())
        {
            User u = new User();
            u.name = cursor.getString(0);
            u.description = cursor.getString(1);
            u.id = Integer.parseInt(cursor.getString(2));
            u.followed = Boolean.parseBoolean(cursor.getString(3));
            list.add(u);
        }
        db.close();
        return list;
    }

    public User getSingleUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM USER WHERE Name = \"" + username + "\"",
                null
        );
        if (cursor.moveToFirst()){
            boolean value = cursor.getInt(3) > 0;
            User u = new User(
                cursor.getString(0),
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                value
            );
            return u;
        }
        db.close();
        return null;
    }

    public void updateUser(String username, User newuser) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(
                "UPDATE USER SET " +
                    "Name = \"" + newuser.name + "\"," +
                    "Description = \"" + newuser.description + "\"," +
                    "id =  " + newuser.id + "," +
                    "Followed =  " + String.valueOf((newuser.followed)) +
                    " WHERE Name = \"" + username + "\""
        );
        db.close();
    }

    public boolean deleteUser(String username) {
        boolean result = false;

        String query = "SELECT * FROM USER WHERE Name = \"" + username + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User u = new User();

        if (cursor.moveToFirst()) {
            u.setId(Integer.parseInt(cursor.getString(0)));
            db.delete("USER", "id = ?", new String[] {String.valueOf(u.getId())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}
