package com.example.final1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {

        static String DATABASE_NAME="ProjectDataBase";

        public static final String TABLE_NAME="StudentTable";

        public static final String TABLE_NAME_1 = "VendorTable";

        public static final String Table_Column_ID="id";

        public static final String Table_Column_1_Name="name";

        public static final String Table_Column_2_Rollno="rollno";

        public static final String Table_Column_3_Email="email";

        public static final String Table_Column_4_Password="password";

        public static final String Table_Column_VendorID = "vendorid";

        public static final String Table_Column_1_VName = "vname";

        public static final String Table_Column_2_location = "vlocation";

        public static final String Table_Column_3_Vid = "vid";

        public static final String Table_Column_4_VEmail = "vemail";

        public static final String Table_Column_5_VPassword = "vpassword";

        public SQLiteHelper(Context context) {

            super(context, DATABASE_NAME, null, 2);
        }
    public void insertData(String name, String price, byte[] image, String email){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO FOOOD VALUES (NULL, ?, ?, ?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);
        statement.bindString(4, email);
        statement.executeInsert();
    }
    public void insertData1(String name, String price, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO HOTEL VALUES (NULL, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);

        statement.executeInsert();
    }
    public void insertData3(String name, String price, String quantity, String total, String status,String email){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO ITEMORDERS VALUES (NULL, ?, ?, ?, ?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindString(3, quantity);
        statement.bindString(4, total);
        statement.bindString(5, status);
        statement.bindString(6, email);

        statement.executeInsert();
    }
    public void insertData4(String name, String price, byte[] image, String email){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO STATIONARY VALUES (NULL, ?, ?, ?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);
        statement.bindString(4, email);
        statement.executeInsert();
    }
    public void updateData(String name, String price, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE FOOOD SET name = ?, price = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();
    }
    public void updateData1(String name, String price, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE HOTEL SET name = ?, price = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();
    }
    public void updateData2(String vname, String vlocation,String vid,String vemail,String vpassword, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE VendorTable SET vname = ?, vlocation = ?, vid = ?, vemail = ?, vpassword = ? WHERE vendorid = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, vname);
        statement.bindString(2, vlocation);
        statement.bindString(3, vid);
        statement.bindString(4, vemail);
        statement.bindString(5, vpassword);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();
    }
    public void updateData3(String name, String rollno,String email,String password, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE StudentTable SET name = ?, rollno = ?, email = ?, password = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, rollno);
        statement.bindString(4, email);
        statement.bindString(5, password);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();
    }
    public void updateData4(String name, String price, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE STATIONARY SET name = ?, price = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();
    }
    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM FOOOD WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }
    public  void deleteData1(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM HOTEL WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }
    public  void deleteData2(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM ITEMORDERS WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }
    public  void deleteData3(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM VendorTable WHERE vendorid = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteData4(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM StudentTable WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteData5(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM STATIONARY WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

        @Override
        public void onCreate(SQLiteDatabase database) {

            String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR,"+Table_Column_2_Rollno+" VARCHAR, "+Table_Column_3_Email+" VARCHAR, "+Table_Column_4_Password+" VARCHAR)";
            database.execSQL(CREATE_TABLE);
            String CREATE_TABLE_1="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_1+" ("+Table_Column_VendorID+" INTEGER PRIMARY KEY, "+Table_Column_1_VName+" VARCHAR,"+Table_Column_2_location+" VARCHAR, "+Table_Column_3_Vid+" VARCHAR, "+Table_Column_4_VEmail+" VARCHAR, "+Table_Column_5_VPassword+"VARCHAR)";
            database.execSQL(CREATE_TABLE_1);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_1);
            onCreate(db);

        }
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
}
