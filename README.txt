         Dependency
implementation 'com.github.dips25:DropDownMenu:1.0'

        Add this to settings.gradle

dependencyResolutionManagement {
repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
repositories {
google()
mavenCentral()
maven { url 'https://jitpack.io' }
}
}

          *********Your XML*********

<?xml version="1.0" encoding="utf-8"?>

<ScrollView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginBottom="60dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

<com.view.menu.mydropdown.MyDropDownMenu

    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginStart="5dp"
    android:layout_marginEnd="5dp"
    android:layout_marginTop="5dp"
    app:heading="Image"<!---Add the Header-->
    android:textStyle="italic|bold"<!---Add the Header text style-->
    app:headertextColor="#808080"<!---Add the Header text color-->
    app:headerTextSize="10"<!---Add the Header text size-->
    android:gravity="center"
    app:labelPosition="center"<!---Add the Header textposition-->
    app:curved="true"<!---Add curved corners-->
    app:outerborder="true"<!---Add border-->
    app:outerborderColor="@color/gray"<!---Add the border color.By default set to black-->
    app:outerborderWidth="3"<!---Add the border width.By default set to 3-->
    app:header="@drawable/lockdoor"<!---Add the background.You can also use a custom layout or any color-->
    app:addDropDownIcon="true"<!---Add the Dropdown icon.By default set to false-->
    app:dropDownResource="@drawable/dropdowniconn"<!---Add any drawable resource for the dropdown icon-->
    android:id="@+id/mydropdown">

    <!--Adding an ImageView.You can add any views--->

    <ImageView
        android:layout_width="match_parent"
        android:layout_height="250dp"
        android:layout_marginTop="10dp"
        android:background="@drawable/lockdoor"/>
 
<!-Setting another dropdown-->

    <com.view.menu.mydropdown.MyDropDownMenu
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        app:curved="true"
        app:outerborder="true"
        app:outerborderWidth="5"
        app:addDropDownIcon="true"
        app:labelPosition="center_bottom"
        app:headerTextSize="14"
        app:headertextColor="@color/material_dynamic_neutral40"
        app:outerborderColor="@color/black"
        app:header="@color/gray"
        android:layout_centerVertical="true"
        app:heading="What is Lorem Ipsum?"
        android:id="@+id/innerlayout">

 <!--Adding textview inside it-->

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@string/sampletext"/>

</com.view.menu.mydropdown.MyDropDownMenu>

</com.view.menu.mydropdown.MyDropDownMenu>

<!--Adding textview inside this dropdown-->

        <com.view.menu.mydropdown.MyDropDownMenu
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="20dp"
            android:layout_marginStart="5dp"
            android:layout_marginEnd="5dp"
            app:curved="true"
            app:outerborder="true"
            app:labelPosition="center"
            app:heading="2ndMenu"
            app:header="@drawable/lockdoor"
            app:outerborderWidth="6"
            app:headertextColor="@color/black"
            app:addDropDownIcon="true"
            android:id="@+id/secondmenu">

     <!--Adding an imageview inside it-->

            <ImageView
                android:layout_width="match_parent"
                android:layout_height="350dp"
                android:layout_marginTop="10dp"
                android:background="@drawable/sampleheader"
                android:scaleType="fitXY"/>

  <!--Setting a button>

            <Button
                        android:layout_width="match_parent"
                        android:layout_height="60dp"
                        android:text="GetAllValues"
                        android:layout_gravity="bottom"
                        android:id="@+id/getselectedvalues"/>

        </com.view.menu.mydropdown.MyDropDownMenu>
        
    </LinearLayout>
</ScrollView>
                      *****Your Activity********
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









