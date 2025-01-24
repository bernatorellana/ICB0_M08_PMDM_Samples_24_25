package org.milaifontanals.onelinepuzzle;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundPlayer {

    public static void playSound(Context context, int soundResourceId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, soundResourceId);
        mediaPlayer.start();

        // Optional: Release the MediaPlayer when playback is complete
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }
}