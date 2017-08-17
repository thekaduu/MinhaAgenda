package br.com.influenciar.minhaagenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.List;

import br.com.influenciar.minhaagenda.Adapters.AlunoAdapter;
import br.com.influenciar.minhaagenda.Dao.AlunoDAO;
import br.com.influenciar.minhaagenda.Models.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private RecyclerView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        loadList();

        Button botaoAdicionar = (Button) findViewById(R.id.lista_alunos_botao_novo);
        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiParaFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intentVaiParaFormulario);
            }
        });
        registerForContextMenu(lista);
    }

    private void loadList() {
        lista = (RecyclerView) findViewById(R.id.lista_alunos);

        LinearLayoutManager layoutManager =  new LinearLayoutManager(this);
        lista.setLayoutManager(layoutManager);

        AlunoDAO alunodao = new AlunoDAO(this);
        List<Aluno> alunos = alunodao.all();
        alunodao.close();


        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        lista.setAdapter(new AlunoAdapter(this, alunos));

        lista.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
//        final Aluno aluno = (Aluno) lista.getItemAtPosition(info.position);
//
//        MenuItem itemFazerLigacao = menu.add("Ligar");
//        MenuItem itemEnviarSMS = menu.add("Enviar SMS");
//        MenuItem itemVisitarSite = menu.add("Visitar site");
//        MenuItem itemVisualizarEndereco = menu.add("Visualizar no Mapa");
//        MenuItem itemDeletar = menu.add("Deletar");
//
//
//        Intent intentSite = new Intent(Intent.ACTION_VIEW);
//        Intent intentSMS = new Intent(Intent.ACTION_VIEW);
//        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
//
//
//        String urlSite = aluno.getSite();
//        if (!urlSite.startsWith("http://")){
//            urlSite = "http://" + urlSite;
//        }
//
//        intentSite.setData(Uri.parse(urlSite));
//        itemVisitarSite.setIntent(intentSite);
//
//        intentSMS.setData(Uri.parse("sms:" + aluno.getTelefone()));
//        itemEnviarSMS.setIntent(intentSMS);
//
//        intentMapa.setData(Uri.parse("geo:0,0?q=" + aluno.getEndereco()));
//        itemVisualizarEndereco.setIntent(intentMapa);
//
//        itemFazerLigacao.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if (ActivityCompat.checkSelfPermission(ListaAlunosActivity.this, android.Manifest.permission.CALL_PHONE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(ListaAlunosActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, RequestPermissionsHelper.CALL_PHONE_CODE );
//                }else {
//                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
//                    intentLigar.setData(Uri.parse("tel:" + aluno.getTelefone()));
//                    startActivity(intentLigar);
//                }
//
//                return false;
//            }
//        });
//
//        itemDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
//                dao.delete(aluno);
//                loadList();
//
//                Toast.makeText(ListaAlunosActivity.this, "Aluno " + aluno.getNome() + " deletado com sucesso!", Toast.LENGTH_SHORT ).show();
//                return false;
//            }
//        });
//
//    }
}
