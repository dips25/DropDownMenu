package com.view.menu.mydropdown;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.transition.ChangeBounds;
import androidx.transition.Fade;
import androidx.transition.Scene;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import java.util.ArrayList;

public class MyDropDownMenu extends LinearLayout  {

    public static final String LEFT = "0";
    public static final String RIGHT = "1";

    public static final String CENTER_LEFT = "2";

    public static final String CENTER_RIGHT = "3";
    public static final String BOTTOM_LEFT = "4";
    public static final String BOTTOM_RIGHT = "5";

    public static final String CENTER = "6";

    public static final String CENTER_TOP = "7";

    public static final String CENTER_BOTTOM = "8";

    private int width , height;


    private Context context;

    private int child;

    private boolean subLayouts;

    private int s;
    private int s1 = 0;
    private String gravity = LEFT;
    private int backgroundColor;
    private int textColor;
    private String heading;
    private int subLayoutss;
    private int childs;

    private int customDropDownIcon = 0;
    private boolean singleSelection , multiSelection;

    private Scene start , end;

    private LinearLayout viewGroup;
    private int headerResId;

    private int headerTextSize;

    private  int borderColor , borderWidth , cornerRadius;



    private int headerBackGroundColor;

    private boolean curved;

   private GradientDrawable border;

    private boolean isBorder , isDropDown;

    private static final String TAG = MyDropDownMenu.class.getName();
    public MyDropDownMenu(Context context , String header , int resId , boolean curve , int borderWidth , int borderColor , int cornerRadius
            , int headerTextColor , int headerTextSize , String gravity , boolean isBorder , boolean isDropDownIcon , int textStyle , int customDropDownIcon) {
        super(context);
        this.context = context;
        setOrientation(VERTICAL);

        this.customDropDownIcon = customDropDownIcon;

        this.s1 = textStyle;

        if (borderWidth == 0) {

            this.borderWidth = 2;

        } else {

            this.borderWidth = borderWidth;
        }

        if (borderColor == 0) {

            this.borderColor = getResources().getColor(R.color.black);

        } else {

            this.borderColor = borderColor;
        }

        if (cornerRadius == 0) {

            this.cornerRadius = 10;
        } else {

            this.cornerRadius = cornerRadius;
        }

        if (headerTextColor == 0) {

            this.textColor = getResources().getColor(R.color.black);
        } else {

            this.textColor = headerTextColor;
        }

        if (headerTextSize == 0) {

            this.headerTextSize = 12;
        } else {

            this.headerTextSize = headerTextSize;
        }

        this.curved = curve;

        this.gravity = gravity;

        this.isBorder = isBorder;

        this.isDropDown = isDropDownIcon;

        if (viewGroup == null) {

            viewGroup = new LinearLayout(context);
            viewGroup.setOrientation(VERTICAL);


        }

        setHeader(header , resId);
        setOnClickListeners();



    }

    public MyDropDownMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setOrientation(VERTICAL);

        if (viewGroup == null) {

            viewGroup = new LinearLayout(context);
            viewGroup.setOrientation(VERTICAL);

        }


        TypedArray a = context.obtainStyledAttributes(attrs , R.styleable.MyDropDownMenu);
        this.s = a.getInteger(R.styleable.MyDropDownMenu_childs , 0);
        this.s1 = a.getInt(R.styleable.MyDropDownMenu_android_textStyle , 0);
        this.gravity = a.getString(R.styleable.MyDropDownMenu_labelPosition);
        this.textColor = a.getColor(R.styleable.MyDropDownMenu_headertextColor , getResources().getColor(R.color.black));
        this.headerTextSize = a.getInt(R.styleable.MyDropDownMenu_headerTextSize , 12);
        this.heading = a.getString(R.styleable.MyDropDownMenu_heading);
        this.childs = a.getInt(R.styleable.MyDropDownMenu_childs , 0);
        this.singleSelection = a.getBoolean(R.styleable.MyDropDownMenu_singleSelection , false);
        this.multiSelection = a.getBoolean(R.styleable.MyDropDownMenu_multiSelection , false);
        this.headerResId = a.getResourceId(R.styleable.MyDropDownMenu_header , 0);
        this.borderWidth = a.getInt(R.styleable.MyDropDownMenu_outerborderWidth , 2);
        this.borderColor = a.getColor(R.styleable.MyDropDownMenu_outerborderColor , Color.BLACK);
        this.curved = a.getBoolean(R.styleable.MyDropDownMenu_curved , false);
        this.cornerRadius = a.getInt(R.styleable.MyDropDownMenu_outerborderRadius , 10);
        this.isBorder = a.getBoolean(R.styleable.MyDropDownMenu_outerborder , false);
        this.isDropDown = a.getBoolean(R.styleable.MyDropDownMenu_addDropDownIcon , false);
        this.customDropDownIcon = a.getResourceId(R.styleable.MyDropDownMenu_dropDownResource , 0);
        //this.headerBackGroundColor = a.getColor(R.styleable.MyDropDownMenu_headerBackGroundColor , getResources().getColor(R.color.white));





