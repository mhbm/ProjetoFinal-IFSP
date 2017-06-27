package com.example.mateusmacedo.projetofinalmateus.Uteis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mateus Macedo on 22/06/17.
 */

public class DatabaseUtil extends SQLiteOpenHelper {

    //Nome do banco de dados
    private static final String databaseName = "sistema.db";

    //Versão do banco de dados
    private static final int databaseVersion = 1;

    //Construtor
    public DatabaseUtil(Context context) {
        super(context,databaseName,null,databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder stringBuilderCreateTable = new StringBuilder();

        stringBuilderCreateTable.append(" CREATE TABLE pessoas (");
        stringBuilderCreateTable.append("        idPessoa      INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        stringBuilderCreateTable.append("        nome          TEXT     NOT NULL,            ");
        stringBuilderCreateTable.append("        cpf           INTEGER(11)  NOT NULL,            ");
        stringBuilderCreateTable.append("        idade         INTEGER  NOT NULL,            ");
        stringBuilderCreateTable.append("        telefone      INTEGER(11)  NOT NULL,            ");
        stringBuilderCreateTable.append("        email         TEXT     NOT NULL            )");


        db.execSQL(stringBuilderCreateTable.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS pessoas");
        onCreate(db);
    }

    public SQLiteDatabase getConexaoDataBase(){

        return this.getWritableDatabase();
    }
}
