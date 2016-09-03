package cn.banyingli.greendaodemo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import cn.banyingli.greendaodemo.db.Concert;
import cn.banyingli.greendaodemo.db.ConcertDao;
import cn.banyingli.greendaodemo.db.DBManager;


public class EditConcertFragment extends BaseFragment implements View.OnClickListener{

    private Context mContext;
    private EditText concertTitleEt = null;
    private EditText venueEt = null;
    private Button restoreBtn;
    private Button editBtn;
    private ConcertDao concertDao = null;
    private String concertTitle = "";
    private String venue = "";
    private long concertId = -1;

    public EditConcertFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_concert, container, false);
        mContext = this.getActivity();
        concertTitleEt = (EditText) view.findViewById(R.id.concertTitleEt);
        venueEt = (EditText) view.findViewById(R.id.venueEt);
        restoreBtn = (Button) view.findViewById(R.id.restoreBtn);
        editBtn = (Button) view.findViewById(R.id.editBtn);
        restoreBtn.setOnClickListener(this);
        editBtn.setOnClickListener(this);

        Bundle bundle = getArguments();
        if(null != bundle){
            concertId = bundle.getLong("convertId");
            concertTitleEt.setText(bundle.getString("concertTitle"));
            venueEt.setText(bundle.getString("venue"));
            editBtn.setText(R.string.action_update);
        }

        concertDao = DBManager.getInstance(mContext).getDaoSession().getConcertDao();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.restoreBtn:
                concertTitleEt.setText("");
                venueEt.setText("");
                break;
            case R.id.editBtn:
                concertTitle = concertTitleEt.getText().toString();
                venue = venueEt.getText().toString();
                if(!"".equals(concertTitle) && !"".equals(venue)){
                    Concert concert = new Concert();
                    concert.setVenue(venue);
                    concert.setTitle(concertTitle);
                    if(-1 != concertId){
                        concert.setId(concertId);
                        concertDao.update(concert);
                    }else{
                        concertDao.insert(concert);
                    }
                }
                break;
        }
    }

    @Override
    protected boolean onBackPressed() {
        return false;
    }
}
