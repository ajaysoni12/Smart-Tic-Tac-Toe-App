package com.example.tictactoegame;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    ImageView imgBack;
    TextView txtAppName;
    Button btnTwoPlayer, btnSmtCmp, btnEvlCmp;

    String player1Name, player2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

        Dialog dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.custom_dailog);

        TextView btnCancel = dialog.findViewById(R.id.btnCancel);
        TextView btnStart = dialog.findViewById(R.id.btnStart);

        // Take Players name from dialog box
        EditText edtPlayer1 = dialog.findViewById(R.id.edtPlayer1);
        EditText edtPlayer2 = dialog.findViewById(R.id.edtPlayer2);

        Intent iPlay = new Intent(HomeActivity.this, PlayActivity.class);

        btnEvlCmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edtPlayer1.setText("Evil Computer");

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btnStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (edtPlayer1.getText().toString().equals("")) {
                            edtPlayer1.setText("Smart Computer");
                        }
                        if (edtPlayer2.getText().toString().equals("")) {
                            edtPlayer2.setText("Player 2");
                        }

                        iPlay.putExtra("Player1", edtPlayer1.getText().toString());
                        iPlay.putExtra("Player2", edtPlayer2.getText().toString());
                        iPlay.putExtra("PlayWith", "3");
                        iPlay.putExtra("Option", "Evil Computer");
                        startActivity(iPlay);
                        dialog.dismiss();
                        edtPlayer1.setText("");
                        edtPlayer2.setText("");
                    }
                });

                dialog.show();
            }
        });

        btnSmtCmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edtPlayer1.setText("Smart Computer");

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btnStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (edtPlayer1.getText().toString().equals("")) {
                            edtPlayer1.setText("Smart Computer");
                        }
                        if (edtPlayer2.getText().toString().equals("")) {
                            edtPlayer2.setText("Player 2");
                        }

                        iPlay.putExtra("Player1", edtPlayer1.getText().toString());
                        iPlay.putExtra("Player2", edtPlayer2.getText().toString());
                        iPlay.putExtra("PlayWith", "2");
                        iPlay.putExtra("Option", "Smart Computer");
                        startActivity(iPlay);
                        dialog.dismiss();
                        edtPlayer1.setText("");
                        edtPlayer2.setText("");
                    }
                });

                dialog.show();
            }
        });

        btnTwoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edtPlayer1.setText("Player 1");
                edtPlayer2.setText("Player 2");

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btnStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (edtPlayer1.getText().toString().equals("")) {
                            edtPlayer1.setText("Player 1");
                        }
                        if (edtPlayer2.getText().toString().equals("")) {
                            edtPlayer2.setText("Player 2");
                        }

                        iPlay.putExtra("Player1", edtPlayer1.getText().toString());
                        iPlay.putExtra("Player2", edtPlayer2.getText().toString());
                        iPlay.putExtra("PlayWith", "1");
                        iPlay.putExtra("Option", "Two Player");
                        startActivity(iPlay);
                        dialog.dismiss();
                        edtPlayer1.setText("");
                        edtPlayer2.setText("");

                    }
                });

                dialog.show();

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txtAppName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Developed by Ajay Soni❤", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void init() {
        imgBack = findViewById(R.id.imgBack);
        txtAppName = findViewById(R.id.txtAppName);
        btnTwoPlayer = findViewById(R.id.btnTwoPlayer);
        btnSmtCmp = findViewById(R.id.btnSmtCmp);
        btnEvlCmp = findViewById(R.id.btnEvlCmp);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder exitDialog = new AlertDialog.Builder(HomeActivity.this);
        exitDialog.setTitle("Exit?");
        exitDialog.setMessage("Are you sure want to exit?");
        exitDialog.setIcon(R.drawable.ic_exit);

        exitDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HomeActivity.super.onBackPressed();
            }
        });

        exitDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(HomeActivity.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
            }
        });

        exitDialog.show();

    }

}