import {AuthManager} from "@/shared/api/AuthManager";
import {WebApi} from "@/shared/api/WebApi";
import {redirect} from "next/navigation";

export const submitUserDataa = async (formBody: string) => {
  const authManager = AuthManager.build();
  const response = await WebApi.client.put("/self", formBody);

  if (!response.ok) {
    return response.status;
  }

  const data = await response.json()
  authManager.setToken(data.token)

  redirect("/")

  return response.status;
}
