package study.com.ailatrieuphu;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.util.Timer;


/**
 * Created by Administrator on 07/03/2018.
 */

public class AudioClip {
    private MediaPlayer mPlayer;
    private String name;
    Timer t;
    public boolean mPlaying = false;
    private boolean mLoop = false;
    public static boolean state_sound=true;

    public AudioClip(Context context, int resID) {
        name = context.getResources().getResourceName(resID);

        mPlayer = MediaPlayer.create(context, resID);
        if (state_sound == false) {
            mPlayer.setVolume(0, 0);
        } else {
            mPlayer.setVolume(1000, 1000);
        }

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mPlaying = false;
                if (mLoop) {
                    Log.v("dd", "dd");
                    System.out.println("AudioClip loop " + name);
                    mediaPlayer.start();
                }
            }
        });
    }

    //	public void time () {
//		t=new Timer();
//		t.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//			//	if()
//				Log.v(" 111"," 1111");
//				if(mPlayer.isPlaying()==false)
//				{
//					Log.v(" msg"," tr");
//					mPlaying = false;
//					t.cancel();
//				}
//			}
//		}, 0,1000);
//
//	}

    public synchronized void play () {
        if ( mPlaying)
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        if (mPlayer != null ) {
            mPlaying = true;
            mPlayer.start();

        }
    }

    public synchronized void stop() {
        try {
            mLoop = false;
            if ( mPlaying ) {
                mPlaying = false;
                mPlayer.pause();
            }
        } catch (Exception e) {
            System.err.println("AduioClip::stop " + name + " " + e.toString());
        }
    }

    public synchronized void loop () {
        mLoop = true;
        mPlaying = true;
        mPlayer.start();

    }

    public void relese () {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
