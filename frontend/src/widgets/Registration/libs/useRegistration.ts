'use client'

import { useForm } from "react-hook-form";
import { RegistrationForm, useRegistrationMutation } from "@/entities/user";
import {useCallback} from "react";
import {useRouter} from "next/navigation";

export const useRegistration = () => {
  const formMethod = useForm<RegistrationForm>();
  const [registrationTrigger, { isLoading }] = useRegistrationMutation();
  const router = useRouter();

  const registration = useCallback(async (form: RegistrationForm) => {
    await registrationTrigger(form).unwrap()
    router.push("/home")
  }, [router, registrationTrigger])

  const onSubmit = formMethod.handleSubmit(registration);

  return {
    onSubmit,
    isLoading,
    formMethod,
  };
};
