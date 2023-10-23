package com.hacktivate8.todolist;

public class TodoList {
    int idKegiatan;
    String namaKegiatan;

    public TodoList() {
        super();
    }

    public TodoList(int mIdKegiatan, String mNamaKegiatan){
        super();
        this.idKegiatan = mIdKegiatan;
        this.namaKegiatan = mNamaKegiatan;
    }

    public TodoList(String mNamaKegiatan){
        this.namaKegiatan = mNamaKegiatan;
    }

    public int getIdKegiatan() {
        return idKegiatan;
    }

    public void setIdKegiatan(int idKegiatan) {
        this.idKegiatan = idKegiatan;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

}
