package temple.edu.gridassignment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//need to handle language change

public class CanvasFragment extends Fragment {

    String color;
    int position;

    public CanvasFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  =  inflater.inflate(R.layout.fragment_canvas, container, false);
        TextView colorView = (TextView) view.findViewById(R.id._colorView);
        Log.i("create_color", "onCreateView: " + color);

        // null: no color is selected thus should only return root view
        // not null: color is selected so update root view to display color and text
        if (color != null) {
            colorView.setBackgroundColor(Color.parseColor(color));
            colorView.setText(view.getResources().getStringArray(R.array.color_array)[position].toUpperCase());
            colorView.setGravity(Gravity.CENTER);
            colorView.setTextSize(15);
        }
        return view;
    }

    /**
     * Calling activity updates CanvasFragment members
     * @param position
     * @param colorName
     */
    public void defineColorView(int position, String colorName){
        this.position = position;
        this.color = colorName;
    }

}
