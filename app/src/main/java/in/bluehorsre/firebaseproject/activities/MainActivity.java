package in.bluehorsre.firebaseproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import in.bluehorsre.firebaseproject.R;
import in.bluehorsre.firebaseproject.adapters.MyListAdapter;
import in.bluehorsre.firebaseproject.bean.MsgObj;

public class MainActivity extends AppCompatActivity {

    EditText etMessage,etName;
    Button btnSend;
    Firebase ref;
    ListView lvList;
    ArrayList<MsgObj> arrayList;
    MyListAdapter myListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        generateTemporaryData();
        loadList();
        onClick();
    }

    private void generateTemporaryData() {
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                MsgObj m = dataSnapshot.getValue(MsgObj.class);
                arrayList.add(m);
                myListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void loadList() {
        myListAdapter = new MyListAdapter(this,arrayList);
        lvList.setAdapter(myListAdapter);
    }

    private void onClick() {

        btnSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                MsgObj msgObj = new MsgObj();
                msgObj.setName(etName.getText().toString());
                msgObj.setMsg(etMessage.getText().toString());
                ref.push().setValue(msgObj);

                etName.setText("");
                etMessage.setText("");
            }
        });
    }

    private void initialize() {
        etMessage = (EditText) findViewById(R.id.etMessage);
        btnSend = (Button) findViewById(R.id.btnSend);
        etName = (EditText) findViewById(R.id.etName);
        lvList = (ListView) findViewById(R.id.lvList);
        arrayList = new ArrayList<>();
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://fir-project-2b93b.firebaseio.com/");
    }
}
