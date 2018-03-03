package co.iam_infinity.www.hackathon.model;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import co.iam_infinity.www.hackathon.R;

/**
 * Created by Infinity on 10-01-2018.
 */

public class NotificationList extends ArrayAdapter<Notification> {
    private Activity context;
    private List<Notification> infoList;

    public NotificationList(Activity context, List<Notification> profileList)
    {
        super(context, R.layout.dataxml, profileList);
        this.context=context;
        this.infoList = profileList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.dataxml,null,true);

        TextView title = (TextView) listViewItem.findViewById(R.id.title_noti);
        TextView body = (TextView)listViewItem.findViewById(R.id.body_noti);
        TextView time = (TextView) listViewItem.findViewById(R.id.time_noti);

        Notification dataInfo = infoList.get(position);
        title.setText(dataInfo.getTitle());
        body.setText(dataInfo.getBody());
        time.setText(dataInfo.getTimeStamp());

        return listViewItem;
    }

}

