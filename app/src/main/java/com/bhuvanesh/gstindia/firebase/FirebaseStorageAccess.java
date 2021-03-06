package com.bhuvanesh.gstindia.firebase;

import android.content.Context;
import android.net.Uri;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;

/**
 * Created by Karthikeyan on 02-07-2017.
 */

public class FirebaseStorageAccess {

    private FirebaseStorage firebaseStorage;
    private Context context;
    public FirebaseStorageAccess(Context context)
    {
        this.context=context;
        firebaseStorage = FirebaseStorage.getInstance();
    }

    public StorageReference getUrl(String filePath) {
       StorageReference gsReference = firebaseStorage.getReferenceFromUrl("gs://gst-india-20cc9.appspot.com");
        StorageReference pathReference = gsReference.child(filePath);

    return pathReference;
    }
    public UploadTask uploadPhoto(Uri photoUrl){
        // Create a storage reference from our app
        StorageReference storageRef = firebaseStorage.getReferenceFromUrl("gs://gst-india-20cc9.appspot.com");
        String fileName="photo"+System.currentTimeMillis()+".jpg";
        StorageReference invoiceRef = storageRef.child("invoice");
        StorageReference photoRef = invoiceRef.child(fileName);
        UploadTask uploadPhoto = null;
        try {
            uploadPhoto = photoRef.putStream(context.getContentResolver().openInputStream(photoUrl));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return uploadPhoto;
    }

}
