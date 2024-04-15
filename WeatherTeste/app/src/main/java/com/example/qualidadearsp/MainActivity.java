package com.example.qualidadearsp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btnBuscar;
    TextView monoxido, dioxidoNitrogenio, ozonio, amonia, dioxidoEnxofre, particulas, particulasGrossas, qualidade_ar, informacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        qualidade_ar = (TextView) findViewById(R.id.qualidade_ar);
        monoxido = (TextView) findViewById(R.id.monoxido);
        dioxidoNitrogenio = (TextView) findViewById(R.id.dioxidoNitrogenio);
        ozonio = (TextView) findViewById(R.id.ozonio);
        amonia = (TextView) findViewById(R.id.amonia);
        dioxidoEnxofre = (TextView) findViewById(R.id.dioxidoEnxofre);
        particulas = (TextView) findViewById(R.id.particulas);
        particulasGrossas = (TextView) findViewById(R.id.particulasGrossas);
        informacao = (TextView) findViewById(R.id.informacao);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayWeather();
            }

            private void displayWeather() {
                String URL = "https://api.openweathermap.org/data/2.5/air_pollution?lat=-23.5506507&lon=-46.6333824&appid=12d6f3db126b6067da265481791685e8";
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray lista = response.getJSONArray("list");
                            JSONObject sublista = lista.getJSONObject(0);
                            JSONObject c = sublista.getJSONObject("components");
                            JSONObject q = sublista.getJSONObject("main");

                            double co = c.getDouble("co");
                            double no2 = c.getDouble("no2");
                            double o3 = c.getDouble("o3");
                            double so2 = c.getDouble("so2");
                            double pm2_5 = c.getDouble("pm2_5");
                            double pm10 = c.getDouble("pm10");
                            double nh3 = c.getDouble("nh3");
                            int qualidade = q.getInt("aqi");

                            if (qualidade == 1){
                                qualidade_ar.setText("Qualidade do ar: Boa");
                            }
                            else if (qualidade == 2){
                                qualidade_ar.setText("Qualidade do ar: Justa");
                            }
                            else if (qualidade == 3){
                                qualidade_ar.setText("Qualidade do ar: Moderada");
                            }
                            else if (qualidade == 4){
                                qualidade_ar.setText("Qualidade do ar: Ruim");
                            }
                            else if (qualidade == 5){
                                qualidade_ar.setText("Qualidade do ar: Muito ruim");
                            }

                            if (co < 4400){
                                monoxido.setText("Monóxido de carbono (em μg/m3)*: " +String.valueOf(co) + " (Bom)");
                            }
                            else if (co >= 4400 && co < 9400){
                                monoxido.setText("Monóxido de carbono (em μg/m3)*: " +String.valueOf(co) + " (Justo)");
                            }
                            else if (co >= 9400 && co < 12400){
                                monoxido.setText("Monóxido de carbono (em μg/m3)*: " +String.valueOf(co) + " (Moderada)");
                            }
                            else if (co >= 12400 && co < 15400){
                                monoxido.setText("Monóxido de carbono (em μg/m3)*: " +String.valueOf(co) + " (Ruim)");
                            }
                            else if (co >= 15400){
                                monoxido.setText("Monóxido de carbono (em μg/m3)*: " +String.valueOf(co) + " (Muito ruim)");
                            }

                            if (so2 < 20){
                                dioxidoEnxofre.setText("Dióxido de enxofre (em μg/m3)*: " +String.valueOf(so2) + " (Bom)");
                            }
                            else if (so2 >= 20 && so2 < 80){
                                dioxidoEnxofre.setText("Dióxido de enxofre (em μg/m3)*: " +String.valueOf(so2) + " (Justo)");
                            }
                            else if (so2 >= 80 && so2 < 250){
                                dioxidoEnxofre.setText("Dióxido de enxofre (em μg/m3)*: " +String.valueOf(so2) + " (Moderada)");
                            }
                            else if (so2 >= 250 && so2 < 350){
                                dioxidoEnxofre.setText("Dióxido de enxofre (em μg/m3)*: " +String.valueOf(so2) + " (Ruim)");
                            }
                            else if (so2 >= 350){
                                dioxidoEnxofre.setText("Dióxido de enxofre (em μg/m3)*: " +String.valueOf(so2) + " (Muito ruim)");
                            }

                            if (no2 < 40){
                                dioxidoNitrogenio.setText("Dióxido de nitrogênio (em μg/m3)*: " +String.valueOf(no2) + " (Bom)");
                            }
                            else if (no2 >= 40 && no2 < 70){
                                dioxidoNitrogenio.setText("Dióxido de nitrogênio (em μg/m3)*: " +String.valueOf(no2) + " (Justo)");
                            }
                            else if (no2 >= 70 && no2 < 150){
                                dioxidoNitrogenio.setText("Dióxido de nitrogênio (em μg/m3)*: " +String.valueOf(no2) + " (Moderada)");
                            }
                            else if (no2 >= 150 && no2 < 200){
                                dioxidoNitrogenio.setText("Dióxido de nitrogênio (em μg/m3)*: " +String.valueOf(no2) + " (Ruim)");
                            }
                            else if (no2 >= 200){
                                dioxidoNitrogenio.setText("Dióxidode nitrogênio (em μg/m3)*: " +String.valueOf(no2) + " (Muito ruim)");
                            }

                            if (o3 < 60){
                                ozonio.setText("Ozônio (em μg/m3)*: " +String.valueOf(o3) + " (Bom)");
                            }
                            else if (o3 >= 60 && o3 < 100){
                                ozonio.setText("Ozônio (em μg/m3)*: " +String.valueOf(o3) + " (Justo)");
                            }
                            else if (o3 >= 100 && o3 < 140){
                                ozonio.setText("Ozônio (em μg/m3)*: " +String.valueOf(o3) + " (Moderada)");
                            }
                            else if (o3 >= 140 && o3 < 180){
                                ozonio.setText("Ozônio (em μg/m3)*: " +String.valueOf(o3) + " (Ruim)");
                            }
                            else if (o3 >= 180){
                                ozonio.setText("Ozônio (em μg/m3)*: " +String.valueOf(o3) + " (Muito ruim)");
                            }

                            if (pm2_5 < 10){
                                particulas.setText("Material particulado (em μg/m3)*: " +String.valueOf(pm2_5) + " (Bom)");
                            }
                            else if (pm2_5 >= 10 && pm2_5 < 25){
                                particulas.setText("Material particulado (em μg/m3)*: " +String.valueOf(pm2_5) + " (Justo)");
                            }
                            else if (pm2_5 >= 25 && pm2_5 < 50){
                                particulas.setText("Material particulado (em μg/m3)*: " +String.valueOf(pm2_5) + " (Moderada)");
                            }
                            else if (pm2_5 >= 50 && pm2_5 < 75){
                                particulas.setText("Material particulado (em μg/m3)*: " +String.valueOf(pm2_5) + " (Ruim)");
                            }
                            else if (pm2_5 >= 75){
                                particulas.setText("Material particulado (em μg/m3)*: " +String.valueOf(pm2_5) + " (Muito ruim)");
                            }

                            if (pm10 < 20){
                                particulasGrossas.setText("Particulas grossas (em μg/m3)*: " +String.valueOf(pm10) + " (Bom)");
                            }
                            else if (pm10 >= 20 && pm10 < 50){
                                particulasGrossas.setText("Particulas grossas (em μg/m3)*: " +String.valueOf(pm10) + " (Justo)");
                            }
                            else if (pm10 >= 50 && pm10 < 100){
                                particulasGrossas.setText("Particulas grossas (em μg/m3)*: " +String.valueOf(pm10) + " (Moderada)");
                            }
                            else if (pm10 >= 100 && pm10 < 200){
                                particulasGrossas.setText("Particulas grossas (em μg/m3)*: " +String.valueOf(pm10) + " (Ruim)");
                            }
                            else if (pm10 >= 200){
                                particulasGrossas.setText("Particulas grossas (em μg/m3)*: " +String.valueOf(pm10) + " (Muito ruim)");
                            }

                            if (nh3 < 20){
                                amonia.setText("Amônia (em μg/m3)*: " +String.valueOf(nh3) + " (Bom)");
                            }
                            else if (nh3 >= 20 && nh3 < 50){
                                amonia.setText("Amônia (em μg/m3)*: " +String.valueOf(nh3) + " (Justo)");
                            }
                            else if (nh3 >= 50 && nh3 < 100){
                                amonia.setText("Amônia (em μg/m3)*: " +String.valueOf(nh3) + " (Moderada)");
                            }
                            else if (nh3 >= 100 && nh3 < 200){
                                amonia.setText("Amônia (em μg/m3)*: " +String.valueOf(nh3) + " (Ruim)");
                            }
                            else if (nh3 >= 200){
                                amonia.setText("Amônia (em μg/m3)*: " +String.valueOf(nh3) + " (Muito ruim)");
                            }

                        btnBuscar.setText("Atualizar");
                            informacao.setVisibility(1);
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Erro durante a busca. Tente novamente.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Erro durante a busca. Tente novamente", Toast.LENGTH_SHORT).show();
                        monoxido.setText("");
                    }
                });
                queue.add(request);
            }
        });
    }
}