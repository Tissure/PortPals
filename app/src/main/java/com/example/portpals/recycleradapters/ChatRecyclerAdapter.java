package com.example.portpals.recycleradapters;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portpals.MainActivity;
import com.example.portpals.R;
import com.example.portpals.models.Event;
import com.example.portpals.models.User;
import com.example.portpals.util.ClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.MyViewHolder> {

    private ArrayList<Event> eventList;
    private ClickListener listener;

    private static FirebaseStorage storage = FirebaseStorage.getInstance();
    private static StorageReference storageReference = storage.getReference();

    public ChatRecyclerAdapter(ArrayList<Event> eventList) {
        this.eventList = eventList;
        this.listener = null;
    }

    @NonNull
    @Override
    public ChatRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRecyclerAdapter.MyViewHolder holder, int position) {
        String titleText = eventList.get(position).getName();
        User user = eventList.get(position).getUser();
        String userName = "Guest";
        if (user != null) {
            userName = user.getDisplayName();
            System.out.println(user);
        } else {
            System.out.println("User is null!");
        }
        String capacity = eventList.get(position).getOccupants()+ "/" + String.valueOf(eventList.get(position).getCapacity());
        //int imgId = eventList.get(position).getProfile_picture();
        //int terminalNum = chatRoomInfoList.get(position).getTerminalNum();
        String roomTime = eventList.get(position).getUpTime();
        String description = eventList.get(position).getDescription();

        holder.titleText.setText(titleText);
        holder.profileName.setText(userName);
        //holder.profile_picture.setImageResource(imgId);
        holder.capacity.setText(capacity);
        holder.roomTime.setText(roomTime);
        //holder.terminalNum.setText("Terminal " + terminalNum);
        holder.description.setText(description);

        // set the profile picture if it exists
        if (user != null) {
            StorageReference profilePictureRef = storageReference.child("images/" + user.getProfilePictureKey());
            if (profilePictureRef != null) {
                profilePictureRef.getDownloadUrl().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Picasso.get().load(task.getResult()).into(holder.profile_picture);
                    } else {
                        System.out.println("Failed to get profile picture and put it on the event");
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView titleText;
        private final TextView profileName;
        private final TextView capacity;
        private final TextView roomTime;
        private final TextView description;

        private final ImageView profile_picture;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title);
            profileName = itemView.findViewById(R.id.profile_name);
            profile_picture = itemView.findViewById(R.id.profile_picture);
            TextView terminalNum = itemView.findViewById(R.id.terminal);
            roomTime = itemView.findViewById(R.id.roomTime);
            capacity = itemView.findViewById(R.id.participants);
            description = itemView.findViewById(R.id.eventDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onClick(view, getAdapterPosition());
            }
        }
    }

}
