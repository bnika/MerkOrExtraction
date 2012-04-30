
/* First created by JCasGen Mon Sep 20 06:38:59 GMT 2010 */
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
 * Updated by JCasGen Tue Jan 25 10:14:51 GMT 2011
 * @generated */
public class Lemma_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Lemma_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Lemma_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Lemma(addr, Lemma_Type.this);
  			   Lemma_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Lemma(addr, Lemma_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Lemma.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("is.merkor.relationextraction.types.Lemma");
 
  /** @generated */
  final Feature casFeat_lemmaString;
  /** @generated */
  final int     casFeatCode_lemmaString;
  /** @generated */ 
  public String getLemmaString(int addr) {
        if (featOkTst && casFeat_lemmaString == null)
      jcas.throwFeatMissing("lemmaString", "is.merkor.relationextraction.types.Lemma");
    return ll_cas.ll_getStringValue(addr, casFeatCode_lemmaString);
  }
  /** @generated */    
  public void setLemmaString(int addr, String v) {
        if (featOkTst && casFeat_lemmaString == null)
      jcas.throwFeatMissing("lemmaString", "is.merkor.relationextraction.types.Lemma");
    ll_cas.ll_setStringValue(addr, casFeatCode_lemmaString, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Lemma_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_lemmaString = jcas.getRequiredFeatureDE(casType, "lemmaString", "uima.cas.String", featOkTst);
    casFeatCode_lemmaString  = (null == casFeat_lemmaString) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lemmaString).getCode();

  }
}



    