export interface IEntity {
  id: string;

  createdAt: string;
  updateAt: string;
  deleteAt: string;
}

export interface IApiRest<Entity extends IEntity = IEntity> {
  get<Params = undefined>(
    endpoint: string,
    params: Params,
  ): Promise<{ data: Entity[] }>;
  post(endpoint: string, entity: IEntity): Promise<{ data: Entity }>;
  put(endpoint: string, id: string, entity: IEntity): Promise<{ data: Entity }>;
  patch(
    endpoint: string,
    id: string,
    entity: Partial<IEntity>,
  ): Promise<{ data: Entity }>;
  delete(endpoint: string, id: string): Promise<void>;
}

export interface ITokenManager {
  getToken(): Promise<string | null>;
  setToken(token: string): Promise<void>;
  removeToken(): Promise<void>;
  updateToken(token: string): Promise<void>;
  hasAccess(rule: string): Promise<boolean>;
}

export interface IApiRPC {
  execute<Args = undefined, Return = undefined>(enpoint: string, args?: Args): Promise<Return>;
}

export interface IAuthService {
  loginByEmail(email: string, password: string, rememberMe: boolean): Promise<{ status: boolean, message: string }>

}

export interface IMinioConfig {
  port: number,
  endpoint: string,
  accessKey: string,
  secretKey: string,
}

export interface IFileUtils {
 updateName(oldName: string): string 
}

export interface IFileStorage {
 uploadInTemp(file: string, fileData: Buffer): Promise<string> 
}
