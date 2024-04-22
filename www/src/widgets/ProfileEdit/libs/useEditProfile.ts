'use client'

import {useForm} from "react-hook-form";
import {UserData} from "@/entities/user";
import {useCallback} from "react";
import {submitUserDataa} from "@/entities/user/api/submitUserData";

export const useEditProfile = () => {
  const formMethod = useForm<UserData>();

  const onSave = useCallback((form: UserData) => submitUserDataa(JSON.stringify(form)), [])

  const onSubmit = formMethod.handleSubmit(onSave)

  return {
    formMethod,
    onSubmit
  }
}
