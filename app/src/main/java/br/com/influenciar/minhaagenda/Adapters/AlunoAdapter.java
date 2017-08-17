package br.com.influenciar.minhaagenda.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import br.com.influenciar.minhaagenda.Dao.AlunoDAO;
import br.com.influenciar.minhaagenda.FormularioActivity;
import br.com.influenciar.minhaagenda.Models.Aluno;
import br.com.influenciar.minhaagenda.R;

/**
 * Created by USER on 17/08/2017.
 */

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.ViewHolder> {
    private final Context context;
    private List<Aluno> alunos;

    public AlunoAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }


    @Override
    public AlunoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_aluno, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Aluno aluno = alunos.get(position);
        holder.nome.setText(aluno.getNome());
        holder.endereco.setText(aluno.getEndereco());
        holder.telefone.setText(aluno.getTelefone());
        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                MenuItem menuVisitarSite = menu.add("Visitar Site");
                Intent intentVisitarSite = new Intent(Intent.ACTION_VIEW);
                intentVisitarSite.setData(Uri.parse("http://" + aluno.getSite()));
                menuVisitarSite.setIntent(intentVisitarSite);

                MenuItem menuDeletar = menu.add("Deletar");
                menuDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        AlunoDAO dao = new AlunoDAO(context);
                        alunos.remove(position);
                        dao.delete(aluno);
                        Toast.makeText(context, aluno.getNome() + " foi deletado", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        return true;
                    }
                });
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showEdit = new Intent(context, FormularioActivity.class);
                showEdit.putExtra("alunoSelecionado", aluno);
                context.startActivity(showEdit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        private TextView nome;
        private TextView endereco;
        private TextView telefone;

        public ViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.item_lista_aluno_nome);
            endereco = (TextView) itemView.findViewById(R.id.item_lista_aluno_endereco);
            telefone = (TextView) itemView.findViewById(R.id.item_lista_aluno_telefone);
        }
    }

}
