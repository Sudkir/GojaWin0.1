package com.example.gojawin01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityBuy extends AppCompatActivity {

    public int boostUp = 10; //цена прокачки тика
    public int mCount =0; //общее количесво очков
    public int lvlUpOne = 1; // уровень прокачки
    public int boostUpTime =500;//цена прокачки тайма
    public int lvlUpTime = 1; // уровень прокачки
    boolean timerBool ;

    public TextView price; // показ цены клика
    public TextView priceTime; // показ цены тайма
    public TextView countView; // показ очков

    public TextView lvlClick; // показ цены клика
    public TextView lvlTime; // показ цены клика

public  void Pered()//передача переменных
{
    Bundle arguments = getIntent().getExtras();
    BuyUp buyUp;
    buyUp = (BuyUp) arguments.getSerializable(BuyUp.class.getSimpleName());
    //передача переменных
    boostUp = buyUp.getBoostUpCl();
    mCount = buyUp.getMCount();
    lvlUpOne = buyUp.getLvlUpOneCl();
    boostUpTime = buyUp.getBoostUpTimeCl();

}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_buy);

        price = (TextView) findViewById(R.id.Price);
        priceTime = (TextView) findViewById(R.id.PriceTime);
        countView = (TextView)findViewById(R.id.CountView);

        lvlClick = (TextView) findViewById(R.id.lvlClickText);
        lvlTime = (TextView)findViewById(R.id.lvlTimeText);

        //передача переменных
        Pered();
        Zagruzka();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //передача переменных
        Pered();
        Zagruzka();

    }

    static final private int CHOOSE_THIEF = 0;// параметр RequestCode
    public final static String THIEF = "com.example.gojawin01.THIEF";

    public void ReturnMain(View view)
    {
        //создание экземпляра класса
        Intent intent = new Intent(MainActivityBuy.this, MainActivity.class);
        BuyUp buyUp = new BuyUp(boostUp, mCount, lvlUpOne,boostUpTime,lvlUpTime,timerBool);
        intent.putExtra(BuyUp.class.getSimpleName(), buyUp);
        //старт окна
        startActivity(intent);
    }

    public void Zagruzka()
    {
        countView.setText(Integer.toString(mCount));
        priceTime.setText(Integer.toString(boostUpTime));
        price.setText(Integer.toString(boostUp));
        lvlClick.setText(Integer.toString(lvlUpOne));
        lvlTime.setText(Integer.toString(lvlUpTime));
    }

    //прокачка клика вывод обновленных результатов
    public void Booster (View view){

        if (mCount>= boostUp) {
            lvlUpOne = lvlUpOne * 2;
            mCount = mCount - boostUp;
            boostUp = boostUp *4;
            price.setText(Integer.toString(boostUp));

        }
        else
        {
            Toast toast = Toast.makeText(this, R.string.boost_message, Toast.LENGTH_LONG);
            toast.show();
        }




        //обновление данных в классе
        Bundle arguments = getIntent().getExtras();
        BuyUp buyUp;
        buyUp = (BuyUp) arguments.getSerializable(BuyUp.class.getSimpleName());
        buyUp.setBoostUpCl(boostUp);
        buyUp.setMCount(mCount);
        buyUp.setLvlUpOneCl(lvlUpOne);
        buyUp.setBoostUpTimeCl(boostUpTime);
        buyUp.setLvlUpTimeCl(lvlUpTime);

        Zagruzka();




    }



    //прокачка таймера
    public void BoosterTime (View view){
        //AnimDollar();


        //формула прокачки
        if (mCount>= boostUpTime) {
            lvlUpTime++;
            mCount = mCount - boostUpTime;
            boostUpTime = boostUpTime * 6;
            //возврат значенией
            priceTime.setText(Integer.toString(boostUpTime));
            countView.setText(Integer.toString(mCount));
            lvlTime.setText(Integer.toString(lvlUpTime));
        }
        else
        {
            Toast toast = Toast.makeText(this, R.string.boost_message, Toast.LENGTH_LONG);
            toast.show();
        }


        //обновление данных в классе
        Bundle arguments = getIntent().getExtras();
        BuyUp buyUp;
        buyUp = (BuyUp) arguments.getSerializable(BuyUp.class.getSimpleName());
        buyUp.setBoostUpCl(boostUp);
        buyUp.setMCount(mCount);
        buyUp.setLvlUpOneCl(lvlUpOne);
        buyUp.setBoostUpTimeCl(boostUpTime);
        buyUp.setLvlUpTimeCl(lvlUpTime);

        Zagruzka();

    }


}