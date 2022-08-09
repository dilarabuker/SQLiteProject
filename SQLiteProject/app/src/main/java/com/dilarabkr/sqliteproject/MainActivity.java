//NOT: veritabanını sıfırlamanın en kolay yolu telefondan appi silmek..
package com.dilarabkr.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY, name VARCHAR, age INTEGER)"); // if not exist sayesinde aynı tabloyu iki kere oluşturmanın önüne geçiyıruz yani eğer böyle bir tablo yoksa oluştur diyoruz

            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('JAMES',50)");
            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('Lars',60)");
            //database.execSQL("INSERT INTO musicians (name, age) VALUES ('Dilara',22)");
            //database.execSQL("UPDATE musicians SET age = 61 WHERE name = 'Lars'");
            //database.execSQL("DELETE FROM musicians WHERE id=6 ");
//Bir kere çalıştırdıktan sonra yorum haline getirebiliriz çünkü aynı veriyi her çalıştırıldığında tekrar tekrar kaydetmesini istemeyiz

            Cursor cursor = database.rawQuery("SELECT * FROM musicians" , null);
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE İD = 2" , null);

            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");
            int idIndex = cursor.getColumnIndex("id");

            while (cursor.moveToNext())  {
                System.out.println("Name: "+ cursor.getString(nameIndex));
                System.out.println("Age: "+cursor.getInt(ageIndex));
                System.out.println("ID: "+cursor.getInt(idIndex));
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}