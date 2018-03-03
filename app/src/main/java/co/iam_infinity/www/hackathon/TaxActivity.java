package co.iam_infinity.www.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.iam_infinity.www.hackathon.model.BlackListedAdapter;
import co.iam_infinity.www.hackathon.model.BlackListedData;
import co.iam_infinity.www.hackathon.model.TaxData;
import co.iam_infinity.www.hackathon.model.TaxDataAdapter;

public class TaxActivity extends AppCompatActivity {

    ListView list_TaxData;
    List<TaxData> blacklist;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax);
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("uid");

        blacklist = new ArrayList<>();
        list_TaxData = (ListView)findViewById(R.id.list_TaxData);

        Toast.makeText(TaxActivity.this,""+uid,Toast.LENGTH_LONG).show();
        getBlacklisted();
    }

    public void getBlacklisted(){
        final FirebaseDatabase fd = FirebaseDatabase.getInstance();
        DatabaseReference myref = fd.getReference().child("development").child("tax");
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
            TaxData a = ds.getValue(TaxData.class);
            if (uid.equals(a.getUid())){
                blacklist.add(a);
            }
        }
        TaxDataAdapter adapter = new TaxDataAdapter(TaxActivity.this,blacklist);
        list_TaxData.setAdapter(adapter);
    }
}
