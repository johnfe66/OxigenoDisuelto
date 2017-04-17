package com.johnfe.oxigenodisuelto;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {


    TextView temperatura;
    TextView oxigeno;
    Button btnBuscar;
    EditText fecha1;
    EditText hora1;
    EditText fecha2;
    EditText hora2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference temper = database.getReference("realtime").child("temperatura");
        DatabaseReference oxigen = database.getReference("realtime").child("oxigeno");



        btnBuscar= (Button) findViewById(R.id.btnBuscar);
        fecha1= (EditText) findViewById(R.id.fecha1);
        fecha2= (EditText) findViewById(R.id.fecha2);
        hora1= (EditText) findViewById(R.id.hora1);
        hora2= (EditText) findViewById(R.id.hora2);

        temperatura= (TextView) findViewById(R.id.txtTemperaturaMain);
        oxigeno= (TextView) findViewById(R.id.txtOxigeno1);

        temper.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                temperatura.setText(value+"°");
                Log.d("", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        });
        oxigen.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                oxigeno.setText(value+"%");
                Log.d("", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        });



        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, ListaDatos.class);
                Bundle bundle= new Bundle();
                bundle.putString("fecha1",fecha1.getText().toString().trim()+hora1.getText().toString().trim());
                bundle.putString("fecha2",fecha2.getText().toString().trim()+hora2.getText().toString().trim());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }


}
