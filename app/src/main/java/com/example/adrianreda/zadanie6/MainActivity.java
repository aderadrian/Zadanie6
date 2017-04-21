package com.example.adrianreda.zadanie6;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private void initResources(){
        Resources res = getResources();
        lang = res.getStringArray(R.array.telefony);
    }

    private String[] lang;

    private ListView lv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.langagues);
        initResources();
        initLanguagesListView();
    }
    private void initLanguagesListView(){
        lv.setAdapter(new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1, lang));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                Context context;
                context = getApplicationContext();
                if(lang[pos].equals("Samsung Galaxy S6")) {
                    Intent intent = new Intent(context, com.example.adrianreda.zadanie6.gals6.class);
                    startActivity(intent);
                }else if(lang[pos].equals("Samsung Galaxy S7")){
                    Intent intent = new Intent(context, com.example.adrianreda.zadanie6.gals7.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(context, com.example.adrianreda.zadanie6.gals8.class);
                    startActivity(intent);
                }

            }
        });

    }
    public void Wyjscie(View view)
    {
        createAlertDialogWithButtons().show();
    }


    private Dialog createAlertDialogWithButtons() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Wyjście");
        dialogBuilder.setMessage("Czy napewno?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Tak", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(),"Wychodzę", Toast.LENGTH_LONG).
                        show();
                if(mediaPlayer != null) {
                    mediaPlayer.release();
                }
                MainActivity.this.finish();
            }
        });
        dialogBuilder.setNegativeButton("Nie", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getApplicationContext(),"Anulowaleś wyjście", Toast.LENGTH_LONG).
                        show();

            }
        });
        return dialogBuilder.create();
    }




    private MediaPlayer mediaPlayer;

    public void playSound(View view){
        createAlertDialogWithList().show();

        }
    public void stopSound(View view){
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }


    }


    public void play(){
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.mik);
        mediaPlayer.start();
    }
    public void play2(){
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.dim);
        mediaPlayer.start();
    }



    private Dialog createAlertDialogWithList() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final String[] options = {"Mike Posner-I took pill in Ibiza", "Dimitri Vegas, MOGUAI & Like Mike - Mammoth"};
        dialogBuilder.setTitle("Lista utworow");
        dialogBuilder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
               Toast.makeText(getApplicationContext(),"Wybrałeś: " + options[position], Toast.LENGTH_LONG).
                        show();
                if(options[position].equals("Mike Posner-I took pill in Ibiza")){
                    play();
                }else{
                    play2();
                }

            }
        });
        return dialogBuilder.create();
    }



}
