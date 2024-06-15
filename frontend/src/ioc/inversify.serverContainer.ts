import { Container } from "inversify";
import { IApiRPC, IApiRest, ITokenManager } from "@/shared/interface";
import { TYPES } from "./types";
import {
  TokenManager,
  ApiRest,
  ApiRPC,
} from "@/shared/api";


const serverContainer = new Container();

serverContainer.bind<ITokenManager>(TYPES.TokenManager).to(TokenManager);
serverContainer.bind<IApiRest>(TYPES.ApiRest).to(ApiRest);
serverContainer.bind<IApiRPC> (TYPES.ApiRPC).to(ApiRPC);

export function bindServerDependencies(dependencies = [], func) {
  const injections = dependencies.map(dependency => serverContainer.get(dependency));
  return func.bind(func, ...injections)
}

export { serverContainer }
