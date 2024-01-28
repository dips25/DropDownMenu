package com.view.menu.mydropdown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Fade;
import androidx.transition.Scene;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    ViewGroup sceneRoot;
    Scene start,end;

    boolean isStart;

    Button btn;

    TextView textView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.getselectedvalues);

        //Inflate the dropdownMenu

        MyDropDownMenu myDropDownMenu = (MyDropDownMenu) findViewById(R.id.mydropdown);

        //Inflate the inner dropdown
        MyDropDownMenu inner1 = (MyDropDownMenu) findViewById(R.id.innerlayout);

        //Create a dropdown inside inner1

        MyDropDownMenu child1 = inner1.setSubLayouts("Child 1" , R.drawable.sampleheader , true , 5 , getResources().getColor(R.color.black)
                , 15 , getResources().getColor(R.color.white) , 12 , MyDropDownMenu.CENTER , true , true , Typeface.NORMAL , R.drawable.dropdowniconn);

        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , 300);
        ll.setMargins(5 , 5 , 5 , 5);

        //Create an ImageView at runtime
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.lockdoor);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(ll);

        ImageView imageView2 = new ImageView(this);
        imageView2.setImageResource(R.drawable.lockdoor);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView2.setLayoutParams(ll);


        child1.addToDropDown(imageView);//add the imageView to the dropdown child1 using method addToDropDown(View view).Any views can be added
        child1.addToDropDown(imageView2);//adding another imageview.You can add any no of views.
        child1.setAllChilds();//setting All the views in child1 hierarchy to the dropdown
        child1.setDropDown();//setting up the dropdown(Most Important.O/W dropdown will not work)


        inner1.setAllChilds();//add all the childs to the dropdown.Important to call this method if there are any views in the layout hierarchy.
                              //in my case there is a textview

        inner1.setDropDown();//set the dropdown of inner1

        myDropDownMenu.setAllChilds(); // set all childs for the main dropdown that holds all these childs.Call it at the last

        myDropDownMenu.setDropDown();// setting the dropdown for maindropdown


        //Creating another dropdown
        MyDropDownMenu secondMenu = (MyDropDownMenu) findViewById(R.id.secondmenu);
        secondMenu.setChilds(1);//I have one view inside the dropdown.If you want you can add any no of views and specify
                                //the no of views you want to show immediately after expanding the dropdown.
        secondMenu.setChilds(5 , false , 0);//setting single choice items.You can choose multichoice also
        secondMenu.setOptionsLabel(new String[]{"Daffodil" , "Rose" , "Jasmine" , "Rhododendron" , "Tulip"});//set labels for the items
        secondMenu.setAllChilds();//addup all the childs



        //Creating another DropDown at runtime
        MyDropDownMenu child2 = new MyDropDownMenu(this,"Child 1" , R.layout.header_layout , true , 5 , getResources().getColor(R.color.black)
                , 15 , getResources().getColor(R.color.white) , 12 , MyDropDownMenu.CENTER , true , true , Typeface.NORMAL , R.drawable.dropdowniconn);
//settingup all the childs
        child2.setAllChilds();
        //setting the dropdown(Important to call)
        child2.setDropDown();
        //adding another view to the dropdown secondMenu
        secondMenu.addToDropDown(child2);
        //setting up all the childs.Call this everytime you add a view
        secondMenu.setAllChilds();
        //set the dropdown.
        secondMenu.setDropDown();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Object[] arr =   secondMenu.getAllSelectedValues();

              for (Object o : arr) {

                  Toast.makeText(MainActivity.this, "Selected Value is:" + (String) o, Toast.LENGTH_SHORT).show();
              }



            }
        });






//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Object[] strings = myDropDownMenu.getAllSelectedValues();
//
//                for (Object s : strings) {
//
//                    Log.d(TAG, "onClick: " + (String) s);
//                }
//
//            }
//        });













        //end = Scene.getSceneForLayout(myDropDownMenu , R.layout.ending_scene , this);

//        myDropDownMenu.setOptionsLabel(true , new String[]{"Option First"});


//        myDropDownMenu.setOptionsLabel(new String[]{"Option 1"});
//        myDropDownMenu.setChilds(5 , true , 0);
//        myDropDownMenu.setOptionsLabel(new String[]{"Option 1"});
//
//        myDropDownMenu.setOptionsLabel(new String[]{"Option 1"});


//
//        MyDropDownMenu outer1 = (MyDropDownMenu) findViewById(R.id.outer1);
//        outer1.setOptionsLabel(true , new String[]{"Outer 1" , "Outer 2" , "Outer 3"});

//        MyDropDownMenu m = myDropDownMenu.setChilds(0 , false , 0 , true);
//        m.setChilds(2 , false , 0 , false);
//        m.setOptionsLabel(new String[]{"Child 1" , "Child 2"});






//        myDropDownMenu.setChilds(0 , false , 0 , true);
//
//        end = new Scene(myDropDownMenu , myDropDownMenu);
//
//
//
//
//        Log.d(TAG, "onCreate: " + myDropDownMenu.getChildCount());
//
//        MyDropDownMenu sub1 = myDropDownMenu.setChilds(0 , false , 0 , true);
//
//        sub1.setChilds(3 , false , 10 , false);



    }
}