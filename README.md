# Structural Model Subtyping with OCL Constraints

This document describes how to install and use the tool supporting the results of the paper **Structural Model Subtyping with OCL Constraints** by **Artur Boronat (University of Leicester, UK)**, accepted for publication at [SLE@SPLASH'17](http://conf.researchr.org/track/sle-2017/sle-2017-papers).

## Abstract

Our tool facilitates the reuse of model management operations that are defined for metamodel specifications, which consist of an EMF metamodel together with its well-formedness constraints (in OCL). Specifically, our tool infers whether two metamodel specifications, whose metamodels and constraints need not be related a priori, are *compatible*. This captures the notion of subtype polymorphism in model management operations using structural model subtyping with optional OCL constraints. Our tool implements the structural subtyping relationship in a type-theoretic framework with multiple inheritance semantics and can be used for implementing various use cases in flexible model-driven engineering, for example: structural (semantic) refinement, evolution of domain-specific modelling languages, and reuse of model management operations. 

To deal with structural subtyping, the tool synthesizes an extension metamodel that is employed both to check the compatibility of OCL constraints and to reuse model management operations without requiring manual intervention. The extension metamodel can be obtained both for single and multiple inheritance contexts. Moreover, the tool produces complements of the supertype and subtype metamodel, w.r.t. the chosen subtyping relation, which can be used for guiding the reuse model management operations. In addition, our structural subtyping mechanism is expressive enough so as to support variants of model subtyping, including multiple, partial, and dynamic model subtyping.

Our tool is available as a Java library together with a self-contained Gradle project that includes all the dependencies required for using the tool together with additional resources implementing the examples of the paper. The tool has been tested on `macOS 10.12.4 (Sierra)` and `Linux Ubuntu 15.04 (Vivid Vervet)`. In addition, a VirtualBox image with the Linux configuration is available for download.

This document contains three main sections: an overview of the contents in the zipped file, an installation guide, and step-by-step instructions arranged in the form of specific scenarios for each of the use cases mentioned above. These step-by-step instructions illustrate the core contributions of the paper and show the full extent of the examples used in it. Moreover, these scenarios have been implemented in executable test cases in order to ensure the experimental reproducibility of the results of the paper. 



## Overview: content of the archive

The contents of the archive are as follows:
* [README.md](https://github.com/mde-subtyping/web/blob/master/README.md): this document
  * Setup/installation guide
    * Environment assumptions for using the tool
    * Getting the tool
    * Linux (VirtualBox): VirtualBox image with Eclipse pre-configured
  * Step-by-step instructions
    * Using the tool programmatically
    * Scenario 1: Structural subtyping (**Subtyping**)
    * Scenario 2: Structural subtyping with OCL constraints (**Subtyping, Multiple-Typing**)
    * Scenario 3: DSML evolution (**Reuse, Partial Typing**)
    * Scenario 4: Stepwise Simulation of Deterministic State Machines (**Reuse, Dynamic Typing**)
  * License
  * Credits
* [README_offline.md](https://github.com/mde-subtyping/web/blob/master/README_offline.md): offline version of the file `README.md`. The difference between these two files is that `README.md` is readable on GitHub and `README_offline.md` is pretty printed as `.README_offline.md.html`, which is readable in a browser offline - that is URL paths are accessible using `file:///` in the browser.
* [.README_offline.md.html](https://github.com/mde-subtyping/web/blob/master/.README_offline.md.html): `README_offline.md` in HTML format. Images should be displayed correctly and there is no need to access any external resource unless the VirtualBox image is downloaded, in which case Google Drive will be accessed.
* Three projects used in the examples that illustrate the step-by-step instructions:
  * [subtyping.tests](https://github.com/mde-subtyping/web/blob/master/subtyping.tests): main project that contains the main test cases and shows how to invoke the tool programmatically, including 
    * how the tool supports **structural subtyping with optional OCL constraints** for checking structural refinement between metamodel specifications, formed by a metamodel and well-formedness constraints in OCL;
    * **multiple model subtyping**;
    * an scenario on DSML evolution illustrating how the tool provides support for **partial model subtyping**;
    * an scenario on state machine simulation illustrating how the tool provides support for **dynamic model subtyping**.
  * [subtyping.sm.atl](subtyping.sm.atl): ATL transformation (evolution) - ATL project containing the transformation needed to test the evolution scenario.
  * [subtyping.sm.atl.simulation](subtyping.sm.atl.simulation): ATL transformation (simulation) - ATL project containing the transformation needed to test the simulation scenario.


## Setup/installation guide


### Environment assumptions to use the tool

The tool has been tested on `macOS 10.12.4 (Sierra)` and `Linux Ubuntu 15.04 (Vivid Vervet)`. A VirtualBox image with Linux Ubuntu 15.04 is available, as explained below. 

The base technology that is required to run the tool is as follows:

* [Java 1.8](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
* [Groovy 2.4](http://groovy-lang.org/download.html)
* [Gradle 4.0](https://gradle.org/install/)

Although the tool is usable in Eclipse-independent standalone mode, we recommend the use of Eclipse Modeling Tools for editing the models. To work with [Eclipse Oxygen (Modelling Package)](http://www.eclipse.org/downloads/packages/eclipse-modeling-tools/oxygenr), the following plugins need to be installed:

* [ATL](http://www.eclipse.org/atl/download/)
* [Groovy integration plugin](https://github.com/groovy/groovy-eclipse/wiki)
* [Gradle Buildship Integration 2.0](https://marketplace.eclipse.org/content/buildship-gradle-integration)
* [Epsilon Platform](https://marketplace.eclipse.org/content/epsilon) 

### Getting the tool 

1. Get the tool 
    * From **GitHub**: clone the git repository: `https://github.com/mde-subtyping/web.git`.
    * From the **zipped file**: you should have unzipped the file to get this README file already.
2. Import the project with test cases:
    * From Java perspective: `Import > Gradle project` 
    * Select the project folder `subtyping.tests` in your git repository.
    * If prompted `Overwrite existing Eclipse project descriptors?`, choose `Overwrite`.
    * Use the option `Gradle wrapper`.
3. Import the ATL projects using `Import > Import Existing Projects into Workspace`.

By default, the tool is configured to run the examples for **macOS**. 

To run the tool on **Linux**, some configuration arguments need to be edited in the project `subtyping.tests`:
* In each of the packages under the folder `src/test/resources`, edit the file `subtyping.properties` and change the following parameter bindings as follows:

		maude.os=OS_LINUX
		maude.maudePath=src/test/resources/maude/maude.linux64 

* Please check that the file `src/test/resources/maude/maude.linux64` is executable, or else modify the corresponding access permissions using `chmod`.

After that explore the test cases under `src/test/groovy`, which can be run as JUnit test cases. Follow the step-by-step instructions given below.

### Linux (VirtualBox)

A VirtualBox image with `Linux Ubuntu 15.04 (Vivid Vervet)` and Eclipse configured to run the test cases is [available for download](https://docs.oracle.com/cd/E26217_01/E26796/html/qs-import-vm.html).

1. Download and install [VirtualBox](https://www.virtualbox.org/wiki/Downloads).
2. Download the VirtualBox image `Ubuntu 15.04_SLE17.ova` from [Google Drive](https://drive.google.com/file/d/0ByBrw5bkMQahOTZKTU1reHhXMk0/view?usp=sharing).
3. [Import the appliance in VirtualBox](https://docs.oracle.com/cd/E26217_01/E26796/html/qs-import-vm.html).
4. In VirtualBox, right click on image and `Start > Normal Start`.
5. Use the following credentials (User/password): ubuntu/reverse.
6. There is a direct link to `Eclipse Oxygen` on the desktop. Run Eclipse.
7. Follow the step-by-step instructions given below.




## Step-by-step instructions

In the following subsections, we provide the examples used to illustrate the core contributions in the paper.


### Using the tool programmatically

The usual structure of a test exercising the subtyping operation is as follows:

* Load the configuration properties for the solver (an example can be found [here](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/resources/sle17/sm/example/subtyping.properties)) indicating where Maude is stored and the bounds to be used by the model finder:

		SolverProperties.loadPropertiesFile(propFilePath)

* Create an instance of the tool and configure it:

		def ModelTypeUtils tool = new ModelTypeUtils()
		// whether the software artifacts involved in solution (reuse metamodel, complement metamodels, etc) are to be persisted
		tool.persistent=true
		// folder where software artifacts will be stored 
		tool.outputPath="src/test/resources/sle17/sm/example/generated/"
		// whether to use multiple subtyping or strict subtyping
		tool.multipleInheritance = false

* Invoke the tool by providing the metamodel specifications:

		tool.isOclConstrainedSubtypeOf(
			sMMPath, // path to subtype EMF metamodel (.ecore)
			sOcl, 	 // string containing OCL constraints for the metamodel above, or empty string otherwise
			tMMPath, // path to supertype EMF metamodel (.ecore)
			tOcl	 // string containing OCL constraints for the metamodel above, or empty string otherwise
		)


The tool will determine whether `(sMMPath,sOcl)` denotes a model subtype of the model type denoted by `(tMMPath,tOcl)`. This use case is illustrated in more detail below, in **Scenario 1: Structural subtyping (Subtyping, Structural Refinement)** and in **Scenario 2: Structural subtyping with OCL constraints (Semantic Refinement, Multiple Typing)**. Note that any of the sets of OCL constraints may be empty (with the empty string `""`).
  * The tool generates the model binding `binding.xmi` containing bindings representing the possible multiple typings of classifiers in the subtype with classifiers of the supertype. This model is used to obtain:
    * `binding0_reuse_mm.ecore`: extension metamodel for reusing model management operations;
    * `binding0_subtype_uncovered.ecore`: classifiers of subtype that have not been covered by the subtyping relation (data types are always included);
    * `binding0_supertype_uncovered.ecore`: classifiers of supertype that have not been covered by the subtyping relation (data types are always included);
    * `binding0_virtual_mm.ecore`: extension metamodel used for the analysis of OCL constraints (supertype classifiers are abstract);
    * `binding0.xmi`: subtypeOf relationships considered.
  * If `tool.multipleInheritance = true` when configuring the tool instance, subsequent `bindingX files represent the strict typing solutions by order of recommendation prioritizing those with a better coverage of subtype classifiers with supertype classifiers, as explained in the paper. For each strict subtyping solution *X*, the models described above are also synthesized.

If the check fails, there are two main sources of incompatibilities: the model types denoted by the metamodels, and the OCL constraints. 
1. In the first case, the tool points at the source of the problem by showing the classes of the supertype metamodel `tMMPath` that are not extended by classes of `sMMPath` in the supertype complement `bindingX_supertype_uncovered.ecore`. That information is useful to assess the advantage of, for example, prunning the supertype metamodel by computing the effective metamodel w.r.t. a specific model management operation, as illustrated in **Scenario 3: DSML evolution (Reuse, Partial Typing)**.
2. In the second case, the tool will provide evidence that contradicts the compatibility property of `sOcl` w.r.t. `tOcl` in the form of a model conforming to the synthesized extension metamodel `bindingX_virtual_mm.ecore`, represented in EMF notation (that is in XMI format), that invalidates a constraint in `tOcl`. Note that the solver needs to be configured appropriately, in the configuration file as explained above, for increasing the likelihood of finding a problem if it exists.

If the check succeeds, the tool guarantees that `(sMMPath,sOcl)` is a structural refinement of `(tMMPath,tOcl)`. Hence, any EMF model management operation that is defined for `(sMMPath,sOcl)` can be safely applied to models of `(tMMPath,tOcl)`. Going one step further, the tool also facilitates the reuse of such operation by automatically synthesizing an extension metamodel `bindingX_reuse_mm.ecore` that can be substituted for `tMMPath` in the signature of the operation ensuring its application to models conforming to `(sMMPath,sOcl)` without any further change.	This use case is illustrated with two scenarios: **Scenario 3: DSML evolution (Reuse, Partial Typing)** and **Scenario 4: Stepwise Simulation of Deterministic State Machines (Reuse, Dynamic Typing)**.

The scenarios below illustrate specific examples that have been used to support the results in the paper. These scenarios have been implemented in test cases for ensuring the reproducibility of the results.

**Note:** for inspecting the generated models using model editors in Eclipse, such as the EMF reflective editor or [Exeed](http://www.eclipse.org/epsilon/doc/articles/inspect-models-exeed/), the corresponding metamodel needs to be registered using Epsilon's `Register EPackages` facility in advance. This option appears when right-clicking on a metamodel `.ecore` in Eclipse. The reason for this is that synthesized metamodels reuse information from the original metamodels and the EMF registry needs to be updated to fetch the corresponding metamodel when loading a model.


### Scenario 1: Structural subtyping (Subtyping, Structural Refinement) 

In this section, we show the expressivity of our structural subtyping operation (without OCL constraints) with respect to model typing [^Steel07] [^Guy12]. We compare our approach to model subtyping by considering their example with the following state machine metamodels:

[^Steel07]: Jim Steel, Jean-Marc Jézéquel. On model typing. Software and Ssytem Modeling 6, 4 (2007), 401-413.
[^Guy12]: Clement Guy, Benoît Combemale, Steven Derrien, Jim Steel, Jean-Marc Jézequel. On Model Subtyping. ECMFA 2012. 400-415.

<img src="images/sm_subtyping.png" height="450">

where changes have been highlighted. 

The test cases implementing the subtyping checks can be found [here](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/groovy/metamodel/sm/test_sle17_sm_subtyping.groovy) and the results are summarized in the following table:

| subtypeOf | sm1 | sm2 | sm3 | sm4 | sm5 | 
|--|--|--|--|--|--|
| sm1 | true | **true** | false | false | false | 
| sm2 | false | true | false | false | false | 
| sm3 | true | **true** | true | false | false | 
| sm4 | true | **true** | false | true | false | 
| sm5 | true | **true** | false | false | true | 

These results are consistent with those presented in [^Steel07] but for the cases where many-bounded references in a supertype are constrained by a lower upper bound in the corresponding subtype. In our tool, those cases are valid.


### Scenario 2: Structural subtyping with OCL constraints (Semantic Refinement, Multiple Typing)

In this section, we use the main example of the paper for illustrating how **multiple typings** can be applied to a metamodel at the classifier level, that is, a class of the subtype metamodel can be typed by more than one class in the supertype metamodel. The generalization of multiple typing at the metamodel level, where several metamodels can be used as supertype of the same subtype metamodel, is supported by providing an extension metamodel for each different pair of metamodel specifications. However, as each such extension metamodel is linked to a different pair of metamodel specifications, with the intention of reusing a model management operation in a given context, we restrict ourselves to an example with one single model management operation.

In the example, we are using the metamodel specifications depicted below for defining graphs ([metamodel](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/resources/sle17/sm/example/graph.emf) and [OCL constraints](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/resources/sle17/sm/example/graph_ocl_mapProperty.use)) and deterministic state machines ([metamodel](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/resources/sle17/sm/example/sm.emf) and [OCL constraints](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/resources/sle17/sm/example/sm_ocl_det.use)), resp. The model types described by both metamodels are structurally similar in that they both describe languages of graphs.

<img src="images/example.png" height="450">

On the one hand, the top metamodel specification characterizes the graph of a function defined over nodes. On the other hand, the bottom metamodel specification characterizes deterministic state machines where transitions can be triggered by an event (indicated in the *name* attribute of the transition) or are triggerless, e.g. they are completion transitions. 

[These test cases](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/groovy/metamodel/sm/test_sle17_sm_example.groovy) show how to use the tool to check that the state machine metamodel specification denotes a model subtype of the one denoted by the graph metamodel:
* Using [strict subtyping (test_singleInheritance_isSubtypeOf_consistent)](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/groovy/metamodel/sm/test_sle17_sm_example.groovy):
* Using [multiple subtyping (test_multipleInheritance_isSubtypeOf_consistent)](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/groovy/metamodel/sm/test_sle17_sm_example.groovy):
  
Moreover, if we consider non-deterministic state machines by removing the OCL constraint defining the deterministic condition from the state machine metamodel specification as instructed in [this test case (test_singleInheritance_isSubtypeOf_inconsistent)](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/groovy/metamodel/sm/test_sle17_sm_example.groovy) we obtain a non-deterministic state machine that does not satisfy the graph constraint, which is represented in object diagram notation as follows:

<img src="images/counterexample.png" height="250">

The counterexample in generated in the folder `temp/model`, where `temp` is the temporary folder specified in the corresponding *subtyping.properties* file. The counterexample is a model conforming to the virtual metamodel and can be *casted down* to the subtype as explained in the sections below. 

[These test cases](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/groovy/metamodel/sm/test_sle17_sm_event.groovy) demonstrate the usage of the possible combinations of OCL constraints with metamodels for checking structural subtyping, showing that OCL constraints are optional in metamodel specifications.




### Scenario 3: DSML evolution (Reuse, Partial Typing)


In this section, we are going to show how to reuse a model management operation - in this case, a model-to-text transformation with ATL - for a modified version of the state machine metamodel. In this scenario, we discuss how to use the tool to reuse an ATL model transformation defined for a metamodel `version 1` for models of a metamodel `version 2` when the metamodel `version 1` is not exactly a supertype of the metamodel `version 2`. In addition, we illustrate that the ATL model transformation can be applied even if a model is only **partially typed** by the metamodel involved in the ATL transformation.

The steps involved in this scenario relating to our tool are fully automated in the test case [test_evolution_scenario](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/groovy/metamodel/sm/test_sle17_evolution.groovy). The steps that require interaction with ATL need to be carried out manually though.

[Initial state machine metamodel](https://github.com/mde-subtyping/web/blob/master/subtyping.sm.atl/models/smObservation.emf) (**version 1**):

<img src="images/mm_SmObservation.png" height="300">

We have developed an [ATL transformation](https://github.com/mde-subtyping/web/blob/master/subtyping.sm.atl/trafo/sm.atl) that serializes a state machine conforming to the previous metamodel into the [format proposed by Martin Fowler](http://www.informit.com/articles/article.aspx?p=1592379&seqNum=3):

	query SM2Text = sm!StateMachine.allInstances()
			->asSequence()
			->first().compile().println();
	
	helper context sm!StateMachine def: compile() : String =
		'events\n' +
		self.edges->iterate(e; acc : String = '' | acc + '  ' + e.name + '\n') +
		'end\n\n' +
		self.nodes->iterate(n; acc : String = '' | acc + n.compile() );
		
	helper context sm!State def: compile() : String =
		'state ' + self.name + '\n' +
		sm!StateMachine.allInstances()->first().edges->iterate(e; acc : String ='' |
			if (e.source.name=self.name) then
				'  ' + e.name + ' => ' + e.target.name + '\n'
			else 
				''
			endif
		) +
		'end\n\n';

This operation maps the state machine:

<img src="images/model_initial.png" height="250">

into

	events
	  a->b
	end
	
	state a
	  a->b => b
	end
	
	state b
	end

In an update of our DSL for state machines, a concept *Event* is added as an explicit class and the concept *Observation* is removed, producing a [new metamodel](https://github.com/mde-subtyping/web/blob/master/subtyping.sm.atl/extended/smEvent.emf) (**version 2**):

<img src="images/mm_SmEvent.png" height="300">

with the following constraint, ensuring the consistency of event names:

	context Transition
	inv event_consistency:
	not(self.event.oclIsUndefined) implies self.name=self.event.name

For which we can define state machines as follows:

<img src="images/model_smEvent.png" height="250">

The questions that we address next are:
* Can we reuse the model management operation for compiling state machines that conform to `version 2` of the metamodel (as the one depicted above)? 
* If so, how can we do it?

Our subtyping operation assists us in determining that `version 2` of the metamodel together with the OCL constraint is a refinement of `version 1` as shown [in this test case](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/groovy/metamodel/sm/test_sle17_evolution.groovy), which is not due to the removal of the **Observation** concept. By looking at the generated binding file and at completement of the supertype metamodel (shown below), the modeller has information to find out the source of the problem. 

<img src="images/mm_SupertypeComp.png" height="175">

To see if there exists a potential valid refinement for reusing the operation, the user can extract the effective metamodel for the ATL transformation, either:
* **manually** by inspecting the software artifacts generated by our tool and by inspecting the ATL transformation, which seems reasonable for an example of this size; or
* **automatically** by using tools like [anATLyzer](http://sanchezcuadrado.es/projects/anatlyzer/) that perform static analysis of ATL transformations.

Our tool provides a facility for prunning a metamodel given the features of interest. The computed [effective metamodel](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/resources/sle17/sm/evolution/smObservation_prunned.emf) is as follows:

<img src="images/mm_SmObservationPrunned.png" height="250">

The subtyping operation is used again to check whether the effective metamodel is a valid supertype for our metamodel (`version 2`), which is correct.

The subtyping operation also synthesizes: 
* the [extension metamodel](https://github.com/mde-subtyping/web/blob/master/subtyping.sm.atl/extended/binding0_reuse_mm.emf), depicted in class diagram notation as follows

<img src="images/mm_SmExtension.png" height="375">

* and the complement of the supertype and subtype metamodels. The complement of the subtype metamodel (shown below) tells us that the models of the subtype are only **partially typed** with the supertype metamodel, as the class *Event* is not covered.

<img src="images/mm_SubtypeComp.png" height="175">

The extension metamodel, shown above, can be used to rewrite the signature of the model management operation. In addition, as the subtyping operation had to apply some automatic renamings in order to avoid name clashes, we have to adapt the original model that conforms to **version 2** to the extension metamodel as shown in the test case [test_evolution_scenario](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/groovy/metamodel/sm/test_sle17_evolution.groovy). This operation retypes the objects in the original model according to the renamings inferred by the subtyping operation: 

<img src="images/model_renamed.png" height="250">

This model can be processed by the ATL transformation, after replacing the original metamodel with the synthesized extension metamodel. Note that the adaptation of the model is only mandatory when the set of class names in version 1 and the set of class names in version 2 are not disjoint. 

In case the renamings applied to the subtype metamodel in the extension model make the object type names different from those in the original subtype metamodel, the tool facilitates an adaptation from the extended metamodel to the original metamodel as explained in the following scenario. 




### Scenario 4: Stepwise Simulation of Deterministic State Machines (Reuse, Dynamic Typing)

In this example, we are considering the reuse of an ATL transformation used in the paper for simulating deterministic state machines stepwise by using an ATL transformation that applies a function graph to a node. The difference with the evolution example is that the simulation example generates new elements in the transformation, which are automatically re-typed in our approach, illustrating how **dynamic typing** is supported.

The steps in the scenario described below have been implemented in the test case [test_simulation_scenario](https://github.com/mde-subtyping/web/blob/master/subtyping.tests/src/test/groovy/metamodel/sm/test_sle17_sm_simulation.groovy). The resources used in the scenario are reachable from the implementation of the test case.

* The graph metamodel that has been considered is

<img src="images/mm_graph.png" height="450">

* with the well-formedness constraints

		context Edge
		inv map:
		not(Edge.allInstances()->exists(e | 
			e.source=self.source
			and
			e.target<>self.target 
		))
	

* The [ATL transformation](https://github.com/mde-subtyping/web/blob/master/subtyping.sm.atl.simulation/trafo/simulation.atl), shown below, applies a marking to the nodes of the graph by following the order imposed by the edges of the function graph. The execution of the ATL transformation simulates the application of the graph function to a node, when there is a successor node that has not been marked in the model.


		module simulation;
		create OUT : graph refining IN : graph;
		
		rule simulate {
			from 
				n1 : graph!Node ( n1.mark.oclIsUndefined() and graph!Edge.allInstances()->exists( e | e.target=n1 and not e.source.mark.oclIsUndefined()) ) 
			to
				n2 : graph!Node (
					mark <- m		
				),
				m : graph!Mark (
					graph <- n1.graph,
					time <- graph!Edge.allInstances()->select( e | e.target=n1 and not e.source.mark.oclIsUndefined())->asSequence()->first().source.mark.time + 1
				)	
		}

* An initial graph and the resulting graphs from applying the transformation twice, that is once to the input model and a second time to the model generated after step 1, are as follows:

<img src="images/graph_simulation.png" height="250">

In these models, we have obliterated the root object *Graph*.
  
As discussed in the paper, the ATL transformation could be applicable to deterministic state machines to facilitate their simulation.  The state machine metamodel that we are considering is

<img src="images/mm_simulation_sm.png" height="450">

with the well-formedness constraint

		context Transition
		inv determinism:
		not(Transition.allInstances()->exists(t | 
			t.source=self.source
			and
			t.target<>self.target 
		))

To reuse the ATL transformation, we use the subtyping operation to obtain the reuse metamodel:

<img src="images/mm_simulation_reuse.png" height="450">

In addition, we can check that the complement of the supertype metamodel only contains datatypes, indicating that all the supertype classifiers are covered by the subtyping relation and, hence, that the metamodel graph is indeed a supertype of the deterministic state machine metamodel specification.

<img src="images/mm_simulation_supertypeComp.png" height="200">

And that the complement of the subtype metamodel contains the references `initial`, `final` and `subMachines` between the classes `State` and `StateMachine`, indicating that the inferred subtyping is a **partial typing**.

<img src="images/mm_simulation_subtypeComp.png" height="200">


To **reuse** the ATL transformation for state machines, the model representing the state machine is re-typed to the extension metamodel, obtaining a model conforming to the extension metamodel, to which the ATL transformation can be applied. In this scenario, this step is optional as the re-typing does not apply any changes to the model. However, in general, such a re-typing may apply changes as illustrated in **Scenario 3: DSML evolution (Reuse, Partial Typing)**. After applying the ATL transformation, another model conforming to the extension metamodel is obtained where concepts from the supertype may have been created (such as `Mark`). A second re-typing is necessary in order to ensure that the produced model conforms to the original state machine metamodel. This process is illustrated as follows:


<img src="images/sm_simulation.png" height="550">

As discussed in the paper, the subtyping relation must be strict in order for the last re-typing to work automatically. 


## License

	MIT License
	
	Copyright (c) 2017 Artur Boronat
	
	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:
	
	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.
	
	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.


## Credits

Our tool reuses (adapting and extending) the following third-party tools as libraries:

* [TOTEM-MDE](https://github.com/totem-mde/totem): for integrating the USE validator with EMF.
* [USE validator](https://sourceforge.net/projects/useocl/) to reason about OCL constraints.
* Maude4J: An Eclipse-independent version of [Maude Development Tools](http://mdt.sourceforge.net/) for integrating Maude with Java.