import { AxiosError, AxiosRequestConfig } from "axios";
import axiosInstance from "@/shared/api/axiosInstance";
import { BaseQueryFn } from "@reduxjs/toolkit/query";

interface AxiosBaseQueryConfig {
  baseURL?: string;
  pathname?: string;
}

type AxiosBaseQuery = (
  config: AxiosBaseQueryConfig,
) => BaseQueryFn<AxiosRequestConfig & { method: AxiosMethod }>;

export const axiosBaseQuery: AxiosBaseQuery =
  ({ baseURL, pathname = "" } = { baseURL: "" }) =>
  async ({ url, ...config }: AxiosRequestConfig) => {
    try {
      url = pathname + url;
      return await axiosInstance({
        baseURL,
        url,
        ...config,
      });
    } catch (axiosError) {
      const err = axiosError as AxiosError;
      return {
        error: {
          status: err.response?.status,
          data: err.response?.data || err.message,
        },
      };
    }
  };

export const enum AxiosMethod {
  GET = "GET",
  POST = "POST",
  PUT = "PUT",
  DELETE = "DELETE",
  UPDATE = "UPDATE",
}
