package com.example.snowt.omnipucks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by higha_000 on 2016-12-06.
 */

public class LocalAdapter extends ArrayAdapter<Local>
{
    public LocalAdapter(Context context, int textViewResourceId)
    {
        super(context, textViewResourceId);
    }

    public  LocalAdapter(Context context, int resource, List<Local> items)
    {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View maView = convertView;

        List<String> logicielsstring = new ArrayList<>();

        if (maView == null)
        {
            LayoutInflater viewInflater = LayoutInflater.from(getContext());
            maView = viewInflater.inflate(R.layout.itemlistelocal, null);
        }

        Local l  = getItem(position);

        if(l != null)
        {
            TextView tvNom = (TextView) maView.findViewById(R.id.tvNom);
            TextView tvNumero = (TextView) maView.findViewById(R.id.tvNumero);
            TextView tvType = (TextView) maView.findViewById(R.id.tvType);
            TextView tvListe = (TextView) maView.findViewById(R.id.tvListe);

            if (tvNom != null)
            {
                tvNom.setText(l.getNom());
            }

            if (tvNumero != null)
            {
                tvNumero.setText(l.getNumero());
            }
            if (tvType != null)
            {
                tvType.setText(l.getTypelocal());
            }



            if (tvListe != null)
            {
                tvListe.setText(l.getLogiciels().toString());

            }
        }

        return maView;
    }
}
