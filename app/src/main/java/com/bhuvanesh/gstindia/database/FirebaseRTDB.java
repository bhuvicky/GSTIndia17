package com.bhuvanesh.gstindia.database;

import com.bhuvanesh.gstindia.model.Bill;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

/**
 * Created by Karthikeyan on 03-07-2017.
 */

public class FirebaseRTDB {
    private DatabaseReference mDatabase;

    public FirebaseRTDB() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void addBill(Bill bill){
        mDatabase.child("bills").child(System.currentTimeMillis()+"").setValue(bill);

    }
}
