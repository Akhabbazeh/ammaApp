package com.zidnyscience.ammaApp.feature.moshaf_almoallem_feature;


import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.MyApplication;
import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.BeSwarIndexItem;
import com.zidnyscience.model.BeTeacherKoran;
import com.zidnyscience.utils.Tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoshafAlmoallemFragment extends Fragment {
    private View view;
    private ImageView img_amma_logo;
    private ImageView azkar_fragment_background;
    private List<String> teacherList;
    private AdapterSpinnerTeacherTopic adapterSpinnerTeacherTopic;
    private List<BeTeacherKoran> beSwarIndexItems;
    private AdapterRecycleSwarTeacher adapterRecycleSwarTeacher;
    private RecyclerView recycle_the_teacher_koran;
    private Spinner spinnerTeacher;
    private ExoPlayer player;
    private TextView current_time;
    private TextView total_time;
    private SeekBar progress_bar;
    private ImageView btn_run_player;
    private ImageView btn_stop_player;
    private ImageView makhtota_img_bottom_bar;
    private Handler handler = new Handler();
    private LinearLayout txt_times_layout;
    private ConstraintLayout player_view_layout;
    private ConstraintLayout coordinator;

    public MoshafAlmoallemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.moshaf_almoallem_fragment, container, false);
        img_amma_logo = view.findViewById(R.id.img_amma_logo);
        azkar_fragment_background = view.findViewById(R.id.azkar_fragment_background);
        spinnerTeacher = view.findViewById(R.id.spinnerTeacher);
        recycle_the_teacher_koran = view.findViewById(R.id.recycle_the_teacher_koran);
        current_time = view.findViewById(R.id.current_time);
        total_time = view.findViewById(R.id.total_time);
        btn_run_player = view.findViewById(R.id.btn_run_player);
        btn_stop_player = view.findViewById(R.id.btn_stop_player);
        makhtota_img_bottom_bar = view.findViewById(R.id.makhtota_img_bottom_bar);
        progress_bar = view.findViewById(R.id.progress_bar);
        txt_times_layout = view.findViewById(R.id.txt_times_layout);
        player_view_layout = view.findViewById(R.id.player_view_layout);
        coordinator = view.findViewById(R.id.coordinator);



        initComponenets();
        initStatuesBar();
        intiRecycleSwarIndex();
        initChooseTeacherSpinner();

        return view;
    }

    private void initStatuesBar() {
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.background_color));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void initComponenets() {

        Tools.displayImageDrawable(getContext(), azkar_fragment_background, R.drawable.background_pattern);
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

    private String formatTime(long millis) {
        int seconds = (int) (millis / 1000) % 60;
        int minutes = (int) (millis / (1000 * 60));
        return String.format("%d:%02d", minutes, seconds);
    }

    private void initChooseTeacherSpinner()
    {

        teacherList = new ArrayList<>();
        teacherList.add("محمد صديق المنشاوي (جزء عمّ)");
        teacherList.add("محمد صديق المنشاوي (المصحف كاملاً)");
        teacherList.add("محمود خليل الحصري (جزء عمّ)");
        teacherList.add("محمود خليل الحصري (المصحف كاملاً)");
        adapterSpinnerTeacherTopic = new AdapterSpinnerTeacherTopic(getContext(), teacherList);

        spinnerTeacher.setAdapter(adapterSpinnerTeacherTopic);

        spinnerTeacher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                beSwarIndexItems.clear();
                if (position == 0 || position == 2) {
                  getData(78);
                } else {
                  getData(1);
                }

                if (position == 2 || position == 3){
                    adapterRecycleSwarTeacher.setMonshawi(false);
                }else {
                    adapterRecycleSwarTeacher.setMonshawi(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
}


    private void getData(int start) {

        if (start ==1){
            beSwarIndexItems.add(new BeTeacherKoran(1, R.drawable.p001, 1, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/001.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/001.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(2, R.drawable.p002, 2, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/002.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/002.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(3, R.drawable.p003, 3, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/003.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/003.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(4, R.drawable.p004, 4, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/004.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/004.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(5, R.drawable.p005, 5, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/005.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/005.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(6, R.drawable.p006, 6, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/006.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/006.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(7, R.drawable.p007, 7, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/007.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/007.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(8, R.drawable.p008, 8, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/008.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/008.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(9, R.drawable.p009, 9, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/009.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/009.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(10, R.drawable.p0010, 10, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/010.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/010.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(11, R.drawable.p0011, 11, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/011.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/011.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(12, R.drawable.p0012, 12, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/012.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/012.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(13, R.drawable.p0013, 13, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/013.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/013.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(14, R.drawable.p0014, 14, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/014.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/014.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(15, R.drawable.p0015, 15, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/015.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/015.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(16, R.drawable.p0016, 16, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/016.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/016.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(17, R.drawable.p0017, 17, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/017.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/017.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(18, R.drawable.p0018, 18, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/018.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/018.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(19, R.drawable.p0019, 19, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/019.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/019.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(20, R.drawable.p0020, 20, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/020.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/020.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(21, R.drawable.p0021, 21, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/021.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/021.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(22, R.drawable.p0022, 22, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/022.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/022.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(23, R.drawable.p0023, 23, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/023.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/023.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(24, R.drawable.p0024, 24, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/024.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/024.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(25, R.drawable.p0025, 25, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/025.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/025.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(26, R.drawable.p0026, 26, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/026.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/026.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(27, R.drawable.p0027, 27, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/027.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/027.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(28, R.drawable.p0028, 28, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/028.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/028.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(29, R.drawable.p0029, 29, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/029.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/029.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(30, R.drawable.p0030, 30, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/030.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/030.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(31, R.drawable.p0031, 31, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/031.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/031.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(32, R.drawable.p0032, 32, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/032.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/032.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(33, R.drawable.p0033, 33, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/033.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/033.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(34, R.drawable.p0034, 34, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/034.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/034.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(35, R.drawable.p0035, 35, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/035.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/035.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(36, R.drawable.p0036, 36, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/036.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/036.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(37, R.drawable.p0037, 37, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/037.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/037.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(38, R.drawable.p0038, 38, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/038.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/038.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(39, R.drawable.p0039, 39, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/039.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/039.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(40, R.drawable.p0040, 40, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/040.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/040.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(41, R.drawable.p0041, 41, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/041.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/041.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(42, R.drawable.p0042, 42, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/042.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/042.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(43, R.drawable.p0043, 43, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/043.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/043.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(44, R.drawable.p0044, 44, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/044.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/044.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(45, R.drawable.p0045, 45, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/045.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/045.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(46, R.drawable.p0046, 46, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/046.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/046.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(47, R.drawable.p0047, 47, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/047.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/047.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(48, R.drawable.p0048, 48, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/048.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/048.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(49, R.drawable.p0049, 49, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/049.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/049.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(50, R.drawable.p0050, 50, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/050.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/050.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(51, R.drawable.p0051, 51, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/051.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/051.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(52, R.drawable.p0052, 52, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/052.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/052.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(53, R.drawable.p0053, 53, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/053.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/053.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(54, R.drawable.p0054, 54, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/054.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/054.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(55, R.drawable.p0055, 55, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/055.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/055.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(56, R.drawable.p0056, 56, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/056.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/056.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(57, R.drawable.p0057, 57, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/057.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/057.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(58, R.drawable.p0058, 58, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/058.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/058.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(59, R.drawable.p0059, 59, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/059.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/059.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(60, R.drawable.p0060, 60, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/060.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/060.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(61, R.drawable.p0061, 61, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/061.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/061.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(62, R.drawable.p0062, 62, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/062.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/062.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(63, R.drawable.p0063, 63, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/063.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/063.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(64, R.drawable.p0064, 64, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/064.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/064.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(65, R.drawable.p0065, 65, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/065.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/065.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(66, R.drawable.p0066, 66, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/066.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/066.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(67, R.drawable.p0067, 67, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/067.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/067.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(68, R.drawable.p0068, 68, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/068.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/068.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(69, R.drawable.p0069, 69, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/069.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/069.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(70, R.drawable.p0070, 70, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/070.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/070.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(71, R.drawable.p0071, 71, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/071.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/071.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(72, R.drawable.p0072, 72, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/072.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/072.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(73, R.drawable.p0073, 73, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/073.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/073.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(74, R.drawable.p0074, 74, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/074.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/074.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(75, R.drawable.p0075, 75, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/075.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/075.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(76, R.drawable.p0076, 76, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/076.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/076.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(77, R.drawable.p0077, 77, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/077.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/077.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(78, R.drawable.p0078, 78, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/078.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/078.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(79, R.drawable.p0079, 79, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/079.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/079.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(80, R.drawable.p0080, 80, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/080.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/080.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(81, R.drawable.p0081, 81, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/081.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/081.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(82, R.drawable.p0082, 82, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/082.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/082.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(83, R.drawable.p0083, 83, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/083.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/083.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(84, R.drawable.p0084, 84, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/084.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/084.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(85, R.drawable.p0085, 85, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/085.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/085.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(86, R.drawable.p0086, 86, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/086.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/086.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(87, R.drawable.p0087, 87, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/087.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/087.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(88, R.drawable.p0088, 88, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/088.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/088.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(89, R.drawable.p0089, 89, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/089.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/089.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(90, R.drawable.p0090, 90, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/090.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/090.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(91, R.drawable.p0091, 91, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/091.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/091.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(92, R.drawable.p0092, 92, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/092.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/092.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(93, R.drawable.p0093, 93, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/093.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/093.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(94, R.drawable.p0094, 94, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/094.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/094.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(95, R.drawable.p0095, 95, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/095.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/095.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(96, R.drawable.p0096, 96, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/096.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/096.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(97, R.drawable.p0097, 97, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/097.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/097.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(98, R.drawable.p0098, 98, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/098.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/098.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(99, R.drawable.p0099, 99, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/099.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/099.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(100, R.drawable.p00100, 100, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/100.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/100.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(101, R.drawable.p00101, 101, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/101.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/101.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(102, R.drawable.p00102, 102, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/102.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/102.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(103, R.drawable.p00103, 103, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/103.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/103.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(104, R.drawable.p00104, 104, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/104.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/104.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(105, R.drawable.p00105, 105, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/105.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/105.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(106, R.drawable.p00106, 106, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/106.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/106.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(107, R.drawable.p00107, 107, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/107.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/107.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(108, R.drawable.p00108, 108, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/108.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/108.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(109, R.drawable.p00109, 109, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/109.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/109.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(110, R.drawable.p00110, 110, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/110.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/110.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(111, R.drawable.p00111, 111, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/111.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/111.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(112, R.drawable.p00112, 112, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/112.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/112.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(113, R.drawable.p00113, 113, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/113.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/113.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(114, R.drawable.p00114, 114, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/114.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/114.mp3"));


        }
        else {
            beSwarIndexItems.add(new BeTeacherKoran(78, R.drawable.p0078, 78, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/078.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/078.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(79, R.drawable.p0079, 79, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/079.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/079.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(80, R.drawable.p0080, 80, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/080.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/080.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(81, R.drawable.p0081, 81, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/081.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/081.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(82, R.drawable.p0082, 82, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/082.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/082.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(83, R.drawable.p0083, 83, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/083.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/083.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(84, R.drawable.p0084, 84, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/084.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/084.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(85, R.drawable.p0085, 85, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/085.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/085.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(86, R.drawable.p0086, 86, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/086.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/086.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(87, R.drawable.p0087, 87, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/087.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/087.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(88, R.drawable.p0088, 88, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/088.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/088.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(89, R.drawable.p0089, 89, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/089.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/089.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(90, R.drawable.p0090, 90, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/090.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/090.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(91, R.drawable.p0091, 91, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/091.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/091.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(92, R.drawable.p0092, 92, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/092.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/092.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(93, R.drawable.p0093, 93, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/093.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/093.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(94, R.drawable.p0094, 94, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/094.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/094.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(95, R.drawable.p0095, 95, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/095.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/095.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(96, R.drawable.p0096, 96, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/096.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/096.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(97, R.drawable.p0097, 97, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/097.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/097.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(98, R.drawable.p0098, 98, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/098.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/098.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(99, R.drawable.p0099, 99, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/099.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/099.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(100, R.drawable.p00100, 100, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/100.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/100.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(101, R.drawable.p00101, 101, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/101.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/101.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(102, R.drawable.p00102, 102, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/102.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/102.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(103, R.drawable.p00103, 103, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/103.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/103.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(104, R.drawable.p00104, 104, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/104.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/104.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(105, R.drawable.p00105, 105, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/105.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/105.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(106, R.drawable.p00106, 106, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/106.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/106.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(107, R.drawable.p00107, 107, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/107.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/107.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(108, R.drawable.p00108, 108, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/108.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/108.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(109, R.drawable.p00109, 109, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/109.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/109.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(110, R.drawable.p00110, 110, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/110.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/110.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(111, R.drawable.p00111, 111, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/111.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/111.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(112, R.drawable.p00112, 112, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/112.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/112.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(113, R.drawable.p00113, 113, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/113.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/113.mp3"));
            beSwarIndexItems.add(new BeTeacherKoran(114, R.drawable.p00114, 114, "https://server10.mp3quran.net/minsh/Almusshaf-Al-Mo-lim/114.mp3", "https://server13.mp3quran.net/husr/Almusshaf-Al-Mojawwad/114.mp3"));

        }

        adapterRecycleSwarTeacher.setSwarList(beSwarIndexItems);

    }





    private void startAudio(String url,int makhtota_img){
        txt_times_layout.setVisibility(View.INVISIBLE);
        Tools.displayImageDrawable(getContext(),btn_run_player,R.drawable.pause_player_icon);
        Tools.displayImageDrawable(getContext(),makhtota_img_bottom_bar,makhtota_img);
        player_view_layout.setVisibility(View.VISIBLE);


        if (player != null) {
            player.release();
        }

        player = new ExoPlayer.Builder(getContext()).build();
        MediaItem mediaItem = MediaItem.fromUri(url);
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


    private void intiRecycleSwarIndex(){

        beSwarIndexItems = new ArrayList<>();
        adapterRecycleSwarTeacher= new AdapterRecycleSwarTeacher(getContext(),beSwarIndexItems);
        recycle_the_teacher_koran.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recycle_the_teacher_koran.setHasFixedSize(true);
        recycle_the_teacher_koran.setAdapter(adapterRecycleSwarTeacher);
        adapterRecycleSwarTeacher.setOnClickListener(new AdapterRecycleSwarTeacher.OnClickListener() {
            @Override
            public void OnSuraClickListener(String url,int makhtota_img) {
            if (MyApplication.hasNetwork()){
                startAudio(url,makhtota_img);
            }else {
                Tools.makeSnakbar(requireContext(),requireActivity(),coordinator,"لا يوجد اتصال بالانترنت الرجاء المحاولة لاحقاَ",R.color.error_color);
            }

            }
        });

        recycle_the_teacher_koran.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (player !=null){
                    if (dy > 0) {
                        player_view_layout.setVisibility(View.GONE);
                    } else if (dy < 0) {
                        player_view_layout.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                // التحقق من حالة التمرير
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // التمرير متوقف
                    Log.d("RecyclerView", "Scroll stopped");
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    // المستخدم يقوم بالسحب
                    Log.d("RecyclerView", "Scrolling by user");
                } else if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    // التمرير تحت تأثير القصور الذاتي
                    Log.d("RecyclerView", "Scroll settling");
                }
            }
        });


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