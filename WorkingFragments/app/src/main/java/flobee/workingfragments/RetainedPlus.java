package flobee.workingfragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

public class RetainedPlus extends Fragment {

  boolean mReady   = false;
  boolean mQuitting = false;
  final Thread mThread = new Thread() {
    @Override
    public void run () {
      for (int ii=0; ii<251; ii++) {
        synchronized (this) {
          while (!mReady && !mQuitting) {
            try {
              wait();
            }
            catch (InterruptedException e){
              e.printStackTrace();
            }
          }
          if (mQuitting)
            return;
          Log.i("ATAG", "Retained ii: " + ii + " "+ Thread.currentThread().getName());
          MainActivity activity = (MainActivity)RetainedPlus.this.getActivity();
          if      (ii==1)  activity.notifyAboutWork(" at 1%");
          else if (ii==63)  activity.notifyAboutWork(" at 25%");
          else if (ii==213) activity.notifyAboutWork(" at 75%");
          else if (ii==250) activity.notifyAboutWork(" at 100%");
        }//end sync
        try {
          this.sleep(20l);
        }catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  };


  @Override
  public void onAttach (Context context) {
    super.onAttach(context);
    log("onAttach()");
  }

  @Override
  public void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    log("onCreate()");
    setRetainInstance(true);
    mThread.start();
  }

  @Override
  public void onActivityCreated (Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    log("onActivityCreated()");
    synchronized (mThread) {
      mReady = true;
      mThread.notify();
    }

  }

  @Override
  public void onStart () {
    super.onStart();
    log("onStart()");
  }

  @Override
  public void onResume () {
    super.onResume();
    log("onResume()");
  }

  @Override
  public void onPause () {
    super.onPause();
    log("onPause()");
  }

  @Override
  public void onStop () {
    super.onStop();
    log("onStop()");
  }

  @Override
  public void onDestroy() {
    log("onDestroy()");
    synchronized (mThread) {
      mReady = false;
      mQuitting = true;
      mThread.notify();
    }
    super.onDestroy();
  }

  // IMPORTANT
  @Override
  public void onDetach () {
    log("onDetach()");
    super.onDetach();
  }

  private void log (String where) {
    Log.i("ATAG", "Retained" + " - " + Thread.currentThread().getName() + "Thread " + where);
  }

}
