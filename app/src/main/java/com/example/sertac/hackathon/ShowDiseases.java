package com.example.sertac.hackathon;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class ShowDiseases extends AppCompatActivity {

    private ArrayList<Hasta> hastalar;
    private Hasta hasta;
    private RecyclerView rw;
    private Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diseases);

        hasta = (Hasta) getIntent().getSerializableExtra("MyClass");
        db = new Database(this);
        rw = findViewById(R.id.recyclerView);
        hastalar = db.findDiseaseLike(hasta);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this,hastalar);
        rw.setAdapter(recyclerViewAdapter);
        rw.setLayoutManager(new LinearLayoutManager(this));

        LottieAnimationView ltv = findViewById(R.id.animation_view);
        int say = 0;
        for (int i = 0; i<11 ; i++){
            if(hasta.getSymptoms()[i]==1)
                say++;
        }
        if(say > 7){
            ltv.setAnimation("simple_skull_animation.json");
            findViewById(R.id.relativeLayout).setVisibility(View.VISIBLE);
        }
        else{
            rw.bringToFront();
            ltv.setAnimation("patient_successfully_added.json");
            findViewById(R.id.relativeLayout).setBackgroundColor(Color.parseColor("#FFFFFF"));
            findViewById(R.id.belowicin).setVisibility(View.GONE);
            if(hastalar.size()==0 && hasta.getSymptoms()[hasta.getSymptoms().length-1]==1){
                TextView tv = findViewById(R.id.belowicin);
                tv.setText("Girilen semptomlarla eşleşen bir hastalık bulunamadı. Lütfen en yakın zamanda doktorunuzla görüşünüz.");
                tv.setTextColor(Color.parseColor("#000000"));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,40.f);
                tv.setVisibility(View.VISIBLE);
            }
        }
        ltv.loop(true);
        ltv.playAnimation();
    }
}
