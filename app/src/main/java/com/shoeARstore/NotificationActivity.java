package com.shoeARstore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class NotificationActivity extends androidx.fragment.app.Fragment {

    private NotificationExpandableHeightListView listview;
    private ArrayList<BeanclassNotification>Bean;
    private BaseAdapterNotification baseAdapter;


    private String[] TITLE = {"Do not miss the 50% Discount on Medicines for You!!!",
            "Thank you for choosing us for your Medicines ...", "Welcome to Prashant Medical , Malegaon First Medical Technology Application!.!"};
    private String[] TIME = {"25 minutes ago", "1 hour ago", "2 days ago"};


    public static NotificationActivity newInstance(){
        NotificationActivity fragment=new NotificationActivity();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_notification,container,false);

        listview = (NotificationExpandableHeightListView) view.findViewById(R.id.listview);


        Bean = new ArrayList<BeanclassNotification>();

        for (int i= 0; i< TITLE.length; i++){

            BeanclassNotification bean = new BeanclassNotification(TITLE[i], TIME[i]);
            Bean.add(bean);

        }

        baseAdapter = new BaseAdapterNotification(getActivity(), Bean) {
        };

        listview.setAdapter(baseAdapter);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //        ********LISTVIEW***********



    }
}
