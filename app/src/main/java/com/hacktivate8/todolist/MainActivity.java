package com.hacktivate8.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TodoListAdapter.hapusKegiatanListener {
    private ImageButton buttonAdd;
    private RecyclerView rvList;

    DataBaseHandler db;
    private List<TodoList> todoListList;
    private TodoListAdapter todoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.b_tambah);
        rvList = findViewById(R.id.rv_list);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showForm();
            }
        });

        db = new DataBaseHandler(this);
        loadData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(layoutManager);
    }

    private void loadData(){
        todoListList = db.getAllKegiatan();
        todoListAdapter = new TodoListAdapter(this, todoListList, this);
        rvList.setAdapter(todoListAdapter);
    }

    private void showForm() {
        AlertDialog.Builder formBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.form_todo, null);
        formBuilder.setView(view);

        AlertDialog popup = formBuilder.create();
        popup.show();

        EditText f_et_namakegiatan = view.findViewById(R.id.form_nama_kegiatan);
        Button b_addform = view.findViewById(R.id.form_b_tambah);

        b_addform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TodoList todoList = new TodoList(f_et_namakegiatan.getText().toString());
                db.addKegiatan(todoList);

                loadData();
                popup.dismiss();
            }
        });
    }

    @Override
    public void onHapusKegiatan(int position) {
        TodoList selectedHapus = todoListList.get(position);
        db.hapusKegiatan(selectedHapus);
        loadData();
    }
}