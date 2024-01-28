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








