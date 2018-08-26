package com.example.android.musicplayerappundacity;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.media.MediaPlayer.OnCompletionListener;

public class MainActivity extends AppCompatActivity {

    //Make the MediaPlayer a global variable
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the MediaPlayer variable in the onCreate method when the page is first loaded to make it ready for button clicks below.
        mediaPlayer = MediaPlayer.create(this, R.raw.celebration);

        Button playButton = (Button) findViewById(R.id.button_play);
        playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "Playing 播放中", Toast.LENGTH_SHORT).show(); //按下按鈕(onClick)時顯示"播放中"快顯訊息
                mediaPlayer.start();                                                                                //並播放音樂
                mediaPlayer.setOnCompletionListener(new OnCompletionListener() {                                    //在播放器開始播放(start)後設置onCompletionListener偵測音樂播放完畢
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Toast.makeText(MainActivity.this, "Done playing 播放完畢", Toast.LENGTH_SHORT).show(); //音樂播放完畢時快顯提示
                    }
                });
            }
        });


        Button pauseButton = (Button) findViewById(R.id.button_pause);
        pauseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "Pausing 暫停中", Toast.LENGTH_SHORT).show(); //按下按鈕(onClick)時顯示"暫停中"快顯訊息
                mediaPlayer.pause();                                                                                //並暫停音樂
            }
        });


        Button resetButton = (Button) findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "Resetting 重置", Toast.LENGTH_SHORT).show(); //按下按鈕(onClick)時顯示"重置"快顯訊息
                mediaPlayer.pause();                                                                               //暫停音樂
                mediaPlayer.seekTo(0);                                                                      //飛梭到音樂的起始秒數 = 第0毫秒
                mediaPlayer.start();

                //mediaPlayer.reset() – 重置（目前測試重置後沒辦法馬上重新播放）
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.release();
    }
}