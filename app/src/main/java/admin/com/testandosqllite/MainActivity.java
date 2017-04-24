package admin.com.testandosqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase db = openOrCreateDatabase("MeuApp", MODE_PRIVATE, null);

            db.execSQL("CREATE TABLE IF NOT EXISTS cadastropessoas (nome VARCHAR, idade INT(3))");

            db.execSQL("INSERT INTO cadastropessoas (nome, idade) VALUES ('Um nome legal', 20)");
            db.execSQL("INSERT INTO cadastropessoas (nome, idade) VALUES ('Um nome legal pra caramba', 22)");
            db.execSQL("INSERT INTO cadastropessoas (nome, idade) VALUES ('Um nome legal mesmo', 10)");

            Cursor cursor = db.rawQuery("SELECT nome, idade FROM cadastropessoas", null);

            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while(cursor != null) {
                Log.i("LogX", cursor.getString(indiceNome));
                Log.i("LogX", cursor.getString(indiceIdade));
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
