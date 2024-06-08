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

export interface IAuthManager {
  getToken(): Promise<string | null>;
  setToken(token: string): Promise<void>;
  removeToken(): Promise<void>;
  updateToken(token: string): Promise<void>;
  hasAccess(rule: string): Promise<boolean>;
}

export interface IApiRPC<Args = undefined, Return = undefined> {
  execute(enpoint: string, args: Args): Promise<Return>;
}
