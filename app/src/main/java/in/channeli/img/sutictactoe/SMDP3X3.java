package in.channeli.img.sutictactoe;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.world.img144.sutictacas.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SMDP3X3 extends AppCompatActivity {
    Button b[] = new Button[9];
    RelativeLayout s[] = new RelativeLayout[8];
    ImageView p[] = new ImageView[2];
    TextView t[] = new TextView[2];
    Boolean turn, my_turn, over = false;
    int i;
    Integer[][] my = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    List<Set<Integer>> win = new ArrayList<>();
    List<Integer> myuser = new ArrayList<>();
    List<Integer> opponent = new ArrayList<>();

    @Override   
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = this.getIntent().getExtras();
        my_turn = bundle.getBoolean("my_turn");
        turn = my_turn;

        for (int j = 0; j < 8; j++) {
            Set<Integer> abs = new HashSet<>();
            abs.addAll(Arrays.asList(my[j]));
            win.add(abs);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smdp3_x3);

        b[0] = findViewById(R.id.b0);
        b[1] = findViewById(R.id.b1);
        b[2] = findViewById(R.id.b2);
        b[3] = findViewById(R.id.b3);
        b[4] = findViewById(R.id.b4);
        b[5] = findViewById(R.id.b5);
        b[6] = findViewById(R.id.b6);
        b[7] = findViewById(R.id.b7);
        b[8] = findViewById(R.id.b8);

        s[0] = findViewById(R.id.s0);
        s[1] = findViewById(R.id.s1);
        s[2] = findViewById(R.id.s2);
        s[3] = findViewById(R.id.s3);
        s[4] = findViewById(R.id.s4);
        s[5] = findViewById(R.id.s5);
        s[6] = findViewById(R.id.s6);
        s[7] = findViewById(R.id.s7);

        p[0] = findViewById(R.id.p1);
        p[1] = findViewById(R.id.p2);

        t[0] = findViewById(R.id.t1);
        t[1] = findViewById(R.id.t2);

        for (i = 0; i < 9; i++) {
            setOnClick(b[i], i);
        }
        onturn();
    }

    private void setOnClick(final Button btn, final Integer x) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn.getText().toString().equals("") && !over) {
                    if (turn) {
                        myuser.add(x);
                        turn = false;
                        btn.setBackgroundColor(getResources().getColor(R.color.play_online));
                        btn.setText("X");
                        endGame(myuser);
                    } else {
                        opponent.add(x);
                        turn = true;
                        btn.setBackgroundColor(getResources().getColor(R.color.quick_play));
                        btn.setText("O");
                        endGame(opponent);
                    }
                }
            }
        });
    }

    public void Switch(View view) {
        onBackPressed();
    }

    public void sound(View view) {
        onBackPressed();
    }

    private void onturn() {
        for (int i = 0; i < 8; i++ ) {
            if (!turn)
                s[i].setBackgroundColor(getResources().getColor(R.color.quick_play));
            else
                s[i].setBackgroundColor(getResources().getColor(R.color.play_online));
        }
        if (!turn) {
            p[0].setImageResource(R.drawable.ic_profile_green);
            p[1].setImageResource(R.drawable.ic_profile_grey);
            t[0].setTextColor(getResources().getColor(R.color.quick_play));
            t[1].setTextColor(getResources().getColor(R.color.black));
        }
        else {
            p[0].setImageResource(R.drawable.ic_profile_grey);
            p[1].setImageResource(R.drawable.ic_profile_red);
            t[0].setTextColor(getResources().getColor(R.color.black));
            t[1].setTextColor(getResources().getColor(R.color.play_online));
        }
    }

    private void endGame(List a1) {
        List<Set<Integer>> x = getSubsets(a1, 3);
        if (x.size() > 0) {
            for (int i = 0; i < x.size(); i++) {
                for (int j = 0; j < 8; j++) {
                    if ((win.get(j)).equals(x.get(i))) {
                        if (turn) {
                            Button resu = findViewById(R.id.resume);
                            resu.setText("Circle Wins!!");
                            Toast.makeText(SMDP3X3.this, "Circle Wins!!", Toast.LENGTH_LONG).show();
                        } else {
                            Button resu = findViewById(R.id.resume);
                            resu.setText("Cross Wins!!");
                            Toast.makeText(SMDP3X3.this, "Cross Wins!!", Toast.LENGTH_LONG).show();
                        }
                        over = true;
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                findViewById(R.id.layout1).setVisibility(View.GONE);
                                findViewById(R.id.layout2).setVisibility(View.VISIBLE);
                            }
                        }, 700);
                        return;
                    }
                }
            }
        }
        if (myuser.size()+opponent.size()==9) {
            over = true;
            Toast.makeText(SMDP3X3.this, "Match Tie!!", Toast.LENGTH_LONG).show();
            Button resu = findViewById(R.id.resume);
            resu.setText("Match Tie!!");
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    findViewById(R.id.layout1).setVisibility(View.GONE);
                    findViewById(R.id.layout2).setVisibility(View.VISIBLE);
                }
            }, 700);
            return;
        }
        onturn();
    }

    public void home(View view) {
        Intent main = new Intent(this, MainActivity.class);
        main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(main);
        return;
    }

    public void restart(View view) {
        finish();
    }

    public void undo(View view) {
        if(!over) {
            try {
                if (turn) {
                    int x = opponent.get(opponent.size() - 1);
                    opponent.remove(opponent.size() - 1);
                    b[x].setText("");
                    b[x].setBackgroundColor(getResources().getColor(R.color.trans));
                    turn = false;
                } else {
                    int x = myuser.get(myuser.size() - 1);
                    myuser.remove(myuser.size() - 1);
                    b[x].setText("");
                    b[x].setBackgroundColor(getResources().getColor(R.color.trans));
                    turn = true;
                }
            } catch (Exception e) {
                Toast.makeText(SMDP3X3.this, "can't undo", Toast.LENGTH_LONG).show();
            }
        }
    }

    private static void getSubsets(List<Integer> superSet, int k, int idx, Set<Integer> current, List<Set<Integer>> solution) {
        //successful stop clause
        if (current.size() == k) {
            solution.add(new HashSet<>(current));
            return;
        }
        //unseccessful stop clause
        if (idx == superSet.size()) return;
        Integer x = superSet.get(idx);
        current.add(x);
        //"guess" x is in the subset
        getSubsets(superSet, k, idx + 1, current, solution);
        current.remove(x);
        //"guess" x is not in the subset
        getSubsets(superSet, k, idx + 1, current, solution);
    }

    public static List<Set<Integer>> getSubsets(List<Integer> superSet, int k) {
        List<Set<Integer>> res = new ArrayList<>();
        getSubsets(superSet, k, 0, new HashSet<Integer>(), res);
        return res;
    }

    public <T> List<T> twoDArrayToList(T[][] twoDArray) {
        List<T> list = new ArrayList<T>();
        for (T[] array : twoDArray) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }

    @Override
    public void onBackPressed() {
        if (over) {
            return;
        }
        if (findViewById(R.id.layout1).getVisibility() != View.VISIBLE) {
            findViewById(R.id.layout1).setVisibility(View.VISIBLE);
            findViewById(R.id.layout2).setVisibility(View.GONE);
        }
        else {
            findViewById(R.id.layout2).setVisibility(View.VISIBLE);
            findViewById(R.id.layout1).setVisibility(View.GONE);
        }
    }
}
