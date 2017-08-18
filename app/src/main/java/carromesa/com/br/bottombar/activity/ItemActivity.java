package carromesa.com.br.bottombar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import carromesa.com.br.bottombar.R;
import carromesa.com.br.bottombar.model.Produtos;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Produtos produto = Parcels.unwrap(getIntent().getParcelableExtra("item"));

        Log.d("Produto","aqui"+produto.url);

        ImageView imageView = (ImageView) findViewById(R.id.imgItem);
        Picasso.with(this).load(produto.url).into(imageView);
    }
}
