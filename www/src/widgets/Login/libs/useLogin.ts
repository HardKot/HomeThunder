'use client'

import { useForm } from "react-hook-form";
import { useLoginMutation } from "@/entities/user/api/userAPI";
import { UserLogin } from "@/entities/user/models/UserLogin";
import { useRouter } from 'next/navigation'
import {useCallback} from "react";

export const useLogin = () => {
  const formMethod = useForm<UserLogin>();
  const [loginTrigger, {  }] = useLoginMutation();
  const router = useRouter();

  const login = useCallback(async (form: UserLogin) => {
    await loginTrigger(form)
    router.push("/home")
  }, [router, loginTrigger])

  const onSubmit = formMethod.handleSubmit(login);

  return {
    formMethod,
    onSubmit,
  };
};
