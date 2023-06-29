package com.shoeARstore;

import adresspage.MemberAdressActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;
import favoritespage.FavoritesActivity;
import orderspage.OrderActivity;

/* loaded from: classes9.dex */
public class HomeFragment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String MyPREFERENCES = "login";
    public static final String id = "id";
    public static final String name = "name";
    LinearLayout adress;
    ImageView bag;
    LinearLayout bag0;
    TextView cartcount;
    TextView categories;
    DrawerLayout drawer;
    ImageView fav;
    LinearLayout fav0;
    LinearLayout findstore;
    TextView helppage;
    ImageView home;
    LinearLayout home0;
    TextView kullaniciadi;
    String kullaniciid;
    LinearLayout logout;
    NavigationView navigationView;
    ImageView noti;
    LinearLayout noti0;
    ImageView offer;
    LinearLayout offer0;
    LinearLayout orders;
    Fragment selectedFragment;
    SharedPreferences sharedPreferences;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        this.sharedPreferences = getSharedPreferences("login", 0);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("login", 0);
        String string = prefs.getString("id", "-1");
        this.kullaniciid = string;
        if (!string.equals("-1")) {
            this.logout.setVisibility(0);
            String welcomeText = getResources().getString(com.mtel.shoe.R.string.welcometext, prefs.getString("name", "NoName"));
            this.kullaniciadi.setText(welcomeText);
        } else {
            this.logout.setVisibility(8);
        }
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mtel.shoe.R.layout.navigationdrawer);
        Toolbar toolbar = (Toolbar) findViewById(com.mtel.shoe.R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        getWindow().setSoftInputMode(3);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("login", 0);
        this.kullaniciid = prefs.getString("id", "-1");
        this.drawer = (DrawerLayout) findViewById(com.mtel.shoe.R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.drawer, toolbar, com.mtel.shoe.R.string.navigation_drawer_open, com.mtel.shoe.R.string.navigation_drawer_close);
        this.drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(com.mtel.shoe.R.id.nav_view);
        this.navigationView = navigationView;
        navigationView.setNavigationItemSelectedListener(this);
        View navigation = this.navigationView.getHeaderView(0);
        this.categories = (TextView) navigation.findViewById(com.mtel.shoe.R.id.categories);
        TextView textView = (TextView) navigation.findViewById(com.mtel.shoe.R.id.name);
        this.kullaniciadi = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.HomeFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SharedPreferences prefs2 = HomeFragment.this.getApplicationContext().getSharedPreferences("login", 0);
                if (prefs2.getString("id", "-1").equals("-1")) {
                    Intent gec = new Intent(HomeFragment.this, SignIn.class);
                    HomeFragment.this.startActivity(gec);
                }
            }
        });
        TextView textView2 = (TextView) navigation.findViewById(com.mtel.shoe.R.id.helpmenu);
        this.helppage = textView2;
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.HomeFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent gec = new Intent(HomeFragment.this, HelpApplication.class);
                HomeFragment.this.startActivity(gec);
            }
        });
        LinearLayout linearLayout = (LinearLayout) navigation.findViewById(com.mtel.shoe.R.id.findstore);
        this.findstore = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.HomeFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (HomeFragment.this.drawer.isDrawerOpen(GravityCompat.START)) {
                    HomeFragment.this.drawer.closeDrawer(GravityCompat.START);
                }
                Intent gec = new Intent(HomeFragment.this, FindStoreActivity.class);
                HomeFragment.this.startActivity(gec);
            }
        });
        LinearLayout linearLayout2 = (LinearLayout) navigation.findViewById(com.mtel.shoe.R.id.logout);
        this.logout = linearLayout2;
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.HomeFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SharedPreferences sharedPreferences = HomeFragment.this.getApplicationContext().getSharedPreferences("login", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                HomeFragment.this.cartcount.setText("0");
                HomeFragment.this.logout.setVisibility(8);
                HomeFragment.this.kullaniciadi.setText(com.mtel.shoe.R.string.giristext);
                HomeFragment.this.clickb("1");
            }
        });
        this.categories.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.HomeFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (HomeFragment.this.drawer.isDrawerOpen(GravityCompat.START)) {
                    HomeFragment.this.drawer.closeDrawer(GravityCompat.START);
                }
                HomeFragment.this.clickb(ExifInterface.GPS_MEASUREMENT_2D);
            }
        });
        LinearLayout linearLayout3 = (LinearLayout) navigation.findViewById(com.mtel.shoe.R.id.siparisler);
        this.orders = linearLayout3;
        linearLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.HomeFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (HomeFragment.this.drawer.isDrawerOpen(GravityCompat.START)) {
                    HomeFragment.this.drawer.closeDrawer(GravityCompat.START);
                }
                if (HomeFragment.this.kullaniciid.equals("-1")) {
                    Toast.makeText(HomeFragment.this, "Please Login!!!", 0).show();
                    return;
                }
                Intent gec = new Intent(HomeFragment.this, OrderActivity.class);
                HomeFragment.this.startActivity(gec);
            }
        });
        LinearLayout linearLayout4 = (LinearLayout) navigation.findViewById(com.mtel.shoe.R.id.adress);
        this.adress = linearLayout4;
        linearLayout4.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.HomeFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (HomeFragment.this.drawer.isDrawerOpen(GravityCompat.START)) {
                    HomeFragment.this.drawer.closeDrawer(GravityCompat.START);
                }
                if (HomeFragment.this.kullaniciid.equals("-1")) {
                    Toast.makeText(HomeFragment.this, "Please Login!!!", 0).show();
                    return;
                }
                Intent gec = new Intent(HomeFragment.this, MemberAdressActivity.class);
                HomeFragment.this.startActivity(gec);
            }
        });
        this.home = (ImageView) findViewById(com.mtel.shoe.R.id.home);
        this.home0 = (LinearLayout) findViewById(com.mtel.shoe.R.id.home0);
        this.offer = (ImageView) findViewById(com.mtel.shoe.R.id.offer);
        this.offer0 = (LinearLayout) findViewById(com.mtel.shoe.R.id.offer0);
        this.fav = (ImageView) findViewById(com.mtel.shoe.R.id.fav);
        this.fav0 = (LinearLayout) findViewById(com.mtel.shoe.R.id.fav0);
        this.bag = (ImageView) findViewById(com.mtel.shoe.R.id.bag);
        this.bag0 = (LinearLayout) findViewById(com.mtel.shoe.R.id.bag0);
        this.noti = (ImageView) findViewById(com.mtel.shoe.R.id.noti);
        this.noti0 = (LinearLayout) findViewById(com.mtel.shoe.R.id.noti0);
        this.cartcount = (TextView) findViewById(com.mtel.shoe.R.id.cartcount);
        cartlistCount();
        this.home0.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.HomeFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeFragment.this.clickb("1");
            }
        });
        this.offer0.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.HomeFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeFragment.this.clickb(ExifInterface.GPS_MEASUREMENT_2D);
            }
        });
        this.fav0.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.HomeFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeFragment.this.clickb(ExifInterface.GPS_MEASUREMENT_3D);
            }
        });
        this.bag0.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.HomeFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeFragment.this.clickb("4");
            }
        });
        this.noti0.setOnClickListener(new View.OnClickListener() { // from class: com.shoeARstore.HomeFragment.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeFragment.this.clickb("5");
            }
        });
        if (getIntent().getExtras() != null) {
            clickb(getIntent().getExtras().getString("fragment", "1"));
        } else {
            clickb("1");
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.shoeARstore.HomeFragment$13] */
    private void cartlistCount() {
        if (!this.kullaniciid.equals("-1")) {
            new getData(this) { // from class: com.shoeARstore.HomeFragment.13
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(String s) {
                    if (s != null) {
                        HomeFragment.this.cartcount.setText(s);
                    }
                    this.dialog.dismiss();
                    super.onPostExecute((AnonymousClass13) s);
                }
            }.execute(new String[]{MainActivity.server_url + "cartlist.php", this.kullaniciid, "count"});
        } else {
            this.cartcount.setText("0");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickb(String s) {
        this.home.setColorFilter(getResources().getColor(com.mtel.shoe.R.color.boticon), PorterDuff.Mode.MULTIPLY);
        this.offer.setColorFilter(getResources().getColor(com.mtel.shoe.R.color.boticon), PorterDuff.Mode.MULTIPLY);
        this.fav.setColorFilter(getResources().getColor(com.mtel.shoe.R.color.boticon), PorterDuff.Mode.MULTIPLY);
        this.bag.setColorFilter(getResources().getColor(com.mtel.shoe.R.color.boticon), PorterDuff.Mode.MULTIPLY);
        this.noti.setColorFilter(getResources().getColor(com.mtel.shoe.R.color.boticon), PorterDuff.Mode.MULTIPLY);
        if (s.equalsIgnoreCase("1")) {
            this.selectedFragment = MainActivity.newInstance();
            this.home.setColorFilter(getResources().getColor(com.mtel.shoe.R.color.boticoncolor), PorterDuff.Mode.MULTIPLY);
        } else if (s.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
            this.selectedFragment = CategoriesActivity.newInstance();
            this.offer.setColorFilter(getResources().getColor(com.mtel.shoe.R.color.boticoncolor), PorterDuff.Mode.MULTIPLY);
        } else if (s.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_3D)) {
            this.fav.setColorFilter(getResources().getColor(com.mtel.shoe.R.color.boticoncolor), PorterDuff.Mode.MULTIPLY);
            this.selectedFragment = FavoritesActivity.newInstance();
        } else if (s.equalsIgnoreCase("4")) {
            this.selectedFragment = CartListActivity.newInstance();
            this.bag.setColorFilter(getResources().getColor(com.mtel.shoe.R.color.boticoncolor), PorterDuff.Mode.MULTIPLY);
        } else if (s.equalsIgnoreCase("5")) {
            this.selectedFragment = NotificationActivity.newInstance();
            this.noti.setColorFilter(getResources().getColor(com.mtel.shoe.R.color.boticoncolor), PorterDuff.Mode.MULTIPLY);
        }
        if (this.selectedFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(com.mtel.shoe.R.id.content, this.selectedFragment);
            transaction.commit();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(268435456);
        startActivity(intent);
    }

    @Override // com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}