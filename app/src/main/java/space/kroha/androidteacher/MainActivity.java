package space.kroha.androidteacher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();




    }
}





      /*   слушатель на изменение в базе  мгновенное изменение в логе данных
      db.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null) {
                        for (QueryDocumentSnapshot queryDocumentSnapshot: queryDocumentSnapshots){
                            Map<String, Object> user = queryDocumentSnapshot.getData();
                            Log.i("FireStore", user.get("name").toString());
                            Log.i("FireStore", user.get("lastname").toString());
                            Log.i("FireStore", user.get("age").toString());
                        }
                }else{
                    Toast.makeText(MainActivity.this, "ERROR null", Toast.LENGTH_SHORT).show();
                }



            }
        });*/



/*  добавление в базу

        Map<String, Object> user = new HashMap<>();
        user.put("name", "Иван");
        user.put("lastname", "Иванов");
        user.put("age", 15);

        db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(MainActivity.this, "TRU", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        */



/*Получение данных

  db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "TRU", Toast.LENGTH_SHORT).show();
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot == null) return;
                    for (QueryDocumentSnapshot queryDocumentSnapshot: querySnapshot){
                        Map<String, Object> user = queryDocumentSnapshot.getData();
                        Log.i("FireStore", user.get("name").toString());
                        Log.i("FireStore", user.get("lastname").toString());
                        Log.i("FireStore", user.get("age").toString());
                    }
                }else{
                    Toast.makeText(MainActivity.this, "ERROR" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });*/