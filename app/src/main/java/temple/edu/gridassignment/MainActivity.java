package temple.edu.gridassignment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements PaletteFragment.FragmentInteractionListener {


    public final static String KEY = "key";
    public final static String COLOR = "color";
    public final static String POSITION = "position";
    public final static String CLICKED_COLOR = "clicked_color";

    private boolean clickedColor;
    private String color;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.name_Palette_activity);

       // clickedColor = false;

        TextView user_prompt = (TextView) findViewById(R.id._prompt);
        user_prompt.setText(R.string.prompt_text);
        user_prompt.setTextSize(12);
        user_prompt.setGravity(Gravity.CENTER);

        final Resources res = getResources();
        ArrayList<String> colors = new ArrayList<String>(Arrays.asList(res.getStringArray(R.array.color_array)));

        Bundle bundle = new Bundle();
        PaletteFragment fragment = PaletteFragment.newInstance(colors);
        bundle.putStringArrayList(MainActivity.KEY, colors);
        fragment.setArguments(bundle);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.container_1, fragment)
                .commit();


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i("Save", "onRestoreInstanceState: is saved");
        super.onSaveInstanceState(outState);
        outState.putString(MainActivity.CLICKED_COLOR,color);
        outState.putString(MainActivity.COLOR,color);
        outState.putInt(MainActivity.POSITION,position);

    }

    // when app is bought into the foreground
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        Log.i("Restore", "onRestoreInstanceState: is restored");
        super.onRestoreInstanceState(savedInstanceState);
        this.clickedColor = savedInstanceState.getBoolean(MainActivity.CLICKED_COLOR);
        this.color = savedInstanceState.getString(MainActivity.COLOR);
        this.position = savedInstanceState.getInt(MainActivity.POSITION);

        ArrayList<String> colors = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.color_array)));

        Bundle bundle = new Bundle();
        PaletteFragment fragment = PaletteFragment.newInstance(colors);
        bundle.putStringArrayList(MainActivity.KEY, colors);
        fragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container_1, fragment)
                .commit();

        displayColor(position, color);
    }


    @Override
    public void displayColor(int positon, String color) {

        CanvasFragment canvas = new CanvasFragment();
        Configuration english_configuration = new Configuration();
        english_configuration.setLocale(new Locale("en"));
        color = this.getApplicationContext().createConfigurationContext(english_configuration).getResources().getStringArray(R.array.color_array)[positon];

        canvas.defineColorView(positon, color);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (clickedColor){
            ft.replace(R.id.container_2, canvas)
                    .commit();
        }else{
            ft.add(R.id.container_2, canvas)
                    .commit();
            clickedColor = true;
        }
        this.color = color;
        this.position = positon;
    }
}