import { Container } from "inversify";
import { IApiRPC, IApiRest, IFileStorage, IFileUtils, IMinioConfig, ITokenManager } from "@/shared/interface";
import { TYPES } from "./types";
import {
  TokenManager,
  ApiRest,
  ApiRPC,
} from "@/shared/api";
import { FileUtils } from "@/shared/libs/FileUtils";
import { FileStorage } from "@/shared/api/FileStorage";


const serverContainer = new Container();

serverContainer.bind<ITokenManager>(TYPES.TokenManager).to(TokenManager);
serverContainer.bind<IApiRest>(TYPES.ApiRest).to(ApiRest);
serverContainer.bind<IApiRPC> (TYPES.ApiRPC).to(ApiRPC);
serverContainer.bind<IFileUtils>(TYPES.FileUtils).to(FileUtils);
serverContainer.bind<IFileStorage>(TYPES.FileStorage).to(FileStorage);
serverContainer.bind<IMinioConfig>(TYPES.MinioConfig).toConstantValue({ 
  port: 9000, 
  endpoint: "localhost", 
  secretKey: "4M59rtW7CkZSUNXbaAgOKZ9Cfi4SynktLlYY3YDc",
  accessKey: "88z5hDnf1DWLQrxANCnb"
})

export function bindServerDependencies(dependencies = [], func) {
  const injections = dependencies.map(dependency => serverContainer.get(dependency));
  return func.bind(func, ...injections)
}

export { serverContainer }
