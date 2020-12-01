package com.projeto_padrao.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Agendamento;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.Usuario;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AgendAdapter extends BaseAdapter {

    Context context;
    List<Agendamento> agendamentos;
    private LayoutInflater mInflater;

    public AgendAdapter(Context context, List<Agendamento> agendamentos) {
        Comparator<Agendamento> comparator = new Comparator<Agendamento>() {
            @Override
            public int compare(Agendamento left, Agendamento right) {
                return (int) (left.getId() - right.getId()); // use your logic
            }
        };

        Collections.sort(agendamentos, comparator); // use the comparator as much as u want
        this.agendamentos = agendamentos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return agendamentos.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Agendamento agendamento = agendamentos.get(position);
        agendamento.setContext(context);
        Usuario usuarioLogado = Usuario.verificaUsuarioLogado();


        final AgendAdapter.ListContent holder;
        View v = convertView;
        if (v == null) {
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = mInflater.inflate(R.layout.agend_item_lista, null);
            holder = new AgendAdapter.ListContent();
            holder.agend_item_view = (View) v.findViewById(R.id.agend_item_view);
            holder.agend_lista_textview_nome = (TextView) v.findViewById(R.id.agend_lista_textview_nome);
            holder.agend_lista_textview_data = (TextView) v.findViewById(R.id.agend_lista_textview_data);
            holder.agend_lista_textview_horainicio = (TextView) v.findViewById(R.id.agend_lista_textview_horainicio);
            holder.agend_lista_textview_horafinal = (TextView) v.findViewById(R.id.agend_lista_textview_horafinal);



            holder.agend_item_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Aplicacao.AlertarpraAdicionar(context,agendamento);





                }
            });


            v.setTag(holder);
        } else {
            holder = (AgendAdapter.ListContent) v.getTag();
        }


        holder.agend_lista_textview_nome.setText(agendamento.getNome_agendamento());
        holder.agend_lista_textview_data.setText(agendamento.getData());
        holder.agend_lista_textview_horainicio.setText(agendamento.getHorainicio());
        holder.agend_lista_textview_horafinal.setText(agendamento.getHorafinal());

        return v;
    }

    public static class ListContent {
        TextView agend_lista_textview_nome, agend_lista_textview_horafinal, agend_lista_textview_data, agend_lista_textview_horainicio;
        View agend_item_view;
        ImageView agend_item_delete;
        ProgressBar agend_item_lista_progressBar;

    }
}

