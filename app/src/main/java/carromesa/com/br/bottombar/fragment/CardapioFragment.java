package carromesa.com.br.bottombar.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import carromesa.com.br.bottombar.R;
import carromesa.com.br.bottombar.adapter.TabsAdapter;


public class CardapioFragment extends Fragment {


    public CardapioFragment() {
        // Required empty public constructor
    }


    public static CardapioFragment newInstance(String param1, String param2) {
        CardapioFragment fragment = new CardapioFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cardapio, container, false);

        setupViewPagerTabs(view);

        return view;
    }

    private void setupViewPagerTabs(View view) {
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabsAdapter(getContext(), this.getChildFragmentManager()));

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        int cor = ContextCompat.getColor(getContext(),R.color.white);
        tabLayout.setTabTextColors(cor,cor);
        tabLayout.setSelectedTabIndicatorColor(cor);
    }
}
