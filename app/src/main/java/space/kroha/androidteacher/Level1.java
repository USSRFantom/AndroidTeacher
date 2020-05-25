package space.kroha.androidteacher;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Level1 extends AppCompatActivity  {

    Button button2;
    Button button3;
    Button button4;
    String textAnswer;
    ImageView img;
    int questionNumber;
    private static FirebaseFirestore db;
    public static List<Lesson> list;
    Dialog dialog;
    public int count = 0;//счетчик правильных ответов
    int correctAnswer;
    int wrongAnswer;


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

        //массив для прогресса игры начало
        final int[] progress = {R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10, R.id.point11, R.id.point12,
                R.id.point13, R.id.point14, R.id.point15, R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20};
        //массив для прогресса игры конец




//==================================================================================
        //проверка нажатия на первую кнопку
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionNumber < 20){
                    if (button2.getText().toString().equals(textAnswer))//проверяем рпавильность ответа
                    {
                        Log.i("111111", "Первая кнопка");
                        Log.i("111111", "Получаем ответ с кнопки " + button2.getText().toString());
                        Log.i("111111", "Получаем ответ с базы" + textAnswer);
                        Log.i("111111", "Верно");

                        Toast.makeText(getApplicationContext(), "Верно!", Toast.LENGTH_SHORT).show();
                        TextView tv = findViewById(progress[questionNumber]);
                        tv.setBackgroundResource(R.drawable.style_points_green);
                        correctAnswer++;


                    }else{
                        Log.i("111111", "Первая кнопка");
                        Log.i("111111", "Получаем ответ с кнопки " + button2.getText().toString());
                        Log.i("111111", "Получаем ответ с базы" + textAnswer);
                        Log.i("111111", "Ошибка");

                        Toast.makeText(getApplicationContext(), "Ошибка!", Toast.LENGTH_SHORT).show();
                        TextView tv = findViewById(progress[questionNumber]);
                        tv.setBackgroundResource(R.drawable.style_points_red);
                        wrongAnswer++;

                    }
                    questionNumber++;
                    setButtonText(questionNumber);
                }else{
                    //выводим сообщение что уровень пройде и выходим в главное мень с уровнями  ???
                }

            }//закрытие метода нажатия кнопки
        });
        //проверка нажатия на первую кнопку конец
//==================================================================================
        //проверка нажатия на вторую кнопку
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionNumber < 20){
                    if (button3.getText().toString().equals(textAnswer))//проверяем рпавильность ответа
                    {

                        Log.i("111111", "Вторая кнопка");
                        Log.i("111111", "Получаем ответ с кнопки " + button3.getText().toString());
                        Log.i("111111", "Получаем ответ с базы" + textAnswer);
                        Log.i("111111", "Верно");


                        Toast.makeText(getApplicationContext(), "Верно!", Toast.LENGTH_SHORT).show();
                        TextView tv = findViewById(progress[questionNumber]);
                        tv.setBackgroundResource(R.drawable.style_points_green);
                        correctAnswer++;


                    }else{

                        Log.i("111111", "Вторая кнопка");
                        Log.i("111111", "Получаем ответ с кнопки " + button3.getText().toString());
                        Log.i("111111", "Получаем ответ с базы" + textAnswer);
                        Log.i("111111", "Ошибка");

                        Toast.makeText(getApplicationContext(), "Ошибка!", Toast.LENGTH_SHORT).show();
                        TextView tv = findViewById(progress[questionNumber]);
                        tv.setBackgroundResource(R.drawable.style_points_red);
                        wrongAnswer++;

                    }
                    questionNumber++;
                    setButtonText(questionNumber);
                }else{
                    //выводим сообщение что уровень пройде и выходим в главное мень с уровнями  ???
                }

            }//закрытие метода нажатия кнопки
        });
        //проверка нажатия на вторую кнопку конец
