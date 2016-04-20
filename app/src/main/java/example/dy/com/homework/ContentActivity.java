package example.dy.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import example.dy.com.homework.entity.JsonUser;
import example.dy.com.homework.entity.User;
import example.dy.com.homework.myUtil.ConnectionUtils;
import example.dy.com.homework.myUtil.DatabaseHelper;
import example.dy.com.homework.myUtil.StringUtils;

public class ContentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView nameText;
    String curUserName;
    private DatabaseHelper dbHelper;
    private  static final String IP = StringUtils.IPString;
    private static final String URL = "http://"+IP+":8080/SportServer/webresources/com.dy.entity.user/findByName";
    JsonUser user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



//        nameText = (TextView) this.findViewById(R.id.curUserName);
        Intent i = getIntent();
        curUserName = (String) i.getSerializableExtra("userName");
        dbHelper = new DatabaseHelper(getApplicationContext());
        dbHelper.findAllUser();

        //get Usr by name
        new ConnectionUtils(URL, new ConnectionUtils.ConnectionCallback() {
            @Override
            public void onSuccess(Object result) {
//                System.out.println("reslut"+result);
                Gson gson = new Gson();
                //Json object array [{..},{}]
                List<JsonUser> list= gson.fromJson(result.toString(), new TypeToken<List<JsonUser>>(){}.getType());
                user = list.get(0);
//                System.out.println("fromJson->"+user);


//                dbHelper.findAllUser();
//                System.out.println("insert before===================");
                dbHelper = new DatabaseHelper(getApplicationContext());
                if(dbHelper.checkUser(curUserName)){
                    System.out.println("exsist");
                }else{
                    System.out.println("add User");
                    User tmp = new User();
                    tmp.setId(user.getId());
                    tmp.setName(user.getName());
                    tmp.setPassword(StringUtils.getPasswordEncryption(user.getPassword()));
                    tmp.setLatitude(0);
                    tmp.setLongitude(0);
                    tmp.setRegistration(StringUtils.getCurTime());
                    dbHelper.addUser(tmp);
                }
                System.out.println("insert after===================");
                dbHelper.findAllUser();
            }

            @Override
            public void onFail() {
                System.out.println("cannot find user in server");

            }
        }, curUserName);
        System.out.println("GET user ->" + user);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
