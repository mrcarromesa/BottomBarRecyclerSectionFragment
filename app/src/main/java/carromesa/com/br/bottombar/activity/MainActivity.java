package carromesa.com.br.bottombar.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import carromesa.com.br.bottombar.helper.BottomNavigationViewHelper;
import carromesa.com.br.bottombar.fragment.CardapioFragment;
import carromesa.com.br.bottombar.fragment.PedidosFragment;
import carromesa.com.br.bottombar.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private AppBarLayout appBarLayout;

    private FragmentManager fm = getSupportFragmentManager();
    private FragmentTransaction ft = fm.beginTransaction();

    private CardapioFragment cardapioFragment;
    private PedidosFragment pedidosFragment;

    private static final String TAG = "fragmentMain";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            appBarLayout.setExpanded(true,true);

            switch (item.getItemId()) {
                case R.id.navigation_car:

                    /*CardapioFragment fragment = (CardapioFragment) fm.findFragmentByTag(TAG);

                    if (fragment != null){
                        ft.remove(fragment);
                    }*/

                    ft = fm.beginTransaction();
                    if (cardapioFragment != null){
                        ft.remove(cardapioFragment);
                        cardapioFragment = null;
                    }

                    cardapioFragment = new CardapioFragment();


                    ft.replace(R.id.content, cardapioFragment, TAG);
                    ft.commit();


                    mTextMessage.setText("Car");
                    return true;
                case R.id.navigation_home:

                    /*PedidosFragment fragmentPedido = (PedidosFragment) fm.findFragmentByTag(TAG);

                    if (fragmentPedido != null) {
                        ft.remove(fragmentPedido);
                    }*/

                    ft = fm.beginTransaction();

                    if (pedidosFragment != null){
                        ft.remove(pedidosFragment);
                    }

                    pedidosFragment = new PedidosFragment();


                    ft.replace(R.id.content, pedidosFragment, TAG);
                    ft.commit();

                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_car);

        BottomNavigationViewHelper.disableShiftMode(navigation);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        getSupportFragmentManager();
    }



}
