package com.example.knutt.androidprojectfinal1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KNUTT on 19/1/2561.
 */

public class Database extends SQLiteOpenHelper {

    private static final int dbVersion = 1;
    private static final String dbName = "knutt.sqlite";
    //create table Emotion
    private static final String tableName2 = "Emotiontable";
    private static final String colEmotion1 = "EmotionID";
    private static final String colEmotion2 = "EmotionWord";
    //create table Attitude
    private static final String tableName = "Attitudetable";
    private static final String colAntitude1 = "AttitudeID";
    private static final String colAntitude2 = "AttitudeWord";
    private static final String colAntitude3 = "AttitudeRank";
    private static final String colAntitude4 = "Emotion";


    public Database(Context context) {
        super(context, dbName, null, dbVersion);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE "+tableName2+"("+colEmotion1+" INTEGER PRIMARY KEY,"+
                colEmotion2+" TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE "+tableName+"("+colAntitude1+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                colAntitude2+" TEXT,"+colAntitude3+" INTEGER,"+colAntitude4+" INTEGER, FOREIGN KEY("+colAntitude4+") REFERENCES "+tableName2+"("+colEmotion1+"));");

        String table_nameemo = "Emotiontable";

        int EmotionID = 0;
        String EmotionWord = "เศร้าเสียใจ";
        int EmotionID2 = 1;
        String EmotionWord2 = "กลัว";
        int EmotionID3 = 2;
        String EmotionWord3 = "ยอมรับ";
        int EmotionID4 = 3;
        String EmotionWord4 = "ประหลาดใจ";
        int EmotionID5 = 4;
        String EmotionWord5 = "รังเกียจ";
        int EmotionID6 = 5;
        String EmotionWord6 = "โกรธ";
        int EmotionID7 = 6;
        String EmotionWord7 = "คาดหวัง";
        int EmotionID8 = 7;
        String EmotionWord8 = "รื่นเริง";
        int EmotionID9 = 8;
        String EmotionWord9 = "ไม่แสดงอารมณ์";


        ContentValues emovalue = new ContentValues();
        ContentValues emovalue2 = new ContentValues();
        ContentValues emovalue3 = new ContentValues();
        ContentValues emovalue4 = new ContentValues();
        ContentValues emovalue5 = new ContentValues();
        ContentValues emovalue6 = new ContentValues();
        ContentValues emovalue7 = new ContentValues();
        ContentValues emovalue8 = new ContentValues();
        ContentValues emovalue9 = new ContentValues();

        emovalue.put("EmotionID", EmotionID);
        emovalue.put("EmotionWord", EmotionWord);
        emovalue2.put("EmotionID", EmotionID2);
        emovalue2.put("EmotionWord", EmotionWord2);
        emovalue3.put("EmotionID", EmotionID3);
        emovalue3.put("EmotionWord", EmotionWord3);
        emovalue4.put("EmotionID", EmotionID4);
        emovalue4.put("EmotionWord", EmotionWord4);
        emovalue5.put("EmotionID", EmotionID5);
        emovalue5.put("EmotionWord", EmotionWord5);
        emovalue6.put("EmotionID", EmotionID6);
        emovalue6.put("EmotionWord", EmotionWord6);
        emovalue7.put("EmotionID", EmotionID7);
        emovalue7.put("EmotionWord", EmotionWord7);
        emovalue8.put("EmotionID", EmotionID8);
        emovalue8.put("EmotionWord", EmotionWord8);
        emovalue9.put("EmotionID", EmotionID9);
        emovalue9.put("EmotionWord", EmotionWord9);



        sqLiteDatabase.insert(table_nameemo, null, emovalue);
        sqLiteDatabase.insert(table_nameemo, null, emovalue2);
        sqLiteDatabase.insert(table_nameemo, null, emovalue3);
        sqLiteDatabase.insert(table_nameemo, null, emovalue4);
        sqLiteDatabase.insert(table_nameemo, null, emovalue5);
        sqLiteDatabase.insert(table_nameemo, null, emovalue6);
        sqLiteDatabase.insert(table_nameemo, null, emovalue7);
        sqLiteDatabase.insert(table_nameemo, null, emovalue8);
        sqLiteDatabase.insert(table_nameemo, null, emovalue9);
///////////////////////////////////////////////////////////////////

        ContentValues attvalue = new ContentValues();
        ContentValues attvalue2 = new ContentValues();
        ContentValues attvalue3 = new ContentValues();
        ContentValues attvalue4 = new ContentValues();
        ContentValues attvalue5 = new ContentValues();
        ContentValues attvalue6 = new ContentValues();
        ContentValues attvalue7 = new ContentValues();
        ContentValues attvalue8 = new ContentValues();
        ContentValues attvalue9 = new ContentValues();
        ContentValues attvalue10 = new ContentValues();
        ContentValues attvalue11 = new ContentValues();
        ContentValues attvalue12 = new ContentValues();
        ContentValues attvalue13 = new ContentValues();
        ContentValues attvalue14 = new ContentValues();
        ContentValues attvalue15 = new ContentValues();
        ContentValues attvalue16 = new ContentValues();
        ContentValues attvalue17 = new ContentValues();
        ContentValues attvalue18 = new ContentValues();
        ContentValues attvalue19 = new ContentValues();
        ContentValues attvalue20 = new ContentValues();
        ContentValues attvalue21 = new ContentValues();
        ContentValues attvalue22 = new ContentValues();
        ContentValues attvalue23 = new ContentValues();
        ContentValues attvalue24 = new ContentValues();
        ContentValues attvalue25 = new ContentValues();
        ContentValues attvalue26 = new ContentValues();
        ContentValues attvalue27 = new ContentValues();
        ContentValues attvalue28 = new ContentValues();
        ContentValues attvalue29 = new ContentValues();

        String table_nameatt = "Attitudetable";

        attvalue.put("AttitudeID",1);attvalue.put("AttitudeWord","กตัญญู");attvalue.put("AttitudeRank",1);attvalue.put("Emotion",8);
        attvalue2.put("AttitudeID",2);attvalue2.put("AttitudeWord","ก็ดี");attvalue2.put("AttitudeRank",1);attvalue2.put("Emotion",2);
        attvalue3.put("AttitudeID",3);attvalue3.put("AttitudeWord","ก็ดี");attvalue3.put("AttitudeRank",1);attvalue3.put("Emotion",7);
        attvalue4.put("AttitudeID",4);attvalue4.put("AttitudeWord","กระจ่าง");attvalue4.put("AttitudeRank",1);attvalue4.put("Emotion",8);
        attvalue5.put("AttitudeID",5);attvalue5.put("AttitudeWord","กระฉับกระเฉง");attvalue5.put("AttitudeRank",1);attvalue5.put("Emotion",8);
        attvalue6.put("AttitudeID",6);attvalue6.put("AttitudeWord","กระตือรือร้น");attvalue6.put("AttitudeRank",1);attvalue6.put("Emotion",7);
        attvalue7.put("AttitudeID",7);attvalue7.put("AttitudeWord","กลมกล่อม");attvalue7.put("AttitudeRank",1);attvalue7.put("Emotion",8);
        attvalue8.put("AttitudeID",8);attvalue8.put("AttitudeWord","กล้า");attvalue8.put("AttitudeRank",1);attvalue8.put("Emotion",2);
        attvalue9.put("AttitudeID",9);attvalue9.put("AttitudeWord","กล้าหาญ");attvalue9.put("AttitudeRank",1);attvalue9.put("Emotion",2);
        attvalue10.put("AttitudeID",10);attvalue10.put("AttitudeWord","กว้าง");attvalue10.put("AttitudeRank",1);attvalue10.put("Emotion",8);
        attvalue11.put("AttitudeID",11);attvalue11.put("AttitudeWord","กว้างขวาง");attvalue11.put("AttitudeRank",1);attvalue11.put("Emotion",8);
        attvalue12.put("AttitudeID",12);attvalue12.put("AttitudeWord","กะทัดรัด");attvalue12.put("AttitudeRank",1);attvalue12.put("Emotion",2);
        attvalue13.put("AttitudeID",13);attvalue13.put("AttitudeWord","กังวาน");attvalue13.put("AttitudeRank",1);attvalue13.put("Emotion",2);
        attvalue14.put("AttitudeID",14);attvalue14.put("AttitudeWord","กำยำ");attvalue14.put("AttitudeRank",1);attvalue14.put("Emotion",8);
        attvalue15.put("AttitudeID",15);attvalue15.put("AttitudeWord","กำไร");attvalue15.put("AttitudeRank",1);attvalue15.put("Emotion",8);
        attvalue16.put("AttitudeID",16);attvalue16.put("AttitudeWord","กำลังใจ");attvalue16.put("AttitudeRank",1);attvalue16.put("Emotion",2);
        attvalue17.put("AttitudeID",17);attvalue17.put("AttitudeWord","กำลังใจ");attvalue17.put("AttitudeRank",1);attvalue17.put("Emotion",7);
        attvalue18.put("AttitudeID",18);attvalue18.put("AttitudeWord","กิตติมศักดิ์");attvalue18.put("AttitudeRank",1);attvalue18.put("Emotion",2);

        attvalue19.put("AttitudeID",19);
        attvalue19.put("AttitudeWord","กินใจ");
        attvalue19.put("AttitudeRank",1);
        attvalue19.put("Emotion",2);

        attvalue20.put("AttitudeID",20);
        attvalue20.put("AttitudeWord","เก่ง");
        attvalue20.put("AttitudeRank",1);
        attvalue20.put("Emotion",2);

        attvalue21.put("AttitudeID",21);
        attvalue21.put("AttitudeWord","เก่ง");
        attvalue21.put("AttitudeRank",1);
        attvalue21.put("Emotion",7);


        attvalue22.put("AttitudeID",22);
        attvalue22.put("AttitudeWord","เกษมสันต์");
        attvalue22.put("AttitudeRank",1);
        attvalue22.put("Emotion",7);

        attvalue23.put("AttitudeID",23);
        attvalue23.put("AttitudeWord","แก่กล้า");
        attvalue23.put("AttitudeRank",1);
        attvalue23.put("Emotion",2);

        attvalue24.put("AttitudeID",24);
        attvalue24.put("AttitudeWord","แก้มใส");
        attvalue24.put("AttitudeRank",1);
        attvalue24.put("Emotion",8);

        attvalue25.put("AttitudeID",25);
        attvalue25.put("AttitudeWord","แกร่ง");
        attvalue25.put("AttitudeRank",1);
        attvalue25.put("Emotion",8);

        attvalue26.put("AttitudeID",26);
        attvalue26.put("AttitudeWord","โก้");
        attvalue26.put("AttitudeRank",1);
        attvalue26.put("Emotion",8);

        attvalue27.put("AttitudeID",27);
        attvalue27.put("AttitudeWord","ขจร");
        attvalue27.put("AttitudeRank",1);
        attvalue27.put("Emotion",8);

        attvalue28.put("AttitudeID",28);
        attvalue28.put("AttitudeWord","ขยัน");
        attvalue28.put("AttitudeRank",1);
        attvalue28.put("Emotion",2);

        attvalue29.put("AttitudeID",29);
        attvalue29.put("AttitudeWord","ขรึม");
        attvalue29.put("AttitudeRank",1);
        attvalue29.put("Emotion",8);

        sqLiteDatabase.insert(table_nameatt,null,attvalue);
        sqLiteDatabase.insert(table_nameatt,null,attvalue2);
        sqLiteDatabase.insert(table_nameatt,null,attvalue3);
        sqLiteDatabase.insert(table_nameatt,null,attvalue4);
        sqLiteDatabase.insert(table_nameatt,null,attvalue5);
        sqLiteDatabase.insert(table_nameatt,null,attvalue6);
        sqLiteDatabase.insert(table_nameatt,null,attvalue7);
        sqLiteDatabase.insert(table_nameatt,null,attvalue8);
        sqLiteDatabase.insert(table_nameatt,null,attvalue9);
        sqLiteDatabase.insert(table_nameatt,null,attvalue10);
        sqLiteDatabase.insert(table_nameatt,null,attvalue11);
        sqLiteDatabase.insert(table_nameatt,null,attvalue12);
        sqLiteDatabase.insert(table_nameatt,null,attvalue13);
        sqLiteDatabase.insert(table_nameatt,null,attvalue14);
        sqLiteDatabase.insert(table_nameatt,null,attvalue15);
        sqLiteDatabase.insert(table_nameatt,null,attvalue16);
        sqLiteDatabase.insert(table_nameatt,null,attvalue17);
        sqLiteDatabase.insert(table_nameatt,null,attvalue18);
        sqLiteDatabase.insert(table_nameatt,null,attvalue19);
        sqLiteDatabase.insert(table_nameatt,null,attvalue20);
        sqLiteDatabase.insert(table_nameatt,null,attvalue21);
        sqLiteDatabase.insert(table_nameatt,null,attvalue22);
        sqLiteDatabase.insert(table_nameatt,null,attvalue23);
        sqLiteDatabase.insert(table_nameatt,null,attvalue24);
        sqLiteDatabase.insert(table_nameatt,null,attvalue25);
        sqLiteDatabase.insert(table_nameatt,null,attvalue26);
        sqLiteDatabase.insert(table_nameatt,null,attvalue27);
        sqLiteDatabase.insert(table_nameatt,null,attvalue28);
        sqLiteDatabase.insert(table_nameatt,null,attvalue29);

    }

