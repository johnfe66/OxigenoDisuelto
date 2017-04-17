package com.johnfe.oxigenodisuelto;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;


import java.util.ArrayList;




public class GraficaActivity extends AppCompatActivity {


    LineChart lineChart;
    LineChart oxigenoVsTemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica);



        oxigenoVsTemp= (LineChart) findViewById(R.id.lineChart2);


        lineChart= (LineChart) findViewById(R.id.lineChart);
        ArrayList<Long> tiempo = new ArrayList<>();
        ArrayList<Entry> oxigeno = new ArrayList<>();
        ArrayList<Entry> oxigenoVsTemperatura = new ArrayList<>();
        ArrayList<Entry> temperatura = new ArrayList<>();

        long fechaReferencia= ListaDatos.datos.get(0).getFecha().getTime();

        for (Datos dato : ListaDatos.datos  ){

            System.out.println("cambio de fechas: "+(dato.getFecha().getTime()-fechaReferencia)/1000);

            tiempo.add((dato.getFecha().getTime()-fechaReferencia)/1000);
            oxigeno.add(new  Entry((dato.getFecha().getTime()-fechaReferencia)/1000,Float.parseFloat(dato.getOxigeno())));
            temperatura.add(new Entry((dato.getFecha().getTime()-fechaReferencia)/1000,Float.parseFloat(dato.getTemperatura())));

            oxigenoVsTemperatura.add(new  Entry(Float.parseFloat(dato.getOxigeno()),Float.parseFloat(dato.getTemperatura())));

        }

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineDataSet lineDataSetOxigeno= new LineDataSet(oxigeno, "Oxigeno");
        lineDataSetOxigeno.setDrawCircles(true);
        lineDataSetOxigeno.setColor(Color.BLUE);

        LineDataSet lineDataSetTemperatura= new LineDataSet(temperatura, "Temperatura");
        lineDataSetTemperatura.setDrawCircles(true);
        lineDataSetTemperatura.setColor(Color.RED);

        lineDataSets.add(lineDataSetOxigeno);
        lineDataSets.add(lineDataSetTemperatura);

        LineDataSet lineDataSetOxigenoVsTemp= new LineDataSet(oxigenoVsTemperatura, "Oxigeno vs Temperatura");
        lineDataSetOxigenoVsTemp.setDrawCircles(true);
        lineDataSetOxigenoVsTemp.setColor(Color.GREEN);


        lineChart.setData(new LineData(lineDataSets));

        oxigenoVsTemp.setData(new LineData(lineDataSetOxigenoVsTemp));




    }
}
