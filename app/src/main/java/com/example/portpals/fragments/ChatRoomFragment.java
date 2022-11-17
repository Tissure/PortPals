//package com.example.portpals.fragments;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.portpals.R;
//import com.example.portpals.chat.ChatAdapter;
//import com.example.portpals.chat.ChatList;
//import com.example.portpals.models.ChatRoomInfo;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ChatRoomFragment extends Fragment {
//
//    public static final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//    public static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    public static final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//
//    private final List<ChatList> chatLists = new ArrayList<>();
//    private String chatKey;
//    String getUserMobile = "";
//    private RecyclerView chattingRecyclerView;
//    private ChatAdapter chatAdapter;
//    private boolean loadingFirstTime = true;
//
//
//    private ChatRoomInfo info;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            System.out.println("GETTING ARGUMENTS");
//            info = (ChatRoomInfo) getArguments().getParcelable("chatRoomInfo");
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // inflate the layout for this fragment
//        View chatView = inflater.inflate(R.layout.fragment_chat, container, false);
//        Toast.makeText(getActivity(), "All Field REQUIRED", Toast.LENGTH_SHORT).show();
//
//
//        final ImageView backBtn = getActivity().findViewById(R.id.backBtn);
//        final TextView roomName = getActivity().findViewById(R.id.name);
//        final EditText messageEditText = getActivity().findViewById(R.id.messageEditTxt);
////        final ImageView profilePic  = getActivity().findViewById(R.id.profilePic);
//        final ImageView sendBtn = getActivity().findViewById(R.id.sendBtn);
////
////        chattingRecyclerView = getActivity().findViewById(R.id.chattingRecyclerView);
////
////        // get data from messages adapter class
////        final String getName = getActivity().getIntent().getStringExtra("name");
////        final String getProfilePic  = getActivity().getIntent().getStringExtra("profile_pic");
////        chatKey = getActivity().getIntent().getStringExtra("chat_key");
////        final String getMobile = getActivity().getIntent().getStringExtra("mobile");
////
////
////        chattingRecyclerView.setHasFixedSize(true);
//       // chattingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
////
//       // chatAdapter = new ChatAdapter(chatLists, getActivity());
//        //chattingRecyclerView.setAdapter(chatAdapter);
//
////        databaseReference.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////
////                if(chatKey.isEmpty()) {
////
////                    //generate chat key by default chat key is 1
////                    chatKey = "1";
////
////                    if (snapshot.hasChild("chat")) {
////                        chatKey = String.valueOf(snapshot.child("chat").getChildrenCount() + 1);
////                    }
////                }
////
////                if(snapshot.hasChild("chat")) {
////                    if(snapshot.child("chat").child(chatKey).hasChild("messages")) {
////                        chatLists.clear();
////                        for(DataSnapshot messagesSnapshot : snapshot.child("chat").child(chatKey).child("messages").getChildren()){
////                            if(messagesSnapshot.hasChild("msg") && messagesSnapshot.hasChild("mobile")) {
////
////                                final String messageTimeStamps = messagesSnapshot.getKey();
////                                final String getMobile = messagesSnapshot.child("mobile").getValue(String.class);
////                                final String getMsg = messagesSnapshot.child("msg").getValue(String.class);
////
////                                Timestamp timestamp = new Timestamp(Long.parseLong(messageTimeStamps));
////                                Date date = new Date(timestamp.getTime());
////                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
////                                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm aa", Locale.getDefault());
////
////                                ChatList chatList = new ChatList(getMobile, getName, getMsg, simpleDateFormat.format(date), simpleTimeFormat.format(date));
////                                chatLists.add(chatList);
////
////                                if(loadingFirstTime || Long.parseLong(messageTimeStamps) > Long.parseLong(MemoryData.getLastMsgTS(getActivity(), chatKey))) {
////
////                                    loadingFirstTime = false;
////
////                                    MemoryData.saveLastMsgTS(messageTimeStamps,chatKey,getActivity());
////                                    chatAdapter.updateChatList(chatLists);
////
////
////                                    chattingRecyclerView.scrollToPosition(chatLists.size()-1);
////                                }
////
////                            }
////                        }
////                    }
////                }
////
////
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
////
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
//
//        return chatView;
//    }
//
//    @Override
//    public void onStart() {
//
//        // set the text views information to the information about the chat
//        super.onStart();
////        TextView chatTime = getActivity().findViewById(R.id.chatTime);
////        chatTime.setText(info.getRoomTime() + " minutes remaining");
//    }
//
//}
