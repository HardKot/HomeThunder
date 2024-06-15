import { cookies } from "next/headers";
import { jwtDecode } from "jwt-decode";
import { injectable } from "inversify";
import { ITokenManager } from "@/shared/interface";
import "reflect-metadata";

@injectable()
export class TokenManager implements ITokenManager {
  private COOKIE_NAME = "Authorization";

  async getToken() {
    return cookies().get(this.COOKIE_NAME)?.value ?? null;
  }

  async setToken(token: string) {
    cookies().set(this.COOKIE_NAME, token, {
      httpOnly: true,
      secure: true,
      expires: new Date(Date.now() + 20 * 60 * 1000),
      sameSite: "lax",
      path: "/",
    });
  }

  async removeToken() {
    cookies().delete(this.COOKIE_NAME);
  }

  async hasAccess(rule: string) {
    const token = await this.getToken();
    if (!token) return false;
    return jwtDecode<{ rules: string[] }>(token).rules.includes(rule);
  }

  async updateToken(token: string): Promise<void> {
    await this.setToken(token);
  }
}
