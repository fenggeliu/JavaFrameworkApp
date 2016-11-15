package com.ekba.javaframework;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fengge on 11/13/2016.
 */

class MyAdapter extends ArrayAdapter<SingleRow> {
    private List<SingleRow> l = new ArrayList<>();
    private Context c;
    MyAdapter(Context context, List<SingleRow> list) {
        super(context, R.layout.single_method, list);
        // get a database instance
        c = context;
        l = list;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater myInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = myInflater.inflate(R.layout.single_method, viewGroup, false);

        TextView methodTitle = (TextView) row.findViewById(R.id.methodTitle);
        TextView inputType = (TextView) row.findViewById(R.id.inputType);
        TextView returnType = (TextView) row.findViewById(R.id.returnType);
        TextView description = (TextView) row.findViewById(R.id.description);

        final Typeface font = Typeface.createFromAsset(c.getAssets(), "fonts/Monaco.ttf");
        SingleRow temp = l.get(i);

        methodTitle.setTypeface(font);
        //inputType.setTypeface(font);
        //returnType.setTypeface(font);
        //description.setTypeface(font);

        methodTitle.setText(Html.fromHtml(temp.title));
        if (temp.inputType.isEmpty()) {
            inputType.setText(Html.fromHtml(temp.returnType));
            returnType.setText(Html.fromHtml(temp.description));
            description.setVisibility(View.GONE);
        } else {
            inputType.setText(Html.fromHtml(temp.inputType));
            returnType.setText(Html.fromHtml(temp.returnType));
            description.setText(Html.fromHtml(temp.description));
        }

        return row;
    }

}


    class SingleRow {
        String title;
        String inputType;
        String returnType;
        String description;

        SingleRow(String title, String inputType, String returnType, String description) {
            this.title = title;
            this.inputType = inputType;
            this.returnType = returnType;
            this.description = description;
        }
    }


