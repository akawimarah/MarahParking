package com.akawi.marah.marahparking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextClock;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by user on 11/22/2016.
 */
public class MyAdapterParking extends ArrayAdapter<Parking1> {
    private DatabaseReference reference;
    public MyAdapterParking(Context context,int resource){
        super(context,resource);
        String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        email=email.replace(".","_");
        reference= FirebaseDatabase.getInstance().getReference(email).child("myParking");



    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_my_parking, parent, false);
        EditText etIsShagiraa = (EditText) convertView.findViewById(R.id.etIsShagiraa);
        EditText etDatee = (EditText) convertView.findViewById(R.id.etDatee);
        EditText etAddress2 = (EditText) convertView.findViewById(R.id.etAddress2);
        EditText textClock2 = (EditText) convertView.findViewById(R.id.textClock2);
        RatingBar rb = (RatingBar) convertView.findViewById(R.id.rb);
        EditText etIs7enamm = (EditText) convertView.findViewById(R.id.etIs7enamm);
        ImageButton btnDel = (ImageButton) convertView.findViewById(R.id.btnDel);
        CheckBox done = (CheckBox) convertView.findViewById(R.id.done);
        final Parking1 myParking = getItem(position);
        etIs7enamm.setText(myParking.getIs7enam());
        etIsShagiraa.setText(myParking.getIsShagira());
        etAddress2.setText(myParking.getAdress());
        etDatee.setText(myParking.getWhen());
        rb.setRating(myParking.getPrioroty());


        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child(myParking.getId()).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null)//deleted
                        {
                            Toast.makeText(getContext(), "Deleted", Toast.LENGTH_LONG).show();
                            remove(myParking);
                            setNotifyOnChange(true);
                        }
                    }
                });
            }
        });
        done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
 return convertView;

    }

}
