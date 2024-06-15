import "reflect-metadata";
import {IApiRPC, ITokenManager} from "@/shared/interface"
import { inject, injectable } from "inversify";
import { TYPES } from "../types";
import axios from "axios";
import { headers } from "next/headers";
import "./axiosApp";


@injectable()
export class ApiRPC implements IApiRPC
{
  constructor(@inject(TYPES.TokenManager) private tokenManager: ITokenManager) {}

  async execute<Args, Return>(enpoint: string, args: Args) {
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
    return `Bearer ${await this.tokenManager.getToken()}`;
  }

  private async getUserAgentHeader() {
    return headers().get("User-Agent");
  }
}
