package com.example.portpals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.portpals.chat.ChatAdapter;
import com.example.portpals.chat.ChatList;
import com.example.portpals.fragments.ChatFragment;
import com.example.portpals.fragments.EventFragment;
import com.example.portpals.fragments.GlobalChat;
import com.example.portpals.fragments.HomeFragment;
import com.example.portpals.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


   // private final List<MessagesList> messagesLists = new ArrayList<>();
    private String mobile;
    private String email;
    private String name;

    private int unseenMessages = 0;
    private String lastMessage = "";

    private String chatKey = "";

    private boolean dataSet = false;
    private RecyclerView messagesRecyclerView;
    //private MessagesAdapter messagesAdapter;


//    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://chat-95a1f-default-rtdb.firebaseio.com/");

    private final List<ChatList> chatLists = new ArrayList<>();
    //private String chatKey;
    String getUserMobile = "";
    private RecyclerView chattingRecyclerView;
    private ChatAdapter chatAdapter;
    private boolean loadingFirstTime = true;


//    final ImageView backBtn = findViewById(R.id.backBtn);
//    final TextView nameTV = findViewById((R.id.name);
//    final EditText messageEditText = findViewById((R.id.messageEditTxt);
//    final ImageView profilePic  = findViewById((R.id.profilePic);
//    final ImageView sendBtn = findViewById((R.id.sendBtn);




    public static final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public static final DatabaseReference databaseReference = firebaseDatabase.getReference();

    private BottomNavigationView bottomNavBar;
    private Map<Integer, Fragment> fragmentMap;

    public MainActivity() {
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.home, new HomeFragment());
        fragmentMap.put(R.id.chat, new ChatFragment());
        fragmentMap.put(R.id.globalChat, new GlobalChat());
        fragmentMap.put(R.id.event, new EventFragment());
        fragmentMap.put(R.id.profile, new ProfileFragment());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            System.out.println("user signed in!");
            bottomNavBar = findViewById(R.id.bottomNavBar);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentMap.get(R.id.home)).commit();

            bottomNavBar.setOnItemSelectedListener(item -> {

//                Toast.makeText(this, i, Toast.LENGTH_LONG).show();
                    Fragment f = fragmentMap.get(item.getItemId());
//                Toast.makeText(this, " " , Toast.LENGTH_LONG).show();
                if (f == null) {
                    return false;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, f).commit();


                return true;
            });
        } else {
            System.out.println("user not signed in!");
            Intent goSignIn = new Intent(this, SignInActivity.class);
            startActivity(goSignIn);
        }







//        // get data from messages adapter class
//        final String getName = getIntent().getStringExtra("name");
//        final String getProfilePic  = getIntent().getStringExtra("profile_pic");
//        chatKey = getIntent().getStringExtra("chat_key");
//        final String getMobile = getIntent().getStringExtra("mobile");
//
//        //  get user mobile from memeory
//        getUserMobile = MemoryData.getData(MainActivity.this);
//
//        nameTV.setText(getName);
//        //Picasso.get().load(getProfilePic).into(profilePic)
//
//        chattingRecyclerView.setHasFixedSize(true);
//        chattingRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//
//        chatAdapter = new ChatAdapter(chatLists, MainActivity.this);
//        chattingRecyclerView.setAdapter(chatAdapter);

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if(chatKey.isEmpty()) {
//
//                    //generate chat key by default chat key is 1
//                    chatKey = "1";
//
//                    if (snapshot.hasChild("chat")) {
//                        chatKey = String.valueOf(snapshot.child("chat").getChildrenCount() + 1);
//                    }
//                }
//
//                if(snapshot.hasChild("chat")) {
//                    if(snapshot.child("chat").child(chatKey).hasChild("messages")) {
//                        chatLists.clear();
//                        for(DataSnapshot messagesSnapshot : snapshot.child("chat").child(chatKey).child("messages").getChildren()){
//                            if(messagesSnapshot.hasChild("msg") && messagesSnapshot.hasChild("mobile")) {
//
//                                final String messageTimeStamps = messagesSnapshot.getKey();
//                                final String getMobile = messagesSnapshot.child("mobile").getValue(String.class);
//                                final String getMsg = messagesSnapshot.child("msg").getValue(String.class);
//
//                                Timestamp timestamp = new Timestamp(Long.parseLong(messageTimeStamps));
//                                Date date = new Date(timestamp.getTime());
//                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
//                                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm aa", Locale.getDefault());
//
//                                ChatList chatList = new ChatList(getMobile, getName, getMsg, simpleDateFormat.format(date), simpleTimeFormat.format(date));
//                                chatLists.add(chatList);
//
//                                if(loadingFirstTime || Long.parseLong(messageTimeStamps) > Long.parseLong(MemoryData.getLastMsgTS(MainActivity.this, chatKey))) {
//
//                                    loadingFirstTime = false;
//
//                                    MemoryData.saveLastMsgTS(messageTimeStamps,chatKey,MainActivity.this);
//                                    chatAdapter.updateChatList(chatLists);
//
//
//                                    chattingRecyclerView.scrollToPosition(chatLists.size()-1);
//                                }
//
//                            }
//                        }
//                    }
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


//        sendBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String getTxtMessage = messageEditText.getText().toString();
//
//                //get current timestamps
//                final String currentTimestamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);
//
//
//
//                databaseReference.child("chat").child(chatKey).child("user_1").setValue(getUserMobile);
//                databaseReference.child("chat").child(chatKey).child("user_2").setValue(getUserMobile);
//                databaseReference.child("chat").child(chatKey).child("messages").child(currentTimestamp).child("msg").setValue(getTxtMessage);
//                databaseReference.child("chat").child(chatKey).child("messages").child(currentTimestamp).child("mobile").setValue(getUserMobile);
//
//                // clear edit Text
//                messageEditText.setText("");
//            }
//        });
//
//        backBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

    }




