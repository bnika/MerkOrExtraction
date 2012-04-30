

/* First created by JCasGen Tue Jun 22 09:29:19 GMT 2010 */
package is.merkor.relationextraction.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Jan 25 10:14:52 GMT 2011
 * XML source: /Users/anna/EclipseProjects/workspace/merkor.hg/descriptors/MerkorTypeSystem.xml
 * @generated */
public class SemRelation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SemRelation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SemRelation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SemRelation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SemRelation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SemRelation(JCas jcas, int begin, int end) {
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
  //* Feature: relation

  /** getter for relation - gets 
   * @generated */
  public String getRelation() {
    if (SemRelation_Type.featOkTst && ((SemRelation_Type)jcasType).casFeat_relation == null)
      jcasType.jcas.throwFeatMissing("relation", "is.merkor.relationextraction.types.SemRelation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SemRelation_Type)jcasType).casFeatCode_relation);}
    
  /** setter for relation - sets  
   * @generated */
  public void setRelation(String v) {
    if (SemRelation_Type.featOkTst && ((SemRelation_Type)jcasType).casFeat_relation == null)
      jcasType.jcas.throwFeatMissing("relation", "is.merkor.relationextraction.types.SemRelation");
    jcasType.ll_cas.ll_setStringValue(addr, ((SemRelation_Type)jcasType).casFeatCode_relation, v);}    
   
    
  //*--------------*
  //* Feature: reflexive

  /** getter for reflexive - gets 
   * @generated */
  public String getReflexive() {
    if (SemRelation_Type.featOkTst && ((SemRelation_Type)jcasType).casFeat_reflexive == null)
      jcasType.jcas.throwFeatMissing("reflexive", "is.merkor.relationextraction.types.SemRelation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SemRelation_Type)jcasType).casFeatCode_reflexive);}
    
  /** setter for reflexive - sets  
   * @generated */
  public void setReflexive(String v) {
    if (SemRelation_Type.featOkTst && ((SemRelation_Type)jcasType).casFeat_reflexive == null)
      jcasType.jcas.throwFeatMissing("reflexive", "is.merkor.relationextraction.types.SemRelation");
    jcasType.ll_cas.ll_setStringValue(addr, ((SemRelation_Type)jcasType).casFeatCode_reflexive, v);}    
   
    
  //*--------------*
  //* Feature: transitive

  /** getter for transitive - gets 
   * @generated */
  public String getTransitive() {
    if (SemRelation_Type.featOkTst && ((SemRelation_Type)jcasType).casFeat_transitive == null)
      jcasType.jcas.throwFeatMissing("transitive", "is.merkor.relationextraction.types.SemRelation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SemRelation_Type)jcasType).casFeatCode_transitive);}
    
  /** setter for transitive - sets  
   * @generated */
  public void setTransitive(String v) {
    if (SemRelation_Type.featOkTst && ((SemRelation_Type)jcasType).casFeat_transitive == null)
      jcasType.jcas.throwFeatMissing("transitive", "is.merkor.relationextraction.types.SemRelation");
    jcasType.ll_cas.ll_setStringValue(addr, ((SemRelation_Type)jcasType).casFeatCode_transitive, v);}    
   
    
  //*--------------*
  //* Feature: word1

  /** getter for word1 - gets 
   * @generated */
  public String getWord1() {
    if (SemRelation_Type.featOkTst && ((SemRelation_Type)jcasType).casFeat_word1 == null)
      jcasType.jcas.throwFeatMissing("word1", "is.merkor.relationextraction.types.SemRelation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SemRelation_Type)jcasType).casFeatCode_word1);}
    
  /** setter for word1 - sets  
   * @generated */
  public void setWord1(String v) {
    if (SemRelation_Type.featOkTst && ((SemRelation_Type)jcasType).casFeat_word1 == null)
      jcasType.jcas.throwFeatMissing("word1", "is.merkor.relationextraction.types.SemRelation");
    jcasType.ll_cas.ll_setStringValue(addr, ((SemRelation_Type)jcasType).casFeatCode_word1, v);}    
   
    
  //*--------------*
  //* Feature: word2

  /** getter for word2 - gets 
   * @generated */
  public String getWord2() {
    if (SemRelation_Type.featOkTst && ((SemRelation_Type)jcasType).casFeat_word2 == null)
      jcasType.jcas.throwFeatMissing("word2", "is.merkor.relationextraction.types.SemRelation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SemRelation_Type)jcasType).casFeatCode_word2);}
    
  /** setter for word2 - sets  
   * @generated */
  public void setWord2(String v) {
    if (SemRelation_Type.featOkTst && ((SemRelation_Type)jcasType).casFeat_word2 == null)
      jcasType.jcas.throwFeatMissing("word2", "is.merkor.relationextraction.types.SemRelation");
    jcasType.ll_cas.ll_setStringValue(addr, ((SemRelation_Type)jcasType).casFeatCode_word2, v);}    
            public SemRelation(JCas jcas, int start, int end, String relation) {
	    super(jcas, start, end);
	    setRelation(relation);
	    
	  }  
  public SemRelation(JCas jcas, int start, int end, String relation, String word1, String word2) {
	    super(jcas, start, end);
	    setRelation(relation);
	    setWord1(word1);
	    setWord2(word2);
	  }
  
  }

    