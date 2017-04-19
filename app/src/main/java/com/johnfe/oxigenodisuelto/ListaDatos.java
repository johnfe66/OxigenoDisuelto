package com.johnfe.oxigenodisuelto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ListaDatos extends AppCompatActivity {


    public ListView list;
    public static  ArrayList<Datos> datos = new ArrayList<Datos>();
    public ListAdapter adapter;
    public Button btnGraficar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_datos);

        btnGraficar= (Button) findViewById(R.id.btnGrafica);

        btnGraficar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ListaDatos.this, GraficaActivity.class);
                startActivity(intent);
            }
        });

        list = (ListView) findViewById(R.id.Lista);
        adapter = new ListAdapter(this);
        list.setAdapter(adapter);




        Bundle bundle;
        bundle= this.getIntent().getExtras();

        new ConsumirREST().execute("http://54.202.17.175:8080/Dosificador/recursos/rest/variablesFecha?" +
                        "inicio="+bundle.getString("fecha1")+"&"+
                        "fin="+bundle.getString("fecha2")
                ,"hola soy el parametro 2");


    }


    public class ConsumirREST extends AsyncTask<String,String,String> {

        private ProgressDialog progreso;

        @Override protected void onPreExecute() {
            progreso = new ProgressDialog(ListaDatos.this);
            progreso.setMessage("Leyendo datos...");
            progreso.setCancelable(false);
            progreso.show();

        }
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection=null;
            BufferedReader reader=null;

            try {

                System.out.println(params[0]);
                URL url = new URL(params[0]);
                System.out.println(params[1]);
                connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line="";

                while ((line=reader.readLine())!=null){
                    buffer.append(line);

                }
                System.out.println(buffer.toString());

                String respuestaJson = buffer.toString();


               String mensaje ="no hay registros";
                JSONArray jsonList = new JSONArray(respuestaJson);

                datos.clear();
                for (int i = 0; i < jsonList.length(); ++i){

                    Datos registroDato = new Datos();

                    JSONObject registro = jsonList.getJSONObject(i);

                    registroDato.setId(registro.getString("id"));
                    registroDato.setFecha(Timestamp.valueOf(registro.getString("fecha")));
                    registroDato.setTemperatura(registro.getString("temperatura"));
                    registroDato.setOxigeno(registro.getString("oxigeno"));


                    datos.add(registroDato);
                }



                return mensaje;




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection!=null){
                    connection.disconnect();
                }

                if(reader!=null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(String mensaje) {
            progreso.dismiss();
            super.onPostExecute(mensaje);

            adapter.notifyDataSetChanged();

        }
    }
}
