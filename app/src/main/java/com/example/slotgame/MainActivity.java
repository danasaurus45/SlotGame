package com.example.slotgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView _slot1,_slot2,_slot3,_slot4;
    Button _btStart;
    boolean isPlay=false;
    private static int[] _imgs = {R.drawable.slot1, R.drawable.slot2, R.drawable.slot3, R.drawable.slot4,
            R.drawable.slot5, R.drawable.slotbar};

    SlotAsyncTask _slotAsyn1,_slotAsyn2,_slotAsyn3,_slotAsyn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _slot1 = findViewById(R.id.id_Slot1);
        _slot2 = findViewById(R.id.id_Slot2);
        _slot3 = findViewById(R.id.id_Slot3);
        _slot4 = findViewById(R.id.id_Slot4);

        _slot1.setImageResource(R.drawable.slotbar);
        _slot2.setImageResource(R.drawable.slotbar);
        _slot3.setImageResource(R.drawable.slotbar);
        _slot4.setImageResource(R.drawable.slotbar);

        _btStart = findViewById(R.id.id_BtPlay);
        _btStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        if(v.getId()==_btStart.getId())
        {
            if(!isPlay){


                _slotAsyn1 = new SlotAsyncTask();
                _slotAsyn2 = new SlotAsyncTask();
                _slotAsyn3 = new SlotAsyncTask();
                _slotAsyn4 = new SlotAsyncTask();


                _slotAsyn1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_slot1);
                _slotAsyn2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_slot2);
                _slotAsyn3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_slot3);
                _slotAsyn4.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,_slot4);

                _btStart.setText("Stop");
                isPlay=!isPlay;
            }
            else {
                _slotAsyn1._play = false;
                _slotAsyn2._play = false;
                _slotAsyn3._play = false;
                _slotAsyn4._play = false;
                _btStart.setText("Play");
                isPlay=!isPlay;
            }

        }

    }


    private class SlotAsyncTask extends AsyncTask<ImageView, Integer, Boolean> {

        ImageView _slotImg;
        Random _random = new Random();
        public  boolean _play=true;





        public SlotAsyncTask() {
               _play=true;
        }

        @Override
        protected Boolean doInBackground(ImageView... imgs) {
            _slotImg = imgs[0];
            int a=0;
            while (_play) {
                int i = _random.nextInt(6);

                publishProgress(i);

              try {
                    Thread.sleep(_random.nextInt(500));}
                 catch (InterruptedException e) {
                 e.printStackTrace(); }
                }
            return !_play;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            _slotImg.setImageResource(_imgs[values[0]]);
        }
    }


}