package com.zidnyscience.ammaApp.feature.azkar_feature;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.BeAzkarIndexIteam;
import com.zidnyscience.utils.Tools;

import java.util.ArrayList;
import java.util.List;


public class AzkarFragment extends Fragment
{
    private View view;
    private ImageView img_amma_logo;
    private ImageView azkar_fragment_background;
    private String selectedFragment = "";
    private LinearLayout txt_morning_layout;
    private View view_morning;
    private TextView txt_morning;
    private LinearLayout txt_night_layout;
    private View view_night;
    private TextView txt_night;
    private LinearLayout txt_other_layout;
    private View view_other;
    private TextView txt_other;
    private TextView txt_azkar_data;
    private RecyclerView recycle_azkar_other;
    private AdapterRecycleAzkarIndex adapterRecycleAzkarIndex;
    private List<BeAzkarIndexIteam> beAzkarIndexIteamList;
    private ImageView azkar_card_background;
    private OnBackPressedCallback callback;
    private ImageView btn_play_zkr_sound;
    private MediaPlayer mediaPlayer;
    private BeAzkarIndexIteam beAzkarIndex;
    private boolean audio_playing = false;

    public AzkarFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.azkar_fragment, container, false);
        img_amma_logo = view.findViewById(R.id.img_amma_logo);
        azkar_fragment_background = view.findViewById(R.id.azkar_fragment_background);
        txt_morning_layout = view.findViewById(R.id.txt_morning_layout);
        view_morning = view.findViewById(R.id.view_morning);
        txt_morning = view.findViewById(R.id.txt_morning);
        txt_night_layout = view.findViewById(R.id.txt_night_layout);
        view_night = view.findViewById(R.id.view_night);
        txt_night = view.findViewById(R.id.txt_night);
        txt_other_layout = view.findViewById(R.id.txt_other_layout);
        view_other = view.findViewById(R.id.view_other);
        txt_other = view.findViewById(R.id.txt_other);
        azkar_card_background = view.findViewById(R.id.azkar_card_background);
        txt_azkar_data = view.findViewById(R.id.txt_azkar_data);
        recycle_azkar_other = view.findViewById(R.id.recycle_azkar_other);
        btn_play_zkr_sound = view.findViewById(R.id.btn_play_zkr_sound);

        callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if ("other".equals(selectedFragment) && recycle_azkar_other.getVisibility() == View.GONE) {
                    txt_azkar_data.setVisibility(View.GONE);
                    txt_azkar_data.setText("");
                    recycle_azkar_other.setVisibility(View.VISIBLE);
                    btn_play_zkr_sound.setVisibility(View.GONE);

                } else {
                    if (getView() != null && isAdded()) {
                        getParentFragmentManager().popBackStack();
                    }
                }
                stopAudio();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        initComponenets();
        initStatuesBar();
        intiRecycleAzkarIndex();
        getData();
        return view;
    }

    private void getData() {
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(1,"أقول إذا أَصْبَحْتُ","- «اللَّهُمَّ بِكَ أَصْبَحْنَا، وَبِكَ أَمْسَيْنَا، وَبِكَ نَحْيَا، وَبِكَ نَمُوتُ, وَإِلَيْكَ النُّشُورُ»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(2,"أقول في كلِّ صَبَاحٍ وَمَسَاءٍ","- «بِسْمِ اللهِ الَّذِي لَا يَضُرُّ مَعَ اسْمِهِ شَيْءٌ فِي الأَرْضِ وَلَا فِي السَّمَاءِ، وَهُوَ السَّمِيعُ العَلِيمُ» 3مَرَّاتٍ.\n" +
                "و «الإِخْلَاصَ والمعَوِّذَتَيْن » 3مَرَّاتٍ.\n"));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(3,"أقول عندَ الاسْتيقاظِ مِنَ النَّومِ ","- «الحَمْدُ للهِ الَّذِي أَحْيَانَا بَعْدَمَا أَمَاتَنَا، وَإِلَيْهِ النُّشُورُ»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(4, "أقول قبلَ دخولِ الخَلاءِ","- «إِنِّي اللَّهُمَّ أَعُوذُ بِكَ مِنَ الـخُبُثِ وَالـخَبَائِثِ»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(5,"أقول بَعدَ الخروجِ من الخَلاءِ","- «غُفْرَانَكَ»، «الحَمْدُ للهِ الَّذِي أَذْهَبَ عَنِّي الْأَذَى وَعَافَانِي»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(6,"أقول إذا خرجْتُ من بَيتي ", "- «بِسْمِ اللهِ , تَوَكَّلْتُ عَلَى اللهِ ، لَا حَوْلَ وَلَا قُوَّةَ إِلَّا باللهِ »."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(7,"أقول في طَريقي إلى المسْجد ","- «اللَّهُمَّ إِنِّي أَسْأَلُكَ بِحَقِّ السَّائِلِينَ عَلَيْكَ، وَبِحَقِّ مَمْشَايَ, فَإِنِّي لَمْ أَخْرُجْ أَشَرًا وَلَا بَطَرًا وَلَا رِيَاءً وَلَا سُمْعَةً, خَرَجْتُ اتِّقَاءَ سَخَطِكَ وَابْتِغَاءَ مَرْضَاتِكَ, أَسْأَلُكَ أَنْ تُنْقِذَنِي مِنْ النَّارِ، وَأَنْ تَغْفِرَ لِي ذُنُوبِي، إِنَّهُ لَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(8,"أقول عندَ دُخُولِ المسجدِ ", "- «بِسْمِ اللهِ، وَالسَّلَامُ عَلَى رَسُولِ اللهِ، اللَّهُمَّ اغْفِرْ لِي ذُنُوبِي، وافْتَحْ لِي أَبْوَابَ رَحْمَتِكَ»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(9,"أقول عندَ الخُروجِ مِنَ المسجِد ", "- «بِسْمِ اللهِ، وَالسَّلَامُ عَلَى رَسُولِ اللهِ، اللَّهُمَّ اغْفِرْ لِي ذُنُوبِي، وافتح لي أبواب فَضْلِكَ»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(10,"أقول بَعدَ الأَذانِ ","- «اللَّهُمَّ رَبَّ هَذِهِ الدَّعْوَةِ التَّامَّةِ، وَالصَّلَاةِ القَائِمَةِ, آتِ سَيِّدَنا مُحَمَّدًا الوَسِيلَةَ وَالفَضِيلَةَ، وَابْعَثْهُ مَقَامًا مَحْمُودًا الَّذِي وَعَدْتَهُ»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(11,"أقول بَعدَ كلِّ صلاة","- «أَسْتَغْفِرُ اللهَ، أَسْتَغْفِرُ اللهَ، أَسْتَغْفِرُ اللهَ، اللَّهُمَّ أَنْتَ السَّلَامُ، وَمِنْكَ السَّلَامُ، تَبَارَكْتَ يَا ذَا الجَلَالِ وَالإِكْرَامِ»." +
                "\n" + "وأنْ أقولَ:\n" + "«اللَّهُمَّ أَعِنِّي على ذِكْرِكَ وَشُكْرِكَ وَحُسْنِ عِبادَتِكَ»."+ "\n" +
                "وأنْ أقولَ:\n" +
                "«سُبْحَانَ اللهِ (33 مرة)، الحَمْدُ للهِ (33 مرة)، اللهُ أكبرُ (33 مرة)، لَا إِلَـٰهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ المُلْكُ وَلَهُ الحَمْدُ، وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِير»." +"وأنْ أقرأَ:\n" +
                "«الإخْلاصَ وَالمُعَوِّذَتَيْنِ»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(12, "أقول قبلَ الطَّعامِ والشَّرابِ",
                "- «اللَّهُمَّ بَارِكْ لَنَا فِيمَا رَزَقْتَنَا، وَقِنَا عَذَابَ النَّارِ، بِسْمِ اللهِ»، وإنْ نَسيتُ التَّسميةَ أوَّله أقولُ: «بِسْمِ اللهِ أَوَّلَهُ وَآخِرَهُ»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(13,"أقول بَعدَ الطَّعامِ و الشَّرابِ ",
                "- «الْحَمْدُ للهِ الَّذِي أَطْعَمَنَا، وَسَقَانَا، وَجَعَلَنَا مُسْلِمِينَ»، «اللَّهُمَّ بَارِكْ لَنَا فِيهِ، وَأَطْعِمْنَا خَيْرًا مِنْهُ»، فإن كان لبنًا أقول: «اللَّهُمَّ بَارِكْ لَنَا فِيهِ وَزِدْنَا مِنْهُ»"));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(14,"أقول إذا عَطَسْتُ ","- «الـحَمْدُ للهِ». فيُجيبُني أخــي: «يَرْحَمُكَ اللهُ». فـأُجيبُـه: «يَهْدِيكُمُ اللهُ وَيُصْلِحُ بَالَكُمْ»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(15,"أقول قبلَ النَّوم ","- «بِاسْمِكَ اللهمّ أَمُوتُ وأَحْيَا».\n" +
                "أقول كُلَّما ذُكِرَ حَبِيبي المصطفَى صَلَّى اللهُ عليهِ وسَلَّم \n" +
                "- ( اللَّهُمَّ صَلِّ عَلَى سَيِّدِنا مُحَمَّدٍ وَعَلَى آلِه وصَحبِه وسلِّم ). أو  (صَلَّى اللهُ عَلَيهِ وَسَلَّم)."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(16,"أقول عندَ الأَلَم ","- «بِسْمِ اللهِ». ثلاثًا.\n" +
                "- «أَعُوذُ بِاللهِ وَقُدْرَتِهِ مِنْ شَرِّ مَا أَجِدُ وَأُحَاذِرُ» (7 مَرَّاتٍ)."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(17,"أقول إذا رَأيْتُ شيئًـا فأعْجَبَني ","- «مَا شَاءَ اللهُ، لَا قُوَّةَ إِلَّا بِاللهِ»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(18,"أقول عندَ الكَرْب ","- «لَا إِلَـٰهَ إِلَّا أَنْتَ، سُبْحَانَكَ، إِنِّي كُنْتُ مِنَ الظَّالِمِينَ»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(19,"أقول إذا رأيْتُ مُـبْـتَـلًى","- «الحَمْدُ للهِ الَّذِي عَافَانِي مِمَّا ابْتَلَاكَ بِهِ، وَفَضَّلَنِي عَلَى كَثِيرٍ مِمَّنْ خَلَقَ تَفْضِيلًا»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(20,"أقول إذا دخلتُ السُّوق ","- «لَا إِلَـٰهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ المُلْكُ وَلَهُ الحَمْدُ، يُحْيِي وَيُمِيتُ، وَهُوَ حَيٌّ لَا يَمُوتُ، بِيَدِهِ الخَيْرُ، وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ »."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(21,"أقول عندَ نُـزُولِ المطَرِ ","- «مُطِرْنَا بِفَضْلِ اللهِ وَرَحْمَتِهِ»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(22,"أقول للمريضِ عند عيادته ","- «أَسْأَلُ اللهَ العَظِيمَ، رَبَّ العَرْشِ العَظِيمِ، أَنْ يَشْفِيَكَ» (7 مَرَّاتٍ) ."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(23,"أقول إذا أَمْسَيْتُ ","- «اللَّهُمَّ بِكَ أَمْسَيْنَا، وبك أَصْبَحْنَا، وَبِكَ نَحْيَا، وَبِكَ نَمُوتُ، وَإِلَيْكَ المَصِيرُ»."));
        beAzkarIndexIteamList.add(new BeAzkarIndexIteam(24,"أقول قبلَ  النَّوْمِ ","- «بِاسْمِكَ رَبِّ وَضَعْتُ جَنْبِي، وَبِكَ أَرْفَعُهُ، إِنْ أَمْسَكْتَ نَفْـسِي فَارْحَمْهَا، وَإِنْ أَرْسَلْتَهَا فَاحْفَظْهَا بِمَا تَحْفَظُ بِهِ عِبَادَكَ الصَّالِحِينَ «.\n" +
                "وأنْ أقولَ: «اللهم قِنِي عَذَابَكَ يَوْمَ تَبْعَثُ عِبَادَكَ» (3 مَرَّاتٍ).\n" +
                "وأنْ أقرأَ: «آيةَ الكُرْسِيّ، والإخْلاصَ والمُعوِّذَتَينِ»."));
        adapterRecycleAzkarIndex.setAzkarIndexList(beAzkarIndexIteamList);
    }
    private void intiRecycleAzkarIndex(){

        beAzkarIndexIteamList = new ArrayList<>();
        adapterRecycleAzkarIndex= new AdapterRecycleAzkarIndex(getContext(),beAzkarIndexIteamList);
        recycle_azkar_other.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recycle_azkar_other.setHasFixedSize(true);
        recycle_azkar_other.setAdapter(adapterRecycleAzkarIndex);

        adapterRecycleAzkarIndex.setOnClickListener(new AdapterRecycleAzkarIndex.OnClickListener() {
            @Override
            public void OnSuraClickListener(BeAzkarIndexIteam beAzkarIndexIteam) {
                beAzkarIndex = beAzkarIndexIteam;
                recycle_azkar_other.setVisibility(View.GONE);
                txt_azkar_data.setVisibility(View.VISIBLE);
                btn_play_zkr_sound.setVisibility(View.VISIBLE);
                txt_azkar_data.setText(beAzkarIndexIteam.getTitle()+ ":" + "\n" +"\n" +beAzkarIndexIteam.getContent());

            }
        });


    }

    private void initStatuesBar()
    {
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.background_color));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void initComponenets() {
        Tools.displayImageDrawable(getContext(),azkar_card_background,R.drawable.azkar_background);
        Tools.displayImageDrawable(getContext(),azkar_fragment_background,R.drawable.background_pattern);
        Tools.displayImageDrawable(getContext(),img_amma_logo,R.drawable.amma_logo);

        btn_play_zkr_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (beAzkarIndex != null){
                    if (!audio_playing){
                        handlePlaySoundClick(beAzkarIndex.getId());
                    }else {
                        stopAudio();
                    }

                }
            }
        });

    txt_morning_layout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switchToFragment("morning");
        }
    });

        txt_night_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToFragment("night");

            }
        });

        txt_other_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToFragment("other");

            }
        });

        txt_morning_layout.callOnClick();

    }

    private void switchToFragment(String fragmentName) {
        if (!selectedFragment.equals(fragmentName)) {
            selectedFragment = fragmentName;

            if (fragmentName.equals("morning")){
                stopAudio();
                txt_morning_layout.setSelected(true);
                txt_night_layout.setSelected(false);
                txt_other_layout.setSelected(false);
                view_morning.setVisibility(View.VISIBLE);
                view_night.setVisibility(View.GONE);
                view_other.setVisibility(View.GONE);
                btn_play_zkr_sound.setVisibility(View.GONE);
                txt_morning.setTextColor(getContext().getResources().getColor(R.color.white));
                txt_night.setTextColor(getContext().getResources().getColor(R.color.black));
                txt_other.setTextColor(getContext().getResources().getColor(R.color.black));
                txt_azkar_data.setVisibility(View.VISIBLE);
                recycle_azkar_other.setVisibility(View.GONE);
                txt_azkar_data.setText(
                        "- أَصْبَحْنا وَأَصْبَحَ المُلْكُ لله وَالحَمدُ لله، لا إلهَ إلاّ اللّهُ وَحدَهُ لا شَريكَ لهُ، لهُ المُلكُ ولهُ الحَمْد، وهُوَ على كلّ شَيءٍ قدير، رَبِّ أسْأَلُكَ خَيرَ ما في هذا اليوم وَخَيرَ ما بَعْدَه، وَأَعوذُ بِكَ مِنْ شرِّ ما في هذا اليوم وَشَرِّ ما بَعْدَه، رَبِّ أَعوذُ بِكَ مِنَ الْكَسَلِ وَسوءِ الْكِبَر، رَبِّ أَعوذُ بِكَ مِنْ عَذابٍ في النّارِ وَعَذابٍ في القَبْر.\n" +
                        "\n" +
                        "- اللّهُمَّ بِكَ أَصْبَحْنا وَبِكَ أَمْسَينا، وَبِكَ نَحْيا وَبِكَ نَمُوتُ وَإِلَيْكَ النُّشُور.\n" +
                        "\n" +
                        "- أَصْبَحْنا عَلَى فِطْرَةِ الإسْلاَمِ، وَعَلَى كَلِمَةِ الإِخْلاَصِ، وَعَلَى دِينِ نَبِيِّنَا مُحَمَّدٍ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ، وَعَلَى مِلَّةِ أَبِينَا إبْرَاهِيمَ حَنِيفاً مُسْلِماً وَمَا كَانَ مِنَ المُشْرِكِينَ.\n" +
                        "\n" +
                        "- اللّهُمَّ إِنِّي أَصْبَحْتُ أُشْهِدُك، وَأُشْهِدُ حَمَلَةَ عَرْشِك، وَمَلَائِكَتَكَ، وَجَميعَ خَلْقِك، أَنَّكَ أَنْتَ اللهُ لا إلهَ إلاّ أَنْتَ وَحْدَكَ لا شَريكَ لَك، وَأَنَّ مُحَمّداً عَبْدُكَ وَرَسولُك.   (4 مرات)\n" +
                        "\n" +
                        "- آية الكرسي.\n" +
                        "\n" +
                        "- سورة الإخلاص، سورة الفلق، والناس.    (3 مرات)\n" +
                        "\n" +
                        "- اللّهمَّ أَنْتَ رَبِّي لا إلهَ إلاّ أَنْتَ، خَلَقْتَني وَأَنا عَبْدُك، وَأَنا عَلى عَهْدِكَ وَوَعْدِكَ ما اسْتَطَعْت، أَعوذُبِكَ مِنْ شَرِّ ما صَنَعْت، أَبوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ وَأَبوءُ بِذَنْبي فَاغْفِرْ لي فَإِنَّهُ لا يَغْفِرُ الذُّنوبَ إِلاّ أَنْتَ.\t\n" +
                        "\n" +
                        "- رَضيتُ بِاللهِ رَبَّاً وَبِالإسْلامِ ديناً وَبِمُحَمَّدٍ صلى الله عليه وسلم نَبِيّاً.   (3 مرات)\n" +
                        "\n" +
                        "- بِسمِ اللهِ الذي لا يَضُرُّ مَعَ اسمِهِ شَيءٌ في الأرْضِ وَلا في السّماءِ وَهوَ السّميعُ العَليم.   (3 مرات)\n" +
                        "\n" +
                        "- بِسمِ اللهِ الذي لا يَضُرُّ مَعَ اسمِهِ شَيءٌ في الأرْضِ وَلا في السّماءِ وَهوَ السّميعُ العَليم.  (3 مرات)\n" +
                        "\n" +
                        "- أسْتَغْفِرُ اللهَ وَأتُوبُ إلَيْهِ.   (100 مرة)");



            }else if (fragmentName.equals("night")){
                stopAudio();
                txt_morning_layout.setSelected(false);
                txt_night_layout.setSelected(true);
                txt_other_layout.setSelected(false);
                view_morning.setVisibility(View.GONE);
                view_night.setVisibility(View.VISIBLE);
                view_other.setVisibility(View.GONE);
                btn_play_zkr_sound.setVisibility(View.GONE);
                txt_morning.setTextColor(getContext().getResources().getColor(R.color.black));
                txt_night.setTextColor(getContext().getResources().getColor(R.color.white));
                txt_other.setTextColor(getContext().getResources().getColor(R.color.black));
                txt_azkar_data.setVisibility(View.VISIBLE);
                recycle_azkar_other.setVisibility(View.GONE);
                txt_azkar_data.setText(
                        "- أَمْسَيْنا وَأَمْسى الملكُ لله وَالحَمدُ لله، لا إلهَ إلاّ اللّهُ وَحدَهُ لا شَريكَ لهُ، لهُ المُلكُ ولهُ الحَمْد، وهُوَ على كلّ شَيءٍ قدير، رَبِّ أسْأَلُكَ خَيرَ ما في هذهِ اللَّيْلَةِ وَخَيرَ ما بَعْدَها، وَأَعوذُ بِكَ مِنْ شَرِّ ما في هذهِ اللَّيْلةِ وَشَرِّ ما بَعْدَها، رَبِّ أَعوذُ بِكَ مِنَ الْكَسَلِ وَسوءِ الْكِبَر، رَبِّ أَعوذُ بِكَ مِنْ عَذابٍ في النّارِ وَعَذابٍ في القَبْر.\n" +
                        "\n" +
                        "- اللّهُمَّ ما أَمسى بي مِنْ نِعْمَةٍ أَو بِأَحَدٍ مِنْ خَلْقِك، فَمِنْكَ وَحْدَكَ لا شريكَ لَك، فَلَكَ الْحَمْدُ وَلَكَ الشُّكْر.\n" +
                        "\n" +
                        "- أَمْسَيْنَا عَلَى فِطْرَةِ الإسْلاَمِ، وَعَلَى كَلِمَةِ الإِخْلاَصِ، وَعَلَى دِينِ نَبِيِّنَا مُحَمَّدٍ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ، وَعَلَى مِلَّةِ أَبِينَا إبْرَاهِيمَ حَنِيفاً مُسْلِماً وَمَا كَانَ مِنَ المُشْرِكِينَ\t.\n" +
                        "\n" +
                        "- اللّهُمَّ إِنِّي أَمسيتُ أُشْهِدُك، وَأُشْهِدُ حَمَلَةَ عَرْشِك، وَمَلَائِكَتَكَ، وَجَميعَ خَلْقِك، أَنَّكَ أَنْتَ اللهُ لا إلهَ إلاّ أَنْتَ وَحْدَكَ لا شَريكَ لَك، وَأَنَّ مُحَمّداً عَبْدُكَ وَرَسولُك.   (4 مرات)\n" +
                        "\n" +
                        "- (آمَنَ الرَّسُولُ بِمَا أُنزِلَ إِلَيْهِ مِن رَّبِّهِ وَالْمُؤْمِنُونَ كُلٌّ آمَنَ بِاللّهِ وَمَلآئِكَتِهِ وَكُتُبِهِ وَرُسُلِهِ لاَ نُفَرِّقُ بَيْنَ أَحَدٍ مِّن رُّسُلِهِ وَقَالُواْ سَمِعْنَا وَأَطَعْنَا غُفْرَانَكَ رَبَّنَا وَإِلَيْكَ الْمَصِيرُ ، لَا يُكَلِّفُ اللَّهُ نَفْسًا إِلَّا وُسْعَهَا لَهَا مَا كَسَبَتْ وَعَلَيْهَا مَا اكْتَسَبَتْ رَبَّنَا لَا تُؤَاخِذْنَا إِنْ نَسِينَا أَوْ أَخْطَأْنَا رَبَّنَا وَلَا تَحْمِلْ عَلَيْنَا إِصْرًا كَمَا حَمَلْتَهُ عَلَى الَّذِينَ مِنْ قَبْلِنَا رَبَّنَا وَلَا تُحَمِّلْنَا مَا لَا طَاقَةَ لَنَا بِهِ وَاعْفُ عَنَّا وَاغْفِرْ لَنَا وَارْحَمْنَا أَنْتَ مَوْلَانَا فَانْصُرْنَا عَلَى الْقَوْمِ الْكَافِرِينَ).\n" +
                        "\n" +
                        "- آية الكرسي.\t\n" +
                        "\n" +
                        "- سورة الإخلاص، سورة الفلق، والناس.   (3 مرات)\n" +
                        "\n" +
                        "- اللّهمَّ أَنْتَ رَبِّي لا إلهَ إلاّ أَنْتَ، خَلَقْتَني وَأَنا عَبْدُك، وَأَنا عَلى عَهْدِكَ وَوَعْدِكَ ما اسْتَطَعْت، أَعوذُبِكَ مِنْ شَرِّ ما صَنَعْت، أَبوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ وَأَبوءُ بِذَنْبي فَاغْفِرْ لي فَإِنَّهُ لا يَغْفِرُ الذُّنوبَ إِلاّ أَنْتَ.\n" +
                        "\n" +
                        "- رَضيتُ بِاللهِ رَبَّاً وَبِالإسْلامِ ديناً وَبِمُحَمَّدٍ صلى الله عليه وسلم نَبِيّاً.   (3 مرات)\n" +
                        "\n" +
                        "- بِسمِ اللهِ الذي لا يَضُرُّ مَعَ اسمِهِ شَيءٌ في الأرْضِ وَلا في السّماءِ وَهوَ السّميعُ العَليم.\n" +
                        "\n" +
                        "- بِسمِ اللهِ الذي لا يَضُرُّ مَعَ اسمِهِ شَيءٌ في الأرْضِ وَلا في السّماءِ وَهوَ السّميعُ العَليم.   (3 مرات)\n" +
                        "\n" +
                        "- أسْتَغْفِرُ اللهَ وَأتُوبُ إلَيْهِ   (100 مرة)");


            }else {
                txt_morning_layout.setSelected(false);
                txt_night_layout.setSelected(false);
                txt_other_layout.setSelected(true);
                view_morning.setVisibility(View.GONE);
                view_night.setVisibility(View.GONE);
                view_other.setVisibility(View.VISIBLE);
                txt_morning.setTextColor(getContext().getResources().getColor(R.color.black));
                txt_night.setTextColor(getContext().getResources().getColor(R.color.black));
                txt_other.setTextColor(getContext().getResources().getColor(R.color.white));
                txt_azkar_data.setVisibility(View.GONE);
                recycle_azkar_other.setVisibility(View.VISIBLE);
                txt_azkar_data.setText("");
            }
        }
    }

    private void handlePlaySoundClick(int zikr_number) {
        String zikr;
        if (zikr_number <10){
            zikr = "zekr" + "_0" + zikr_number;
        }else {
            zikr = "zekr" + "_" + zikr_number;
        }

        int audioFile = Tools.getAudioFileByTitle(getContext(), zikr);

        if (audioFile != -1) {

            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }

            try {

                mediaPlayer = MediaPlayer.create(getContext(), audioFile);
                mediaPlayer.setOnCompletionListener(mp -> {
                    Tools.displayImageDrawable(getContext(),btn_play_zkr_sound,R.drawable.sound_icon);
                    audio_playing = false;
                    mediaPlayer.release();
                    mediaPlayer = null;
                });
                mediaPlayer.start();
                Tools.displayImageDrawable(getContext(),btn_play_zkr_sound,R.drawable.stop_voice_icon);
                audio_playing = true;

            } catch (Exception e) {
                Log.e("AudioUtils", "Error while playing audio: " + e.getMessage());
            }
        } else {

            Log.e("AudioUtils", "Audio file not found for: " + zikr);
        }
    }

    private void stopAudio(){
        audio_playing = false;
        Tools.displayImageDrawable(getContext(),btn_play_zkr_sound,R.drawable.sound_icon);
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (callback != null) {
            callback.setEnabled(false);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        stopAudio();
    }
}