//==================================================================================
        //проверка нажатия на третью кнопку
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionNumber < 20){
                    if (button4.getText().toString().equals(textAnswer))//проверяем рпавильность ответа
                    {
                        Log.i("111111", "Третья кнопка");
                        Log.i("111111", "Получаем ответ с кнопки " + button4.getText().toString());
                        Log.i("111111", "Получаем ответ с базы" + textAnswer);
                        Log.i("111111", "Верно");

                        Toast.makeText(getApplicationContext(), "Верно!", Toast.LENGTH_SHORT).show();
                        TextView tv = findViewById(progress[questionNumber]);
                        tv.setBackgroundResource(R.drawable.style_points_green);
                        correctAnswer++;


                    }else{

                        Log.i("111111", "Третья кнопка");
                        Log.i("111111", "Получаем ответ с кнопки " + button4.getText().toString());
                        Log.i("111111", "Получаем ответ с базы" + textAnswer);
                        Log.i("111111", "Ошибка");

                        Toast.makeText(getApplicationContext(), "Ошибка!", Toast.LENGTH_SHORT).show();
                        TextView tv = findViewById(progress[questionNumber]);
                        tv.setBackgroundResource(R.drawable.style_points_red);
                        wrongAnswer++;

                    }
                    questionNumber++;
                    setButtonText(questionNumber);
                    System.out.println();
                }else{
                    //выводим сообщение что уровень пройде и выходим в главное мень с уровнями  ???
                }

            }//закрытие метода нажатия кнопки
        });
        //проверка нажатия на первую кнопку конец
 // ==================================================================================












    }//метод OnCreate



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
        //метод установки данных в кнопки и картинку
    public void setButtonText (final int a){
        readData(new FirestoreCallback() {
            @Override
            public void onCallback(List<Lesson> list) {
                button2.setText(list.get(a).getAnswer1());//установка значений для кнопки 1
                button3.setText(list.get(a).getAnswer2());//установка значений для кнопки 1
                button4.setText(list.get(a).getAnswer3());//установка значений для кнопки 1
                Picasso.get().load(list.get(a).getAnswer4()).into(img);//установка картинки
                textAnswer = list.get(a).getAnswer5();
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








/*
        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //проверяем касание
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    button3.setEnabled(false); //блокируем кнопки остальные
                    button4.setEnabled(false); //блокируем кнопки остальные

                    if (button2.getText() == textAnswer){
                        button2.setBackgroundColor(getResources().getColor(R.color.colorGreen)); //если правильно заливаем фон кнопки зеленым цветом
                    }else{
                        button2.setBackgroundColor(getResources().getColor(R.color.colorAccent));//если не правильно заливаем фон кнопки красным цветом
                    }


                }else
                    //проверка отпуска пальца
                    if (event.getAction()==MotionEvent.ACTION_UP){
                        if (button2.getText() == textAnswer){
                            if (count < 20 ){
                                count++;
                            }
                            //закрашиваем прогресс серым цветом
                            for (int i = 0; i <20; i++){
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points);
                            }
                            //закрашиваем прогресс серым цветом конец

                            //определяем правильные ответы и закрашиваем зеленым начало
                            for (int i = 0; i < count; i ++){
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points_green);
                            }
                            //определяем правильные ответы и закрашиваем зеленым конец


                        }else{
                            if (count > 0){
                                if (count == 1){
                                    count = 0;
                                }else{
                                    count = count -2;
                                }
                            }
                            //закрашиваем прогресс серым цветом
                            for (int i = 0; i <19; i++){
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points);
                            }
                            //закрашиваем прогресс серым цветом конец
                            //определяем правильные ответы и закрашиваем зеленым начало
                            for (int i = 0; i < count; i ++){
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points_green);
                            }
                            //определяем правильные ответы и закрашиваем зеленым конец
                        }

                        if (count == 20){
                            //ВЫХОД ИЗ УРОВНЯ
                        }else{
                            setButtonText(questionNumber);
                            button3.setEnabled(true); //включаем кнопки остальные
                            button4.setEnabled(true); //включаем кнопки остальные
                        }

                }
                //проверяем касание конец


                return true;
            }
        });
 */


//added to firebase 20 questions