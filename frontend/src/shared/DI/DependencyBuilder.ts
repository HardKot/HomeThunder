import { Dependency } from "./Dependency";

export class DependencyBuilder {
	private name?: string;
	private factory?: (dependency: {[name: string]: any}) => any;
	private type?: string;
	private dependency?: string[];
	

	private constructor() {
	}

	setName = (value: string) => {
		this.name = value;
		return this;
	}

	setFactory = (value: {(dependencys: {[keyof: string]: any}): any}) => {
		this.factory = value;
		return this;
	}
	
	setType = (value: string) => {
		this.type = value;
		return this;
	}

	setDependency = (value: string[]) => {
		this.dependency = value;
		return this;
	}

	setServiceType = () => {
		this.type = 'service';
		return this;
	}

	build = () => {
		if (!this.name) throw new Error("Name in Compmonent is empty"); 
		if (!this.type) throw new Error("Type in Compmonent is empty"); 
		if (!this.factory) throw new Error("Factory in Component is empty");
		if (!this.dependency) throw new Error("Dependencys in Component is empty"); 
		Dependency.CreateComponent({
			name: this.name,
			type: this.type,
			factory: this.factory,
			dependency: this.dependency
		})
	}
}
