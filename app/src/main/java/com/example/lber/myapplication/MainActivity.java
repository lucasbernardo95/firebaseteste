package com.example.lber.myapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lber.myapplication.model.Anunciante;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btcadastro;
    EditText nome, cpf, contato, email;

    FirebaseDatabase database;
    DatabaseReference ref;

    List<Anunciante> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = (EditText) findViewById(R.id.campoNome);
        cpf = (EditText) findViewById(R.id.editcpf);
        contato = (EditText) findViewById(R.id.editcontato);
        email = (EditText) findViewById(R.id.editemail);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("anunciante");
        lista = new ArrayList<>();
    }

    public void cadastrarUsuario (View v) {

        Anunciante a = new Anunciante(
                nome.getText().toString(),
                cpf.getText().toString(),
                contato.getText().toString(),
                email.getText().toString()
        );

        ref.push().setValue(a);
    }

    public void buscarUsuario (View v) {

        Query query = ref;
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot ds, @Nullable String s) {
                lista.clear();
                Anunciante an = ds.getValue(Anunciante.class);
                lista.add(an);
                for (Anunciante b : lista){
                    Log.i("lista", "Dano lista: " + b.toString());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Toast.makeText(MainActivity.this, "buscar " + nome.getText(), Toast.LENGTH_SHORT).show();
    }

    public void exluirUsuario (View v) {
        final Query query = ref.orderByChild("cpf_cnpj").equalTo(cpf.getText().toString());//busca pelo cpf
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot ds, @Nullable String s) {
                Anunciante an = ds.getValue(Anunciante.class);
                database.getReference("anunciante/"+ds.getKey()).removeValue();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Toast.makeText(MainActivity.this, "excluir " + nome.getText(), Toast.LENGTH_SHORT).show();
    }

    public void alterarrUsuario (View v) {
        final Query query = ref.orderByChild("cpf_cnpj").equalTo(cpf.getText().toString());//busca pelo cpf
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot ds, @Nullable String s) {
                Anunciante an = ds.getValue(Anunciante.class);
                an.setContato(contato.getText().toString());
                an.setCpf_cnpj(cpf.getText().toString());
                an.setEmail(email.getText().toString());
                an.setNome(nome.getText().toString());

                Map<String, Object> att = new HashMap<>();
                att.put(ds.getKey(), an);

                ref.updateChildren(att);
                Log.i("alterar", "alterou");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toast.makeText(MainActivity.this, "alterar " + nome.getText(), Toast.LENGTH_SHORT).show();
    }
}
