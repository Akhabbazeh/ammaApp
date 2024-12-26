package com.zidnyscience.ammaApp.feature.about_us_feature;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.utils.Tools;


public class AboutUsFragment extends Fragment
{
    private View view;
    private ImageView img_bsem_allah;
    private ImageView img_amma_logo;
    private ImageView about_us_fragment_background;
    private TextView txt_about_us_data;


    public AboutUsFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.about_us_fragment, container, false);
        about_us_fragment_background = view.findViewById(R.id.about_us_fragment_background);
        img_amma_logo = view.findViewById(R.id.img_amma_logo);
        img_bsem_allah = view.findViewById(R.id.img_bsem_allah);
        txt_about_us_data = view.findViewById(R.id.txt_about_us_data);

        initStatuesBar();
        intiComponents();
        return view;
    }

    private void intiComponents() {
        Tools.displayImageDrawable(getContext(),about_us_fragment_background,R.drawable.background_pattern);
        Tools.displayImageDrawable(getContext(),img_amma_logo,R.drawable.amma_logo);
        Tools.displayImageDrawable(getContext(),img_bsem_allah,R.drawable.basmalah_naskh);

        txt_about_us_data.setText("هذا التطبيق \n" +
                "- لتعلُّم الجزء الثلاثين من القرآن الكريم (جزء عمَّ).\n" +
                "- إنَّهُ المرحلة الأولى للدُّخول إلى القرآن بتلاوة صحيحة مع تدبُّرٍ وتعلُّمٍ لبعض الفوائد التربويَّة. \n" +
                "- حوى مفردات جزء عمَّ من القرآن الكريم (شرح الشيخ حسنين مخلوف).\n" +
                "- حوى التطبيق تلاوة تعليمية بصوت الشيخ د.أيمن سويد.\n" +
                "- بالإضافة لتلاوة الشيخ محمد صدّيق المنشاوي لكامل المصحف بطريقة الترديد ( يحتاج اتصال انترنت).\n" +
                "- مع ملحق فيما يلزم طالب العلم من الأذكار الضرورية وأناشيد هادفة حول القرآن والحبيب المصطفى صلى الله عليه وسلم.\n" +
                "- هذا التطبيق هو ضمن ( سلسلة زدني علمًا وخلقًا ). \n" +
                "- اختبر نفسك وتعلّم من خلال ذلك .\n" +
                "- التطبيق سهل الاستخدام من خلال واجهات يسيرة ومبسطة.\n" +
                "- هذا التطبيق هو رديفٌ للمعلم المربِّي وليس بديلًا عنه، يختصر لك زمن السَّنوات في أيام معدودات .  \n" +
                "اضغط للتَّعرُّف على إصداراتنا الإلكترونيَّة والمطبوعة");
    }

    private void initStatuesBar()
    {
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.background_color));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }



}

