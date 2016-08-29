package cn.banyingli.greendaodemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.banyingli.greendaodemo.db.Concert;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by Administrator on 2016/8/25.
 */
public class ConcertAdpater extends RecyclerView.Adapter<ConcertAdpater.MyViewHolder> {
    private Context mContext;
    private List<Concert> dataList;

    public ConcertAdpater(Context mContext, List<Concert> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_concert, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.titleTv.setText(dataList.get(position).getTitle());
        holder.venueTv.setText(dataList.get(position).getVenue());
        holder.dateTv.setText("----");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends ViewHolder {

        TextView titleTv;
        TextView venueTv;
        TextView dateTv;

        public MyViewHolder(View view) {
            super(view);
            titleTv = (TextView) view.findViewById(R.id.titleTv);
            venueTv = (TextView) view.findViewById(R.id.venueTv);
            dateTv = (TextView) view.findViewById(R.id.dateTv);
        }
    }

}