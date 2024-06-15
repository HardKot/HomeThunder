"use server";

import { IApiRPC, IFileStorage, ITokenManager } from "@/shared/interface";
import { TYPES } from "@/ioc";
import { bindServerDependencies } from "@/ioc/inversify.serverContainer";

async function registrationByEmail(
  apiRpc: IApiRPC,
  tokenManager: ITokenManager,
  fileStorage: IFileStorage,
  form: FormData,
) {
  const data = await apiRpc.execute("/auth/registration", {
   firstname: form.get("firstname"),
   lastname: form.get("lastname"),
   patronymic: form.get("patronymic"),
   gender: form.get("gender"),
   birthday: form.get("birthday"),
   email: form.get("email"),
   avatarURL: form.get("avatarURL"),
   password: form.get("password"),
   confirmPassword: form.get("confirmPassword"),
  })
  
  return 200;
}

export default bindServerDependencies([
  TYPES.ApiRPC,
  TYPES.TokenManager,
  TYPES.FileStorage,
], registrationByEmail)
