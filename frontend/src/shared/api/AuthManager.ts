import {cookies} from "next/headers";
import {jwtDecode} from "jwt-decode";
import {IRules} from "@/shared/api/IRules";

export class AuthManager {
  private COOKIE_NAME = "Authorization"

  getToken = () => cookies().get(this.COOKIE_NAME)?.value

  setToken = (token: string) => cookies().set(this.COOKIE_NAME, token, {
    httpOnly: true,
    secure: true,
    expires: new Date(Date.now() + 20 * 60 * 1000),
    sameSite: 'lax',
    path: '/',
  })


  removeToken = () => cookies().delete(this.COOKIE_NAME)

  hasAccess = (rule: string) => {
    const token = this.getToken()
    if (!token) return false;
    return jwtDecode<IRules>(token).rules.includes(rule)
  }

  static build = () => new AuthManager()
}
