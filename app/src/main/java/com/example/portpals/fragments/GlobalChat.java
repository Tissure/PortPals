package com.example.portpals.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.portpals.R;
import com.example.portpals.Chat;


public class GlobalChat extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_global_chat, container, false);
        //Create a path to listen for a click for each button
        rootView.findViewById(R.id.sendBtn).setOnClickListener(this);
       // rootView.findViewById(R.id.btnEventHistory).setOnClickListener(this);
        // Inflate the layout for this fragment
        return rootView;

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "this", Toast.LENGTH_SHORT);
//        switch (view.getId()) {
//            case R.id.btnToSignUp:
                Intent intent = new Intent(getActivity(), Chat.class);
                startActivity(intent);
//                break;
//            case R.id.btnEventHistory:
//                Intent intent2 = new Intent(getActivity(), EventHistoryActivity.class);
//                startActivity(intent2);
//                break;
//        }

    }
}


//    Button sendBtn;
//
////    private final int[] profilePics = {R.drawable.chad, R.drawable.crazy, R.drawable.vibe};
////    private ArrayList<ChatRoomInfo> chatRoomInfoList;
////
////    private RecyclerView recyclerView;
////
////    private void setChatRoomInfo() {
////        chatRoomInfoList.add(new ChatRoomInfo("PogRoom","Pog-man", "2/4", 60, 12, profilePics[0]));
////
////    }
////
//////    private void setChatRoomAdapter() {
//////        ChatRecyclerAdapter adapter = new ChatRecyclerAdapter(chatRoomInfoList);
//////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//////        adapter.setClickListener(this);
//////        layoutManager.canScrollVertically();
//////        recyclerView.setLayoutManager(layoutManager);
//////        recyclerView.setItemAnimator(new DefaultItemAnimator());
//////        recyclerView.setAdapter(adapter);
//////    }
////
//////    @Override
//////    public void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        Button joinBtn = getActivity().findViewById(R.id.joinChatBtn);
//////
//////        joinBtn.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View view) {
//////                Intent intent = new Intent(getContext(), Chat.class);
//////                getActivity().startActivity(intent);
//////            }
//////        });
//////
//////    }
////
////    @Override
////    public View onCreateView(LayoutInflater inflater, ViewGroup container,
////                             Bundle savedInstanceState) {
////
////        // initialize the chat room info with default values for the time being
////        chatRoomInfoList = new ArrayList<>();
////        setChatRoomInfo();
////        // Inflate the layout for this fragment
////        View view = inflater.inflate(R.layout.fragment_chatrooms, container, false);
////        return view;
////    }
////
////    @Override
////    public void onStart() {
////        super.onStart();
////
////        // fill the recycler view
////        recyclerView = getActivity().findViewById(R.id.chatRecyclerView);
////
////
////
////
//////        setChatRoomAdapter();
////    }
////
//////    @Override
//////    public void onClick(View view, int position) {
//////        ChatRoomFragment chatRoomClickedOn = new ChatRoomFragment();
//////        Bundle bundle = new Bundle();
//////        bundle.putParcelable("chatRoomInfo", chatRoomInfoList.get(position));
//////        chatRoomClickedOn.setArguments(bundle);
//////        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, chatRoomClickedOn).commit();
//////    }
////
////}
//
//
//
//
//
//
//
//
//
//
//
//    public static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    public static final DatabaseReference databaseReference = firebaseDatabase.getReference();
//
//    private final List<ChatList> chatLists = new ArrayList<>();
//    private boolean loadingFirstTime = true;
//    private ChatAdapter chatAdapter;
//    private String chatKey;
//    private RecyclerView chattingRecyclerView;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View rootView = inflater.inflate(R.layout.fragment_global_chat, container, false);
//
//    //    chattingRecyclerView.setHasFixedSize(true);
////        chattingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
////        chatAdapter = new ChatAdapter(chatLists, getActivity());
////        chattingRecyclerView.setAdapter(chatAdapter);
//        sendBtn = rootView.findViewById(R.id.sendBtn);
//        sendBtn.setOnClickListener(this);
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
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
////
////                if(!snapshot.hasChild("Chat")){
////                    MainActivity.databaseReference.child("Chat").child("global").child("messages").setValue("msg");
////                }
//
////                if(snapshot.hasChild("chat")) {
////                    if(snapshot.child("chat").child(chatKey).hasChild("messages")) {
////                        chatLists.clear();
//
//
//
////                        for(DataSnapshot messagesSnapshot : snapshot.child("chat").child("global").child("messages").getChildren()){
////
////                            if(messagesSnapshot.hasChild("msg")) {
////
////                                final String messageTimeStamps = messagesSnapshot.getKey();
////                                final String getMsg = messagesSnapshot.child("msg").getValue(String.class);
////
////                                Timestamp timestamp = new Timestamp(Long.parseLong(messageTimeStamps));
////                                Date date = new Date(timestamp.getTime());
////                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
////                                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm aa", Locale.getDefault());
////
////                                ChatList chatList = new ChatList("Name here", getMsg, simpleDateFormat.format(date), simpleTimeFormat.format(date));
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
//
//
////                    }
//                }
//
//
////            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
////
////
////
////
////
////
////
////
////
//
//
//
//        return rootView;
//    }
//
//    @Override
//    public void onClick(View view) {
//        Toast.makeText(getActivity(), "THIS ", Toast.LENGTH_SHORT);
////        EditText messageEditText = view.findViewById(R.id.messageEditTxt);
////
////        final String getTxtMessage = messageEditText.getText().toString();
////
////        //get current timestamps
////        //final String currentTimestamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);
////
////
////
////
////        databaseReference.child("chat").child("global").child("messages").child("msg").setValue(getTxtMessage);
//
//
//        // clear edit Text
//       // messageEditText.setText("");
//    }
//
//}