        if (singleSelection) {

            multiSelection = false;
        }


        setHeader(heading , headerResId);
        if (this.childs>0) {

            setChilds(this.childs , multiSelection , 0);

        }

        setOnClickListeners();

    }

    private void setOnClickListeners() {

        viewGroup.getChildAt(0).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Transition fadeTransition = new Fade();

                TransitionManager.go(start , fadeTransition);


            }
        });
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.width = w;
        this.height = h;

        Log.d(TAG, "onSizeChanged: " + w + " " + h);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                Log.d(TAG, "onTouchEvent: " + "Down Touched");

                return true;

            case MotionEvent.ACTION_UP:

                    viewChilds();

                return true;
        }

        return super.onTouchEvent(event);
    }

    private void viewChilds()  {

        try {

            finalize();

        } catch (Throwable throwable) {

            throw new RuntimeException(throwable.getMessage());

        }

        setAnimation();

    }

    public void removeChilds() {


        for (int i = 1 ; i<this.getChildCount() ; i++) {

            Log.d(TAG, "removeChilds: " + this.getChildAt(i).getClass().getName());

            this.getChildAt(i).setVisibility(GONE);
        }


    }

    private void removeViews(int child , boolean multichoice , int multiChilds) {

        if (!multichoice) {

            for (int i = 1 ; i<=child ; i++) {

                View view = this.getChildAt(i);
                this.removeView(view);

            }

        } else {

            for (int i = 1 ; i<=child ; i++) {

                View view = this.getChildAt(1);
                this.removeView(view);
            }
        }


    }

    public MyDropDownMenu setSubLayouts(String header , int resId , boolean curve , int borderWidth , int borderColor , int cornerRadius
            , int headerTextColor , int headerTextSize ,  String gravity , boolean isBorder , boolean isDropDownIcon , int textStyle , int customDropDownIcon) {

        MyDropDownMenu menu = new MyDropDownMenu(context , header , resId , curve , borderWidth , borderColor
                , cornerRadius , headerTextColor , headerTextSize , gravity , isBorder , isDropDownIcon , textStyle , customDropDownIcon);
        //menu.setBackGround(backGroundColorId);

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 10;
        menu.setLayoutParams(params);

        this.addView(menu , this);

        return menu;


    }

    public MyDropDownMenu setChilds(int child , boolean multichoice , int multiChilds) {

        this.childs+=child;

        if (!multichoice) {

            RadioGroup radioGroup = new RadioGroup(context);

            for (int i = 0 ; i<child ; i++) {

                RadioButton radioButton = new RadioButton(context);
                radioButton.setText("Option " + Integer.toString(i));
                radioGroup.addView(radioButton);
            }


            viewGroup.addView(radioGroup);



        } else {

            for (int i = 0 ; i<child ; i++) {

                CheckBox checkBox = new CheckBox(context);

                viewGroup.addView(checkBox);
            }
        }



        return this;

    }

    public void setAllChilds() {

        int childCount = this.getChildCount();

        for (int i = 1 ; i<childCount ; i++) {

            View view = this.getChildAt(1);

            this.removeViewAt(1);


                viewGroup.addView(view);


        }
    }

    public void setChilds(int childs) {

        int childCount = this.getChildCount();

        for (int i = 1 ; i<=childs ; i++) {

            View view = this.getChildAt(1);

            this.removeViewAt(1);


            viewGroup.addView(view);


        }


    }



    private void addView(View view , LinearLayout linearLayout) {

        super.addView(view);

    }

    public void addToDropDown(View view) {

        viewGroup.addView(view);


    }


    public void setOptionsLabel(String[] label) {

        int j = 0;

        for (int i = 1 ; i<viewGroup.getChildCount() ; i++) {

            if (viewGroup.getChildAt(i) instanceof RadioGroup) {

                RadioGroup radioGroup = (RadioGroup) viewGroup.getChildAt(i);

                int count = ((RadioGroup)viewGroup.getChildAt(i)).getChildCount();

                for (int m = 0 ; m<count ;m++) {

                    try {

                        if (((RadioButton)((RadioGroup)viewGroup.getChildAt(i)).getChildAt(m)).getText().toString().equalsIgnoreCase("")) {

                            ((RadioButton)((RadioGroup)viewGroup.getChildAt(i)).getChildAt(m)).setText(label[m]);


                        }



                    } catch (ArrayIndexOutOfBoundsException ex) {

                        if (((RadioButton)((RadioGroup)viewGroup.getChildAt(i)).getChildAt(m)).getText().toString().equalsIgnoreCase("")) {

                            ((RadioButton)((RadioGroup)viewGroup.getChildAt(i)).getChildAt(m)).setText("No Option");


                        }


                    }


                }

                Log.d(TAG, "setOptionsLabel: " + this);




            } else if (viewGroup.getChildAt(i) instanceof CheckBox) {

                while (j>=0) {

                    try {



                        ((CheckBox) viewGroup.getChildAt(i)).setText(label[j]);




                    } catch (ArrayIndexOutOfBoundsException ex) {

//

                        ((CheckBox) viewGroup.getChildAt(i)).setText("No Option");


                    }

                    j+=1;
                    break;


                }

            }


        }

        j = 0;

    }

    public void setDropDown() {

        end = new Scene((ViewGroup) this , viewGroup);

    }

    private void setHeader(String header , int resId) {

        Drawable drawable;

        if (curved || isBorder) {

            border = new GradientDrawable();
            border.setCornerRadius(Utils.dPtoPixel(context , cornerRadius));
            border.setColor(borderColor);

        }

        try {

                drawable = getResources().getDrawable(resId);

            RelativeLayout headerLayout = new RelativeLayout(context);

            LayoutParams headerParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , Utils.dPtoPixel(context , 80));
            LayoutParams cardParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , Utils.dPtoPixel(context , 80));

            CardView headerCard = new CardView(context);

            if (curved && isBorder) {

                headerCard.setRadius(Utils.dPtoPixel(context , cornerRadius));
                cardParams.setMargins(Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth)
                        , Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth));
                headerLayout.setBackground(border);

            } else if (!curved && isBorder) {

                    cardParams.setMargins(Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth)
                            , Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth));
                    headerLayout.setBackground(border);

            } else if (curved && !isBorder) {

                headerCard.setRadius(Utils.dPtoPixel(context , cornerRadius));


            }

            View view = new View(context);
            view.setBackground(drawable);


            headerCard.addView(view);



            headerLayout.setLayoutParams(headerParams);
            headerCard.setLayoutParams(cardParams);

            TextView textView = new TextView(context);
            textView.setElevation(10);
            textView.setTextColor(this.textColor);
            textView.setText(header);

            if (this.gravity != null) {

                if (this.gravity.equals("0")) {

                    textView.setGravity(Gravity.START);

                } else if (this.gravity.equals("1")) {

                    textView.setGravity(Gravity.END);

                } else if (this.gravity.equals("2")) {

                    textView.setGravity(Gravity.CENTER_VERTICAL);

                } else if (this.gravity.equals("3")) {

                    textView.setGravity(Gravity.END|Gravity.CENTER_VERTICAL);

                } else if (this.gravity.equals("4")) {

                    textView.setGravity(Gravity.BOTTOM);

                } else if (this.gravity.equals("5")) {

                    textView.setGravity(Gravity.BOTTOM|Gravity.END);

                } else if (this.gravity.equals("6")) {

                    textView.setGravity(Gravity.CENTER);

                } else if (this.gravity.equals("7")) {

                    textView.setGravity(Gravity.CENTER|Gravity.TOP);

                } else if (this.gravity.equals("8")) {

                    textView.setGravity(Gravity.CENTER|Gravity.BOTTOM);


                }


            } else {

                textView.setGravity(Gravity.START);
            }


            textView.setTextSize((float) Utils.dPtoPixel(context , headerTextSize));
            textView.setTypeface(null , s1);

            RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT);
            textParams.leftMargin=Utils.dPtoPixel(context , 10);


            if (isDropDown) {

                RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(Utils.dPtoPixel(context , 20) , Utils.dPtoPixel(context , 80));
                imageParams.rightMargin = Utils.dPtoPixel(context , 10);
                imageParams.addRule(RelativeLayout.ALIGN_PARENT_END);

                if (this.customDropDownIcon!=0) {

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources() , customDropDownIcon);
                    Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap , 50 , 50 , false);

                    ImageView imageView = new ImageView(context);
                    imageView.setId(View.generateViewId());
                    imageView.setElevation(10);
                    imageView.setImageBitmap(bitmap1);
                    imageView.setLayoutParams(imageParams);

                    textParams.addRule(RelativeLayout.START_OF , imageView.getId());
                    headerLayout.addView(imageView);


                } else {

                    int res = getResources().getIdentifier("dropdownicon" , "drawable" , getContext().getPackageName());

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources() , res);
                    Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap , 50 , 50 , false);

                    ImageView imageView = new ImageView(context);
                    imageView.setId(View.generateViewId());
                    imageView.setElevation(10);
                    imageView.setImageBitmap(bitmap1);
                    imageView.setLayoutParams(imageParams);

                    textParams.addRule(RelativeLayout.START_OF , imageView.getId());
                    headerLayout.addView(imageView);


                }



            }

            textView.setLayoutParams(textParams);

            headerLayout.addView(headerCard);
            headerLayout.addView(textView);

            this.addView(headerLayout , 0);

            //DropDown
            RelativeLayout dropDownHeaderLayout = new RelativeLayout(context);

            LayoutParams innerheaderParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , Utils.dPtoPixel(context , 80));
            LayoutParams innercardParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , Utils.dPtoPixel(context , 80));

            CardView innerheaderCard = new CardView(context);

            if (curved && isBorder) {

                innerheaderCard.setRadius(Utils.dPtoPixel(context , cornerRadius));
                innercardParams.setMargins(Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth)
                        , Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth));
                dropDownHeaderLayout.setBackground(border);

            } else if (!curved && isBorder) {

                innercardParams.setMargins(Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth)
                        , Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth));
                dropDownHeaderLayout.setBackground(border);

            } else if (curved && !isBorder) {

                innerheaderCard.setRadius(Utils.dPtoPixel(context , cornerRadius));


            }

            View innerview = new View(context);
            innerview.setBackground(drawable);

            innerheaderCard.addView(innerview);

            dropDownHeaderLayout.setLayoutParams(innerheaderParams);
            innerheaderCard.setLayoutParams(innercardParams);


            TextView innertextView = new TextView(context);
            innertextView.setElevation(10);
            innertextView.setText(header);
            innertextView.setTextColor(this.textColor);
            innertextView.setTextSize((float) Utils.dPtoPixel(context , headerTextSize));
            innertextView.setTypeface(null , s1);

            if (this.gravity != null) {

                if (this.gravity.equals("0")) {

                    innertextView.setGravity(Gravity.START);

                } else if (this.gravity.equals("1")) {

                    innertextView.setGravity(Gravity.END);

                } else if (this.gravity.equals("2")) {

                    innertextView.setGravity(Gravity.CENTER_VERTICAL);

                } else if (this.gravity.equals("3")) {

                    innertextView.setGravity(Gravity.END|Gravity.CENTER_VERTICAL);

                } else if (this.gravity.equals("4")) {

                    innertextView.setGravity(Gravity.BOTTOM);

                } else if (this.gravity.equals("5")) {

                    innertextView.setGravity(Gravity.BOTTOM|Gravity.END);

                } else if (this.gravity.equals("6")) {

                    innertextView.setGravity(Gravity.CENTER);

                } else if (this.gravity.equals("7")) {

                    innertextView.setGravity(Gravity.CENTER|Gravity.TOP);

                } else if (this.gravity.equals("8")) {

                    innertextView.setGravity(Gravity.CENTER|Gravity.BOTTOM);

                }


            } else {

                innertextView.setGravity(Gravity.START);
            }


            RelativeLayout.LayoutParams innertextParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT);
            innertextParams.leftMargin=Utils.dPtoPixel(context , 10);

            if (isDropDown) {

                RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(Utils.dPtoPixel(context , 20) , Utils.dPtoPixel(context , 80));
                imageParams.rightMargin = Utils.dPtoPixel(context , 10);
                imageParams.addRule(RelativeLayout.ALIGN_PARENT_END);

                if (customDropDownIcon!=0) {

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources() , customDropDownIcon);
                    Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap , 50 , 50 , false);

                    ImageView imageView = new ImageView(context);
                    imageView.setId(View.generateViewId());
                    imageView.setElevation(10);
                    imageView.setImageBitmap(bitmap1);
                    imageView.setLayoutParams(imageParams);
                    imageView.setRotation(180f);

                    innertextParams.addRule(RelativeLayout.START_OF , imageView.getId());
                    dropDownHeaderLayout.addView(imageView);


                } else {

                    int res = getResources().getIdentifier("dropdownicon" , "drawable" , getContext().getPackageName());

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources() , res);
                    Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap , 50 , 50 , false);

                    ImageView imageView = new ImageView(context);
                    imageView.setId(View.generateViewId());
                    imageView.setElevation(10);
                    imageView.setImageBitmap(bitmap1);
                    imageView.setLayoutParams(imageParams);
                    imageView.setRotation(180f);

                    innertextParams.addRule(RelativeLayout.START_OF , imageView.getId());
                    dropDownHeaderLayout.addView(imageView);


                }



            }

            innertextView.setLayoutParams(innertextParams);

            dropDownHeaderLayout.addView(innerheaderCard);
            dropDownHeaderLayout.addView(innertextView);



            viewGroup.addView(dropDownHeaderLayout , 0);

            setStartingScene(headerLayout);




        } catch(Exception exception) {

            Log.d(TAG, "setHeader: " + exception);


            RelativeLayout headerLayout = new RelativeLayout(context);
            LayoutParams headerParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , Utils.dPtoPixel(context , 80));
            LayoutParams cardParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , Utils.dPtoPixel(context , 80));

            CardView headerCard = new CardView(context);

            if (curved && isBorder) {

                headerCard.setRadius(Utils.dPtoPixel(context , cornerRadius));
                cardParams.setMargins(Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth) ,
                        Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth));
                headerLayout.setBackground(border);

            } else if (!curved && isBorder) {

                cardParams.setMargins(Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth)
                        , Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth));
                headerLayout.setBackground(border);

            } else if (curved && !isBorder) {

                headerCard.setRadius(Utils.dPtoPixel(context , cornerRadius));


            }

            headerLayout.setLayoutParams(headerParams);
            headerCard.setLayoutParams(cardParams);

            View view = LayoutInflater.from(context).inflate(resId , null);

            headerCard.addView(view);

            TextView textView = new TextView(context);
            textView.setText(header);
            textView.setTextColor(this.textColor);
            textView.setElevation(10f);
            textView.setTextSize((float) Utils.dPtoPixel(context , headerTextSize));
            textView.setTypeface(null , s1);

            if (this.gravity != null) {

                if (this.gravity.equals("0")) {

                    textView.setGravity(Gravity.START);

                } else if (this.gravity.equals("1")) {

                    textView.setGravity(Gravity.END);

                } else if (this.gravity.equals("2")) {

                    textView.setGravity(Gravity.CENTER_VERTICAL);

                } else if (this.gravity.equals("3")) {

                    textView.setGravity(Gravity.END|Gravity.CENTER_VERTICAL);

                } else if (this.gravity.equals("4")) {

                    textView.setGravity(Gravity.BOTTOM);

                } else if (this.gravity.equals("5")) {

                    textView.setGravity(Gravity.BOTTOM|Gravity.END);

                } else if (this.gravity.equals("6")) {

                    textView.setGravity(Gravity.CENTER);

                } else if (this.gravity.equals("7")) {

                    textView.setGravity(Gravity.CENTER|Gravity.TOP);

                } else if (this.gravity.equals("8")) {

                    textView.setGravity(Gravity.CENTER|Gravity.BOTTOM);


                }


            } else {

                textView.setGravity(Gravity.START);
            }



            RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT);
            textParams.leftMargin=Utils.dPtoPixel(context , 10);

            if (isDropDown) {

                RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(Utils.dPtoPixel(context , 20) , Utils.dPtoPixel(context , 80));
                imageParams.rightMargin = Utils.dPtoPixel(context , 10);
                imageParams.addRule(RelativeLayout.ALIGN_PARENT_END);

                if (this.customDropDownIcon!=0) {

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources() , customDropDownIcon);
                    Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap , 50 , 50 , false);

                    ImageView imageView = new ImageView(context);
                    imageView.setId(View.generateViewId());
                    imageView.setElevation(10);
                    imageView.setImageBitmap(bitmap1);
                    imageView.setLayoutParams(imageParams);

                    textParams.addRule(RelativeLayout.START_OF , imageView.getId());
                    headerLayout.addView(imageView);



                } else {

                    int res = getResources().getIdentifier("dropdownicon" , "drawable" , getContext().getPackageName());

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources() , res);
                    Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap , 50 , 50 , false);

                    ImageView imageView = new ImageView(context);
                    imageView.setId(View.generateViewId());
                    imageView.setElevation(10);
                    imageView.setImageBitmap(bitmap1);
                    imageView.setLayoutParams(imageParams);

                    textParams.addRule(RelativeLayout.START_OF , imageView.getId());
                    headerLayout.addView(imageView);



                }


            }
            textView.setLayoutParams(textParams);


            headerLayout.addView(headerCard);
            headerLayout.addView(textView);

            this.addView(headerLayout , 0);



            RelativeLayout dropDownHeaderLayout = new RelativeLayout(context);

            LayoutParams innerheaderParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , Utils.dPtoPixel(context , 80));
            LayoutParams innercardParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , Utils.dPtoPixel(context , 80));

            CardView innerheaderCard = new CardView(context);

            if (curved && isBorder) {

                innerheaderCard.setRadius(Utils.dPtoPixel(context , cornerRadius));
                innercardParams.setMargins(Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth)
                        , Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth));
                dropDownHeaderLayout.setBackground(border);

            } else if (!curved && isBorder) {

                innercardParams.setMargins(Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth)
                        , Utils.dPtoPixel(context , borderWidth) , Utils.dPtoPixel(context , borderWidth));
                dropDownHeaderLayout.setBackground(border);

            } else if (curved && !isBorder) {

                innerheaderCard.setRadius(Utils.dPtoPixel(context , cornerRadius));


            }

            dropDownHeaderLayout.setLayoutParams(innerheaderParams);
            innerheaderCard.setLayoutParams(innercardParams);



            View innerView = LayoutInflater.from(context).inflate(resId , null);
            innerheaderCard.addView(innerView);

            RelativeLayout.LayoutParams innertextParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT);
            innertextParams.leftMargin=Utils.dPtoPixel(context , 10);

            TextView innertextView = new TextView(context);
            innertextView.setElevation(10);
            innertextView.setTextColor(this.textColor);
            innertextView.setText(header);
            innertextView.setTextSize((float) Utils.dPtoPixel(context , headerTextSize));
            innertextView.setTypeface(null , s1);

            if (this.gravity != null) {

                if (this.gravity.equals("0")) {

                    innertextView.setGravity(Gravity.START);

                } else if (this.gravity.equals("1")) {

                    innertextView.setGravity(Gravity.END);

                } else if (this.gravity.equals("2")) {

                    innertextView.setGravity(Gravity.CENTER_VERTICAL);

                } else if (this.gravity.equals("3")) {

                    innertextView.setGravity(Gravity.END|Gravity.CENTER_VERTICAL);

                } else if (this.gravity.equals("4")) {

                    innertextView.setGravity(Gravity.BOTTOM);

                } else if (this.gravity.equals("5")) {

                    innertextView.setGravity(Gravity.BOTTOM|Gravity.END);

                } else if (this.gravity.equals("6")) {

                    innertextView.setGravity(Gravity.CENTER);

                } else if (this.gravity.equals("7")) {

                    innertextView.setGravity(Gravity.CENTER|Gravity.TOP);

                } else if (this.gravity.equals("8")) {

                    innertextView.setGravity(Gravity.CENTER|Gravity.BOTTOM);


                }


            } else {

                innertextView.setGravity(Gravity.START);
            }

            if (isDropDown) {

                RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(Utils.dPtoPixel(context , 20) , Utils.dPtoPixel(context , 80));
                imageParams.rightMargin = Utils.dPtoPixel(context , 10);
                imageParams.addRule(RelativeLayout.ALIGN_PARENT_END);

                if (this.customDropDownIcon!=0) {

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources() , customDropDownIcon);
                    Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap , 50 , 50 , false);

                    ImageView imageView = new ImageView(context);
                    imageView.setId(View.generateViewId());
                    imageView.setElevation(10);
                    imageView.setRotation(180);
                    imageView.setImageBitmap(bitmap1);
                    imageView.setLayoutParams(imageParams);

                    innertextParams.addRule(RelativeLayout.START_OF , imageView.getId());
                    dropDownHeaderLayout.addView(imageView);



                } else {

                    int res = getResources().getIdentifier("dropdownicon" , "drawable" , getContext().getPackageName());

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources() , res);
                    Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap , 50 , 50 , false);

                    ImageView imageView = new ImageView(context);
                    imageView.setId(View.generateViewId());
                    imageView.setElevation(10);
                    imageView.setRotation(180);
                    imageView.setImageBitmap(bitmap1);
                    imageView.setLayoutParams(imageParams);

                    innertextParams.addRule(RelativeLayout.START_OF , imageView.getId());
                    dropDownHeaderLayout.addView(imageView);



                }


            }


            innertextView.setLayoutParams(innertextParams);

            dropDownHeaderLayout.addView(innerheaderCard);
            dropDownHeaderLayout.addView(innertextView);

            viewGroup.addView(dropDownHeaderLayout , 0);

            setStartingScene(headerLayout);

        }

    }



    private void setHeader(String heading) {

        RelativeLayout headerLayout = new RelativeLayout(context);

        TextView textView = new TextView(context);
        textView.setText(heading);

        textView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        textView.setGravity(Gravity.CENTER);

        LayoutParams textParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , 200);

        textView.setLayoutParams(textParams);

        headerLayout.addView(textView);



        this.addView(headerLayout , 0);

        RelativeLayout dropDownHeaderLayout = new RelativeLayout(context);

        TextView innertextView = new TextView(context);

        innertextView.setText(heading);

        innertextView.setGravity(Gravity.CENTER);

        LayoutParams innertextParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT);

        innertextView.setLayoutParams(textParams);

        dropDownHeaderLayout.addView(innertextView);



        viewGroup.addView(dropDownHeaderLayout , 0);



        setStartingScene(headerLayout);

    }

    private void setStartingScene(View header) {

        start = new Scene((ViewGroup) this , header);
    }

    private LinearLayout getViewGroup() {

        return viewGroup;
    }

    public Object[] getAllSelectedValues() {

        ArrayList<String> stringArrayList = new ArrayList<>();

        for (int i = 1 ; i<this.viewGroup.getChildCount() ; i++) {

            if (this.viewGroup.getChildAt(i) instanceof RadioGroup) {

                RadioGroup rg =(RadioGroup) this.viewGroup.getChildAt(i);

                int childCount = rg.getChildCount();

                for (int j = 0 ; j<childCount ; j++) {

                    if (((RadioButton)rg.getChildAt(j)).isChecked()) {

                        stringArrayList.add(((RadioButton)rg.getChildAt(j)).getText().toString());

                    }

                }
            } else if (this.viewGroup.getChildAt(i) instanceof CheckBox) {

                CheckBox cb =(CheckBox) this.viewGroup.getChildAt(i);

                if (cb.isChecked()) {

                    stringArrayList.add(cb.getText().toString());


                }


            }
        }

        Object[] arr = stringArrayList.toArray();
        Log.d(TAG, "getAllSelectedValues: " + arr.length);

        return arr;


    }

    @Override
    public void finalize() throws Throwable {

        super.finalize();


        for (int i = 1 ; i<viewGroup.getChildCount() ; i++) {

            if (viewGroup.getChildAt(i) instanceof RadioGroup) {

                int count = ((RadioGroup)viewGroup.getChildAt(i)).getChildCount();

                for (int m = 0 ; m<count ;m++) {

                    if (((RadioButton)((RadioGroup)viewGroup.getChildAt(i)).getChildAt(m)).getText().toString().equalsIgnoreCase("")) {


                        throw new Throwable("Label Not set for all the fields.Please set label for all the fields using setOptionsLabel().");

                    }


                }




            } else if (viewGroup.getChildAt(i) instanceof CheckBox) {

                if (((CheckBox) viewGroup.getChildAt(i)).getText().toString().equalsIgnoreCase("")) {

                    throw new Throwable("Label Not set for all fields.Please set label for all the fields using setOptionsLabel().");

                        }


            }


        }

    }



    public void setAnimation() {

        Transition fadeTransition = new ChangeBounds();
        TransitionManager.go(end , fadeTransition);

    }

}
