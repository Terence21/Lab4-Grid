package temple.edu.gridassignment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements PaletteFragment.FragmentInteractionListener {

    boolean clickedColor;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.name_Palette_activity);

        clickedColor = false;

        TextView user_prompt = (TextView) findViewById(R.id._prompt);
        user_prompt.setText(R.string.prompt_text);
        user_prompt.setTextSize(12);
        user_prompt.setGravity(Gravity.CENTER);

        ArrayList<String> colors = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.color_array)));

        Bundle bundle = new Bundle();
        PaletteFragment fragment = PaletteFragment.newInstance(colors);
        bundle.putStringArrayList("key", colors);
        fragment.setArguments(bundle);


        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.container_1, fragment)
                .commit();


    }


    @Override
    public void displayColor(int positon, String color) {

        CanvasFragment canvas = CanvasFragment.newInstance(this);
        canvas.defineColorView(positon, color);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (clickedColor){
            ft.replace(R.id.container_2, canvas)
                    .commit();
        } else{
            ft.add(R.id.container_2, canvas)
                    .commit();
            clickedColor = true;
        }



    }
}