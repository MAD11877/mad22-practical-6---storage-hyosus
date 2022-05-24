package sg.edu.np.mad.practical_2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class UserViewHolder extends RecyclerView.ViewHolder {
    ImageView img;
    TextView name;
    TextView desc;
    ImageView meowImg;

    public UserViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.nameVH);
        desc = itemView.findViewById(R.id.descVH);
        img = itemView.findViewById(R.id.imgVH);
        meowImg = itemView.findViewById(R.id.meowImg);
    }
}
