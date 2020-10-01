package temple.edu.gridassignment;

import android.widget.BaseAdapter;
import android.widget.GridView;
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

        GridView grid = findViewById(R.id._ColorGrid);
        grid.setColumnWidth(3);

        BaseAdapter adapter = new ColorAdapter(this,colors);
        grid.setAdapter(adapter);
    }
}