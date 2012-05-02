package is.merkor.relationextraction.annotators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import is.merkor.relationextraction.RelationInitializer;
import is.merkor.relationextraction.types.SemRelation;
import is.merkor.util.FileCommunicatorReading;
import is.merkor.relationextraction.RegExMap;
import is.merkor.relationextraction.RelationValidator;
import is.merkor.relationextraction.RuleExtractor;
import is.merkor.relationextraction.StringMapResource;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * RelationAnnotator annotates semantic relations according to a map of regular expressions
 * and corresponding rules. The input JCas has to be annotated with <code>PairWordPOS</code>.
 * <p>
 * Needs the file <code>resources/net/merkor/ruleMapping.txt</code>
 * 
 * @author Anna B. Nikulásdóttir
 *
 */
public class RelationAnnotator extends JCasAnnotator_ImplBase {
	
	private String resource = "ruleMapping"; // includes regular expressions for patterns and rules
	private StringMapResource map;
	private RegExMap regExMap;				// a map for the content of the resource
	private RelationInitializer relInitializer;
	private RuleExtractor ruleExtractor;
	private RelationValidator relationValidator;

	
	public void initialize (UimaContext aContext) throws ResourceInitializationException {
	    super.initialize(aContext);
	    relInitializer = new RelationInitializer();
	    relationValidator = new RelationValidator();
	    // get a reference to the String Map Resource
	    try {
	      map = (StringMapResource) getContext().getResourceObject(resource); // need this? directly give regexMap the filename? constructor for that already exists
	      regExMap = new RegExMap(map.getMap());
	    } catch (ResourceAccessException e) {
	      throw new ResourceInitializationException(e);
	    }
	    
	 }
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		ruleExtractor = new RuleExtractor();
		System.out.println("processing RelationAnnotator ...");
		long startTime = System.currentTimeMillis();
	    Set<Pattern> keys = regExMap.keySet();
	    String ruleString = "";
	    String[] ruleArr;
	    List<String> rules = new ArrayList<String>();
	    //the text that is searched for patterns
		String text = aJCas.getDocumentText();
	    // go through every pattern in regExMap
	    // extract rules and relations from matched strings
	    for (Pattern p : keys) {
	    	Matcher matcher = p.matcher(text);
	    	ruleString = (String)regExMap.get(p);
	    	while(matcher.find()) {
	    		if(matcher.group().length() > 0) {
		    		rules = ruleExtractor.getRules(matcher, ruleString);
		 		    //create annotation for every rule
		    		for(int i = 0; i < rules.size(); i++) {
		    			ruleArr = rules.get(i).split("%");
		    			if(isValidRule(ruleArr)) {
		    				SemRelation annot = new SemRelation(aJCas, matcher.start(), matcher.end(), rules.get(i));
		    				annot.addToIndexes();
		    			}
		    		}
	    		}	            
	    	}
	    	rules.clear();
	    }
	    aJCas = relInitializer.setRelationWords(aJCas);
	    long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	    
	}
	
	private boolean isValidRule(String[] arr) {
		if(arr.length != 3) {
			System.out.println("wrong format!");
			return false;
		}
		return relationValidator.validateRelation(arr);
	}

}
