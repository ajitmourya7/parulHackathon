package co.iam_infinity.www.hackathon;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.iam_infinity.www.hackathon.model.BlackListedAdapter;
import co.iam_infinity.www.hackathon.model.BlackListedData;
import co.iam_infinity.www.hackathon.model.TaxAllActivity;
import co.iam_infinity.www.hackathon.model.TaxData;
import co.iam_infinity.www.hackathon.model.TaxDataAdapter;
import co.iam_infinity.www.hackathon.model.TaxDatanew;

public class TaxActivity extends AppCompatActivity {

    ListView list_TaxData;
    List<TaxData> blacklist;
    String uid;
    TextView electricity,gas,water,all;
    AlertDialog alertDialogBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax);
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("uid");

        blacklist = new ArrayList<>();
        //list_TaxData = (ListView)findViewById(R.id.list_TaxData);

        Toast.makeText(TaxActivity.this,""+uid,Toast.LENGTH_LONG).show();
        alertDialogBuilder = new AlertDialog.Builder(TaxActivity.this).create();

        electricity = (TextView)findViewById(R.id.electricity);
        gas = (TextView)findViewById(R.id.gas);
        water = (TextView)findViewById(R.id.water);
        all = (TextView)findViewById(R.id.all);

        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder.setTitle("Electricity");
                alertDialogBuilder.setMessage("You will be required to submit some documents along with the form.\n" +
                        "1. Ownership sale agreement\n" +
                        "2. Election ID\n" +
                        "3. Passport size photographs\n"+
                        "4. Two witness with their photocopy of electricity bill same sub division.\n"+
                        "5. Copy of sale deed or assessment copy of house rent");
                alertDialogBuilder.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, int which) {
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                DatabaseReference databaseReference = firebaseDatabase.getReference().child("development").child("electricity");
                                TaxDatanew taxDatanew = new TaxDatanew();
                                taxDatanew.setUid(uid);
                                taxDatanew.setData("electricity");
                                databaseReference.push().setValue(taxDatanew).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(TaxActivity.this,"Your request has been submitted",Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
                alertDialogBuilder.show();
            }
        });

        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder.setTitle("Gas");
                alertDialogBuilder.setMessage("You will be required to submit some documents along with the form.\n" +
                        "1. Aadhaar Card\n" +
                        "2. Lease Agreement\n" +
                        "3. Recent telephone, water or electricity bills\n"+
                        "4. Passport\n"+
                        "5. House registration document");
                alertDialogBuilder.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, int which) {
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                DatabaseReference databaseReference = firebaseDatabase.getReference().child("development").child("gas");
                                TaxDatanew taxDatanew = new TaxDatanew();
                                taxDatanew.setUid(uid);
                                taxDatanew.setData("gas");
                                databaseReference.push().setValue(taxDatanew).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(TaxActivity.this,"Your request has been submitted",Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
                alertDialogBuilder.show();
            }
        });

        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder.setTitle("Water");
                alertDialogBuilder.setMessage("You will be required to submit some documents along with the form.\n" +
                        "1. Residence Certificate\n" +
                        "2. Copy of Registry\n" +
                        "3. Building plan & copy of allotment letter issued by M.C.\n"+
                        "4. Electricity bill/Telephone bill/Mobile bill\n"+
                        "5. Voter card");
                alertDialogBuilder.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, int which) {
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                DatabaseReference databaseReference = firebaseDatabase.getReference().child("development").child("water");
                                TaxDatanew taxDatanew = new TaxDatanew();
                                taxDatanew.setUid(uid);
                                taxDatanew.setData("water");
                                databaseReference.push().setValue(taxDatanew).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(TaxActivity.this,"Your request has been submitted",Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
                alertDialogBuilder.show();
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaxActivity.this,TaxAllActivity.class));
            }
        });
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
