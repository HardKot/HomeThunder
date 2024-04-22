import {AuthManager} from "@/shared/api/AuthManager";
import {headers} from "next/headers";

export class WebApi {

  private readonly baseUrl: URL
  private readonly authManager = AuthManager.build()

  constructor(host: string) {
    this.baseUrl = new URL(host)
  }

  public get = (endpoint: string, params?: {[key: string]: string}) => {
    const url = new URL(endpoint, this.baseUrl)
    if (params) {
      for (const param in params) {
        url.searchParams.append(param, params[param])
      }
    }

    return this.request(url, "GET");
  }

  public post = (endpoint: string, body: any) => this.request(new URL(endpoint, this.baseUrl), "POST", body);

  public put = (endpoint: string, body: any) => this.request(new URL(endpoint, this.baseUrl), "PUT", body);


  private request = (url: URL, method: string, body?: string) => fetch(url, {
    method,
    headers: {
      ...this.authorizationHeader(),
      ...this.userAgentHeader(),
      "Content-Type": "application/json"
    },
    body
  })

  private authorizationHeader = (): {} | { Authorization: string } => {
    const token = this.authManager.getToken();
    if (!token) return {};
    return { Authorization: `Bearer ${token}` }
  }

  private userAgentHeader = (): { "User-Agent"?: string } => ({
    "User-Agent":  headers().get("User-Agent") ?? undefined
  })


  static client = new WebApi(process.env.APP_HOST ?? "http://localhost:8080")
}
