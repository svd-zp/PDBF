package pdbf.html;

import java.io.FileWriter;
import java.util.ArrayList;
import pdbf.common.CustomUnit;
import pdbf.common.Date;
import pdbf.common.LineChart;
import pdbf.common.Overlay;
import pdbf.common.Text;
import pdbf.common.Unit;
import pdbf.common.UnitTypeAdapter;
import pdbf.common.Visualization;
import pdbf.common.VisualizationTypeAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CreateTest {

    public static void main(String[] args) {
	GsonBuilder builder = new GsonBuilder();
	builder.setPrettyPrinting();
	builder.registerTypeAdapter(Visualization.class,
		new VisualizationTypeAdapter());
	builder.registerTypeAdapter(Unit.class, new UnitTypeAdapter());
	Gson gson = builder.create();

	ArrayList<Overlay> overlays = new ArrayList<Overlay>();

	Date date = new Date();
	CustomUnit temp = new CustomUnit("Temperatur");
	//CustomUnit z1 = new CustomUnit("Zahl1");
	//CustomUnit z2 = new CustomUnit("Zahl2");
	LineChart chart = new LineChart("SELECT * FROM data1",
		0.5150353982300885, 0.8407079646017699, 0.2496875, 0.3601875,
		false, date, temp, 1);
	Overlay cur = new Overlay("Tabelle1", chart);
	overlays.add(cur);

	Text text = new Text("SELECT * FROM test;",
		0.12831858407079646017699115044248,
		0.33185840707964601769911504424779, 0.2515625, 0.2671875, 1);
	cur = new Overlay("SQL1", text);
	overlays.add(cur);

	text = new Text("SELECT col2 FROM test WHERE col1=1;",
		0.12831858407079646017699115044248,
		0.34955752212389380530973451327434, 0.2671875, 0.2984375, 1);
	cur = new Overlay("SQL2", text);
	overlays.add(cur);

	text = new Text("INSERT INTO test VALUES (1, 123);",
		0.12831858407079646017699115044248,
		0.30088495575221238938053097345133, 0.2984375, 0.334375, 1);
	cur = new Overlay("SQL3", text);
	overlays.add(cur);

	try {
	    FileWriter writer = new FileWriter("config.json");
	    gson.toJson(overlays, writer);
	    writer.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	try {
	    FileWriter writer = new FileWriter("db.sql");
	    //Random r = new Random(System.currentTimeMillis());
	    writer.write("CREATE TABLE data1 (Datum DATE, Temperatur INTEGER); CREATE TABLE data2 (Zahl1 INTEGER, Zahl2 INTEGER); INSERT INTO data1 VALUES ('2008/05/07', 75), ('2008/05/08', 70), ('2008/05/09', 80); CREATE TABLE test (col1 INTEGER, col2 FLOAT); INSERT INTO test VALUES (1,111.1), (2,222.1);");
	    //for (int i = 0; i < 100000; ++i) {
		//writer.write("INSERT INTO test VALUES ("+i+","+r.nextInt()+");");
	    //}
	    writer.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
