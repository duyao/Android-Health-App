package example.dy.com.homework;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import example.dy.com.homework.entity.JsonUser;
import example.dy.com.homework.myUtil.DatabaseHelper;

/**
 * Created by dy on 2016/4/22.
 */
public class DietFragment2 extends Fragment {
    private View vDiet;
    private ListView dietListView;
    private Button button;
    private DatabaseHelper databaseHelper;
    private JsonUser u;
    private FragmentManager manager;
    private FragmentTransaction ft;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //1. get String of foodList
        //2. selct food info based on food list
        //3. fisrt show category, show food name, fat, details, add or decrease, weight (get from API)
        //4. checkbox? or radio

        vDiet = inflater.inflate(R.layout.fragment_diet2, container, false);

//        dietListView = (ListView) vDiet.findViewById(R.id.category_listview);
//        button = (Button) vDiet.findViewById(R.id.select_diet_button);

        databaseHelper = new DatabaseHelper(vDiet.getContext());
        manager = getFragmentManager();

        u = this.getArguments().getParcelable("user");
        System.out.println("diet2->u" + u);



//        String[] list = {"Pork Products", "Fruits and Fruit Juices", "Meals, Entrees, and Side Dishes", "Vegetables and Vegetable Products", "Soups, Sauces, and Gravies", "Breakfast Cereals"};
//        final CategoryAdapter adapter = new CategoryAdapter(vDiet.getContext(),list);
//        dietListView.setAdapter(adapter);
//
//
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
