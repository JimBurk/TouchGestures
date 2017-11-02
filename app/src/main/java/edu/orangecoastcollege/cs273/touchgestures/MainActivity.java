package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
    implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        singleTapTextView = (TextView) findViewById(R.id.singleTapTextView);
        doubleTapTextView = (TextView) findViewById(R.id.doubleTapTextView);
        longPressTextView = (TextView) findViewById(R.id.longPressTextView);
        scrollTextView = (TextView) findViewById(R.id.scrollTextView);
        flingTextView = (TextView) findViewById(R.id.flingTextView);

        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);

        mGestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
    }

    /***
     *
     * @param ev the motion event triggering the touch.
     * @return True if the event was handled, false otherwise
     */

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {

        gesturesLogTextView.append("\nonSingleTap gesture occurred");
        singleTapTextView.setText(String.valueOf(++singleTaps));
        return true;
    }

    /***
     * Responds to double tap event, i.e. two single tap gestures within some duration
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
          gesturesLogTextView.append("\nonDoubleTap gesture occurred");
          doubleTapTextView.setText(String.valueOf(++doubleTaps));    ;
          return true;
    }

    /***
     * During a double tap, another event occurs (including move).
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    /***
     * Every gesture begins with a down event
      * @param motionEvent
     * @return
     */
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDown gesture occurred");
        return true;
    }

    /***
     * Down event where user doesn't let go in a short time
     * @param motionEvent
     */
    @Override
    public void onShowPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nPress gesture occurred");
    }

    /***
     * Similar to onSingleTapConfirmed, but could be part of a double tap sequence
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonSingleTapUp gesture occurred");
        return true;
    }

    /***
     * Down event, folloewed by a press and lateral movement, without letting go
     * @param motionEvent - The event where the scroll started
     * @param motionEvent1 - The event where scroll stopped
     * @param distanceX - in pixels -
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float distanceX, float distanceY) {
        gesturesLogTextView.append("\nScroll gesture occurred, distanceX = " + distanceX + ", distanceY = " + distanceY);
        scrollTextView.setText(String.valueOf(++scrolls));
        return true;
    }

    /***
     * Down and hold for long duration
     * @param motionEvent
     */
    @Override
    public void onLongPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nPress gesture occurred");
        longPressTextView.setText(String.valueOf(++longPresses));
    }
    /***
     * Similar to scroll with fast velocity and release
     * @param motionEvent
     * @param motionEvent1
     * @param v - x velocity, pixels per second
     * @param v1 - y velocity
     * @return
     */
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        gesturesLogTextView.append("\nFling gesture occurred, velocityX = " + v + ", velocityY = " + v1);
        flingTextView.setText(String.valueOf(++flings));
        return true;
    }

    public void clearAll (View v) {
        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings = 0;

        gesturesLogTextView.setText("");

        singleTapTextView.setText(getString(R.string.zero));
        doubleTapTextView.setText(getString(R.string.zero));
        longPressTextView.setText(getString(R.string.zero));
        scrollTextView.setText(getString(R.string.zero));
        flingTextView.setText(getString(R.string.zero));

    }
}
