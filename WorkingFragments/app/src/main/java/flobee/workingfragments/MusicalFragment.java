package flobee.workingfragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class MusicalFragment extends Fragment {

  boolean mReady = false;
  MediaPlayer player;

  final Thread mThread = new Thread() {
    @Override
    public void run () {
      synchronized (this) {
        while (!mReady) {
          try {
            wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        player = MediaPlayer.create(getActivity().getApplicationContext(),
                                    R.raw.amazing_grace);
        player.start();
      } //end synchronization
      for (int ii=0; ii<251; ii++) {
        try {
          this.sleep(200l);
        }catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      player.stop();
      player.release();
    }
  };

  @Override
  public void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(false);
    mThread.start();
  }

  @Override
  public void onActivityCreated (Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    synchronized (mThread) {
      mReady = true;
      mThread.notify();
    }
  }
}
