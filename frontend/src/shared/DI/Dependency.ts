export class Dependency {
	private constructor(
		private name: string,
		private dependency: string[],
		private factory: (dependency: {[name: string]: any}) => any,
		private type: string,
	) {
		
	}

	build = () => this.factory(
			this.dependency
			.map((dependencyName) => Dependency.components.get(dependencyName))
	)


	private static components = new Map<string, Dependency>();
	
	static CreateComponent = (component: {
		name: string,
		dependency: string[]
		factory: (dependency: {[name: string]: any}) => any,
		type: string,
	}) => {
		const dependency = new Dependency(
			component.name,
			component.dependency,
			component.factory,
			component.type,
		);
		this.components.set(component.name, dependency); 
	}

	static BuildComponent = (componentName: string) => this.components.get(componentName)?.build();
}
