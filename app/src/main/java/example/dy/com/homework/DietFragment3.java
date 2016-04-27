package example.dy.com.homework;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import example.dy.com.homework.entity.JsonUser;
import example.dy.com.homework.entity.ONutrient;
import example.dy.com.homework.entity.Step;
import example.dy.com.homework.myUtil.ConnectionUtils;
import example.dy.com.homework.myUtil.DatabaseHelper;

/**
 * Created by dy on 2016/4/26.
 */
public class DietFragment3 extends Fragment {

    private ListView nListView;
    private TextView foodName;
    private TextView foodGroup;
    private ImageView iamge;
    private JsonUser u;
    private FragmentManager manager;
    private FragmentTransaction ft;
    private View vDiet;
    private String keyword;
    private final String URL = "https://www.googleapis.com/customsearch/v1?q=" + keyword + "&cx=014672565480653443522:pzdmuyfup4e&fileType=jpg&imgSize=medium&imgType=photo&safe=medium&searchType=image&key=AIzaSyCww4RAetD_OrkuKw72Zwc_bqozSVdOyns";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vDiet = inflater.inflate(R.layout.fragment_diet3, container, false);


        nListView = (ListView) vDiet.findViewById(R.id.nutrient_list);
        foodName = (TextView) vDiet.findViewById(R.id.food_name);
        foodGroup = (TextView) vDiet.findViewById(R.id.food_category);
        iamge = (ImageView) vDiet.findViewById(R.id.food_image);


        JsonUser u = getArguments().getParcelable("user");
        String foodName = getArguments().getString("foodName");
        String foodCategory = getArguments().getString("foodCategory");
        ArrayList<String> nValue = getArguments().getStringArrayList("nValue");
        ArrayList<String> nName = getArguments().getStringArrayList("nName");
        System.out.println("foodName" + foodName);
        System.out.println("foodCategory" + foodCategory);
        System.out.println("U->" + u);

        for (int i = 0; i < nValue.size(); i++) {
            System.out.println("Nutrient->name=" + nName.get(i) + ",value=" + nValue.get(i));
        }

        NutrientAdapter nutrientAdapter = new NutrientAdapter(vDiet.getContext(), nName, nValue);
        nListView.setAdapter(nutrientAdapter);


        keyword = "soup";
        //get consume and burned
        new ConnectionUtils(URL, new ConnectionUtils.ConnectionCallback() {
            @Override
            public void onSuccess(Object result) {
                System.out.println("comsume->" + result);

                try {
                    JSONObject jsonObj = new JSONObject(result.toString());
                    JSONArray jsonArray = jsonObj.getJSONArray("items");
                    JSONObject item = (JSONObject) jsonArray.get(0);
                    String imagrLink = item.getString("link");
                    System.out.println("link->" + imagrLink);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


//                java.net.URL url = new URL("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464");
//                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                imageView.setImageBitmap(bmp);

            }

            @Override
            public void onFail() {
                System.out.println("cannot find consume and burn of server");

            }
        }, new String[]{});


        return vDiet;
    }
}
