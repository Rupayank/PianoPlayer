package com.example.piano;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MediaRecorder mediaRecorder;
    private String recordfile;
//    private Chronometer timer;

    public  static String fileName1=null;
    public  static String fileName2=null;
    public  static String fileName3=null;
    public  static String fileName4=null;
    public  static String fileName5=null;
    public  static String fileName6=null;

    boolean startRecord=true;
    public int recordingno=1;

    private SoundPool soundPool;
    Button left_nav,right_nav,record,play;

    private int ic3,ic3Black,ic4,ic4Black,ic5,ic5Black,ic6,ic6Black,ic7,ic7Black;
    private int id3,id3Black,id4,id4Black,id5,id5Black,id6,id6Black,id7,id7Black;
    private int ie3,ie4,ie5,ie6,ie7;
    private int if3,if3Black,if4,if4Black,if5,if5Black,if6,if6Black,if7,if7Black;
    private int ig3,ig3Black,ig4,ig4Black,ig5,ig5Black,ig6,ig6Black,ig7,ig7Black;
    private int ia3,ia3Black,ia4,ia4Black,ia5,ia5Black,ia6,ia6Black,ia7,ia7Black;
    private int ib3,ib4,ib5,ib6,ib7;

    private HorizontalScrollView scrollView;

    private Button c3,c3Black,c4,c4Black,c5,c5Black,c6,c6Black,c7,c7Black;
    private Button d3,d3Black,d4,d4Black,d5,d5Black,d6,d6Black,d7,d7Black;
    private Button e3,e4,e5,e6,e7;
    private Button f3,f3Black,f4,f4Black,f5,f5Black,f6,f6Black,f7,f7Black;
    private Button g3,g3Black,g4,g4Black,g5,g5Black,g6,g6Black,g7,g7Black;
    private Button a3,a3Black,a4,a4Black,a5,a5Black,a6,a6Black,a7,a7Black;
    private Button b3,b4,b5,b6,b7;

    private TextView tc3,td3,te3,tf3,tg3,ta3,tb3;
    private TextView tc4,td4,te4,tf4,tg4,ta4,tb4;
    private TextView tc5,td5,te5,tf5,tg5,ta5,tb5;
    private TextView tc6,td6,te6,tf6,tg6,ta6,tb6;
    private TextView tc7,td7,te7,tf7,tg7,ta7,tb7;

    @Override
    public void onStop() {
        super.onStop();
        Log.d("stop", Boolean.toString(startRecord));
        if(!startRecord)
        {
            stopRecording();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView=findViewById(R.id.scrollView);

        initialiseAllKeys();

        intitialiseTextViewLabels();

        soundPool=new SoundPool.Builder().setMaxStreams(5).build();
//3
        ic3=soundPool.load(this,R.raw.c3,1);
        ic3Black=soundPool.load(this,R.raw.c3black,1);
        id3=soundPool.load(this,R.raw.d3,1);
        id3Black=soundPool.load(this,R.raw.d3black,1);
        ie3=soundPool.load(this,R.raw.e3,1);
        if3=soundPool.load(this,R.raw.f3,1);
        if3Black=soundPool.load(this,R.raw.f3black,1);
        ig3=soundPool.load(this,R.raw.g3,1);
        ig3Black=soundPool.load(this,R.raw.g3black,1);
        ia3=soundPool.load(this,R.raw.a3,1);
        ia3Black=soundPool.load(this,R.raw.a3black,1);
        ib3=soundPool.load(this,R.raw.b3,1);

//        4
        ic4=soundPool.load(this,R.raw.c4,1);
        ic4Black=soundPool.load(this,R.raw.c4black,1);
        id4=soundPool.load(this,R.raw.d4,1);
        id4Black=soundPool.load(this,R.raw.d4black,1);
        ie4=soundPool.load(this,R.raw.e4,1);
        if4=soundPool.load(this,R.raw.f4,1);
        if4Black=soundPool.load(this,R.raw.f4black,1);
        ig4=soundPool.load(this,R.raw.g4,1);
        ig4Black=soundPool.load(this,R.raw.g4black,1);
        ia4=soundPool.load(this,R.raw.a4,1);
        ia4Black=soundPool.load(this,R.raw.a4black,1);
        ib4=soundPool.load(this,R.raw.b4,1);

//        5
        ic5=soundPool.load(this,R.raw.c5,1);
        ic5Black=soundPool.load(this,R.raw.c5black,1);
        id5=soundPool.load(this,R.raw.d5,1);
        id5Black=soundPool.load(this,R.raw.d5black,1);
        ie5=soundPool.load(this,R.raw.e5,1);
        if5=soundPool.load(this,R.raw.f5,1);
        if5Black=soundPool.load(this,R.raw.f5black,1);
        ig5=soundPool.load(this,R.raw.g5,1);
        ig5Black=soundPool.load(this,R.raw.g5black,1);
        ia5=soundPool.load(this,R.raw.a5,1);
        ia5Black=soundPool.load(this,R.raw.a5black,1);
        ib5=soundPool.load(this,R.raw.b5,1);

//        6
        ic6=soundPool.load(this,R.raw.c6,1);
        ic6Black=soundPool.load(this,R.raw.c6black,1);
        id6=soundPool.load(this,R.raw.d6,1);
        id6Black=soundPool.load(this,R.raw.d6black,1);
        ie6=soundPool.load(this,R.raw.e6,1);
        if6=soundPool.load(this,R.raw.f6,1);
        if6Black=soundPool.load(this,R.raw.f6black,1);
        ig6=soundPool.load(this,R.raw.g6,1);
        ig6Black=soundPool.load(this,R.raw.g6black,1);
        ia6=soundPool.load(this,R.raw.a6,1);
        ia6Black=soundPool.load(this,R.raw.a6black,1);
        ib6=soundPool.load(this,R.raw.b6,1);

//        7
        ic7=soundPool.load(this,R.raw.c7,1);
        ic7Black=soundPool.load(this,R.raw.c7black,1);
        id7=soundPool.load(this,R.raw.d7,1);
        id7Black=soundPool.load(this,R.raw.d7black,1);
        ie7=soundPool.load(this,R.raw.e7,1);
        if7=soundPool.load(this,R.raw.f7,1);
        if7Black=soundPool.load(this,R.raw.f7black,1);
        ig7=soundPool.load(this,R.raw.g7,1);
        ig7Black=soundPool.load(this,R.raw.g7black,1);
        ia7=soundPool.load(this,R.raw.a7,1);
        ia7Black=soundPool.load(this,R.raw.a7black,1);
        ib7=soundPool.load(this,R.raw.b7,1);

        //        navigation and control buttons
        left_nav=(Button)findViewById(R.id.bt_left_nav);
        right_nav=(Button)findViewById(R.id.bt_right_nav);
        record=(Button)findViewById(R.id.bt_record);
        play=(Button)findViewById(R.id.bt_play);

        fileName1=getExternalCacheDir().getAbsolutePath();
        fileName1+="/Record1.3gp";
        fileName2=getExternalCacheDir().getAbsolutePath();
        fileName2+="/Record2.3gp";
        fileName3=getExternalCacheDir().getAbsolutePath();
        fileName3+="/Record3.3gp";
        fileName4=getExternalCacheDir().getAbsolutePath();
        fileName4+="/Record4.3gp";
        fileName5=getExternalCacheDir().getAbsolutePath();
        fileName5+="/Record5.3gp";
        fileName6=getExternalCacheDir().getAbsolutePath();
        fileName6+="/Record6.3gp";

        SharedPreferences preferences=getSharedPreferences("FILENO", MODE_PRIVATE);
//        recordingno=preferences.getInt("fileno",1);
//        Log.d("Recordingno",Integer.toString(recordingno));
    }


    private void intitialiseTextViewLabels() {
//        3
        tc3=(TextView)findViewById(R.id.tc3);
        tc3.setOnClickListener(this);
        td3=(TextView)findViewById(R.id.td3);
        td3.setOnClickListener(this);
        te3=(TextView)findViewById(R.id.te3);
        te3.setOnClickListener(this);
        tf3=(TextView)findViewById(R.id.tf3);
        tf3.setOnClickListener(this);
        tg3=(TextView)findViewById(R.id.tg3);
        tg3.setOnClickListener(this);
        ta3=(TextView)findViewById(R.id.ta3);
        ta3.setOnClickListener(this);
        tb3=(TextView)findViewById(R.id.tb3);
        tb3.setOnClickListener(this);

//        4
        tc4=(TextView)findViewById(R.id.tc4);
        tc4.setOnClickListener(this);
        td4=(TextView)findViewById(R.id.td4);
        td4.setOnClickListener(this);
        te4=(TextView)findViewById(R.id.te4);
        te4.setOnClickListener(this);
        tf4=(TextView)findViewById(R.id.tf4);
        tf4.setOnClickListener(this);
        tg4=(TextView)findViewById(R.id.tg4);
        tg4.setOnClickListener(this);
        ta4=(TextView)findViewById(R.id.ta4);
        ta4.setOnClickListener(this);
        tb4=(TextView)findViewById(R.id.tb4);
        tb4.setOnClickListener(this);

//        5
        tc5=(TextView)findViewById(R.id.tc5);
        tc5.setOnClickListener(this);
        td5=(TextView)findViewById(R.id.td5);
        td5.setOnClickListener(this);
        te5=(TextView)findViewById(R.id.te5);
        te5.setOnClickListener(this);
        tf5=(TextView)findViewById(R.id.tf5);
        tf5.setOnClickListener(this);
        tg5=(TextView)findViewById(R.id.tg5);
        tg5.setOnClickListener(this);
        ta5=(TextView)findViewById(R.id.ta5);
        ta5.setOnClickListener(this);
        tb5=(TextView)findViewById(R.id.tb5);
        tb5.setOnClickListener(this);

//        6
        tc6=(TextView)findViewById(R.id.tc6);
        tc6.setOnClickListener(this);
        td6=(TextView)findViewById(R.id.td6);
        td6.setOnClickListener(this);
        te6=(TextView)findViewById(R.id.te6);
        te6.setOnClickListener(this);
        tf6=(TextView)findViewById(R.id.tf6);
        tf6.setOnClickListener(this);
        tg6=(TextView)findViewById(R.id.tg6);
        tg6.setOnClickListener(this);
        ta6=(TextView)findViewById(R.id.ta6);
        ta6.setOnClickListener(this);
        tb6=(TextView)findViewById(R.id.tb6);
        tb6.setOnClickListener(this);

//        7
        tc7=(TextView)findViewById(R.id.tc7);
        tc7.setOnClickListener(this);
        td7=(TextView)findViewById(R.id.td7);
        td7.setOnClickListener(this);
        te7=(TextView)findViewById(R.id.te7);
        te7.setOnClickListener(this);
        tf7=(TextView)findViewById(R.id.tf7);
        tf7.setOnClickListener(this);
        tg7=(TextView)findViewById(R.id.tg7);
        tg7.setOnClickListener(this);
        ta7=(TextView)findViewById(R.id.ta7);
        ta7.setOnClickListener(this);
        tb7=(TextView)findViewById(R.id.tb7);
        tb7.setOnClickListener(this);
    }

    void initialiseAllKeys()
    {
//        3 key
        c3=(Button)findViewById(R.id.button);
        c3.setOnClickListener(this);
        c3Black=(Button)findViewById(R.id.bl1);
        c3Black.setOnClickListener(this);
        d3=(Button)findViewById(R.id.button2);
        d3.setOnClickListener(this);
        d3Black=(Button)findViewById(R.id.bl2);
        d3Black.setOnClickListener(this);

        e3=(Button)findViewById(R.id.button3);
        c3.setOnClickListener(this);

        f3=(Button)findViewById(R.id.button4);
        f3.setOnClickListener(this);
        f3Black=(Button)findViewById(R.id.bl3);
        f3Black.setOnClickListener(this);
        g3=(Button)findViewById(R.id.button5);
        g3.setOnClickListener(this);
        g3Black=(Button)findViewById(R.id.bl4);
        g3Black.setOnClickListener(this);
        a3=(Button)findViewById(R.id.button6);
        a3.setOnClickListener(this);
        a3Black=(Button)findViewById(R.id.bl5);
        a3Black.setOnClickListener(this);

        b3=(Button)findViewById(R.id.button7);
        b3.setOnClickListener(this);

//        4 key
        c4=(Button)findViewById(R.id.button8);
        c4.setOnClickListener(this);
        c4Black=(Button)findViewById(R.id.bl6);
        c4Black.setOnClickListener(this);
        d4=(Button)findViewById(R.id.button9);
        d4.setOnClickListener(this);
        d4Black=(Button)findViewById(R.id.bl7);
        d4Black.setOnClickListener(this);

        e4=(Button)findViewById(R.id.button10);
        e4.setOnClickListener(this);

        f4=(Button)findViewById(R.id.button11);
        f4.setOnClickListener(this);
        f4Black=(Button)findViewById(R.id.bl8);
        f4Black.setOnClickListener(this);
        g4=(Button)findViewById(R.id.button12);
        g4.setOnClickListener(this);
        g4Black=(Button)findViewById(R.id.bl9);
        g4Black.setOnClickListener(this);
        a4=(Button)findViewById(R.id.button13);
        a4.setOnClickListener(this);
        a4Black=(Button)findViewById(R.id.bl10);
        a4Black.setOnClickListener(this);

        b4=(Button)findViewById(R.id.button14);
        b4.setOnClickListener(this);

//        5 key
        c5=(Button)findViewById(R.id.button15);
        c5.setOnClickListener(this);
        c5Black=(Button)findViewById(R.id.bl11);
        c5Black.setOnClickListener(this);
        d5=(Button)findViewById(R.id.button16);
        d5.setOnClickListener(this);
        d5Black=(Button)findViewById(R.id.bl12);
        d5Black.setOnClickListener(this);

        e5=(Button)findViewById(R.id.button17);
        e5.setOnClickListener(this);

        f5=(Button)findViewById(R.id.button18);
        f5.setOnClickListener(this);
        f5Black=(Button)findViewById(R.id.bl13);
        f5Black.setOnClickListener(this);
        g5=(Button)findViewById(R.id.button19);
        g5.setOnClickListener(this);
        g5Black=(Button)findViewById(R.id.bl14);
        g5Black.setOnClickListener(this);
        a5=(Button)findViewById(R.id.button20);
        a5.setOnClickListener(this);
        a5Black=(Button)findViewById(R.id.bl15);
        a5Black.setOnClickListener(this);

        b5=(Button)findViewById(R.id.button21);
        b5.setOnClickListener(this);

//        6 key
        c6=(Button)findViewById(R.id.button22);
        c6.setOnClickListener(this);
        c6Black=(Button)findViewById(R.id.bl16);
        c6Black.setOnClickListener(this);
        d6=(Button)findViewById(R.id.button23);
        d6.setOnClickListener(this);
        d6Black=(Button)findViewById(R.id.bl17);
        d6Black.setOnClickListener(this);

        e6=(Button)findViewById(R.id.button24);
        e6.setOnClickListener(this);

        f6=(Button)findViewById(R.id.button25);
        f6.setOnClickListener(this);
        f6Black=(Button)findViewById(R.id.bl18);
        f6Black.setOnClickListener(this);
        g6=(Button)findViewById(R.id.button26);
        g6.setOnClickListener(this);
        g6Black=(Button)findViewById(R.id.bl19);
        g6Black.setOnClickListener(this);
        a6=(Button)findViewById(R.id.button27);
        a6.setOnClickListener(this);
        a6Black=(Button)findViewById(R.id.bl20);
        a6Black.setOnClickListener(this);

        b6=(Button)findViewById(R.id.button28);
        b6.setOnClickListener(this);

//        7 key
        c7=(Button)findViewById(R.id.button29);
        c7.setOnClickListener(this);
        c7Black=(Button)findViewById(R.id.bl21);
        c7Black.setOnClickListener(this);
        d7=(Button)findViewById(R.id.button30);
        d7.setOnClickListener(this);
        d7Black=(Button)findViewById(R.id.bl22);
        d7Black.setOnClickListener(this);

        e7=(Button)findViewById(R.id.button31);
        e7.setOnClickListener(this);

        f7=(Button)findViewById(R.id.button32);
        f7.setOnClickListener(this);
        f7Black=(Button)findViewById(R.id.bl23);
        f7Black.setOnClickListener(this);
        g7=(Button)findViewById(R.id.button33);
        g7.setOnClickListener(this);
        g7Black=(Button)findViewById(R.id.bl24);
        g7Black.setOnClickListener(this);
        a7=(Button)findViewById(R.id.button34);
        a7.setOnClickListener(this);
        a7Black=(Button)findViewById(R.id.bl25);
        a7Black.setOnClickListener(this);

        b7=(Button)findViewById(R.id.button35);
        b7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
//            3
            case R.id.tc3:
            case R.id.button:
                soundPool.play(ic3,1,1,0,0,1);
                break;
            case R.id.bl1:
                soundPool.play(ic3Black,1,1,0,0,1);
                break;
            case R.id.td3:
            case R.id.button2:
                soundPool.play(id3,1,1,0,0,1);
                break;
            case R.id.bl2:
                soundPool.play(id3Black,1,1,0,0,1);
                break;
            case R.id.te3:
            case R.id.button3:
                soundPool.play(ie3,1,1,0,0,1);
                break;
            case R.id.tf3:
            case R.id.button4:
                soundPool.play(if3,1,1,0,0,1);
                break;
            case R.id.bl3:
                soundPool.play(if3Black,1,1,0,0,1);
                break;
            case R.id.tg3:
            case R.id.button5:
                soundPool.play(ig3,1,1,0,0,1);
                break;
            case R.id.bl4:
                soundPool.play(ig3Black,1,1,0,0,1);
                break;
            case R.id.ta3:
            case R.id.button6:
                soundPool.play(ia3,1,1,0,0,1);
                break;
            case R.id.bl5:
                soundPool.play(ia3Black,1,1,0,0,1);
                break;
            case R.id.tb3:
            case R.id.button7:
                soundPool.play(ib3,1,1,0,0,1);
                break;

//            4
            case R.id.tc4:
            case R.id.button8:
                soundPool.play(ic4,1,1,0,0,1);
                break;
            case R.id.bl6:
                soundPool.play(ic4Black,1,1,0,0,1);
                break;
            case R.id.td4:
            case R.id.button9:
                soundPool.play(id4,1,1,0,0,1);
                break;
            case R.id.bl7:
                soundPool.play(id4Black,1,1,0,0,1);
                break;
            case R.id.te4:
            case R.id.button10:
                soundPool.play(ie4,1,1,0,0,1);
                break;
            case R.id.tf4:
            case R.id.button11:
                soundPool.play(if4,1,1,0,0,1);
                break;
            case R.id.bl8:
                soundPool.play(if4Black,1,1,0,0,1);
                break;
            case R.id.tg4:
            case R.id.button12:
                soundPool.play(ig4,1,1,0,0,1);
                break;
            case R.id.bl9:
                soundPool.play(ig4Black,1,1,0,0,1);
                break;
            case R.id.ta4:
            case R.id.button13:
                soundPool.play(ia4,1,1,0,0,1);
                break;
            case R.id.bl10:
                soundPool.play(ia4Black,1,1,0,0,1);
                break;
            case R.id.tb4:
            case R.id.button14:
                soundPool.play(ib4,1,1,0,0,1);
                break;

//                5
            case R.id.tc5:
            case R.id.button15:
                soundPool.play(ic5,1,1,0,0,1);
                break;
            case R.id.bl11:
                soundPool.play(ic5Black,1,1,0,0,1);
                break;
            case R.id.td5:
            case R.id.button16:
                soundPool.play(id5,1,1,0,0,1);
                break;
            case R.id.bl12:
                soundPool.play(id5Black,1,1,0,0,1);
                break;
            case R.id.te5:
            case R.id.button17:
                soundPool.play(ie5,1,1,0,0,1);
                break;
            case R.id.tf5:
            case R.id.button18:
                soundPool.play(if5,1,1,0,0,1);
                break;
            case R.id.bl13:
                soundPool.play(if5Black,1,1,0,0,1);
                break;
            case R.id.tg5:
            case R.id.button19:
                soundPool.play(ig5,1,1,0,0,1);
                break;
            case R.id.bl14:
                soundPool.play(ig5Black,1,1,0,0,1);
                break;
            case R.id.ta5:
            case R.id.button20:
                soundPool.play(ia5,1,1,0,0,1);
                break;
            case R.id.bl15:
                soundPool.play(ia5Black,1,1,0,0,1);
                break;
            case R.id.tb5:
            case R.id.button21:
                soundPool.play(ib5,1,1,0,0,1);
                break;

//                6
            case R.id.tc6:
            case R.id.button22:
                soundPool.play(ic6,1,1,0,0,1);
                break;
            case R.id.bl16:
                soundPool.play(ic6Black,1,1,0,0,1);
                break;
            case R.id.td6:
            case R.id.button23:
                soundPool.play(id6,1,1,0,0,1);
                break;
            case R.id.bl17:
                soundPool.play(id6Black,1,1,0,0,1);
                break;
            case R.id.te6:
            case R.id.button24:
                soundPool.play(ie6,1,1,0,0,1);
                break;
            case R.id.tf6:
            case R.id.button25:
                soundPool.play(if6,1,1,0,0,1);
                break;
            case R.id.bl18:
                soundPool.play(if6Black,1,1,0,0,1);
                break;
            case R.id.tg6:
            case R.id.button26:
                soundPool.play(ig6,1,1,0,0,1);
                break;
            case R.id.bl19:
                soundPool.play(ig6Black,1,1,0,0,1);
                break;
            case R.id.ta6:
            case R.id.button27:
                soundPool.play(ia6,1,1,0,0,1);
                break;
            case R.id.bl20:
                soundPool.play(ia6Black,1,1,0,0,1);
                break;
            case R.id.tb6:
            case R.id.button28:
                soundPool.play(ib6,1,1,0,0,1);
                break;

//                7
            case R.id.tc7:
            case R.id.button29:
                soundPool.play(ic7,1,1,0,0,1);
                break;
            case R.id.bl21:
                soundPool.play(ic7Black,1,1,0,0,1);
                break;
            case R.id.td7:
            case R.id.button30:
                soundPool.play(id7,1,1,0,0,1);
                break;
            case R.id.bl22:
                soundPool.play(id7Black,1,1,0,0,1);
                break;
            case R.id.te7:
            case R.id.button31:
                soundPool.play(ie7,1,1,0,0,1);
                break;
            case R.id.tf7:
            case R.id.button32:
                soundPool.play(if7,1,1,0,0,1);
                break;
            case R.id.bl23:
                soundPool.play(if7Black,1,1,0,0,1);
                break;
            case R.id.tg7:
            case R.id.button33:
                soundPool.play(ig7,1,1,0,0,1);
                break;
            case R.id.bl24:
                soundPool.play(ig7Black,1,1,0,0,1);
                break;
            case R.id.ta7:
            case R.id.button34:
                soundPool.play(ia7,1,1,0,0,1);
                break;
            case R.id.bl25:
                soundPool.play(ia7Black,1,1,0,0,1);
                break;
            case R.id.tb7:
            case R.id.button35:
                soundPool.play(ib7,1,1,0,0,1);
                break;
        }
    }

    public void scroll_right(View view)
    {
        scrollView.scrollTo((int)scrollView.getScrollX()+120,(int)scrollView.getScrollY());
    }

    public void scroll_left(View view)
    {
        scrollView.scrollTo((int)scrollView.getScrollX()-120,(int)scrollView.getScrollY());
    }

    public void play_record(View view) {
        if(!startRecord)
        {
            Log.d("Play record",Boolean.toString(startRecord));
            AlertDialog.Builder alertDialog= new AlertDialog.Builder(getApplicationContext());
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startRecord=true;
                    Intent j =new Intent(MainActivity.this,PlayingAudio.class);
                    startActivity(j);
                }
            });
            alertDialog.setNegativeButton("CANCEL",null);
            alertDialog.setTitle("Audio is being recorded");
            alertDialog.setMessage("Do you want to stop the recording?");
            alertDialog.create().show();
        }
        else{
            Log.d("Else Play record",Boolean.toString(startRecord));
            Intent i =new Intent(MainActivity.this,PlayingAudio.class);
            startActivity(i);
        }
