package co.iam_infinity.www.hackathon.model;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.iam_infinity.www.hackathon.R;
import co.iam_infinity.www.hackathon.TaxActivity;

public class TaxAllActivity extends AppCompatActivity {

    ListView list_TaxData;
    List<TaxData> blacklist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_all);

        blacklist = new ArrayList<>();
        list_TaxData = (ListView)findViewById(R.id.list_TaxData);
        getBlacklisted();
    }

    public void getBlacklisted(){
        final FirebaseDatabase fd = FirebaseDatabase.getInstance();
        DatabaseReference myref = fd.getReference().child("development").child("electricity");
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
            blacklist.add(a);
        }
        TaxDataAdapter adapter = new TaxDataAdapter(TaxAllActivity.this,blacklist);
        list_TaxData.setAdapter(adapter);
    }
}
