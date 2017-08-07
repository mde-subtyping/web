package metamodel.sm

import fma.metamodel.subtyping.utils.ModelTypeUtils
import maude4j.MaudeDaemon
import spock.lang.Specification

class test_sle17_sm_subtyping extends Specification {
	
	MaudeDaemon maudeDaemon;
	String result = ''
	
	private Boolean eval(String sourceMetamodelPath, String sourceRootClassName, String targetMetamodelPath, String targetRootClassName) {
		
		def String os = "OS_MACOSX";
		def String maudePath = "/Users/ab373/Documents/ArturData/SOFTWARE/maude/maude_2.6/maude.intelDarwin";
		def String fullMaudePath = "/Users/ab373/Documents/ArturData/SOFTWARE/maude/maude2_6/full-maude26.maude";
		def String logPath = "./";
		def String logName = "maude.log";
//		def fmaCodePath = TestProperties.fmaCodePath
		def fmaCodePath = '/Users/ab373/Documents/ArturData/WORK/git/fma.maude/fma.maude/maude/fase18/fmaOcl-unit-refSem-proc-typeInf/000_LOAD_SUBTYPING.maude'
		
		def ModelTypeUtils tool = new ModelTypeUtils(os, maudePath, fullMaudePath, logPath, logName, fmaCodePath)
		def result = tool.isSubtypeOf(sourceMetamodelPath,sourceRootClassName,targetMetamodelPath,targetRootClassName)

		return result
	}
	
	public void "subtypeOf__sm1_sm1"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm1.ecore"
		def sRoot = 'StateMachine'
		def tMMPath = "src/test/resources/sle17/sm/subtyping/sm1.ecore"
		def tRoot = "StateMachine"

		boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
		expect: 
			result == true
	}
	
	public void "subtypeOf__sm1_sm2"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm1.ecore"
		def sRoot = 'StateMachine'
		def tMMPath = "src/test/resources/sle17/sm/subtyping/sm2.ecore"
		def tRoot = "StateMachine"
		
		boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
		expect: 
			result == true
	}
	
	public void "subtypeOf__sm1_sm3"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm1.ecore"
		def sRoot = 'StateMachine'
		def tMMPath = "src/test/resources/sle17/sm/subtyping/sm3.ecore"
		def tRoot = "StateMachine"
		
		boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
		expect: 
			result == false
	}
	
	public void "subtypeOf__sm1_sm4"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm1.ecore"
		def sRoot = 'StateMachine'
		def tMMPath = "src/test/resources/sle17/sm/subtyping/sm4.ecore"
		def tRoot = "StateMachine"
		
		boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
		expect: 
			result == false
	}
	
	public void "subtypeOf__sm1_sm5"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm1.ecore"
		def sRoot = 'StateMachine'
		def tMMPath = "src/test/resources/sle17/sm/subtyping/sm5.ecore"
		def tRoot = "StateMachine"
		
		boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
		expect: 
			result == false
	}
	
	public void "subtypeOf__sm2_sm1"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm2.ecore"
		def sRoot = 'StateMachine'
		def tMMPath = "src/test/resources/sle17/sm/subtyping/sm1.ecore"
		def tRoot = "StateMachine"
		
		boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
		expect: 
			result == false
	}
	
	public void "subtypeOf__sm2_sm2"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm2.ecore"
		def sRoot = 'StateMachine'
		def tMMPath = "src/test/resources/sle17/sm/subtyping/sm2.ecore"
		def tRoot = "StateMachine"
		
		boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
		expect: 
			result == true
	}
	
	public void "subtypeOf__sm2_sm3"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm2.ecore"
		def sRoot = 'StateMachine'
		def tMMPath = "src/test/resources/sle17/sm/subtyping/sm3.ecore"
		def tRoot = "StateMachine"
		
		boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
		expect: 
			result == false
	}
	
	public void "subtypeOf__sm2_sm4"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm2.ecore"
		def sRoot = 'StateMachine'
		def tMMPath = "src/test/resources/sle17/sm/subtyping/sm4.ecore"
		def tRoot = "StateMachine"
		
		boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
		expect: 
			result == false
	}
	
	public void "subtypeOf__sm2_sm5"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm2.ecore"
		def sRoot = 'StateMachine'
		def tMMPath = "src/test/resources/sle17/sm/subtyping/sm5.ecore"
		def tRoot = "StateMachine"
		
		boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
		expect: 
			result == false
	}
	
	public void "subtypeOf__sm3_sm1"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm3.ecore"
		def sRoot = 'StateMachine'
		def tMMPath = "src/test/resources/sle17/sm/subtyping/sm1.ecore"
		def tRoot = "StateMachine"
		
		boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
		expect: 
			result == true
	}
	
	public void "subtypeOf__sm3_sm2"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm3.ecore"
		def sRoot = 'StateMachine'
		def tMMPath = "src/test/resources/sle17/sm/subtyping/sm2.ecore"
		def tRoot = "StateMachine"
		
		boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
		expect: 
			result == true
	}
	
	public void "subtypeOf__sm3_sm3"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm3.ecore"
		def sRoot = 'StateMachine'
		def tMMPath = "src/test/resources/sle17/sm/subtyping/sm3.ecore"
		def tRoot = "StateMachine"
		
		boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
		expect: 
			result == true
	}
	
	public void "subtypeOf__sm3_sm4"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm3.ecore"
		def sRoot = 'StateMachine'
		def tMMPath = "src/test/resources/sle17/sm/subtyping/sm4.ecore"
		def tRoot = "StateMachine"
		
		boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
		expect: 
			result == false
	}

	public void "subtypeOf__sm3_sm5"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm3.ecore"
				def sRoot = 'StateMachine'
				def tMMPath = "src/test/resources/sle17/sm/subtyping/sm5.ecore"
				def tRoot = "StateMachine"
				
				boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
				expect: 
					result == false
	}
	
	public void "subtypeOf__sm4_sm1"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm4.ecore"
				def sRoot = 'StateMachine'
				def tMMPath = "src/test/resources/sle17/sm/subtyping/sm1.ecore"
				def tRoot = "StateMachine"
				
				boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
				expect: result == true
	}
	
	public void "subtypeOf__sm4_sm2"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm4.ecore"
				def sRoot = 'StateMachine'
				def tMMPath = "src/test/resources/sle17/sm/subtyping/sm2.ecore"
				def tRoot = "StateMachine"
				
				boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
				expect: result == true
	}
	
	public void "subtypeOf__sm4_sm3"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm4.ecore"
				def sRoot = 'StateMachine'
				def tMMPath = "src/test/resources/sle17/sm/subtyping/sm3.ecore"
				def tRoot = "StateMachine"
				
				boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
				expect: result == false
	}
	
	public void "subtypeOf__sm4_sm4"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm4.ecore"
				def sRoot = 'StateMachine'
				def tMMPath = "src/test/resources/sle17/sm/subtyping/sm4.ecore"
				def tRoot = "StateMachine"
				
				boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
				expect: result == true
	}
	
	public void "subtypeOf__sm4_sm5"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm4.ecore"
				def sRoot = 'StateMachine'
				def tMMPath = "src/test/resources/sle17/sm/subtyping/sm5.ecore"
				def tRoot = "StateMachine"
				
				boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
				expect: result == false
	}
	

	public void "subtypeOf__sm5_sm1"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm5.ecore"
		def sRoot = 'StateMachine'
		def tMMPath = "src/test/resources/sle17/sm/subtyping/sm1.ecore"
		def tRoot = "StateMachine"
		
		boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
		expect: result == true
	}
	
	public void "subtypeOf__sm5_sm2"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm5.ecore"
				def sRoot = 'StateMachine'
				def tMMPath = "src/test/resources/sle17/sm/subtyping/sm2.ecore"
				def tRoot = "StateMachine"
				
				boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
				expect: result == true
	}
	
	public void "subtypeOf__sm5_sm3"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm5.ecore"
				def sRoot = 'StateMachine'
				def tMMPath = "src/test/resources/sle17/sm/subtyping/sm3.ecore"
				def tRoot = "StateMachine"
				
				boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
				expect: result == false
	}
	
	public void "subtypeOf__sm5_sm4"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm5.ecore"
				def sRoot = 'StateMachine'
				def tMMPath = "src/test/resources/sle17/sm/subtyping/sm4.ecore"
				def tRoot = "StateMachine"
				
				boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
				expect: result == false
	}
	

	public void "subtypeOf__sm5_sm5"() {
		def sMMPath =  "src/test/resources/sle17/sm/subtyping/sm5.ecore"
				def sRoot = 'StateMachine'
				def tMMPath = "src/test/resources/sle17/sm/subtyping/sm5.ecore"
				def tRoot = "StateMachine"
				
				boolean result = eval(sMMPath,sRoot,tMMPath,tRoot)
				expect: result == true
	}
	
}
