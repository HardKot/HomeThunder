import "reflect-metadata";
import { IApiRPC } from "../interfaces";
import { inject, injectable } from "inversify";
import { TYPES } from "../types";
import { AuthManager } from "./AuthManager";
import axios from "axios";
import { headers } from "next/headers";

@injectable()
export class ApiRPC<Args = undefined, Return = undefined>
  implements IApiRPC<Args, Return>
{
  constructor(@inject(TYPES.AuthManager) private authManager: AuthManager) {}

  async execute(enpoint: string, args: Args) {
    return axios
      .post<Return>(enpoint, args, {
        headers: {
          Authorization: await this.getAuthHeader(),
          "User-Agent": await this.getUserAgentHeader(),
        },
      })
      .then(({ data }) => data);
  }

  private async getAuthHeader() {
    return `Bearer ${await this.authManager.getToken()}`;
  }

  private async getUserAgentHeader() {
    return headers().get("User-Agent");
  }
}
