package com.example.portpals.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.portpals.Chat;
import com.example.portpals.CreateEventActivity;
import com.example.portpals.MainActivity;
import com.example.portpals.R;
import com.example.portpals.models.Event;
import com.example.portpals.recycleradapters.ChatRecyclerAdapter;
import com.example.portpals.util.AirportsInfoManager;
import com.example.portpals.util.ClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

public class ChatFragment extends Fragment implements ClickListener {

    private final int[] profilePics = {R.drawable.chad, R.drawable.crazy, R.drawable.vibe};
    private ArrayList<Event> eventList;
    private RecyclerView recyclerView;


    private void initEventList() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query eventsQuery = databaseReference.child("Events");
        String iata = FlightInfoFragment.getIata();
        eventsQuery.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DataSnapshot child : task.getResult().getChildren()) {
                    if(Objects.equals(child.child("iata").getValue(String.class), iata)) {
                        Gson gson = new Gson();
                        Event currentEvent = gson.fromJson(gson.toJson(child.getValue()), Event.class);
                        eventList.add(currentEvent);
                    }
                }
                ChatRecyclerAdapter adapter = new ChatRecyclerAdapter(eventList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                adapter.setClickListener(this);
                layoutManager.canScrollVertically();
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chatrooms, container, false);
        Button createEventBtn = view.findViewById(R.id.createEventBtn);
        createEventBtn.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), CreateEventActivity.class);
            startActivity(intent);
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // fill the recycler view
        eventList = new ArrayList<>();
        initEventList();
        recyclerView = getActivity().findViewById(R.id.chatRecyclerView);
    }


    @Override
    public void onClick(View view, int position) {
        eventList.get(position).incOccupants();
        Intent intent = new Intent(this.getActivity(), Chat.class);
        Bundle bundle = new Bundle();
//        bundle.getParcelable("key ",eventList.get(position));

        bundle.putString("chatType", "Events");
        bundle.putString("chatID", eventList.get(position).getId());
        bundle.putString("chatName", eventList.get(position).getName());
        intent.putExtras(bundle);
        startActivity(intent);

//        ChatRoomFragment chatRoomClickedOn = new ChatRoomFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("eventInfo", eventList.get(position));
//        chatRoomClickedOn.setArguments(bundle);
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, chatRoomClickedOn).commit();
    }

//    public void createEvent(View view) {
//        Intent intent = new Intent(getActivity(), CreateEventActivity.class);
//        startActivity(intent);
//    }
}