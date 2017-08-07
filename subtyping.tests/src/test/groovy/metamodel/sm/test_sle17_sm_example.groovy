package metamodel.sm

import org.junit.Test

import fma.integration.use.SolverProperties
import fma.metamodel.subtyping.utils.ModelTypeUtils
import spock.lang.Specification

class test_sle17_sm_example extends Specification {
	
	// this test requires solver.scopesubtype_sm_StateMachine=1..5
	@Test
	public void "test_singleInheritance_isSubtypeOf_inconsistent"() {
		def sMMPath =  "src/test/resources/sle17/sm/example/sm.ecore"
		def sOclPath =  "src/test/resources/sle17/sm/example/sm_ocl.use"
		def tMMPath = "src/test/resources/sle17/sm/example/graph.ecore"
		def tOclPath = "src/test/resources/sle17/sm/example/graph_ocl.use"
		
		def propFilePath = "src/test/resources/sle17/sm/example/subtyping2.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = false
		tool.outputPath="src/test/resources/sle17/sm/example/generated/"
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
			
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == false
	}
	
	@Test
	public void "test_singleInheritance_isSubtypeOf_consistent"() {
		
		def sMMPath =  "src/test/resources/sle17/sm/example/sm.ecore"
		def sOclPath =  "src/test/resources/sle17/sm/example/sm_ocl_correct.use"
		def tMMPath = "src/test/resources/sle17/sm/example/graph.ecore"
		def tOclPath = "src/test/resources/sle17/sm/example/graph_ocl.use"
		
		def propFilePath = "src/test/resources/sle17/sm/example/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = false
		tool.outputPath="src/test/resources/sle17/sm/example/generated/"
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
			
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == true
	}

	// this test requires solver.scopesubtype_sm_StateMachine=1..5
	@Test
	public void "test_multipleInheritance_isSubtypeOf_inconsistent"() {
		def sMMPath =  "src/test/resources/sle17/sm/example/sm.ecore"
		def sOclPath =  "src/test/resources/sle17/sm/example/sm_ocl.use"
		def tMMPath = "src/test/resources/sle17/sm/example/graph.ecore"
		def tOclPath = "src/test/resources/sle17/sm/example/graph_ocl.use"
		
		def propFilePath = "src/test/resources/sle17/sm/example/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = true
		tool.outputPath="src/test/resources/sle17/sm/example/generated/"
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
		
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == false
	}
	
	@Test
	public void "test_multipleInheritance_isSubtypeOf_consistent"() {
		def sMMPath =  "src/test/resources/sle17/sm/example/sm.ecore"
		def sOclPath =  "src/test/resources/sle17/sm/example/sm_ocl_correct.use"
		def tMMPath = "src/test/resources/sle17/sm/example/graph.ecore"
		def tOclPath = "src/test/resources/sle17/sm/example/graph_ocl.use"
		
		def propFilePath = "src/test/resources/sle17/sm/example/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = true
		tool.debugMode = true
		tool.outputPath="src/test/resources/sle17/sm/example/generated/"
		
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
		
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == true
	}

	@Test
	public void "test_multipleInheritance_isSubtypeOf_superEmpty"() {
		def sMMPath =  "src/test/resources/sle17/sm/example/sm.ecore"
		def sOclPath =  "src/test/resources/sle17/sm/example/sm_ocl_correct.use"
		def tMMPath = "src/test/resources/sle17/sm/example/graph.ecore"
		def tOclPath = "src/test/resources/sle17/sm/example/graph_ocl_empty.use"
		
		def propFilePath = "src/test/resources/sle17/sm/example/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = true
		tool.debugMode = true
		tool.outputPath="src/test/resources/sle17/sm/example/generated/"
		
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
		
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == true
	}
	
	@Test
	public void "test_multipleInheritance_isSubtypeOf_deterministicSM"() {
		def sMMPath =  "src/test/resources/sle17/sm/example/sm.ecore"
		def sOclPath =  "src/test/resources/sle17/sm/example/sm_ocl_det.use"
		def tMMPath = "src/test/resources/sle17/sm/example/graph.ecore"
		def tOclPath = "src/test/resources/sle17/sm/example/graph_ocl_mapProperty.use"
		
		def propFilePath = "src/test/resources/sle17/sm/example/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = true
		tool.debugMode = true
		tool.outputPath="src/test/resources/sle17/sm/example/generated/"
		
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
		
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == true
	}

	@Test
	public void "test_multipleInheritance_isSubtypeOf_nondeterministicSM"() {
		def sMMPath =  "src/test/resources/sle17/sm/example/sm.ecore"
		def sOclPath =  "src/test/resources/sle17/sm/example/sm_ocl_nonDet.use"
		def tMMPath = "src/test/resources/sle17/sm/example/graph.ecore"
		def tOclPath = "src/test/resources/sle17/sm/example/graph_ocl_mapProperty.use"
		
		def propFilePath = "src/test/resources/sle17/sm/example/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = true
		tool.debugMode = true
		tool.outputPath="src/test/resources/sle17/sm/example/generated/"
		
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
		
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == false
	}
	
	
	
}
