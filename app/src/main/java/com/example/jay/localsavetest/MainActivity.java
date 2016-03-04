package com.example.jay.localsavetest;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.BufferedReader;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.InputStreamReader;
        import java.lang.reflect.Type;
        import java.util.ArrayList;
        import java.util.List;

        import com.google.gson.Gson;
        import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity implements StatEntryAdapter.BtnClickListener{


    StatEntryAdapter customAdapter;
    private Stats Stats;
    public final String gsonStatsFilename = "com.example.jay.localsavetest.Stats.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stats = new Stats();
        buildList();
        Button updateItem = (Button) this.findViewById(R.id.button2);
        updateItem.setEnabled(false);

        loadGson(this.findViewById(android.R.id.content).getRootView());


        Button addItem = (Button) this.findViewById(R.id.button);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToTSL(view);
            }
        });


        ListView LV1=(ListView)findViewById(R.id.listView);
        LV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {

                ItemTest value = (ItemTest) adapter.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "onItemClick: " + value.Stat + value.amt, Toast.LENGTH_SHORT).show();
            }

            ;
        });
        Button updateItemButton=(Button)findViewById(R.id.button2);
        updateItemButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                updateItem(v);
                }

            });


        /*Button saveGsonB = (Button) this.findViewById(R.id.button4);
        saveGsonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveGson(view);

            }

        });

        Button loadGsonB = (Button) this.findViewById(R.id.button5);
        loadGsonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadGson(view);
                //Toast.makeText(getApplicationContext(), "Gson Button Activated", Toast.LENGTH_SHORT).show();

            }

        });*/


    }


    public void buildList() {
        //This uses the custom adapter in ItemEntryAdapter.java
        ListView lv = (ListView) findViewById(R.id.listView);
        customAdapter = new StatEntryAdapter(this, R.layout.item_entry_row, Stats.Stats,this);//List<yourItem>);
        lv.setAdapter(customAdapter);
        Toast.makeText(this, "buildList", Toast.LENGTH_SHORT).show();
    }

    public void addItemToTSL(View view) {
        TextView i = (TextView) this.findViewById(R.id.editText);
        TextView q = (TextView) this.findViewById(R.id.editText2);
        //Toast.makeText(getApplicationContext(),i.getText().toString() + q.getText().toString(), Toast.LENGTH_SHORT).show();

        try {
            ItemTest item = new ItemTest(i.getText().toString(), Integer.parseInt(q.getText().toString()));
            Stats.Stats.add(item);

            this.refreshList();
            Toast.makeText(getApplicationContext(), "Stat " + item.Stat + " With A Quantity Of " + item.amt + " Was Added!", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Error: Quantity not a number!", Toast.LENGTH_SHORT).show();
        }


    }

    public void refreshList() {
        //this forces the list of items to be updated to show the newly added item
        //if we don't do this, the item will be there, but not shown until the screen refreshes
        final ArrayAdapter adapter = (ArrayAdapter) customAdapter;
        runOnUiThread(new Runnable() {
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void saveGson(View view) {

        Gson gson = new Gson();
        ItemTest temp;
        String s = "";
        Type listType = new TypeToken<ArrayList<ItemTest>>() {
        }.getType();
        s = gson.toJson(this.Stats.Stats, listType);
        /*Iterator<ItemEntry> recipeIterator = this.recipe.recipeItems.iterator();
        while (recipeIterator.hasNext()) {
            s=s+ gson.toJson(recipeIterator.next());
        }*/


        //Toast.makeText(this, "saveGSON[" + s + "]", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Stats Saved!", Toast.LENGTH_SHORT).show();
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(gsonStatsFilename, this.MODE_PRIVATE);
            outputStream.write(s.getBytes());
            outputStream.close();
        } catch (Exception e) {
            Toast.makeText(this, "saveGson file output exception", Toast.LENGTH_SHORT).show();
        }
        //this.refreshList();
        //this.buildList();
        Toast.makeText(this, "Stats Saved" + this.Stats.Stats.size(), Toast.LENGTH_SHORT).show();
    }

    public void loadGson(View view) {

        FileInputStream fis = null;
        StringBuilder sb = new StringBuilder();
        //Toast.makeText(this, "Load Gson Method Activated", Toast.LENGTH_SHORT).show();

        try {
            //Toast.makeText(this, "Load Gson Try Activated", Toast.LENGTH_SHORT).show();

            fis = this.openFileInput(gsonStatsFilename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                //Toast.makeText(this, "Load gson while activated[" + line + "]", Toast.LENGTH_SHORT).show();
            }
        } catch (java.io.FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), "loadGSON file not found exception", Toast.LENGTH_SHORT).show();
        } catch (java.io.IOException e) {
            Toast.makeText(getApplicationContext(), "loadGSON IO exception", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Other exception:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        String json = sb.toString();
        Gson gson = new Gson();

        Type listType = new TypeToken<ArrayList<ItemTest>>() {
        }.getType();
        List<ItemTest> yourClassList = gson.fromJson(json, listType);


        if (yourClassList != null) {
            this.Stats.Stats = yourClassList;
            this.refreshList();
            this.buildList();
            Toast.makeText(getApplicationContext(), "Stats Loaded" + this.Stats.Stats.size(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Nothing to load", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onStop(){
        super.onStop();
        saveGson(this.findViewById(android.R.id.content).getRootView());

    }


    public void onDeleteClick(int position){

        Stats.Stats.remove(position);
        Toast.makeText(getApplicationContext(), "Sucsessfully Deleted"+position, Toast.LENGTH_SHORT).show();
        this.refreshList();
        this.buildList();

    };

    public void onEditClick(int position){


        ItemTest toEdit=Stats.Stats.get(position);
        Toast.makeText(getApplicationContext(), "Editing:"+position, Toast.LENGTH_SHORT).show();
        TextView i=(TextView) this.findViewById(R.id.editText);
        i.setText(toEdit.Stat);
        TextView q=(TextView) this.findViewById(R.id.editText2);
        q.setText(toEdit.amt+"");
        Button updateItem = (Button) this.findViewById(R.id.button2);
        updateItem.setTag(position);
        updateItem.setEnabled(true);
        this.refreshList();
        this.buildList();


    };


    public void updateItem(View v){
        Button updateItem = (Button) this.findViewById(R.id.button2);
        int position=(int)updateItem.getTag();
        ItemTest toEdit=Stats.Stats.get(position);
        TextView i=(TextView) this.findViewById(R.id.editText);
        TextView q=(TextView) this.findViewById(R.id.editText2);
        toEdit.Stat=i.getText().toString();
        Toast.makeText(getApplicationContext(), "Updated "+i.getText().toString()+" to a value of "+q.getText().toString()+"!",
                Toast.LENGTH_SHORT).show();
        try {
            toEdit.amt=Integer.parseInt(q.getText().toString());
            updateItem.setEnabled(false);
        }
        catch(NumberFormatException e)
        {
            Toast.makeText(getApplicationContext(), "Refresh Error: Quantity not a number", Toast.LENGTH_SHORT).show();
        }
        this.refreshList();
        this.buildList();

    }

}