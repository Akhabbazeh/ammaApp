package com.zidnyscience.ammaApp.feature.anashid_feature;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.BeAnashidItem;
import com.zidnyscience.utils.Tools;

import java.util.ArrayList;
import java.util.List;


public class AnashidFragment extends Fragment
{
    private View view;
    private RecyclerView recycle_anashid;
    private ExoPlayer player;
    private List<BeAnashidItem> anashidItemList;
    private AdapterRecycleAnasid adapterRecycleAnasid;
    private ImageView img_amma_logo;
    private ImageView anashid_fragment_background;
    private ConstraintLayout player_view_layout;
    private ImageView btn_run_player;
    private ImageView btn_stop_player;
    private SeekBar progress_bar;
    private LinearLayout txt_times_layout;
    private TextView current_time;
    private TextView total_time;
    private Handler handler = new Handler();

    public AnashidFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.anashid_fragment, container, false);
        recycle_anashid = view.findViewById(R.id.recycle_anashid);
        img_amma_logo = view.findViewById(R.id.img_amma_logo);
        anashid_fragment_background = view.findViewById(R.id.anashid_fragment_background);
        player_view_layout = view.findViewById(R.id.player_view_layout);
        btn_run_player = view.findViewById(R.id.btn_run_player);
        btn_stop_player = view.findViewById(R.id.btn_stop_player);
        btn_stop_player = view.findViewById(R.id.btn_stop_player);
        txt_times_layout = view.findViewById(R.id.txt_times_layout);
        current_time =view.findViewById(R.id.current_time);
        total_time = view.findViewById(R.id.total_time);
        progress_bar = view.findViewById(R.id.progress_bar);


        initStatuesBar();
        initComponenets();
        intiRecycleAnashid();
        getData();

        return view;
    }

    private void getData() {
        anashidItemList.add(new BeAnashidItem(1,"رحمن يار حمن","المنشد الشيخ:","مشاري العفاسي"));
        anashidItemList.add(new BeAnashidItem(2,"يا أمة القرآن","المنشد الشيخ:","منذر أبو الجود سرميني"));
        anashidItemList.add(new BeAnashidItem(3,"ما زلت ألازم قرآني","المنشد الشيخ:","منذر أبو الجود سرميني"));
        anashidItemList.add(new BeAnashidItem(4,"قلبي ينادي يارب","المنشد الشيخ:","منذر أبو الجود سرميني"));
        anashidItemList.add(new BeAnashidItem(5, "هذا القرآن","المنشد الشيخ:"," منذر أبو الجود سرميني"));
        anashidItemList.add(new BeAnashidItem(6,"تاج الوقار","المنشد الشيخ:","عمار صرصر"));
        anashidItemList.add(new BeAnashidItem(7,"رسول الله","المنشد الشيخ:","منذر أبو الجود سرميني"));
        adapterRecycleAnasid.setAnashidItemList(anashidItemList);
    }

    private void initComponenets() {
        Tools.displayImageDrawable(getContext(), anashid_fragment_background, R.drawable.background_pattern);
        Tools.displayImageDrawable(getContext(), img_amma_logo, R.drawable.amma_logo);

        btn_run_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying()) {
                    player.pause();
                    Tools.displayImageDrawable(getContext(),btn_run_player,R.drawable.run_player_icon);

                } else {
                    player.play();
                    Tools.displayImageDrawable(getContext(),btn_run_player,R.drawable.pause_player_icon);


                }

            }
        });

        btn_stop_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                if (player != null) {
                    player.release();
                    player = null;
                }

                Tools.displayImageDrawable(getContext(),btn_run_player,R.drawable.run_player_icon);
                player_view_layout.setVisibility(View.GONE);
                txt_times_layout.setVisibility(View.INVISIBLE);



            }
        });


        progress_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser && player != null) {
                    current_time.setText(formatTime(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (player != null) {
                    int newPosition = seekBar.getProgress();
                    player.seekTo(newPosition);
                }
            }
        });



    }

    private void startAudio(int audioResId) {
        txt_times_layout.setVisibility(View.INVISIBLE);
        Tools.displayImageDrawable(getContext(), btn_run_player, R.drawable.pause_player_icon);
        player_view_layout.setVisibility(View.VISIBLE);

        if (player != null) {
            player.release();
        }

        player = new ExoPlayer.Builder(getContext()).build();

        Uri audioUri = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + audioResId);
        MediaItem mediaItem = MediaItem.fromUri(audioUri);

        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();

        handler.post(updateProgressAction);

        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int playbackState) {
                if (playbackState == Player.STATE_READY) {
                    txt_times_layout.setVisibility(View.VISIBLE);

                    long duration = player.getDuration();
                    total_time.setText(formatTime(duration));
                } else {
                    txt_times_layout.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private final Runnable updateProgressAction = new Runnable() {
        @Override
        public void run() {
            if (player != null) {
                long currentPosition = player.getCurrentPosition();
                long duration = player.getDuration();

                progress_bar.setMax((int) duration);
                progress_bar.setProgress((int) currentPosition);


                current_time.setText(formatTime(currentPosition));
                total_time.setText("/"+formatTime(duration));

                progress_bar.setSecondaryProgress((int) player.getBufferedPosition());

                handler.postDelayed(this, 200);
            }
        }
    };


    private void initStatuesBar() {
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.background_color));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void intiRecycleAnashid(){
        anashidItemList = new ArrayList<>();
        adapterRecycleAnasid= new AdapterRecycleAnasid(getContext(),anashidItemList);
        recycle_anashid.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recycle_anashid.setHasFixedSize(true);
        recycle_anashid.setAdapter(adapterRecycleAnasid);

        adapterRecycleAnasid.setOnClickListener(new AdapterRecycleAnasid.OnClickListener() {
            @Override
            public void OnSuraClickListener(int audio_path) {
                startAudio(R.raw.rahman);
            }
        });
    }

    private String formatTime(long millis) {
        int seconds = (int) (millis / 1000) % 60;
        int minutes = (int) (millis / (1000 * 60));
        return String.format("%d:%02d", minutes, seconds);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null && player.isPlaying()) {
            player.pause();
            Tools.displayImageDrawable(getContext(),btn_run_player,R.drawable.run_player_icon);
        }
    }


}

