package example.dy.com.homework;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import example.dy.com.homework.entity.JsonFood;
import example.dy.com.homework.entity.JsonUser;
import example.dy.com.homework.myUtil.ConnectionUtils;
import example.dy.com.homework.myUtil.DatabaseHelper;
import example.dy.com.homework.myUtil.StringUtils;

/**
 * Created by dy on 2016/4/22.
 */
public class DietFragment2 extends Fragment {
    private View vDiet;
    private ListView foodListView;
    private Button button;
    private DatabaseHelper databaseHelper;
    private JsonUser u;
    private FragmentManager manager;
    private FragmentTransaction ft;
    private FoodAdapter adapter;
    private int selectedPosition = -1;
    private static final String IP = StringUtils.IPString;
    final static String URL = "http://" + IP + "/SportServer/webresources/com.dy.entity.food/findByServing";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //1. get String of foodList
        //2. selct food info based on food list
        //3. fisrt show category, show food name, fat, details, add or decrease, weight (get from API)
        //4. checkbox? or radio

        vDiet = inflater.inflate(R.layout.fragment_diet2, container, false);

        foodListView = (ListView) vDiet.findViewById(R.id.food_listview);
        button = (Button) vDiet.findViewById(R.id.addnum_food_button);

        databaseHelper = new DatabaseHelper(vDiet.getContext());
        manager = getFragmentManager();
        Bundle b = getArguments();
        String category = "";
        if(b!=null){
            u = this.getArguments().getParcelable("user");
            category = this.getArguments().getString("category");
        }

        new ConnectionUtils(URL, new ConnectionUtils.ConnectionCallback() {
            @Override
            public void onSuccess(Object result) {
                System.out.println("reslut" + result);
                Gson gson = new Gson();
                //Json object array [{..},{}]
                List<JsonFood> list = gson.fromJson(result.toString(), new TypeToken<List<JsonFood>>() {
                }.getType());

                adapter = new FoodAdapter(vDiet.getContext(), list);
                foodListView.setAdapter(adapter);

                foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        adapter.select(position);
                        selectedPosition = position;

                    }
                });


            }

            @Override
            public void onFail() {
                System.out.println("cannot find user in server");

            }
        }, category);


//        //after submitting return this view
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("click");
//                System.out.println("llalal->get->"+adapter.getIndex()+",get item->"+adapter.getItem(adapter.getIndex()));
////                StepFragment stepFragment = new StepFragment();
////                Bundle bundle = new Bundle();
////                bundle.putParcelable("user", u);
////                stepFragment.setArguments(bundle);
////
////                ft = manager.beginTransaction();
////                ft.replace(R.id.content_frame, stepFragment);
////                ft.addToBackStack(null);
////
////                ft.commit();
//
//
//            }
//        });


        return vDiet;
    }
}
