

/* First created by JCasGen Fri Jun 11 10:27:21 GMT 2010 */
package is.merkor.relationextraction.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Jan 25 10:14:51 GMT 2011
 * XML source: /Users/anna/EclipseProjects/workspace/merkor.hg/descriptors/MerkorTypeSystem.xml
 * @generated */
public class PairWordPOS extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(PairWordPOS.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected PairWordPOS() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public PairWordPOS(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public PairWordPOS(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public PairWordPOS(JCas jcas, int begin, int end) {
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
  //* Feature: pos

  /** getter for pos - gets 
   * @generated */
  public POS getPos() {
    if (PairWordPOS_Type.featOkTst && ((PairWordPOS_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "is.merkor.relationextraction.types.PairWordPOS");
    return (POS)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((PairWordPOS_Type)jcasType).casFeatCode_pos)));}
    
  /** setter for pos - sets  
   * @generated */
  public void setPos(POS v) {
    if (PairWordPOS_Type.featOkTst && ((PairWordPOS_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "is.merkor.relationextraction.types.PairWordPOS");
    jcasType.ll_cas.ll_setRefValue(addr, ((PairWordPOS_Type)jcasType).casFeatCode_pos, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: word

  /** getter for word - gets 
   * @generated */
  public Word getWord() {
    if (PairWordPOS_Type.featOkTst && ((PairWordPOS_Type)jcasType).casFeat_word == null)
      jcasType.jcas.throwFeatMissing("word", "is.merkor.relationextraction.types.PairWordPOS");
    return (Word)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((PairWordPOS_Type)jcasType).casFeatCode_word)));}
    
  /** setter for word - sets  
   * @generated */
  public void setWord(Word v) {
    if (PairWordPOS_Type.featOkTst && ((PairWordPOS_Type)jcasType).casFeat_word == null)
      jcasType.jcas.throwFeatMissing("word", "is.merkor.relationextraction.types.PairWordPOS");
    jcasType.ll_cas.ll_setRefValue(addr, ((PairWordPOS_Type)jcasType).casFeatCode_word, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: lemma

  /** getter for lemma - gets 
   * @generated */
  public String getLemma() {
    if (PairWordPOS_Type.featOkTst && ((PairWordPOS_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "is.merkor.relationextraction.types.PairWordPOS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((PairWordPOS_Type)jcasType).casFeatCode_lemma);}
    
  /** setter for lemma - sets  
   * @generated */
  public void setLemma(String v) {
    if (PairWordPOS_Type.featOkTst && ((PairWordPOS_Type)jcasType).casFeat_lemma == null)
      jcasType.jcas.throwFeatMissing("lemma", "is.merkor.relationextraction.types.PairWordPOS");
    jcasType.ll_cas.ll_setStringValue(addr, ((PairWordPOS_Type)jcasType).casFeatCode_lemma, v);}    
                  /**
  * initializes PairWordPOS with Word and POS
  * @param jcas
  * @param begin
  * @param end
  */
  public PairWordPOS(JCas jcas, int begin, int end, Word word, POS pos) {
	    this(jcas, begin, end);
	    setWord(word);
	    setPos(pos);
	  }  
  
  }



    