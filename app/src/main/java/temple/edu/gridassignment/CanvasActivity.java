package temple.edu.gridassignment;

import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        this.setTitle(R.string.name_Canvas_activity);

        Intent receiver = getIntent();
        String english = receiver.getExtras().getString(getResources().getString(R.string.English));
        // handle change in language by using index of respective locale  array
        int position = receiver.getExtras().getInt(getResources().getString(R.string.position));
        String[] color_arr = getResources().getStringArray(R.array.color_array);

        TextView textView = findViewById(R.id._colorText);
        textView.setText(color_arr[position].toUpperCase());
        textView.setGravity(Gravity.CENTER);

        ConstraintLayout layout = findViewById(R.id._constraint);
        layout.setBackgroundColor(Color.parseColor(english));
    }
}