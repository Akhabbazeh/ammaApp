package com.zidnyscience.ammaApp.feature.educational_advantages_feature;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.ammaApp.feature.npages_feature.nPagesFragment;
import com.zidnyscience.ammaApp.feature.word_meaning_feature.AdapterRecycleWordMeaning;
import com.zidnyscience.ammaApp.feature.word_meaning_feature.AdapterSpinnerSwarTopic;
import com.zidnyscience.model.BeEducationalAdvantagesItem;
import com.zidnyscience.model.BeSurahWordsMeaningItem;
import com.zidnyscience.model.EducationalAdvantages;
import com.zidnyscience.model.WordMeaning;
import com.zidnyscience.utils.Tools;
import com.zidnyscience.utils.WordMeaningLoader;

import java.util.ArrayList;
import java.util.List;


public class EducationalAdvantagesFragment extends Fragment
{
    private View view;
    private ImageView img_amma_logo;
    private ImageView word_meaning_fragment_background;
    private Spinner spinnerSwar;
    private RecyclerView recycle_educational_advantages;
    private List<EducationalAdvantages> educationalAdvantagesList;
    private AdapterRecycleEducationalAdvantages adapterRecycleEducationalAdvantages;
    private AdapterSpinnerSwarTopic adapterSpinnerSwarTopic;
    private List<String> swarList;
    private List<BeEducationalAdvantagesItem> beEducationalAdvantagesItems = new ArrayList<>();


