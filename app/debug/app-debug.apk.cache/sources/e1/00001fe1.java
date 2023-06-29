package yorumlarsayfasi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.mtel.shoe.R;
import com.shoeARstore.MainActivity;
import com.shoeARstore.getData;
import com.shoeARstore.localdatabase;

/* loaded from: classes8.dex */
public class AddCommentActivity extends AppCompatActivity {
    private RatingBar commentRating;
    private EditText commentText;
    private String kullaniciid;
    private TextView saveComment;
    private String stockcode;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_comment);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("login", 0);
        this.kullaniciid = prefs.getString("id", "0");
        if (getIntent().getExtras() != null) {
            this.stockcode = getIntent().getExtras().getString(localdatabase.KEY_ROW_STOCKCODE);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView title = (TextView) toolbar.findViewById(R.id.eshop);
        title.setText("SEND COMMENT");
        ImageView backButton = (ImageView) toolbar.findViewById(R.id.backImage);
        backButton.setOnClickListener(new View.OnClickListener() { // from class: yorumlarsayfasi.AddCommentActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                AddCommentActivity.this.finish();
            }
        });
        this.commentText = (EditText) findViewById(R.id.comment);
        this.commentRating = (RatingBar) findViewById(R.id.commentRating);
        TextView textView = (TextView) findViewById(R.id.saveComment);
        this.saveComment = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: yorumlarsayfasi.AddCommentActivity.2
            /* JADX WARN: Type inference failed for: r0v11, types: [yorumlarsayfasi.AddCommentActivity$2$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (!AddCommentActivity.this.commentText.getText().toString().equals("") && AddCommentActivity.this.commentRating.getRating() != 0.0f) {
                    new getData(AddCommentActivity.this) { // from class: yorumlarsayfasi.AddCommentActivity.2.1
                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // android.os.AsyncTask
                        public void onPostExecute(String s) {
                            if (!s.equals("Hata")) {
                                Toast.makeText(AddCommentActivity.this, "Your Comment Has Been Submitted...", 0).show();
                                AddCommentActivity.this.finish();
                            } else {
                                Toast.makeText(AddCommentActivity.this, "Your Comment Could Not Be Submitted!!!", 0).show();
                            }
                            this.dialog.dismiss();
                            super.onPostExecute((AnonymousClass1) s);
                        }
                    }.execute(new String[]{MainActivity.server_url + "addcomment.php", AddCommentActivity.this.kullaniciid + "-" + AddCommentActivity.this.commentText.getText().toString(), String.valueOf(AddCommentActivity.this.commentRating.getRating()) + "-" + AddCommentActivity.this.stockcode});
                } else {
                    Toast.makeText(AddCommentActivity.this, "Please Fill In The Information Completely!!!", 0).show();
                }
            }
        });
    }
}