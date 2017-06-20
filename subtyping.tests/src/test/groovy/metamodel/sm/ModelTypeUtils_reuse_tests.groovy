package metamodel.sm

import org.junit.Test

import fma.integration.use.SolverProperties
import fma.metamodel.subtyping.utils.ModelTypeUtils
import fma.metamodel.subtyping.utils.Retype
import spock.lang.Specification

class ModelTypeUtils_reuse_tests extends Specification {
	
	@Test
	public void "test_singleInheritance_isSubtypeOf_consistent"() {
		def sMMPath =  "src/test/resources/emf/model/type/reuse/smEvent.ecore"
		def sOclPath =  "src/test/resources/emf/model/type/reuse/smEvent_ocl_det.use"
		def tMMPath = "src/test/resources/emf/model/type/reuse/sm.ecore"
		def tOclPath = "src/test/resources/emf/model/type/reuse/sm_ocl_det.use"
		
		def propFilePath = "src/test/resources/emf/model/type/reuse/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = false
		tool.outputPath="src/test/resources/emf/model/type/reuse/generated/"
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
			
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == true
	}
	
	public void "testRetype_smEvent_asExtended"() {
		String sourceMMPath =  "src/test/resources/emf/model/type/reuse/smEvent.ecore"
		String extMMPath =  "src/test/resources/emf/model/type/reuse/extension.ecore"
		String modelPath =  "src/test/resources/emf/model/type/reuse/StateMachineEvent.xmi"
				
		Retype r  = new Retype(sourceMMPath, extMMPath)
		r.asExtended(modelPath)
		
		// The model is generated at 
		// src/test/resources/emf/model/type/reuse/StateMachineEvent_renamed.xmi

		expect: 1==1
	}
	
	public void "testRetype_smEvent_asOriginal"() {
		String sourceMMPath =  "src/test/resources/emf/model/type/reuse/smEvent.ecore"
		String extMMPath =  "src/test/resources/emf/model/type/reuse/extension.ecore"
		String modelPath =  "src/test/resources/emf/model/type/reuse/StateMachineEvent-extended.xmi"
		
		Retype r  = new Retype(sourceMMPath, extMMPath)
		r.asOriginal(modelPath)
		
		// The model is generated at
		// src/test/resources/emf/model/type/reuse/StateMachineEvent-extended_original.xmi
		
		expect: 1==1
	}
}
