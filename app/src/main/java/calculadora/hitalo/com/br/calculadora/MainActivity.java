package calculadora.hitalo.com.br.calculadora;



        import android.app.Activity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;
        import android.view.View.OnClickListener;
        import android.widget.Button;

public class MainActivity extends Activity
{
    TextView textView;
    String currentString="0",previusString=null;
    boolean isTempStringShown=false;
    int currentopperand=0;
    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.nomeEditText);
        int numberButtons[]={R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9};
        NumberButtonClickListener numberClickListener=new NumberButtonClickListener();
        for(int id:numberButtons)
        {	View v=findViewById(id);
            v.setOnClickListener(numberClickListener);
        }
        int opperandButtons[]={R.id.btnSoma,R.id.btnSub,R.id.btnDiv,R.id.btnMult,R.id.btnVirg,R.id.btnLimpa,R.id.btnResult};
        OpperandButtonClickListener oppClickListener=new OpperandButtonClickListener();
        for(int id:opperandButtons)
        {	View v=findViewById(id);
            v.setOnClickListener(oppClickListener);
        }
        setCurrentString("0");
    }
    void setCurrentString(String s)
    {	currentString=s;
        textView.setText(s);
    }
    class NumberButtonClickListener implements OnClickListener
    {	@Override public void onClick(View v)
    {	if(isTempStringShown)
    {	previusString=currentString;
        currentString="0";
        isTempStringShown=false;
    }
        String text=(String)((Button)v).getText();
        if(currentString.equals("0"))setCurrentString(text);
        else setCurrentString(currentString+text);
    }
    }
    class OpperandButtonClickListener implements OnClickListener
    {	@Override public void onClick(View v)
    {	int id=v.getId();
        if(id==R.id.btnLimpa)
        {	isTempStringShown=false;
            setCurrentString("0");
            previusString=null;
        }
        if(id==R.id.btnVirg)if(!currentString.contains("."))setCurrentString(currentString+".");
        if(id==R.id.btnSoma||id==R.id.btnSub||id==R.id.btnMult||id==R.id.btnDiv)
        {	currentopperand=id;
            previusString=currentString;
            isTempStringShown=true;
        }
        if(id==R.id.btnResult)
        {	double curr=Double.parseDouble(currentString);
            double result=0;
            if(previusString!=null)
            {	double prev=Double.parseDouble(previusString);
                switch(currentopperand)
                {	case R.id.btnSoma: result=prev+curr; break;
                    case R.id.btnSub: result=prev-curr; break;
                    case R.id.btnMult: result=prev*curr; break;
                    case R.id.btnDiv: result=prev/curr; break;
                }
            }
            setCurrentString(Double.toString(result));
        }
    }
    }
}

