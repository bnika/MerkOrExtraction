

/* First created by JCasGen Mon May 31 15:09:45 GMT 2010 */
package is.merkor.relationextraction.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Jan 25 10:14:51 GMT 2011
 * XML source: /Users/anna/EclipseProjects/workspace/merkor.hg/descriptors/MerkorTypeSystem.xml
 * @generated */
public class POS extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(POS.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected POS() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public POS(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public POS(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public POS(JCas jcas, int begin, int end) {
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
  //* Feature: word_class

  /** getter for word_class - gets Word class of this pos-string
   * @generated */
  public String getWord_class() {
    if (POS_Type.featOkTst && ((POS_Type)jcasType).casFeat_word_class == null)
      jcasType.jcas.throwFeatMissing("word_class", "is.merkor.relationextraction.types.POS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((POS_Type)jcasType).casFeatCode_word_class);}
    
  /** setter for word_class - sets Word class of this pos-string 
   * @generated */
  public void setWord_class(String v) {
    if (POS_Type.featOkTst && ((POS_Type)jcasType).casFeat_word_class == null)
      jcasType.jcas.throwFeatMissing("word_class", "is.merkor.relationextraction.types.POS");
    jcasType.ll_cas.ll_setStringValue(addr, ((POS_Type)jcasType).casFeatCode_word_class, v);}    
   
    
  //*--------------*
  //* Feature: casus

  /** getter for casus - gets 
   * @generated */
  public String getCasus() {
    if (POS_Type.featOkTst && ((POS_Type)jcasType).casFeat_casus == null)
      jcasType.jcas.throwFeatMissing("casus", "is.merkor.relationextraction.types.POS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((POS_Type)jcasType).casFeatCode_casus);}
    
  /** setter for casus - sets  
   * @generated */
  public void setCasus(String v) {
    if (POS_Type.featOkTst && ((POS_Type)jcasType).casFeat_casus == null)
      jcasType.jcas.throwFeatMissing("casus", "is.merkor.relationextraction.types.POS");
    jcasType.ll_cas.ll_setStringValue(addr, ((POS_Type)jcasType).casFeatCode_casus, v);}    
   
    
  //*--------------*
  //* Feature: TreeTaggerTag

  /** getter for TreeTaggerTag - gets 
   * @generated */
  public String getTreeTaggerTag() {
    if (POS_Type.featOkTst && ((POS_Type)jcasType).casFeat_TreeTaggerTag == null)
      jcasType.jcas.throwFeatMissing("TreeTaggerTag", "is.merkor.relationextraction.types.POS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((POS_Type)jcasType).casFeatCode_TreeTaggerTag);}
    
  /** setter for TreeTaggerTag - sets  
   * @generated */
  public void setTreeTaggerTag(String v) {
    if (POS_Type.featOkTst && ((POS_Type)jcasType).casFeat_TreeTaggerTag == null)
      jcasType.jcas.throwFeatMissing("TreeTaggerTag", "is.merkor.relationextraction.types.POS");
    jcasType.ll_cas.ll_setStringValue(addr, ((POS_Type)jcasType).casFeatCode_TreeTaggerTag, v);}    
                public POS(JCas jcas, int start, int end, String word_class) {
	    super(jcas, start, end);
	    setWord_class(word_class);
	    }
  public POS(JCas jcas, int start, int end, String word_class, String casus) {
	    super(jcas, start, end);
	    setWord_class(word_class);
	    setCasus(casus);
	    }
  }

    