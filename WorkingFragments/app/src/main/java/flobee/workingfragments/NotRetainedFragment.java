package flobee.workingfragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

public class NotRetainedFragment extends Fragment {

  @Override
  public void onAttach (Context context) {
    super.onAttach(context);
    log("onAttach");
  }

  @Override
  public void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(false);
    log("onCreate()");
  }

  @Override
  public void onActivityCreated (Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    log("onActivityCreated()");
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
    super.onDestroy();
  }

  @Override
  public void onDetach () {
    log("onDetach()");
    super.onDetach();
  }

  private void log (String where) {
    Log.i("ATAG", Thread.currentThread().getName() + " NotRetained" + " - " + where);
  }

}
