package metamodel.sm

import org.junit.Test

import fma.integration.use.SolverProperties
import fma.metamodel.subtyping.utils.ModelTypeUtils 
import fma.metamodel.subtyping.utils.Retype
import spock.lang.Specification
import static org.junit.Assert.*

class test_sle17_sm_simulation extends Specification {
	
	@Test
	public void "test_simulation_scenario"() {
		def sMMPath =  "src/test/resources/sle17/sm/simulation/sm.ecore"
		def sOclPath =  "src/test/resources/sle17/sm/simulation/sm_ocl_det.use"
		def tMMPath = "src/test/resources/sle17/sm/simulation/graph.ecore"
		def tOclPath = "src/test/resources/sle17/sm/simulation/graph_ocl_mapProperty.use"
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
		
		def propFilePath = "src/test/resources/sle17/sm/simulation/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		// 1. Generate extension metamodel
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = true
		tool.debugMode = true
		tool.outputPath="src/test/resources/sle17/sm/simulation/generated/"
		
		assertEquals(true,tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl))
		
		// 2. Re-type: as extended
		//    The input model is re-typed to the extension metamodel.
		
		// extension metamodel (generated in step 1)
		String extMMPath =  "src/test/resources/sle17/sm/simulation/binding0_reuse_mm.ecore"
		// input model provided by user
		String modelPath =  "src/test/resources/sle17/sm/simulation/StateMachine1.xmi"
				
		Retype r  = new Retype(sMMPath, extMMPath)
		r.asExtended(modelPath)
		
		// 3. ATL transformation (manual step) with
		//		metamodel: binding0_reuse_mm.ecore (extension metamodel generated in step 1)
		//		model: StateMachine1_renamed (generated in step 2)
		
		// 4. Re-type: as original 
		//    The output model is re-typed to the original metamodel
		modelPath =  "src/test/resources/sle17/sm/simulation/StateMachine1_renamed_out.xmi"
		
		r.asOriginal(modelPath)
		
		expect: 1==1

	}
	
	@Test
	public void "test_multipleInheritance_isSubtypeOf_consistent"() {
		def sMMPath =  "src/test/resources/sle17/sm/simulation/sm.ecore"
		def sOclPath =  "src/test/resources/sle17/sm/simulation/sm_ocl_det.use"
		def tMMPath = "src/test/resources/sle17/sm/simulation/graph.ecore"
		def tOclPath = "src/test/resources/sle17/sm/simulation/graph_ocl_mapProperty.use"
		
		def propFilePath = "src/test/resources/sle17/sm/simulation/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = true
		tool.debugMode = true
		tool.outputPath="src/test/resources/sle17/sm/simulation/generated/"
		
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
		
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == true
	}	
}
