package com.example.portpals.recycleradapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portpals.R;
import com.example.portpals.models.ChatRoomInfo;
import com.example.portpals.util.ClickListener;

import java.util.ArrayList;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.MyViewHolder> {

    private ArrayList<ChatRoomInfo> chatRoomInfoList;
    private ClickListener listener;

    public ChatRecyclerAdapter(ArrayList<ChatRoomInfo> chatRoomInfoList) {
        this.chatRoomInfoList = chatRoomInfoList;
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
        String titleText = chatRoomInfoList.get(position).getTitle();
        String userName = chatRoomInfoList.get(position).getUsername();
        String participants = chatRoomInfoList.get(position).getParticipants();
        int imgId = chatRoomInfoList.get(position).getProfile_picture();
        int terminalNum = chatRoomInfoList.get(position).getTerminalNum();
        int roomTime = chatRoomInfoList.get(position).getRoomTime();

        holder.titleText.setText(titleText);
        holder.profileName.setText(userName);
        holder.profile_picture.setImageResource(imgId);
        holder.participants.setText(participants);
        holder.roomTime.setText(roomTime + " minutes");
        holder.terminalNum.setText("Terminal " + terminalNum);
    }

    @Override
    public int getItemCount() {
        return chatRoomInfoList.size();
    }

    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleText;
        private TextView profileName;
        private TextView participants;
        private TextView roomTime;
        private TextView terminalNum;

        private ImageView profile_picture;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title);
            profileName = itemView.findViewById(R.id.profile_name);
            profile_picture = itemView.findViewById(R.id.profile_picture);
            terminalNum = itemView.findViewById(R.id.terminal);
            roomTime = itemView.findViewById(R.id.roomTime);
            participants = itemView.findViewById(R.id.participants);
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
