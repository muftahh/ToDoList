package com.hacktivate8.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    private List<TodoList> todoListList;
    private Context context;

    public TodoListAdapter(Context context, List<TodoList> todoListList){
        this.context = context;
        this.todoListList = todoListList;
    }

    public TodoListAdapter(Context context, List<TodoList> todoListList, hapusKegiatanListener hapusListener){
        this.context = context;
        this.todoListList = todoListList;
        this.hapusListener = hapusListener;
    }


    @NonNull
    @Override
    public TodoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListAdapter.ViewHolder holder, int position) {
        TodoList todoList = todoListList.get(position);

        holder.tv_namaKegiatan.setText(todoList.getNamaKegiatan());
    }

    @Override
    public int getItemCount() {
        return todoListList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_namaKegiatan;
        private ImageButton b_hapusKegiatan;

        public ViewHolder(@NonNull View itemview){
            super(itemview);

            tv_namaKegiatan = itemview.findViewById(R.id.tv_nama_kegiatan);
            b_hapusKegiatan = itemview.findViewById(R.id.b_hapus);

            b_hapusKegiatan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hapusListener.onHapusKegiatan(getAdapterPosition());
                }
            });
        }
    }

    public interface hapusKegiatanListener{
        void onHapusKegiatan(int position);
    }

    private hapusKegiatanListener hapusListener;
}
