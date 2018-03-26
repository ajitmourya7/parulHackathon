package co.iam_infinity.www.hackathon.model;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import co.iam_infinity.www.hackathon.R;

/**
 * Created by Infinity on 03-03-2018.
 */

public class TaxDataAdapter extends ArrayAdapter<TaxData> {
    private Activity context;
    private List<TaxData> infoList;

    public TaxDataAdapter(Activity context, List<TaxData> profileList)
    {
        super(context, R.layout.taxdataxml, profileList);
        this.context=context;
        this.infoList = profileList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.taxdataxml,null,true);

        TextView name = (TextView) listViewItem.findViewById(R.id.nametax);
        TextView uid = (TextView)listViewItem.findViewById(R.id.uidtax);
        TextView status = (TextView) listViewItem.findViewById(R.id.statustax);

        TaxData dataInfo = infoList.get(position);
        name.setText(dataInfo.getName());
        uid.setText(dataInfo.getUid());
        status.setText(dataInfo.getStatus());

        return listViewItem;
    }

}
