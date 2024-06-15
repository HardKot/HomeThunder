import { Container } from "inversify";


const appContainer = new Container();


export function binAppDependencies(dependencies = [], func) {
  const injections = dependencies.map(dependency => appContainer.get(dependency));
  return func.bind(func, ...injections)
}

export { appContainer }
