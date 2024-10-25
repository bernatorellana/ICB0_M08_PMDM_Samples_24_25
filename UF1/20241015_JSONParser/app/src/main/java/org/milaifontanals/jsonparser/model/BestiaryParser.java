package org.milaifontanals.jsonparser.model;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.milaifontanals.jsonparser.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class BestiaryParser {

    public static List<Section> getBestiary(Context c){

        List<Section> bestiary = new ArrayList<>();

        // 0.- Llegir arxiu JSON
        //     i posar-lo en una cadena
        String json= readJson(c);
        if(json==null) return null;

        // 1.- Parsejar JSON i recórrer
        // les Sections
        try {
            JSONObject arrel = new JSONObject(json);
            JSONArray sections = arrel.getJSONArray("sections");
            for(int s=0;s<sections.length();s++){
                JSONObject section = sections.getJSONObject(s);
                String title =  section.getString("title");
                String subtitle =  section.getString("subtitle");
                String image =  section.getString("image");
                Section sec = new Section(title,subtitle,image);

                // 2.- Per cada section, buscar
                // les entries
                JSONArray entries = section.getJSONArray("entries");
                for(int e=0;e<entries.length();e++){
                    JSONObject entry = entries.getJSONObject(e);
                    title =  entry.getString("title");
                    image =  entry.getString("image");
                    Entry ent = new Entry(title, image);
                    sec.getEntries().add(ent);
                }
                bestiary.add(sec);
            }

        } catch (JSONException e) {
            Log.e("WATCHER", "Error parsejant JSON",e);
            return null;
        }

        return bestiary;
    }

    private static String readJson(Context c) {
        String json ="";
        try {
            InputStream is =  c.getResources().openRawResource(R.raw.bestiary);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            while(((line = br.readLine())!=null)) {
                json +=line+"\n";
            }
            //Log.d("WITCHER", json);
        } catch (IOException e) {
            return null;
        }
        return json;
    }

}
