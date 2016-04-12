package bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class LineReader extends BaseBasicBolt{

	private static final long serialVersionUID = 64760795716046405L;

	public void execute(Tuple input, BasicOutputCollector collector) {
		String lineStr = input.getStringByField("linesStr");
		System.out.println("Output:"+lineStr);
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

}
