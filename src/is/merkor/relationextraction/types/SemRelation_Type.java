
/* First created by JCasGen Tue Jun 22 09:29:19 GMT 2010 */
package is.merkor.relationextraction.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Tue Jan 25 10:14:52 GMT 2011
 * @generated */
public class SemRelation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SemRelation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SemRelation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SemRelation(addr, SemRelation_Type.this);
  			   SemRelation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SemRelation(addr, SemRelation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SemRelation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("is.merkor.relationextraction.types.SemRelation");
 
  /** @generated */
  final Feature casFeat_relation;
  /** @generated */
  final int     casFeatCode_relation;
  /** @generated */ 
  public String getRelation(int addr) {
        if (featOkTst && casFeat_relation == null)
      jcas.throwFeatMissing("relation", "is.merkor.relationextraction.types.SemRelation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_relation);
  }
  /** @generated */    
  public void setRelation(int addr, String v) {
        if (featOkTst && casFeat_relation == null)
      jcas.throwFeatMissing("relation", "is.merkor.relationextraction.types.SemRelation");
    ll_cas.ll_setStringValue(addr, casFeatCode_relation, v);}
    
  
 
  /** @generated */
  final Feature casFeat_reflexive;
  /** @generated */
  final int     casFeatCode_reflexive;
  /** @generated */ 
  public String getReflexive(int addr) {
        if (featOkTst && casFeat_reflexive == null)
      jcas.throwFeatMissing("reflexive", "is.merkor.relationextraction.types.SemRelation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_reflexive);
  }
  /** @generated */    
  public void setReflexive(int addr, String v) {
        if (featOkTst && casFeat_reflexive == null)
      jcas.throwFeatMissing("reflexive", "is.merkor.relationextraction.types.SemRelation");
    ll_cas.ll_setStringValue(addr, casFeatCode_reflexive, v);}
    
  
 
  /** @generated */
  final Feature casFeat_transitive;
  /** @generated */
  final int     casFeatCode_transitive;
  /** @generated */ 
  public String getTransitive(int addr) {
        if (featOkTst && casFeat_transitive == null)
      jcas.throwFeatMissing("transitive", "is.merkor.relationextraction.types.SemRelation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_transitive);
  }
  /** @generated */    
  public void setTransitive(int addr, String v) {
        if (featOkTst && casFeat_transitive == null)
      jcas.throwFeatMissing("transitive", "is.merkor.relationextraction.types.SemRelation");
    ll_cas.ll_setStringValue(addr, casFeatCode_transitive, v);}
    
  
 
  /** @generated */
  final Feature casFeat_word1;
  /** @generated */
  final int     casFeatCode_word1;
  /** @generated */ 
  public String getWord1(int addr) {
        if (featOkTst && casFeat_word1 == null)
      jcas.throwFeatMissing("word1", "is.merkor.relationextraction.types.SemRelation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_word1);
  }
  /** @generated */    
  public void setWord1(int addr, String v) {
        if (featOkTst && casFeat_word1 == null)
      jcas.throwFeatMissing("word1", "is.merkor.relationextraction.types.SemRelation");
    ll_cas.ll_setStringValue(addr, casFeatCode_word1, v);}
    
  
 
  /** @generated */
  final Feature casFeat_word2;
  /** @generated */
  final int     casFeatCode_word2;
  /** @generated */ 
  public String getWord2(int addr) {
        if (featOkTst && casFeat_word2 == null)
      jcas.throwFeatMissing("word2", "is.merkor.relationextraction.types.SemRelation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_word2);
  }
  /** @generated */    
  public void setWord2(int addr, String v) {
        if (featOkTst && casFeat_word2 == null)
      jcas.throwFeatMissing("word2", "is.merkor.relationextraction.types.SemRelation");
    ll_cas.ll_setStringValue(addr, casFeatCode_word2, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SemRelation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_relation = jcas.getRequiredFeatureDE(casType, "relation", "uima.cas.String", featOkTst);
    casFeatCode_relation  = (null == casFeat_relation) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_relation).getCode();

 
    casFeat_reflexive = jcas.getRequiredFeatureDE(casType, "reflexive", "uima.cas.String", featOkTst);
    casFeatCode_reflexive  = (null == casFeat_reflexive) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_reflexive).getCode();

 
    casFeat_transitive = jcas.getRequiredFeatureDE(casType, "transitive", "uima.cas.String", featOkTst);
    casFeatCode_transitive  = (null == casFeat_transitive) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_transitive).getCode();

 
    casFeat_word1 = jcas.getRequiredFeatureDE(casType, "word1", "uima.cas.String", featOkTst);
    casFeatCode_word1  = (null == casFeat_word1) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_word1).getCode();

 
    casFeat_word2 = jcas.getRequiredFeatureDE(casType, "word2", "uima.cas.String", featOkTst);
    casFeatCode_word2  = (null == casFeat_word2) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_word2).getCode();

  }
}



    