package spout;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.storm.shade.org.apache.commons.io.IOUtils;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class FileReader extends BaseRichSpout{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6084044516738260177L;
	private SpoutOutputCollector outputCollector;
	private List<String> txtLines;

	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		this.outputCollector = collector;
		try {
			txtLines = IOUtils.readLines(ClassLoader.getSystemResourceAsStream("testfile.txt"),Charset.defaultCharset().name());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void nextTuple() {
		for(String lineStr : txtLines){
			outputCollector.emit(new Values(lineStr));
		}
		
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("linesStr"));
		
	}

}
