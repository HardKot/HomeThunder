"use server";

import {IApiRest, IApiRPC, ITokenManager} from "@/shared/interface";
import {TYPES} from "@/ioc";
import { bindServerDependencies } from "@/ioc/inversify.serverContainer";

async function loginByEmail(
  apiRpc: IApiRPC,
  tokenManager: ITokenManager,
  form: FormData
  ) {

  const data = await apiRpc.execute("/auth/login", {
    email: form.get("email"),
    password: form.get("password"),
  })

  return 200;
}


export default bindServerDependencies([TYPES.ApiRPC, TYPES.TokenManager], loginByEmail) as (form: FormData) => Promise<number>
