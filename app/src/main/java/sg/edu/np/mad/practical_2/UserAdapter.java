package sg.edu.np.mad.practical_2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    ArrayList<User> userList;

    public UserAdapter(ArrayList<User> input){
        userList = input;
    }

    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_view_holder, parent, false);
        return new UserViewHolder(item);
    }

    public void onBindViewHolder(UserViewHolder holder, int position) {
        User u = userList.get(position);
        holder.name.setText(u.name);
        holder.desc.setText(u.description);

         holder.img.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("Profile");
                builder.setMessage(u.name);
                builder.setCancelable(true);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int id) {
                        Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);
                        mainActivityIntent.putExtra("uName",u.name);
                        mainActivityIntent.putExtra("uDesc",u.description);
                        mainActivityIntent.putExtra("uFollowed",u.isFollowed());
                        Log.v("peepoo",Boolean.toString(u.isFollowed()));
                        view.getContext().startActivity(mainActivityIntent);
                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
//                        ((Activity)view.getContext()).finish();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
             }
         });
         if (u.name.substring(u.name.length()-1).equals("7")) {
             Log.v("peepoo",u.name.substring(u.name.length()-1) + String.format(" MEOW SHUD BE HERER %s",position));
             holder.meowImg.setVisibility(View.VISIBLE);

         }
         else{
             holder.meowImg.setVisibility(View.GONE);
         }

    }

    public int getItemCount() {
        return userList.size();
    }
}
