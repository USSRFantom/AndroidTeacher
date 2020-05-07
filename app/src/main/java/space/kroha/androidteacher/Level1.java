package space.kroha.androidteacher;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Level1 extends AppCompatActivity  {

    Button button2;
    Button button3;
    Button button4;
    ImageView img;
    int questionNumber;
    private static FirebaseFirestore db;
    public static List<Lesson> list;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        img = findViewById(R.id.imageTest);

        list = new ArrayList<>();
        img.setClipToOutline(true);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        list = new ArrayList<>();
        questionNumber = 0;

        setButtonText(questionNumber);
    }



    private void readData(final FirestoreCallback firestoreCallback){
        db = FirebaseFirestore.getInstance();
        db.collection("users")
              .get()
              .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                  @Override
                  public void onComplete(@NonNull Task<QuerySnapshot> task) {
                      if (task.isSuccessful()){
                          Log.i("123233333", "Данные есть!");
                          QuerySnapshot querySnapshot = task.getResult();
                          if (querySnapshot == null)return;
                          for (QueryDocumentSnapshot documentSnapshot : querySnapshot){
                              Lesson lesson = documentSnapshot.toObject(Lesson.class);
                              list.add(lesson);
                          }
                          firestoreCallback.onCallback(list);
                      }else {
                          Log.i("123233333", "НЕТ ДАННЫХ");
                      }
                      Log.i("333", list.get(1).getAge());
                  }
              });
    }


    private interface FirestoreCallback{
        void onCallback(List<Lesson> list);
    }

    public void setButtonText (final int a){
        readData(new FirestoreCallback() {
            @Override
            public void onCallback(List<Lesson> list) {
                button2.setText(list.get(a).getName());
                button3.setText(list.get(a + 1).getName());
                button4.setText(list.get(a + 2).getName());
                System.out.println(a);

            }
        });
    }

}
