package space.kroha.androidteacher;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    Dialog dialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        img = findViewById(R.id.imageTest);
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);
        list = new ArrayList<>();


        //скругление картинки
        img.setClipToOutline(true);

        //убираем менюшку с экрана
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Вызов диалогового окна в начале игры
        dialog = new Dialog(this);//создаем новое диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//скрываем заголовок
        dialog.setContentView(R.layout.previewdialog);//путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалогового окна
        dialog.setCancelable(false);//диалог нельзя закрыть кнопкой назат

        //кнопка закрывающая диалоговое окно
        TextView btnclose = dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);  //возвращаемся в прошлое активити с выбором уровня
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    //Кода нет
                }
                dialog.dismiss();//закрываем диалоговое окно
            }
        });
        //кнопка закрывающая диалоговое окно конец

        //кнопка продолжить начало
        Button buttoncontinue = dialog.findViewById(R.id.buttoncontinue);
        buttoncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog.dismiss();//закрываем диалоговое окно, чтобы приступить к прохождению уровня
                }catch (Exception e){
                    //Кода нет
                }
            }
        });
        //кнопка продолжить конец

        //кнопка "назад" начало
        Button btn_back = findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    //Кода нет
                }
            }
        });
        //кнопка "назад" конец

        dialog.show();//показать диалоговое окно


        questionNumber = 0;

        setButtonText(questionNumber);
    }

    public void Click(View view) {

        String b = button2.getText().toString();
        System.out.println(b);
        questionNumber++;
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
                      Log.i("333", list.get(1).getAnswer1());
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
                button2.setText(list.get(a).getAnswer1());
                button3.setText(list.get(a).getAnswer2());
                button4.setText(list.get(a).getAnswer3());

            }
        });
    }

    //системная кнопка назад начало
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Level1.this, GameLevels.class);
            startActivity(intent);
            finish();
        }catch (Exception e){
            //Кода нет
        }
    }
    //системная кнопка назад конец
}
