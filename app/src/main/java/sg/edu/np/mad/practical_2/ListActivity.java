package sg.edu.np.mad.practical_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
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

        //  Create list of 20 User objects
        ArrayList<User> userList = new ArrayList<User>();
        for (int i=0; i<20; i++){
            int randomint = new Random().nextInt(823172316);
            int randomintDesc = new Random().nextInt(39817316);
            boolean randBool = new Random().nextBoolean();
            userList.add(new User("Name"+String.valueOf(randomint), "Description "+String.valueOf(randomintDesc), 0, randBool));
        }


        //  Create Recycler View
        RecyclerView userListRecyclerView = findViewById(R.id.userListRV);
        UserAdapter adapter = new UserAdapter(userList);
        LinearLayoutManager lm = new LinearLayoutManager(this);

        userListRecyclerView.setLayoutManager(lm);
        userListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        userListRecyclerView.setAdapter(adapter);

    }



}