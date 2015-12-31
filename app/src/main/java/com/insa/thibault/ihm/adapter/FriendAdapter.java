package com.insa.thibault.ihm.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.TextView;

import com.insa.thibault.ihm.R;

/**
 * Created by Thibault on 16/12/2015.
 */
public class FriendAdapter extends ResourceCursorAdapter {



    public FriendAdapter(Context context, int layout, Cursor c, boolean autoRequery) {
        super(context, layout, c, autoRequery);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String nameF  = cursor.getString(2);


        TextView name = (TextView) view.findViewById(R.id.friend_name);


        name.setText(nameF);


    }
}
