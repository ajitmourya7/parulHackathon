package co.iam_infinity.www.hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.iam_infinity.www.hackathon.model.Notification;
import co.iam_infinity.www.hackathon.model.NotificationList;

public class NotificationActivity extends AppCompatActivity {

    ListView listView_notification;
    List <Notification> notifications;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notifications = new ArrayList<>();
        listView_notification = (ListView)findViewById(R.id.list_viewnotification);
        getnotification();
    }

    public void getnotification(){
        final FirebaseDatabase fd = FirebaseDatabase.getInstance();
        DatabaseReference myref = fd.getReference().child("development").child("notification");
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
        notifications.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            Notification a = ds.getValue(Notification.class);
            notifications.add(a);
        }
        NotificationList adapter = new NotificationList(NotificationActivity.this,notifications);
        listView_notification.setAdapter(adapter);
    }
}