//        Intent i =new Intent(MainActivity.this,PlayingAudio.class);
//        startActivity(i);
    }

    public void record(View view)
    {
        onRecord(startRecord);
        if(startRecord)
        {
//            startRecording();
            record.setText("Done");
        }
        else
        {
//            stopRecording();
            record.setText("Record");
        }
        startRecord=!startRecord;
    }

    private void startRecording()
    {
        //Chronometer
//        timer.setBase(SystemClock.elapsedRealtime());
//        timer.start();

        mediaRecorder= new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
//        Log.i("Recording","Media "+ Integer.toString(recordingno));
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy_MM_dd_hh_mm", Locale.ENGLISH);
        Date now= new Date();
//        String recordPath=MainActivity.this.getExternalFilesDir("/").getAbsolutePath();
        String recordPath=getApplicationContext().getExternalFilesDir("/").getAbsolutePath();
        recordfile="Recording_"+formatter.format(now)+".3gp";
        mediaRecorder.setOutputFile(recordPath+"/"+recordfile);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();


//        switch (recordingno)
//        {
//            case 1:
//                mediaRecorder.setOutputFile(fileName1);
//                recordingno++;
//                if(recordingno==7) {
//                    recordingno = 1;
//                }
//                break;
//            case 2:
//                mediaRecorder.setOutputFile(fileName2);
//                recordingno++;
//                if(recordingno==7) {
//                    recordingno = 1;
//                }
//                break;
//            case 3:
//                mediaRecorder.setOutputFile(fileName3);
//                recordingno++;
//                if(recordingno==7) {
//                    recordingno = 1;
//                }
//                break;
//            case 4:
//                mediaRecorder.setOutputFile(fileName4);
//                recordingno++;
//                if(recordingno==7) {
//                    recordingno = 1;
//                }
//                break;
//            case 5:
//                mediaRecorder.setOutputFile(fileName5);
//                recordingno++;
//                if(recordingno==7) {
//                    recordingno = 1;
//                }
//                break;
//            case 6:
//                mediaRecorder.setOutputFile(fileName6);
//                recordingno++;
//                if(recordingno==7) {
//                    recordingno = 1;
//                }
//                break;
//            default:
//                Log.i("Recording","Default");
//        }
//        SharedPreferences.Editor editor=getSharedPreferences("FILENO",MODE_PRIVATE).edit();
//        editor.putInt("fileno",recordingno);
//        editor.commit();
//        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//        try {
//            mediaRecorder.prepare();
//        }catch (IOException e) {
//            Log.e("Prepare failed","Failed");
//        }
//        mediaRecorder.start();
    }
    private void stopRecording()
    {
//        timer.stop();
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder=null;
    }

    private void onRecord(boolean start)
    {
        if(start)
        {
            startRecording();
        }
        else {
            stopRecording();
            if (recordingno == 1)
            {
                Toast recordingmsg=Toast.makeText(getApplicationContext(),"Song"+6+"saved",Toast.LENGTH_SHORT);
                recordingmsg.show();
            }
            else
            {
                int tempRecordNo=recordingno-1;
                Toast recordingmsg=Toast.makeText(getApplicationContext(),"Song"+tempRecordNo+"saved",Toast.LENGTH_SHORT);
                recordingmsg.show();
            }
        }
    }
}