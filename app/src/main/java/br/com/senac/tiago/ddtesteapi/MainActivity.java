package br.com.senac.tiago.ddtesteapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ListMenuItemView;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private EditText editClasse;
    private TextView tvClasse;

    private ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
       editClasse = findViewById(R.id.editClasse);
       tvClasse = findViewById(R.id.tvClasse);
       listView = findViewById(R.id.List_view);

    }

    public void pesquisar(View view) {
        String classeDigitada = editClasse.getText().toString();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://www.dnd5eapi.co/api/classes/" + classeDigitada, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                Toast.makeText(MainActivity.this, "Carregando...", Toast.LENGTH_SHORT).show();
                editClasse.setText("Carregando...");
            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String response = new String(bytes);
                Gson gson = new Gson();

                ClasseDTO classeDTO = gson.fromJson(response, ClasseDTO.class);


                Classe classe = classeDTO.getClasse();
                popularFormClasse(classe);

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Falhou. Erro: " + i, Toast.LENGTH_SHORT).show();
                editClasse.setText("Não existe essa classe. Tente outra, por exemplo 'bard'' ");
            }
        });
    }

    public void listar(View v){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://www.dnd5eapi.co/api/classes/", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String response = new String(bytes);
                Gson gson = new Gson();
                ClasseDTO classeDTO = gson.fromJson(response, ClasseDTO.class);

                popularListView(classeDTO.toString());

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });


    }

    private void popularFormClasse(Classe classe) {

        editClasse.setText(classe.getNomeClasse());
        tvClasse.setText(classe.getNomeClasse());
    }

    public void popularListView(String classeDTO) {
        try {
            JSONObject jsonObject = new JSONObject(classeDTO);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String indexClasse = object.getString("index");
                String nameClasse = object.getString("name");
                arrayList.add(indexClasse + ") " + nameClasse);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Initialize Array Adapter
        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,arrayList);
        //Set Array Adapter to ListView
        listView.setAdapter(arrayAdapter);
    }


}