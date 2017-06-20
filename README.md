# Subtyping in MDE

## Configuration

To use the tool, download the [test Gradle project](https://github.com/mde-subtyping/web/tree/master/subtyping.tests/src/test). The usual structure of a test exercising the subtyping operation is as follows:

* Load the configuration properties for the solver (an example can be found [here](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/resources/emf/model/type/sm/subtyping.properties)) indicating where Maude and the bounds to be used by the model finder:

		SolverProperties.loadPropertiesFile(propFilePath)
	
* Create an instance of the tool and configure it:

		def ModelTypeUtils tool = new ModelTypeUtils()
		// whether the software artifacts involved in solution (reuse metamodel, complement metamodels, etc) are to be persisted
		tool.persistent=true
		// folder where software artifacts will be stored 
		tool.outputPath="src/test/resources/emf/model/type/sm/generated/"
		// whether to use multiple subtyping or strict subtyping
		tool.multipleInheritance = false
	
* Invoke the tool by providing the metamodel specifications:

		tool.isOclConstrainedSubtypeOf(
			sMMPath, // path to subtype EMF metamodel (.ecore)
			sOcl, 	 // string containing OCL constraints for the metamodel above
			tMMPath, // path to supertype EMF metamodel (.ecore)
			tOcl	 // string containing OCL constraints for the metamodel above
		)
	
The tool will determine whether `(sMMPath,sOcl)` denotes a model subtype of the model type denoted by `(tMMPath,tOcl)`. This use case is illustrated below, in Section **Subtyping**. Note that any of the sets of OCL constraints may be empty (with the empty string `""`).

If the check fails, there are two main sources of incompatibilities: the model types denoted by the metamodels, and the OCL constraints. 
1. In the first case, the tool points at the source of the problem by showing the classes of the supertype metamodel `tMMPath` that are not extended by classes of `sMMPath`. That information is useful to assess the advantage of, for example, prunning the supertype metamodel by computing the effective metamodel w.r.t. a specific model management operation. 
2. In the second case, the tool will provide evidence that contradicts the compatibility property of `sMMPath` w.r.t. `tMMPath` in the form of a model represented in EMF notation (that is in XMI format), that invalidates a constraint in `tOcl`.

If the check succeeds, the tool guarantees that `(sMMPath,sOcl)` is a structural refinement of `(tMMPath,tOcl)`. Hence, any EMF model management operation that is defined for `(sMMPath,sOcl)` can be safely applied to models of `(tMMPath,tOcl)`. Going one step further, the tool also facilitates the reuse of such operation by automatically synthesizing an extension metamodel that can be substituted for `tMMPath` in the signature of the operation ensuring its application to models conforming to `(sMMPath,sOcl)` without any further change.	This use case is illustrated with a scenario below, in Section **Reuse of Model Management Operations**.


	

## Subtyping

In the example, we are using the metamodel specifications depicted below for defining graphs ([metamodel](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/resources/emf/model/type/sm/graph.emf) and [OCL constraints](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/resources/emf/model/type/sm/graph_ocl_mapProperty.use)) and deterministic state machines ([metamodel](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/resources/emf/model/type/sm/sm.emf) and [OCL constraints](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/resources/emf/model/type/sm/sm_ocl_det.use)), resp. The model types described by both metamodels are structurally similar in that they both describe languages of graphs.

<img src="images/example.png" height="450">

On the one hand, the top metamodel specification characterizes the graph of a function defined over nodes. On the other hand, the bottom metamodel specification characterizes deterministic state machines where transitions can be triggered by an event (indicated in the *name* attribute of the transition) or are triggerless, e.g. they are completion transitions. 

The following test cases show how to use the tool to check that the state machine metamodel specification denotes a model subtype of the one denoted by the graph metamodel,
* using [strict subtyping (lines 11-30)](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/groovy/metamodel/sm/ModelTypeUtils_OCL_tests.groovy)
* using [multiple subtyping (lines 32-52)](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/groovy/metamodel/sm/ModelTypeUtils_OCL_tests.groovy)

In addition, if we consider non-deterministic state machines by removing the OCL constraint defining the deterministic condition from the state machine metamodel specification as instructed in [this test case (lines 54-72)](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/groovy/metamodel/sm/ModelTypeUtils_OCL_tests.groovy) we obtain a non-deterministic state machine that does not satisfy the graph constraint, which is represented in object diagram notation as follows:

<img src="images/counterexample.png" height="250">

