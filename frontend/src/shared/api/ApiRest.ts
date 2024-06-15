import { IApiRest } from "@/ioc/interface";
import axios from "axios";
import { headers } from "next/headers";
import { inject, injectable } from "inversify";
import { TYPES } from "../types";
import "reflect-metadata";
import {IEntity, ITokenManager} from "@/shared/interface";
import "./axiosApp";

@injectable()
export class ApiRest<Entity extends IEntity = IEntity>
  implements IApiRest<Entity>
{
  constructor(@inject(TYPES.TokenManager) private tokenManager: ITokenManager) {}

  async get<Params = undefined>(endpoint: string, params: Params) {
    return axios.get<Entity[]>(`${endpoint}`, {
      params,
      headers: {
        Authorization: await this.getAuthHeader(),
        "User-Agent": await this.getUserAgentHeader(),
      },
    });
  }

  async post(endpoint: string, entity: Entity) {
    return axios.post<Entity>(endpoint, entity, {
      headers: {
        Authorization: await this.getAuthHeader(),
        "User-Agent": await this.getUserAgentHeader(),
      },
    });
  }

  async patch(endpoint: string, id: string, entity: Partial<IEntity>) {
    return axios.patch<Entity>(`${endpoint}/${id}`, entity, {
      headers: {
        Authorization: await this.getAuthHeader(),
        "User-Agent": await this.getUserAgentHeader(),
      },
    });
  }

  async put(endpoint: string, id: string, entity: IEntity) {
    return axios.put<Entity>(`${endpoint}/${id}`, entity, {
      headers: {
        Authorization: await this.getAuthHeader(),
        "User-Agent": await this.getUserAgentHeader(),
      },
    });
  }

  async delete(endpoint: string, id: string): Promise<void> {
    return axios.delete(`${endpoint}/${id}`, {
      headers: {
        Authorization: await this.getAuthHeader(),
        "User-Agent": await this.getUserAgentHeader(),
      },
    });
  }

  private async getAuthHeader() {
    return `Bearer ${await this.tokenManager.getToken()}`;
  }

  private async getUserAgentHeader() {
    return headers().get("User-Agent");
  }
}
