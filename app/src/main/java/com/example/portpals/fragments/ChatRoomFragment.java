package com.example.portpals.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portpals.R;
import com.example.portpals.chat.ChatAdapter;
import com.example.portpals.chat.ChatList;
import com.example.portpals.models.ChatRoomInfo;
import com.example.portpals.models.Event;
import com.example.portpals.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomFragment extends Fragment {

    public static final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public static final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private final List<ChatList> chatLists = new ArrayList<>();
    private String chatKey;
    String getUserMobile = "";
    private RecyclerView chattingRecyclerView;
    private ChatAdapter chatAdapter;
    private boolean loadingFirstTime = true;


    private Event info;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            System.out.println("GETTING ARGUMENTS");
            info = (Event) getArguments().getParcelable("eventInfo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate the layout for this fragment
        View chatView = inflater.inflate(R.layout.fragment_chat, container, false);
        Toast.makeText(getActivity(), "All Field REQUIRED", Toast.LENGTH_SHORT).show();


//        Button backBtn = chatView.findViewById(R.id.backBtn);
//        backBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFragmentManager().beginTransaction().remove(ActivityFragment.this).commit();
//            }
//        });
        return chatView;
    }

    @Override
    public void onStart() {
        super.onStart();

        // set the text views information to the information about the chat
        TextView nameTextView = getView().findViewById(R.id.name);
        User user = info.getUser();
        if (user != null) {
            nameTextView.setText(user.getDisplayName());
        } else {
            nameTextView.setText("Guest");
        }
    }


}
