
/* First created by JCasGen Thu Jun 10 14:31:33 GMT 2010 */
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
public class Pattern_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Pattern_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Pattern_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Pattern(addr, Pattern_Type.this);
  			   Pattern_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Pattern(addr, Pattern_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Pattern.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("is.merkor.relationextraction.types.Pattern");
 
  /** @generated */
  final Feature casFeat_patternString;
  /** @generated */
  final int     casFeatCode_patternString;
  /** @generated */ 
  public String getPatternString(int addr) {
        if (featOkTst && casFeat_patternString == null)
      jcas.throwFeatMissing("patternString", "is.merkor.relationextraction.types.Pattern");
    return ll_cas.ll_getStringValue(addr, casFeatCode_patternString);
  }
  /** @generated */    
  public void setPatternString(int addr, String v) {
        if (featOkTst && casFeat_patternString == null)
      jcas.throwFeatMissing("patternString", "is.merkor.relationextraction.types.Pattern");
    ll_cas.ll_setStringValue(addr, casFeatCode_patternString, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Pattern_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_patternString = jcas.getRequiredFeatureDE(casType, "patternString", "uima.cas.String", featOkTst);
    casFeatCode_patternString  = (null == casFeat_patternString) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_patternString).getCode();

  }
}



    