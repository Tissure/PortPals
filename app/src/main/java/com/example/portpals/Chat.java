package com.example.portpals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.portpals.chat.ChatAdapter;
import com.example.portpals.chat.ChatList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Chat extends AppCompatActivity {

    public static final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public static final DatabaseReference databaseReference = firebaseDatabase.getReference();

    private final List<ChatList> chatLists = new ArrayList<>();
    private String chatKey;
    private RecyclerView chattingRecyclerView;
    private ChatAdapter chatAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView nameTV = findViewById(R.id.name);
        final EditText messageEditText = findViewById(R.id.messageEditTxt);
        final ImageView sendBtn = findViewById(R.id.sendBtn);



        chattingRecyclerView = findViewById(R.id.chattingRecyclerView);

        // get data from messages adapter class
       // final String getName = getIntent().getStringExtra("name");

        chatKey = getIntent().getStringExtra("chat_key");

        final String UID = firebaseAuth.getCurrentUser().getUid();

        nameTV.setText("AIRPORT CHAT");

        chattingRecyclerView.setHasFixedSize(true);
        chattingRecyclerView.setLayoutManager(new LinearLayoutManager(Chat.this));

        chatAdapter = new ChatAdapter(chatLists, Chat.this);
        chattingRecyclerView.setAdapter(chatAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.hasChild("chat")) {
                    if(snapshot.child("chat").child("global").hasChild("messages")) {
                        chatLists.clear();
                        for(DataSnapshot messagesSnapshot : snapshot.child("chat").child("global").child("messages").getChildren()){
                            if(messagesSnapshot.hasChild("msg")) {

                                final String messageTimeStamps = messagesSnapshot.getKey();

                                final String getMsg = messagesSnapshot.child("msg").getValue(String.class);
                                final String getUID = messagesSnapshot.child("UID").getValue(String.class);
                                final String getDisplayName = snapshot.child("Users").child(UID).child("displayName").getValue(String.class);

                                Timestamp timestamp = new Timestamp(Long.parseLong(messageTimeStamps));
                                Date date = new Date(timestamp.getTime());
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm aa", Locale.getDefault());

                                ChatList chatList = new ChatList(getUID, getDisplayName,getMsg, simpleDateFormat.format(date), simpleTimeFormat.format(date));
                                chatLists.add(chatList);
                                chatAdapter.updateChatList(chatLists);
                            }
                        }
                    }
                }
                chattingRecyclerView.scrollToPosition(chatLists.size()-1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Crashed while reading chat", error.getMessage());
            }
        });


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String getTxtMessage = messageEditText.getText().toString();
                final String currentTimestamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);


                databaseReference.child("chat").child("global").child("messages").child(currentTimestamp).child("msg").setValue(getTxtMessage);
                databaseReference.child("chat").child("global").child("messages").child(currentTimestamp).child("UID").setValue(UID);

                chattingRecyclerView.scrollToPosition(chatLists.size()-1);
                // clear edit Text
                messageEditText.setText("");
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}