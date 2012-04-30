

/* First created by JCasGen Mon Sep 20 06:38:59 GMT 2010 */
package is.merkor.relationextraction.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Jan 25 10:14:51 GMT 2011
 * XML source: /Users/anna/EclipseProjects/workspace/merkor.hg/descriptors/MerkorTypeSystem.xml
 * @generated */
public class Lemma extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Lemma.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Lemma() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Lemma(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Lemma(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Lemma(JCas jcas, int begin, int end) {
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
  //* Feature: lemmaString

  /** getter for lemmaString - gets 
   * @generated */
  public String getLemmaString() {
    if (Lemma_Type.featOkTst && ((Lemma_Type)jcasType).casFeat_lemmaString == null)
      jcasType.jcas.throwFeatMissing("lemmaString", "is.merkor.relationextraction.types.Lemma");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Lemma_Type)jcasType).casFeatCode_lemmaString);}
    
  /** setter for lemmaString - sets  
   * @generated */
  public void setLemmaString(String v) {
    if (Lemma_Type.featOkTst && ((Lemma_Type)jcasType).casFeat_lemmaString == null)
      jcasType.jcas.throwFeatMissing("lemmaString", "is.merkor.relationextraction.types.Lemma");
    jcasType.ll_cas.ll_setStringValue(addr, ((Lemma_Type)jcasType).casFeatCode_lemmaString, v);}    
  }

    