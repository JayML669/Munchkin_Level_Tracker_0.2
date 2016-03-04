package com.example.jay.localsavetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Jay on 2/12/2016.
 */
public class StatEntryAdapter extends ArrayAdapter<ItemTest>{




    public interface BtnClickListener{

        public abstract void onDeleteClick(int position);
        public abstract void onEditClick(int position);

    }
    public BtnClickListener clickListener;
        public StatEntryAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

        public StatEntryAdapter(Context context, int resource, List<ItemTest> items) {
        super(context, resource, items);
    }

    public StatEntryAdapter(Context context, int resource, List<ItemTest> items,BtnClickListener listener) {
        super(context, resource, items);
        clickListener = listener;
    }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.item_entry_row, null);
            }



            Button deleteB = (Button) v.findViewById(R.id.DeleteButton);
            deleteB.setTag(position);

            deleteB.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    if(clickListener != null)

                        clickListener.onDeleteClick((Integer) v.getTag());

                }

            });

            Button editB = (Button) v.findViewById(R.id.EditButton);
            editB.setTag(position);

            editB.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    if (clickListener != null)
                        clickListener.onEditClick((Integer) v.getTag());

                }

            });




        ItemTest i = this.getItem(position);

        if (i != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.Stat);
            TextView tt2 = (TextView) v.findViewById(R.id.amt);


            if (tt1 != null) {
                tt1.setText(i.Stat);
            }

            if (tt2 != null) {
                tt2.setText(""+i.amt);
            }



        }

        return v;
    }

    }





