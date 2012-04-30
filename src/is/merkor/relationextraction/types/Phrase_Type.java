
/* First created by JCasGen Tue Jun 01 10:47:25 GMT 2010 */
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
public class Phrase_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Phrase_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Phrase_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Phrase(addr, Phrase_Type.this);
  			   Phrase_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Phrase(addr, Phrase_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Phrase.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("is.merkor.relationextraction.types.Phrase");
 
  /** @generated */
  final Feature casFeat_phrase;
  /** @generated */
  final int     casFeatCode_phrase;
  /** @generated */ 
  public String getPhrase(int addr) {
        if (featOkTst && casFeat_phrase == null)
      jcas.throwFeatMissing("phrase", "is.merkor.relationextraction.types.Phrase");
    return ll_cas.ll_getStringValue(addr, casFeatCode_phrase);
  }
  /** @generated */    
  public void setPhrase(int addr, String v) {
        if (featOkTst && casFeat_phrase == null)
      jcas.throwFeatMissing("phrase", "is.merkor.relationextraction.types.Phrase");
    ll_cas.ll_setStringValue(addr, casFeatCode_phrase, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Phrase_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_phrase = jcas.getRequiredFeatureDE(casType, "phrase", "uima.cas.String", featOkTst);
    casFeatCode_phrase  = (null == casFeat_phrase) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_phrase).getCode();

  }
}



    