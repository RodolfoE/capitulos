package br.com.livroandroid.provider.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.livroandroid.provider.R;
import br.com.livroandroid.provider.agenda.Contato;

/**
 * Created by Usuário on 29/03/2015.
 */
public class ContatoAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private Context context;
    private List<Contato> contatos;

    public ContatoAdapter(Context context, List<Contato> contatos) {
        this.context = context;
        this.contatos = contatos;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return contatos != null ? contatos.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        Contato c = contatos.get(position);
        return c.id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(br.com.livroandroid.provider.R.layout.adapter_contato,parent, false);

        TextView tNome = (TextView) view.findViewById(br.com.livroandroid.provider.R.id.tNome);
        QuickContactBadge img = (QuickContactBadge) view.findViewById(br.com.livroandroid.provider.R.id.img);

        Contato c = contatos.get(position);

        Uri uriContato = c.getUri();
        tNome.setText(c.nome);

        img.assignContactUri(uriContato);
        Picasso.with(context).load(uriContato).into(img);

        return view;
    }
}
