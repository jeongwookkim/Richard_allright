package com.gihoon.richardallright;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Information extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        ImageView userview = findViewById(R.id.userview);
        TextView username = findViewById(R.id.username);
        if (currentUser.getPhotoUrl() != null) {
            Glide.with(this).load(currentUser.getPhotoUrl()).into(userview);
        }
        if(currentUser.getDisplayName()!=null) {
            if (!currentUser.getDisplayName().equals("")) {
                username.setText(currentUser.getDisplayName());
            }
        }
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        final String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day =  String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        if(month.length()==1) month = "0"+month;
        if(day.length()==1) day = "0"+day;

        final String finalmonth = month;
        final String finalday = day;
        db.collection("reservation")
                .whereEqualTo("uid", currentUser.getUid())
                .whereEqualTo("date", year+month+day)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HashMap a = (HashMap) document.getData();
                                String docname = a.get("parkingLotId").toString();

                                db.collection("parkingLot").document(docname).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> taska) {
                                        if (taska.isSuccessful()) {
                                            DocumentSnapshot documentSnapshot = taska.getResult();
                                            TextView resertt = findViewById(R.id.reservText);
                                            TextView address = findViewById(R.id.roomAddress);
                                            TextView name = findViewById(R.id.oneroomname);
                                            Button remove = findViewById(R.id.remove);
                                            if (documentSnapshot.exists()) {
                                                resertt.setVisibility(View.VISIBLE);
                                                ImageView imageView = findViewById(R.id.oneroomimage);
                                                if(documentSnapshot.get("imageUrl")!=null) {
                                                    Glide.with(getApplicationContext())
                                                            .load(documentSnapshot.get("imageUrl"))
                                                            .into(imageView);
                                                }
                                                address.setText(documentSnapshot.get("address").toString());
                                                name.setText(documentSnapshot.get("title").toString());

                                                remove.setVisibility(View.VISIBLE);
                                            } else {
                                                System.out.println("No such document");
                                                remove.setVisibility(View.INVISIBLE);
                                                resertt.setVisibility(View.INVISIBLE);
                                            }
                                        } else
                                            System.out.println("get failed with " + taska.getException());
                                    }
                                });
                            }
                        } else {
                            System.out.println(task.getException());
                        }
                    }
                });
        Button bt = findViewById(R.id.homemove);
        bt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ab = new Intent(getApplication(), Map.class);
                ab.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ab);
                finish();
            }
        });
        Button remove = findViewById(R.id.remove);
        remove.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("reservation")
                        .whereEqualTo("uid", currentUser.getUid())
                        .whereEqualTo("date", year+ finalmonth+finalday)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        HashMap a = (HashMap) document.getData();
                                        String docname = a.get("parkingLotId").toString();
                                        final String docId = document.getId();
                                        db.collection("parkingLot").document(docname).update("flag", true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                System.out.println("DocumentSnapshot successfully updated!");
                                                db.collection("reservation").document(docId).delete();
                                                Intent bbb = new Intent(getApplication(), Map.class);
                                                bbb.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(bbb);
                                                finish();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {

                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                System.out.println("Error updating document");
                                                Toast.makeText(getApplicationContext(),"예약 삭제에 실패하였습니다. 잠시후 다시 시도해주세요!",Toast.LENGTH_SHORT);
                                            }
                                        });
                                    }
                                } else {
                                    System.out.println(task.getException());
                                }
                            }
                        });
            }
        });


    }
}