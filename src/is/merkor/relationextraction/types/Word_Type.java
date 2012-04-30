
/* First created by JCasGen Tue Jun 01 15:12:03 GMT 2010 */
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
public class Word_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Word_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Word_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Word(addr, Word_Type.this);
  			   Word_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Word(addr, Word_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Word.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("is.merkor.relationextraction.types.Word");
 
  /** @generated */
  final Feature casFeat_word_string;
  /** @generated */
  final int     casFeatCode_word_string;
  /** @generated */ 
  public String getWord_string(int addr) {
        if (featOkTst && casFeat_word_string == null)
      jcas.throwFeatMissing("word_string", "is.merkor.relationextraction.types.Word");
    return ll_cas.ll_getStringValue(addr, casFeatCode_word_string);
  }
  /** @generated */    
  public void setWord_string(int addr, String v) {
        if (featOkTst && casFeat_word_string == null)
      jcas.throwFeatMissing("word_string", "is.merkor.relationextraction.types.Word");
    ll_cas.ll_setStringValue(addr, casFeatCode_word_string, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Word_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_word_string = jcas.getRequiredFeatureDE(casType, "word_string", "uima.cas.String", featOkTst);
    casFeatCode_word_string  = (null == casFeat_word_string) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_word_string).getCode();

  }
}



    