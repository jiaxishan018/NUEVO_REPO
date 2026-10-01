package com.example.tema2_ciclodevidas;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Ejemplo", "Estoy en on Create");
    }
    protected void onStart(){
        super.onStart();
        Log.i("Ejemplo","Estoy en on Start");
    }
    protected void onResume(){
        super.onResume();
        Log.i("Ejemplo","Estoy en on Resume");
    }
    protected void onPause(){
        super.onPause();
        Log.i("Ejemplo","Estoy en on Pause");
    }
    protected void onStop(){
        super.onStop();
        Log.i("Ejemplo","Estoy en on Stop");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i("Ejemplo", "Estoy en on Destroy");
    }
}