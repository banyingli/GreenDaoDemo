package cn.banyingli.greendaodemo;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.banyingli.greendaodemo.db.Concert;
import cn.banyingli.greendaodemo.db.ConcertDao;
import cn.banyingli.greendaodemo.db.DBManager;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment implements ConcertAdpater.OnRecyclerViewItemClickListener{

    private Context mContext;
    private RecyclerView recyclerView = null;;
    private ConcertAdpater recycleAdapter = null;
    private List<Concert> dataList = new ArrayList<Concert>();
    private ConcertDao concertDao = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mContext = this.getActivity();
        concertDao = DBManager.getInstance(mContext).getDaoSession().getConcertDao();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadData();

        return view;
    }

    private void loadData(){
        dataList = concertDao.loadAll();
        recycleAdapter = new ConcertAdpater(this.getActivity(), dataList);
        recycleAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(recycleAdapter);
    }

    @Override
    protected boolean onBackPressed() {
        return false;
    }

    @Override
    public void onItemClick(View view, Concert data) {
        BaseFragment editFragment = new EditConcertFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("concertId", data.getId());
        bundle.putString("concertTitle", data.getTitle());
        bundle.putString("venue", data.getVenue());
        editFragment.setArguments(bundle);
        ((MainActivity) getActivity()).loadFragment(editFragment);
    }

    @Override
    public void onItemLongClick(View view, Concert data) {
        AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}
