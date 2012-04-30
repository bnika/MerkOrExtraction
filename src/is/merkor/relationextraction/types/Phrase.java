

/* First created by JCasGen Tue Jun 01 10:47:25 GMT 2010 */
package is.merkor.relationextraction.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Jan 25 10:14:52 GMT 2011
 * XML source: /Users/anna/EclipseProjects/workspace/merkor.hg/descriptors/MerkorTypeSystem.xml
 * @generated */
public class Phrase extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Phrase.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Phrase() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Phrase(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Phrase(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Phrase(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: phrase

  /** getter for phrase - gets Phrase name
   * @generated */
  public String getPhrase() {
    if (Phrase_Type.featOkTst && ((Phrase_Type)jcasType).casFeat_phrase == null)
      jcasType.jcas.throwFeatMissing("phrase", "is.merkor.relationextraction.types.Phrase");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Phrase_Type)jcasType).casFeatCode_phrase);}
    
  /** setter for phrase - sets Phrase name 
   * @generated */
  public void setPhrase(String v) {
    if (Phrase_Type.featOkTst && ((Phrase_Type)jcasType).casFeat_phrase == null)
      jcasType.jcas.throwFeatMissing("phrase", "is.merkor.relationextraction.types.Phrase");
    jcasType.ll_cas.ll_setStringValue(addr, ((Phrase_Type)jcasType).casFeatCode_phrase, v);}    
              public Phrase(JCas jcas, int start, int end, String phrase) {
	    super(jcas, start, end);
	    setPhrase(phrase);
	  }
  }

    