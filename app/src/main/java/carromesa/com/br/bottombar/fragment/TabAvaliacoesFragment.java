package carromesa.com.br.bottombar.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import carromesa.com.br.bottombar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabAvaliacoesFragment extends Fragment {

    private String tipo;

    public TabAvaliacoesFragment() {
        // Required empty public constructor
    }

    /*
    * public static TabCardapioFragment newInstance(String tipo) {
        TabCardapioFragment fragment = new TabCardapioFragment();
        Bundle args = new Bundle();
        args.putString("tipo",tipo);
        fragment.setArguments(args);
        return fragment;
    }
    * */

    public static TabAvaliacoesFragment newInstance(String tipo) {
        TabAvaliacoesFragment fragment = new TabAvaliacoesFragment();
        Bundle args = new Bundle();
        args.putString("tipo", tipo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            this.tipo = getArguments().getString("tipo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_avaliacoes, container, false);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(this.tipo);

        return view;
    }

}
