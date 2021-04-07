package com.example.foodreco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NutritionAnalysis extends ClassifierModel {
    private RequestQueue queue;
    private JsonObjectRequest request;
    Button btn_calories, btn_dietLabels, btn_nutrients;
    EditText Food_name;
    ListView nutrition_results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_analysis);

        //assign values to each control on the layout.
        btn_calories= (Button) findViewById(R.id.btn_calories);
        btn_dietLabels= (Button) findViewById((R.id.btn_dietLabels));
        btn_nutrients= (Button) findViewById((R.id.btn_nutrients));
        Food_name= (EditText) findViewById((R.id.Food_name));
        nutrition_results= (ListView) findViewById((R.id.nutrition_results));
        queue = Volley.newRequestQueue(NutritionAnalysis.this);
        Intent intent = getIntent();
        String message = intent.getStringExtra("messagekey");
        Food_name.setText("1 "+message);


        //click listeners for each button.
        btn_calories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://edamam-edamam-nutrition-analysis.p.rapidapi.com/api/nutrition-data?ingr=" + Food_name.getText().toString();
                request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONArray cal=null ;
                                try {
                                    cal = response.getJSONArray("healthLabels");
                                    ArrayList<String> result = new ArrayList<String>();
                                    result.add(cal.toString(2));
                                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(NutritionAnalysis.this, android.R.layout.simple_list_item_1, result);
                                    nutrition_results.setAdapter(arrayAdapter);
                                } catch (JSONException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            // 4th param - method onErrorResponse lays the code procedure of error return

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // display a simple message on the screen
                                Toast.makeText(NutritionAnalysis.this, "Error occured", Toast.LENGTH_SHORT).show();
                            }
                        })
                {
                    @Override
                    public Map<String,String> getHeaders() throws AuthFailureError{
                        Map<String,String> params= new HashMap<>();
                        params.put("x-rapidapi-host", "edamam-edamam-nutrition-analysis.p.rapidapi.com");
                        params.put("x-rapidapi-key", "77c934c169msh0097cd22ca82ebbp12eab7jsnb8ebe60baecc");
                        return params;
                    }
                };
                queue.add(request);
            }
        });
        btn_nutrients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://edamam-edamam-nutrition-analysis.p.rapidapi.com/api/nutrition-data?ingr=" + Food_name.getText().toString();
                request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONObject nutrients = null;
                                try {
                                    nutrients = response.getJSONObject("totalNutrients");
                                    ArrayList<String> tubeLines = new ArrayList<>();
                                    tubeLines.add(nutrients.toString(2));
                                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(NutritionAnalysis.this, android.R.layout.simple_list_item_1, tubeLines);
                                    nutrition_results.setAdapter(arrayAdapter);

                                } catch (JSONException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            // 4th param - method onErrorResponse lays the code procedure of error return
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // display a simple message on the screen
                                Toast.makeText(NutritionAnalysis.this, "Error occured", Toast.LENGTH_SHORT).show();
                            }
                        })
                {
                    @Override
                    public Map<String,String> getHeaders() throws AuthFailureError{
                        Map<String,String> params= new HashMap<>();
                        params.put("x-rapidapi-host", "edamam-edamam-nutrition-analysis.p.rapidapi.com");
                        params.put("x-rapidapi-key", "77c934c169msh0097cd22ca82ebbp12eab7jsnb8ebe60baecc");
                        return params;
                    }
                };
                queue.add(request);
            }
        });
        btn_dietLabels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://edamam-edamam-nutrition-analysis.p.rapidapi.com/api/nutrition-data?ingr=" + Food_name.getText().toString();
                request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONArray dietInfo = null;
                                try {

                                    dietInfo = response.getJSONArray("dietLabels");
                                    ArrayList<String> result = new ArrayList<>();
                                    result.add(dietInfo.toString(2));
                                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(NutritionAnalysis.this, android.R.layout.simple_list_item_1, result);
                                    nutrition_results.setAdapter(arrayAdapter);
                                } catch (JSONException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            // 4th param - method onErrorResponse lays the code procedure of error return
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // display a simple message on the screen
                                Toast.makeText(NutritionAnalysis.this, "Error occured", Toast.LENGTH_SHORT).show();
                            }
                        })
                {
                    @Override
                    public Map<String,String> getHeaders() throws AuthFailureError{
                        Map<String,String> params= new HashMap<>();
                        params.put("x-rapidapi-host", "edamam-edamam-nutrition-analysis.p.rapidapi.com");
                        params.put("x-rapidapi-key", "77c934c169msh0097cd22ca82ebbp12eab7jsnb8ebe60baecc");
                        return params;
                    }
                };
                queue.add(request);
            }
        });
    }
    public static final String TAG = "MyTag";
// Set the tag on the request.
    protected void onStop () {
        super.onStop();
        if (queue != null) {
            queue.cancelAll(TAG);
        }
        request.setTag(TAG);
        queue.add(request);
    }
}
