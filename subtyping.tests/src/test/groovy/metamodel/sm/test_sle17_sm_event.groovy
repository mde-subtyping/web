package metamodel.sm

import org.junit.Test

import fma.integration.use.SolverProperties
import fma.metamodel.subtyping.utils.ModelTypeUtils
import spock.lang.Specification

class test_sle17_sm_event extends Specification {
	

	@Test
	public void "test_multipleInheritance_isSubtypeOf_smEvent_sm"() {
		def sMMPath = "src/test/resources/sle17/sm/evolution/smEvent.ecore"
		def sOclPath =  "src/test/resources/sle17/sm/evolution/smEvent_ocl_det.use"

		def tMMPath =  "src/test/resources/sle17/sm/evolution/smObservation_prunned.ecore"
		def tOclPath =  "src/test/resources/sle17/sm/evolution/sm_ocl_det.use"
		
		def propFilePath = "src/test/resources/sle17/sm/evolution/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = true
		tool.debugMode = true
		tool.outputPath="src/test/resources/sle17/sm/evolution/generated/"
		
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
		
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == true
	}

		
	
	@Test
	public void "test_multipleInheritance_isSubtypeOf_smEventWithOcl_smWithOcl"() {
		def sMMPath = "src/test/resources/sle17/sm/evolution/smEvent.ecore"
		def sOclPath =  "src/test/resources/sle17/sm/evolution/smEvent_ocl_det.use"
		
		def tMMPath =  "src/test/resources/sle17/sm/evolution/smObservation_prunned.ecore"
		def tOclPath =  "src/test/resources/sle17/sm/evolution/sm_ocl_det.use"
		
		def propFilePath = "src/test/resources/sle17/sm/evolution/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = true
		tool.debugMode = true
		tool.outputPath="src/test/resources/sle17/sm/evolution/generated/"
		
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
		
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == true
	}
	
	@Test
	public void "test_multipleInheritance_isSubtypeOf_smEventWithoutOcl_smWithOcl"() {
		def sMMPath = "src/test/resources/sle17/sm/evolution/smEvent.ecore"
		def tMMPath =  "src/test/resources/sle17/sm/evolution/smObservation_prunned.ecore"
		def tOclPath =  "src/test/resources/sle17/sm/evolution/sm_ocl_det.use"
		
		def propFilePath = "src/test/resources/sle17/sm/evolution/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = true
		tool.debugMode = true
		tool.outputPath="src/test/resources/sle17/sm/evolution/generated/"
		
		
		def sOcl = ''
		def tOcl = new File(tOclPath).text
		
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == false
	}
	
	@Test
	public void "test_multipleInheritance_isSubtypeOf_smEventWithOcl_smWithoutOcl"() {
		def sMMPath = "src/test/resources/sle17/sm/evolution/smEvent.ecore"
		def sOclPath =  "src/test/resources/sle17/sm/evolution/smEvent_ocl_det.use"
		
		def tMMPath =  "src/test/resources/sle17/sm/evolution/smObservation_prunned.ecore"
		
		def propFilePath = "src/test/resources/sle17/sm/evolution/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = true
		tool.debugMode = true
		tool.outputPath="src/test/resources/sle17/sm/evolution/generated/"
		
		
		def sOcl = new File(sOclPath).text
		def tOcl = ''
		
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == true
	}

	@Test
	public void "test_multipleInheritance_isSubtypeOf_smEventWithoutOcl_smWithoutOcl"() {
		def sMMPath = "src/test/resources/sle17/sm/evolution/smEvent.ecore"
		def tMMPath =  "src/test/resources/sle17/sm/evolution/smObservation_prunned.ecore"
		
		def propFilePath = "src/test/resources/sle17/sm/evolution/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = true
		tool.debugMode = true
		tool.outputPath="src/test/resources/sle17/sm/evolution/generated/"
		
		def sOcl = ''
		def tOcl = ''
		
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == true
	}
	
	
}
