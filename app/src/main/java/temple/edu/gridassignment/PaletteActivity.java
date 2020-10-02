package temple.edu.gridassignment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class PaletteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.name_Palette_activity);

        ArrayList<String> colors = new ArrayList<>();
        colors.add("green");
        colors.add("blue");
        colors.add("red");
        colors.add("yellow");
        colors.add("teal");
        colors.add("cyan");
        colors.add("lime");
        colors.add("darkgray");
        colors.add("purple");


        GridView grid = findViewById(R.id._ColorGrid);
        grid.setNumColumns(3);

        BaseAdapter adapter = new ColorAdapter(this,colors);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                send(view);
            }
        });
    }

    /**
     * send the text value of view to another activity
     * @param v TextView from from ColorAdapter to send text to activity
     */
    public void send(View v){
        Intent intent = new Intent(PaletteActivity.this, CanvasActivity.class);
        intent.putExtra(String.valueOf(R.string.intent_name),((TextView)v).getText().toString());
        startActivity(intent);

    }
}