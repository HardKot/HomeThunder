'use client'

import { useForm } from "react-hook-form";
import { UserLogin } from "@/entities/user/models/UserLogin";
import {loginByEmail} from "@/features/Login/LoginByEmail/api/loginByEmail";
import {useCallback} from "react";

export const useLogin = () => {
  const formMethod = useForm<UserLogin>({ defaultValues: { rememberMe: false } });

  const login = useCallback(({ rememberMe, ...form }: UserLogin) =>
    loginByEmail(JSON.stringify(form), rememberMe).then(status => {
      if (status === 400) formMethod.setError(`email`, { message: "Неверный логин или пароль"  })
    }), [])

  const onSubmit = formMethod.handleSubmit(login);

  return {
    formMethod,
    onSubmit,
  };
};
