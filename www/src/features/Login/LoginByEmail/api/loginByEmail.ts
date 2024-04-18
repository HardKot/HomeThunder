import {UserLogin} from "@/entities/user/models/UserLogin";
import {cookies} from "next/headers";
import {redirect} from "next/navigation";
import axios from "axios";

export const loginByEmail = async (form: UserLogin) => {
  const { data, status} = await axios.post("/login", form);
  if (status !== 200) throw "Error"

  const oneDay = 24 * 60 * 60 * 1000

  cookies().set("Authorized", data.token, {
    secure: true,
    expires: new Date(Date.now() + (form.rememberMe ? 28 : 0.25) * oneDay)
  })


  redirect("/")

  return data;
}
