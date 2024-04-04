import {AxiosError, AxiosRequestConfig} from "axios";
import axiosInstance from "@/shared/api/axiosInstance";
import {BaseQueryFn} from "@reduxjs/toolkit/query";

interface AxiosBaseQueryConfig {
  baseUrl: string
}

type AxiosBaseQuery = (config: AxiosBaseQueryConfig) => BaseQueryFn<AxiosRequestConfig & { method: AxiosMethod }>

export const axiosBaseQuery: AxiosBaseQuery =
  ({baseUrl} = {baseUrl: ''}) =>
    async ({url, ...config}: AxiosRequestConfig) => {
      try {
        return await axiosInstance({
          url: baseUrl + url,
          ...config
        })
      } catch (axiosError) {
        const err = axiosError as AxiosError
        return {
          error: {
            status: err.response?.status,
            data: err.response?.data || err.message,
          },
        }
      }
    }

export const enum AxiosMethod {
  GET = 'GET',
  POST = 'POST',
  PUT = 'PUT',
  DELETE = 'DELETE',
  UPDATE = 'UPDATE'
}
