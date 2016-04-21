package example.dy.com.homework;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import example.dy.com.homework.entity.JsonUser;
import example.dy.com.homework.entity.Step;
import example.dy.com.homework.myUtil.DatabaseHelper;
import example.dy.com.homework.myUtil.StringUtils;

/**
 * Created by dy on 2016/4/21.
 */
public class StepFragment extends Fragment {
    View vStep;
    ListView stepListView;
    EditText stepText;
    Button button;
    TextView curDate;
    DatabaseHelper databaseHelper;
    JsonUser u;
    private FragmentManager manager;
    private FragmentTransaction ft;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vStep = inflater.inflate(R.layout.fragment_step, container, false);

        curDate = (TextView) vStep.findViewById(R.id.curDate_fragment_step);
        stepListView = (ListView) vStep.findViewById(R.id.step_list);
        stepText = (EditText) vStep.findViewById(R.id.stepText_fragment);
        button = (Button) vStep.findViewById(R.id.step_button);

        databaseHelper = new DatabaseHelper(vStep.getContext());
        manager = getFragmentManager();

        u = this.getArguments().getParcelable("user");

        String curDay = StringUtils.getCurTime().substring(0, 10);
        curDate.setText("Steps in "+ curDay);
        //get step from sqllite
//        List<Step> list = databaseHelper.findAllStep();

        //how to update listview?
        List<Step> list =databaseHelper.getStep(u.getId(), curDay);

        StepAdapter stepAdapter = new StepAdapter(vStep.getContext(),list);
        stepListView.setAdapter(stepAdapter);



        //after submitting return this view
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click");
                boolean b = databaseHelper.addStep(stepText.getText().toString(), u.getId());
                System.out.println(b);
                StepFragment stepFragment = new StepFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("user", u);
                stepFragment.setArguments(bundle);

                ft = manager.beginTransaction();
                ft.replace(R.id.content_frame, stepFragment);
                ft.addToBackStack(null);

                ft.commit();



            }
        });


        return vStep;
    }
}


