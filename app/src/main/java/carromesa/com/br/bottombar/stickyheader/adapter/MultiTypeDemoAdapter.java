package carromesa.com.br.bottombar.stickyheader.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import carromesa.com.br.bottombar.R;
import carromesa.com.br.bottombar.model.Grupos;
import carromesa.com.br.bottombar.model.Produtos;

/**
 * Created by carlosrodolfosantos on 13/07/17.
 */

public class MultiTypeDemoAdapter extends SectioningAdapter {

    static final String TAG = MultiTypeDemoAdapter.class.getSimpleName();

    static final int USER_HEADER_TYPE_0 = 0;
    static final int USER_HEADER_TYPE_1 = 1;

    static final int USER_ITEM_TYPE_0 = 0;
    static final int USER_ITEM_TYPE_1 = 1;

    static final int USER_FOOTER_TYPE_0 = 0;
    static final int USER_FOOTER_TYPE_1 = 1;

    class Item {
        int type;
        String title;
        String url;

        public Item(int type, String title) {
            this.type = type;
            this.title = title;
        }

        public Item(int type, String title, String url){
            this.type = type;
            this.title = title;
            this.url = url;
        }

        public int getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() { return url; }
    }

    class Footer {
        int type;
        String title;

        public Footer(int type, String title) {
            this.type = type;
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }
    }

    class Section {
        int type;
        String title;
        ArrayList<Item> items = new ArrayList<>();
        //Footer footer;

        public Section(int type, String title) {
            this.type = type;
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }

        public ArrayList<Item> getItems() {
            return items;
        }

        /*public Footer getFooter() {
            return footer;
        }*/
    }

    ///////////////

    public class ItemViewHolder0 extends SectioningAdapter.ItemViewHolder {
        TextView textView;
        ImageView imageView;

