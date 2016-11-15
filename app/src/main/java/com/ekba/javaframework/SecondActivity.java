package com.ekba.javaframework;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // get the extra interface name
        Bundle prevData = getIntent().getExtras();
        if(prevData == null) return;
        String interfaceName = prevData.getString("interfaceName");

        this.listView = (ListView) findViewById(R.id.listView);
        //load database adapter
        DatabaseAdapter mDatabaseAdapter = new DatabaseAdapter(this);

        mList = mDatabaseAdapter.getClasses(interfaceName);
        mDatabaseAdapter.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.main_menu, mList);
        this.listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
                String openMethod = mList.get(pos);
                Intent intent = new Intent(SecondActivity.this, FinalActivity.class);
                intent.putExtra("className", openMethod);
                startActivity(intent);
            }


        });
    }
}
