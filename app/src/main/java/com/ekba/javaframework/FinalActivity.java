package com.ekba.javaframework;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;


public class FinalActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<SingleRow> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        listView = (ListView) findViewById(R.id.listView);
        //get intent extra
        Bundle prevData = getIntent().getExtras();
        if (prevData == null) return;
        String className = prevData.getString("className");

        DatabaseAdapter mDatabaseAdapter = new DatabaseAdapter(this);
        Cursor cursor = mDatabaseAdapter.getInstance(className);
        // extract string list of method names with html font support
        // extract string list of input, return and description
        //List<String> prefix = new ArrayList<>();
        //List<String> returnType = new ArrayList<>();
        //List<String> description = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String title = "";
            if (!cursor.isNull(cursor.getColumnIndex("type"))) {
                title = ".";
            }
            title += "<font color = \"#FFA500\">" + stringHelper(cursor, 1)
                    + "</font>(" + "<font color = \"#0059FF\">"
                    + stringHelper(cursor, 9)
                    + "</font>" + objHelper(cursor, 10)
                    + classHelper(cursor, 11)
                    + "</font>" + objHelper(cursor, 12)
                    + classHelper(cursor, 16)
                    + "</font>" + objHelper(cursor, 17)
                    + classHelper(cursor, 18)
                    + "</font>" + objHelper(cursor, 19) + ")";
            /*titles.add(temp);
            prefix.add(stringHelper(cursor, "type"));
            returnType.add(stringHelper(cursor, "return") + " " + stringHelper(cursor, "return_2"));
            description.add(stringHelper(cursor, "description"));*/
            String inputType;
            if (cursor.isNull(7))
                inputType = "";
            else inputType = "input type: <font color = \"#0059FF\">" + stringHelper(cursor, 7) + "</font>";
            String descritption;
            if (cursor.isNull(15))
                descritption = "";
            else descritption = "description:<br />" + stringHelper(cursor, 15);
            String returned;
            if (stringHelper(cursor,13).equals("void"))
                returned = "return: void";
            else returned = "return: <font color = \"#0059FF\">" + stringHelper(cursor, 13)
                    + "</font> " + stringHelper(cursor, 14);
            items.add(new SingleRow(title, inputType, returned, descritption));

            cursor.moveToNext();
        }
        cursor.close();
        mDatabaseAdapter.close();

        listView.setAdapter(new MyAdapter(this, items));

    }
    private String stringHelper(Cursor c, int i) {
        if (c.isNull(i))
            return "";
        else return c.getString(i);
    }

    private String objHelper(Cursor c, int i) {
        if (c.isNull(i))
            return "";
        else return " " + c.getString(i);
    }

    private String classHelper(Cursor c, int i) {
        if (c.isNull(i))
            return "";
        else return ", <font color = \"#0059FF\">" + c.getString(i)
                + "</font>";
    }
}