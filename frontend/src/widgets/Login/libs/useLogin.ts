import {headers} from "next/headers";

export const useLogin = () => {
  const csrf_token = headers().get('X-CSRF-Token') || 'missing';


  return {
    csrf_token
  };
};
