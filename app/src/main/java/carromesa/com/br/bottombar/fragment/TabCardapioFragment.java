package carromesa.com.br.bottombar.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import carromesa.com.br.bottombar.R;
import carromesa.com.br.bottombar.activity.ItemActivity;
import carromesa.com.br.bottombar.model.Grupos;
import carromesa.com.br.bottombar.model.Produtos;
import carromesa.com.br.bottombar.stickyheader.StickyHeaderLayoutManager;
import carromesa.com.br.bottombar.stickyheader.adapter.MultiTypeDemoAdapter;


public class TabCardapioFragment extends Fragment implements SearchView.OnQueryTextListener {

    private String tipo;

    MultiTypeDemoAdapter adapter;
    RecyclerView recyclerView;

    SearchView searchView;

    List<Grupos> gruposFilter = new ArrayList<>();

    private List<Grupos> grupos;

    public TabCardapioFragment() {
        // Required empty public constructor
    }


    public static TabCardapioFragment newInstance(String tipo) {
        TabCardapioFragment fragment = new TabCardapioFragment();
        Bundle args = new Bundle();
        args.putString("tipo",tipo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.tipo = getArguments().getString("tipo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_cardapio, container, false);

        searchView = (SearchView) view.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StickyHeaderLayoutManager());

        //recyclerView.setAdapter(new MultiTypeDemoAdapter(0,0,onClickItem()));

        setProdutos();

        return view;
    }

    private void setProdutos(){
        grupos = new ArrayList<>();

        for (int i = 0; i < 20; i++){
            List<Produtos> produtos = new ArrayList<>();
            for (int j = 0; j < 20; j++){
                Produtos produto = new Produtos();
                produto.id = j;
                produto.nome = "Produto - "+i+" "+j;
                produtos.add(produto);
            }

            Grupos grupo = new Grupos(i,"Grupo - "+i,produtos);
            grupos.add(grupo);
        }

        adapter = new MultiTypeDemoAdapter(getContext(), grupos,onClickItem());
        recyclerView.setAdapter(adapter);
    }




    private MultiTypeDemoAdapter.ItemOnClickListener onClickItem() {

        return new MultiTypeDemoAdapter.ItemOnClickListener() {

            @Override
            public void onClickItem(View view, int idSection, int idItem) {

                Produtos produto = grupos.get(idSection).getProduto(idItem);

                if (gruposFilter.size() > 0){
                    produto = gruposFilter.get(idSection).getProduto(idItem);
                }

                Intent intent = new Intent(getContext(), ItemActivity.class);
                intent.putExtra("item", Parcels.wrap(produto));
                startActivity(intent);



                Toast.makeText(getContext(), produto.nome, Toast.LENGTH_SHORT).show();
            }
        };
    }

    private List<Grupos> filter(String busca){
        /*
        *
        * query = query.toLowerCase();final List<CountryModel> filteredModelList = new ArrayList<>();
        for (CountryModel model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
        * */


        busca = busca.toLowerCase();
        gruposFilter = new ArrayList<>();

        for (Grupos grupo : grupos) {

            List<Produtos> produtos = grupo.getProdutos();

            boolean encontrou = false;

            for (Produtos produto : produtos){
                String text = produto.nome.toLowerCase();

                if (text.contains(busca)){
                    encontrou = true;
                }
            }

            if (encontrou) {
                gruposFilter.add(grupo);
            }
        }

        return gruposFilter;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Grupos> gruposFilterAll = this.filter(newText);
        Log.d("SIZE", String.valueOf(gruposFilterAll.size()));
        //adapter.setFilter(gruposFilter);
        adapter = new MultiTypeDemoAdapter(getContext(),gruposFilterAll,onClickItem());
        recyclerView.setAdapter(adapter);
        return false;
    }
}
