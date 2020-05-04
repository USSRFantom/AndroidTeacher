package space.kroha.androidteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Убираем все менюшки с экрана начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Убираем все менюшки с экрана конец

        Button buttonStart = findViewById(R.id.buttonStart);

        //Слушатель на нажатие кнопки начало
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            try {
                Intent intent = new Intent(MainActivity.this, GameLevels.class);
                startActivity(intent);
                finish();
            }catch (Exception e){

            }

            }
        });
        //Слушатель на нажатие кнопки конец
    }
    //системная кнопка "назад" делаем выход на двойной щелчек начало

    //системная кнопка "назад" делаем выход на двойной щелчек конец

}
