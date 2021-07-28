package com.example.piano;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;
import java.io.IOException;

public class PlayingAudio extends AppCompatActivity implements AudioListAdapter.onItemListClick {

//    private ConstraintLayout playersheet;
//    private BottomSheetBehavior bottomSheetBehavior;
    private RecyclerView audiolist;
    private File[] allfiles;
    private AudioListAdapter audioListAdapter;
    private MediaPlayer mediaPlayer=null;
    private boolean isplaying=false;
    private File filetoplay=null;

    private ImageButton playbtn,rewind,fastforward;
    private TextView playerHeaderTitle,playerFilename;

    private SeekBar playerSeekBar;
    private Handler seekbarHandler;
    private Runnable updateSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_audio);

        audiolist=(RecyclerView)findViewById(R.id.audio_list_view);

        playerSeekBar=(SeekBar)findViewById(R.id.player_seekbar);

        String path=getApplicationContext().getExternalFilesDir("/").getAbsolutePath();
        File directory=new File(path);
        allfiles=directory.listFiles();

        playbtn=(ImageButton)findViewById(R.id.player_play_btn);
        rewind=(ImageButton)findViewById(R.id.player_rewind_btn);
        fastforward=(ImageButton)findViewById(R.id.player_forward_btn);
        //onclick for rewind and forward pending

        playerHeaderTitle=(TextView)findViewById(R.id.player_header_title);
        playerFilename=(TextView)findViewById(R.id.player_filename);

        audioListAdapter=new AudioListAdapter(allfiles,this);
        audiolist.setHasFixedSize(true);
        audiolist.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        audiolist.setAdapter(audioListAdapter);

        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isplaying)pauseAudio();
                else if(filetoplay!=null){
                    resumeAudio();
                }
            }
        });

        playerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                pauseAudio();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(filetoplay!=null)
                {
                    int progress=seekBar.getProgress();
                    mediaPlayer.seekTo(progress);
                    resumeAudio();
                }
            }
        });

//        playersheet=(ConstraintLayout)findViewById(R.id.player_sheet);
//        bottomSheetBehavior=BottomSheetBehavior.from(playersheet);
//
//        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                if(newState==BottomSheetBehavior.STATE_HIDDEN){
//                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//            //this method gives the live data of current position of bottomsheet slide
//            }
//        });
    }

    @Override
    public void onClickListener(File file, int position) {
        filetoplay=file;
        if(isplaying)
        {
            stopAudio();
            playAudio(filetoplay);
        }
        else
        {
            playAudio(filetoplay);
        }
    }
    private void pauseAudio()
    {
        mediaPlayer.pause();
        playbtn.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.play_button,null));
        isplaying=false;
        seekbarHandler.removeCallbacks(updateSeekbar);
    }

    private void resumeAudio()
    {
        mediaPlayer.start();
        playbtn.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.pause_btn,null));
        isplaying=true;
        updateRunnable();
        seekbarHandler.postDelayed(updateSeekbar,500);
    }

    private void stopAudio() {
        playbtn.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.play_button,null));
        playerHeaderTitle.setText("Stopped");
        isplaying=false;
        mediaPlayer.stop();
        seekbarHandler.removeCallbacks(updateSeekbar);
    }

    private void playAudio(File filetoplay) {
        mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource(filetoplay.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        playbtn.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.pause_btn,null));
        playerFilename.setText(filetoplay.getName());
        playerHeaderTitle.setText("Playing");
        isplaying=true;

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopAudio();
                playerHeaderTitle.setText("Finished");
            }
        });

        playerSeekBar.setMax(mediaPlayer.getDuration());
        seekbarHandler=new Handler();
        updateRunnable();
        seekbarHandler.postDelayed(updateSeekbar,0);
    }

    private void updateRunnable() {
        updateSeekbar=new Runnable() {
            @Override
            public void run() {
                playerSeekBar.setProgress(mediaPlayer.getCurrentPosition());
                seekbarHandler.postDelayed(this,500);
            }
        };
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isplaying)
        {
            stopAudio();
        }
    }
}