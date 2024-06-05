import { Dependency } from "../DI/Dependency";

export class ViewModelBuilder<ViewProps, ViewModel, Models = Record<string, any>> {
	private dependency: string[] = [];
	private factory?: (props: ViewProps & Models) => ViewModel;
	
	private constructor() {}

	setDependency = (value: string[]) => {
		this.dependency = value;
		return this;
	}

	addDependency = (value: string) => {
		this.dependency.push(value)
		return this;
	}

	setFactory = (factory: (props: ViewProps & Models) => ViewModel) => {
		this.factory = factory;
		return this;
	}

	build = () => {
		const models = Object.fromEntries(
			this.dependency
				.map(dependencyName => ([dependencyName, Dependency.BuildComponent(dependencyName)]))
		) as unknown as Models;
		const factory = this.factory; 
		if (!factory) throw new Error("Factory not found");
		return (props: ViewProps) => factory({ ...props, ...models })
	}
}
