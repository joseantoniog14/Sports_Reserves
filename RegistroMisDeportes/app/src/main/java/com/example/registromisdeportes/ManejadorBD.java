package com.example.practicafinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ManejadorBD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "practica.db";
    private static final String COL_ID="ID";
    private static final String COL_PREGUNTA="PREGUNTA";
    private static final String COL_CORRECTA="CORRECTA";
    private static final String COL_ERRONEA1="ERRONEA1";
    private static final String COL_ERRONEA2="ERRONEA2";
    private static final String TABLE_NAME="MOVILES";
    private static final String COL_ID2="ID2";
    private static final String COL_ACIERTOS="ACIERTOS";
    private static final String COL_FECHAA="FECHAA";
    private static final String TABLE_NAME2="LOGROS";
    private static final String COL_ID3="ID3";
    private static final String COL_FECHAA2="FECHAA2";
    private static final String BATERIA="BATERIA";
    private static final String COORX="COORX";
    private static final String COORY="COORY";
    private static final String TABLE_NAME3="ENTRADAS";
    public static Boolean sonido;
    public static String ultima="";
    public static String image="";

    public ManejadorBD(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    public ManejadorBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COL_PREGUNTA+" TEXT,"+COL_CORRECTA+" TEXT,"+COL_ERRONEA1+" TEXT,"+COL_ERRONEA2+" TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME2+" ("+COL_ID2+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COL_ACIERTOS+" TEXT,"+COL_FECHAA+" TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME3+" ("+COL_ID3+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COL_FECHAA2+" TEXT,"+BATERIA+" TEXT,"+COORX+" TEXT,"+COORY+" TEXT)");

    }
    public boolean insertar(String pregunta,String correcta, String erronea1, String erronea2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_PREGUNTA,pregunta);
        contentValues.put(COL_CORRECTA,correcta);
        contentValues.put(COL_ERRONEA1,erronea1);
        contentValues.put(COL_ERRONEA2,erronea2);
        long resultado = db.insert(TABLE_NAME,null,contentValues);
        db.close();
        return (resultado!=-1);
    }
    public boolean insertarLogros(String aciertos, String fecha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_ACIERTOS,aciertos);
        contentValues.put(COL_FECHAA,fecha);
        long resultado = db.insert(TABLE_NAME2,null,contentValues);
        db.close();
        return (resultado!=-1);
    }
    public boolean insertarEntradas(String fecha, String bateria, String x, String y){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_FECHAA2,fecha);
        contentValues.put(BATERIA,bateria);
        contentValues.put(COORX,x);
        contentValues.put(COORY,y);
        long resultado = db.insert(TABLE_NAME3,null,contentValues);
        db.close();
        return (resultado!=-1);
    }
    public boolean borrar(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int borrados = sqLiteDatabase.delete(TABLE_NAME,COL_ID+"=?",new String[]{id});
        sqLiteDatabase.close();
        return (borrados>0);
    }
    public void borrarLogros(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME2, null, null);
        //sqLiteDatabase.delete(TABLE_NAME3, null, null);
        sqLiteDatabase.close();
    }
    public Cursor listar(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }
    public Cursor listarLogros(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME2,null);
        return cursor;
    }
    public Cursor listarEntradas(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME3,null);
        return cursor;
    }
    public Cursor listarultimo(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME3+" ORDER BY "+COL_ID2+" DESC LIMIT 1",null);
        return cursor;
    }
    /*
    public Cursor listar2(String num1,String num2){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL_PRECIO+ " > cast(? as int) AND "+COL_PRECIO+" < cast(? as int)",new String[]{num1}, new String[]{num2});
        return cursor;
    }*/
    public Cursor leer(String id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL_ID+"=?",new String[]{id},null);
        return cursor;
    }
    public boolean actualizar(String id, String pregunta,String correcta, String erronea1, String erronea2){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PREGUNTA,pregunta);
        contentValues.put(COL_CORRECTA,correcta);
        contentValues.put(COL_ERRONEA1,erronea1);
        contentValues.put(COL_ERRONEA2,erronea2);
        long resultado= sqLiteDatabase.update(TABLE_NAME,contentValues,COL_ID+"=?",new String[]{id});
        sqLiteDatabase.close();
        return (resultado>0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
