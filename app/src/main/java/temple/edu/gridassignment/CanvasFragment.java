package temple.edu.gridassignment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
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
   String[] colors;

    public CanvasFragment() {

    }

    public static CanvasFragment newInstance(Context context) {
        CanvasFragment fragment = new CanvasFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        colors = getResources().getStringArray(R.array.color_array);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  =  inflater.inflate(R.layout.fragment_canvas, container, false);
        TextView colorView = (TextView) view.findViewById(R.id._colorView);
        colorView.setBackgroundColor(Color.parseColor(color));
        colorView.setText(view.getResources().getStringArray(R.array.color_array)[position].toUpperCase());
        colorView.setGravity(Gravity.CENTER);
        colorView.setTextSize(15);
        return view;
    }


    public void defineColorView(int position, String colorName){
        this.position = position;
        this.color = colorName;
    }


}