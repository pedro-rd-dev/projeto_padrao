package com.projeto_padrao.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Aplicacao;
import com.projeto_padrao.models.chamados.Chamado;
import com.projeto_padrao.models.Usuario;

import java.util.List;

public class ChamadosAdapter extends BaseAdapter {

    Context context;
    List<Chamado> chamados;
    private LayoutInflater mInflater;

    public ChamadosAdapter(Context context, List<Chamado> chamados) {
        this.chamados = chamados;
        this.context = context;
    }

    @Override
    public int getCount() {
        return chamados.size();
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
        final Chamado chamado = chamados.get(position);
        Usuario usuarioLogado = Usuario.verificaUsuarioLogado();


        final ChamadosAdapter.ListContent holder;
        View v = convertView;
        if (v == null) {
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = mInflater.inflate(R.layout.chamados_item_lista, null);
            holder = new ChamadosAdapter.ListContent();
            holder.chamados_lista_textview_titulo = (TextView) v.findViewById(R.id.chamados_lista_textview_titulo);
            //holder.chamados_lista_textview_descricao = (TextView) v.findViewById(R.id.chamados_lista_textview_descricao);
            holder.chamados_item_view = (View) v.findViewById(R.id.chamados_item_view);
            holder.chamados_lista_textview_dataAbertura = (TextView) v.findViewById(R.id.chamados_lista_textview_dataAbertura);
            holder.chamados_lista_textview_horaAbertura = (TextView) v.findViewById(R.id.chamados_lista_textview_horaAbertura);









            holder.chamados_item_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Aplicacao.irParaChamadoDetalheActivity(context, chamado.getId());
                }
            });








            v.setTag(holder);
        } else {
            holder = (ChamadosAdapter.ListContent) v.getTag();
        }

        holder.chamados_lista_textview_titulo.setText(chamado.getTitulo());
        holder.chamados_lista_textview_dataAbertura.setText(chamado.getDataAbertura());
        holder.chamados_lista_textview_horaAbertura.setText(chamado.getHorarioAbertura());
        /*holder.chamados_lista_textview_descricao.setText(chamado.getDescricao());*/




        return v;
    }

    public static class ListContent {
        TextView chamados_lista_textview_titulo,chamados_lista_textview_descricao, chamados_lista_textview_dataAbertura, chamados_lista_textview_horaAbertura;
        View chamados_item_view;
        /*ImageView chamados_item_delete;
        ProgressBar chamados_item_lista_progressBar;*/
    }


}