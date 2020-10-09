package temple.edu.gridassignment;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.logging.Logger;

public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        this.setTitle(R.string.name_Canvas_activity);

        Intent receiver = getIntent();

        String english = receiver.getExtras().getString(getResources().getString(R.string.English));
        String language = receiver.getExtras().getString(getResources().getString(R.string.Language));

        TextView textView = findViewById(R.id._colorText);
        textView.setText(language); textView.setGravity(Gravity.CENTER);

        ConstraintLayout layout = findViewById(R.id._constraint);
        layout.setBackgroundColor(Color.parseColor(english));
    }
}