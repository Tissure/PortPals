package com.example.portpals.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.portpals.R;
import com.example.portpals.models.ChatRoomInfo;

public class ChatRoomFragment extends Fragment {

    private ChatRoomInfo info;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            System.out.println("GETTING ARGUMENTS");
            info = (ChatRoomInfo) getArguments().getParcelable("chatRoomInfo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate the layout for this fragment
        View chatView = inflater.inflate(R.layout.fragment_chat, container, false);

        return chatView;
    }

    @Override
    public void onStart() {

        // set the text views information to the information about the chat
        super.onStart();
        TextView chatTime = getActivity().findViewById(R.id.chatTime);
        chatTime.setText(info.getRoomTime() + " minutes remaining");
    }

}