//
//        ImageView userProfilePic = findViewById(R.id.userProfilePic);
//        messagesRecyclerView = findViewById(R.id.messagesRecyclerView);
//
//        // get intent data from Register.class activity
//        mobile = getIntent().getStringExtra("mobile");
//        email = getIntent().getStringExtra("emil");
//        name = getIntent().getStringExtra("name");
//
//        messagesRecyclerView.setHasFixedSize(true);
//        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        // set adapter to recyclerview
//        messagesAdapter = new MessagesAdapter(messagesLists, MainActivity.this);
//
//        messagesRecyclerView.setAdapter(messagesAdapter);
//
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                messagesLists.clear();
//                unseenMessages = 0;
//                lastMessage = "";
//                chatKey = "";
//
//                for(DataSnapshot dataSnapshot : snapshot.child("users").getChildren()) {
//
//                    final String getMobile = dataSnapshot.getKey();
//
//                    dataSet = false;
//
//                    if(!getMobile.equals(mobile)) {
//                        final String getName = dataSnapshot.child("name").getValue(String.class);
//                        final String getProfilePic = dataSnapshot.child("profile_pic").getValue(String.class);
//
//                        databaseReference.child("chat").addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                                int getChatCounts = (int)snapshot.getChildrenCount();
//
//                                if(getChatCounts > 0) {
//                                    for(DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
//
//                                        final String getKey = dataSnapshot1.getKey();
//                                        chatKey = getKey;
//
//                                        if(dataSnapshot1.hasChild("user_1") && dataSnapshot1.hasChild("user_2") && dataSnapshot1.hasChild("messages")) {
//                                            final String getUserOne = dataSnapshot1.child("user_1").getValue(String.class);
//                                            final String getUserTwo = dataSnapshot1.child("user_2").getValue(String.class);
//
//                                            if((getUserOne.equals(getMobile) && getUserTwo.equals(mobile)) || getUserOne.equals(mobile) && getUserTwo.equals(getMobile)) {
//
//                                                for(DataSnapshot chatDataSnapshot : dataSnapshot1.child("messages").getChildren()) {
//
//                                                    final long getMessageKey = Long.parseLong(chatDataSnapshot.getKey());
//                                                    final long getLastSeenMessage = Long.parseLong(MemoryData.getLastMsgTS(MainActivity.this, getKey));
//
//                                                    lastMessage = chatDataSnapshot.child("msg").getValue(String.class);
//
//                                                    if(getMessageKey > getLastSeenMessage) {
//                                                        unseenMessages++;
//                                                    }
//
//                                                }
//                                            }
//                                        }
//
//
//                                    }
//                                }
//
//                                if(!dataSet) {
//                                    dataSet = true;
//                                    MessagesList messagesList = new MessagesList(getName, getMobile, "", getProfilePic, unseenMessages, chatKey);
//                                    // arraylist of messageList
//                                    messagesLists.add(messagesList);
//                                    messagesAdapter.updateData(messagesLists);
//                                }
//
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//
//
//                    }
//                }
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//
//    }
//


//    }

}