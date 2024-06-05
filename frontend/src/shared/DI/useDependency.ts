import { useRef } from "react";
import { Dependency } from "./Dependency";

export const useDependency = (name: string) => {
	const dependecyContainer = useRef(Dependency.BuildComponent(name));

	return dependecyContainer.current;
}
