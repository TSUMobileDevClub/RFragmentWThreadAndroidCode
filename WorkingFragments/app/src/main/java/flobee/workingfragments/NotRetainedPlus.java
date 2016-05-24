package flobee.workingfragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

public class NotRetainedPlus extends Fragment {

  boolean mReady = false;

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
      }
      for (int ii=0; ii<251; ii++) {
        try {
          this.sleep(20l);
        }catch (InterruptedException e) {
          e.printStackTrace();
        }
        Log.i("ATAG", "NotRetainedFragment ii: " + ii + " "+ Thread.currentThread().getName());
        synchronized (this) {
          if (mReady) {
            MainActivity activity = (MainActivity)NotRetainedPlus.this.getActivity();
            if      (ii==1) activity.notifyAboutWork(" at 1%");
            else if (ii==63)  activity.notifyAboutWork(" at 25%");
            else if (ii==213) activity.notifyAboutWork(" at 75%");
            else if (ii==250) activity.notifyAboutWork(" at 100%");
          }
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
    setRetainInstance(false);
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
      mThread.notify();
    }
    super.onDestroy();
  }

  @Override
  public void onDetach () {
    log("onDetach()");
    super.onDetach();
  }

  private void log (String where) {
    Log.i("ATAG", "NotRetained" + " - " + Thread.currentThread().getName() + "Thread " + where);
  }

}
