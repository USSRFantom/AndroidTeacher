package space.kroha.androidteacher;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Level1 extends AppCompatActivity {
    private FirebaseFirestore db;
    List<Lesson> list = new ArrayList<>();
    Button button1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);
        button1 = findViewById(R.id.button2);


        final ImageView img = findViewById(R.id.imageTest);

        //код который скругляет углы картинки
        img.setClipToOutline(true);

        //Убираем все менюшки с экрана начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Убираем все менюшки с экрана конец

        db = FirebaseFirestore.getInstance();
                db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            Log.i("123233333", "YES!");
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot == null)return;
                            for (QueryDocumentSnapshot documentSnapshot : querySnapshot){
                                Lesson lesson = documentSnapshot.toObject(Lesson.class);
                                list.add(lesson);
                            }
                        }else {
                            Log.i("123233333", "NO!");
                        }
                        Log.i("333", list.get(2).getAge());
                        button1.setText(list.get(2).getAge());


            }

        });



    }
}
