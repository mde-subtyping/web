package metamodel.sm

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Test

import fma.integration.use.SolverProperties
import fma.metamodel.effective.EffectiveStructuralMetamodel
import fma.metamodel.subtyping.utils.ModelTypeUtils
import fma.metamodel.subtyping.utils.Retype
import moment2.registry.EmfMetamodelRegistry
import spock.lang.Specification
import static org.junit.Assert.*

class test_sle17_evolution extends Specification {
	
	@Test
	public void "test_evolution_scenario"() {
		def sMMPath =  "src/test/resources/sle17/sm/evolution/smEvent.ecore"
		def sOclPath =  "src/test/resources/sle17/sm/evolution/smEvent_ocl_det.use"
		def tMMPath = "src/test/resources/sle17/sm/evolution/smObservation.ecore"
		def tOclPath = "src/test/resources/sle17/sm/evolution/smObservation_ocl_det.use"
		
		def propFilePath = "src/test/resources/sle17/sm/evolution/subtyping.properties"
		SolverProperties.loadPropertiesFile(propFilePath)
		
		// --------------------------------------------------------------------------------
		// 1. Check subtyping
		def ModelTypeUtils tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = true
		tool.outputPath="src/test/resources/sle17/sm/evolution/generated/"
		
		def sOcl = new File(sOclPath).text
		def tOcl = new File(tOclPath).text
			
		assertEquals(tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tMMPath, tOcl), false)
		
		// the generated binding (sle17.sm.evolution.generated/binding.xmi) together with 
		// the uncovered supertype (sle17.sm.evolution.generated/binding0_supertype_uncovered.ecore) 
		// show that class Observation is not covering any metamodel
		
		// --------------------------------------------------------------------------------
		// 2. extract effective metamodel
		def Map<String,Set<String>> featuresOfInterest = [
			"StateMachine" : ["name", "initial", "final", "nodes", "edges"] as Set<String>,
			"State" : ["name", "subMachines"] as Set<String>,
			"Transition" : ["name", "source", "target"] as Set<String>,
		] 
		
		def EmfMetamodelRegistry mmReg = new EmfMetamodelRegistry()
		def Resource supertypeMM = mmReg.loadMetamodel(tMMPath)
		def String tPrunnedMMPath = tMMPath.minus(".ecore") + "_prunned.ecore"
		def Resource prunnedSupertypeMM = mmReg.createModel(tPrunnedMMPath)
		
		def effMM = new EffectiveStructuralMetamodel(supertypeMM, featuresOfInterest)
		prunnedSupertypeMM.contents.addAll(EcoreUtil.copyAll(effMM.emmResource.contents))
		prunnedSupertypeMM.save(null)
		
		// --------------------------------------------------------------------------------
		// 3. check subtyping with prunned supertype
		
		tool = new ModelTypeUtils()
		tool.persistent=true
		tool.multipleInheritance = true
		tool.outputPath="src/test/resources/sle17/sm/evolution/generated/"
		
		assertEquals(true, tool.isOclConstrainedSubtypeOf(sMMPath, sOcl, tPrunnedMMPath, tOcl))
		
		// --------------------------------------------------------------------------------
		// Reuse of code generation 

		// 4. Transformation execution (manual step using ATL)		
		// the ATL transformation in subtyping.sm.atl.evolution can be executed
		// for the metamodel subtyping.sm.atl.evolution/model/smObservation.ecore
		// with the model subtyping.sm.atl.evolution/model/StateMachine.xmi
		
		// the reuse metamodel is sle17.sm.evolution.generated/binding0_reuse_mm.ecore
		// which has been pasted onto subtyping.sm.atl.evolution/extended/
				
		// 5. Retype model subtyping.sm.atl.evolution/extended/StateMachineEvent.xmi
		//    subtyping.sm.atl.evolution/extended/smEvent.ecore to
		//    subtyping.sm.atl.evolution/extended/binding0_reuse_mm.ecore
		String sourceMMPath =  "src/test/resources/sle17/sm/evolution/smEvent.ecore"
		String extMMPath =  "src/test/resources/sle17/sm/evolution/generated/binding0_reuse_mm.ecore"
		String modelPath =  "src/test/resources/sle17/sm/evolution/StateMachineEvent.xmi"
				
		Retype r  = new Retype(sourceMMPath, extMMPath)
		r.asExtended(modelPath)
		// this step generates 'src/test/resources/sle17/sm/evolution/StateMachineEvent_renamed.xmi'
		
		// 6. REUSE STEP: execute the transformation (manual step using ATL) using
		//    the metamodel subtyping.sm.atl.evolution/extended/binding0_reuse_mm.ecore
		//    and the model src/test/resources/sle17/sm/evolution/StateMachineEvent_renamed.xmi
		
		expect: 1==1
	}
	
	
	
}
