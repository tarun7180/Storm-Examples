package runner;

import spout.FileReader;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;
import bolt.LineReader;

public class StormRunner {

	private static final int TEN_MINUTES=90000;
	public static void main(String[] args) {
		TopologyBuilder topologyBuilder = new TopologyBuilder();
		
		topologyBuilder.setSpout("FileReader", new FileReader());
		
		topologyBuilder.setBolt("LineReader", new LineReader())
		.shuffleGrouping("FileReader");
		
		Config configObj = new Config();
		configObj.setDebug(false);
		
		StormTopology sTopology = topologyBuilder.createTopology();
		
		LocalCluster lClusture = new LocalCluster();
		lClusture.submitTopology("Hello World", configObj, sTopology);
		
		Utils.sleep(TEN_MINUTES);
		lClusture.killTopology("Hello World");
		lClusture.shutdown();
	}

}