        public ItemViewHolder0(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageItem);
        }
    }

    public class HeaderViewHolder0 extends SectioningAdapter.HeaderViewHolder {
        TextView textView;

        public HeaderViewHolder0(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public class FooterViewHolder0 extends SectioningAdapter.FooterViewHolder {
        TextView textView;

        public FooterViewHolder0(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    ///////////////

    public class ItemViewHolder1 extends SectioningAdapter.ItemViewHolder {
        TextView textView;

        public ItemViewHolder1(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public class HeaderViewHolder1 extends SectioningAdapter.HeaderViewHolder {
        TextView textView;

        public HeaderViewHolder1(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public class FooterViewHolder1 extends SectioningAdapter.FooterViewHolder {
        TextView textView;

        public FooterViewHolder1(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }


    Random rng;
    ArrayList<Section> sections;

    private final ItemOnClickListener itemOnClickListener;

    public MultiTypeDemoAdapter(int numSections, int numItemsPerSection, ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;

        rng = new Random();
        sections = new ArrayList<>();
        for (int s = 0; s < numSections; s++) {
            //int sectionType = s % 2;
            int sectionType = 0;
            Section section = new Section(sectionType, "Section: " + Integer.toString(s));
            for (int i = 0; i < numItemsPerSection; i++) {
                section.items.add(new Item(sectionType, "Item: " + Integer.toString(i)));
            }
            //section.footer = new Footer(sectionType, "Footer for section: " + s);
            sections.add(section);
        }
    }

    Context context;

    public MultiTypeDemoAdapter(Context context, List<Grupos> grupos, ItemOnClickListener itemOnClickListener){
        this.context = context;
        this.itemOnClickListener = itemOnClickListener;
        sections = new ArrayList<>();

        for (int s = 0; s < grupos.size(); s++){
            int sectionType = 0;
            Section section = new Section(sectionType, grupos.get(s).getNome());

            List<Produtos> produtos = grupos.get(s).getProdutos();

            if (produtos.size() > 0){
                for (int i = 0; i < produtos.size(); i++){
                    section.items.add(new Item(sectionType,produtos.get(i).nome,produtos.get(i).url));
                }
            }

            sections.add(section);
        }
    }

    public void setFilter(List<Grupos> grupos){

        sections = new ArrayList<>();

        for (int s = 0; s < grupos.size(); s++){
            int sectionType = 0;
            Section section = new Section(sectionType, grupos.get(s).getNome());

            List<Produtos> produtos = grupos.get(s).getProdutos();

            if (produtos.size() > 0){
                for (int i = 0; i < produtos.size(); i++){
                    section.items.add(new Item(sectionType,produtos.get(i).nome));
                }
            }

            sections.add(section);
        }

        notifyDataSetChanged();
    }

    @Override
    public int getNumberOfSections() {
        return sections.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        return sections.get(sectionIndex).items.size();
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public boolean doesSectionHaveFooter(int sectionIndex) {
        return false;
    }

    @Override
    public int getSectionHeaderUserType(int sectionIndex) {
        return sections.get(sectionIndex).getType();
    }

    @Override
    public int getSectionItemUserType(int sectionIndex, int itemIndex) {
        Section section = sections.get(sectionIndex);
        return section.items.get(itemIndex).getType();
    }

    /*@Override
    public int getSectionFooterUserType(int sectionIndex) {
        return sections.get(sectionIndex).getFooter().getType();
    }*/

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        try {
            return new ItemViewHolder0(inflater.inflate(R.layout.list_item_multi, parent, false));
        } catch (IllegalArgumentException e) {
            Log.d("ERROR",e.getMessage());
            throw new IllegalArgumentException("Unrecognized itemType: " + itemType);
        }

    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        try {
            return new HeaderViewHolder0(inflater.inflate(R.layout.list_item_multi_header,parent,false));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unrecognized headerType: " + headerType);
        }

    }

    /*@Override
    public FooterViewHolder onCreateFooterViewHolder(ViewGroup parent, int footerType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        try {
            return new FooterViewHolder0(inflater.inflate(R.layout.list_item_multi_footer,parent, false));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unrecognized footerType: " + footerType);
        }
    }*/

    public interface ItemOnClickListener {
        public void onClickItem(View view, int idSection, int idItem);
    }

    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, final int sectionIndex, final int itemIndex, int itemType) {
        Section s = sections.get(sectionIndex);

        switch (itemType) {
            case USER_ITEM_TYPE_0: {
                final ItemViewHolder0 ivh = (ItemViewHolder0) viewHolder;
                ivh.textView.setText(s.items.get(itemIndex).getTitle());

                Picasso.with(this.context).load(s.items.get(itemIndex).getUrl()).fit().into(ivh.imageView);

                ivh.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemOnClickListener.onClickItem(ivh.itemView,sectionIndex,itemIndex);
                        Log.d("clickItem",ivh.textView.getText().toString());
                    }
                });
                break;
            }
            case USER_ITEM_TYPE_1: {
                ItemViewHolder1 ivh = (ItemViewHolder1) viewHolder;
                ivh.textView.setText(s.items.get(itemIndex).getTitle());
                break;
            }

            default:
                throw new IllegalArgumentException("Unrecognized itemType: " + itemType);
        }

    }

    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, int sectionIndex, int headerType) {
        Section s = sections.get(sectionIndex);

        switch (headerType) {
            case USER_HEADER_TYPE_0: {
                HeaderViewHolder0 hvh = (HeaderViewHolder0) viewHolder;
                hvh.textView.setText(s.getTitle());
                break;
            }
            case USER_HEADER_TYPE_1: {
                HeaderViewHolder1 hvh = (HeaderViewHolder1) viewHolder;
                hvh.textView.setText(s.getTitle());
                break;
            }

            default:
                throw new IllegalArgumentException("Unrecognized headerType: " + headerType);
        }

    }

    /*@Override
    public void onBindFooterViewHolder(SectioningAdapter.FooterViewHolder viewHolder, int sectionIndex, int footerType) {
        Section s = sections.get(sectionIndex);

        switch (footerType) {
            case USER_FOOTER_TYPE_0: {
                FooterViewHolder0 fvh = (FooterViewHolder0) viewHolder;
                fvh.textView.setText(s.footer.getTitle());
                break;
            }
            case USER_FOOTER_TYPE_1: {
                FooterViewHolder1 fvh = (FooterViewHolder1) viewHolder;
                fvh.textView.setText(s.footer.getTitle());
                break;
            }

            default:
                throw new IllegalArgumentException("Unrecognized footerType: " + footerType);
        }

    }*/

}
