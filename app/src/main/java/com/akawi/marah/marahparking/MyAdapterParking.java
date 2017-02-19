package com.akawi.marah.marahparking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by user on 11/22/2016.
 */
public class MyAdapterParking extends ArrayAdapter<Parking> {
    private DatabaseReference reference;//?
    public MyAdapterParking(Context context,int resource){
        super(context,resource);
        String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        email=email.replace(".","_");//?
        reference= FirebaseDatabase.getInstance().getReference(email).child("myParking");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_my_parking, parent, false);
       TextView ishagira=(TextView) convertView.findViewById(R.id.ishagira);
        TextView itextclock=(TextView) convertView.findViewById(R.id.itextclock);
       TextView i7enam=(TextView) convertView.findViewById(R.id.i7enam);
        TextView iAddress=(TextView)convertView.findViewById(R.id.etAddress);

        ImageButton idel = (ImageButton) convertView.findViewById(R.id.idel);
        final Parking myParking = getItem(position);
        if(myParking.getIsShagira().equals("true"))
                ishagira.setText("Vacant");
        else
            ishagira.setText("Not Vacant");
        if (myParking.getIs7enam().equals("true"))
            i7enam.setText("with pay");
        else
        i7enam.setText("without pay");

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        email=email.replace(".","_");
        if(!email.equals(myParking.getOwner()))
            idel.setVisibility(View.GONE);
       else {
            idel.setOnClickListener(new View.OnClickListener() {// TODO: 2/15/2017
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
        }
        iAddress.setText(myParking.getAdress());



 return convertView;

    }

}
