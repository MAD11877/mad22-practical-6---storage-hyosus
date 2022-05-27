package sg.edu.np.mad.practical_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String thisUname = null;
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView header = findViewById(R.id.header);
        TextView desc = findViewById(R.id.description);
        Button fllw = findViewById(R.id.followBtn);

        Intent receivingEnd = getIntent();
        DBAdapter dba = new DBAdapter(this);
        User u = dba.getSingleUser(receivingEnd.getStringExtra("uName"));
        thisUname = u.name;

        header.setText(u.name);
        desc.setText(u.description);
        if (u.isFollowed()) {
            fllw.setText("Unfollow");
        }
        else {
            fllw.setText("Follow");
        }

        Button message = findViewById(R.id.msgBtn);

        message.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivityIntent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(mainActivityIntent);
            }
        }));

        // Shared pref & SQL

    }

    public void follow(View view){
        DBAdapter dba = new DBAdapter(this);
        Button fllw = findViewById(R.id.followBtn);
        User u = dba.getSingleUser(thisUname);
        switch(String.valueOf(u.isFollowed())) {
            case "true":
                u.followed = false;
                fllw.setText("Unfollow");
                Toast.makeText(getApplicationContext(), "Followed "+u.name,Toast.LENGTH_SHORT).show();
                break;

            case "false":
                u.followed = true;
                fllw.setText("Follow");
                Toast.makeText(getApplicationContext(),"Unfollowed "+u.name, Toast.LENGTH_SHORT).show();
                break;
        }
        dba.updateUser(u.name, u);
    }
}

