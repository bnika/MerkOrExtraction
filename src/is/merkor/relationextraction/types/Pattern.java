

/* First created by JCasGen Thu Jun 10 14:31:33 GMT 2010 */
package is.merkor.relationextraction.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Jan 25 10:14:51 GMT 2011
 * XML source: /Users/anna/EclipseProjects/workspace/merkor.hg/descriptors/MerkorTypeSystem.xml
 * @generated */
public class Pattern extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Pattern.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Pattern() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Pattern(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Pattern(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Pattern(JCas jcas, int begin, int end) {
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
  //* Feature: patternString

  /** getter for patternString - gets 
   * @generated */
  public String getPatternString() {
    if (Pattern_Type.featOkTst && ((Pattern_Type)jcasType).casFeat_patternString == null)
      jcasType.jcas.throwFeatMissing("patternString", "is.merkor.relationextraction.types.Pattern");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Pattern_Type)jcasType).casFeatCode_patternString);}
    
  /** setter for patternString - sets  
   * @generated */
  public void setPatternString(String v) {
    if (Pattern_Type.featOkTst && ((Pattern_Type)jcasType).casFeat_patternString == null)
      jcasType.jcas.throwFeatMissing("patternString", "is.merkor.relationextraction.types.Pattern");
    jcasType.ll_cas.ll_setStringValue(addr, ((Pattern_Type)jcasType).casFeatCode_patternString, v);}    
  }

    