package co.iam_infinity.www.hackathon;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.iam_infinity.www.hackathon.model.BlackListedAdapter;
import co.iam_infinity.www.hackathon.model.BlackListedData;
import de.hdodenhof.circleimageview.CircleImageView;

public class BlacklistedActivity extends AppCompatActivity {

    ListView list_Blacklisted;
    List<BlackListedData> blacklist;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blacklisted);
        blacklist = new ArrayList<>();
        list_Blacklisted = (ListView)findViewById(R.id.list_Blacklisted);

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(BlacklistedActivity.this);
        final LayoutInflater inflater = this.getLayoutInflater();

        getBlacklisted();

        list_Blacklisted.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final View dialogView = inflater.inflate(R.layout.blacklisted_dialog, null);
                dialogBuilder.setView(dialogView);

                final CircleImageView imagebd = (CircleImageView)dialogView.findViewById(R.id.imagebd);
                final TextView namebd = (TextView)dialogView.findViewById(R.id.namebd);
                final TextView despbd = (TextView)dialogView.findViewById(R.id.despbd);
                final TextView typebd = (TextView)dialogView.findViewById(R.id.typebd);
                final Button statusbd = (Button) dialogView.findViewById(R.id.statusbd);

                Glide.with(dialogView).load(blacklist.get(position).getUrl()).into(imagebd);

                namebd.setText(blacklist.get(position).getName());
                despbd.setText(blacklist.get(position).getDesp());
                typebd.setText(blacklist.get(position).getBizType());
                statusbd.setText(blacklist.get(position).getStatus());

                if (blacklist.get(position).getStatus().equals("blacklisted")){
                    statusbd.setBackgroundColor(dialogView.getResources().getColor(R.color.red));
                }else{
                    statusbd.setBackgroundColor(dialogView.getResources().getColor(R.color.green));
                }

                AlertDialog b = dialogBuilder.create();
                b.show();
            }
        });
    }

    public void getBlacklisted(){
        final FirebaseDatabase fd = FirebaseDatabase.getInstance();
        DatabaseReference myref = fd.getReference().child("development").child("blackList");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {
        blacklist.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            BlackListedData a = ds.getValue(BlackListedData.class);
            blacklist.add(a);
        }
        BlackListedAdapter adapter = new BlackListedAdapter(BlacklistedActivity.this,blacklist);
        list_Blacklisted.setAdapter(adapter);
    }
}
