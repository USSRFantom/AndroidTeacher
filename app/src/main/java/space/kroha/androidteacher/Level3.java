package space.kroha.androidteacher;

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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Level3 extends AppCompatActivity  {

    Button button2;
    Button button3;
    Button button4;
    String textAnswer;
    ImageView img;
    int questionNumber;
    Dialog dialog;
    Dialog dialogEnd;
    public int count = 0;//счетчик правильных ответов
    int correctAnswer;
    int wrongAnswer;
    TextView textViewQuest;
    String[] question3;
    String[] answer31;
    String[] answer32;
    String[] answer33;
    String[] answer3true;


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
        text_levels.setText(R.string.level3);
        textViewQuest = findViewById(R.id.textViewQuestion);
        question3 = getResources().getStringArray(R.array.question3);
        answer31 = getResources().getStringArray(R.array.answer31);
        answer32 = getResources().getStringArray(R.array.answer32);
        answer33 = getResources().getStringArray(R.array.answer33);
        answer3true = getResources().getStringArray(R.array.answer3true);





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
                    Intent intent = new Intent(Level3.this, GameLevels.class);  //возвращаемся в прошлое активити с выбором уровня
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


        //_____________________________Диалоговое окно в конце игры
        //Вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this);//создаем новое диалоговое окно
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//скрываем заголовок
        dialogEnd.setContentView(R.layout.dialogend);//путь к макету диалогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалогового окна
        dialogEnd.setCancelable(false);//диалог нельзя закрыть кнопкой назат

        //кнопка закрывающая диалоговое окно
        TextView btnclose2 = dialogEnd.findViewById(R.id.btnclose2);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.i("1231231231", "перешли на вторую активность");
                    Intent intent = new Intent(Level3.this, GameLevels.class);  //возвращаемся в прошлое активити с выбором уровня
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    //Кода нет
                }
                dialogEnd.dismiss();//закрываем диалоговое окно
            }
        });
        //кнопка закрывающая диалоговое окно конец

        //кнопка продолжить начало
        Button buttoncontinue2 = dialogEnd.findViewById(R.id.buttoncontinue2);
        buttoncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, Level3.class);  //возвращаемся в прошлое активити с выбором уровня
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    //Кода нет
                }
                dialogEnd.dismiss();//закрываем диалоговое окно
            }

        });

        //_____________________________Диалоговое окно в конце игры




        //кнопка "назад" начало
        Button btn_back = findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    //Кода нет
                }
            }
        });
        //кнопка "назад" конец

        questionNumber = 0;
        dialog.show();//показать диалоговое окно

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
                if (questionNumber < 19){
                    if (button2.getText().toString().equals(answer3true[questionNumber]))//проверяем рпавильность ответа
                    {
                        TextView tv = findViewById(progress[questionNumber]);
                        tv.setBackgroundResource(R.drawable.style_points_green);
                        correctAnswer++;
                        Log.i("222222", button2.getText().toString());
                        Log.i("222222", answer3true[questionNumber]);


                    }else{

                        TextView tv = findViewById(progress[questionNumber]);
                        tv.setBackgroundResource(R.drawable.style_points_red);
                        wrongAnswer++;
                        Log.i("2222221", button2.getText().toString());
                        Log.i("2222221", answer3true[questionNumber]);

                    }
                    questionNumber++;
                    setButtonText(questionNumber);
                }else{
                    //выводим сообщение что уровень пройде и выходим в главное мень с уровнями  ???
                    dialogEnd.show();
                }

            }//закрытие метода нажатия кнопки
        });
        //проверка нажатия на первую кнопку конец
//==================================================================================
        //проверка нажатия на вторую кнопку
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionNumber <= 19){
                    if (button3.getText().toString().equals(answer3true[questionNumber]))//проверяем рпавильность ответа
                    {
                        TextView tv = findViewById(progress[questionNumber]);
                        tv.setBackgroundResource(R.drawable.style_points_green);
                        correctAnswer++;
                    }else{
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
                    if (button4.getText().toString().equals(answer3true[questionNumber]))//проверяем рпавильность ответа
                    {
                        TextView tv = findViewById(progress[questionNumber]);
                        tv.setBackgroundResource(R.drawable.style_points_green);
                        correctAnswer++;
                    }else{
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


    }






    //метод установки данных в кнопки

    public void setButtonText (final int a){
        button2.setText(answer31[a]);
        button3.setText(answer32[a]);
        button4.setText(answer33[a]);
        textViewQuest.setText(question3[a]);
    }


    //системная кнопка назад начало
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Level3.this, GameLevels.class);
            startActivity(intent);
            finish();
        }catch (Exception e){
            //Кода нет
        }
    }
    //системная кнопка назад конец
}