    public ArrayList<HashMap<String,String>> getAttitudeList(){
        try{

            ArrayList<HashMap<String,String>> arr = new ArrayList<HashMap<String,String>>();
            HashMap<String,String> map;
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "SELECT * FROM " + tableName;
            Cursor cur = db.rawQuery(sql,null);
            if(cur!= null){
                if(cur.moveToFirst()){

                    do{
                        map = new HashMap<String,String>();
                        map.put("AttitudeID",cur.getString(0));
                        map.put("AttitudeWord",cur.getString(1));
                        map.put("AttitudeRank",cur.getString(2));
                        map.put("Emotion",cur.getString(3));
                        arr.add(map);

                    }while (cur.moveToNext());
                }

            }
            cur.close();
            db.close();
            return arr;
        }catch (Exception e){
            return null;
        }

    }

    public ArrayList<HashMap<String,String>> getEmotionList(){
        try {
            ArrayList<HashMap<String,String>> arr = new ArrayList<HashMap<String,String>>();
            HashMap<String, String> map;

            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "select * from " + tableName2;
            Cursor cursor = db.rawQuery(sql, null);
            if(cursor != null){
                if(cursor.moveToFirst()){
                    do{
                        map = new HashMap<String,String>();
                        map.put("EmotionID",cursor.getString(0));
                        map.put("EmotionWord",cursor.getString(1));
                        arr.add(map);
                    } while (cursor.moveToNext());
                }

            }
            cursor.close();
            db.close();
            return  arr;

        } catch (Exception e){
            return  null;
        }


    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
