package com.example.portpals.recycleradapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portpals.R;
import com.example.portpals.models.Event;
import com.example.portpals.models.User;
import com.example.portpals.util.ClickListener;

import java.util.ArrayList;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.MyViewHolder> {

    private ArrayList<Event> eventList;
    private ClickListener listener;

    public ChatRecyclerAdapter(ArrayList<Event> eventList) {
        this.eventList = eventList;
        this.listener = null;
    }

    @NonNull
    @Override
    public ChatRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list_item, parent, false);
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
        int capacity = eventList.get(position).getCapacity();
        //int imgId = eventList.get(position).getProfile_picture();
        //int terminalNum = chatRoomInfoList.get(position).getTerminalNum();
        String roomTime = eventList.get(position).getUpTime();
        String description = eventList.get(position).getDescription();

        holder.titleText.setText(titleText);
        holder.profileName.setText(userName);
        //holder.profile_picture.setImageResource(imgId);
        holder.capacity.setText(String.valueOf(capacity));
        holder.roomTime.setText(roomTime);
        //holder.terminalNum.setText("Terminal " + terminalNum);
        holder.description.setText(description);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleText;
        private TextView profileName;
        private TextView capacity;
        private TextView roomTime;
        private TextView terminalNum;
        private TextView description;

        private ImageView profile_picture;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title);
            profileName = itemView.findViewById(R.id.profile_name);
            profile_picture = itemView.findViewById(R.id.profile_picture);
            terminalNum = itemView.findViewById(R.id.terminal);
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
