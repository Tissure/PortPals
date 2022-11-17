package com.example.portpals.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.portpals.R;
//import com.example.portpals.Chat;
import com.example.portpals.recycleradapters.ChatRecyclerAdapter;
import com.example.portpals.models.ChatRoomInfo;
import com.example.portpals.util.ClickListener;

import java.util.ArrayList;

public class ChatFragment extends Fragment implements ClickListener {

    private final int[] profilePics = {R.drawable.chad, R.drawable.crazy, R.drawable.vibe};
    private ArrayList<ChatRoomInfo> chatRoomInfoList;

    private RecyclerView recyclerView;

    private void setChatRoomInfo() {
        chatRoomInfoList.add(new ChatRoomInfo("PogRoom","Pog-man", "2/4", 60, 12, profilePics[0]));
        chatRoomInfoList.add(new ChatRoomInfo("Toxic","Pog-man", "3/4", 45, 16, profilePics[1]));
        chatRoomInfoList.add(new ChatRoomInfo("War Room","Pog-man", "4/4", 20, 17, profilePics[2]));

        chatRoomInfoList.add(new ChatRoomInfo("Legends only","Legend", "2/4", 12, 12, profilePics[0]));
        chatRoomInfoList.add(new ChatRoomInfo("Living like a chad","Chad", "3/4", 120, 16, profilePics[1]));
        chatRoomInfoList.add(new ChatRoomInfo("How to be cool","GigaChad", "4/4", 15, 17, profilePics[2]));
    }

    private void setChatRoomAdapter() {
        ChatRecyclerAdapter adapter = new ChatRecyclerAdapter(chatRoomInfoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter.setClickListener(this);
        layoutManager.canScrollVertically();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

//    private void setBtn() {
//        Button joinBtn = getActivity().findViewById(R.id.joinChatBtn);
//
//        joinBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "blehh", Toast.LENGTH_LONG);
//                Intent intent = new Intent(getContext(), Chat.class);
//                getActivity().startActivity(intent);
//            }
//        });
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // initialize the chat room info with default values for the time being
        chatRoomInfoList = new ArrayList<>();
        setChatRoomInfo();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chatrooms, container, false);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // fill the recycler view
        recyclerView = getActivity().findViewById(R.id.chatRecyclerView);




        setChatRoomAdapter();




    }

    @Override
    public void onClick(View view, int position) {
//        ChatRoomFragment chatRoomClickedOn = new ChatRoomFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("chatRoomInfo", chatRoomInfoList.get(position));
//        chatRoomClickedOn.setArguments(bundle);
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, chatRoomClickedOn).commit();
    }

}