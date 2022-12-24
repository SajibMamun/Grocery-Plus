package com.example.groceryplus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class OfferFragmet extends Fragment implements AdapterView.OnItemSelectedListener {

    RadioGroup radioGroup;
    Spinner spinner;
    ImageView offerimgid;
    Button checkbutton;
    String level,coins;
    ListView listView;

    String [] coinlist={"Select Coins",">=5000",">=10000",">=20000",">=3000",">=40000",">=50000",">=60000",">=70000",">=80000",">=90000"};

    String[] offeritemlist={"Vegetable","Fruits","Plastic","Fish"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_offer_fragmet, container, false);
        checkbutton=root.findViewById(R.id.checkbtn);
        radioGroup=root.findViewById(R.id.RadioGroupid);
        spinner=root.findViewById(R.id.Spinnerid);
        offerimgid=root.findViewById(R.id.offerimgid);
        listView=root.findViewById(R.id.listviewid);

        ArrayAdapter arrayAdapter=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,coinlist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);


        checkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radioGroup.getCheckedRadioButtonId()==R.id.SilverMemberID){
                    level="silver";
                }
                else if(radioGroup.getCheckedRadioButtonId()==R.id.PlatinumMemberid)

                {
                    level="platinum";

                } else if(radioGroup.getCheckedRadioButtonId()==R.id.GoldeMemberID)

                {
                    level="gold";

                }
                else if(radioGroup.getCheckedRadioButtonId()==R.id.DiamondMemberid)
                {
                    level="diamond";
                }

                checkoffer(level,coins);

            }
        });



        ArrayAdapter<String> myadapter=new ArrayAdapter<>(getContext(),R.layout.customlayout,R.id.textviewidcustom,offeritemlist);
        listView.setAdapter(myadapter);





        return root;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        coins=coinlist[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    private void checkoffer(String level, String coins) {


        if(level=="silver" && coins==">=5000")
        {
            offerimgid.setImageResource(R.drawable.fiveoff);
        }
        else if(level=="silver" && coins==">=10000")
        {
            offerimgid.setImageResource(R.drawable.fiveoff);
        }
        else if(level=="gold" && coins==">=20000")
        {
            offerimgid.setImageResource(R.drawable.tenoff);
        }
        else if(level=="gold" && coins==">=40000")
        {
            offerimgid.setImageResource(R.drawable.twentyoff);
        }
        else if(level=="platinum" && coins==">=50000")
        {
            offerimgid.setImageResource(R.drawable.thirtyoff);
        }
        else if(level=="platinum" && coins==">=60000")
        {
            offerimgid.setImageResource(R.drawable.fourtyoff);
        }
        else if(level=="diamond" && coins==">=70000")
        {
            offerimgid.setImageResource(R.drawable.fivetyoff);
        }
        else if(level=="diamond" && coins==">=80000")
        {
            offerimgid.setImageResource(R.drawable.sixtyoff);
        } else if(level=="diamond" && coins==">=90000")
        {
            offerimgid.setImageResource(R.drawable.seventyoff);
        }

        else
        {
            offerimgid.setImageResource(R.drawable.noteligable);

        }

    }

}