    public EducationalAdvantagesFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.educational_advantages_fragment, container, false);
        img_amma_logo = view.findViewById(R.id.img_amma_logo);
        word_meaning_fragment_background = view.findViewById(R.id.word_meaning_fragment_background);
        spinnerSwar = view.findViewById(R.id.spinnerSwar);
        recycle_educational_advantages = view.findViewById(R.id.recycle_educational_advantages);



        initStatuesBar();
        initComponenets();
        initChooseSwarSpinner();
        intiRecycleEducationalAdvantages();
        getData();
        return view;
    }

    private void getData() {
        List<EducationalAdvantages> educationalAdvantages_78 = new ArrayList<>();
        educationalAdvantages_78.add(new EducationalAdvantages(0,"س 1 : متى تكون البسملة؟","أول التلاوة ،,بداية الطعام والشراب."));
        educationalAdvantages_78.add(new EducationalAdvantages(0,"س 2 : ما هي أعظم سورة في القرآن؟","سورة الفاتحة"));
        educationalAdvantages_78.add(new EducationalAdvantages(0,"س 3 : ما هي السورة التي اشتغلت على عدة مواضيع في القرآن ( أصول الإيمان ، العبادة ، القصص ، اليوم الآخر ، الصراط المستقيم )؟","سورة الفاتحة"));
        educationalAdvantages_78.add(new EducationalAdvantages(0,"س 4 : يوم القيامة له أسماء منها؟","يوم الدين – يوم الحساب – يوم الجزاء ....."));
        educationalAdvantages_78.add(new EducationalAdvantages(0,"س 5 : ما الفرق بين ( نعبدك ) و ( إياك نعبد )؟"," نعبدك : تحتمل أن نعبد غيرك معك ." + "\n" +
               " •  إياك نعبد : نخصك وحدك بالعبادة دون غيرك."));
        educationalAdvantages_78.add(new EducationalAdvantages(0,"س 6 : ما أهم دعاء نطلبه من الله تعالى؟","اهدنا الصِّراط المستقيم ."));
        educationalAdvantages_78.add(new EducationalAdvantages(0,"س 7 : هل يمكن أن تستقيم من غير قدوة وما الدليل؟","لا ، (صراط الذين أنعمت عليهم) ."));
        educationalAdvantages_78.add(new EducationalAdvantages(0,"س 8 : اذكر سبعة من أسماء الله الحسنى؟","الله ، الرحمن ، الرحيم ، الملك ، القدوس ، السلام ،القادر ."));
        educationalAdvantages_78.add(new EducationalAdvantages(0,"س 9 : هل يمكنك الاستعانة بغير الله؟","الاستعانة الحقيقية هي من الله وحدها ، وحينما أطلب من أحد أن يعينني فهذا سبب فقط والله هو الذي يسخر لي من يعينني."));
        educationalAdvantages_78.add(new EducationalAdvantages(0,"س 10 :هل الأفضل أن تنعزل عن الناس في عبادتك لأم أن تكون مع الجماعة وما الدليل؟","صلاة الجماعة أفضل " + "\n" +
                "والدليل ( نعبد – نستعين ) بدل ( أعبد – أستعين ) ."));
                educationalAdvantages_78.add(new EducationalAdvantages(0,"س 11 : هل تدعو لنفسك فقط أم يشمل دعاؤك الآخر وما الدليل ؟","الدعاء للناس جميعا أفضل ، والدليل ( اهدنا ) بدل ( اهدني )"));
        educationalAdvantages_78.add(new EducationalAdvantages(0,"س 12 : ماذا نستفيد من قوله تعالى (غير المغضوب عليهم ولا الضالين) ؟","أن أبتعدَ عن طريق المنحرفين عن الصراط المستقيم ."));
        beEducationalAdvantagesItems.add(new BeEducationalAdvantagesItem(0,educationalAdvantages_78));
     //   adapterRecycleEducationalAdvantages.setWordMeaningList(educationalAdvantagesList);

    }

    private void initComponenets() {
        Tools.displayImageDrawable(getContext(),img_amma_logo,R.drawable.amma_logo);
        Tools.displayImageDrawable(getContext(),word_meaning_fragment_background,R.drawable.background_pattern);
    }

    private void initStatuesBar()
    {
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.background_color));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void initChooseSwarSpinner()
    {

        swarList = new ArrayList<>();
        swarList.add("سورة الفاتحة");
        swarList.add("سورة النبأ");
        swarList.add("سورة النازعات");
        swarList.add("سورة عبس");
        swarList.add("سورة التكوير");
        swarList.add("سورة الانفطار");
        swarList.add("سورة المطففين");
        swarList.add("سورة الانشقاق");
        swarList.add("سورة البروج");
        swarList.add("سورة الطارق");
        swarList.add("سورة الأعلى");
        swarList.add("سورة الغاشية");
        swarList.add("سورة الفجر");
        swarList.add("سورة البلد");
        swarList.add("سورة الشمس");
        swarList.add("سورة الليل");
        swarList.add("سورة الضحى");
        swarList.add("سورة الشرح");
        swarList.add("سورة التين");
        swarList.add("سورة العلق");
        swarList.add("سورة القدر");
        swarList.add("سورة البينة");
        swarList.add("سورة الزلزلة");
        swarList.add("سورة العاديات");
        swarList.add("سورة القارعة");
        swarList.add("سورة التكاثر");
        swarList.add("سورة العصر");
        swarList.add("سورة الهمزة");
        swarList.add("سورة الفيل");
        swarList.add("سورة قريش");
        swarList.add("سورة الماعون");
        swarList.add("سورة الكوثر");
        swarList.add("سورة الكافرون");
        swarList.add("سورة النصر");
        swarList.add("سورة المسد");
        swarList.add("سورة الإخلاص");
        swarList.add("سورة الفلق");
        swarList.add("سورة الناس");

        adapterSpinnerSwarTopic = new AdapterSpinnerSwarTopic(getContext(), swarList);

        spinnerSwar.setAdapter(adapterSpinnerSwarTopic);

        spinnerSwar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recycle_educational_advantages.smoothScrollToPosition(0);
                if (position == 0){
                    adapterRecycleEducationalAdvantages.setWordMeaningList(beEducationalAdvantagesItems.get(position).getEducationalAdvantagesList());
                }else {
                    adapterRecycleEducationalAdvantages.setWordMeaningList(new ArrayList<>());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void intiRecycleEducationalAdvantages(){
        educationalAdvantagesList = new ArrayList<>();
        adapterRecycleEducationalAdvantages= new AdapterRecycleEducationalAdvantages(getContext(),educationalAdvantagesList);
        recycle_educational_advantages.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recycle_educational_advantages.setHasFixedSize(true);
        recycle_educational_advantages.setAdapter(adapterRecycleEducationalAdvantages);

    }


}

