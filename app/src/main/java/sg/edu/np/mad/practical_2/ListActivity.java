package sg.edu.np.mad.practical_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DBAdapter db = new DBAdapter(this);

        //  Create list of 20 User objects
        ArrayList<User> userList = new ArrayList<User>();
//        for (int i=1; i<21; i++){
//            User u = new User();
//            Integer id = 1;
//            int randomint = new Random().nextInt(823172316);
//            int randomintDesc = new Random().nextInt(39817316);
//            boolean randBool = new Random().nextBoolean();
//            id = i;
//
//            u.name = "Name" +String.valueOf(randomint);
//            u.description = " Description" +String.valueOf(randomintDesc);
//            u.id = id;
//            u.followed = randBool;
//            db.addUser(u);
//        }

        userList = db.getUser();

        //  Create Recycler View
        RecyclerView userListRecyclerView = findViewById(R.id.userListRV);
        UserAdapter adapter = new UserAdapter(userList);
        LinearLayoutManager lm = new LinearLayoutManager(this);

        userListRecyclerView.setLayoutManager(lm);
        userListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        userListRecyclerView.setAdapter(adapter);

        // shared pref
        SharedPreferences pref=getSharedPreferences("P05",MODE_PRIVATE);
        String text=pref.getString("Name","Not Found !!");
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Name", "hello world");
        editor.apply();

    }



}