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
 * Created by OMEE PC on 22/1/2018.
 */

public class fair_adapter extends RecyclerView.Adapter<fair_adapter.FairHandler>{
    private List<fair> fairList;
    private LayoutInflater inflater;
    private Context context;

    private fair_adapter.ItemClickCallback itemClickCallback;

    public interface ItemClickCallback {
        void fonItemClick(int p);
    }

    public void setItemClickCallback(final fair_adapter.ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public fair_adapter(List<fair> fairList, Context c) {
        this.context = c;
        this.inflater = LayoutInflater.from(c);
        this.fairList = fairList;
    }

    @Override
    public fair_adapter.FairHandler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fair, parent, false);
        return new fair_adapter.FairHandler(view);
    }

    @Override
    public void onBindViewHolder(fair_adapter.FairHandler holder, int position) {
        fair item = fairList.get(position);
        holder.fnm.setText(item.getFname());
        holder.fcat.setText(item.getFcategory());
        holder.flo.setText(item.getFlocation());
        holder.fsd.setText(item.getFsdate());
        holder.fed.setText(item.getFedate());
        holder.fdst.setText(item.getFdst());
        holder.fdet.setText(item.getFdet());
        holder.fnos.setText(item.getFnost());
        holder.fsp.setText(item.getFsponsor());
        holder.fcnt.setText(item.getFcontact());
        Picasso.with(context).load(item.getFimagename()).into(holder.imageViewfpp);
        holder.downtext.setText(item.getTdowntext());
    }

    @Override
    public int getItemCount() {
        return fairList.size();
    }

    class FairHandler extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fnm, fcat, flo, fsd, fed, fdst, fdet, fnos, fsp, fcnt;
        TextView downtext;
        ImageView imageViewfpp;
        private View container;

        public FairHandler(View itemView) {
            super(itemView);

            fnm = (TextView) itemView.findViewById(R.id.f_name);
            fcat = (TextView) itemView.findViewById(R.id.f_category);
            flo = (TextView) itemView.findViewById(R.id.f_location);
            fsd = (TextView) itemView.findViewById(R.id.f_sdate);
            fed = (TextView) itemView.findViewById(R.id.f_edate);
            fdst = (TextView) itemView.findViewById(R.id.f_dst);
            fdet = (TextView) itemView.findViewById(R.id.f_det);
            fnos = (TextView) itemView.findViewById(R.id.f_nos);
            fsp = (TextView) itemView.findViewById(R.id.f_sponsor);
            fcnt = (TextView) itemView.findViewById(R.id.f_contact);

            imageViewfpp = (ImageView) itemView.findViewById(R.id.f_image);

            downtext = (TextView) itemView.findViewById(R.id.diffr);

            container = itemView.findViewById(R.id.fair_container);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.fair_container) {
                itemClickCallback.fonItemClick(getAdapterPosition());
            }
        }
    }

}
