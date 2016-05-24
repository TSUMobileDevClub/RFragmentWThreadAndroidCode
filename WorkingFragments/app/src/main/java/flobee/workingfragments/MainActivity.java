package flobee.workingfragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
  public Thread activityThread = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    log("onCreate-begin()");
    setContentView(R.layout.activity_main);
    FragmentManager fm = getSupportFragmentManager();
    MusicalFragment frag = (MusicalFragment) fm.findFragmentByTag("work");
    if (frag == null) {
      frag = new MusicalFragment();
      fm.beginTransaction().add(frag, "work").commit();
    }
    log("onCreate-end()");
  }

  public void stopFragment (View view) {
    log("stopFragment() begin");
    FragmentManager fm = getSupportFragmentManager();
    MusicalFragment frag = (MusicalFragment) fm.findFragmentByTag("work");
    if (frag != null)
      fm.beginTransaction().remove(frag).commit();
  }

  @Override
  protected void onStart() {
    super.onStart();
    log("onStart()");
  }

  @Override
  protected void onRestoreInstanceState (Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    log("onRestoreInstanceState()");
  }

  @Override
  protected void onResume () {
    super.onResume();
    log("onResume()");
  }

  @Override
  protected void onPause () {
    super.onPause();
    log("onPause()");
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    log("onSaveInstanceState()");
  }

  @Override
  protected void onStop () {
    super.onStop();
    log("onStop()");
  }

  @Override
  protected void onDestroy () {
    super.onDestroy();
    log("onDestroy()");
  }

  private void log (String where) {
    Log.i("ATAG", Thread.currentThread().getName() + " Activity" + " - " + where);
  }

  public void notifyAboutWork (String status) {
    Log.i("ATAG", Thread.currentThread().getName() + " Work " + status);
  }

}
