package com.ekba.javaframework;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.List;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = (ListView) findViewById(R.id.listView);
        //load database adapter
        DatabaseAdapter mDatabaseAdapter = new DatabaseAdapter(this);
        //get interface names string list
        mList = mDatabaseAdapter.getInterface();
        mDatabaseAdapter.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.main_menu, mList);
        this.listView.setAdapter(adapter);

        //make onClick intent to second page
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
                String openClass = mList.get(pos);
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("interfaceName", openClass);
                startActivity(intent);
            }


        });
    }



}