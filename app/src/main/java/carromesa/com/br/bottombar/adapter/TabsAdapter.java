package carromesa.com.br.bottombar.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import carromesa.com.br.bottombar.R;
import carromesa.com.br.bottombar.fragment.TabAvaliacoesFragment;
import carromesa.com.br.bottombar.fragment.TabCardapioFragment;

/**
 * Created by carlosrodolfosantos on 03/08/17.
 */

public class TabsAdapter extends FragmentPagerAdapter {
    private final Context context;

    public TabsAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        if (position == 0){
            fragment = TabCardapioFragment.newInstance("Cadápio");
        } else if (position == 1){
            fragment = TabAvaliacoesFragment.newInstance("Avaliação");
        } else {
            fragment = TabAvaliacoesFragment.newInstance("Sobre");
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0){
            return context.getString(R.string.tabCardapio);
        } else if (position == 1) {
            return context.getString(R.string.tabAvaliacoes);
        }

        return context.getString(R.string.tabSobre);

    }
}
