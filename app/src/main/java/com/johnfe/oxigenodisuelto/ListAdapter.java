package com.johnfe.oxigenodisuelto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Johnfe Vargas on 2017-04-06.
 */

public class ListAdapter extends BaseAdapter {

    ListaDatos main;

    public ListAdapter(ListaDatos main) {
        this.main = main;
    }

    @Override
    public int getCount() {

        return  main.datos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolderItem {
        TextView id;
        TextView fecha;
        TextView temperatura;
        TextView oxigeno;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) main.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lista_valores, null);

            holder.id = (TextView) convertView.findViewById(R.id.txtId);
            holder.fecha = (TextView) convertView.findViewById(R.id.txtHora);
            holder.temperatura = (TextView) convertView.findViewById(R.id.txtTemperatura);
            holder.oxigeno = (TextView) convertView.findViewById(R.id.txtOxigeno);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolderItem) convertView.getTag();
        }


        holder.id.setText("Id: "+this.main.datos.get(position).getId());
        holder.fecha.setText("Fecha: "+this.main.datos.get(position).getFecha());
        holder.temperatura.setText("Temperatura: "+this.main.datos.get(position).getTemperatura()+"°");
        holder.oxigeno.setText("Oxigeno Disuelto: "+this.main.datos.get(position).getOxigeno()+"%");

        return convertView;
    }


}
