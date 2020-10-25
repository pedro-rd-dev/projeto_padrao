package com.projeto_padrao.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projeto_padrao.R;
import com.projeto_padrao.models.Usuario;

import java.util.List;

public class UsuariosAdapter extends BaseAdapter {


    Context context;
    List<Usuario> usuarios;
    private LayoutInflater mInflater;

    public UsuariosAdapter(Context context, List<Usuario> usuarios) {
        this.usuarios = usuarios;
        this.context = context;
    }

    @Override
    public int getCount() {
        return usuarios.size();
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
        final Usuario usuario = usuarios.get(position);

        final UsuariosAdapter.ListContent holder;
        View v = convertView;
        if (v == null) {
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = mInflater.inflate(R.layout.usuarios_item_lista, null);
            holder = new UsuariosAdapter.ListContent();
            holder.usuarios_lista_textview_email = (TextView) v.findViewById(R.id.usuarios_lista_textview_email);
            holder.usuarios_lista_textview_nome = (TextView) v.findViewById(R.id.usuarios_lista_textview_nome);



            v.setTag(holder);
        } else {
            holder = (UsuariosAdapter.ListContent) v.getTag();
        }

        holder.usuarios_lista_textview_email.setText(usuario.getEmail());
        holder.usuarios_lista_textview_nome.setText(usuario.getNome());

        return v;
    }

    public static class ListContent {
        TextView usuarios_lista_textview_email,usuarios_lista_textview_nome;
    }
}