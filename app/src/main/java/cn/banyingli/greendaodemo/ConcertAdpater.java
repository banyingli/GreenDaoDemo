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
public class ConcertAdpater extends RecyclerView.Adapter<ConcertAdpater.MyViewHolder>
        implements View.OnClickListener, View.OnLongClickListener{
    private Context mContext;
    private List<Concert> dataList;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public ConcertAdpater(Context mContext, List<Concert> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_concert, parent,
                false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.titleTv.setText(dataList.get(position).getTitle());
        holder.venueTv.setText(dataList.get(position).getVenue());
        holder.dateTv.setText("----");
        holder.itemView.setTag(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onClick(View v) {
        if(null != mOnItemClickListener){
            mOnItemClickListener.onItemClick(v, (Concert) v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if(null != mOnItemClickListener){
            mOnItemClickListener.onItemLongClick(v, (Concert) v.getTag());
        }
        return false;
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

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, Concert data);
        void onItemLongClick(View view, Concert data);
    }

}