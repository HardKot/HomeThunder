import { Container } from "inversify";
import { TYPES } from "./types";
import { FileUtils } from "@/shared/libs/FileUtils";


const appContainer = new Container();

appContainer.bind(TYPES.FileUtils).to(FileUtils);

export function binAppDepenencies(dependencies = [], func) {
  const injections = dependencies.map(dependency => appContainer.get(dependency));
  return func.bind(func, ...injections)
}

export { appContainer }
