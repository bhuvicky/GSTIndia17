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
        // Create a storage reference from our app
        //StorageReference storageRef = firebaseStorage.getReference();

        // Create a reference with an initial file path and name

        // Create a reference to a file from a Google Cloud Storage URI
        StorageReference gsReference = firebaseStorage.getReferenceFromUrl("gs://gst-india-20cc9.appspot.com");
        StorageReference pathReference = gsReference.child(filePath);
       // StorageReference httpsReference = firebaseStorage.getReferenceFromUrl("https://firebasestorage.googleapis.com/b/bucket/o/images%20stars.jpg");
    return pathReference;
    }
    public UploadTask uploadPhoto(Uri photoUrl){
        // Create a storage reference from our app
        StorageReference storageRef = firebaseStorage.getReferenceFromUrl("gs://talent-hive.appspot.com");
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
