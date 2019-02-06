package edu.temple.colorspinner;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ColorActivity extends AppCompatActivity {
    Spinner spinner;
    String[] colors = {"WHITE", "MAGENTA", "BLUE", "BLACK", "RED", "CYAN", "YELLOW", "GREEN", "MAROON", "PURPLE", "OLIVE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        ColorAdapter adapter = new ColorAdapter(ColorActivity.this, android.R.layout.simple_spinner_dropdown_item, colors);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String color = (String) parent.getItemAtPosition(position);
                findViewById(R.id.constraint).setBackgroundColor(Color.parseColor(color));
                spinner.setBackgroundColor(Color.WHITE);
                findViewById(R.id.text_view_item_name).setBackgroundColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public class ColorAdapter extends BaseAdapter {
        private Context context;
        private String[] objects;

        public ColorAdapter(Context context, int textViewResourceId, String[] objects) {
            this.context = context;
            this.objects = objects;
        }

        @Override
        public int getCount() {
            return this.objects.length;
        }

        @Override
        public Object getItem(int position) {
            return objects[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.spinner_layout, parent, false);
            }
            CharSequence currentItem = (CharSequence) getItem(position);
            String color = (String) getItem(position);

            TextView textViewItemName = (TextView) convertView.findViewById(R.id.text_view_item_name);
            textViewItemName.setText(currentItem);
            textViewItemName.setBackgroundColor(Color.parseColor(color));
            return convertView;
        }
    }

    private void showMessage(AdapterView parent, int position) {
        String color = parent.getItemAtPosition(position).toString();

        Toast.makeText(ColorActivity.this, color, Toast.LENGTH_SHORT).show();
    }

}
