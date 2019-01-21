package com.example.sertac.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

/**
 * This is the second activity for the graphical user interface
 */
public class semptomVarmi extends AppCompatActivity {

    private CheckBox[] checkBoxes;
    private Database db;
    private Hasta hasta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semptom_varmi);

        LottieAnimationView ltv = findViewById(R.id.animation_view);
        ltv.setAnimation("heart_rate.json");
        ltv.loop(true);
        ltv.playAnimation();

        db = new Database(this);
        checkBoxes    = new CheckBox[11];
        checkBoxes[0] = findViewById(R.id.cbAtes);
        checkBoxes[1] = findViewById(R.id.cbBurunA);
        checkBoxes[2] = findViewById(R.id.cbOksuruk);
        checkBoxes[3] = findViewById(R.id.cbMideB);
        checkBoxes[4] = findViewById(R.id.cbBasA);
        checkBoxes[5] = findViewById(R.id.cbKusma);
        checkBoxes[6] = findViewById(R.id.cbBasD);
        checkBoxes[7] = findViewById(R.id.cbKanama);
        checkBoxes[8] = findViewById(R.id.cbIshal);
        checkBoxes[9] = findViewById(R.id.cbKabizlik);
        checkBoxes[10] = findViewById(R.id.cbDiger);




    }

    /**
     * This button passes the third activity with required calculation
     * @param view
     */
    public void ikisfileriButton(View view) {
        int[] hastalikVarYok = new int[11];
        for (int i =0 ; i<checkBoxes.length;i++){
            if(checkBoxes[i].isChecked()){
                hastalikVarYok[i] = 1;

            }
        }

        int yaş = Integer.parseInt(getIntent().getStringExtra("YAŞ"));
        String cinsiyet = getIntent().getStringExtra("CINSIYET");
        hasta = new Hasta("current",yaş,null,cinsiyet,null,-1, (new int[11]));
        hasta.setDiseases(hastalikVarYok);

        Intent intent = new Intent(this,ShowDiseases.class);
        intent.putExtra("MyClass", hasta);
        startActivity(intent);
    }

    public void onClickikisfgerButton(View view) {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED,returnIntent);
        finish();
    }
}
