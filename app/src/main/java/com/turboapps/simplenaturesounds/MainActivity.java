package com.turboapps.simplenaturesounds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.SoundPool.OnLoadCompleteListener;
import android.media.SoundPool;
import android.media.AudioManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    /**
     * Called when the activity is first created.
     */

    private SoundPool soundPool;
    private int my_sound;
    boolean plays = false, loaded = false;
    float actVolume, maxVolume, volume;
    AudioManager audioManager;
    int counter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // AudioManager audio settings for adjusting the volume
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;

        //Hardware buttons setting to adjust the media sound
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        // the counter will help us recognize the stream id of the sound played  now
        counter = 0;

        // Load the sounds
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        my_sound = soundPool.load(this, R.raw.crickets, 1);

        soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
            public void onLoadComplete(SoundPool soundPool, int sampleId,int status) {
                loaded = true;
            }
        });

    }

    public void playSound(View v) {
        // Is the sound loaded does it already play?
        if (loaded && !plays) {
            soundPool.play(my_sound, volume, volume, 1, -1, 1f);
            counter = counter++;
            plays = true;
        }
    }
}
