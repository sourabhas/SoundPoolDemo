package com.soushetty.soundpool;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // three separate buttons for 3 different audio's
    private Button button1,button2,button3;

    // SoundPool class manages and plays audio resources for applications.
    private SoundPool soundPool;
    private int sound1,sound2,sound3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          /*object created to tell sound pool what type of audio we are sending and what should be set up
        inorder to avoid memory leaks other catastrophes which may occur*/
        AudioAttributes audioAttribites=new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION) //the type of audio we are passing
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION) //defining the usage of the audio(refer Android developer guide for different types)
                .build();

        //Builder class for SoundPool objects,create and configure a SoundPool instance
        soundPool=new SoundPool.Builder() //sound pool will allocate memory for every attribute we add
                .setMaxStreams(3) //numbers of memory allocation req,here 3 as we are going to add 3 audio's
                .setAudioAttributes(audioAttribites) //created a variable separately to pass as object
                .build();

        //Load the sound from the specified path.
        sound1=soundPool.load(this,R.raw.complete,1);
        sound2=soundPool.load(this,R.raw.correct,1);
        sound3=soundPool.load(this,R.raw.defeat_one,1);

        //initializing the buttons using their id
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);

        //events to perform on clicking each button
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button1:
                //play sound for Button1
                soundPool.play(sound1,1,1,0,0,1);
                break;
            case R.id.button2:
                //play 2nd song
                soundPool.play(sound2,1,1,0,0,1);
                break;
            case R.id.button3:
                //play 3rd song
                soundPool.play(sound3,1,1,0,0,1);
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(soundPool!=null){
            soundPool.release(); //to release the memory
            soundPool=null; //to again confirm that it's released from memory
        }
    }
}
