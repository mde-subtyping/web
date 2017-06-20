package metamodel.sm

import org.junit.Test

import fma.integration.use.SolverProperties
import fma.metamodel.subtyping.utils.ModelTypeUtils
import spock.lang.Specification

class ModelTypeUtils_OCL_tests extends Specification {
	
	@Test
	public void "test_singleInheritance_isSubtypeOf_consistent"() {
		def sMMPath =  "src/test/resources/emf/model/type/sm/sm.ecore"
		def sOclPath =  "src/test/resources/emf/model/type/sm/sm_ocl_det.use"
		def tMMPath = "src/test/resources/emf/model/type/sm/graph.ecore"
		def tOclPath = "src/test/resources/emf/model/type/sm/graph_ocl_mapProperty.use"
		
		def propFilePath = "src/test/resources/emf/model/type/sm/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = false
		tool.outputPath="src/test/resources/emf/model/type/sm/generated/"
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
			
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == true
	}

	@Test
	public void "test_multipleInheritance_isSubtypeOf_consistent"() {
		def sMMPath =  "src/test/resources/emf/model/type/sm/sm.ecore"
		def sOclPath =  "src/test/resources/emf/model/type/sm/sm_ocl_det.use"
		def tMMPath = "src/test/resources/emf/model/type/sm/graph.ecore"
		def tOclPath = "src/test/resources/emf/model/type/sm/graph_ocl_mapProperty.use"
		
		def propFilePath = "src/test/resources/emf/model/type/sm/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = true
		tool.outputPath="src/test/resources/emf/model/type/sm/generated/"
		
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
		
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == true
	}
	
	@Test
	public void "test_singleInheritance_isSubtypeOf_inconsistent"() {
		def sMMPath =  "src/test/resources/emf/model/type/sm/sm.ecore"
		def sOclPath =  "src/test/resources/emf/model/type/sm/sm_ocl_nondet.use"
		def tMMPath = "src/test/resources/emf/model/type/sm/graph.ecore"
		def tOclPath = "src/test/resources/emf/model/type/sm/graph_ocl_mapProperty.use"
		
		def propFilePath = "src/test/resources/emf/model/type/sm/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = false
		tool.outputPath="src/test/resources/emf/model/type/sm/generated/"
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
			
		expect: tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl) == false
	}
	
}
