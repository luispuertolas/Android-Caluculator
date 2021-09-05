package com.example.luis.calculadora;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MyActivity extends Activity {
    EditText editText;
    String campo="0";
    String doubleafloat;
    float temp;
    boolean existePunto;
    boolean existeSigno = false;
    boolean limpiapantalla=false;
    int apuntadorDelUltimoSigno;
    int[] operadores;
    int contadorOperadores = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        /////////////////////////////////////////////
        editText = (EditText) findViewById(R.id.editText);
        existePunto = false;
        operadores = new int[20];
        operadores[0] = 0;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void click1(View unView)
    {
        EscribeNumero(""+1);
    }
    public void click2(View unView)
    {
        EscribeNumero(""+2);
    }
    public void click3(View unView)
    {
        EscribeNumero(""+3);
    }
    public void click4(View unView)
    {
        EscribeNumero(""+4);
    }
    public void click5(View unView)
    {
        EscribeNumero(""+5);
    }
    public void click6(View unView)
    {
        EscribeNumero(""+6);
    }
    public void click7(View unView)
    {
        EscribeNumero(""+7);
    }
    public void click8(View unView)
    {
        EscribeNumero(""+8);
    }
    public void click9(View unView)
    {
        EscribeNumero(""+9);
    }
    public void click0(View unView)
    {
        EscribeNumero(""+0);
    }
    public void clickMas(View unView)
    {
        if(campo.charAt(campo.length()-1) != '+' && campo.charAt(campo.length()-1) != '-' && campo.charAt(campo.length()-1) != '/' && campo.charAt(campo.length()-1) != 'x' && campo.charAt(campo.length()-1) != '^') //Evita escribir + si el último campo es un signo
        {
            EscribeSigno("+");
        }
    }
    public void clickDiv(View unView)
    {
        if(campo.charAt(campo.length()-1) != '+' && campo.charAt(campo.length()-1) != '-' && campo.charAt(campo.length()-1) != '/' && campo.charAt(campo.length()-1) != 'x'&& campo.charAt(campo.length()-1) != '^')  //Evita escribir / si el último campo es un signo
        {
            EscribeSigno("/");
        }
    }
    public void clickMult(View unView)
    {
        if(campo.charAt(campo.length()-1) != '+' && campo.charAt(campo.length()-1) != '-' && campo.charAt(campo.length()-1) != '/' && campo.charAt(campo.length()-1) != 'x'&& campo.charAt(campo.length()-1) != '^')  //Evita escribir x si el último campo es un signo
        {
            EscribeSigno("x");
        }
    }
    public void clickResta(View unView)
    {
        if(campo.charAt(campo.length()-1) != '+' && campo.charAt(campo.length()-1) != '-' && campo.charAt(campo.length()-1) != '/' && campo.charAt(campo.length()-1) != 'x'&& campo.charAt(campo.length()-1) != '^')  //Evita escribir - si el último campo es un signo
        {
            EscribeSigno("-");
        }
    }
    public void clickPunto(View unView)
    {
        for(int i=0; i<campo.length(); i++)
        {
            if(campo.charAt(i) == '/' || campo.charAt(i) == 'x' || campo.charAt(i) == '-' || campo.charAt(i) == '+' || campo.charAt(i) == '^')  //Busca el último signo en el campo de texto
            {
                apuntadorDelUltimoSigno = i;
            }
        }
        for(int i=apuntadorDelUltimoSigno; i<campo.length(); i++)
        {
            if(campo.charAt(i) == '.')    //Verificar si ya hay un punto en el último dígito, para no escribir un número con 2 puntos
            {
                apuntadorDelUltimoSigno = i;
                existePunto=true;
            }
        }
        if(existePunto == false)
        {
            EscribeNumero(".");
        }
        existePunto = false;
    }
    public void clickClr(View unView)
    {
        editText.setText("0");
    }
    public void clickRes(View unView)
    {
        campo = editText.getText().toString();
        double exponente;
        contadorOperadores = 0;
        for(int i = 0; i<campo.length() ; i++)
        {
            if(campo.charAt(i) == 'x' || campo.charAt(i) == '/' || campo.charAt(i) == '+' || campo.charAt(i) == '-' || campo.charAt(i) == '^')
            {
                operadores[contadorOperadores] = i;       //Almacena las posiciones del campo en donde hay operadores
                contadorOperadores = contadorOperadores+1; //Cuenta el número de operadores que hay en el campo
            }
        }
        for(int i = 0; i < contadorOperadores; i++)
        {
            if(i == 0 && contadorOperadores >0 )
            {
                temp = Float.parseFloat(campo.substring(0,operadores[i]));
            }
            if(i>0 && i< contadorOperadores)
            {
                if(campo.charAt(operadores[i-1]) == '+')
                {
                    temp = temp + Float.parseFloat(campo.substring(operadores[i - 1] + 1, operadores[i]));
                }
                if(campo.charAt(operadores[i-1]) == '-')
                {
                    temp = temp - Float.parseFloat(campo.substring(operadores[i - 1] + 1, operadores[i]));
                }
                if(campo.charAt(operadores[i-1]) == '/')
                {
                    temp = temp / Float.parseFloat(campo.substring(operadores[i - 1] + 1, operadores[i]));
                }
                if(campo.charAt(operadores[i-1]) == 'x')
                {
                    temp = temp * Float.parseFloat(campo.substring(operadores[i - 1] + 1, operadores[i]));
                }
                if(campo.charAt(operadores[i-1]) == '^')
                {
                    exponente = Math.pow(temp,Float.parseFloat(campo.substring(operadores[i-1]+1,operadores[i])));
                    doubleafloat = String.valueOf(exponente);
                    temp = Float.parseFloat(doubleafloat);
                }
            }
        }
        if(contadorOperadores > 0)
        {
            if (campo.charAt(operadores[contadorOperadores - 1]) == '+' && campo.charAt(campo.length()-1) != '+' && campo.charAt(campo.length()-1) != '-' && campo.charAt(campo.length()-1) != 'x' && campo.charAt(campo.length()-1) != '/' && campo.charAt(campo.length()-1) != '^')
            {
                temp = temp + Float.parseFloat(campo.substring(operadores[contadorOperadores - 1] + 1));
            }
            if (campo.charAt(operadores[contadorOperadores - 1]) == '-' && campo.charAt(campo.length()-1) != '+' && campo.charAt(campo.length()-1) != '-' && campo.charAt(campo.length()-1) != 'x' && campo.charAt(campo.length()-1) != '/' && campo.charAt(campo.length()-1) != '^')
            {
                temp = temp - Float.parseFloat(campo.substring(operadores[contadorOperadores - 1] + 1));
            }
            if (campo.charAt(operadores[contadorOperadores - 1]) == 'x' && campo.charAt(campo.length()-1) != '+' && campo.charAt(campo.length()-1) != '-' && campo.charAt(campo.length()-1) != 'x' && campo.charAt(campo.length()-1) != '/' && campo.charAt(campo.length()-1) != '^')
            {
                temp = temp * Float.parseFloat(campo.substring(operadores[contadorOperadores - 1] + 1));
            }
            if (campo.charAt(operadores[contadorOperadores - 1]) == '/' && campo.charAt(campo.length()-1) != '+' && campo.charAt(campo.length()-1) != '-' && campo.charAt(campo.length()-1) != 'x' && campo.charAt(campo.length()-1) != '/' && campo.charAt(campo.length()-1) != '^')
            {
                temp = temp / Float.parseFloat(campo.substring(operadores[contadorOperadores - 1] + 1));
            }
            if (campo.charAt(operadores[contadorOperadores - 1]) == '^'&& campo.charAt(campo.length()-1) != '+' && campo.charAt(campo.length()-1) != '-' && campo.charAt(campo.length()-1) != 'x' && campo.charAt(campo.length()-1) != '/' && campo.charAt(campo.length()-1) != '^')
            {
                exponente = Math.pow(temp,Float.parseFloat(campo.substring(operadores[contadorOperadores - 1] + 1)));
                doubleafloat = String.valueOf(exponente);
                temp = Float.parseFloat(doubleafloat);
            }
        }
        if (contadorOperadores==0)
        {
            temp = Float.parseFloat(campo);
        }
        contadorOperadores=0;
        limpiapantalla = true;
        editText.setText("" + temp);
        for(int i = 0; i<20; i++)
        {
            operadores[i]=0;
        }
    }
    public void clickRaiz(View unView)
    {
        double raiz;
        clickRes(unView);
        campo = editText.getText().toString();
        raiz = Float.parseFloat(campo);
        raiz = Math.sqrt(raiz);
        editText.setText("" + raiz);
    }
    public void clickLogaritmo(View unView)
    {
        double logaritmo;
        clickRes(unView);
        campo = editText.getText().toString();
        logaritmo = Float.parseFloat(campo);
        logaritmo = Math.log10(logaritmo);
        editText.setText("" + logaritmo);
    }
    public void clickTangente(View unView)
    {
        double tangente;
        clickRes(unView);
        campo = editText.getText().toString();
        tangente = Float.parseFloat(campo);
        System.out.println(tangente);
        tangente = Math.tan(tangente);
        editText.setText("" + tangente);
    }
    public void clickSeno(View unView)
    {
        double seno;
        clickRes(unView);
        campo = editText.getText().toString();
        seno = Float.parseFloat(campo);
        seno = Math.sin(seno);
        editText.setText("" + seno);
    }
    public void clickExponencial(View unView)
    {
        double Exponencial;
        clickRes(unView);
        campo = editText.getText().toString();
        Exponencial = Float.parseFloat(campo);
        Exponencial = Math.exp(Exponencial);
        editText.setText("" + Exponencial);
    }
    public void clickCoseno(View unView)
    {
        double coseno;
        clickRes(unView);
        campo = editText.getText().toString();
        coseno = Float.parseFloat(campo);
        coseno = Math.cos(coseno);
        editText.setText("" + coseno);
    }
    public void clickExponente(View unView)
    {
        if(campo.charAt(campo.length()-1) != '+' && campo.charAt(campo.length()-1) != '-' && campo.charAt(campo.length()-1) != '/' && campo.charAt(campo.length()-1) != 'x'&& campo.charAt(campo.length()-1) != '^')  //Evita escribir - si el último campo es un signo
        {
            EscribeSigno("^");
        }
    }
    private void EscribeNumero(String str)
    {
        campo = editText.getText().toString();
        if (campo.charAt(campo.length()-1) == '/' || campo.charAt(campo.length()-1) == 'x' || campo.charAt(campo.length()-1) == '-' || campo.charAt(campo.length()-1) == '+' || campo.charAt(campo.length()-1) == '^') //Verifica si hay signo en el último caracter del campo
        {
            existeSigno  = true;
        }
        if(limpiapantalla == true && existeSigno  == false) //Si se oprimió igual y no hay ningún signo a aplicar al resultado, limpiar la pantalla antes de escribir el número
        {
            campo = "";
        }
        if(campo.equals("0")) //Quitar el 0 a la izquierda cuando el campo es solamente 0
        {
            campo = "";
        }
        campo += str;    //Agrega el número escrito al campo
        editText.setText(campo);
        existeSigno = false;
        limpiapantalla=false;
    }
    private void EscribeSigno(String str)       //Se escribe el signo por separado para poder realizar operaciones después de dar igual
    {
        campo = editText.getText().toString();
        campo += str;
        editText.setText(campo);
    }
}
