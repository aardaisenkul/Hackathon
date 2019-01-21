package com.example.sertac.hackathon;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * This class creates database tables which is needed(in real application databases will be taken from real hospitals)
 *
 */
public class Database extends SQLiteOpenHelper {

    // All Static variables

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Database";//database adı

    //Disease Table and veriables
    private static final String HASTALIK_TABLE  = "Hastalıklar";//database adı
    private static String HASTA_ADI             = "isim";
    private static String YAS                   = "yaş";
    private static String DONEM                 = "dönem";
    private static String CINSIYET              = "cinsiyet";
    private static String TEDAVI                = "hastalık";
    private static String TC_NO                 = "TC no";
    private static String ATES                  = "ateş";
    private static String BURUN_AKINTISI        = "burun akıntısı";
    private static String OKSURUK               = "öksürük";
    private static String MIDE_BULANTISI        = "mide bulantısı";
    private static String BAS_AGRISI            = "baş ağrısı";
    private static String KUSMA                 = "kusma";
    private static String BAS_DONMESI           = "baş dönmesi";
    private static String KANAMA                = "kanama";
    private static String ISHAL                 = "ishal";
    private static String KABIZLIK              = "kabızlık";
    private static String DIGER                 = "diğer";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Create Table in onCreate method
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_HASTALIKLAR = "CREATE TABLE '"	+ HASTALIK_TABLE + "' ('"
                + HASTA_ADI						            + "' TEXT NOT NULL, '"
                + YAS       					            + "' INTEGER, '"
                + DONEM					                    + "' TEXT, '"
                + CINSIYET      							+ "' TEXT, '"
                + TEDAVI                                    + "' TEXT,'"
                + TC_NO                                     + "' INTEGER NOT NULL,'"
                + ATES                                      + "' INTEGER, '"
                + BURUN_AKINTISI                            + "' INTEGER, '"
                + OKSURUK                                   + "' INTEGER, '"
                + MIDE_BULANTISI                            + "' INTEGER, '"
                + BAS_AGRISI                                + "' INTEGER, '"
                + KUSMA                                     + "' INTEGER, '"
                + BAS_DONMESI                               + "' INTEGER, '"
                + KANAMA                                    + "' INTEGER, '"
                + ISHAL                                     + "' INTEGER, '"
                + KABIZLIK                                  + "' INTEGER, '"
                + DIGER                                     + "' INTEGER, "
                + "PRIMARY KEY ('"+TC_NO+"') );";
        db.execSQL(CREATE_TABLE_HASTALIKLAR);
    }

    /**
     * This method takes current patient to find patients like him/her from recorded patient(From Database).
     *
     * @param asil is the patient that we want to find
     * @return patients like "asil patient"
     */
    public ArrayList<Hasta> findDiseaseLike(Hasta asil){
        SQLiteDatabase db = this.getReadableDatabase();
        int alt = asil.getAge()-5;
        int ust = asil.getAge()+5;
        Cursor cursor = db.rawQuery("SELECT *  FROM " + HASTALIK_TABLE+" WHERE "+CINSIYET+" LIKE '%"+asil.getGender()+"%' AND "+YAS+" <= "+ust+" AND "+YAS+">= "+alt,null);

        ArrayList<Hasta> hastalar = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                int[] temp = new int[11];
                for (int i = 0; i < temp.length;i++){
                    temp[i] = cursor.getInt(i+6);
                }
                hastalar.add(new Hasta(
                        cursor.getString(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        temp

                ));

            }while(cursor.moveToNext());
        }

        db.close();


        ArrayList<Hasta> yakinHastalar = new ArrayList<>();


        for (Hasta h: hastalar) {


            if (asil.eslesmeMiktari(h)>=2){
                yakinHastalar.add(h);
            }
        }
        return  yakinHastalar;

    }

    /**
     * This method return all patients in the database.
     * @return all patients
     */
    public ArrayList<Hasta> butunHastalar(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+HASTALIK_TABLE,null);
        ArrayList<Hasta> hastalar = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                int[] temp = new int[11];
                for (int i = 0; i < temp.length;i++){
                    temp[i] = cursor.getInt(i+6);
                }
                hastalar.add(new Hasta(
                        cursor.getString(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        temp

                ));
            }while (cursor.moveToNext());
        }
        db.close();
        return hastalar;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
