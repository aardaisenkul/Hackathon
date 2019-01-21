package com.example.sertac.hackathon;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

/**
 * Main Class
 * @author Sertaç
 * User faced with this activity firstly
 */
public class MainActivity extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;
    private EditText yaş;
    private RadioGroup rg;
    private Database db;
    public static ArrayList<Hasta> yasaVeCinsiyeteGoreHastaliklar;
    private boolean erkekMi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lottieAnimationView = findViewById(R.id.animation_view);

        lottieAnimationView.setAnimation("healthtap_spinner.json");
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();

        db = new Database(this);

        yaş = findViewById(R.id.ageEditText);

        rg = findViewById(R.id.radioGroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = rg.getCheckedRadioButtonId();

                switch (id){
                    case R.id.femaleRadioButton:
                        erkekMi = false;
                        break;
                    case R.id.maleRadioButton:
                        erkekMi = true;
                        break;
                }
            }
        });

        yaş.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if((yaş.getText().toString()).equals("") || Integer.parseInt(yaş.getText().toString())>120 ){
                    yaş.setError("Yaşınız boş bırakılamaz ve 0 ile 120 arasında olmalıdır.");
                }else
                {
                    yaş.setError(null);
                }
            }
        });

    }

    /**
     * This button makes required compares, if these requirements are met then it passes the next screen
     * @param view
     */
    public void onClickİleriButton(View view) {


        if(rg != null && rg.getCheckedRadioButtonId()==-1){
            Toast.makeText(this, "Lütfen cinsiyetinizi giriniz.", Toast.LENGTH_SHORT).show();
        }else if((yaş.getText().toString()).equals("") || Integer.parseInt(yaş.getText().toString()) <0 || Integer.parseInt(yaş.getText().toString())>120 ){
            Toast.makeText(this, "Lütfen yaşınızı doğru giriniz.", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, semptomVarmi.class);
            intent.putExtra("YAŞ", yaş.getText().toString());
            if(erkekMi)
                intent.putExtra("CINSIYET", "e");
            else
                intent.putExtra("CINSIYET","k");
            startActivityForResult(intent,0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1){
            if(resultCode == 0){

            }
        }
    }
}
