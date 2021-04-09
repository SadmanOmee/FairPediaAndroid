package com.example.omeepc.fairpedia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 6/14/2017.
 */

public class product_adapter extends RecyclerView.Adapter<product_adapter.ProductHandler> {

    private List<product> productList;
    private LayoutInflater inflater;
    private Context context;

    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback {
        void onItemClick(int p);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public product_adapter(List<product> productList, Context c) {
        this.context = c;
        this.inflater = LayoutInflater.from(c);
        this.productList = productList;
    }

    @Override
    public ProductHandler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product, parent, false);
        return new ProductHandler(view);
    }

    @Override
    public void onBindViewHolder(ProductHandler holder, int position) {
        product item = productList.get(position);
        holder.prname.setText(item.getPname());
        holder.prcat.setText(item.getPcategory());
        holder.prprice.setText(item.getPprice());
        holder.prcfair.setText(item.getCfair());
        holder.prfloc.setText(item.getFlocation());
        holder.prsno.setText(item.getSno());
        holder.prsname.setText(item.getSname());
        holder.pramount.setText(item.getPamount());
        holder.prdemand.setText(item.getPdemand());
        holder.prreview.setText(item.getPreview());
        Picasso.with(context).load(item.getPrdimgpath()).into(holder.imageView_prop);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductHandler extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView prname, prcat, prprice, prcfair, prfloc, prsno, prsname, pramount, prdemand, prreview;
        ImageView imageView_prop;
        private View container;

        public ProductHandler(View itemView) {
            super(itemView);

            prname = (TextView) itemView.findViewById(R.id.p_name);
            prcat = (TextView) itemView.findViewById(R.id.p_category);
            prprice = (TextView) itemView.findViewById(R.id.p_price);
            prcfair = (TextView) itemView.findViewById(R.id.p_cfair);
            prfloc = (TextView) itemView.findViewById(R.id.p_flocation);
            prsno = (TextView) itemView.findViewById(R.id.p_sno);
            prsname = (TextView) itemView.findViewById(R.id.p_sname);
            pramount = (TextView) itemView.findViewById(R.id.p_amount);
            prdemand = (TextView) itemView.findViewById(R.id.p_demand);
            prreview = (TextView) itemView.findViewById(R.id.p_review);
            imageView_prop = (ImageView) itemView.findViewById(R.id.p_img);

            container = itemView.findViewById(R.id.product_container);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.product_container) {
                itemClickCallback.onItemClick(getAdapterPosition());
            }
        }
    }

}
