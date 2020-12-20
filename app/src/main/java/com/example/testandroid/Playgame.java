package com.example.testandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.CompoundButtonCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import java.nio.file.Files;
import java.util.Timer;
import java.util.TimerTask;

public class Playgame extends AppCompatActivity {
    TextView txtscore,txtplay;
Button btn1,btn2,btn3;
FrameLayout frame;
LinearLayout lnlife;
ImageView imgplanelife1,imgplanelife2,imgplanelife3,imgface1,imgfood1,imgfood2,imgfood3,imgdead;
Drawable imgface2;
WindowManager wm;
/*int plane[]={
  R.drawable.airplanedied,
        R.drawable.airportlife
};*/
//private Paint scorePaint=new Paint();

//private Bitmap life[]=new Bitmap[2];

//private Bitmap plane[]=new Bitmap[2];
private float face;
private float food1X,food1Y,food2X,food2Y,food3X,food3Y,dot3X,dot3Y;
private Timer timer=new Timer();
private Handler handler=new Handler();
private boolean action=false;
private boolean start =false;
private int framheight,facesize;
private int screenheight,screenwidth;
private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playgame);

        addControls();

        addEvents();

    }


    private void addControls() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        txtplay=findViewById(R.id.txtplay);
        txtscore=findViewById(R.id.txtscore);
        imgplanelife1 = findViewById(R.id.imgplaneife1);
        imgplanelife2 = findViewById(R.id.imgplanelife2);
        imgplanelife3 = findViewById(R.id.imgplanelife3);
        imgfood1=findViewById(R.id.food1);
        imgfood2=findViewById(R.id.food2);
        imgfood3=findViewById(R.id.food3);
        imgdead=findViewById(R.id.dead);
        lnlife=findViewById(R.id.lnlife);
        frame=findViewById(R.id.frame);
        imgface1 = findViewById(R.id.face1);
        imgface2=getResources().getDrawable(R.drawable.face2);

        wm=getWindowManager();
        Display dp=wm.getDefaultDisplay();
        Point size=new Point();
        dp.getSize(size);

        screenwidth=size.x;
        screenheight=size.y;

        imgfood1.setX(-80.0f);
        imgfood1.setY(-80.0f);
        imgfood2.setX(-80.0f);
        imgfood2.setY(-80.0f);
        imgfood3.setX(-80.0f);
        imgfood3.setY(-80.0f);
        imgdead.setX(-80.0f);
        imgdead.setY(-80.0f);

        txtscore.setText("Điểm của tui : "+score);
    //    txtplay.setVisibility(View.INVISIBLE);

       // planeY=500.0f;

/*scorePaint.setColor(Color.RED);
scorePaint.setTextSize(30);
scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
scorePaint.setAntiAlias(true);*/

/*life[0]= BitmapFactory.decodeResource(getResources(),R.drawable.airportlife);
        life[1]= BitmapFactory.decodeResource(getResources(),R.drawable.airplanedied);*/
//plane=BitmapFactory.decodeResource(getResources(),R.drawable.testplane1);
    }

    private void addEvents() {

    }
public void addChay(){

        tinhdiem();

        food1X-=12;
        if(food1X<0){
food1X= screenwidth+20;
food1Y=(float)Math.floor(Math.random()*(framheight-imgfood1.getHeight()));
        }
        imgfood1.setX(food1X);
        imgfood1.setY(food1Y);

    food2X-=20;
    if(food2X<0){
        food2X= screenwidth+5000;
        food2Y=(float)Math.floor(Math.random()*(framheight-imgfood2.getHeight()));
    }
    imgfood2.setX(food2X);
    imgfood2.setY(food2Y);

    food3X-=30;
    if(food3X<0){
        food3X= screenwidth+5000;
        food3Y=(float)Math.floor(Math.random()*(framheight-imgfood3.getHeight()));
    }
    imgfood3.setX(food3X);
    imgfood3.setY(food3Y);

    dot3X-=16;
    if(dot3X<0){
        dot3X= screenwidth+10;
        dot3Y=(float)Math.floor(Math.random()*(framheight-imgdead.getHeight()));
    }
    imgdead.setX(dot3X);
    imgdead.setY(dot3Y);

        if(action){
            face-=20;
        }else {
            face+=20;
        }

        if(face<0) {
            face= 0;
        }

        if(face>framheight-facesize) {
            face= framheight-facesize;
        }

    imgface1.setY(face);
        txtscore.setText("Điểm của tui : "+score);
}

    private void tinhdiem() {

        float food1CenterX= food1X+imgfood1.getWidth() / 2.0f;
        float food1CenterY= food1Y+imgfood1.getHeight() / 2.0f;
        if(0<=food1CenterX && food1CenterX<=facesize && face<=food1CenterY && food1CenterY<= face+facesize){
food1X=-100.0f;
score+=10;
imgface1.setImageDrawable(imgface2);
        }

        float food2CenterX= food2X+imgfood2.getWidth() / 2.0f;
        float food2CenterY= food2Y+imgfood2.getHeight() / 2.0f;
        if(0<=food2CenterX && food2CenterX<=facesize && face<=food2CenterY && food2CenterY<= face+facesize){
          food2X=-100.0f;
            score+=40;
            imgface1.setImageDrawable(imgface2);
        }

        float food3CenterX= food2X+imgfood2.getWidth() / 2.0f;
        float food3CenterY= food2Y+imgfood2.getHeight() / 2.0f;
        if(0<=food3CenterX && food3CenterX<=facesize && face<=food3CenterY && food3CenterY<= face+facesize){
            food2X=-100.0f;
            score+=40;
            imgface1.setImageDrawable(imgface2);
        }

        float dot3CenterX= dot3X+imgdead.getWidth() / 2.0f;
        float dot3CenterY= dot3Y+imgdead.getHeight() / 2.0f;
        if(0<=dot3CenterX && dot3CenterX<=facesize && face<=dot3CenterY && dot3CenterY<= face+facesize) {
        if(timer!=null)
        {
timer.cancel();
timer=null;
        }
            Intent intent = new Intent(getApplicationContext(),EndGame.class);
        intent.putExtra("ĐIỂM",score);
            startActivity(intent);
        }
        imgface1.setImageDrawable(imgface2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!start) {
    start=true;

    framheight=frame.getHeight();
    face=imgface1.getY();
    facesize=imgface1.getHeight();

    txtplay.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            addChay();
                        }

                    });
                }
            },0,20);
        }else {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {

                action=true;
            }else if(event.getAction()==MotionEvent.ACTION_UP)
            {
                action=false;
            }
        }


        return super.onTouchEvent(event);
    }

    public void xulydung(View view) {
if(action==false)
{
action=true;
timer.cancel();
timer=null;
btn1.setText("Resume");
}else {
action=false;
btn1.setText("Pause");
timer=new Timer();
timer.schedule(new TimerTask() {
    @Override
    public void run() {
        handler.post(new Runnable() {
            @Override
            public void run() {
           addChay();
            }
        });

    }
},0,20);
}
    }

    public void xulymogamemoi(View view) {
        startActivity(new Intent(getApplicationContext(),Playgame.class));
    }

    public void xulythoat(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Playgame.this);
        builder.setTitle("Xác Nhận Trở Về Màn Hình Chính");
        builder.setIcon(android.R.drawable.ic_dialog_dialer);
        builder.setMessage("Thoát?");
        builder.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
            @Override
            public void
            onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("NOOOOOOOOOOO", new DialogInterface.OnClickListener() {
            @Override
            public void
            onClick(DialogInterface dialogInterface, int i) { dialogInterface.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false)
        ;
        dialog.show();
    }
}