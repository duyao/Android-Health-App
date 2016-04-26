package example.dy.com.homework;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.dy.com.homework.entity.JsonUser;
import example.dy.com.homework.myUtil.ConnectionUtils;

/**
 * Created by dy on 2016/4/26.
 */
public class DietFragment3 extends Fragment{
    private JsonUser u;
    private FragmentManager manager;
    private FragmentTransaction ft;
    private View vDiet;
    String keyword;
    final String URL =  "https://www.googleapis.com/customsearch/v1?q="+keyword+"&cx=014672565480653443522:pzdmuyfup4e&fileType=jpg&imgSize=medium&imgType=photo&safe=medium&searchType=image&key=AIzaSyAoDJVjIMvfw4trjzv9OARrrcRyEDLteTY";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vDiet = inflater.inflate(R.layout.fragment_diet3, container, false);

//        var params = {};
//        params.q = keywords; // search text
//        params.num = num; // integer value range between 1 to 10 including
//        params.start = start; // integer value range between 1 to 101, it is like the offset
//        params.imgSize = "medium"; // for image size
//        params.searchType = "image"; // compulsory
//        params.fileType = "jpg"; // you can leave these if extension does not matters you
//        params.key = "xxxxxxxx-xxxxxxx-xxxxxxx"; // API_KEY got from https://console.developers.google.com/
//        params.cx = "xxxxxxxx-xxxxxxx-xxxxxxx"; // cx value is the custom search engine value got from https://cse.google.com/cse(if not created then create it).

        //get consume and burned
        new ConnectionUtils(URL, new ConnectionUtils.ConnectionCallback() {
            @Override
            public void onSuccess(Object result) {
                System.out.println("comsume->" + result);


            }

            @Override
            public void onFail() {
                System.out.println("cannot find consume and burn of server");

            }
        }, new String[]{});



        return vDiet;
    }
}
