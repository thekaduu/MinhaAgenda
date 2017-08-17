package br.com.influenciar.minhaagenda.Helpers;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.influenciar.minhaagenda.FormularioActivity;
import br.com.influenciar.minhaagenda.Models.Aluno;
import br.com.influenciar.minhaagenda.R;

/**
 * Created by USER on 03/08/2017.
 */

public class FormularioHelper {
    private EditText nome;
    private EditText endereco;
    private EditText telefone;
    private EditText site;
    private RatingBar nota;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity) {
        this.nome = (EditText) activity.findViewById(R.id.formulario_edit_text_nome);
        this.endereco = (EditText) activity.findViewById(R.id.formulario_edit_text_endereco);
        this.telefone = (EditText) activity.findViewById(R.id.formulario_edit_text_telefone);
        this.nota = (RatingBar) activity.findViewById(R.id.formulario_edit_text_nota);
        this.site = (EditText) activity.findViewById(R.id.formulario_edit_text_site);
        this.aluno = new Aluno();

    }

    public Aluno getAluno() {
        aluno.setNome(this.nome.getText().toString());
        aluno.setEndereco(this.endereco.getText().toString());
        aluno.setTelefone(this.telefone.getText().toString());
        aluno.setSite(this.site.getText().toString());
        aluno.setNota(Double.valueOf(this.nota.getProgress()));
        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        this.nome.setText(aluno.getNome());
        this.endereco.setText(aluno.getEndereco());
        this.telefone.setText(aluno.getTelefone());
        this.site.setText(aluno.getSite());
        this.nota.setProgress(aluno.getNota().intValue());
        this.aluno = aluno;
    }
}