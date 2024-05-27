'use server'

import {redirect} from "next/navigation";
import {WebApi} from "@/shared/api/WebApi";
import {AuthManager} from "@/shared/api/AuthManager";

export const loginByEmail = async (formBody: string, rememberMe = false) => {
  const authManager = AuthManager.build();
  const response = await WebApi.client.post("/login", formBody);

  if (!response.ok) {
    return response.status;
  }

  const data = await response.json()
  authManager.setToken(data.token)

  redirect("/")

  return response.status;
}
