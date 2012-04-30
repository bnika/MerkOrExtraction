

/* First created by JCasGen Tue Jun 01 15:12:03 GMT 2010 */
package is.merkor.relationextraction.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Jan 25 10:14:52 GMT 2011
 * XML source: /Users/anna/EclipseProjects/workspace/merkor.hg/descriptors/MerkorTypeSystem.xml
 * @generated */
public class Word extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Word.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Word() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Word(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Word(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Word(JCas jcas, int begin, int end) {
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
  //* Feature: word_string

  /** getter for word_string - gets lexical unit
   * @generated */
  public String getWord_string() {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_word_string == null)
      jcasType.jcas.throwFeatMissing("word_string", "is.merkor.relationextraction.types.Word");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Word_Type)jcasType).casFeatCode_word_string);}
    
  /** setter for word_string - sets lexical unit 
   * @generated */
  public void setWord_string(String v) {
    if (Word_Type.featOkTst && ((Word_Type)jcasType).casFeat_word_string == null)
      jcasType.jcas.throwFeatMissing("word_string", "is.merkor.relationextraction.types.Word");
    jcasType.ll_cas.ll_setStringValue(addr, ((Word_Type)jcasType).casFeatCode_word_string, v);}    
                              public Word(JCas jcas, int start, int end, String word_string) {
	    super(jcas, start, end);
	    setWord_string(word_string);
	  }
  }

    