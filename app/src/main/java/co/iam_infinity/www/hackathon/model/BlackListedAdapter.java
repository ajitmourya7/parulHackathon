package co.iam_infinity.www.hackathon.model;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import co.iam_infinity.www.hackathon.R;

/**
 * Created by Infinity on 02-03-2018.
 */

public class BlackListedAdapter extends ArrayAdapter<BlackListedData> {
    private Activity context;
    private List<BlackListedData> infoList;

    public BlackListedAdapter(Activity context, List<BlackListedData> profileList)
    {
        super(context, R.layout.blacklistedxml, profileList);
        this.context=context;
        this.infoList = profileList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.blacklistedxml,null,true);

        TextView type = (TextView) listViewItem.findViewById(R.id.bizType);
        TextView desp = (TextView)listViewItem.findViewById(R.id.desp);
        TextView name = (TextView) listViewItem.findViewById(R.id.name);
        TextView status = (TextView) listViewItem.findViewById(R.id.status);

        BlackListedData dataInfo = infoList.get(position);
        type.setText(dataInfo.getBizType());
        desp.setText(dataInfo.getDesp());
        name.setText(dataInfo.getName());
        status.setText(dataInfo.getStatus());

        if (dataInfo.getStatus().equals("blacklisted")){
            status.setTextColor(context.getResources().getColor(R.color.red));
        }else {
            status.setTextColor(context.getResources().getColor(R.color.green));
        }

        return listViewItem;
    }

}
