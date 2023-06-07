package com.example.tictactoegame;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.util.HashMap;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    ImageView imgBack;
    LottieAnimationView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    String b1 = "", b2 = "", b3 = "", b4 = "", b5 = "", b6 = "", b7 = "", b8 = "", b9 = "";
    TextView txtAppName, txtPlayer1Name, txtPlayer2Name, txtPlayer1Score, txtPlayer2Score, txtPlayerTurns;
    Button btnRestartGame;

    LinearLayout llPlayer1, llPlayer2;
    int player1Score = 0, player2Score = 0;
    String player1Symbol, player2Symbol;
    int cntMoves = 0;
    boolean flag = false;

    String playWith;


    HashMap<Integer, Integer> hashMap = new HashMap<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        init();

        Intent getPlayer = getIntent();
        String Player1 = getPlayer.getStringExtra("Player1");
        String Player2 = getPlayer.getStringExtra("Player2");
        playWith = getPlayer.getStringExtra("PlayWith");

        txtPlayer1Name.setText("" + Player1);
        txtPlayer2Name.setText("" + Player2);
        txtPlayer1Score.setText(Integer.toString(player1Score));
        txtPlayer2Score.setText(Integer.toString(player2Score));
        txtAppName.setText(getPlayer.getStringExtra("Option"));


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txtAppName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PlayActivity.this, "Developed by Ajay Soni❤", Toast.LENGTH_SHORT).show();
            }
        });

        LottieAnimationView imgPlayer1 = findViewById(R.id.imgPlayer1);
        LottieAnimationView imgPlayer2 = findViewById(R.id.imgPlayer2);
        imgPlayer1.setAnimation(R.raw.cross);
        imgPlayer2.setAnimation(R.raw.circle);
        imgPlayer1.playAnimation();
        imgPlayer2.playAnimation();
        player1Symbol = "X";
        player2Symbol = "O";
        txtPlayerTurns.setText(txtPlayer1Name.getText().toString() + " turns");
        llPlayer1.setBackgroundResource(R.drawable.square_background);

        if (playWith.equals("2")) {
            int id = smtCmpMove();
            Play(findViewById(id));
        } else if (playWith.equals("3")) {
            int id = smtCmpMove();
            Play(findViewById(id));
        }

        btnRestartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame("X");
            }
        });


    }

    public boolean opponentPlayerWin() {
        boolean temp = false;
        if ((b1.equals("0") && b2.equals("0")) || (b1.equals("0") && b3.equals("0")) || (b2.equals("0") && b3.equals("0"))) {
            if (b1.equals(""))
                temp = true;
            if (b2.equals(""))
                temp = true;
            if (b3.equals(""))
                temp = true;
        }

        // second row
        if ((b4.equals("0") && b5.equals("0")) || (b4.equals("0") && b6.equals("0")) || (b5.equals("0") && b6.equals("0"))) {
            if (b4.equals(""))
                temp = true;
            if (b5.equals(""))
                temp = true;
            if (b6.equals(""))
                temp = true;
        }

        // third row
        if ((b7.equals("O") && b8.equals("O")) || (b7.equals("O") && b9.equals("O")) || (b8.equals("O") && b9.equals("O"))) {
            if (b7.equals(""))
                temp = true;
            if (b8.equals(""))
                temp = true;
            if (b9.equals(""))
                temp = true;
        }

        // first col
        if ((b1.equals("O") && b4.equals("O")) || (b1.equals("O") && b7.equals("O")) || (b4.equals("O") && b7.equals("O"))) {
            if (b1.equals(""))
                temp = true;
            if (b4.equals(""))
                temp = true;
            if (b7.equals(""))
                temp = true;
        }

        // second col
        if ((b2.equals("O") && b5.equals("O")) || (b2.equals("O") && b8.equals("O")) || (b5.equals("O") && b8.equals("O"))) {
            if (b2.equals(""))
                temp = true;
            if (b5.equals(""))
                temp = true;
            if (b8.equals(""))
                temp = true;
        }

        // third col
        if ((b3.equals("O") && b6.equals("O")) || (b3.equals("O") && b9.equals("O")) || (b6.equals("O") && b9.equals("O"))) {
            if (b3.equals(""))
                temp = true;
            if (b6.equals(""))
                temp = true;
            if (b9.equals(""))
                temp = true;
        }

        // left diagonal
        if ((b1.equals("O") && b5.equals("O")) || (b1.equals("O") && b9.equals("O")) || (b5.equals("O") && b9.equals("O"))) {
            if (b1.equals(""))
                temp = true;
            if (b5.equals(""))
                temp = true;
            if (b9.equals(""))
                temp = true;
        }

        // right diagonal
        if ((b3.equals("O") && b5.equals("O")) || (b3.equals("O") && b7.equals("O")) || (b5.equals("O") && b7.equals("O"))) {
            if (b3.equals(""))
                temp = true;
            if (b5.equals(""))
                temp = true;
            if (b7.equals(""))
                temp = true;
        }
        return temp;
    }

    public int smtCmpMove() {

        // computer win condition
        // first row
        if ((b1.equals("X") && b2.equals("X")) || (b1.equals("X") && b3.equals("X")) || (b2.equals("X") && b3.equals("X"))) {
            if (b1.equals(""))
                return R.id.img1;
            if (b2.equals(""))
                return R.id.img2;
            if (b3.equals(""))
                return R.id.img3;
        }

        // second row
        if ((b4.equals("X") && b5.equals("X")) || (b4.equals("X") && b6.equals("X")) || (b5.equals("X") && b6.equals("X"))) {
            if (b4.equals(""))
                return R.id.img4;
            if (b5.equals(""))
                return R.id.img5;
            if (b6.equals(""))
                return R.id.img6;
        }

        // third row
        if ((b7.equals("X") && b8.equals("X")) || (b7.equals("X") && b9.equals("X")) || (b8.equals("X") && b9.equals("X"))) {
            if (b7.equals(""))
                return R.id.img7;
            if (b8.equals(""))
                return R.id.img8;
            if (b9.equals(""))
                return R.id.img9;
        }

        // first col
        if ((b1.equals("X") && b4.equals("X")) || (b1.equals("X") && b7.equals("X")) || (b4.equals("X") && b7.equals("X"))) {
            if (b1.equals(""))
                return R.id.img1;
            if (b4.equals(""))
                return R.id.img4;
            if (b7.equals(""))
                return R.id.img7;
        }

        // second col
        if ((b2.equals("X") && b5.equals("X")) || (b2.equals("X") && b8.equals("X")) || (b5.equals("X") && b8.equals("X"))) {
            if (b2.equals(""))
                return R.id.img2;
            if (b5.equals(""))
                return R.id.img5;
            if (b8.equals(""))
                return R.id.img8;
        }

        // third col
        if ((b3.equals("X") && b6.equals("X")) || (b3.equals("X") && b9.equals("X")) || (b6.equals("X") && b9.equals("X"))) {
            if (b3.equals(""))
                return R.id.img3;
            if (b6.equals(""))
                return R.id.img6;
            if (b9.equals(""))
                return R.id.img9;
        }

        // left diagonal
        if ((b1.equals("X") && b5.equals("X")) || (b1.equals("X") && b9.equals("X")) || (b5.equals("X") && b9.equals("X"))) {
            if (b1.equals(""))
                return R.id.img1;
            if (b5.equals(""))
                return R.id.img5;
            if (b9.equals(""))
                return R.id.img9;
        }

        // right diagonal
        if ((b3.equals("X") && b5.equals("X")) || (b3.equals("X") && b7.equals("X")) || (b5.equals("X") && b7.equals("X"))) {
            if (b3.equals(""))
                return R.id.img3;
            if (b5.equals(""))
                return R.id.img5;
            if (b7.equals(""))
                return R.id.img7;
        }

        // Player 2 Win Stop Condition
        // first row
        if ((b1.equals("O") && b2.equals("O")) || (b1.equals("O") && b3.equals("O")) || (b2.equals("O") && b3.equals("O"))) {
            if (b1.equals(""))
                return R.id.img1;
            if (b2.equals(""))
                return R.id.img2;
            if (b3.equals(""))
                return R.id.img3;
        }

        // second row
        if ((b4.equals("O") && b5.equals("O")) || (b4.equals("O") && b6.equals("O")) || (b5.equals("O") && b6.equals("O"))) {
            if (b4.equals(""))
                return R.id.img4;
            if (b5.equals(""))
                return R.id.img5;
            if (b6.equals(""))
                return R.id.img6;
        }

        // third row
        if ((b7.equals("O") && b8.equals("O")) || (b7.equals("O") && b9.equals("O")) || (b8.equals("O") && b9.equals("O"))) {
            if (b7.equals(""))
                return R.id.img7;
            if (b8.equals(""))
                return R.id.img8;
            if (b9.equals(""))
                return R.id.img9;
        }

        // first col
        if ((b1.equals("O") && b4.equals("O")) || (b1.equals("O") && b7.equals("O")) || (b4.equals("O") && b7.equals("O"))) {
            if (b1.equals(""))
                return R.id.img1;
            if (b4.equals(""))
                return R.id.img4;
            if (b7.equals(""))
                return R.id.img7;
        }

        // second col
        if ((b2.equals("O") && b5.equals("O")) || (b2.equals("O") && b8.equals("O")) || (b5.equals("O") && b8.equals("O"))) {
            if (b2.equals(""))
                return R.id.img2;
            if (b5.equals(""))
                return R.id.img5;
            if (b8.equals(""))
                return R.id.img8;
        }

        // third col
        if ((b3.equals("O") && b6.equals("O")) || (b3.equals("O") && b9.equals("O")) || (b6.equals("O") && b9.equals("O"))) {
            if (b3.equals(""))
                return R.id.img3;
            if (b6.equals(""))
                return R.id.img6;
            if (b9.equals(""))
                return R.id.img9;
        }

        // left diagonal
        if ((b1.equals("O") && b5.equals("O")) || (b1.equals("O") && b9.equals("O")) || (b5.equals("O") && b9.equals("O"))) {
            if (b1.equals(""))
                return R.id.img1;
            if (b5.equals(""))
                return R.id.img5;
            if (b9.equals(""))
                return R.id.img9;
        }

        // right diagonal
        if ((b3.equals("O") && b5.equals("O")) || (b3.equals("O") && b7.equals("O")) || (b5.equals("O") && b7.equals("O"))) {
            if (b3.equals(""))
                return R.id.img3;
            if (b5.equals(""))
                return R.id.img5;
            if (b7.equals(""))
                return R.id.img7;
        }

        Random random = new Random();
        int min = 1;
        int max = 9;
        int randNum = random.nextInt(max - min + 1) + min;
        if (randNum == 1 && b1.equals(""))
            return R.id.img1;
        if (randNum == 2 && b2.equals(""))
            return R.id.img2;
        if (randNum == 3 && b3.equals(""))
            return R.id.img3;
        if (randNum == 4 && b4.equals(""))
            return R.id.img4;
        if (randNum == 5 && b5.equals(""))
            return R.id.img5;
        if (randNum == 6 && b6.equals(""))
            return R.id.img6;
        if (randNum == 7 && b7.equals(""))
            return R.id.img7;
        if (randNum == 8 && b8.equals(""))
            return R.id.img8;
        if (randNum == 9 && b9.equals(""))
            return R.id.img9;
        if (b1.equals(""))
            return R.id.img1;
        if (b2.equals(""))
            return R.id.img2;
        if (b3.equals(""))
            return R.id.img3;
        if (b4.equals(""))
            return R.id.img4;
        if (b5.equals(""))
            return R.id.img5;
        if (b6.equals(""))
            return R.id.img6;
        if (b7.equals(""))
            return R.id.img7;
        if (b8.equals(""))
            return R.id.img8;
        return R.id.img9;

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void init() {

        llPlayer1 = findViewById(R.id.llPlayer1);
        llPlayer2 = findViewById(R.id.llPlayer2);

        txtAppName = findViewById(R.id.txtAppName);
        txtPlayer1Name = findViewById(R.id.txtPlayer1Name);
        txtPlayer2Name = findViewById(R.id.txtPlayer2Name);
        txtPlayer1Score = findViewById(R.id.txtPlayer1Score);
        txtPlayer2Score = findViewById(R.id.txtPlayer2Score);
        txtPlayerTurns = findViewById(R.id.txtPlayerTurns);

        imgBack = findViewById(R.id.imgBack);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        img9 = findViewById(R.id.img9);

        btnRestartGame = findViewById(R.id.btnRestartGame);

    }

    public void Play(View view) {
        int id = view.getId();

        if (hashMap.containsKey(id))
            return;

        MediaPlayer mediaPlayer = MediaPlayer.create(PlayActivity.this, R.raw.moves_music);
        mediaPlayer.start();

        hashMap.put(id, 1);
        cntMoves++;

        if (id == R.id.img1) {
            b1 = setSymbol(view, flag);
        } else if (id == R.id.img2) {
            b2 = setSymbol(view, flag);
        } else if (id == R.id.img3) {
            b3 = setSymbol(view, flag);
        } else if (id == R.id.img4) {
            b4 = setSymbol(view, flag);
        } else if (id == R.id.img5) {
            b5 = setSymbol(view, flag);
        } else if (id == R.id.img6) {
            b6 = setSymbol(view, flag);
        } else if (id == R.id.img7) {
            b7 = setSymbol(view, flag);
        } else if (id == R.id.img8) {
            b8 = setSymbol(view, flag);
        } else if (id == R.id.img9) {
            b9 = setSymbol(view, flag);
        }

        flag = !flag;

        if (cntMoves > 4) {
            winCondition(cntMoves);
        }

        if (playWith.equals("2") && flag == false) {
            int idd = smtCmpMove();
            Play(findViewById(idd));
            flag = true;
        }
        if (playWith.equals("3") && flag == false) {
            int cnt = 0;
            while (opponentPlayerWin()) {
                int idd = smtCmpMove();
                Play(findViewById(idd));
                flag = false;
                cnt++;
            }
            if (cnt == 0) {
                int idd = smtCmpMove();
                Play(findViewById(idd));
            }
            flag = true;
        }

    }

    private void restartGame(String symbol) {
        b1 = b2 = b3 = b4 = b5 = b6 = b7 = b8 = b9 = "";
        hashMap.clear();
        cntMoves = 0;

        img1.setImageResource(0);
        img2.setImageResource(0);
        img3.setImageResource(0);
        img4.setImageResource(0);
        img5.setImageResource(0);
        img6.setImageResource(0);
        img7.setImageResource(0);
        img8.setImageResource(0);
        img9.setImageResource(0);

        LottieAnimationView imgPlayer1 = findViewById(R.id.imgPlayer1);
        LottieAnimationView imgPlayer2 = findViewById(R.id.imgPlayer2);
        imgPlayer1.setAnimation(R.raw.cross);
        imgPlayer2.setAnimation(R.raw.circle);
        imgPlayer2.playAnimation();
        imgPlayer1.playAnimation();


        if (symbol.equals("O")) {
            txtPlayerTurns.setText(txtPlayer2Name.getText().toString() + " turns");
            llPlayer1.setBackgroundResource(0);
            llPlayer2.setBackgroundResource(R.drawable.square_background);
            flag = true;
        } else {
            txtPlayerTurns.setText(txtPlayer1Name.getText().toString() + " turns");
            llPlayer1.setBackgroundResource(R.drawable.square_background);
            llPlayer2.setBackgroundResource(0);
            flag = false;
        }

        if (playWith.equals("2") && flag == false) {
            int id = smtCmpMove();
            Play(findViewById(id));
        }
        if (playWith.equals("3") && flag == false) {
            int id = smtCmpMove();
            Play(findViewById(id));
        }
    }


    public void winCondition(int moves) {
        int id1 = -1, id2 = -1, id3 = -1;
        String symbol = "";

        if (b1.equals(b2) && b2.equals(b3) && !b1.equals("")) {
            id1 = R.id.img1;
            id2 = R.id.img2;
            symbol = b1;
            id3 = R.id.img3;
        } else if (b4.equals(b5) && b5.equals(b6) && !b4.equals("")) {
            id1 = R.id.img4;
            id2 = R.id.img5;
            symbol = b4;
            id3 = R.id.img6;
        } else if (b7.equals(b8) && b8.equals(b9) && !b7.equals("")) {
            id1 = R.id.img7;
            id2 = R.id.img8;
            symbol = b7;
            id3 = R.id.img9;
        } else if (b1.equals(b4) && b4.equals(b7) && !b1.equals("")) {
            id1 = R.id.img1;
            id2 = R.id.img4;
            symbol = b1;
            id3 = R.id.img7;
        } else if (b2.equals(b5) && b5.equals(b8) && !b2.equals("")) {
            id1 = R.id.img2;
            id2 = R.id.img5;
            symbol = b2;
            id3 = R.id.img8;
        } else if (b3.equals(b6) && b6.equals(b9) && !b3.equals("")) {
            id1 = R.id.img3;
            id2 = R.id.img6;
            symbol = b3;
            id3 = R.id.img9;
        } else if (b1.equals(b5) && b5.equals(b9) && !b1.equals("")) {
            id1 = R.id.img1;
            id2 = R.id.img5;
            id3 = R.id.img9;
            symbol = b1;
        } else if (b3.equals(b5) && b5.equals(b7) && !b3.equals("")) {
            id1 = R.id.img3;
            symbol = b5;
            id2 = R.id.img5;
            id3 = R.id.img7;
        } else if (cntMoves >= 9) {

        } else {
            return;
        }

        endGame(symbol, id1, id2, id3);
    }

    public void endGame(String symbol, int id1, int id2, int id3) {

        MediaPlayer mediaPlayer = MediaPlayer.create(PlayActivity.this, R.raw.draw_music);
        mediaPlayer.start();

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.game_result);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LottieAnimationView imgResult = dialog.findViewById(R.id.imgResult);
        TextView txtResult = dialog.findViewById(R.id.txtResult);

        LottieAnimationView l1 = findViewById(id1);
        LottieAnimationView l2 = findViewById(id2);
        LottieAnimationView l3 = findViewById(id3);

        if (id1 != -1 && id2 != -1 && id3 != -1) {
            l1.loop(true);
            l2.loop(true);
            l3.loop(true);
            l1.playAnimation();
            l2.playAnimation();
            l3.playAnimation();
        }

        if (symbol.equals(player1Symbol)) {
            txtResult.setText(txtPlayer1Name.getText().toString() + " Wins");
            player1Score++;
            txtPlayer1Score.setText(Integer.toString(player1Score));
            imgResult.setAnimation(R.raw.win);
            imgResult.loop(true);
            imgResult.playAnimation();
        } else if (symbol.equals(player2Symbol)) {
            player2Score++;
            txtPlayer2Score.setText(Integer.toString(player2Score));
            txtResult.setText(txtPlayer2Name.getText().toString() + " Wins");
            imgResult.setAnimation(R.raw.win);
            imgResult.loop(true);
            imgResult.playAnimation();
        } else {
            txtResult.setText("Ohh! Match is Drawn.");
            imgResult.setAnimation(R.raw.draw);
            imgResult.loop(true);
            imgResult.playAnimation();
        }

        dialog.show();

        Handler handler = new Handler();
        String finalSymbol = symbol;
        dialog.getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Dismiss the dialog if the user touches the screen before the scheduled time

                if (finalSymbol.equals("X") || finalSymbol.equals("O")) {
                    l1.loop(false);
                    l2.loop(false);
                    l3.loop(false);
                }
                dialog.dismiss();
                handler.removeCallbacksAndMessages(null);
                restartGame(finalSymbol);
                return true;
            }
        });
    }

    public String setSymbol(View view, boolean flag) {
        LottieAnimationView lottieView = (LottieAnimationView) view;
        if (flag) {
            lottieView.setAnimation(R.raw.circle);
            lottieView.playAnimation();
            llPlayer1.setBackgroundResource(R.drawable.square_background);
            txtPlayerTurns.setText(txtPlayer1Name.getText().toString() + " turns");
            llPlayer2.setBackgroundResource(0);
            return "O";
        } else {
            lottieView.setAnimation(R.raw.cross);
            lottieView.playAnimation();
            llPlayer1.setBackgroundResource(0);
            txtPlayerTurns.setText(txtPlayer2Name.getText().toString() + " turns");
            llPlayer2.setBackgroundResource(R.drawable.square_background);
            return "X";
        }
    }

}