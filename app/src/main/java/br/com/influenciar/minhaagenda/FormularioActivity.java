package br.com.influenciar.minhaagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;

import br.com.influenciar.minhaagenda.Dao.AlunoDAO;
import br.com.influenciar.minhaagenda.Helpers.FormularioHelper;
import br.com.influenciar.minhaagenda.Models.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("alunoSelecionado");
        if (aluno != null) {
            helper.preencheFormulario(aluno);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.getAluno();
                AlunoDAO dao = new AlunoDAO(this);
                String msg;
                if (aluno.getId() != null) {
                    dao.update(aluno);
                    msg = "Aluno " + aluno.getNome() + " atualizado com sucesso!";
                }else {
                    dao.insert(aluno);
                    msg = "Aluno " + aluno.getNome() + " salvo com sucesso!";
                }
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                dao.close();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
