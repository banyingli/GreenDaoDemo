package cn.banyingli.greendaodemo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private RecyclerView recyclerView = null;;
    private ConcertAdpater recycleAdapter = null;
    private List<Concert> dataList = new ArrayList<Concert>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        for(int i = 0; i < 5 ;i ++){
            Concert concert = new Concert();
            concert.setTitle("title" + i);
            concert.setVenue("venue" + i);
            dataList.add(concert);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        //���ò��ֹ�����
        recyclerView.setLayoutManager(layoutManager);
        //����Ϊ��ֱ���֣���Ҳ��Ĭ�ϵ�
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
        //����Adapter
        recycleAdapter = new ConcertAdpater(this.getActivity(), dataList);
        recyclerView.setAdapter(recycleAdapter);
        //���÷ָ���
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        //�������ӻ�ɾ����Ŀ�Ķ���
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}
