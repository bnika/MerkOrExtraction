
/* First created by JCasGen Mon May 31 15:09:45 GMT 2010 */
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
public class POS_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (POS_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = POS_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new POS(addr, POS_Type.this);
  			   POS_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new POS(addr, POS_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = POS.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("is.merkor.relationextraction.types.POS");
 
  /** @generated */
  final Feature casFeat_word_class;
  /** @generated */
  final int     casFeatCode_word_class;
  /** @generated */ 
  public String getWord_class(int addr) {
        if (featOkTst && casFeat_word_class == null)
      jcas.throwFeatMissing("word_class", "is.merkor.relationextraction.types.POS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_word_class);
  }
  /** @generated */    
  public void setWord_class(int addr, String v) {
        if (featOkTst && casFeat_word_class == null)
      jcas.throwFeatMissing("word_class", "is.merkor.relationextraction.types.POS");
    ll_cas.ll_setStringValue(addr, casFeatCode_word_class, v);}
    
  
 
  /** @generated */
  final Feature casFeat_casus;
  /** @generated */
  final int     casFeatCode_casus;
  /** @generated */ 
  public String getCasus(int addr) {
        if (featOkTst && casFeat_casus == null)
      jcas.throwFeatMissing("casus", "is.merkor.relationextraction.types.POS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_casus);
  }
  /** @generated */    
  public void setCasus(int addr, String v) {
        if (featOkTst && casFeat_casus == null)
      jcas.throwFeatMissing("casus", "is.merkor.relationextraction.types.POS");
    ll_cas.ll_setStringValue(addr, casFeatCode_casus, v);}
    
  
 
  /** @generated */
  final Feature casFeat_TreeTaggerTag;
  /** @generated */
  final int     casFeatCode_TreeTaggerTag;
  /** @generated */ 
  public String getTreeTaggerTag(int addr) {
        if (featOkTst && casFeat_TreeTaggerTag == null)
      jcas.throwFeatMissing("TreeTaggerTag", "is.merkor.relationextraction.types.POS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_TreeTaggerTag);
  }
  /** @generated */    
  public void setTreeTaggerTag(int addr, String v) {
        if (featOkTst && casFeat_TreeTaggerTag == null)
      jcas.throwFeatMissing("TreeTaggerTag", "is.merkor.relationextraction.types.POS");
    ll_cas.ll_setStringValue(addr, casFeatCode_TreeTaggerTag, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public POS_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_word_class = jcas.getRequiredFeatureDE(casType, "word_class", "uima.cas.String", featOkTst);
    casFeatCode_word_class  = (null == casFeat_word_class) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_word_class).getCode();

 
    casFeat_casus = jcas.getRequiredFeatureDE(casType, "casus", "uima.cas.String", featOkTst);
    casFeatCode_casus  = (null == casFeat_casus) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_casus).getCode();

 
    casFeat_TreeTaggerTag = jcas.getRequiredFeatureDE(casType, "TreeTaggerTag", "uima.cas.String", featOkTst);
    casFeatCode_TreeTaggerTag  = (null == casFeat_TreeTaggerTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_TreeTaggerTag).getCode();

  }
}



    