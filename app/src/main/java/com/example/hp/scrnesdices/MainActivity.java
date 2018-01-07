package com.example.hp.scrnesdices;

import android.graphics.Color;
import android.os.Vibrator;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public boolean userturn=true;
    public int usrmainscore=0;
    public int usrturnscore=0;
    public int comptrmainscor=0;
    public int comptrturnscore=0;

    public void userUpdate()
    {
        userturn=false;
        usrturnscore=0;
        TextView ut = (TextView) findViewById(R.id.textView6);//score in one turn all addition by user
        TextView ums = (TextView) findViewById(R.id.textView2);//user main score in text view
        ut.setText(""+usrturnscore);
        ums.setText(""+usrmainscore);


    }
    public void computerUpdate()
    {
        userturn=true;
        comptrturnscore=0;
        TextView cms = (TextView) findViewById(R.id.textView3);//computer main score in textview
        TextView ct=(TextView) findViewById(R.id.textView8);//score in one turn all addition by computer
        cms.setText(""+comptrmainscor);
        ct.setText(""+comptrturnscore);

    }
    public void intialize()
    {
         userturn=true;
         usrmainscore=0;
         usrturnscore=0;
         comptrmainscor=0;
         comptrturnscore=0;
         TextView result=(TextView) findViewById(R.id.textView9);
        result.setVisibility(View.INVISIBLE);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout r=(RelativeLayout)findViewById(R.id.relalayout);
       // r.setBackgroundColor(Color.rgb(0,255,0));
        final Button roll = (Button) findViewById(R.id.button);
        final Button hold = (Button) findViewById(R.id.button2);
        final Button reset = (Button) findViewById(R.id.button3);
        final ImageView iv = (ImageView) findViewById(R.id.imageView);
        final TextView ums = (TextView) findViewById(R.id.textView2);//user main score in text view
        final TextView cms = (TextView) findViewById(R.id.textView3);//computer main score in textview
        final TextView ut = (TextView) findViewById(R.id.textView6);//score in one turn all addition by user
        final TextView ct=(TextView) findViewById(R.id.textView8);//score in one turn all addition by computer
        final TextView result=(TextView) findViewById(R.id.textView9);
        final int photoes[] = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
        final android.os.Handler handler=new android.os.Handler();
        intialize();
       final Vibrator vibe= (Vibrator) getSystemService(VIBRATOR_SERVICE);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(userturn)
                {

                    int random=new Random().nextInt(6)+1;
                    if(random==1)
                    {
                        userturn=false;
                        usrmainscore=0;
                        userUpdate();
                        iv.setImageResource(photoes[0]);
                        vibe.vibrate(100);
                        hold.performClick();

                    }
                    else
                    {
                        usrturnscore+=random;
                        iv.setImageResource(photoes[random-1]);
                        ut.setText(""+usrturnscore);

                    }
                }

            }

        });
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usrmainscore+=usrturnscore;
                userUpdate();
                roll.setEnabled(false);
                final int random=new Random().nextInt(6)+1;
                for(int i=0;i<random;i++)
                {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {}

                    },1000);
                    int diceValue=new Random().nextInt(6)+1;
                    if(diceValue==1)
                    {
                        userturn=true;
                        comptrmainscor=0;
                        computerUpdate();
                        iv.setImageResource(photoes[0]);
                        vibe.vibrate(100);
                        break;
                    }
                    else
                    {
                        iv.setImageResource(photoes[diceValue-1]);
                        comptrturnscore+=diceValue;
                        ct.setText(""+comptrturnscore);
                    }

                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        comptrmainscor+=comptrturnscore;
                        computerUpdate();
                        roll.setEnabled(true);
                        if(usrmainscore>=100)
                        {
                            result.setVisibility(View.VISIBLE);
                            result.setTextColor(Color.rgb(0,0,255));
                            result.setText("You win");
                            reset.performClick();
                        }
                        else if(comptrmainscor>=100)
                        {
                            result.setVisibility(View.VISIBLE);
                            result.setTextColor(Color.rgb(255,0,0));
                            result.setText("You lost");
                            reset.performClick();
                        }
                    }

                },1000);

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ums.setText(""+0);
                cms.setText(""+0);
                ut.setText(""+0);
                ct.setText(""+0);
                userturn=true;
                intialize();
                iv.setImageResource(photoes[0]);










            }
        });




    }
}
